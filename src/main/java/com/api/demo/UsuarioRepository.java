package com.api.demo;

import com.api.demo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Você pode adicionar consultas customizadas se necessário
    Optional<Usuario> findByUsername(String username);
    
}
