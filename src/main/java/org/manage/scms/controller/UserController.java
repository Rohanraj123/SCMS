package org.manage.scms.controller;

import org.manage.scms.dto.AuthDto;
import org.manage.scms.exception.UserAlreadyExistsException;
import org.manage.scms.model.User;
import org.manage.scms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthDto authDto)
    {
        try
        {
            User user = authService.registerUser(authDto);
            return ResponseEntity.ok("User " + user.getUsername() + " is registered!");
        } catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthDto authDto)
    {
        try
        {
             User user = authService.loginUser(authDto);
             if (user != null)
             {
                 return ResponseEntity.ok("User " + user.getUsername() + " has loggedIn!");
             }
             else
             {
                 return ResponseEntity.badRequest().body("Unexpected event occurred!");
             }
        } catch (UsernameNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
