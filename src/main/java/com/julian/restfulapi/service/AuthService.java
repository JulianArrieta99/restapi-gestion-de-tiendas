package com.julian.restfulapi.service;

import com.julian.restfulapi.controller.models.AuthRequest;
import com.julian.restfulapi.controller.models.AuthResponse;
import com.julian.restfulapi.controller.models.RegisterRequest;

public interface AuthService {
   AuthResponse register(RegisterRequest request);
   AuthResponse login(AuthRequest request);


}
