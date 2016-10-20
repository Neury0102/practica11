package com.example.servicios;

import com.example.entidades.Evento;
import com.example.entidades.Usuario;
import com.example.repositorio.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EventoServices {

    //Inyectando el com.example.repositorio
    @Autowired
    private EventoRepository eventoRepository;

    public long cantidadUsuario(){
        return eventoRepository.count();
    }


    @Transactional
    public Evento creacionEvento(Evento evento){
        eventoRepository.save(evento);
        return evento;
    }

    public List<Evento> todosEventos(){

        return eventoRepository.findAll();
    }


}
