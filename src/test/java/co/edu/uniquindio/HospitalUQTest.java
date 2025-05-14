package co.edu.uniquindio;


import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Clase que prueba las funcionalides de HospitalUQ
 *
 * @Authors Juan Felipe Ibarra - Johan Stiven Pineda - Samuel Arturo Rodriguez - Jacobo Arboleda Carvajal
 *
 */

public class HospitalUQTest {
    private static final Logger log =
            Logger.getLogger(HospitalUQTest.class.getName());


    // PRUEBAS UNITARIAS CRUD PACIENTE

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

            // buscar con ID que no existe
            assertNull(hospitalUQ.buscarPacienteID("9999"));

            // buscar con ID nulo
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

        // eliminar con ID null
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



    // PRUEBAS UNITARIAS CRUD MEDICO


    @Test
    public void registrarMedico() {
        log.info("La prueba Inicio");

        // Crear un hospital
        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        // Crear un medico
        Medico medico1 = new Medico("Jacobo", "0001","Jacobo@uq.edu.co", "3023456712");

        // Registrar el medico
        boolean resultado = hospitalUQ.registrarMedico(medico1);

        // Verificar que el medico fue registrado correctamente
        assertTrue(resultado);
        assertEquals(1, hospitalUQ.getMedicos().size());

        // Intentar registrar un medico con el mismo ID (debe fallar)
        Medico medico2 = new Medico("Juanfe", "0001", "Juanfe@uq.edu.co", "3023456562");

        // El resultado debe ser falso, porque el ID ya existe
        resultado = hospitalUQ.registrarMedico(medico2);
        assertFalse(resultado);
        assertEquals(1, hospitalUQ.getMedicos().size()); // No debe agregar al segundo paciente

        // Intentar registrar un medico nulo (debe fallar)
        Medico MedicoNulo = null;
        resultado = hospitalUQ.registrarMedico(MedicoNulo);
        assertFalse(resultado);
        assertEquals(1, hospitalUQ.getMedicos().size()); // No debe agregar un paciente nulo

        log.info("La prueba termino");

    }



    @Test
    public void buscarMedicoId() {
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        Medico medico1 = new Medico("Samuel", "0001","Samuel@uq.edu.co", "3154672134");
        hospitalUQ.registrarMedico(medico1);

        Medico resultado = hospitalUQ.buscarMedicoID("0001");
        assertNotNull(resultado);
        assertEquals("Samuel", resultado.getNombre());

        // buscar con ID que no existe
        assertNull(hospitalUQ.buscarMedicoID("9999"));

        // buscar con ID nulo
        assertNull(hospitalUQ.buscarMedicoID(null));

        log.info("La prueba termino");
    }



    @Test
    public void eliminarMedico() {
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        Medico medico1 = new Medico("Raul", "0002", "Raul@uq.edu.co", "31567375621");
        hospitalUQ.registrarMedico(medico1);

        boolean eliminado = hospitalUQ.eliminarMedico("0002");
        assertTrue(eliminado);

        assertNull(hospitalUQ.buscarMedicoID("0002"));
        assertEquals(0, hospitalUQ.getMedicos().size());

        // eliminar con ID null
        boolean resultadoNulo = hospitalUQ.eliminarMedico(null);
        assertFalse(resultadoNulo);

        log.info("La prueba Termino");
    }



    @Test
    public void modificarMedico() {
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("SanJuan", "900-234-462");

        // modificar medico existente
        Medico medicoOriginal = new Medico("Johan", "0003", "Johan@uq.edu.co", "3004567903");
        hospitalUQ.registrarMedico(medicoOriginal);

        Medico medicoModificado = new Medico("Johan Stiven", "0003", "Stiven@uq.edu.co", "3004567904");
        Medico resultado = hospitalUQ.modificarMedico(medicoModificado);

        assertNotNull(resultado);
        assertEquals("Johan Stiven", resultado.getNombre());
        assertEquals("Stiven@uq.edu.co", resultado.getCorreo());
        assertEquals("3004567904", resultado.getTelefono());

        // Validar que se haya reemplazado en la lista
        Medico enLista = hospitalUQ.getMedicos().get(0);
        assertEquals("Johan Stiven", enLista.getNombre());

        // Cuando el medico a modificar es null
        Medico resultadoNulo = hospitalUQ.modificarMedico(null);
        assertNull(resultadoNulo);

        // Cuando el ID del medico es null
        Medico medicoSinId = new Medico("Sin ID", null, "noid@uq.edu.co", "3114330000");
        Medico resultadoSinId = hospitalUQ.modificarMedico(medicoSinId);
        assertNull(resultadoSinId);

        // Cuando el ID no existe en la lista
        Medico medicoInexistente = new Medico("Falso", "9999", "falso@uq.edu.co", "3000000000");
        Medico resultadoFalso = hospitalUQ.modificarMedico(medicoInexistente);
        assertNull(resultadoFalso);

        log.info("La prueba Termino");
    }














}