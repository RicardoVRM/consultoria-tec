package com.rvrm.consultoria_tec.dto;

import java.time.LocalDateTime;

public record UsuarioDTO(Long id, String nome, String cpf, String email, boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataAlteracao) {
}