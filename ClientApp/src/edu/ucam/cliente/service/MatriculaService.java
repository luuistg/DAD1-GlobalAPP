package edu.ucam.cliente.service;

import java.util.List;

import edu.ucam.cliente.interfaces.IGenericService;
import edu.ucam.cliente.repository.MatriculaRepository;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;

public class MatriculaService implements IGenericService<Matricula, String>{

	private MatriculaRepository repo;

    public MatriculaService(MatriculaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void add(Object t) throws Exception {
 
    	if(t != null) {
    		repo.add(t); 
    	}else {
    		System.out.println("No se pududo enviar la información al servidor.");
    	}
        
    }

	@Override
	public void delete(String id) throws Exception {
		repo.delete(id);
		
	}

	@Override
	public Matricula get(String id) throws Exception {
		
		Matricula t = (Matricula) repo.getModel(id);	
		if (t != null) this.toString(t);
		return t;
	}

	@Override
	public void update(String id, Object model) throws Exception {
		repo.update(id, model);
	}

	@Override
	public List<Matricula> list() throws Exception {
		
		List<Matricula> list = (List<Matricula>) repo.list();
		
		if(list.isEmpty()) {
			System.out.println("No Hay Matriculas añadidas a la univerisdad");
		} else {
			for(Matricula t : list) {
				toString(t);
			}
		}
		return list;
	}
	
	public void toString(Matricula t) {
		
		System.out.println();
		System.out.println("Matricula: " + System.lineSeparator()
				+ "Id: " + t.getId() + System.lineSeparator()
				+ "DNI del Alumno: " + t.getAlumno().getDni() + System.lineSeparator()
				+ "Alumno: " + t.getAlumno().getNombre() +  " " + t.getAlumno().getApellidos() + System.lineSeparator()
				+ "Asignaturas: "
				);
		for (Asignatura a : t.getAsignaturas()) {
			System.out.println("ID: " + a.getId() + ", " + a.getNombre() + " nº de Creditos: " + a.getCreditos());
		}
		
		
	}

}
