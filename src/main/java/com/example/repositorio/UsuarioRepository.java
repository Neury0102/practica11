package com.example.repositorio;


import com.example.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by vacax on 20/09/16.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);
    Usuario findByUsername(String username);
    List<Usuario> findAll();
    Usuario findByNombreAndApellido(String nombre, String apellido);





}
