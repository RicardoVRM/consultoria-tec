package com.rvrm.consultoria_tec.repository;

import com.rvrm.consultoria_tec.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long> {
}
