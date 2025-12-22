package edu.ucam.dao.impl;

import java.util.List;

import edu.ucam.data.UniversityRepository;
import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.AsignaturaDAO;

public class AsigDAOImplementation implements AsignaturaDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public void guardar(Asignatura t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean  eliminar(String id) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Asignatura buscar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Asignatura> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAsigToTit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAsigToTit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listAsigFromTit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Asignatura t) {
		// TODO Auto-generated method stub
		return;
	}

}
