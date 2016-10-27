package com.example.servicios;

import com.example.entidades.Evento;
import com.example.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    EventoServices eventoServices;

    @Autowired
    UsuarioServices usuarioServices;

    @Autowired
    EmailServices emailServices;


    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime(){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        int minutos = 5;
        cal.add(GregorianCalendar.MINUTE, minutos);
        List<Evento> eventos = eventoServices.todosEventos();
        for(Evento evento: eventos){
            long diff = cal.getTime().getTime() - evento.getFechaInicio().getTime();
            System.out.print(diff);
            if(diff > 0 && !evento.isNotificacionEnviada()){
                Usuario u = usuarioServices.findAll().get(0);
                emailServices.enviarCorreo(evento,u);
                evento.setNotificacionEnviada(true);
                eventoServices.creacionEvento(evento);
            }
        }
    }
}