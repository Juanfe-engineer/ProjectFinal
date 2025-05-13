package co.edu.uniquindio;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;



public class HospitalUQTest {
    private static final Logger log =
            Logger.getLogger(HospitalUQTest.class.getName());
    @Test
    public void registrarPaciente() {
        log.info("La prueba Inicio");

        // Crear un hospital
        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        // Crear un paciente
        Paciente paciente1 = new Paciente("1234","Juan","JuanfI@uq.edu.co","3103612453");

        //registrar ese paciente al hospital
        hospitalUQ.registrarPaciente(paciente1);

        // Verificar que se haya guardado (la lista debe tener 1)
        assertEquals(1, hospitalUQ.getPacientes().size());

        log.info("La prueba termino");

    }



    @Test
    public void buscarPacienteId() {
        log.info("La prueba Inicio");

        // Crear un hospital
        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        //Crear un paciente
        Paciente paciente1 = new Paciente("0001","Johan","Johansp@uq.edu.co","3156734821");

        // registrar ese paciente al hospital
        hospitalUQ.buscarPacienteID("0001");

        // verificamos que el Id sea el indicado
        assertEquals("Johan", paciente1.getId());

        log.info("La prueba termino");
    }




  
}