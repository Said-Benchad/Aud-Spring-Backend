package org.sid.secservice;

import org.sid.secservice.sec.entities.AppRole;
import org.sid.secservice.sec.entities.AppUser;
import org.sid.secservice.sec.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

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

			accountService.addNewUser(new AppUser(null,"wassim","sdsds",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"safaa","12345",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"petissam","ASDFGG",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
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


		};
	}
}
