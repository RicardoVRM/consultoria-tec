package com.rvrm.consultoria_tec.controller;

import com.rvrm.consultoria_tec.dto.UsuarioDTO;
import com.rvrm.consultoria_tec.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void quandoBuscarPorId_entaoRetornarUsuarioDTO() throws Exception {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "João Silva", "12345678901", "joao@example.com", true, LocalDateTime.now(), LocalDateTime.now());
        when(usuarioService.buscarPorId(1L)).thenReturn(Optional.of(usuarioDTO));

        // Act & Assert
        mockMvc.perform(get("/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(usuarioDTO.nome()));
    }

    @Test
    public void quandoSalvarUsuario_entaoRetornarUsuarioDTOSalvo() throws Exception {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO(null, "Maria Oliveira", "98765432109", "maria@example.com", true, null, null);
        UsuarioDTO usuarioSalvo = new UsuarioDTO(1L, "Maria Oliveira", "98765432109", "maria@example.com", true, LocalDateTime.now(), LocalDateTime.now());
        when(usuarioService.criar(any(UsuarioDTO.class))).thenReturn(usuarioSalvo);

        // Act & Assert
        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuarioSalvo.id()));
    }
}
