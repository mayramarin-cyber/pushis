package com.example.libreria_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria_back.dto.AuthResponse;
import com.example.libreria_back.dto.LoginRequest;
import com.example.libreria_back.entity.UsuarioEntity;
import com.example.libreria_back.security.JwtUtil;
import com.example.libreria_back.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticación", description = "API de autenticación y gestión de tokens JWT")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Buscar usuario por email
            UsuarioEntity usuario = findUserByEmail(loginRequest.getEmail());

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse(null, null, "Usuario no encontrado"));
            }

            // Verificar contraseña (SIN ENCRIPTAR - versión simple)
            if (!usuario.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse(null, null, "Contraseña incorrecta"));
            }

            // Generar token
            String token = jwtUtil.generateToken(usuario.getEmail());

            return ResponseEntity.ok(new AuthResponse(token, usuario.getEmail(), "Login exitoso"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(null, null, "Error en el servidor: " + e.getMessage()));
        }
    }

    // Método auxiliar para buscar usuario por email
    private UsuarioEntity findUserByEmail(String email) {
        return usuarioService.listarUsuarios().stream()
                .filter(u -> u.getEmail() != null && u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
