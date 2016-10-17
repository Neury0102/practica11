package com.example.vistas;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TextField;
import com.vaadin.ui.*;

/**
 * Created by Dell_2 on 10/17/2016.
 */
@SpringUI(path = "/login")
@Theme("valo")
public class Login extends UI {
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("Login"));

        TextField textField = new TextField();
        TextField textField2 = new TextField();
        textField.setDescription("Ayuda del componente..");
        textField.setInputPrompt("UserName");
        textField2.setInputPrompt("Password");



        Button boton=new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Hola ${textField.value}", Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        Button boton2=new Button("Registrar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Notification.show("Hola ${textField.value}", Notification.Type.HUMANIZED_MESSAGE);
            }
        });


        VerticalLayout hl=new VerticalLayout();
        hl.addComponent(textField);
        hl.addComponent(textField2);
        hl.addComponent(boton);
        hl.addComponent(boton2);




        verticalLayout.addComponent(hl);

        setContent(verticalLayout);
    }
}
