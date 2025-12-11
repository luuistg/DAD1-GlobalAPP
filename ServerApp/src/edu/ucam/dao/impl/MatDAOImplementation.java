package edu.ucam.dao.impl;

import java.util.List;

import edu.ucam.data.UniversityRepository;
import edu.ucam.domain.Matricula;
import edu.ucam.interfaces.MatriculaDAO;

public class MatDAOImplementation implements MatriculaDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public void guardar(Matricula t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Matricula buscar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matricula> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
