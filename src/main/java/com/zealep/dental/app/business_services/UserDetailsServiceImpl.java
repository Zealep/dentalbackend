package com.zealep.dental.app.business_services;

import com.zealep.dental.app.model.entities.Usuario;
import com.zealep.dental.app.model.repository.UsuarioRepository;
import com.zealep.dental.app.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("zealep.users")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado. " + username));
        return this.userBuilder(user.getUsername(), user.getPassword(), new String[]{user.getRol()}, true);

    }

    private User userBuilder(String username, String password, String[] roles,
                             boolean active) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new User(username, password, active, true,
                true, true, authorities);
    }
}
