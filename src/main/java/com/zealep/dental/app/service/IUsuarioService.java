package com.zealep.dental.app.service;

import com.zealep.dental.app.model.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario findById(Long id);

    List<Usuario> findAll();

    List<Usuario> findByRol(String rol);

    Usuario findByUsername(String username);

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Long id);


}
