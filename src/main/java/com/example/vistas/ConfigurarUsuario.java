package com.example.vistas;

import com.example.entidades.Usuario;
import com.example.servicios.UsuarioServices;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dell_2 on 10/17/2016.
 */
@SpringUI(path = "/configurar")
@Theme("valo")
public class ConfigurarUsuario extends UI {
    @Autowired
    UsuarioServices usuarioServices;
    @Override
    protected void init(VaadinRequest request) {
        Usuario usuario = (Usuario) getCurrent().getSession().getAttribute("usuario");
        if(usuario == null){
            getUI().getPage().setLocation("http://localhost:8080/login");
            return;
        }
        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setWidth("2000px");
        layout.setHeight("500px");
        Label text = new Label("Configuracion");
        layout.addComponent(text);

        TextField textField = new TextField();
        TextField textField2 = new TextField();
        textField.setValue(usuario.getNombre());
        textField2.setValue(usuario.getCorreo());
        textField.setInputPrompt("Nombre");
        textField2.setInputPrompt("Correo");



        Button boton=new Button("Guardar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                usuario.setNombre(textField.getValue());
                usuario.setCorreo(textField2.getValue());
                usuarioServices.creacionUsuario(usuario);
                getCurrent().getSession().setAttribute("usuario",usuario);
                getUI().getPage().setLocation("http://localhost:8080/calendario");

            }
        });

        


        VerticalLayout hl=new VerticalLayout();
        layout.addComponent(text,"left: 950px; top: 25px;");
        layout.addComponent(textField,"left: 900px; top: 50px;");
        layout.addComponent(textField2,"left: 900px; top: 100px;");
        layout.addComponent(boton,"left: 950px; top: 140px;");


        hl.addComponent(layout);

        setContent(layout);
    }
}
