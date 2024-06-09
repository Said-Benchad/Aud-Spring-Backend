package org.sid.secservice.sec.controllers;

import org.sid.secservice.sec.dtos.*;
import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.services.AccountService;
import org.sid.secservice.sec.services.UserDetailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.text.html.parser.Entity;
import java.security.Principal;
import java.util.*;


@RestController
@RequestMapping("/api/test")

public class AccountRestController implements WebMvcConfigurer {

    private final AccountService accountService;
    private final UserDetailServiceImpl userDetailService;
    public AccountRestController(AccountService accountService , UserDetailServiceImpl userDetailService) {
        this.accountService = accountService;
        this.userDetailService = userDetailService;
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
    public List<Services> services ( Principal principal){
        UserDetails user =  userDetailService.loadUserByUsername(principal.getName());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
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

    /*@GetMapping(path = "/PreDevis")
    public PreDevisDTO getPreDevis (@RequestBody FormDTO client){
        Moteur moteur = accountService.getMoteurByName(client.getMotorisation());
        Voiture v = accountService.getVoiture(moteur,client.getModele());
        Packages pack = accountService.getPackByTypeNVtr(v, client.getKilometrage());*/
    @GetMapping(path = "/PreDevis")
    public PreDevisDTO getPreDevis (@RequestParam String motorisation,
                                    @RequestParam String modele,
                                    @RequestParam String kilometrage){

        Moteur moteur = accountService.getMoteurByName(motorisation);
        Voiture v = accountService.getVoiture(moteur,modele);
        Packages pack = accountService.getPackByTypeNVtr(v, kilometrage);

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
        return removeDuplicates(modele);
    }
    public static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }
    @GetMapping(path = "/ListFinition")
    public List<String> getfinition( @RequestParam String modele){
        List<VoitureDTO> v = accountService.listVoiture(modele);
        List<String>m = new ArrayList<>();
        v.forEach(voitureDTO -> {
            m.add(voitureDTO.getFinition());
        });
        return removeDuplicates(m);
    }

    @GetMapping(path = "/ListMotrorisation")
    public List<String> getMotorisation(@RequestParam String modele ,@RequestParam String finition){
        List<VoitureDTO> v = accountService.listVoiture(modele ,finition);
        List<String>m = new ArrayList<>();
        v.forEach(voitureDTO -> {
            m.add(voitureDTO.getMoteur());
        });
        return removeDuplicates(m);
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
    @GetMapping(path = "/devisNB")
    public int getcountDevis() {
        return accountService.countDevis();
    }
    @GetMapping(path = "/userNB")
    public int getcountUser() {
        return accountService.countUsers();
    }
    @GetMapping(path = "/serviceNB")
    public int getcountService() {
        return accountService.coutServices();
    }
    @GetMapping(path = "/revenue")
    public double getRevenue() {
        return accountService.getRevenue();
    }

    @GetMapping("/DevisParMois")
    public ResponseEntity<Map<String, List<Integer>>> getDevisByMonth() {

        List<Integer> vidangeSimpleData = Arrays.asList(70, 69, 95, 145, 182, 215, 252, 265, 233, 183, 139, 196);
        List<Integer> vidangeToutLesFiltresData = Arrays.asList(47, 52, 44, 35, 58, 69, 32, 53, 71, 82, 99, 159);

        Map<String, List<Integer>> responseData = new HashMap<>();
        responseData.put("vidangeSimpleData", vidangeSimpleData);
        responseData.put("vidangeToutLesFiltresData", vidangeToutLesFiltresData);

        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/DevisParUti")
    public List<Map<String, Object>> getDevisByUser() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        String redHex = String.format("%02X", red);
        String greenHex = String.format("%02X", green);
        String blueHex = String.format("%02X", blue);
        List<Map<String, Object>> data = new ArrayList<>();

        // Sample data, you would fetch this from your database or other source
        Map<String, Object> amineData = new HashMap<>();
        amineData.put("name", "Amine");
        amineData.put("y", 14);
        amineData.put("color", "#" + redHex + greenHex + blueHex);
        data.add(amineData);

        Map<String, Object> wassimData = new HashMap<>();
        wassimData.put("name", "Wassim");
        wassimData.put("y", 5);
        wassimData.put("color", "#7e0505");
        data.add(wassimData);

        Map<String, Object> saidData = new HashMap<>();
        saidData.put("name", "Said");
        saidData.put("y", 10);
        saidData.put("color", "#ed9e20");
        data.add(saidData);

        Map<String, Object> marwaData = new HashMap<>();
        marwaData.put("name", "Marwa");
        marwaData.put("y", 14);
        marwaData.put("color", "#6920fb");
        data.add(marwaData);

        Map<String, Object> aymenData = new HashMap<>();
        aymenData.put("name", "Aymen");
        aymenData.put("y", 20);
        aymenData.put("color", "#121212");
        data.add(aymenData);

        return data;
    }

    @GetMapping("/LastDevis")
    public ArrayList<Map<String, Object>> getLastDevis() {
        ArrayList<Map<String, Object>> devisList = new ArrayList<>();

        // Première devis
        Map<String, Object> devis1 = new HashMap<>();
        devis1.put("id", 1);
        devis1.put("title", "devis_title");
        devis1.put("price", "$299");
        devis1.put("shop", "Vidange Simple 10000KM");
        devis1.put("location", "A3 SportBack");
        devis1.put("status", "Wassim");
        devisList.add(devis1);

        // Deuxième devis
        Map<String, Object> devis2 = new HashMap<>();
        devis2.put("id", 2);
        devis2.put("title", "devis_title");
        devis2.put("price", "$299");
        devis2.put("shop", "Vidange Simple 10000KM");
        devis2.put("location", "A3 SportBack");
        devis2.put("status", "Aymen");
        devisList.add(devis2);

        // Troisième devis
        Map<String, Object> devis3 = new HashMap<>();
        devis3.put("id", 3);
        devis3.put("title", "devis_title");
        devis3.put("price", "$299");
        devis3.put("shop", "Vidange Simple 10000KM");
        devis3.put("location", "A3 SportBack");
        devis3.put("status", "Meriem");
        devisList.add(devis3);

        return devisList;
    }


    @GetMapping("/TopVoiture")
    public List<Map<String, Object>> getTopCars() {

        List<Map<String, Object>> data = new ArrayList<>();

        // Sample data, you would fetch this from your database or other source
        Map<String, Object> amineData = new HashMap<>();
        amineData.put("name", "A3 SportBack");
        amineData.put("y", 395);
        amineData.put("color","#F1e8A5" );
        data.add(amineData);

        Map<String, Object> wassimData = new HashMap<>();
        wassimData.put("name", "Q8");
        wassimData.put("y", 385);
        wassimData.put("color","#7e0505" );
        data.add(wassimData);

        Map<String, Object> saidData = new HashMap<>();
        saidData.put("name", "Q2 Urban BVM");
        saidData.put("y", 275);
        saidData.put("color", "#ed9e20");
        data.add(saidData);

        Map<String, Object> marwaData = new HashMap<>();
        marwaData.put("name", "Marwa");
        marwaData.put("y", 14);
        marwaData.put("color", "#6920fb");
        data.add(marwaData);


        return data;
    }

    @GetMapping("/cars")
    public List<Object> getCars() {
        List<VoitureDTO> v = accountService.listVoiture("");
        List<Object> a = new ArrayList<>();
        v.forEach(voitureDTO -> {
       a.add(createCar(voitureDTO.getId(), String.valueOf(new Date()), voitureDTO.getFinition(), voitureDTO.getMoteur(), voitureDTO.getModele()));});
        return a;
    }
    private Map<String, Object> createCar(Long id, String annee_fab, String finition, String moteur, String proprietaire) {
        Map<String, Object> car = new HashMap<>();
        car.put("id", id);
        car.put("annee_fab", annee_fab);
        car.put("finition", finition);
        car.put("moteur", moteur);
        car.put("proprietaire_id", proprietaire);
        return car;
    }

    @PostMapping("/Wassim")
    public void a(@RequestBody List<Map<String, Object>> objets) {
        objets.forEach(objet -> {
            System.out.println("Objet reçu : " + objet);
            if (! accountService.findMO(objet.get("mainoueuvre").toString()).isPresent()){
                MainOeuvre mainOeuvre = new MainOeuvre(null, objet.get("mainoueuvre").toString() );
                    accountService.addNewMainOeuvre(mainOeuvre);
            

            Moteur m = accountService.getMoteurByName(objet.get("moteur").toString());
            Voiture v = accountService.getVoiture(m,objet.get("modele").toString());
            Prixmainouevre p = new Prixmainouevre(null , v , mainOeuvre,242);

            accountService.addNewPrixMain(p);
            }
        });

    }



}
