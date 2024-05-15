package org.sid.secservice;

import org.sid.secservice.sec.entities.*;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SecServiceApplication {





	public static void main(String[] args) {

		SpringApplication.run(SecServiceApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
			 AppUser user = new AppUser("said","Said");
			 accountService.addNewUser(user);
 		 	accountService.addNewRole(new AppRole(null, "USER"));
 		 	accountService.addNewRole(new AppRole(null, "ADMIN"));
 		 	accountService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
 		 	accountService.addNewRole(new AppRole(null, "PRODUCT_MANAGER"));
 		 	accountService.addNewRole(new AppRole(null, "BILLS_MANAGER"));

 		 	accountService.addNewUser(new AppUser("wassim","sdsds"));
 		 	accountService.addNewUser(new AppUser("safaa","12345"));
 		 	accountService.addNewUser(new AppUser("petissam","ASDFGG"));
 		 	accountService.addNewUser(new AppUser("narjiss","89@&"));
 		 	accountService.addNewUser(new AppUser("ghazal","145:;,as"));

 		 	accountService.addRoletoUser("wassim","USER");
 		 	accountService.addRoletoUser("wassim","ADMIN");
 		 	accountService.addRoletoUser("safaa","CUSTOMER_MANAGER");
 		 	accountService.addRoletoUser("safaa","USER");
 		 	accountService.addRoletoUser("safaa","ADMIN");
 		 	accountService.addRoletoUser("petissam","PRODUCT_MANAGER");
 		 	accountService.addRoletoUser("petissam","USER");
 		 	accountService.addRoletoUser("ghazal","BILLS_MANAGER");
 		 	accountService.addRoletoUser("ghazal","USER");

 		 	accountService.addNewPiece(new Piece("2L","qddd",655));
 		 	accountService.addNewPiece(new Piece("6L","wsasliidd",95));
 		 	accountService.addNewPiece(new Piece("2L","qddwagjkad",354));
 		 	accountService.addNewPiece(new Piece("26L","referencecc",235));
 		 	accountService.addNewPiece(new Piece("AB","l autoroute",100));

 		 	Moteur m1 = new Moteur("2,0L", "150 S-Tronic", "TDI");
 		 	accountService.addNewMoteur(m1);
 		 	Moteur m2 = new Moteur("2,0L", "163 S-Tronic", "TDI");
 		 	accountService.addNewMoteur(m2);
 		 	Moteur m3 = new Moteur("2,0L", "204 S-Tronic", "TDI");
 		 	accountService.addNewMoteur(m3);
 		 	Moteur m4 = new Moteur("2,0L", "116 BVM", "TDI");
 		 	accountService.addNewMoteur(m4);
 		 	Moteur m5 = new Moteur("2,0L", "200 S-Tronic", "TDI");
 		 	accountService.addNewMoteur(m5);
 		 	Moteur m6 = new Moteur("2,0L", "136 S-Tronic", "TDI");
 		 	accountService.addNewMoteur(m6);
 		 	Moteur m7 = new Moteur("3,0L", "286 quattro Tiptronic", "TDI");
 		 	accountService.addNewMoteur(m7);
 		 	Moteur m8 = new Moteur("3,0L", "341 quattro Tiptronic", "TDI");
 		 	accountService.addNewMoteur(m8);
 		 	Moteur m9 = new Moteur(null, "341 quattro 95kw/h", "e-tron");
 		 	accountService.addNewMoteur(m9);
 		 	Moteur m10 = new Moteur(null, "410 quattro 114kw/h", "e-tron");
 		 	accountService.addNewMoteur(m10);
 		 	Moteur m11 = new Moteur(null, "476 quattro 93kw/h ", "e-tron");
 		 	accountService.addNewMoteur(m11);
 		 	Moteur m12 = new Moteur(null, "600 quattro 93kw/h ", "e-tron");
 		 	accountService.addNewMoteur(m12);
 		 	Moteur m13 = new Moteur(null, "394 quattro", "TFSIe");
 		 	accountService.addNewMoteur(m13);
 		 	Moteur m14 = new Moteur(null, "299 quattro", "TFSIe");
 		 	accountService.addNewMoteur(m14);
 		 	Moteur m15 = new Moteur(null, "462 quattro", "TFSIe");
 		 	accountService.addNewMoteur(m15);
 		 	Voiture v1 = new Voiture("A3", "Sport",m1);
 		 	accountService.addNewVoiture(v1);
// 		 	Voiture v2 = new Voiture("A3", "Premium",m1);
// 		 	accountService.addNewVoiture(v2);
// 		 	Voiture v3 = new Voiture("A3", "Design",m1);
// 		 	accountService.addNewVoiture(v3);
 		 	Voiture v4 = new Voiture("A4", "S Edition ",m2);
 		 	accountService.addNewVoiture(v4);
 		 	Voiture v5 = new Voiture("A5", "S-Line",m3);
 		 	accountService.addNewVoiture(v5);
 		 	Voiture v6 = new Voiture("A6", "S-Edition",m7);
 		 	accountService.addNewVoiture(v6);
 		 	Voiture v7 = new Voiture("Q2", "URBAN BVM",m4);
 		 	accountService.addNewVoiture(v7);
 		 	/*accountService.addMoteurToVoiture(m1,v1);
 		 	accountService.addMoteurToVoiture(m1,v2);
 		 	accountService.addMoteurToVoiture(m1,v3);
 		 	accountService.addMoteurToVoiture(m1,v4);
 		 	accountService.addMoteurToVoiture(m1,v5);
 		 	accountService.addMoteurToVoiture(m1,v6);
 		 	accountService.addMoteurToVoiture(m1,v7);*/
 		 	accountService.listVoiture(m1,"A3");
 			Services s1 = new Services("HUILE Moteur");
 			accountService.addService(s1);
			 Services s2 = new Services("FILTRE A HUILE");
 			accountService.addService(s2);
 			Services s3 = new Services("FILTRE A AIR");
 			accountService.addService(s3);
 			Services s4 = new Services("FILTRE A GASOIL");
 			accountService.addService(s4);
 			Services s5 = new Services("ETANCHEMENT ");
 			accountService.addService(s5);
 			Services s6 = new Services("FILTRE A POLLEN");
 			accountService.addService(s6);
 			Services s7 = new Services("COURROIE CRANTE");
 			accountService.addService(s7);
 			Services s8 = new Services("GALET TENDEUR");
 			accountService.addService(s8);
 			Services s9 = new Services("GALET D INVERSION");
 			accountService.addService(s9);
 			Services s10 = new Services("GALET D INVERSION");
 			accountService.addService(s10);
 			Services s11 = new Services("VIS + ECROUS");
 			accountService.addService(s11);
 			Services s12 = new Services("COURROIE MULTIPISTES");
 			accountService.addService(s12);
 			Services s13 = new Services("GALET D INVERSION ou vis");
 			accountService.addService(s13);
 			Services s14 = new Services("TENDEUR");
 			accountService.addService(s14);
 			Services s15 = new Services("HUILE BOITE A VITESSE");
 			accountService.addService(s15);
 			Services s16 = new Services("TAMIS D'HUILE + JOINTS");
 			accountService.addService(s16);
 			Services s17 = new Services("HUILE UNIVERSE (Lubrifiant)");
 			accountService.addService(s17);
 			Services s18 = new Services("EAU DISTILE");
 			accountService.addService(s18);
 			Services s19 = new Services("AD-BLEU ");
 			accountService.addService(s19);
 			Services s20 = new Services("FILTRE -SEPARATEUR D'EAU-");
 			accountService.addService(s20);

 			MainOeuvre mo0 = new MainOeuvre( "ADBLUE MAINOUEAVRE",s19,0);
			accountService.addNewMainOeuvre(mo0);
			MainOeuvre mo1 = new MainOeuvre( "agent de réduction:vérifier et compléter",s1,158.40);
			accountService.addNewMainOeuvre(mo1);
			MainOeuvre mo2 = new MainOeuvre( "Vidange Huile Moteur",s2,79.20);
 			accountService.addNewMainOeuvre(mo2);
 			MainOeuvre mo3 = new MainOeuvre( "contrôle suivant check liste 40 pt",s17,118.80);
 			accountService.addNewMainOeuvre(mo3);
 			MainOeuvre mo4 = new MainOeuvre( "Remplacement Filtre à Huile",s18,158.40);
 			accountService.addNewMainOeuvre(mo4);
 			MainOeuvre mo5 = new MainOeuvre( "Filtre à carburant:déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo5);
 			MainOeuvre mo6 = new MainOeuvre( "Cartouche filtre à air:déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo6);
 			MainOeuvre mo7 = new MainOeuvre( "Filtre à poussière et pollen:déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo7);
 			MainOeuvre mo8 = new MainOeuvre( "remplacement huile ATF",0);
 			accountService.addNewMainOeuvre(mo8);
 			MainOeuvre mo9 = new MainOeuvre( "Diagnostic",0);
 			accountService.addNewMainOeuvre(mo9);
 			MainOeuvre mo10 = new MainOeuvre( "Courroie à nervures trapézoïdale:dép. et rep.",0);
 			accountService.addNewMainOeuvre(mo10);
 			MainOeuvre mo11 = new MainOeuvre( "Courroie crantée:déposer et reposer ",0);
 			accountService.addNewMainOeuvre(mo11);
 			MainOeuvre mo12 = new MainOeuvre( "Galet d'inversion p. courroie crantée:dép. et rep.",0);
 			accountService.addNewMainOeuvre(mo12);
 			MainOeuvre mo13 = new MainOeuvre( "Tendeur-amortisseur de courroie ALT :déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo13);
 			MainOeuvre mo14 = new MainOeuvre( "Galets d'inversion ALT:déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo14);
 			MainOeuvre mo15 = new MainOeuvre( "Amortisseur de vibrations de vilebrequin :déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo15);
 			MainOeuvre mo16 = new MainOeuvre( "Carter de soufflante de refroidissement :déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo16);
 			MainOeuvre mo17 = new MainOeuvre( "Montant porte-serrure:déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo17);
 			MainOeuvre mo18 = new MainOeuvre( "Filtre du séparateur d'eau: déposer et reposer",0);
 			accountService.addNewMainOeuvre(mo18);
			PrixServices p1 = new PrixServices(null , v1 ,s1 ,888.26);
			accountService.addNewPrixSer(p1);
			PrixServices p2 = new PrixServices(null , v1 ,s2 ,233.93);
			accountService.addNewPrixSer(p2);
			PrixServices p3 = new PrixServices(null , v1 ,s18 ,27.37);
			accountService.addNewPrixSer(p3);
			PrixServices p4 = new PrixServices(null , v1 ,s17 ,6.05);
			accountService.addNewPrixSer(p4);
			PrixServices p5 = new PrixServices(null , v1 ,s19 ,398.00);
			accountService.addNewPrixSer(p5);
			mo1.setService(s1);
			mo2.setService(s2);
			mo3.setService(s17);
			mo4.setService(s18);
			mo0.setService(s19);

			List<Services> s = new ArrayList<>();
			s.add(s1);s.add(s2);s.add(s18);s.add(s17);s.add(s19);
			Packages pack1 = new Packages(null , "Simple 10000" ,s ,v1 ,1218.25);
			//System.out.println(accountService.getPrixServices(v2,s1));
			//accountService.CalcCout(pack1);
			accountService.addNewPack(pack1);
			StatusDevis statusDevis = new StatusDevis(null,Status.INPROGRESS,new Date());
			accountService.addNewStatusDevis(statusDevis);
			Devis d1 = new Devis(null, "devissdsd","dsdqsdqssd",user ,statusDevis,v1,null ,null,null ,null,s);
			accountService.addNewDevis(d1);

		};
	}
}
