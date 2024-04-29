package org.sid.secservice.sec.services;


import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.rep.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AccountServiceImpl(MoteurRepository moteurRepository, EmployeRepository employeRepository, PackagesRepository packagesRepository, MainOeuvreRepository mainOeuvreRepository, ServicesRepository servicesRepository, AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, DevisRepository devisRepository, VoitureRepository voitureRepository, PasswordEncoder passwordEncoder, PrixServicesRepository prixServicesRepository, PieceRepository pieceRepository) {
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
    AppUser appUser = appUserRepository.findByUsername(username);
    AppRole appRole = appRoleRepository.findByRoleName(roleName);
    appUser.getAppRole().add(appRole);
    }

    @Override
    public AppUser LoadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
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
    public void deleteVoiture(Voiture voiture) {

        if (voitureRepository.existsById(voiture.getId())) {

            voitureRepository.deleteById(voiture.getId());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }
    }

    @Override
    public Services addService(Services services) {
        return servicesRepository.save(services);
    }

    @Override
    public Services updateService(Long id, Services services) {

        Optional<Services> services1 = servicesRepository.findById(id);

        if (services1.isPresent()) {
            Services existingEntity = services1.get();

            existingEntity.setNom(services.getNom());
            existingEntity.setTypeService(services.getTypeService());

            return servicesRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
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
    public Devis updateDevis(UUID id, Devis devis) {
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
    public List<PrixServices> prixservices(Voiture voiture) {

        return prixServicesRepository.findAllByVoit(voiture);
    }

    @Override
    public List<PrixServices> prixservices(Services services) {

        return prixServicesRepository.findAllBySer(services);
    }

    @Override
    public PrixServices getPrixservices(Voiture voiture, Services services) {
        return prixServicesRepository.findByVoitAndSer(voiture , services);
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
            existingEntity.setCout(mainOeuvre.getCout());//statu
            existingEntity.setService(mainOeuvre.getService());//desc
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
    public Packages updatePack(UUID id, Packages packages) {
        Optional<Packages> packages1 = packagesRepository.findById(id);

        if (packages1.isPresent()) {
            Packages existingEntity = packages1.get();

            existingEntity.setType(packages.getType());
            existingEntity.setCout(packages.getCout());//statu
            existingEntity.setServices(packages.getServices());
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
    public Moteur addNewMoteur(Moteur moteur) {
        return moteurRepository.save(moteur);
    }

    @Override
    public Moteur updateMoteur(UUID id, Moteur moteur) {
        Optional<Moteur> moteur1 = moteurRepository.findById(id);

        if (moteur1.isPresent()) {
            Moteur existingEntity = moteur1.get();

            existingEntity.setCylindee(moteur.getCylindee());
            existingEntity.setPuissance(moteur.getPuissance());//statu
            existingEntity.setVoiture(moteur.getVoiture());
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
    public void deleteUser(AppUser appUser) {
        if (appUserRepository.existsById(appUser.getId())) {

            appUserRepository.deleteById(appUser.getId());
        } else {

            throw new EntityNotFoundException("Entity with ID  not found");
        }

    }

    @Override
    public Optional<AppUser> user(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public void addVoitureToUser(AppUser appUser, Voiture voiture) {
        appUser.getVoiture().add(voiture);
    }

    @Override
    public void addMoteurToVoiture(Moteur moteur, Voiture voiture) {
    //     //voiture.setMoteur(moteur);
    //    System.out.println(voitureRepository.findAll());
    //      //voitureRepository.save(voiture);
    }

    @Override
    public void addVoitureToDevis(Voiture voiture, Devis devis) {
        devis.setVoiture(voiture);
    }

    @Override
    public void addVoitureToPack( Long id, Packages pack) {
       Optional <Voiture> v=voitureRepository.findById(id);
        pack.setVoiture(v.get());
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
    public void addVoitureToPService(Voiture voiture, PrixServices prixServices) {
    prixServices.setVoit(voiture);

    }

    @Override
    public void addSerToPServices(Services services, PrixServices prixSer) {
        prixSer.setSer(services);
    }

    @Override
    public Voiture listVoiture(Moteur moteur, String modele) {
        return voitureRepository.findByModeleAndMoteur(modele,moteur);
    }

    @Override
    public Moteur getMoteurByName(String motorisation) {
        return moteurRepository.findByTypeMotorisation(motorisation);
    }


   @Override
    public List<Packages> getPackByTypeNVtr( Voiture voiture) {
       List<Voiture> v =new ArrayList<>();
       v.add(voiture);
       return packagesRepository.findByVoitureContaining(voiture);
    }

    @Override
    public void CalcCout( Packages pack) {
        pack.resetCout();
        double montant = 0;
        for (Services services1 : pack.getServices()) {
            for (PrixServices p : services1.getPrixService()) {
                montant+= p.getPrixServVoitr();
            }
            }
        pack.setCout(montant);
        }
    }

