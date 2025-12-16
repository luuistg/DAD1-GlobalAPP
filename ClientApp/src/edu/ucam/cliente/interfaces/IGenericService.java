package edu.ucam.cliente.interfaces;

import java.util.List;

import edu.ucam.domain.Titulacion;

public interface IGenericService <T, K>{
	    
	    void add(Object object) throws Exception;
	    
	    void delete(K id) throws Exception;
	    
	    void get(String id) throws Exception;
	    
	    void update(String id, Object model) throws Exception;
	    
	    void list() throws Exception;

}
