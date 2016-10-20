package com.example.vistas;

import com.example.entidades.Evento;
import com.example.servicios.EventoServices;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

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
    private PruebaCalendario calendarioUI;



    public EventoForm() {

        fecha.setResolution(Resolution.MINUTE);
        duracion.addItem("1");
        duracion.addItem("2");
        duracion.addItem("3");
        duracion.addItem("4");
        duracion.addItem("5");

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save);
        buttons.setSpacing(true);

        addComponents(nombre, descripcion, fecha,duracion, buttons);
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
                service.creacionEvento(evento);
                calendarioUI.container.addBean(evento);

            }
        });



    }

    public void setCalendarioUI(PruebaCalendario calendarioUI){
        this.calendarioUI = calendarioUI;
    }






}
