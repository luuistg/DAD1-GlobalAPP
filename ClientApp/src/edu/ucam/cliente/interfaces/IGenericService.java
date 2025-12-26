package edu.ucam.cliente.interfaces;

import java.util.List;

public interface IGenericService <T, K>{
	    
	    void add(Object object) throws Exception;
	    
	    void delete(String id) throws Exception;
	    
	    T get(String id) throws Exception;
	    
	    void update(String id, Object model) throws Exception;
	    
	    List<T> list() throws Exception;

}
