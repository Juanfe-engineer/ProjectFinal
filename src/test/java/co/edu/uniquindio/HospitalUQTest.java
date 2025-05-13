package co.edu.uniquindio;


import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Clase para probar las funcionalidades de HospitalUQ
 *
 * @authors JuanFelipeIbarra - JohanStivenPineda - SamuelArturoRodriguez - JacoboArboledaCarvajal del area de programacion Uq
 *
 */


public class HospitalUQTest {
    private static final Logger log =
            Logger.getLogger(HospitalUQTest.class.getName());

    @Test
    public void testUnoRegistrarPaciente(){
        log.info("La prueba testUnoRegistrarPaciente inicio");

        HospitalUQ newHospital = new HospitalUQ("Hospitaluq", "900-345-655");

        



        log.info("La prueba testUnoRegistrarPaciente termino");
    }

}