package com.gestion_proyectos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.gestion_proyectos.api.security.JwtUtil;
import com.gestion_proyectos.api.dto.LoginRequest;
import com.gestion_proyectos.api.dto.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Endpoints para autenticación y autorización")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/login")
  @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un JWT token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login exitoso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
      @ApiResponse(responseCode = "400", description = "Credenciales inválidas"),
      @ApiResponse(responseCode = "401", description = "No autorizado")
  })
  public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
    logger.info("Login attempt for email: {}", loginRequest.getEmail());

    try {
      // Validate input
      if (loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
        logger.error("Email is null or empty");
        return ResponseEntity.badRequest().body("Email is required");
      }

      if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
        logger.error("Password is null or empty");
        return ResponseEntity.badRequest().body("Password is required");
      }

      logger.info("Attempting authentication for user: {}", loginRequest.getEmail());

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

      logger.info("Authentication successful for user: {}", loginRequest.getEmail());

      final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
      final String jwt = jwtUtil.generateToken(userDetails);

      logger.info("JWT token generated successfully for user: {}", loginRequest.getEmail());

      return ResponseEntity.ok(new LoginResponse(jwt));
    } catch (Exception e) {
      logger.error("Authentication failed for user: {} - Error: {}", loginRequest.getEmail(), e.getMessage(), e);
      return ResponseEntity.badRequest().body("Invalid email or password: " + e.getMessage());
    }
  }

  @PostMapping("/test")
  @Operation(summary = "Endpoint de prueba", description = "Verifica que el endpoint de autenticación esté funcionando")
  @ApiResponse(responseCode = "200", description = "Endpoint funcionando correctamente")
  public ResponseEntity<?> testEndpoint() {
    logger.info("Test endpoint called");
    return ResponseEntity.ok("Auth endpoint is working!");
  }
}
