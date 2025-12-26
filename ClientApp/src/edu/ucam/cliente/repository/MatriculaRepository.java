package edu.ucam.cliente.repository;

import edu.ucam.cliente.interfaces.IChannelData;
import edu.ucam.cliente.interfaces.IComunicationServer;
import edu.ucam.domain.Matricula;

public class MatriculaRepository extends BaseRepository <Matricula>{

	public MatriculaRepository(IComunicationServer comm, IChannelData channelData) {
		super(comm, channelData, 
				"ADDMATRICULA", 
				"REMOVEMATRICULA", 
				"", 
				"UPDATEMATRICULA",
				"GETMATRICULA"
				);
	}

}
