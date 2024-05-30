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
//			 AppUser user = new AppUser("WASSIMMM","WASSIMMM" ,"wassimASDSJDSs@gmail.com");
//			 accountService.addNewUser(user);
// 		 	accountService.addNewRole(new AppRole(null, "USER"));
// 		 	accountService.addNewRole(new AppRole(null, "ADMIN"));
// 		 	accountService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
// 		 	accountService.addNewRole(new AppRole(null, "PRODUCT_MANAGER"));
// 		 	accountService.addNewRole(new AppRole(null, "BILLS_MANAGER"));
//
// 		 	accountService.addNewUser(new AppUser("wassim","sdsds"));
// 		 	accountService.addNewUser(new AppUser("safaa","12345"));
// 		 	accountService.addNewUser(new AppUser("petissam","ASDFGG"));
// 		 	accountService.addNewUser(new AppUser("narjiss","89@&"));
// 		 	accountService.addNewUser(new AppUser("ghazal","145:;,as"));
//
// 		 	accountService.addRoletoUser("wassim","USER");
// 		 	accountService.addRoletoUser("wassim","ADMIN");
// 		 	accountService.addRoletoUser("safaa","CUSTOMER_MANAGER");
// 		 	accountService.addRoletoUser("safaa","USER");
// 		 	accountService.addRoletoUser("safaa","ADMIN");
// 		 	accountService.addRoletoUser("petissam","PRODUCT_MANAGER");
// 		 	accountService.addRoletoUser("petissam","USER");
// 		 	accountService.addRoletoUser("ghazal","BILLS_MANAGER");
// 		 	accountService.addRoletoUser("ghazal","USER");
//
// 		 	accountService.addNewPiece(new Piece("2L","qddd",655));
// 		 	accountService.addNewPiece(new Piece("6L","wsasliidd",95));
// 		 	accountService.addNewPiece(new Piece("2L","qddwagjkad",354));
// 		 	accountService.addNewPiece(new Piece("26L","referencecc",235));
// 		 	accountService.addNewPiece(new Piece("AB","l autoroute",100));
//
 		 /*	Moteur m1 = new Moteur(1L,"2,0L", "150 S-Tronic", "TDI");
			  accountService.addNewMoteur(m1);*/
// 		 	accountService.addNewMoteur(m1);
 		 //	Moteur m2 = new Moteur("2,0L", "163 S-Tronic", "TDI");
// 		 	accountService.addNewMoteur(m2);
// 		 	Moteur m3 = new Moteur("2,0L", "204 S-Tronic", "TDI");
// 		 	accountService.addNewMoteur(m3);
// 		 	Moteur m4 = new Moteur("2,0L", "116 BVM", "TDI");
// 		 	accountService.addNewMoteur(m4);
// 		 	Moteur m5 = new Moteur("2,0L", "200 S-Tronic", "TDI");
// 		 	accountService.addNewMoteur(m5);
// 		 	Moteur m6 = new Moteur("2,0L", "136 S-Tronic", "TDI");
// 		 	accountService.addNewMoteur(m6);
// 		 	Moteur m7 = new Moteur("3,0L", "286 quattro Tiptronic", "TDI");
// 		 	accountService.addNewMoteur(m7);
// 		 	Moteur m8 = new Moteur("3,0L", "341 quattro Tiptronic", "TDI");
// 		 	accountService.addNewMoteur(m8);
// 		 	Moteur m9 = new Moteur(null, "341 quattro 95kw/h", "e-tron");
// 		 	accountService.addNewMoteur(m9);
// 		 	Moteur m10 = new Moteur(null, "410 quattro 114kw/h", "e-tron");
// 		 	accountService.addNewMoteur(m10);
// 		 	Moteur m11 = new Moteur(null, "476 quattro 93kw/h ", "e-tron");
// 		 	accountService.addNewMoteur(m11);
// 		 	Moteur m12 = new Moteur(null, "600 quattro 93kw/h ", "e-tron");
// 		 	accountService.addNewMoteur(m12);
// 		 	Moteur m13 = new Moteur(null, "394 quattro", "TFSIe");
// 		 	accountService.addNewMoteur(m13);
// 		 	Moteur m14 = new Moteur(null, "299 quattro", "TFSIe");
// 		 	accountService.addNewMoteur(m14);
// 		 	Moteur m15 = new Moteur(null, "462 quattro", "TFSIe");
// 		 	accountService.addNewMoteur(m15);
		/*	Voiture v1 = new Voiture(1L, null, "A3 SortBack", "all", m1, null,null);
			accountService.addNewVoiture(v1);*/


// 		 	Voiture v2 = new Voiture("A3 Berline", "all",m1);
// 		 	accountService.addNewVoiture(v2);
// 		 	Voiture v3 = new Voiture("A6", "S-Edition",m3);
// 		 	accountService.addNewVoiture(v3);
// 		 	Voiture v4 = new Voiture("A4", "all",m2);
// 		 	accountService.addNewVoiture(v4);
// 		 	Voiture v5 = new Voiture("A5 SportBack", "all",m2);
// 		 	accountService.addNewVoiture(v5);
//			  Voiture v6 = new Voiture("A5 Coupé", "S-Line",m3);
// 		 	accountService.addNewVoiture(v6);
// 		 	Voiture v7 = new Voiture("A6", "S-Line Limited",m7);
// 		 	accountService.addNewVoiture(v7);
// 		 	Voiture v8 = new Voiture("A6", "Hybrid S",m14);
// 		 	accountService.addNewVoiture(v8);
//			Voiture v9 = new Voiture("A7", "Executive",m3);
//			accountService.addNewVoiture(v9);
//			Voiture v10 = new Voiture("A7", "Sline",m7);
//			accountService.addNewVoiture(v10);
//			Voiture v11 = new Voiture("A7", "Hybrid S",m14);
//			accountService.addNewVoiture(v11);
//			Voiture v12 = new Voiture("A8", "Signature",m7);
//			accountService.addNewVoiture(v12);
//			Voiture v13 = new Voiture("A8L", "Luxury Hybrid",m15);
//			accountService.addNewVoiture(v13);
//			Voiture v14 = new Voiture("Q2 Urban BVM", "Urban BVM",m4);
//			accountService.addNewVoiture(v14);
//			Voiture v15 = new Voiture("Q2", "All",m2);
//			accountService.addNewVoiture(v15);
//			Voiture v16 = new Voiture("Q3", "all",m2);
//			accountService.addNewVoiture(v16);
//			Voiture v17 = new Voiture("Q3", "S-Edition",m5);
//			accountService.addNewVoiture(v17);
//
//
//
//
//			MainOeuvre mo1 = new MainOeuvre(null, "agent de réduction:vérifier et compléter");
//			accountService.addNewMainOeuvre(mo1);
//			MainOeuvre mo2 = new MainOeuvre(null, "Vidange Huile Moteur");
//			accountService.addNewMainOeuvre(mo2);
//			MainOeuvre mo3 = new MainOeuvre(null, "contrôle suivant check liste 40 pt");
//			accountService.addNewMainOeuvre(mo3);
//			MainOeuvre mo4 = new MainOeuvre(null, "Remplacement Filtre à Huile");
//			accountService.addNewMainOeuvre(mo4);
//			MainOeuvre mo5 = new MainOeuvre( null,"Filtre à carburant:déposer et reposer");
//			accountService.addNewMainOeuvre(mo5);
//			MainOeuvre mo6 = new MainOeuvre( null,"Cartouche filtre à air:déposer et reposer");
//			accountService.addNewMainOeuvre(mo6);
//			MainOeuvre mo7 = new MainOeuvre( null,"Filtre à poussière et pollen:déposer et reposer");
//			accountService.addNewMainOeuvre(mo7);
//			MainOeuvre mo8 = new MainOeuvre(null, "remplacement huile ATF");
//			accountService.addNewMainOeuvre(mo8);
//			MainOeuvre mo9 = new MainOeuvre( null,"Diagnostic");
//			accountService.addNewMainOeuvre(mo9);
//			MainOeuvre mo10 = new MainOeuvre( null,"Courroie à nervures trapézoïdale:dép. et rep.");
//			accountService.addNewMainOeuvre(mo10);
//			MainOeuvre mo11 = new MainOeuvre(null, "Courroie crantée:déposer et reposer ");
//			accountService.addNewMainOeuvre(mo11);
//			MainOeuvre mo12 = new MainOeuvre( null,"Galet d'inversion p. courroie crantée:dép. et rep.");
//			accountService.addNewMainOeuvre(mo12);
//			MainOeuvre mo13 = new MainOeuvre( null,"Tendeur-amortisseur de courroie ALT :déposer et reposer");
//			accountService.addNewMainOeuvre(mo13);
//			MainOeuvre mo14 = new MainOeuvre( null,"Galets d'inversion ALT:déposer et reposer");
//			accountService.addNewMainOeuvre(mo14);
//			MainOeuvre mo15 = new MainOeuvre( null,"Amortisseur de vibrations de vilebrequin :déposer et reposer");
//			accountService.addNewMainOeuvre(mo15);
//			MainOeuvre mo16 = new MainOeuvre(null, "Carter de soufflante de refroidissement :déposer et reposer");
//			accountService.addNewMainOeuvre(mo16);
//			MainOeuvre mo17 = new MainOeuvre( null,"Montant porte-serrure:déposer et reposer");
//			accountService.addNewMainOeuvre(mo17);
//			MainOeuvre mo18 = new MainOeuvre(null, "Filtre du séparateur d'eau: déposer et reposer");
//			accountService.addNewMainOeuvre(mo18);
//
//
//			List<MainOeuvre> main = new ArrayList<>();
//			main.add(mo2);
//
//
//			Revision revision1 = new Revision("HUILE Moteur",main);
// 			accountService.addservice(revision1); main.clear();main.add(mo4);
//			Revision revision2 = new Revision("FILTRE A HUILE",main);
// 			accountService.addservice(revision2); main.clear();main.add(mo6);
//			Revision revision3 = new Revision("FILTRE A AIR",main);
// 			accountService.addservice(revision3); main.clear();main.add(mo5);
//			Revision revision4 = new Revision("FILTRE A GASOIL",main);
// 			accountService.addservice(revision4);
//			Revision revision5 = new Revision("ETANCHEMENT ",main);
// 			accountService.addservice(revision5); main.clear();main.add(mo7);
//			Revision revision6 = new Revision("FILTRE A POLLEN",main);
// 			accountService.addservice(revision6); main.clear();main.add(mo11);main.add(mo15);main.add(mo16);main.add(mo17);
//			Revision revision7 = new Revision("COURROIE CRANTE",main);
// 			accountService.addservice(revision7); main.clear();main.add(mo12);
//			Revision revision8 = new Revision("GALET TENDEUR",main);
// 			accountService.addservice(revision8); main.clear();main.add(mo14);
//			Revision revision9 = new Revision("GALET D INVERSION",main);
// 			accountService.addservice(revision9);
////			Revision revision10 = new Revision("GALET D INVERSION");
//// 			accountService.addservice(revision10);
//			Revision revision11 = new Revision("VIS + ECROUS",null);
// 			accountService.addservice(revision11); main.clear();main.add(mo10);
//			Revision revision12 = new Revision("COURROIE MULTIPISTES",main);
// 			accountService.addservice(revision12); main.clear();main.add(mo14);
//			Revision revision13 = new Revision("GALET D INVERSION ou vis",main);
// 			accountService.addservice(revision13); main.clear();main.add(mo13);
//			Revision revision14 = new Revision("TENDEUR",main);
// 			accountService.addservice(revision14); main.clear();main.add(mo8);
//			Revision revision15 = new Revision("HUILE BOITE A VITESSE",main);
// 			accountService.addservice(revision15); main.clear();main.add(mo9);
//			Revision revision16 = new Revision("TAMIS D'HUILE + JOINTS",main);
// 			accountService.addservice(revision16);
//			Revision revision17 = new Revision("HUILE UNIVERSE (Lubrifiant)",null);
// 			accountService.addservice(revision17);
//			Revision revision18 = new Revision("EAU DISTILE",null);
// 			accountService.addservice(revision18); main.clear();main.add(mo1);
//			Revision revision19 = new Revision("AD-BLEU ",main);
// 			accountService.addservice(revision19); main.clear();main.add(mo18);
//			Revision revision20 = new Revision("FILTRE -SEPARATEUR D'EAU-",main);
// 			accountService.addservice(revision20);
//




//			Revision r1 = new Revision("sqdqsdsqd",mainOeuvres);
//			accountService.addservice(r1);
//			Revision r2 = new Revision("wiwiwiwwiiw");
//			accountService.addservice(r2);
//			Revision r3 = new Revision("waawaawwawaaw");
//			accountService.addservice(r3);
//			Revision r4 = new Revision("hahaahahah");
//			accountService.addservice(r4);
//			PrixServices p1 = new PrixServices(null , v1 ,s1 ,888.26);
//			accountService.addNewPrixSer(p1);
//			PrixServices p2 = new PrixServices(null , v1 ,s2 ,233.93);
//			accountService.addNewPrixSer(p2);
//			PrixServices p3 = new PrixServices(null , v1 ,s18 ,27.37);
//			accountService.addNewPrixSer(p3);
//			PrixServices p4 = new PrixServices(null , v1 ,s17 ,6.05);
//			accountService.addNewPrixSer(p4);
//			PrixServices p5 = new PrixServices(null , v1 ,s19 ,398.00);
//			accountService.addNewPrixSer(p5);
			/*mo1.setService(s1);
			mo2.setService(s2);
			mo3.setService(s17);
			mo4.setService(s18);
			mo0.setService(s19);*/

//			List<Services> s = new ArrayList<>();
//			s.add(s1);s.add(s2);s.add(s18);s.add(s17);s.add(s19);
//			Packages pack1 = new Packages(null , "Simple 10000" ,s ,v1 ,1218.25);
//			//System.out.println(accountService.getPrixServices(v2,s1));
//			//accountService.CalcCout(pack1);
//			accountService.addNewPack(pack1);
//			StatusDevis statusDevis = new StatusDevis(null,Status.INPROGRESS,new Date());
//			accountService.addNewStatusDevis(statusDevis);
//			Devis d1 = new Devis(null, "devissdsd","dsdqsdqssd",user ,statusDevis,v1,null ,null,null ,null,s);
//			accountService.addNewDevis(d1);
		/*	MainOeuvre mainOeuvre = new MainOeuvre(1L,"agent de réduction:vérifier et compléter");
			accountService.addNewMainOeuvre(mainOeuvre);*/
			System.out.println(new Date());
		};
	}
}
