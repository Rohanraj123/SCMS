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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController
{
    private final AuthService authService;
    private final JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

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
    public String login(@RequestBody User user)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String jwtToken = jwtService.generateToken(user);
            return "Bearer " + jwtToken;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
