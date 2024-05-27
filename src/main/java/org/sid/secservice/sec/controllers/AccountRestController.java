package org.sid.secservice.sec.controllers;

import org.sid.secservice.sec.dtos.*;
import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Principal;
import java.util.*;


@RestController
@RequestMapping("/api/test")

public class AccountRestController implements WebMvcConfigurer {

    private final AccountService accountService;
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }




    // @GetMapping(path = "/refreshToken")
    // public void refreshToken(HttpServletRequest request , HttpServletResponse response) throws Exception{
    //     String authToken = request.getHeader(JWTUtil.AUTH_HEADER);
    //     if(authToken!=null && authToken.startsWith(JWTUtil.PREFIX)){
    //         try{
    //             String jwt = authToken.substring(JWTUtil.PREFIX.length());//length = 7
    //             Algorithm algo = Algorithm.HMAC256(JWTUtil.SECRET);
    //             JWTVerifier jwtVerifier = JWT.require(algo).build();
    //             DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
    //             String username = decodedJWT.getSubject();
    //             AppUser appUser = accountService.LoadUserByUsername(username);
    //             String jwtAccessToken = JWT.create()
    //                     .withSubject(appUser.getUsername())
    //                     .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
    //                     .withIssuer(request.getRequestURL().toString())
    //                     .withClaim("role",appUser.getAppRole().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
    //                     .sign(algo);
    //             Map<String,String >idToken = new HashMap<>();
    //             idToken.put("acces-token",jwtAccessToken);
    //             idToken.put("refresh-token",jwt);
    //             response.setContentType("application/json");
    //             new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    //         }catch (Exception e){

    //             throw e;
    //         }
    //     }
    //     else {
    //         throw new RuntimeException("Refresh token required");
    //     }
    // }

    // @GetMapping(path = "/profile")
    // public AppUser profile(Principal principal){
    //     return accountService.LoadUserByUsername(principal.getName());
    // }

     @GetMapping(path = "/historique")
     public List<Devis> historique(Principal principal ){
        Optional<AppUser> user = accountService.LoadUserByUsername(principal.getName());
//         Optional<AppUser> user = accountService.user( id);
         List<Devis> devisList =accountService.listeDevisByclient(user.get());                                       // work with success
         /*List<Historique> historiques = new ArrayList<>();
         devisList.forEach(e->{
             Historique htr = new Historique(e.getTitre_devis(),e.getDateCreation(), e.getStatusDevis());
             historiques.add(htr);
         });*/
         return /*historiques*/ devisList;
     }

//    @GetMapping(path = "/Revision")
//    public List<Packages> Revision(@RequestBody RevisionForm revisionForm){
//      Moteur moteur = accountService.getMoteurByName(revisionForm.getPuissance());                  // working perfectly
//      Voiture voiture = accountService.getVoiture(moteur , revisionForm.getModele());
//      List<Packages> packages = accountService.getPackByTypeNVtr(voiture , "sdqdsqdqsdsqdsqdqsdq");
//       return packages;
//    }

    @GetMapping(path = "/listeService")
    public List<Services> services (){
        return accountService.listService();
    }
    @GetMapping(path = "/getDevis")
    public List<Devis> getAllDevis(){
        return accountService.listDevis();
    }
    @GetMapping(path = "/getServices")
    public List<Services> getAllServices(){
        return accountService.listService();
    }
    @GetMapping(path = "/PrixSer")
    public List<PrixServices> prixservice(){
//        Moteur moteur = accountService.getMoteur(1l).get();
//        Voiture voiture = accountService.getVoiture(moteur , "A3");
//        Optional<Services> services= accountService.getService(1L);
//      return accountService.getPrixServices(voiture, services.get());
        return accountService.prixservices();
    }
    @GetMapping(path = "/PrixMo")
    public List<Prixmainouevre> prixmainouevres( ){
        return accountService.getPrixMainOeuvre();
    }
    @GetMapping(path = "/Pack")
    public List<Packages> getpack( ){
        return accountService.getPack();
    }
    @GetMapping(path = "/PackV")
    public Packages getpackV(@RequestBody Voiture voiture){
        return accountService.getPackByTypeNVtr(voiture ,"Vidange Simple 10 000KM");
    }

    @GetMapping(path = "/PreDevis")
    public PreDevisDTO getPreDevis (@RequestBody FormDTO client){
        Moteur moteur = accountService.getMoteurByName(client.getMotorisation());
        Voiture v = accountService.getVoiture(moteur,client.getModele());
        Packages pack = accountService.getPackByTypeNVtr(v, client.getKilometrage());
        PackDTO packDTO = new PackDTO();

        for (Revision r : pack.getRevision()) {
            RevisionDTO rvsionDTO = new RevisionDTO();
            PrixServices prixService = accountService.getPrixServices(v,r);
            rvsionDTO.setId(r.getIdService());
            rvsionDTO.setNom(r.getNom());
            rvsionDTO.setCouSer(prixService.getPrixServVoitr());
            for (MainOeuvre m : r.getMainOeuvre()) {
                MainOeuvreDTO mainDTO = new MainOeuvreDTO();
                Prixmainouevre prixmainouevre =accountService.getPrixMainOeuvre(v,m);
                 mainDTO.setId(prixmainouevre.getIdPrixMO());
                mainDTO.setNom(prixmainouevre.getMainOeuvre().getNom());
                mainDTO.setCoutMO(prixmainouevre.getPrixServVoitrMo());
                rvsionDTO.getMainOeuvre().add(mainDTO);
            }
           packDTO.getService().add(rvsionDTO);
        }
        packDTO.setId(pack.getCodePack());
        packDTO.setType(pack.getType());
        //---------------------------------------------------------
        List<RevisionDTO> revisionDTOList= accountService.listSerParV(v);
        //List des RevionDTO
        return new PreDevisDTO(packDTO ,revisionDTOList );
    }

    @GetMapping(path = "/ListVoiture/search")
    public List<VoitureDTO> getVoiture( @RequestParam(name = "keyword",defaultValue = "") String keyword){
        return accountService.listVoiture(keyword);
    }
    @GetMapping(path = "/ListMoteur")
    public List<Moteur> getMoteur( ){
        return accountService.listMoteur();
    }
    @PostMapping(path = "/saveVoiture")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public Voiture saveUser( @RequestBody VoitureDTO voitureDTO){

        return accountService.addVoiture(voitureDTO);
    }
    @DeleteMapping(path = "/deleteVoiture/{id}")
    public void deleteVoiture(@PathVariable Long id ){
        accountService.deleteVoiture(id);
    }

    @PostMapping(path = "/saveMoteur")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public Moteur saveMoteur( @RequestBody MoteurDTO moteurDTO){

        return accountService.addNewMoteur(moteurDTO);
    }

    @GetMapping(path = "/ListMoteurPuissance")
    public List<String> getMoteurbyPuissance( ){
        List<Moteur> m = accountService.listMoteur();
        List<String>motorisation = new ArrayList<>();
        m.forEach(moteur -> {
            motorisation.add(moteur.getPuissance());
        });
        return motorisation;
    }
    @GetMapping(path = "/ListModele")
    public List<String> getModele( ){
        List<VoitureDTO> v = accountService.listVoiture("");
        List<String>modele = new ArrayList<>();
        v.forEach(voitureDTO -> {
            modele.add(voitureDTO.getModele());
        });
        return modele;
    }
    @GetMapping(path = "/ListPackType")
    public List<String> getPackType( ){
        List<Packages> v = accountService.getPack();
        List<String>type = new ArrayList<>();
        v.forEach(pack -> {
            type.add(pack.getType());
        });
        return type;
    }



}
