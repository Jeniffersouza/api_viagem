package com.api.demo;
import java.util.Optional;

import com.api.demo.Usuario;
import com.api.demo.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UsuarioService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private  PasswordEncoder passwordEncoder; // Adicionando o PasswordEncoder

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder; // Injeção correta
    }

    // Método para buscar o usuário pelo nome de usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome: " + username));

        // Retorna os detalhes do usuário para o Spring Security
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getPerfil().name())  // Exemplo: "ADMIN" ou "USER"
                .build();
    }

    // Método para buscar um usuário pelo ID
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);  // Usa o método findById do JPA
    }

    // Outros métodos para salvar, atualizar, excluir, etc.
    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Criptografa a senha
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> update(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
