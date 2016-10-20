package com.example.repositorio;


import com.example.entidades.Evento;
import com.example.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by vacax on 20/09/16.
 */
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findAllByOrderByFechaInicio();


    //Ordenando la lista de profesores por fecha.
   // List<Usuario> findAllByOrderByFechaNacimientoDesc();

    //Trabajando con los querys de HQL.
  /*  @Query("select u from Usuario u where u.cedula = ?1")
    Usuario consultaProfesor(String cedula);

    @Query("select u from Usuario u where u.cedula = :cedula")
    Usuario consultaProfesorCedula(@Param("cedula") String cedula);

    @Query(value = "select * from Usuario p where p.cedula = :cedula", nativeQuery = true)
    Usuario consultaProfesorCedulaNativo(@Param("cedula") String cedula);*/
}
