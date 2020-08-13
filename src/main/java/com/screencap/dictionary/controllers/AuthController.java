package com.screencap.dictionary.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.screencap.dictionary.models.AuthRequest;
import com.screencap.dictionary.models.AuthResponse;
import com.screencap.dictionary.models.ErrorMessage;
import com.screencap.dictionary.models.RegistrationBody;
import com.screencap.dictionary.models.User;
import com.screencap.dictionary.security.ApplicationUserDao;
import com.screencap.dictionary.security.JwtUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javassist.NotFoundException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserDao applicationUserDao;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    @PostMapping
    @RequestMapping("/signup")
    public ResponseEntity<?> signupAction(@RequestBody RegistrationBody reqBody) {

        String username = reqBody.getUsername();
        String password = reqBody.getPassword();
        String email = reqBody.getEmail();

        Set<ConstraintViolation<RegistrationBody>> violations = validator.validate(reqBody);

        if (violations.size() > 0) {
            List<String> violationMessages = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());

            String message = String.join(", ", violationMessages);

            return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(message),
                HttpStatus.BAD_REQUEST
            );
        }

        User theUser = applicationUserDao.getUserByUserName(username);

        // if user exists, close registration the process and return error:
        if (theUser != null)

            return new ResponseEntity<ErrorMessage>(
                new ErrorMessage("User already exists"),
                HttpStatus.BAD_REQUEST
            );

        // else
        User newUser = new User(username, passwordEncoder.encode(password));

        if (email != null)
            newUser.setEmail(reqBody.getEmail());

        applicationUserDao.saveUser(newUser);

        String jwtToken = jwtUtil.createToken("username", newUser.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }



    @RequestMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest reqBody) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    reqBody.getUsername(),
                    reqBody.getPassword()
                )
            );
        } catch (Exception e) {
            throw new Exception();
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(reqBody.getUsername());
        String token = jwtUtil.createToken("username", userDetails.getUsername());
        AuthResponse response = new AuthResponse(token);

        return ResponseEntity.ok(response);
    }

}
