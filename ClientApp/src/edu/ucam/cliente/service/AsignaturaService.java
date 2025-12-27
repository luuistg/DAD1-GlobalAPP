package edu.ucam.cliente.service;

import java.io.IOException;
import java.util.List;

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
		repo.add(object);
		
	}

	@Override
	public void delete(String id) throws Exception {
		repo.delete(id);
		
	}

	@Override
	public Asignatura get(String id) throws Exception {
		Asignatura t = (Asignatura) repo.getModel(id);	
		if (t != null) this.toString(t);
		
		return t;
		
	}

	@Override
	public void update(String id, Object model) throws Exception {
		repo.update(id, model);
		
	}

	@Override
	public List<Asignatura> list() throws Exception {
		
		List<Asignatura> list = (List<Asignatura>) repo.list();
		
		if(list.isEmpty()) {
			System.out.println("No Hay Asignaturas añadidas a la univerisdad");
		} else {
			for(Asignatura a : list) {
				toString(a);
			}
		}
		return list;
		
	}

	public void addAsigToTit(String idAsig, String idTit) throws IOException {
		repo.addAsigToTit(idAsig, idTit);
		
	}

	public void removeAsigToTit(String idAsig, String idTit) throws IOException {
		repo.removeAsigToTit(idAsig, idTit);
		
	}

	public List<Asignatura> listAsigFromTit(String idTit) throws ClassNotFoundException, IOException {
		
		List<Asignatura> list = (List<Asignatura>)repo.listAsigFromTit(idTit);
		
		if(list.isEmpty()) {
			System.out.println("No Hay Asignaturas añadidas a la titulación con id: " + idTit);
		} else {
			for(Asignatura a : list) {
				toString(a);
			}
		}
		return list;
		
	}
	
	public void toString(Asignatura a) {
		
		System.out.println();
		System.out.println("Asignatura:");
		
		System.out.println("ID: " + a.getId() + ", " + a.getNombre() + " nº de Creditos: " + a.getCreditos());

		
	}

}
