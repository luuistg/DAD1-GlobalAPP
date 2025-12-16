package edu.ucam.cliente.repository;

import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Titulacion;

public class AsignaturaRepository extends BaseRepository <Asignatura>{

	public AsignaturaRepository(IComunicationServer comm, IChannelData channelData) {
		super(comm, channelData, 
	              Asignatura.class,
	              "ADDASIG",         
	              "DELASIG",         
	              "LISTAASIG",        
	              "UPDATEASIG",         
	              "GETASIG"          
	        );
	}


	
	public void addAsigToTit() {
		
	}
	
	public void removeAsigToTit() {
		
	}
	
	public void listAsigFromTit() {
		
	}

}
