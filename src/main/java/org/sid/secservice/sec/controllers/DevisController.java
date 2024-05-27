package org.sid.secservice.sec.controllers;
import com.lowagie.text.DocumentException;
import org.sid.secservice.sec.dtos.DevisDTO;
import org.sid.secservice.sec.entities.Devis;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/devis")
public class DevisController implements WebMvcConfigurer {

    private final AccountService accountService;


    public DevisController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/generer-devis")//done
    public String genererDevis(@RequestBody DevisDTO devisDTO, Model model) throws IOException {

        Devis deviss = devisDTO.getDevis();
        deviss.setServices(devisDTO.getServices());
        accountService.addNewDevis(deviss);
        model.addAttribute("devis", deviss);

        Date currentDate = new Date();

        // Create SimpleDateFormat instances with the desired formats
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        String formattedDateTime = dateTimeFormat.format(currentDate);

        // Chemin du fichier HTML personnalisé
        String fileName = "devis-" + deviss.getCode_devis() + ".html";
        File file = new File("src/main/resources/storage", fileName);

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
                "        <h2>Devis Service DS24-010828</h2>\n" +
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
                "            <p>KM N° 10.5</p>\n" +
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
                "                    <span>A5 --IMPORTE--</span>\n" +
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
                "              <div>54254625</div>\n" +
                "              <div>qsdqsdqs625</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>Revétement centre déposer et reposer</div>\n" +
                "              <div>Cache:déposer et reposer</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>150,00</div>\n" +
                "              <div>60,00</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>3,10</div>\n" +
                "              <div>3,30</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>465,00</div>\n" +
                "              <div>198,00</div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"6\">\n" +
                "              <div>\n" +
                "                <b>Sous total : </b>\n" +
                "                <b style=\"margin-left: 566px\">654.50</b>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div>8w6825208B</div>\n" +
                "              <div>8W68252067</div>\n" +
                "              <div style=\"height: 300px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>REVETEMENT DE SOUBASSEMENT D</div>\n" +
                "              <div>CARENAGE DE DESOUS DE CAISE</div>\n" +
                "              <div style=\"height: 300px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>1,00</div>\n" +
                "              <div>4,00</div>\n" +
                "              <div style=\"height: 300px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>90,95</div>\n" +
                "              <div>1578,78</div>\n" +
                "              <div style=\"height: 300px\"></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>363,81</div>\n" +
                "              <div>1578,78</div>\n" +
                "              <div style=\"height: 300px\"></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"6\">\n" +
                "              <div>\n" +
                "                <b>Sous total : </b>\n" +
                "                <b style=\"margin-left: 566px\">654.50</b>\n" +
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
                "              <div>4655.03</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>931.20</div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div>5587.16</div>\n" +
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
                "              <div><b>4655.03</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>931.20</b></div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <div><b>5587.16</b></div>\n" +
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
        try (OutputStream os = new FileOutputStream(pdfFilePath)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext cntxt = renderer.getSharedContext();
            cntxt.setPrint(true);
            cntxt.setInteractive(false);

            String baseUrl = FileSystems.getDefault().getPath("src/main/resources/storage")
                    .toUri().toURL().toString();
            renderer.setDocumentFromString(doc.html(), baseUrl);

            renderer.layout();
            renderer.createPDF(os);
            System.out.println("done");
            if (htmlFile.exists()) {
                htmlFile.delete();
            }


            return "redirect:/api/devis/convertir-pdf/" + fileName;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
