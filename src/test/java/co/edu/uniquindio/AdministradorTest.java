package co.edu.uniquindio;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Metodo para probar las funcionalidades de administrador
 *
 *
 */

class AdministradorTest {
    private static Logger log =
            Logger.getLogger(AdministradorTest.class.getName());

    //Pruebas unitarias de Administrador

    @Test
    public void testRegistrarNuevoPaciente(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Paciente p = new Paciente("Juan","1098546074","Juanfe@Uq.virtual.edu.co","3103686123");
        boolean resultado = administrador.registrarPaciente(p);

        assertTrue(resultado);

        log.info("La prueba finalizo");
    }


    @Test
    public void ModificarPaciente(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);




        log.info("La prueba Finalizo");
    }




}