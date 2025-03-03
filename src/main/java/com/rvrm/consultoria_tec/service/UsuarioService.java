package com.rvrm.consultoria_tec.service;

import com.rvrm.consultoria_tec.dto.UsuarioDTO;
import com.rvrm.consultoria_tec.entity.UsuarioEntity;
import com.rvrm.consultoria_tec.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(this::toDTO);
    }

    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = toEntity(usuarioDTO);
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataAlteracao(LocalDateTime.now());
        usuario = usuarioRepository.save(usuario);
        return toDTO(usuario);
    }

    public Optional<UsuarioDTO> atualizar(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioDTO.nome());
            usuario.setCpf(usuarioDTO.cpf());
            usuario.setEmail(usuarioDTO.email());
            usuario.setAtivo(usuarioDTO.ativo());
            usuario.setDataAlteracao(LocalDateTime.now());
            usuario = usuarioRepository.save(usuario);
            return toDTO(usuario);
        });
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toDTO(UsuarioEntity usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getAtivo(),
                usuario.getDataCriacao(),
                usuario.getDataAlteracao()
        );
    }

    private UsuarioEntity toEntity(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(usuarioDTO.nome());
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setEmail(usuarioDTO.email());
        usuario.setAtivo(usuarioDTO.ativo());
        return usuario;
    }
}