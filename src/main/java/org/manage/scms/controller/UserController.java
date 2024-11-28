package org.manage.scms.controller;

import org.manage.scms.dto.AuthDto;
import org.manage.scms.dto.LoginResponse;
import org.manage.scms.dto.RegisterDto;
import org.manage.scms.exception.UserAlreadyExistsException;
import org.manage.scms.model.User;
import org.manage.scms.service.AuthService;
import org.manage.scms.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        try {
            User user = authService.register(registerDto);
            return ResponseEntity.ok("User " + user.getUsername() + " is registered!");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody AuthDto authDto) {
        try {
            String token = authService.authenticate(authDto);
            LoginResponse logInResponse = new LoginResponse();

            logInResponse.setExpiresIn(jwtService.getExpirationTime());
            logInResponse.setToken(token);

            return ResponseEntity.ok(logInResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
