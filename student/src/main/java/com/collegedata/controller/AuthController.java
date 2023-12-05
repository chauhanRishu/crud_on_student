package com.collegedata.controller;



import com.collegedata.entities.JwtRequest;
import com.collegedata.entities.JwtResponse;
import com.collegedata.security.JwtHelper;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Builder
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {
//        System.out.println("doAuthentication");

        this.doAuthenticate(request.getEmail(), request.getPassword());
//        System.out.println("After doAuthentication");

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println("UserDetail :: "+userDetails.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .email(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private void doAuthenticate(String email, String password) throws Exception {
        System.out.println("Password : "+ password);

//        System.out.println("Above inside Auth");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
//            System.out.println("Inside doAuthe");

            manager.authenticate(authentication);

        } catch (BadCredentialsException invalidUser) {
           throw new Exception("user is invalid");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}

