package co.edu.uniquindio.persistencia;

import co.edu.uniquindio.model.DatosHospital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaHospital {
    private static final String  RUTA_ARCHIVO    = "hospital.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarDatos(DatosHospital datos) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)){
            gson.toJson(datos, writer);
            System.out.println("✅ Datos guardados en hospital.json");
        } catch (IOException e){
            System.err.println("❌ Error al guardar datos: " + e.getMessage());
        }
    }

    public static DatosHospital cargarDatos(){
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)){
            DatosHospital datos = gson.fromJson(reader, DatosHospital.class);
            System.out.println("✅ Datos cargados desde hospital.json");
            return datos;
        } catch (IOException e){
            System.out.println("⚠️ Archivo hospital.json no encontrado, iniciando con datos vacíos");
            return new DatosHospital();
        }
    }

}
