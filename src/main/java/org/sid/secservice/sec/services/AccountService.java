package org.sid.secservice.sec.services;

import org.sid.secservice.sec.entities.*;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppUser updateUser(Long id , AppUser user);
    AppRole addNewRole( AppRole appRole);
    void addRoletoUser(String username ,String roleName );
    AppUser LoadUserByUsername( String username);
    List<AppUser> listeUser();
    List<Devis> listeDevisByclient(AppUser user);
    Voiture addNewVoiture(Voiture voiture);
    Voiture updateVoiture ( Long id , Voiture voiture);
    void deleteVoiture(Voiture voiture);
    Services addService (Services services);
    Services updateService( Long id , Services services);
    void deleteService (Services services);
    Devis addNewDevis(Devis devis);
    Devis updateDevis(UUID id ,Devis devis);
    void deleteDevis (Devis devis);
    List<PrixServices> prixservices(Voiture voiture);
    List<PrixServices> prixservices(Services services);
    PrixServices getPrixservices(Voiture voiture , Services services);
    Piece addNewPiece(Piece piece);
    Piece updatePiece(Long id , Piece piece);
    Piece deletePiece(Piece piece);
    MainOeuvre addNewMainOeuvre( MainOeuvre mainOeuvre);
    MainOeuvre updateMainOeuvre (Long id , MainOeuvre mainOeuvre);
    MainOeuvre deleteMainOeuvre( MainOeuvre mainOeuvre);
    Packages addNewPack (Packages packages);
    Packages updatePack(UUID id ,Packages packages);
    Packages deletePack (Packages packages);
    Moteur addNewMoteur (Moteur moteur);
    Moteur updateMoteur(UUID id , Moteur moteur);
    Moteur deleteMoteur (Moteur moteur);
    Employe addNewEmploye ( Employe employe);
    Employe updateEmploye ( Long id , Employe eploye);
    Employe deleteEmploye ( Employe employe);


    void addUsertoVoiture(AppUser appUser , Voiture voiture);
    void addMoteurToVoiture(Moteur moteur , Voiture voiture);
    void addVoitureToDevis(Voiture voiture , Devis devis);
    void addVoitureToPack ( Voiture voiture , Packages pack);
    void addEmployeToDevis (Employe employe , Devis devis);
    void addPieceToVoiture(Piece p , Voiture v );
    void addVoitureToPService(Voiture voiture , PrixServices prixServices);
    void addSerToPServices(Services services , PrixServices prixSer);

}
