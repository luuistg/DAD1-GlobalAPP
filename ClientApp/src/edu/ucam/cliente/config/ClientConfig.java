package edu.ucam.cliente.config;

import java.util.HashMap;

import edu.ucam.cliente.form.AsigForm;
import edu.ucam.cliente.form.TitulacionForm;
import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.cliente.interfaces.IForm;
import edu.ucam.cliente.repository.BaseRepository;
import edu.ucam.cliente.repository.TitulacionRepository;
import edu.ucam.cliente.repository.AsignaturaRepository;

public class ClientConfig {
	
	/**
	 * Variables de confitucaión para los puerto
	 */
	public static final String ip="127.0.0.1";
	public static final int comunicationPort = 8000;

    
    public static HashMap<Integer, BaseRepository> initRepositories(IComunicationServer comm, IChannelData data) {
        HashMap<Integer, BaseRepository> repos = new HashMap<>();
        
        repos.put(1, new TitulacionRepository(comm, data));
        repos.put(2, null);
        repos.put(3, new AsignaturaRepository(comm, data));
        repos.put(0, null);
        
        return repos;
    }

    public static HashMap<Integer, IForm> initForms() {
        HashMap<Integer, IForm> forms = new HashMap<>();
        
        forms.put(1, new TitulacionForm());
        forms.put(2, null);
        forms.put(3, new AsigForm());
        
        return forms;
    }

}
