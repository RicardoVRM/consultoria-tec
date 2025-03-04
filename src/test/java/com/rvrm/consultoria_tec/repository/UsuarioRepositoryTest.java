package com.rvrm.consultoria_tec.repository;

import com.rvrm.consultoria_tec.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void quandoBuscarPorId_entaoRetornarUsuario() {
        // Arrange
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("João Silva");
        usuario.setCpf("12345678901");
        usuario.setEmail("joao@example.com");
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataAlteracao(LocalDateTime.now());
        entityManager.persistAndFlush(usuario);

        // Act
        Optional<UsuarioEntity> dadoEncontrado = usuarioRepository.findById(usuario.getId());

        // Assert
        assertThat(dadoEncontrado).isPresent();
        assertThat(dadoEncontrado.get().getNome()).isEqualTo(usuario.getNome());
    }

    @Test
    public void quandoSalvarUsuario_entaoRetornarUsuarioSalvo() {
        // Arrange
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Maria Oliveira");
        usuario.setCpf("98765432109");
        usuario.setEmail("maria@example.com");
        usuario.setAtivo(true);

        // Act
        UsuarioEntity salvo = usuarioRepository.save(usuario);

        // Assert
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getNome()).isEqualTo(usuario.getNome());
    }
}