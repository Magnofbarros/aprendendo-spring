package com.javanauta.aprendendospring.business;

import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import com.javanauta.aprendendospring.infrastructure.exceptions.ConflictException;
import com.javanauta.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {

        try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Email já cadastrado: " + usuario.getEmail(), e);
        }
    }

    public void emailExiste(String email) {
        if (verificarEmailExiste(email)) {
            throw new ConflictException("Email já cadastrado: " + email);
        }
    }

    private boolean verificarEmailExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("email não encontrado" + email));
    }
    public void deletarPorEmail(String email) {
            usuarioRepository.deleteByEmail(email);
    }
}