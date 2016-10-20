package com.example.vistas;


import com.example.entidades.Evento;
import com.example.repositorio.EventoRepository;
import com.example.servicios.EventoServices;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.sqlcontainer.query.generator.filter.StringDecorator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import com.vaadin.ui.components.calendar.event.BasicEvent;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.EntityManager;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

@SpringUI(path = "/calendario")
@Theme("valo")
class PruebaCalendario extends UI {


    //Utilizando el calendario.
    public Calendar calendario;
    @Autowired
    EventoServices eventoServices;
    @Autowired
    private EventoForm form;

    BeanItemContainer container;
    @Override
    protected void init(VaadinRequest request) {
        form.setCalendarioUI(this);

        calendario = new Calendar();
        calendario.setLocale(Locale.US);
        calendario.setFirstVisibleDayOfWeek(2);   //Lunes
        calendario.setLastVisibleDayOfWeek(6);   // Viernes
        calendario.setFirstVisibleHourOfDay(0); // 8 am
        calendario.setLastVisibleHourOfDay(24); // 5 pm
        calendario.setWidth("1400px");
        calendario.setHeight("600px");
        List<Evento> eventos = eventoServices.todosEventos();
        container = new BeanItemContainer<Evento>(Evento.class,eventos);
        calendario.setContainerDataSource(container, "nombre",
                "descripcion", "fechaInicio", "fechaFin","styleName");







        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(calendario);

        hl.addComponent(form);
        setContent(hl);






    }



}
