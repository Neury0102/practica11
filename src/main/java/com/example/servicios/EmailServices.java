package com.example.servicios;

import com.example.config.AppConfig;
import com.example.entidades.Evento;
import com.example.entidades.Usuario;
import com.example.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by vacax on 20/09/16.
 */
@Service
public class EmailServices {

  public void enviarCorreo(Evento evento, Usuario usuario){

      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.register(AppConfig.class);
      ctx.refresh();
      JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
      try {
          mailMsg.setFrom("practica11spring@gmail.com");
          mailMsg.setTo(usuario.getCorreo());
          mailMsg.setSubject("Un evento esta cercano.");
          mailMsg.setText("Hola, "+usuario.getNombre()+", el evento: " +evento.getNombre()+ " esta a punto de iniciar.");
      } catch (MessagingException e) {
          e.printStackTrace();
      }
      mailSender.send(mimeMessage);
  }

}
