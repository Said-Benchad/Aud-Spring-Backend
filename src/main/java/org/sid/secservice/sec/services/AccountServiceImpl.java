package org.sid.secservice.sec.services;


import org.sid.secservice.sec.dtos.*;
import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.rep.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private MoteurRepository moteurRepository;
    private EmployeRepository employeRepository;
    private PackagesRepository packagesRepository;
    private MainOeuvreRepository mainOeuvreRepository;
    private ServicesRepository servicesRepository;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private DevisRepository devisRepository;
    private VoitureRepository voitureRepository;
    private PasswordEncoder passwordEncoder;
    private PrixServicesRepository prixServicesRepository;
    private PieceRepository pieceRepository ;
    private  StatusDevisRepository statusDevisrepository;
    private PrixMORepository prixMORepository;
    private RevisionRepository revisionRepository;

    public AccountServiceImpl(MoteurRepository moteurRepository, EmployeRepository employeRepository, PackagesRepository packagesRepository, MainOeuvreRepository mainOeuvreRepository, ServicesRepository servicesRepository, AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, DevisRepository devisRepository, VoitureRepository voitureRepository, PasswordEncoder passwordEncoder, PrixServicesRepository prixServicesRepository, PieceRepository pieceRepository, StatusDevisRepository statusDevisrepository, PrixMORepository prixMORepository, RevisionRepository revisionRepository) {
        this.moteurRepository = moteurRepository;
        this.employeRepository = employeRepository;
        this.packagesRepository = packagesRepository;
        this.mainOeuvreRepository = mainOeuvreRepository;
        this.servicesRepository = servicesRepository;
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.devisRepository = devisRepository;
        this.voitureRepository = voitureRepository;
        this.passwordEncoder = passwordEncoder;
        this.prixServicesRepository = prixServicesRepository;
        this.pieceRepository = pieceRepository;
        this.statusDevisrepository = statusDevisrepository;
        this.prixMORepository = prixMORepository;
        this.revisionRepository = revisionRepository;

    }


    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));

        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateUser( Long id,AppUser appUser) {

        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isPresent()) {
            AppUser existingEntity = user.get();

            existingEntity.setUsername(appUser.getUsername());
            String pw = appUser.getPassword();
            existingEntity.setPassword(passwordEncoder.encode(pw));

            return appUserRepository.save(existingEntity);
        } else {
                throw new EntityNotFoundException("Entity not found");
        }
    }


    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoletoUser(String username, String roleName) {
    Optional<AppUser> appUser = appUserRepository.findFirstByUsername(username);
    System.out.println(appUser);
    AppRole appRole = appRoleRepository.findFirstByRoleName(roleName);
    System.out.println(appRole);
    appUser.get().getAppRole().add(appRole);
        appUserRepository.save(appUser.get());
    }

    @Override
    public AppRole getRoleByname(String rolename) {
        return appRoleRepository.findFirstByRoleName(rolename);
    }

    @Override
    public Optional<AppUser> LoadUserByUsername(String username) {
        return appUserRepository.findFirstByUsername(username);
    }

    @Override
    public List<AppUser> listeUser() {
        return appUserRepository.findAll();
    }

    @Override
    public List<Devis> listeDevisByclient(AppUser user) {
        return devisRepository.findAllByClient(user);
    }

    @Override
    public Voiture addNewVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @Override
    public Voiture updateVoiture(Long id, Voiture voiture) {


        Optional<Voiture> voiture1 = voitureRepository.findById(id);

        if (voiture1.isPresent()) {
            Voiture existingEntity = voiture1.get();

            existingEntity.setAnneeFab(voiture.getAnneeFab());
            existingEntity.setFinition(voiture.getFinition());
            existingEntity.setModele(voiture.getModele());
            existingEntity.setMoteur(voiture.getMoteur());

            return voitureRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public void deleteVoiture(Long id) {

        if (voitureRepository.existsById(id)) {

            voitureRepository.deleteById(id);
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }


    @Override
    public Services updateService(Long id, Services services) {

//        Optional<Services> services1 = servicesRepository.findById(id);
//
//        if (services1.isPresent()) {
//            Services existingEntity = services1.get();
//
//            existingEntity.setNom(services.getNom());
//            //existingEntity.setTypeService(services.getTypeService());
//
//            return servicesRepository.save(existingEntity);
//        } else {
//            throw new EntityNotFoundException("Entity not found");
//        }
        return null;
    }

    @Override
    public void deleteService(Services services) {

        if (servicesRepository.existsById(services.getIdService())) {

            servicesRepository.deleteById(services.getIdService());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public Devis addNewDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    @Override
    public Devis updateDevis(Long id, Devis devis) {
        Optional<Devis> devis1 = devisRepository.findById(id);

        if (devis1.isPresent()) {
            Devis existingEntity = devis1.get();

            existingEntity.setTitre_devis(devis.getTitre_devis());
            existingEntity.setStatusDevis(devis.getStatusDevis());//statu
            existingEntity.setDescription(devis.getDescription());//desc
            existingEntity.setClient(devis.getClient());//client
            existingEntity.setDateModif(new Date());
            return devisRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }


    @Override
    public void deleteDevis(Devis devis) {
        if (devisRepository.existsById(devis.getCode_devis())) {

            devisRepository.deleteById(devis.getCode_devis());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public List<PrixServices> prixservices() {
        return prixServicesRepository.findAll();
    }

    @Override
    public List<PrixServices> prixservices(Services services) {

        return prixServicesRepository.findAllByServices(services);
    }



    @Override
    public Piece addNewPiece(Piece piece) {
        return pieceRepository.save(piece);
    }

    @Override
    public Piece updatePiece(Long id, Piece piece) {
        Optional<Piece> piece1 = pieceRepository.findById(id);

        if (piece1.isPresent()) {
            Piece existingEntity = piece1.get();

            existingEntity.setNom(piece.getNom());
            existingEntity.setReference(piece.getReference());//statu
            existingEntity.setPrix(piece.getPrix());//desc
            return pieceRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public void deletePiece(Piece piece) {
        if (pieceRepository.existsById(piece.getIdPiece())) {

            pieceRepository.deleteById(piece.getIdPiece());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public MainOeuvre addNewMainOeuvre(MainOeuvre mainOeuvre) {
        return mainOeuvreRepository.save(mainOeuvre);
    }

    @Override
    public MainOeuvre updateMainOeuvre(Long id, MainOeuvre mainOeuvre) {
        Optional<MainOeuvre> mainOeuvre1 = mainOeuvreRepository.findById(id);

        if (mainOeuvre1.isPresent()) {
            MainOeuvre existingEntity = mainOeuvre1.get();

            existingEntity.setNom(mainOeuvre.getNom());
           // existingEntity.setService(mainOeuvre.getService());//desc
            return mainOeuvreRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public void deleteMainOeuvre(MainOeuvre mainOeuvre) {
        if (mainOeuvreRepository.existsById(mainOeuvre.getIdMO_Service())) {

            mainOeuvreRepository.deleteById(mainOeuvre.getIdMO_Service());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public Packages addNewPack(Packages packages) {
        return packagesRepository.save(packages);
    }

    @Override
    public Packages updatePack(Long id, Packages packages) {
        Optional<Packages> packages1 = packagesRepository.findById(id);

        if (packages1.isPresent()) {
            Packages existingEntity = packages1.get();

            existingEntity.setType(packages.getType());
            existingEntity.setCout(packages.getCout());//statu
            existingEntity.setRevision(packages.getRevision());
            existingEntity.setVoiture(packages.getVoiture());//desc
            return packagesRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public void deletePack(Packages packages) {
        if (packagesRepository.existsById(packages.getCodePack())) {

            packagesRepository.deleteById(packages.getCodePack());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public Moteur addNewMoteur(MoteurDTO moteur) {

        return moteurRepository.save(new Moteur(moteur.getCylindee(), moteur.getPuissance(), moteur.getTypeMotorisation()));
    }

    @Override
    public Moteur updateMoteur(Long id, Moteur moteur) {
        Optional<Moteur> moteur1 = moteurRepository.findById(id);

        if (moteur1.isPresent()) {
            Moteur existingEntity = moteur1.get();

            existingEntity.setCylindee(moteur.getCylindee());
            existingEntity.setPuissance(moteur.getPuissance());//statu
           // existingEntity.setVoiture(moteur.getVoiture());
            existingEntity.setTypeMotorisation(moteur.getTypeMotorisation());//desc
            return moteurRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public void deleteMoteur(Moteur moteur) {
        if (moteurRepository.existsById(moteur.getCodeMoteur())) {

            moteurRepository.deleteById(moteur.getCodeMoteur());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public Employe addNewEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe updateEmploye(Long id, Employe employe) {
        Optional<Employe> employe1 = employeRepository.findById(id);

        if (employe1.isPresent()) {
            Employe existingEntity = employe1.get();

            existingEntity.setNom(employe.getNom());
            existingEntity.setTel(employe.getTel());//statu
            existingEntity.setMail(employe.getMail());
            existingEntity.setAdress(employe.getAdress());
            existingEntity.setPassword(employe.getPassword());//desc
            return employeRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public void deleteEmploye(Employe employe) {
        if (employeRepository.existsById(employe.getId_emp())) {

            employeRepository.deleteById(employe.getId_emp());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (appUserRepository.existsById(id)) {

            appUserRepository.deleteById(id);
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }

    }



    @Override
    public Optional<AppUser> user(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public List<VoitureDTO> listVoiture(String keyword) {
        List<VoitureDTO> v = new ArrayList<>();
       List<Voiture> voitures = voitureRepository.findAllByModeleContaining(keyword);
       for (Voiture vt: voitures ){
           v.add( new VoitureDTO(vt.getId(), vt.getModele(), vt.getFinition(), vt.getMoteur().getPuissance()));
       }
        return v;
    }

    @Override
    public List<VoitureDTO> listVoiture(String modele, String finition) {
        List<VoitureDTO> v = new ArrayList<>();
        List<Voiture> voitures = voitureRepository.findAllByModeleAndFinition(modele,finition);
        for (Voiture vt: voitures ){
            v.add( new VoitureDTO(vt.getId(), vt.getModele(), vt.getFinition(), vt.getMoteur().getPuissance()));
        }
        return v;
    }

    @Override
    public List<Moteur> listMoteur() {
        return moteurRepository.findAll();}

    @Override
    public Voiture addVoiture(VoitureDTO voitureDTO) {
        Moteur m = moteurRepository.findByPuissance(voitureDTO.getMoteur());
        return voitureRepository.save(new Voiture(voitureDTO.getModele(), voitureDTO.getFinition(), m));
    }


    @Override
    public void addMoteurToVoiture(Moteur moteur, Voiture voiture) {
         voiture.setMoteur(moteur);
          voitureRepository.save(voiture);
    }

    @Override
    public void addVoitureToDevis(Voiture voiture, Devis devis) {
        devis.setVoiture(voiture);
    }

    @Override
    public void addEmployeToDevis(Employe employe, Devis devis) {
        devis.getEmployes().add(employe);
    }

    @Override
    public void addPieceToVoiture(Piece p, Voiture v) {
        v.getPieces().add(p);
    }

    @Override
    public void addServiceToPack(Packages p, Revision r) {
        p.getRevision().add(r);
    }
    @Override
    public void addVoitureToPack( Long id, Packages pack) {
        Optional <Voiture> v=voitureRepository.findById(id);
        pack.setVoiture(v.get());
    }
    @Override
    public void addVoitureToPService(Voiture voiture, PrixServices prixServices) {
    prixServices.setVoiture(voiture);

    }

    @Override
    public void addSerToPServices(Services services, PrixServices prixSer) {
        prixSer.setServices(services);
    }

    @Override
    public void CalcCout( Packages pack) {
        pack.resetCout();
        //List<PrixServices> prixServices =new ArrayList<>();
        double montant = 0;
//        for (Services services1 : pack.getServices()) {
//            System.out.println(services1);
//            System.out.println(pack.getVoiture());
//            System.out.println(prixServicesRepository.findDistinctFirstByVoitureAndServices(pack.getVoiture(),services1));
//          PrixServices prixServices  = prixServicesRepository.findDistinctFirstByVoitureAndServices(pack.getVoiture(),services1);
//                montant+= prixServices.getPrixServVoitr();
//            }
        montant = 1218.45 ;
        pack.setCout(montant);
        }
    @Override
    public PrixServices addNewPrixSer(PrixServices prixServices) {
        return prixServicesRepository.save(prixServices);
    }

    @Override
    public PrixServices getPrixServices(Voiture voiture, Services services) {
        return prixServicesRepository.findFirstByVoitureAndServices(voiture ,services);
    }

    @Override
    public List<Prixmainouevre> getPrixMainOeuvre() {
        return prixMORepository.findAll();
    }

    @Override
    public Prixmainouevre getPrixMainOeuvre(Voiture voiture, MainOeuvre mainOeuvre) {
        return prixMORepository.findFirstByVoitureAndMainOeuvre(voiture,mainOeuvre);
    }

    @Override
    public List<RevisionDTO> listSerParV(Voiture voiture) {

        List<Revision> revisions = revisionRepository.findAll();
        List<RevisionDTO> revisionDTOList = new ArrayList<>();
        for (Revision r : revisions) {
            RevisionDTO rvsionDTO = new RevisionDTO();
            PrixServices prixService = prixServicesRepository.findFirstByVoitureAndServices(voiture, r);
            rvsionDTO.setId(r.getIdService());
            rvsionDTO.setNom(r.getNom());
            rvsionDTO.setCouSer(prixService.getPrixServVoitr());
            for (MainOeuvre m : r.getMainOeuvre()) {
                MainOeuvreDTO mainDTO = new MainOeuvreDTO();
                Prixmainouevre prixmainouevre = prixMORepository.findFirstByVoitureAndMainOeuvre(voiture, m);
                if (prixmainouevre != null) {
                    mainDTO.setId(prixmainouevre.getIdPrixMO());
                    mainDTO.setNom(prixmainouevre.getMainOeuvre().getNom());
                    mainDTO.setCoutMO(prixmainouevre.getPrixServVoitrMo());
                    rvsionDTO.getMainOeuvre().add(mainDTO);
                }
                revisionDTOList.add(rvsionDTO);
            }
        }
        return revisionDTOList;
    }


    @Override
    public Optional<Services> getService(Long id) {
        return servicesRepository.findById(id);
    }

    @Override
    public Optional<Moteur> getMoteur(Long id) {
        return moteurRepository.findById(id);
    }

    @Override
    public StatusDevis addNewStatusDevis(StatusDevis statusDevis) {
        return statusDevisrepository.save(statusDevis);
    }

    @Override
    public List<Devis> listDevis() {
        return devisRepository.findAll();
    }

    @Override
    public List<Services> listService() {
        return servicesRepository.findAll();
    }


    //---------------------------------      DEMANDE DE DEVIS     -----------------------------------------\\
    @Override
    public Moteur getMoteurByName(String puissance) {
        return moteurRepository.findByPuissance(puissance);
    }

    @Override
    public Voiture getVoiture(Moteur moteur, String modele) {
        return voitureRepository.findByModeleAndMoteur(modele,moteur);
    }


    @Override
    public Packages getPackByTypeNVtr( Voiture voiture , String type) {

        return packagesRepository.findFirstByVoitureAndType(voiture , type);

    }

    @Override
    public List<Packages> getPack() {
        return packagesRepository.findAll();
    }


    @Override
    public Double calcMontant(Packages pack, List<Services> servicesList) {
        double montat = 0;
        double sum = 0;
        List<PrixServices> prixServices = new ArrayList<>();
        pack.getRevision().forEach((e)->{
            prixServices.add(getPrixServices(pack.getVoiture(),e));
        });
        servicesList.forEach((e)->{
            prixServices.add(getPrixServices(pack.getVoiture(),e));
        });
//        for (PrixServices prixSr : prixServices){
//            montat+= prixSr.getPrixServVoitr();
//            List<Prixmainouevre> prixmainouevres = prixSr.getPrixMainOeuvres();
//            for (Prixmainouevre p : prixmainouevres){
//                sum+=p.getPrixServVoitrMo();
//            }
//            montat+=sum;
//
//        }
        return montat;
    }

    @Override
    public Services addservice(Revision revision) {
        return servicesRepository.save(revision);
    }

    @Override
    public Services addservice(Reparation reparation) {
        return servicesRepository.save(reparation);
    }

    @Override
    public List<AppUser> searchUser(String keyword) {

        return appUserRepository.userserchead(keyword);
    }


}

