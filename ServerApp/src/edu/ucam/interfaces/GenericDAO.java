package edu.ucam.interfaces;

import java.util.List;

public interface GenericDAO<T> {
	
	void guardar(T t);
    boolean eliminar(String id);
    T buscar(String id);
    List<T> listar();

}
