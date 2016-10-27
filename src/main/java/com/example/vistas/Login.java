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
        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setWidth("1000px");
        layout.setHeight("500px");
        Label text = new Label("Login");
        layout.addComponent(text);

        TextField textField = new TextField();
        TextField textField2 = new TextField();
        textField.setDescription("Ayuda del componente..");
        textField.setInputPrompt("UserName");
        textField2.setInputPrompt("Password");



        Button boton=new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getPage().setLocation("http://localhost:8080/calendario");
            }
        });

        


        VerticalLayout hl=new VerticalLayout();
        layout.addComponent(text,"left: 860px; top: 25px;");
        layout.addComponent(textField,"left: 600px; top: 50px;");
        layout.addComponent(textField2,"left: 600px; top: 100px;");
        layout.addComponent(boton,"left: 650px; top: 140px;");


        hl.addComponent(layout);

        setContent(layout);
    }
}
