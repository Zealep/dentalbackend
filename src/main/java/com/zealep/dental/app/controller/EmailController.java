package com.zealep.dental.app.controller;

import com.zealep.dental.app.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender sender;

    @PostMapping(value="/enviar")
    public ResponseEntity<RespuestaApi> sendMail() throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<>();
        model.put("nombre","Cristhian Pelaez");
        model.put("edad","26");
        model.put("fecha","04-04-2020");

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("cita",context);

        try {
            helper.setTo("cristhianpelaez13@gmail.com");
            helper.setText(html,true);
            helper.setSubject("Cita Jossdent");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
        return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null, ""), HttpStatus.CREATED);
    }

}