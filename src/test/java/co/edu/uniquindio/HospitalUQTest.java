package co.edu.uniquindio;

import org.junit.jupiter.api.BeforeEach;
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
        Paciente paciente1 = new Paciente("Juan", "1234", "JuanfI@uq.edu.co", "3103612453");

        // Registrar el paciente
        boolean resultado = hospitalUQ.registrarPaciente(paciente1);

        // Verificar que el paciente fue registrado correctamente
        assertTrue(resultado);
        assertEquals(1, hospitalUQ.getPacientes().size());

        // Intentar registrar un paciente con el mismo ID (debe fallar)
        Paciente paciente2 = new Paciente("Juan", "1234", "Carlos@uq.edu.co", "3103612454");

        // El resultado debe ser falso, porque el ID ya existe
        resultado = hospitalUQ.registrarPaciente(paciente2);
        assertFalse(resultado);
        assertEquals(1, hospitalUQ.getPacientes().size()); // No debe agregar al segundo paciente

        // Intentar registrar un paciente nulo (debe fallar)
        Paciente pacienteNulo = null;
        resultado = hospitalUQ.registrarPaciente(pacienteNulo);
        assertFalse(resultado);
        assertEquals(1, hospitalUQ.getPacientes().size()); // No debe agregar un paciente nulo

        log.info("La prueba termino");

    }




        @Test
    public void buscarPacienteId() {
            log.info("La prueba Inicio");

            HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

            Paciente paciente1 = new Paciente("Johan", "0001", "Johansp@uq.edu.co", "3156734821");
            hospitalUQ.registrarPaciente(paciente1);

            Paciente resultado = hospitalUQ.buscarPacienteID("0001");
            assertNotNull(resultado);
            assertEquals("Johan", resultado.getNombre());

            // Caso: buscar con ID que no existe
            assertNull(hospitalUQ.buscarPacienteID("9999"));

            // Caso: buscar con ID nulo
            assertNull(hospitalUQ.buscarPacienteID(null));

            log.info("La prueba termino");
    }


    @Test
    public void eliminarPaciente() {
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        Paciente paciente1 = new Paciente("Raul", "0002", "Raul@uq.edu.co", "31567375621");
        hospitalUQ.registrarPaciente(paciente1);

        boolean eliminado = hospitalUQ.eliminarPaciente("0002");
        assertTrue(eliminado);

        assertNull(hospitalUQ.buscarPacienteID("0002"));
        assertEquals(0, hospitalUQ.getPacientes().size());

        // Caso: eliminar con ID null
        boolean resultadoNulo = hospitalUQ.eliminarPaciente(null);
        assertFalse(resultadoNulo);

        log.info("La prueba Termino");
    }

    @Test
    public void modificarPaciente() {
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        // modificar paciente existente
        Paciente pacienteOriginal = new Paciente("Oliver", "0003", "oliver@uq.edu.co", "3114337562");
        hospitalUQ.registrarPaciente(pacienteOriginal);

        Paciente pacienteModificado = new Paciente("Oliver Atom", "0003", "atom@uq.edu.co", "3114337569");
        Paciente resultado = hospitalUQ.modificarPaciente(pacienteModificado);

        assertNotNull(resultado);
        assertEquals("Oliver Atom", resultado.getNombre());
        assertEquals("atom@uq.edu.co", resultado.getCorreo());
        assertEquals("3114337569", resultado.getTelefono());

        // Validar que se haya reemplazado en la lista
        Paciente enLista = hospitalUQ.getPacientes().get(0);
        assertEquals("Oliver Atom", enLista.getNombre());

        // Cuando el paciente a modificar es null
        Paciente resultadoNulo = hospitalUQ.modificarPaciente(null);
        assertNull(resultadoNulo);

        // Cuando el ID del paciente es null
        Paciente pacienteSinId = new Paciente("Sin ID", null, "noid@uq.edu.co", "3114330000");
        Paciente resultadoSinId = hospitalUQ.modificarPaciente(pacienteSinId);
        assertNull(resultadoSinId);

        // Cuando el ID no existe en la lista
        Paciente pacienteInexistente = new Paciente("Falso", "9999", "falso@uq.edu.co", "3000000000");
        Paciente resultadoFalso = hospitalUQ.modificarPaciente(pacienteInexistente);
        assertNull(resultadoFalso);

        log.info("La prueba Termino");
    }




  
}