package com.parksmartbackend.parksmart.controller;

import com.parksmartbackend.parksmart.model.User;
import com.parksmartbackend.parksmart.payload.request.LoginRequest;
import com.parksmartbackend.parksmart.payload.response.JwtResponse;
import com.parksmartbackend.parksmart.repositories.UserRepository;
import com.parksmartbackend.parksmart.security.jwt.JwtUtils;
import com.parksmartbackend.parksmart.services.UserDetailsImpl;
import com.parksmartbackend.parksmart.services.UserDetailsServiceImpl;
import com.parksmartbackend.parksmart.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "Authorization Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;

    private final UserService userService;

    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          UserDetailsServiceImpl userDetailsService,UserService userService, JwtUtils jwtUtils) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userDetailsService=  userDetailsService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        if (!userRepository.existsByEmail(loginRequest.getEmail()).booleanValue())
            return ResponseEntity.badRequest().body("accountNotFound");

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
                .loadUserByUsername(loginRequest.getEmail());

        User user = userService.findByEmail(loginRequest.getEmail());
        if (!userDetails.isEnabled()) {
            return ResponseEntity.badRequest().body("userNotActivated");
        }

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("wrongPass");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, user, roles));
    }
}
