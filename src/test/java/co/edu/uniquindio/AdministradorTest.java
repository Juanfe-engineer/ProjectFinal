package co.edu.uniquindio;

import co.edu.uniquindio.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public void RegistrarPaciente(){
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

        Paciente paciente = new Paciente("Juan", "1098546074", "Juanfe@Uq.virtual.edu.co", "3103686123");
        administrador.modificarPaciente(paciente);
        Paciente modificado = new Paciente("Juan Felipe", "1098546074", "JuanfeI@Uq.virtual.edu.co", "3103686723");
        Paciente resultado = administrador.modificarPaciente(modificado);
        assertEquals("Juan Felipe", resultado.getNombre());

        log.info("La prueba Finalizo");
    }

    
    @Test
    public void eliminarPaciente(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Paciente paciente = new Paciente("Johan","1092654873", "Johans@UqVirtual.edu.co", "3103685673");
        administrador.registrarPaciente(paciente);
        boolean resultado = administrador.eliminarPaciente("1092654873");
        assertTrue(resultado);
        assertEquals(0,hospitalUQ.getPacientes().size());

        log.info("La prueba Finalizo");
    }


    @Test
    public void registrarMedico(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Medico medico = new Medico("Dr. Jacobo","M001","Jacobo01@Uq.edu.co","606783214", Especialidad.CARDIOLOGO,EstadoMedico.DISPONIBLE);
        boolean resultado = administrador.registrarMedico(medico);
        assertTrue(resultado);

        log.info("La prueba Finalizo");

    }


    @Test
    public void modificarMedico(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Medico medico = new Medico("Dr. Jacobo", "M002","Jacobo01@uq.edu.co","606754821",Especialidad.CIRUJANO,EstadoMedico.EN_CONSULTA);
        administrador.registrarMedico(medico);

        Medico modificado = new Medico("Dr. Jacobinho","M002","Jacobinho@uq.edu.co","6067548912",Especialidad.OFTAGMOLOGO,EstadoMedico.DISPONIBLE);
        Medico resultado = administrador.modificarMedico(modificado);

        assertEquals("Dr. Jacobinho", resultado.getNombre());

        log.info("La prueba Finalizo");
    }


    @Test
    public void eliminarMedico(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Medico medico = new Medico("Dr. Jacobo","M001","Jacoco@uq.virtual.edu.co", "6086748374", Especialidad.PEDIATRIA,EstadoMedico.DISPONIBLE);
        administrador.registrarMedico(medico);
        boolean resultado = administrador.eliminarMedico("M001");
        assertTrue(resultado);

        log.info("La prueba Finalizo");
    }


    @Test
    public void registrarSala(){
        log.info("La prueba inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Sala sala = new Sala("S001", TipoSala.CONSULTA,EstadoSala.DISPONIBLE,5);
        administrador.registrarSala(sala);
        assertEquals(1,hospitalUQ.getSalas().size());

        log.info("La prueba finalizo");
    }


    @Test
    public void eliminarSala(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Sala sala = new Sala("S001",TipoSala.URGENCIAS,EstadoSala.DISPONIBLE,5);
        administrador.registrarSala(sala);
        boolean resultado = administrador.eliminarSala("S001");
        assertTrue(resultado);
        
        log.info("La prueba Finalizo");
    }
    
    
    @Test
    public void registrarHorario(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);
        
        Horario horario = new Horario("H001", LocalDate.of(2025,5,21), LocalTime.now(),Jornada.NOCHE);
        boolean resultado = administrador.registrarHorario(horario);
        assertTrue(resultado);

        log.info("La prueba Finalizo");
    }


    @Test
    public void eliminarHorario(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Horario horario = new Horario("H001",LocalDate.of(2025,2,27),LocalTime.now(),Jornada.TARDE);
        administrador.registrarHorario(horario);
        boolean resultado = administrador.eliminarHorario("H001");
        assertTrue(resultado);

        log.info("La prueba Finalizo");
    }


    @Test
    public void asignarMedicoAPaciente(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);
        
        Paciente paciente = new Paciente("Juan","P001","Juanfe@uqvirtual.edu.co","3116573744");
        Medico medico = new Medico("Dr. Juan","M001","JuanD@uqvirtual.edu.co","6067847351",Especialidad.CIRUJANO,EstadoMedico.DISPONIBLE);
        
        administrador.registrarMedico(medico);
        administrador.registrarPaciente(paciente);
        
        boolean resultado = administrador.asignarMedicoAPaciente("P001","M001");
        assertTrue(resultado);
        assertEquals("M001",hospitalUQ.buscarPacienteID("P001").getMedicoAsignado().getId());
        
        log.info("La prueba Finalizo");

    }
    
    
    @Test
    public void asignarSalaACita(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Sala sala = new Sala("S001",TipoSala.CONSULTA,EstadoSala.DISPONIBLE,4);
        Cita cita = new Cita("Diagnostico", "Tratamiento","C001",null,null,sala,null,EstadoCita.PROGRAMADA);
        hospitalUQ.registrarSala(sala);
        hospitalUQ.registrarCita(cita);
        
        boolean resultado = administrador.asignarSalaACita("C001","S001");
        assertTrue(resultado);
        assertEquals("S001", hospitalUQ.buscarCitaPorID("C001").getSala().getIdSala());

        log.info("La prueba Finalizo");
    }


    @Test
    public void asignarHorarioACita(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Horario horario = new Horario("H001",LocalDate.of(2025,1,31),LocalTime.now(),Jornada.TARDE);
        Cita cita = new Cita("Diagnostico","Tratamiento","C001",null,null,null,null,EstadoCita.PROGRAMADA);
        hospitalUQ.registrarCita(cita);
        boolean resultado = administrador.asignarHorarioACita("C001",horario);

        assertTrue(resultado);
        assertEquals("H001",hospitalUQ.buscarCitaPorID("C001").getHorario().getIdHorario());

        log.info("La prueba Finalizo");
    }


    @Test
    public void VerReporte(){
        log.info("La prueba Inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        Cita cita = new Cita("Diagnostico","Tratamiento","C001",null,null,null,null,EstadoCita.PROGRAMADA);
        hospitalUQ.registrarCita(cita);
        String reporte = administrador.verReporte("C001");
        assertTrue(reporte.contains("C001"));

        log.info("La prueba finalizo");
    }


    @Test
    public void reporteOcupacionSalas(){
        log.info("La prueba inicio");

        HospitalUQ hospitalUQ = new HospitalUQ("Hospital UQ", "123");
        Administrador administrador = new Administrador("Admin","1234","Admin@UqVirtual.edu.co","0180009654",hospitalUQ);

        String reporte = administrador.generarReporteOcupacionSalas();
        assertNotNull(reporte);

        log.info("La prueba finalizo");
    }






}