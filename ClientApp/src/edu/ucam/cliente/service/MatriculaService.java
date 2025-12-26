package edu.ucam.cliente.service;

import java.util.List;

import edu.ucam.cliente.interfaces.IGenericService;
import edu.ucam.cliente.repository.MatriculaRepository;
import edu.ucam.domain.Matricula;

public class MatriculaService implements IGenericService<Matricula, String>{

	private MatriculaRepository repo;

    public MatriculaService(MatriculaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void add(Object t) throws Exception {
 
        repo.add(t); 
    }

	@Override
	public void delete(String id) throws Exception {
		repo.delete(id);
		
	}

	@Override
	public void get(String id) throws Exception {
		
		Matricula t = (Matricula) repo.getModel(id);	
		if (t != null) this.toString(t);
	}

	@Override
	public void update(String id, Object model) throws Exception {
		repo.update(id, model);
	}

	@Override
	public void list() throws Exception {
		
		List<Matricula> list = (List<Matricula>) repo.list();
		
		if(list.isEmpty()) {
			System.out.println("No Hay Matriculas añadidas a la univerisdad");
		} else {
			for(Matricula t : list) {
				toString(t);
			}
		}
	}
	
	public void toString(Matricula t) {
		
		System.out.println("Matricula: " + System.lineSeparator()
				+ "Id: " + t.getId() + System.lineSeparator()
				+ "DNI del Alumno: " + t.getAlumno().getDni() + System.lineSeparator()
				+ "Alumno: " + t.getAlumno().getNombre() +  " " + t.getAlumno().getApellidos());
		
		
	}

}
