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
	public Titulacion get(String id) throws Exception {		
		Titulacion t = (Titulacion) repo.getModel(id);	
		if (t != null) this.toString(t);
		
		return t;
	}

	@Override
	public void update(String id, Object model) throws Exception {
		repo.update(id, model);
	}

	@Override
	public List<Titulacion> list() throws Exception {
		
		List<Titulacion> list = (List<Titulacion>) repo.list();
		
		if(list.isEmpty()) {
			System.out.println("No Hay Titulaciones añadidas a la univerisdad");
		} else {
			for(Titulacion t : list) {
				toString(t);
			}
		}
		return list;
	}
	
	public void count() throws Exception{
		
		repo.modelSize();
	}
	
	public void toString(Titulacion t) {
		
		System.out.println();
		System.out.println("Titulacion: " + System.lineSeparator()
				+ "Id: " + t.getId() + System.lineSeparator()
				+ "Nombre: " + t.getNombre());
		
		
	}

}
