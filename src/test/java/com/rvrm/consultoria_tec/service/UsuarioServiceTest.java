package com.rvrm.consultoria_tec.service;


import com.rvrm.consultoria_tec.dto.UsuarioDTO;
import com.rvrm.consultoria_tec.entity.UsuarioEntity;
import com.rvrm.consultoria_tec.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void quandoBuscarPorId_entaoRetornarUsuarioDTO() {
        // Arrange
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setCpf("12345678901");
        usuario.setEmail("joao@example.com");
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataAlteracao(LocalDateTime.now());

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        Optional<UsuarioDTO> encontrado = usuarioService.buscarPorId(1L);

        // Assert
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().nome()).isEqualTo(usuario.getNome());
    }

    @Test
    public void quandoSalvarUsuario_entaoRetornarUsuarioDTO() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO(null, "Maria Oliveira", "98765432109", "maria@example.com", true, null, null);
        UsuarioEntity usuarioSalvo = new UsuarioEntity();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome(usuarioDTO.nome());
        usuarioSalvo.setCpf(usuarioDTO.cpf());
        usuarioSalvo.setEmail(usuarioDTO.email());
        usuarioSalvo.setAtivo(usuarioDTO.ativo());
        usuarioSalvo.setDataCriacao(LocalDateTime.now());
        usuarioSalvo.setDataAlteracao(LocalDateTime.now());

        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioSalvo);

        // Act
        UsuarioDTO salvo = usuarioService.criar(usuarioDTO);

        // Assert
        assertThat(salvo.id()).isNotNull();
        assertThat(salvo.nome()).isEqualTo(usuarioDTO.nome());
    }
}
