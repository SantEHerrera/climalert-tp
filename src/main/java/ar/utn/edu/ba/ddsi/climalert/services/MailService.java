package ar.utn.edu.ba.ddsi.climalert.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendAlert(String body){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("santherrera@frba.utn.edu.ar");
        message.setTo(
                "admin@clima.com","emergencias@clima.com","meteorologia@clima.com"
        );

        message.setSubject("ALERTA CLIMÁTICA");

        message.setText(body);

        mailSender.send(message);

    }
}
