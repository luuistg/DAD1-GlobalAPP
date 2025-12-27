package edu.ucam.interfaces;

import java.util.List;

import edu.ucam.domain.Asignatura;

public interface AsignaturaDAO extends GenericDAO<Asignatura>{
	
	public boolean addAsigToTit(String idAsig, String idTit);
	
	public boolean removeAsigToTit(String idAsig, String idTit);
	
	public List<Asignatura> listAsigFromTit(String idTit);
}
