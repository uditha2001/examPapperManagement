package com.example.examManagementBackend.userManagement.userManagementController;

import com.example.examManagementBackend.userManagement.userManagementDTO.LoginRequestDTO;
import com.example.examManagementBackend.userManagement.userManagementDTO.LoginResponseDTO;
import com.example.examManagementBackend.userManagement.userManagementServices.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LogginController {
    @Autowired
    private JwtService jwtService;
    @PostMapping("/authentication")
    public LoginResponseDTO createJwtTokenAndLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
            LoginResponseDTO loginResponseDTO1=jwtService.CreateJwtToken(loginRequestDTO);
            return loginResponseDTO1;

    }
}