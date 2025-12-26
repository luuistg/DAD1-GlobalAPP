package edu.ucam.dao.impl;

import java.util.List;

import edu.ucam.data.UniversityRepository;
import edu.ucam.domain.Matricula;
import edu.ucam.interfaces.MatriculaDAO;

public class MatDAOImplementation implements MatriculaDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public boolean eliminar(String id) {
		db.titulaciones.remove(id);
		
		if(!db.matriculas.containsKey(id)) return true;
		
		return false;
	}

	@Override
	public Matricula buscar(String id) {
		
		return db.matriculas.get(id);
		
	}

	@Override
	public void guardar(Matricula t) {
		db.matriculas.put(t.getId().trim(), t);
		
	}

	@Override
	public void actualizar(Matricula t) {
		
		if(!db.matriculas.containsKey(t.getId())) return;
		
		db.matriculas.put(t.getId(), t);
		return;
	}

	@Override
	public List<Matricula> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
