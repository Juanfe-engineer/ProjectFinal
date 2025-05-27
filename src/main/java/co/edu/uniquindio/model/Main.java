package co.edu.uniquindio.model;

import co.edu.uniquindio.clasesExtra.CorreoUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;



public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // 2. Autenticación (usa una contraseña de aplicación)
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "jacoboarboleda511@gmail.com",  // Reemplaza con tu correo
                        "glcq hxhd mfod hkpo"   // Contraseña de aplicación
                );
            }
        });

        try {
            // 3. Creación del mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jacoboarboleda511@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("juanfe0151@gmail.com"));
            message.setSubject("Cita medica");

            // 4. Usando CorreoUtils (simulado)
            Paciente paciente = new Paciente("Jacobo","123","Jacobo@Uqvirtual.edu.co","3103674329");
            Horario horario = new Horario("tarde",LocalDate.of(2025,5,30), LocalTime.now(),Jornada.TARDE);
            Medico medico = new Medico("Dr. Garcia","M001","Garcia@Uqvirtual.edu","3106543278",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);
            Sala sala = new Sala("S001",TipoSala.CONSULTA,EstadoSala.DISPONIBLE,4);

            Cita cita = new Cita("Fractura","No moverse","C001",paciente,medico,sala, horario,EstadoCita.PROGRAMADA);

            String mensajeTexto = CorreoUtils.generarMensajeCorreo(cita);


            message.setText(mensajeTexto);

            // 5. Envío
            System.out.println("Enviando...");
            Transport.send(message);
            System.out.println("¡Correo enviado!");

        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo:");
            e.printStackTrace(); // Detalles del error
        }
    }
}