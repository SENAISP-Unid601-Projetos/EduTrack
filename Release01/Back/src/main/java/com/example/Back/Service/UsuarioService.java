package com.example.Back.Service;

import com.example.Back.dto.CadastroDTO;
import com.example.Back.entity.Usuario;
import com.example.Back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String email, String senha) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        if (!usuario.getSenha().equals(senha)) {
            throw new Exception("Senha incorreta");
        }

        return usuario;
    }

    public Usuario cadastrar(CadastroDTO cadastroDTO) throws Exception {
        if (usuarioRepository.findByEmail(cadastroDTO.getEmail()).isPresent()) {
            throw new Exception("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(cadastroDTO.getNome());
        usuario.setEmail(cadastroDTO.getEmail());
        usuario.setSenha(cadastroDTO.getSenha());

        return usuarioRepository.save(usuario);
    }
}