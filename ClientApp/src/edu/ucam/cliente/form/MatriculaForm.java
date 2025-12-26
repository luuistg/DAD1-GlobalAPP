package edu.ucam.cliente.form;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import edu.ucam.cliente.factory.ServiceFactory;
import edu.ucam.cliente.service.AsignaturaService;
import edu.ucam.domain.Alumno;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Asignatura;

public class MatriculaForm extends GenericForm{

	@Override
	public Matricula addForm(Scanner sc){
		
		System.out.println("\n--- REGISTRO DE NUEVA MATRICULA ---");

        String id = pedirTextoNoVacio(sc, "Introduce el ID: ");

        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre del alumno: ");
        String apellidos = pedirTextoNoVacio(sc, "Introduce los apellidos del alumno: ");
        String dni = pedirTextoNoVacio(sc, "Introduce el dni del alumno: ");
        
        Alumno a = new Alumno(dni, apellidos, nombre);
        
        String idAsig = "";
    	AsignaturaService service = (AsignaturaService) ServiceFactory.getInstance().getService(3);

        try {
        	List<Asignatura> serverList = ((AsignaturaService)service).list();
            
            Hashtable<String, Asignatura> local = new Hashtable<>();
            if (serverList != null) {
                for (Asignatura asig : serverList) {
                	local.put(asig.getId(), asig);
                }
            }
            
            Hashtable<String, Asignatura> asignaturas = new Hashtable<>();
            
            System.out.println("\n--- SELECCIÓN DE ASIGNATURAS ---");
            
            System.out.println("Introduce 'OK' para terminar el proceso de selección.");
            
        	while (true) {
                System.out.print("ID Asignatura > ");
                idAsig = sc.nextLine().trim();

                if ("OK".equalsIgnoreCase(idAsig)) {
                    break;
                }
                
                if (idAsig.isEmpty()) continue;

                if (local.containsKey(idAsig)) {
                	
                	if (asignaturas.containsKey(idAsig)) {
                        System.out.println("AVISO: Esa asignatura ya está añadida en esta matrícula.");
                    }else {
                        asignaturas.put(idAsig, local.get(idAsig));
                        System.out.println("Asignatura añadida a la matrícula.");
                    }
                } else {
                    System.out.println("ID desconocido. Revisa la lista de arriba.");
                }
        	}
        	
            return new Matricula(id, a, asignaturas);
             
        } catch (Exception e) {
            System.out.println("Error de comunicación al buscar asignatura.");
            return null;
        }
    }

	@Override
	public String getForm(Scanner sc) {
		String id = pedirTextoNoVacio(sc, "Introduce el ID: ");
		return id;
	}

	@Override
	public Object updateForm(String id, Scanner sc) {
		
        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre del alumno: ");
        String apellidos = pedirTextoNoVacio(sc, "Introduce los apellidos del alumno: ");
        String dni = pedirTextoNoVacio(sc, "Introduce el dni del alumno: ");
        
        Alumno a = new Alumno(apellidos, dni, nombre);
        
		return new Matricula(id, a, null);

	}

}
