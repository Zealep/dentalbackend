package com.zealep.dental.app.model.repository;

import com.zealep.dental.app.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);

    List<Usuario> findByRol(String rol);

    @Query("select u.idUsuario from Usuario u where u.username=?1 and u.estado='A'")
    String findIdUsuarioByUsername(String usuario);

}
