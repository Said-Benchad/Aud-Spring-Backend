package org.sid.secservice.sec.controllers;

import org.sid.secservice.sec.dtos.UserDto;
import org.sid.secservice.sec.entities.JwtResponse;
import org.sid.secservice.sec.jwt.JwtUtils;
import org.sid.secservice.sec.rep.AppUserRepository;
import org.sid.secservice.sec.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private  AuthenticationManager authenticationManager;
    // @Autowired
    // private  JWTUtil jwtTokenUtil;
    @Autowired
    private AppUserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
     public AuthController(AuthenticationManager authenticationManager,
             AppUserRepository userRepo,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }



        @GetMapping("/test")
        public ResponseEntity<?> test(){
            System.out.print("Request Recieved!");
             return ResponseEntity.ok(new JwtResponse("ASdasd", 
            2, 
            "said", 
            "said@said",
            null));
        }
        @PostMapping("/signin")
        public ResponseEntity<?> login(@RequestBody UserDto userDto) {



        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
        // List<String> roles = userDetails.getAuthorities().stream()
        //     .map(item -> item.getAuthority())
        //     .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt, 
            userDetails.getId(), 
            userDetails.getUsername(), 
            userDetails.getEmail(), 
            null));
        }
}
