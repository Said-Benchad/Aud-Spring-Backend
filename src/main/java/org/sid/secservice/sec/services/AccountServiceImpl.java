package org.sid.secservice.sec.services;


import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.rep.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private ServicesRepository servicesRepository;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private DevisRepository devisRepository;
    private VoitureRepository voitureRepository;
    private PasswordEncoder passwordEncoder;
    private PrixServicesRepository prixServicesRepository;
    private PieceRepository pieceRepository ;
    public AccountServiceImpl(ServicesRepository servicesRepository, AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, DevisRepository devisRepository, VoitureRepository voitureRepository, PasswordEncoder passwordEncoder, PrixServicesRepository prixServicesRepository, PieceRepository pieceRepository) {
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
            existingEntity.setStatusDevis(devis.getStatusDevis());
            existingEntity.setDescription(devis.getDescription());
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
        return null;
    }

    @Override
    public Piece deletePiece(Piece piece) {
        return null;
    }

    @Override
    public MainOeuvre addNewMainOeuvre(MainOeuvre mainOeuvre) {
        return null;
    }

    @Override
    public MainOeuvre updateMainOeuvre(Long id, MainOeuvre mainOeuvre) {
        return null;
    }

    @Override
    public MainOeuvre deleteMainOeuvre(MainOeuvre mainOeuvre) {
        return null;
    }

    @Override
    public Packages addNewPack(Packages packages) {
        return null;
    }

    @Override
    public Packages updatePack(UUID id, Packages packages) {
        return null;
    }

    @Override
    public Packages deletePack(Packages packages) {
        return null;
    }

    @Override
    public Moteur addNewMoteur(Moteur moteur) {
        return null;
    }

    @Override
    public Moteur updateMoteur(UUID id, Moteur moteur) {
        return null;
    }

    @Override
    public Moteur deleteMoteur(Moteur moteur) {
        return null;
    }

    @Override
    public Employe addNewEmploye(Employe employe) {
        return null;
    }

    @Override
    public Employe updateEmploye(Long id, Employe eploye) {
        return null;
    }

    @Override
    public Employe deleteEmploye(Employe employe) {
        return null;
    }

    @Override
    public void addUsertoVoiture(AppUser appUser, Voiture voiture) {

    }

    @Override
    public void addMoteurToVoiture(Moteur moteur, Voiture voiture) {

    }

    @Override
    public void addVoitureToDevis(Voiture voiture, Devis devis) {

    }

    @Override
    public void addVoitureToPack(Voiture voiture, Packages pack) {

    }

    @Override
    public void addEmployeToDevis(Employe employe, Devis devis) {

    }

    @Override
    public void addPieceToVoiture(Piece p, Voiture v) {

    }

    @Override
    public void addVoitureToPService(Voiture voiture, PrixServices prixServices) {

    }

    @Override
    public void addSerToPServices(Services services, PrixServices prixSer) {

    }
}
