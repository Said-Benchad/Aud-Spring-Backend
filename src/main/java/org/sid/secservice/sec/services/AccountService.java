package org.sid.secservice.sec.services;

import org.sid.secservice.sec.entities.*;

import java.util.List;
import java.util.Optional;
public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppUser updateUser(Long id , AppUser user);
    AppRole addNewRole( AppRole appRole);
    void addRoletoUser(String username ,String roleName );
    Optional<AppUser> LoadUserByUsername( String username);
    List<AppUser> listeUser();
    List<Devis> listeDevisByclient(AppUser user);
    Voiture addNewVoiture(Voiture voiture);
    Voiture updateVoiture ( Long id , Voiture voiture);
    void deleteVoiture(Voiture voiture);
    Services addService (Services services);
    Services updateService( Long id , Services services);
    void deleteService (Services services);
    Devis addNewDevis(Devis devis);
    Devis updateDevis(Long id ,Devis devis);
    void deleteDevis (Devis devis);
    List<PrixServices> prixservices(Voiture voiture);
    List<PrixServices> prixservices(Services services);
    Piece addNewPiece(Piece piece);
    Piece updatePiece(Long id , Piece piece);
    void deletePiece(Piece piece);
    MainOeuvre addNewMainOeuvre( MainOeuvre mainOeuvre);
    MainOeuvre updateMainOeuvre (Long id , MainOeuvre mainOeuvre);
    void deleteMainOeuvre(MainOeuvre mainOeuvre);
    Packages addNewPack (Packages packages);
    Packages updatePack(Long id ,Packages packages);
    void deletePack (Packages packages);
    Moteur addNewMoteur (Moteur moteur);
    Moteur updateMoteur(Long id , Moteur moteur);
    void deleteMoteur (Moteur moteur);
    Employe addNewEmploye ( Employe employe);
    Employe updateEmploye ( Long id , Employe eploye);
    void deleteEmploye (Employe employe);
    void deleteUser (AppUser appUser );
    Services addNewService( Services services);

    Optional<AppUser> user(Long id);


    void addVoitureToUser(AppUser appUser , Voiture voiture);
    void addMoteurToVoiture(Moteur moteur , Voiture voiture);
    void addVoitureToDevis(Voiture voiture , Devis devis);
    void addVoitureToPack ( Long id, Packages pack);
    void addEmployeToDevis (Employe employe , Devis devis);
    void addPieceToVoiture(Piece p , Voiture v );
    void addServiceToPack(Packages p , Services s);

    void addVoitureToPService(Voiture voiture , PrixServices prixServices);
    void addSerToPServices(Services services , PrixServices prixSer);

    Voiture listVoiture(Moteur moteur , String modele);
    Moteur getMoteurByName(String puissance);

    List<Packages> getPackByTypeNVtr( Voiture voiture);

    void CalcCout (Packages pack);
    PrixServices addNewPrixSer(PrixServices prixServices);
    PrixServices getPrixServices(Voiture voiture , Services services);
    Optional<Services> getService(Long id);
    Optional<Moteur> getMoteur (Long id);
    StatusDevis addNewStatusDevis ( StatusDevis statusDevis);
    List<Devis> listDevis();
    List<Services> listService();



}
