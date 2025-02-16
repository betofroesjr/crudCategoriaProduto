package com.josehumberto.testeDev.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.josehumberto.testeDev.Dto.LoginRequest;
import com.josehumberto.testeDev.Util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // Simulação de usuário fixo (substituir por consulta ao banco)
        if ("admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
    }
}