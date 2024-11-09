package org.manage.scms.service;

import org.manage.scms.dto.AuthDto;
import org.manage.scms.exception.UserAlreadyExistsException;
import org.manage.scms.model.User;
import org.manage.scms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(AuthDto authDto) throws UserAlreadyExistsException
    {
        if (userRepository.findByUsername(authDto.getUsername()).isPresent())
        {
            throw new UserAlreadyExistsException("User already exists!");
        }
        User user = new User();
        user.setUsername(authDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authDto.getPassword()));

        userRepository.save(user);
        return user;
    }

    public User authenticate(AuthDto authDto)
    {
        User user = userRepository.findByUsername(authDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String reqPassword = authDto.getPassword();
        boolean matches = bCryptPasswordEncoder.matches(reqPassword, user.getPassword());
        if (matches) return user;

        return null;
    }
}
