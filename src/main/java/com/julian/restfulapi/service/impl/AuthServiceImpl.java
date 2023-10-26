package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.controller.models.AuthRequest;
import com.julian.restfulapi.controller.models.AuthResponse;
import com.julian.restfulapi.controller.models.RegisterRequest;
import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Role;
import com.julian.restfulapi.repository.CustomerRepository;
import com.julian.restfulapi.service.AuthService;
import com.julian.restfulapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        customerRepository.save(customer);
        String jwtToken = jwtService.generateToken(customer);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        Customer customer = customerRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(customer);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
