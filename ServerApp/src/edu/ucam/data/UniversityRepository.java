package edu.ucam.data;

import java.util.Hashtable;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public class UniversityRepository {
	
	/*
	 * Listas para cada Entidad
	 * Utilizando un patrón SINGLETON
	 * Metodo Synchronized para evitar concurrencia
	 */
	
	private static UniversityRepository instance;

	
    public Hashtable<String, Titulacion> titulaciones;
    public Hashtable<String, Asignatura> asignaturas;
    //private Hashtable<String, Alumno> alumnos; <-- Entrega Junio
    public Hashtable<String, Matricula> matriculas;
    
    public Hashtable<String, String> usuarios;
    
    private UniversityRepository() {
        // Inicializamos las tablas
        this.titulaciones = new Hashtable<>();
        this.asignaturas = new Hashtable<>();
        //this.alumnos = new Hashtable<>();
        this.matriculas = new Hashtable<>();
        this.usuarios = new Hashtable<>();

        cargarDatosPrueba();
    }

    public static synchronized UniversityRepository getInstance() {
        if (instance == null) {
            instance = new UniversityRepository();
        }
        return instance;
    }
    
    private void cargarDatosPrueba() {
        usuarios.put("admin", "admin");
    }
    
}
