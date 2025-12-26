package edu.ucam.cliente.service;

import edu.ucam.cliente.interfaces.IGenericService;
import edu.ucam.cliente.repository.AsignaturaRepository;
import edu.ucam.domain.Asignatura;

public class AsignaturaService implements IGenericService<Asignatura, String>{
	
	private AsignaturaRepository repo;

    public AsignaturaService(AsignaturaRepository repo) {
        this.repo = repo;
    }

	@Override
	public void add(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Asignatura get(String id) throws Exception {
		Asignatura t = (Asignatura) repo.getModel(id);	
		if (t != null) this.toString(t);
		
		return t;
		
	}

	@Override
	public void update(String id, Object model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void list() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void addAsigToTit() {
		// TODO Auto-generated method stub
		
	}

	public void removeAsigToTit() {
		// TODO Auto-generated method stub
		
	}

	public void listAsigFromTit() {
		// TODO Auto-generated method stub
		
	}
	public void toString(Asignatura t) {
		
		System.out.println("Matricula: " + System.lineSeparator()
				+ "Id: " + t.getId());
		
		
	}

}
