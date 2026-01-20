package edu.ucam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ucam.data.UniversityRepository;
import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.AsignaturaDAO;

public class AsigDAOImplementation implements AsignaturaDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public void guardar(Asignatura t) {
		db.asignaturas.put(t.getId(), t);
		
	}

	@Override
	public boolean  eliminar(String id) {
		db.asignaturas.remove(id);
		
		if(!db.titulaciones.containsKey(id)) return true;
		
		return false;
		
	}

	@Override
	public Asignatura buscar(String id) {
		return db.asignaturas.get(id);
	}

	@Override
	public List<Asignatura> listar() {
		
		if (db.asignaturas.isEmpty()) {
            return new ArrayList<>();
        }
        // Creamos una copia nueva para enviar
        return new ArrayList<>(db.asignaturas.values());
	}

	@Override
	public boolean addAsigToTit(String idAsig, String idTit) {
		
		boolean existAsignatura = db.asignaturas.containsKey(idAsig);
	    boolean existTitulacion = db.titulaciones.containsKey(idTit);
	    
		if( existAsignatura && existTitulacion) {
			
			db.titulaciones.get(idTit).addAsignatura(db.asignaturas.get(idAsig));
			return true;

		}
		
		return false;
		
	}

	@Override
	public boolean removeAsigToTit(String idAsig, String idTit) {
		
		boolean existAsignatura = db.asignaturas.containsKey(idAsig);
	    boolean existTitulacion = db.titulaciones.containsKey(idTit);
	    
		if( existAsignatura && existTitulacion) {
			
			db.titulaciones.get(idTit).removeAsignatura(idAsig);
			return true;

		}
		
		return false;
		
	}

	@Override
	public List<Asignatura> listAsigFromTit(String idTit) {
		if(db.titulaciones.containsKey(idTit)) {
			
			
			return new ArrayList<>(db.titulaciones.get(idTit).getAsignaturas());

		}
		
		return null;
		
	}

	@Override
	public void actualizar(Asignatura t) {
		if(!db.asignaturas.containsKey(t.getId())) return;
		
		db.asignaturas.put(t.getId(), t);
		return;
	}

}
