package org.sid.secservice.sec.controllers;
import com.lowagie.text.DocumentException;
import org.sid.secservice.sec.dtos.DevisDTO;
import org.sid.secservice.sec.dtos.MainOeuvreDTO;
import org.sid.secservice.sec.dtos.RevisionDTO;
import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.services.AccountService;
import org.sid.secservice.sec.services.UserDetailServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xhtmlrenderer.pdf.ITextRenderer;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devis")
public class DevisController implements WebMvcConfigurer {

    private final AccountService accountService;



    public DevisController(AccountService accountService, UserDetailServiceImpl userDetailService) {
        this.accountService = accountService;
    }

    @PostMapping("/generer-devis")//done
    public String genererDevis(@RequestBody DevisDTO devisDTO, Model model, Principal principal) throws IOException {
        Optional<AppUser> user = accountService. LoadUserByUsername(principal.getName());
        Moteur m = accountService.getMoteurByName(devisDTO.getMotorisation());
        Voiture voiture= accountService.getVoiture(m,devisDTO.getModele());
        List<Services> services = new ArrayList<>();
        devisDTO.getServices().forEach(e -> {
            System.out.println(e.getId());
            Optional<Services> optionalService = accountService.getService(e.getId());
            optionalService.ifPresent(services::add);
        });

        Devis deviss = new Devis(null,null,user.get(),voiture,new Date(),services , devisDTO.getCout());
        deviss.setTitre_devis(devisDTO.getFirstName()+"_"+devisDTO.getLastName()+"_"+devisDTO.getTypePack().split(" ")[1]);
        accountService.addNewDevis(deviss);
        model.addAttribute("devis", deviss);

        Date currentDate = new Date();

        // Create SimpleDateFormat instances with the desired formats
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        String formattedDateTime = dateTimeFormat.format(currentDate);

        // Chemin du fichier HTML personnalisé
        String fileName = deviss.getTitre_devis()+ ".html";
        File file = new File("src/main/resources/storage", fileName);

        StringBuffer ref1 = new StringBuffer();
        StringBuffer des1= new StringBuffer();
        StringBuffer quan1 = new StringBuffer();
        StringBuffer remise1 = new StringBuffer();
        StringBuffer prixUnitaire1 = new StringBuffer();
        StringBuffer ref2 = new StringBuffer();
        StringBuffer des2= new StringBuffer();
        StringBuffer quan2 = new StringBuffer();
        StringBuffer remise2 = new StringBuffer();
        StringBuffer prixUnitaire2 = new StringBuffer();
        double MTHT2 =0;
        double MTHT1 =0;
        double total;
        int padding=300;
        List<RevisionDTO> r = accountService.listSerParV(voiture);
        for(RevisionDTO service : devisDTO.getServices()){
            padding-=12;
            for (MainOeuvreDTO mainOeuvreDTO : service.getMainOeuvre()){
                padding-=12;

                ref2.append("<div>"+mainOeuvreDTO.getId()+"</div>\n");
                des2.append("<div>"+mainOeuvreDTO.getNom()+"</div>\n");
                quan2.append("<div>"+mainOeuvreDTO.getId()+"</div>\n");
                remise2.append("<div>"+mainOeuvreDTO.getId()+"</div>\n");
                prixUnitaire2.append("<div>"+mainOeuvreDTO.getCoutMO()+"</div>\n");
                MTHT2+=mainOeuvreDTO.getCoutMO();
            }

            ref1.append("<div>"+service.getId()+"</div>\n");
            des1.append("<div>"+service.getNom()+"</div>\n");
            quan1.append("<div>"+service.getId()+"</div>\n");
            remise1.append("<div>"+service.getId()+"</div>\n");
            prixUnitaire1.append("<div>"+service.getCouSer()+"</div>\n");
            MTHT1+=service.getCouSer();
        }
        total=MTHT2+MTHT1;
        double ht = total+ total*0.2;
        String html ="<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\" />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"header\">\n" +
                "      <div class=\"a1\">\n" +
                "        <img src=\"th.jpg\" alt=\"\" />\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div class=\"a2\">\n" +
                "      <div class=\"a3\">\n" +
                "        <h2>"+deviss.getTitre_devis()+"</h2>\n" +
                "        <p>Date "+formattedDateTime+"</p>\n" +
                "        <p>page 1</p>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "      <div class=\"a4\">\n" +
                "        <div>\n" +
                "          <div>\n" +
                "            <p>CL21858</p>\n" +
                "            <p>MADEC</p>\n" +
                "            <p>ICE: 001224566287931101</p>\n" +
                "            <p>RTE D'EL JADIDA LISSASFA</p>\n" +
                "            <p>"+ devisDTO.getTypePack()+"</p>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <div class=\"a6\">\n" +
                "        <div>\n" +
                "          <table>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>Marque</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>AUDI</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>Type</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>"+voiture.getModele()+voiture.getFinition()+"</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>N° de chassis</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>WAUZZZZZF55JA015906</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>Livré le</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>"+formattedDate+"</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>KM</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>0</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>N° Immatriculation</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>71300-H-1</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "\n" +
                "      <div class=\"a7\">\n" +
                "        <div>\n" +
                "          <table>\n" +
                "            <tr>\n" +
                "              <td colspan=\"2\">\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>Condition de paiement</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>Comptant</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>N° </b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>DS24-0108625</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>Du</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span>"+formattedDate+"</span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td colspan=\"2\">\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>recu par </b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span></span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>N° Dossier SF</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span></span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "              <td>\n" +
                "                <div>\n" +
                "                  <div class=\"first\"><b>N° Devis SF</b></div>\n" +
                "                  <div class=\"second\">\n" +
                "                    <span></span>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div class=\"a8\">\n" +
                "      <div class=\"a9\">\n" +
                "        <table>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div><b>reference</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>Designation</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>quantité</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>Remise</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>P.U HT</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>Montant HT</b></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                ref2+
                "            </td>\n" +
                "            <td>\n" +
                des2+
                "            </td>\n" +
                "            <td>\n" +
                quan2+
                "            </td>\n" +
                "            <td>\n" +
                "            </td>\n" +
                "            <td>\n" +
                quan2+
                "            </td>\n" +
                "            <td>\n" +
                prixUnitaire2+
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"6\">\n" +
                "              <div>\n" +
                "                <b>Sous total : </b>\n" +
                "                <b style=\"margin-left: 566px\">"+ String.format("%.2f", MTHT2) +"</b>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                ref1+
                "              <div style=\"height: "+String.valueOf(padding)+"px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                des2+
                "              <div style=\"height:"+String.valueOf(padding)+"px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                quan1+
                "              <div style=\"height: "+String.valueOf(padding)+"px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                quan1+
                "              <div style=\"height: "+String.valueOf(padding)+"px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                prixUnitaire1+
                "              <div style=\"height: "+String.valueOf(padding)+"px\"></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"6\">\n" +
                "              <div>\n" +
                "                <b>Sous total : </b>\n" +
                "                <b style=\"margin-left: 566px\">"+String.format("%.2f", MTHT1)+"</b>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "      <div class=\"a10\">\n" +
                "        <table>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div><b>Identificant TVA</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>% TVA</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>Montant HT</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>Montant TVA</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>Total TTC</b></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div>TVA20</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>20</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>"+total+"</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>"+String.valueOf(ht-total)+"</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>"+ht+"</div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>"+total+"</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>"+String.valueOf(ht-total)+"</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>"+ht+"</b></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";

        try (FileWriter writer = new FileWriter(file)) {
            // Écriture du contenu HTML dans le fichier
            writer.write(html);
        }
        String htmlFilePath = "src/main/resources/storage/" + fileName;
        String pdfFilePath = "src/main/resources/storage/" + fileName.replace(".html", ".pdf");

        File htmlFile = new File(htmlFilePath);
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (OutputStream os = Files.newOutputStream(Paths.get(pdfFilePath))) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext cntxt = renderer.getSharedContext();
            cntxt.setPrint(true);
            cntxt.setInteractive(false);

            String baseUrl = FileSystems.getDefault().getPath("src/main/resources/storage")
                    .toUri().toURL().toString();
            renderer.setDocumentFromString(doc.html(), baseUrl);

            renderer.layout();
            renderer.createPDF(os);
            if (htmlFile.exists()) {
                htmlFile.delete();
            }

            return  fileName;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-pdf/{fileName}")
    public ResponseEntity<InputStreamResource> getPDF(@PathVariable String fileName) throws IOException {
        String pdfFilePath = "src/main/resources/storage/" + fileName;
        File pdfFile = new File(pdfFilePath);

        if (!pdfFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfFile.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
