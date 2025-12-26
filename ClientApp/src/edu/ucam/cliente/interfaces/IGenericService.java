package edu.ucam.cliente.interfaces;

public interface IGenericService <T, K>{
	    
	    void add(Object object) throws Exception;
	    
	    void delete(String id) throws Exception;
	    
	    T get(String id) throws Exception;
	    
	    void update(String id, Object model) throws Exception;
	    
	    void list() throws Exception;

}
