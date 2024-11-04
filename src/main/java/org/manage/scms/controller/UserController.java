package org.manage.scms.controller;

import io.jsonwebtoken.Jwt;
import org.manage.scms.dto.AuthDto;
import org.manage.scms.dto.LoginResponse;
import org.manage.scms.exception.UserAlreadyExistsException;
import org.manage.scms.model.User;
import org.manage.scms.service.AuthService;
import org.manage.scms.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController
{
    private final AuthService authService;
    private final JwtService jwtService;

    @Autowired
    public UserController(AuthService authService, JwtService jwtService)
    {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthDto authDto)
    {
        try
        {
            User user = authService.registerUser(authDto);
            return ResponseEntity.ok("User " + user.getUsername() + " is registered!");
        }
        catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AuthDto authDto)
    {
        try
        {
             User authenticatedUser = authService.loginUser(authDto);
             String jwtToken = jwtService.generateToken(authenticatedUser);
             LoginResponse loginResponse = new LoginResponse();
             loginResponse.setToken(jwtToken);
             loginResponse.setExpiresIn(jwtService.getExpirationTime());

             return ResponseEntity.ok(loginResponse);
        }
        catch (UsernameNotFoundException e)
        {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
