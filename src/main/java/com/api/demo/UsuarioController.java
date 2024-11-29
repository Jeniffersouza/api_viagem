package com.api.demo;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    // No seu UsuarioController
    @Autowired
    private PasswordEncoder passwordEncoder;


    // Injeta o repository para teste da conexão
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    // Método para testar a conexão ao banco de dados
    @GetMapping("/teste-conexao")
    public ResponseEntity<String> testeConexao() {
        try {
            // Tentamos buscar o primeiro usuário no banco
            usuarioRepository.findAll();
            return ResponseEntity.ok("Conexão com o banco de dados bem-sucedida.");
        } catch (Exception e) {
            // Se algum erro acontecer, retornamos um erro
            return ResponseEntity.status(500).body("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
         
        public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
            try {
                // Verifica se o usuário já existe
               
                
                // Criptografando a senha antes de salvar
                String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
                usuario.setPassword(senhaCriptografada);

                // Salva o usuário no banco
                usuarioRepository.save(usuario);

                return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor: " + e.getMessage());
            }
        }

    @PostMapping("/login")
         
        public ResponseEntity<?> login(@RequestBody Usuario usuario) {
            try {
               // Busca o usuário pelo username no repositório
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(usuario.getUsername());
        
        // Verifica se o usuário existe
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        Usuario usuarioExistente = optionalUsuario.get();

        // Verifica se a senha corresponde à senha criptografada
        boolean senhaCorreta = passwordEncoder.matches(usuario.getPassword(), usuarioExistente.getPassword());
        if (!senhaCorreta) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida.");
        }

        // Retorna mensagem de sucesso
        return ResponseEntity.ok("Usuário logado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor: " + e.getMessage());
            }
        }



    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
