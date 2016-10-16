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

    public List<Usuario> profesoresConApellidos(){

        return usuarioRepository.findAllByApellidoNotNull();
    }

    /**
     *
     * @param nombre
     * @return
     */
    public List<Usuario> listaProfesorInicia(String nombre){
        System.out.println("Nombre recibido: "+nombre);
        return usuarioRepository.findAllByNombreStartingWith(nombre);
    }

    public List<Usuario> listaProfesorIniciaIgnorandoCase(String nombre){
        System.out.println("Nombre recibido: "+nombre);
        return usuarioRepository.findAllByNombreStartingWithIgnoreCase(nombre);
    }

   /* public Usuario profesorPorCedula(String cedula) {
        return usuarioRepository.consultaProfesor(cedula);
    }*/
}
