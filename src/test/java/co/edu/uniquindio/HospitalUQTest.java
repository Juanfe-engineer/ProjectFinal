package co.edu.uniquindio;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
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


    // GESTION DE USUARIOS

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
        Medico medico1 = new Medico("Jacobo", "0001","Jacobo@uq.edu.co", "3023456712",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        // Registrar el medico
        boolean resultado = hospitalUQ.registrarMedico(medico1);

        // Verificar que el medico fue registrado correctamente
        assertTrue(resultado);
        assertEquals(1, hospitalUQ.getMedicos().size());

        // Intentar registrar un medico con el mismo ID (debe fallar)
        Medico medico2 = new Medico("Juanfe", "0001", "Juanfe@uq.edu.co", "3023456562",Especialidad.CARDIOLOGO,EstadoMedico.DISPONIBLE);

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

        Medico medico1 = new Medico("Samuel", "0001","Samuel@uq.edu.co", "3154672134", Especialidad.CIRUJANO, EstadoMedico.DISPONIBLE);
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

        Medico medico1 = new Medico("Raul", "0002", "Raul@uq.edu.co", "31567375621", Especialidad.NEUROLOGO, EstadoMedico.DISPONIBLE);
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
        Medico medicoOriginal = new Medico("Johan", "0003", "Johan@uq.edu.co", "3004567903",Especialidad.PEDIATRIA, EstadoMedico.DISPONIBLE);
        hospitalUQ.registrarMedico(medicoOriginal);

        Medico medicoModificado = new Medico("Johan Stiven", "0003", "Stiven@uq.edu.co", "3004567904", Especialidad.OFTAGMOLOGO, EstadoMedico.DISPONIBLE);
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
        Medico medicoSinId = new Medico("Sin ID", null, "noid@uq.edu.co", "3114330000",Especialidad.CIRUJANO, EstadoMedico.DISPONIBLE);
        Medico resultadoSinId = hospitalUQ.modificarMedico(medicoSinId);
        assertNull(resultadoSinId);

        // Cuando el ID no existe en la lista
        Medico medicoInexistente = new Medico("Falso", "9999", "falso@uq.edu.co", "3000000000", Especialidad.GENERAL, EstadoMedico.EN_CONSULTA);
        Medico resultadoFalso = hospitalUQ.modificarMedico(medicoInexistente);
        assertNull(resultadoFalso);

        log.info("La prueba Termino");
    }

    //----------------------------------------------------------------------------------------------------------------//

    // Pruebas unitarias para asignar medico a paciente y luego liberarlo

    @Test
    public void asignarYLiberarMedicoAPaciente() {
        log.info("La prueba Inicio");

        HospitalUQ hospital = new HospitalUQ("SanJuan", "900-234-462");

        Medico medico = new Medico("Dr. House", "M001", "drhouse@uq.edu.co", "3171234567", Especialidad.CARDIOLOGO, EstadoMedico.DISPONIBLE);
        Paciente paciente = new Paciente("Lucas", "P001", "lucas@uq.edu.co", "3120001111");

        hospital.registrarMedico(medico);
        hospital.registrarPaciente(paciente);

        // Asignar médico
        boolean asignado = hospital.asignarMedicoAPaciente("P001", "M001");
        assertTrue(asignado);
        assertEquals(EstadoMedico.NO_DISPONIBLE, medico.getEstado());
        assertEquals(medico, paciente.getMedicoAsignado());

        // Liberar médico
        boolean liberado = hospital.liberarMedicoDePaciente("P001");
        assertFalse(liberado);
        assertEquals(EstadoMedico.DISPONIBLE, medico.getEstado());
        assertNull(paciente.getMedicoAsignado());

        log.info("La prueba termino");
    }



    //----------------------------------------------------------------------------------------------------------------//

    // pruebas para agregar y cancelar una cita medica

    @Test
    public void agregarCita() {
        log.info("La prueba inicio");

        HospitalUQ hospital = new HospitalUQ("San Juan", "900-123");

        // Crear entidades necesarias
        Paciente paciente = new Paciente("P001", "Ana", "ana@uq.edu.co", "3216549870");
        Medico medico = new Medico("M001", "Dr. Pineda", "Dpineda@uq.edu.co", "3201234567", Especialidad.NEUROLOGO, EstadoMedico.DISPONIBLE);
        Sala sala = new Sala("S001", TipoSala.CONSULTA, EstadoSala.DISPONIBLE, 1);
        Horario horario = new Horario("H001", LocalDate.of(2025, 5, 14), LocalTime.now(), Jornada.TARDE);


        Cita cita = new Cita("diagnostico","tratamiento", "S001",paciente,medico,sala,horario,EstadoCita.PROGRAMADA);

        // Agregar entidades al hospital
        hospital.getPacientes().add(paciente);
        hospital.getMedicos().add(medico);
        hospital.getSalas().add(sala);

        boolean resultado = hospital.crearCita(cita);

        assertTrue(resultado);
        assertEquals(1, hospital.getCitas().size());
        assertEquals(EstadoMedico.NO_DISPONIBLE, medico.getEstado());
        assertEquals(EstadoSala.OCUPADA, sala.getEstado());
        assertEquals(EstadoCita.PROGRAMADA, cita.getEstado());

        log.info("La prueba finalizo");
    }

    //----------------------------------------------------------------------------------------------------------------//

    // pruebas para buscar Sala y Cita por ID


    // Sala

    @Test
    public void testBuscarSalaPorIdExistente() {
        log.info("La prueba inicio");

        // Se crea el hospital y una sala
        HospitalUQ hospital = new HospitalUQ("UQ Salud", "123");
        Sala sala = new Sala("S01", TipoSala.CONSULTA, EstadoSala.DISPONIBLE, 2);
        hospital.getSalas().add(sala); // Se agrega la sala a las salas del hospital

        // Se busca la sala por su ID
        Sala resultado = hospital.buscarSalaPorId("S01");

        // Se comprueba que la sala sea la misma que la buscada
        assertNotNull(resultado);
        assertEquals("S01", resultado.getIdSala()); // Verifica que el ID coincida

        log.info("La prueba finalizo");
    }

    @Test
    public void testBuscarSalaPorIdInexistente() {
        log.info("La prueba inicio");

        // Se crea el hospital (sin agregar salas aún)
        HospitalUQ hospital = new HospitalUQ("UQ Salud", "123");

        // Se busca una sala que no existe
        Sala resultado = hospital.buscarSalaPorId("S02");

        // Se verifica que no se encuentre la sala
        assertNull(resultado); // La sala no debería ser encontrada

        log.info("La prueba finalizo");
    }



    // Cita

    @Test
    public void testBuscarCitaPorIdExistente() {
        log.info("La prueba inicio");

        // Creación de los objetos necesarios para crear una cita
        Paciente paciente = new Paciente("P01", "Juan", "juan@uq.edu.co", "3123456789");
        Medico medico = new Medico("M01", "Dr. Luis", "luis@uq.edu.co", "3219876543", Especialidad.GENERAL, EstadoMedico.DISPONIBLE);
        Sala sala = new Sala("S01", TipoSala.CONSULTA, EstadoSala.DISPONIBLE, 2);
        Horario horario = new Horario("H01", LocalDate.now(), LocalTime.now(), Jornada.MANANA);
        EstadoCita estado = EstadoCita.PROGRAMADA;

        // Crear la cita con los objetos creados
        Cita cita = new Cita("C01","Acetaminofen 8 horas","C01", paciente, medico, null, horario, estado);

        // Agregar la cita al hospital
        HospitalUQ hospital = new HospitalUQ("UQ Salud", "123");
        hospital.getCitas().add(cita);

        // Buscar la cita por su ID
        Cita resultado = hospital.buscarCitaPorID("C01");

        // Verificar que la cita encontrada es la correcta
        assertNotNull(resultado);
        assertEquals("C01", resultado.getIdCita()); // Verifica que el ID coincida

        log.info("La prueba finalizo");
    }


    @Test
    public void testBuscarCitaPorIdInexistente() {
        log.info("La prueba inicio");

        // Se crea el hospital (sin agregar citas aún)
        HospitalUQ hospital = new HospitalUQ("UQ Salud", "123");

        // Se busca una cita que no existe
        Cita resultado = hospital.buscarCitaPorID("C02");

        // Se verifica que no se encuentre la cita
        assertNull(resultado); // La cita no debería ser encontrada

        log.info("La prueba finalizo");
    }


    //----------------------------------------------------------------------------------------------------------------//

    // pruebas para AsignarSalaACita y liberarSaladeCita


    @Test
    public void testAsignarSalaACita() {
        log.info("La prueba inicio");

        HospitalUQ hospital = new HospitalUQ("UQ Salud", "123");

        Sala sala = new Sala("S01", TipoSala.CONSULTA, EstadoSala.DISPONIBLE, 2);
        Paciente paciente = new Paciente("P01", "Ana", "ana@uq.edu.co", "3123456789");
        Medico medico = new Medico("M01", "Dr. Luis", "luis@uq.edu.co", "3219876543", Especialidad.GENERAL, EstadoMedico.DISPONIBLE);
        Horario horario = new Horario("H01", LocalDate.now(), LocalTime.now(), Jornada.MANANA);

        Cita cita = new Cita("C01","Acetaminofen 8 horas","C01", paciente, medico,null, horario, EstadoCita.PROGRAMADA);

        hospital.getCitas().add(cita);
        hospital.getSalas().add(sala);

        boolean resultado = hospital.asignarSalaACita("S01", "C01");

        assertTrue(resultado);
        assertEquals(EstadoSala.OCUPADA, sala.getEstado());

        log.info("La prueba finalizo");
    }




    @Test
    public void testLiberarSalaDeCita() {
        log.info("La prueba inicio");

        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Sala sala = new Sala("S01", TipoSala.CONSULTA, EstadoSala.OCUPADA, 2);
        Paciente paciente = new Paciente("P01", "Ana", "ana@uq.edu.co", "3001234567");
        Medico medico = new Medico("M01", "Dr. Juan", "juan@uq.edu.co", "3012345678", Especialidad.GENERAL, EstadoMedico.DISPONIBLE);
        Horario horario = new Horario("H01", LocalDate.now(), LocalTime.now(), Jornada.MANANA);

        Cita cita = new Cita("C01","Acetaminofen 8 horas","C01", paciente, medico, sala, horario, EstadoCita.PROGRAMADA);

        hospital.getSalas().add(sala);
        hospital.getCitas().add(cita);

        boolean resultado = hospital.liberarSalaDeCita("C01");

        assertTrue(resultado);
        assertEquals(EstadoSala.DISPONIBLE, sala.getEstado());
        assertNull(cita.getSala());

        log.info("La prueba finalizo");
    }


    //----------------------------------------------------------------------------------------------------------------//


    // Gestion de Salas

    // registrar sala

    @Test
    public void registrarSala () {
        log.info("La prueba inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Sala sala = new Sala("S001",TipoSala.CONSULTA,EstadoSala.DISPONIBLE, 4);
        assertTrue(hospitalUQ.registrarSala(sala));
        assertEquals(sala, hospitalUQ.buscarSalaPorId("S001"));
        assertFalse(hospitalUQ.registrarSala(null));

        Sala sala1 = new Sala("S002", TipoSala.URGENCIAS, EstadoSala.DISPONIBLE, 4);
        Sala sala2 = new Sala("S002", TipoSala.URGENCIAS, EstadoSala.DISPONIBLE, 4);

        hospitalUQ.registrarSala(sala1);
        assertFalse(hospitalUQ.registrarSala(sala2));

        log.info("La prueba finalizo");

    }

    // buscar sala por id

    @Test
    public void buscarSalaPorId() {
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Sala sala = new Sala("S003",TipoSala.PEDIATRIA,EstadoSala.DISPONIBLE, 4);
        hospitalUQ.registrarSala(sala);
        Sala resultado = hospitalUQ.buscarSalaPorId("S003");

        assertNotNull(resultado);
        assertEquals("S003", resultado.getIdSala());
        assertNull(hospitalUQ.buscarSalaPorId("S000"));
        assertNull(hospitalUQ.buscarSalaPorId(null));

        log.info("La prueba Finalizo");
    }


    // modificar Sala

    @Test
    public void modificarSala(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");

        Sala salaOriginal = new Sala("S004", TipoSala.CONSULTA, EstadoSala.OCUPADA, 4);
        Sala salaModificada = new Sala("S004", TipoSala.URGENCIAS, EstadoSala.DISPONIBLE, 6);

        hospitalUQ.registrarSala(salaOriginal);
        Sala resultado = hospitalUQ.modificarSala(salaModificada);

        assertNotNull(resultado);
        assertEquals(TipoSala.URGENCIAS, resultado.getTipoSala(TipoSala.URGENCIAS));

        Sala sala = new Sala("S005",TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 4);
        assertNull(hospitalUQ.modificarSala(sala));

        assertNull(hospitalUQ.modificarSala(null));

        log.info("La prueba finalizo");

    }


    // Eliminar Sala

    @Test
    public void eliminarSala(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");

        Sala sala = new Sala("S006",TipoSala.HOSPITALIZACION,EstadoSala.OCUPADA,3);
        hospitalUQ.registrarSala(sala);
        assertTrue(hospitalUQ.eliminarSala("S006"));
        assertNull(hospitalUQ.buscarSalaPorId("S006"));

        assertFalse(hospitalUQ.eliminarSala("S007"));

        assertFalse(hospitalUQ.eliminarSala(null));

        log.info("La prueba finalizo");
    }




    // Gestion de horarios

    @Test
    public void registrarHorario(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");

        Horario horario = new Horario("H001",LocalDate.of(2025,5,13), LocalTime.now(),Jornada.TARDE);
        boolean resultado = hospitalUQ.registrarHorario(horario);
        assertTrue(resultado);
        assertEquals(horario,hospitalUQ.buscarHorarioPorId("H001"));

        boolean resultado1 = hospitalUQ.registrarHorario(null);
        assertFalse(resultado1);

        Horario h1 = new Horario("H002",LocalDate.of(2025,4,14),LocalTime.now(),Jornada.MANANA);
        Horario h2 = new Horario("H002",LocalDate.of(2025,4,14),LocalTime.now(),Jornada.MANANA);

        hospitalUQ.registrarHorario(h1);
        boolean resultado2 = hospitalUQ.registrarHorario(h2);

        assertFalse(resultado2);

        log.info("La pruea finalizo");

    }

    //buscar horario por id


    @Test
    public void buscarHorarioPorId(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");

        Horario horario = new Horario("H003",LocalDate.of(2025,4,15),LocalTime.now(),Jornada.TARDE);
        hospitalUQ.registrarHorario(horario);

        Horario resultado = hospitalUQ.buscarHorarioPorId("H003");
        assertNotNull(resultado);
        assertEquals("H003", resultado.getIdHorario());

        Horario resultado1 = hospitalUQ.buscarHorarioPorId("H999");
        assertNull(resultado1);

        Horario resultado2 = hospitalUQ.buscarHorarioPorId(null);
        assertNull(resultado2);

        log.info("La prueba Finalizo");
    }


    // modificar Horario

    @Test
    public void modificarHorario(){
        log.info("La prueba Inicio");

        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Horario original = new Horario("H004",LocalDate.of(2025,2,23),LocalTime.now(),Jornada.TARDE);
        hospital.registrarHorario(original);

        Horario modificado = new Horario("H004",LocalDate.of(2025,2,24), LocalTime.now(),Jornada.MANANA);
        Horario resultado = hospital.modificarHorario(modificado);

        assertNotNull(resultado);
        assertEquals(LocalDate.of(2025,2,24),resultado.getFecha());

        Horario resultado1 = hospital.modificarHorario(null);
        assertNull(resultado1);

        Horario modificado2 = new Horario(null, LocalDate.now(),LocalTime.now(),Jornada.TARDE);
        Horario resultado2 = hospital.modificarHorario(modificado2);

        assertNull(resultado2);

        log.info("La prueba Finalizo");
    }



    @Test
    public void eliminarHorario(){
        log.info("La prueba inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);
        hospitalUQ.registrarHorario(horario);
        
        boolean resultado = hospitalUQ.eliminarHorario("H005");
        
        assertTrue(resultado);
        assertNull(hospitalUQ.buscarHorarioPorId("H005"));
        
        boolean resultado1 = hospitalUQ.eliminarHorario("H098");
        assertFalse(resultado1);

        boolean resultado2 = hospitalUQ.eliminarHorario(null);
        assertFalse(resultado2);

        log.info("La prueba Finalizo");
    }






    // ---

    @Test
    public void RegistrarCita(){
        log.info("La prueba inicio");

        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");


        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);

        hospital.crearCita(cita1);

        EstadoMedico estadoesperado = EstadoMedico.NO_DISPONIBLE;


        assertEquals(estadoesperado,cita1.getMedico().getEstado());

        log.info("La prueba Finalizo");

    }
    @Test
    public void Buscarcita(){
        log.info("La prueba inicio");
        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");


        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);

        Cita cita2 = hospital.buscarCitaPorID("10");

        assertEquals(cita1,cita2);
        log.info("La prueba finalizo");

    }


    @Test
    public void ActualizarCita(){
        log.info("La prueba inicio");
        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);
        Sala sala2 = new Sala("S006",TipoSala.HOSPITALIZACION,EstadoSala.DISPONIBLE,3);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");

        Horario horario2 = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);
        Medico medico2 = new Medico("M01", "Dr. Juan", "juan@uq.edu.co", "3012345678", Especialidad.GENERAL, EstadoMedico.DISPONIBLE);


        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);


        Cita cita2 = hospital.actualizarCita("10","12", horario2, medico2, sala2);

        assertNotEquals(cita1,cita2);

        log.info("La prueba finalizo");


    }
    @Test
    public void EliminarCita(){
        log.info("La prueba inicio");
        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");


        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);


        boolean esperado = hospital.eliminarCita("10");

        assertEquals(true,esperado);
        log.info("La prueba finalizo");



    }
    //AsignarHorarioAcita

    @Test
    public void AsignarHorariocita(){
        log.info("La prueba inicio");
        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);
        Horario horario2 = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");


        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);


        boolean esperado = hospital.asignarHorarioACita("10", horario2);

        assertEquals(true,esperado);
        log.info("La prueba finalizo");




    }
    @Test
    public void Liberarhorario(){
        log.info("La prueba inicio");
        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");


        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);

        hospital.liberarHorarioDeCita("10");


        assertNull(cita1.getHorario());
        log.info("La prueba finalizo");


    }
    @Test
    public void solicitarcita(){
        log.info("La prueba inicio");
        HospitalUQ hospital = new HospitalUQ("Hospital UQ", "123");

        Medico medico1 = new Medico ("Oliver","10","Oliver@hospital.com", "3255059",Especialidad.GENERAL,EstadoMedico.DISPONIBLE);

        Sala sala1 = new Sala("21", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE, 10);

        Horario horario = new Horario("H005",LocalDate.of(2025,3,03),LocalTime.now(),Jornada.NOCHE);

        Paciente paciente = new Paciente("P002", "Luis", "luis@uq.edu.co", "3123456789");
        paciente.setHospital(hospital);

        Cita cita1 = new Cita("10","Acetaminofen 8 horas","10", paciente, medico1,sala1, horario, EstadoCita.PROGRAMADA);

        paciente.solicitarCita(Especialidad.GENERAL);







    }

















    





}