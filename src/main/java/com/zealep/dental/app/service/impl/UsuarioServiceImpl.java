package com.zealep.dental.app.service.impl;

import com.zealep.dental.app.exceptions.NotFoundException;
import com.zealep.dental.app.model.entities.Usuario;
import com.zealep.dental.app.model.repository.UsuarioRepository;
import com.zealep.dental.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {

        //return usuarioRepository.findById(id).orElse(null);
        return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("usuario con id no encontrado. " + id));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado. " + username));
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
