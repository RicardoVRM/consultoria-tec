package com.rvrm.consultoria_tec.dto;

import com.rvrm.consultoria_tec.entity.UsuarioEntity;

import java.time.LocalDateTime;

public class UsuarioDTO extends UsuarioEntity {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
}
