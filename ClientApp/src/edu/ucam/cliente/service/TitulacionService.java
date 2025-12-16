package edu.ucam.cliente.service;

import java.util.List;

import edu.ucam.cliente.interfaces.IGenericService;
import edu.ucam.cliente.repository.TitulacionRepository;
import edu.ucam.domain.Titulacion;

public class TitulacionService implements IGenericService<Titulacion, String>{

	private TitulacionRepository repo;

    public TitulacionService(TitulacionRepository repo) {
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
		
		this.toString((Titulacion) repo.getModel(id));
	}

	@Override
	public void update(String id, Object model) throws Exception {
		repo.update(id, model);
	}

	@Override
	public void list() throws Exception {
		
		
		for(Titulacion t : (List<Titulacion>) repo.list()) {
			toString(t);
		}
	}
	
	public void toString(Titulacion t) {
		
		System.out.println("Titulacion: " + System.lineSeparator()
				+ "Id: " + t.getId() + System.lineSeparator()
				+ "Nombre: " + t.getNombre());
		
		
	}

}
