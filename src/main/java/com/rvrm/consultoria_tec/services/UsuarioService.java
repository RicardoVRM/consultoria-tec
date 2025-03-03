package com.rvrm.consultoria_tec.services;

import com.rvrm.consultoria_tec.dto.UsuarioDTO;
import com.rvrm.consultoria_tec.entity.UsuarioEntity;
import com.rvrm.consultoria_tec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return toDTO(usuarioEntity);
    }

    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = toEntity(usuarioDTO);
        usuarioEntity.setAtivo(true);
        usuarioEntity.setDataCriacao(LocalDateTime.now());
        usuarioEntity.setDataAlteracao(LocalDateTime.now());
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return toDTO(usuarioEntity);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioEntity.setNome(usuarioDTO.getNome());
        usuarioEntity.setCpf(usuarioDTO.getCpf());
        usuarioEntity.setEmail(usuarioDTO.getEmail());
        usuarioEntity.setAtivo(usuarioDTO.getAtivo());
        usuarioEntity.setDataAlteracao(LocalDateTime.now());
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return toDTO(usuarioEntity);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toDTO(UsuarioEntity usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setAtivo(usuario.getAtivo());
        dto.setDataCriacao(usuario.getDataCriacao());
        dto.setDataAlteracao(usuario.getDataAlteracao());
        return dto;
    }

    private UsuarioEntity toEntity(UsuarioDTO dto) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setAtivo(dto.getAtivo());
        return usuario;
    }
}