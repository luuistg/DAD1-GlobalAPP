package edu.ucam.interfaces;

import edu.ucam.domain.Asignatura;

public interface AsignaturaDAO extends GenericDAO<Asignatura>{
	
	public void addAsigToTit();
	
	public void removeAsigToTit();
	
	public void listAsigFromTit();
}
