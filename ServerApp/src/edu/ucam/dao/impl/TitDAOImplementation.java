package edu.ucam.dao.impl;

import java.util.List;

import edu.ucam.data.UniversityRepository;
import edu.ucam.domain.Titulacion;
import edu.ucam.interfaces.TitulacionDAO;

public class TitDAOImplementation implements TitulacionDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public void eliminar(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Titulacion buscar(String id) {
		
		return db.titulaciones.get(id);
		
	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Titulacion t) {
		db.titulaciones.put(t.getId().trim(), t);
		
	}

	@Override
	public int modelSize() {
		// TODO Auto-generated method stub
		return 0;
	}
    
    

}
