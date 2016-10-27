package com.example.servicios;

import com.example.entidades.Usuario;
import com.example.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by vacax on 20/09/16.
 */
@Service
public class UsuarioServices {

    //Inyectando el com.example.repositorio
    @Autowired
    private UsuarioRepository usuarioRepository;

    public long cantidadUsuario(){
        return usuarioRepository.count();
    }

    /**
     * Indica que será una transacción, ver la anotación...
     * @param usuario
     * @return
     */
    @Transactional
    public Usuario creacionUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public Usuario autenticar(String username, String password){
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null)
            return null;
        if( password.equals(usuario.getPassword())){
            return  usuario;
        }
        else
            return null;
    }


}
