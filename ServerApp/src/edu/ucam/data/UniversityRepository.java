package edu.ucam.data;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
    
    
    //USUARIO  
    
    //Usuario Existe
    public boolean existUser(String user){
    	if (usuarios.containsKey(user)) {
            return true;
        }
        return false;
    }
    
    //Validar Usuario
    public boolean validarUsuario(String user, String pass) {
        if (usuarios.containsKey(user)) {
            return usuarios.get(user).equals(pass);
        }
        return false;
    }
    
    //Titulaciones     
    //ADDTIT
    public synchronized void addTitulacion(Titulacion t) throws Exception {
        // Asumo que t.getId() existe. Si el ID ya existe, lanzamos error o sobrescribimos según lógica.
        if (titulaciones.containsKey(t.getId())) {
            throw new Exception("La titulación ya existe.");
        }
        titulaciones.put(t.getId(), t);
    }
    
    // REMOVETIT
    public synchronized boolean removeTitulacion(String id) {
        if (titulaciones.containsKey(id)) {
            titulaciones.remove(id);
            return true;
        }
        return false;
    }
    
    //LISTTIT
    public List<Titulacion> listarTitulaciones() {
        return new ArrayList<>(titulaciones.values());
    }

    //COUNTTIT
    public int contarTitulaciones() {
        return titulaciones.size();
    }
    
    //Asignaturas
    //ADDASIG
    public synchronized void addAsignatura(Asignatura a) throws Exception {
        if (asignaturas.containsKey(a.getId())) {
            throw new Exception("La asignatura ya existe.");
        }
        asignaturas.put(a.getId(), a);
    }
    
    //GETASIG
    public Asignatura getAsignatura(String id) {
        return asignaturas.get(id);
    }

    //REMOVEASIG
    public synchronized boolean removeAsignatura(String id) {
        if (asignaturas.containsKey(id)) {
            asignaturas.remove(id);
            return true;
        }
        return false;
    }

    //LISTASIG
    public List<Asignatura> listarAsignaturas() {
        return new ArrayList<>(asignaturas.values());
    }
    
    //Relacion: ASIGNATURA <--> TITULACION
    //ADDASIG2TIT
    public synchronized void addAsignaturaATitulacion(String idAsig, String idTit) throws Exception {
        Titulacion tit = titulaciones.get(idTit);
        Asignatura asig = asignaturas.get(idAsig); 

        if (tit == null) throw new Exception("Titulación no encontrada.");
        if (asig == null) throw new Exception("Asignatura no encontrada.");

        tit.addAsignatura(asig);
    }

    //REMOVEASIGFROMTIT
    public synchronized void removeAsignaturaDeTitulacion(String idAsig, String idTit) throws Exception {
        Titulacion tit = titulaciones.get(idTit);
        if (tit == null) throw new Exception("Titulación no encontrada.");
        
        Asignatura asigEnTitulacion = tit.getAsignatura(idAsig);
        
        if (asigEnTitulacion == null) throw new Exception("La asignatura no está en esa titulación.");

        tit.removeAsignatura(asigEnTitulacion);
    }
    
    //LISTASIGFROMTIT
    /*public List<Asignatura> listarAsignaturasDeTitulacion(String idTit) throws Exception {
        Titulacion tit = titulaciones.get(idTit);
        if (tit == null) throw new Exception("Titulación no encontrada.");
        return tit.;
    }*/
    
    //Matricula
    //ADDMATRICULA
    public synchronized void addMatricula(Matricula m) throws Exception {
        // Confirmado: Matricula tiene getId()
        if (matriculas.containsKey(m.getId())) throw new Exception("La matrícula ya existe.");
        
        // Opcional: Validar que el alumno exista antes de añadir
        // Alumno a = m.getAlumno();
        // if (a != null && !alumnos.containsKey(a.getDni())) ...
        
        matriculas.put(m.getId(), m);
    }
    
    //GETMATRICULA
    public Matricula getMatricula(String id) {
        return matriculas.get(id);
    }
    
    //UPDATEMATRICULA
    public synchronized void updateMatricula(Matricula m) throws Exception {
         if (!matriculas.containsKey(m.getId())) throw new Exception("La matrícula no existe.");
         matriculas.put(m.getId(), m);
    }

    //REMOVEMATRICULA
    public synchronized boolean removeMatricula(String id) {
        if (matriculas.containsKey(id)) {
            matriculas.remove(id);
            return true;
        }
        return false;
    }
    

}
