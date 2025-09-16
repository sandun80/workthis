package org.example.workthis.Controller;

import org.example.workthis.Model.Client;
import org.example.workthis.Model.LoginClient;
import org.example.workthis.Services.JWTService;
import org.example.workthis.Services.UserPrincipal;
import org.example.workthis.Services.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebController {
    @Autowired
    WebService service;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Client client){
        client.setPassword(encoder.encode(client.getPassword()));
        service.saveData(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginClient authRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            if (authentication.isAuthenticated()){
                return new ResponseEntity<>(jwtService.generateToken(authRequest.getEmail()), HttpStatus.OK);
//                return ResponseEntity.ok("Login successful");
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
    }
}
