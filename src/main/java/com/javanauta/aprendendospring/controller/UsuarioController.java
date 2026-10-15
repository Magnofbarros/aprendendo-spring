package com.javanauta.aprendendospring.controller;

import com.javanauta.aprendendospring.business.UsuarioService;
import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import com.javanauta.aprendendospring.infrastructure.security.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    public final UsuarioService usuarioService;
    public final AuthenticationManager authenticationManager;
    public final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity <Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }
    @GetMapping("/email")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(
            @RequestParam("email") String email) {

        return ResponseEntity.ok(
                usuarioService.buscarUsuarioPorEmail(email)
        );

    }
    @DeleteMapping("/email")
    public ResponseEntity<Void> deletarPorEmail(
            @RequestParam("email") String email) {

        usuarioService.deletarPorEmail(email);
        return ResponseEntity.noContent().build();

    }

}
