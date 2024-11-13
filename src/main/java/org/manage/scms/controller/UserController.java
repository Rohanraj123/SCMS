package org.manage.scms.controller;

import io.jsonwebtoken.Jwt;
import org.manage.scms.dto.AuthDto;
import org.manage.scms.dto.LogInResponse;
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
    public ResponseEntity<String> register(@RequestBody AuthDto authDto)
    {
        try
        {
            User user = authService.register(authDto);
            return ResponseEntity.ok("User " + user.getUsername() + " is registered!");
        }
        catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LogInResponse> authenticate(@RequestBody AuthDto authDto)
    {
        try
        {
            User user = authService.authenticate(authDto);
            if (user != null)
            {
                String jwtToken = jwtService.generateToken(user);
                LogInResponse logInResponse = new LogInResponse();
                logInResponse.setExpiresIn(jwtService.getExpirationTime());
                logInResponse.setToken(jwtToken);

                return ResponseEntity.ok(logInResponse);
            }
            return ResponseEntity.badRequest().body(null);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public User convertDtoToUser(AuthDto authDto)
    {
        User user = new User();
        user.setPassword(authDto.getPassword());
        user.setUsername(authDto.getUsername());

        return user;
    }
}
