package co.edu.uniquindio.model;

public class CorreoUtils {
    public static String generarMensajeCorreo(Cita cita) {
        StringBuilder sb = new StringBuilder();
        sb.append("Estimado paciente,\n\n");
        sb.append("Su cita ha sido programada con éxito.\n");
        sb.append("Detalles de la cita:\n");

        if (cita.getHorario() != null) {
            sb.append("Horario: ").append(cita.getHorario().toString()).append("\n");
        } else {
            sb.append("Horario: No asignado\n");
        }

        if (cita.getMedico() != null) {
            sb.append("Médico: ").append(cita.getMedico().getNombre());
            if (cita.getMedico().getEspecialidad() != null) {
                sb.append(" (").append(cita.getMedico().getEspecialidad()).append(")");
            }
            sb.append("\n");
        } else {
            sb.append("Médico: No asignado\n");
        }

        if (cita.getSala() != null) {
            sb.append("Sala: ").append(cita.getSala().getIdSala());
            if (cita.getSala().getTipoSala() != null) {
                sb.append(" (").append(cita.getSala().getTipoSala()).append(")");
            }
            sb.append("\n");
        } else {
            sb.append("Sala: No asignada\n");
        }

        sb.append("\nPor favor, preséntese con 10 minutos de anticipación.\n");
        sb.append("Gracias por confiar en nuestro hospital.\n\n");
        sb.append("Atentamente,\nHospital UQ");
        String mensaje = sb.toString();
        return mensaje;
    }
}
