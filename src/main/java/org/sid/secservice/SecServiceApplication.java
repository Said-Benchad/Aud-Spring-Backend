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
			accountService.addNewRole(new AppRole(null, "USER"));
			accountService.addNewRole(new AppRole(null, "ADMIN"));
			accountService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
			accountService.addNewRole(new AppRole(null, "PRODUCT_MANAGER"));
			accountService.addNewRole(new AppRole(null, "BILLS_MANAGER"));

			accountService.addNewUser(new AppUser("wassim","sdsds"));
			accountService.addNewUser(new AppUser("safaa","12345"));
			accountService.addNewUser(new AppUser("petissam","ASDFGG"));
			accountService.addNewUser(new AppUser(null,"narjiss","89@&",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"ghazal","145:;,as",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));

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
			Voiture v2 = new Voiture("A3", "Premium",m1);
			accountService.addNewVoiture(v2);
			Voiture v3 = new Voiture("A3", "Design",m1);
			accountService.addNewVoiture(v3);
			Voiture v4 = new Voiture("A4", "S Edition ",m2);
			accountService.addNewVoiture(v4);
			Voiture v5 = new Voiture("A5", "S-Line",m3);
			accountService.addNewVoiture(v5);
			Voiture v6 = new Voiture("A6", "S-Edition",m7);
			accountService.addNewVoiture(v6);
			Voiture v7 = new Voiture("Q2", "URBAN BVM",m4);
			accountService.addNewVoiture(v7);





		};
	}
}
