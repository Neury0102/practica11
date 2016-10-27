package com.example.vistas;

import com.example.entidades.Usuario;
import com.example.servicios.UsuarioServices;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TextField;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dell_2 on 10/17/2016.
 */
@SpringUI(path = "/login")
@Theme("valo")
public class Login extends UI {
    @Autowired
    UsuarioServices usuarioServices;
    @Override
    protected void init(VaadinRequest request) {

        if(usuarioServices.findAll().size() <= 0){
            Usuario usuario = new Usuario();
            usuario.setNombre("Defecto");
            usuario.setApellido("Usuario");
            usuario.setCorreo("saletamanuel@gmail.com");
            usuario.setUsername("admin");
            usuario.setPassword("1234");
            usuarioServices.creacionUsuario(usuario);
        }
        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setWidth("2000px");
        layout.setHeight("500px");
        Label text = new Label("Login");
        layout.addComponent(text);

        TextField textField = new TextField();
        PasswordField textField2 = new PasswordField();
        textField.setDescription("Ayuda del componente..");
        textField.setInputPrompt("UserName");
        textField2.setInputPrompt("Password");



        Button boton=new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Usuario usuario = usuarioServices.autenticar(textField.getValue(),textField2.getValue());
                if(usuario != null){
                    getCurrent().getSession().setAttribute("usuario",usuario);
                    getUI().getPage().setLocation("http://localhost:8080/calendario");
                }
                else{
                    textField2.setValue("");
                }


            }
        });

        


        VerticalLayout hl=new VerticalLayout();
        layout.addComponent(text,"left: 960px; top: 25px;");
        layout.addComponent(textField,"left: 900px; top: 50px;");
        layout.addComponent(textField2,"left: 900px; top: 100px;");
        layout.addComponent(boton,"left: 950px; top: 140px;");


        hl.addComponent(layout);

        setContent(layout);
    }
}
