package com.example.vistas;

import com.example.entidades.Evento;
import com.example.servicios.EventoServices;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;


@Component
@UIScope
class EventoForm extends FormLayout{

    @Autowired
    private EventoServices service;
    private Evento evento;

    private DateField fecha=new DateField("Hora y fecha");
    private TextField nombre=new TextField("Nombre");
    private TextField descripcion=new TextField("Descripcion");
    private ComboBox duracion = new ComboBox("Duracion");
    private Button save = new Button("Guardar");
    private Calendario calendarioUI;
    private Label validacion = new Label("HAY UN EVENTO QUE EMPALMA CON ESTA FECHA");



    public EventoForm() {

        fecha.setResolution(Resolution.MINUTE);
        validacion.setVisible(false);
        duracion.addItem("1");
        duracion.addItem("2");
        duracion.addItem("3");
        duracion.addItem("4");
        duracion.addItem("5");

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save);
        buttons.setSpacing(true);

        addComponents(nombre, descripcion, fecha,duracion,validacion, buttons);
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(fecha.getValue());
                int tiempo = Integer.parseInt(duracion.getValue().toString());
                cal.add(GregorianCalendar.HOUR_OF_DAY, tiempo);


                Evento evento = new Evento();
                evento.setDescripcion(descripcion.getValue());
                evento.setNombre(nombre.getValue());
                evento.setFechaInicio(fecha.getValue());
                evento.setFechaFin(cal.getTime());
                evento.setNotificacionEnviada(false);
                if(validateEvent(evento)){
                    service.creacionEvento(evento);
                    calendarioUI.container.addBean(evento);
                    validacion.setVisible(false);
                }
                else {
                    validacion.setVisible(true);
                }

            }
        });



    }

    public void setCalendarioUI(Calendario calendarioUI){
        this.calendarioUI = calendarioUI;
    }

    private boolean validateEvent(Evento evento){
        for(Evento e: service.todosEventos() ){
            DateTime start1 = new DateTime(evento.getFechaInicio());
            DateTime end1 = new DateTime(evento.getFechaFin());
            DateTime start2 =  new DateTime(e.getFechaInicio());
            DateTime end2 = new DateTime(e.getFechaFin());
            Interval interval = new Interval( start1, end1 );
            Interval interval2 = new Interval( start2, end2 );
            if(interval.overlaps(interval2))
                return  false;

        }
        return true;
    }






}
