package edu.ucam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ucam.data.UniversityRepository;
import edu.ucam.domain.Titulacion;
import edu.ucam.interfaces.TitulacionDAO;

public class TitDAOImplementation implements TitulacionDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public boolean eliminar(String id) {
		db.titulaciones.remove(id);
		
		if(!db.titulaciones.containsKey(id)) return true;
		
		return false;
	}

	@Override
	public Titulacion buscar(String id) {
		
		return db.titulaciones.get(id);
		
	}

	@Override
	public List listar() {
		
		if (db.titulaciones.isEmpty()) {
            return new ArrayList<>();
        }
        // Creamos una copia nueva para enviar
        return new ArrayList<>(db.titulaciones.values());
	}

	@Override
	public void guardar(Titulacion t) {
		db.titulaciones.put(t.getId().trim(), t);
		
	}

	@Override
	public int modelSize() {
		
		return db.titulaciones.size();
	}

	@Override
	public void actualizar(Titulacion t) {
		
		if(!db.titulaciones.containsKey(t.getId())) return;
		
		db.titulaciones.put(t.getId(), t);
		return;
	}
    
    

}
