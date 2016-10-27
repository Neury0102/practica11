package com.example.servicios;

import com.example.entidades.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    EventoServices eventoServices;

    @Autowired
    EmailServices emailServices;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime(){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        int minutos = 5;
        cal.add(GregorianCalendar.MINUTE, minutos);
        List<Evento> eventos = eventoServices.todosEventos();
        for(Evento evento: eventos){
            long diff = cal.getTime().getTime() - evento.getFechaInicio().getTime();
            Date now = cal.getTime();
            Date check = evento.getFechaInicio();
            System.out.print(diff);
            if(diff > 0 && !evento.isNotificacionEnviada()){
                emailServices.enviarCorreo(evento);
                evento.setNotificacionEnviada(true);
                eventoServices.creacionEvento(evento);
            }
        }
    }
}