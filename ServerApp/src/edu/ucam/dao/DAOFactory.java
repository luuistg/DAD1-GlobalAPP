package edu.ucam.dao;

import edu.ucam.dao.impl.AsigDAOImplementation;
import edu.ucam.dao.impl.MatDAOImplementation;
import edu.ucam.dao.impl.TitDAOImplementation;
import edu.ucam.dao.impl.UserDAOImplementation;
import edu.ucam.interfaces.AsignaturaDAO;
import edu.ucam.interfaces.MatriculaDAO;
import edu.ucam.interfaces.TitulacionDAO;
import edu.ucam.interfaces.UserDAO;

public class DAOFactory {
	
	private static DAOFactory instance;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    // --- MÉTODOS DE FABRICACIÓN ---

    public TitulacionDAO getTitulacionDAO() {
        return new TitDAOImplementation();
    }

    public AsignaturaDAO getAsignaturaDAO() {
        return new AsigDAOImplementation();
    }
    
    public MatriculaDAO getMatriculaDAO() {
    	return new MatDAOImplementation();
    }

	public UserDAO getUsuarioDAO() {
		return new UserDAOImplementation();
	}
    
}

