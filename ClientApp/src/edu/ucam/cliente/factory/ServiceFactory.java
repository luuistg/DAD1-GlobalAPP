package edu.ucam.cliente.factory;

import java.util.HashMap;
import java.util.Map;
import edu.ucam.cliente.interfaces.*;
import edu.ucam.cliente.repository.*;
import edu.ucam.cliente.service.AsignaturaService;
import edu.ucam.cliente.service.MatriculaService;
import edu.ucam.cliente.service.TitulacionService;

public class ServiceFactory {

    private static ServiceFactory instance;
    
    // ESTE ES TU MAPA MÁGICO
    // Usamos el comodín <?, ?> porque cada servicio tiene tipos distintos
    private Map<Integer, IGenericService<?,?>> servCache;

    private IComunicationServer comm;
    private IChannelData data;

    // Constructor privado: Aquí inicializamos y llenamos el mapa
    private ServiceFactory(IComunicationServer comm, IChannelData data) {
        this.comm = comm;
        this.data = data;
        this.servCache = new HashMap<>();
        
        cargarServicios();
    }

    // Método para crear y guardar las instancias una sola vez
    private void cargarServicios() {
        TitulacionRepository repoTit = new TitulacionRepository(comm, data);
        TitulacionService servTit = new TitulacionService(repoTit);
        servCache.put(1, servTit); 
        
        MatriculaRepository repoMat = new MatriculaRepository(comm, data);
        MatriculaService servMat = new MatriculaService(repoMat);
        servCache.put(2, servMat);
        
        AsignaturaRepository repoAsig = new AsignaturaRepository(comm, data);
        AsignaturaService servAsig = new AsignaturaService(repoAsig);
        servCache.put(3, servAsig);
        
    }

    public static void init(IComunicationServer comm, IChannelData data) {
        if (instance == null) {
            instance = new ServiceFactory(comm, data);
        }
    }

    public static ServiceFactory getInstance() {
        if (instance == null) throw new RuntimeException("llamar init()");
        return instance;
    }

    // --- MÉTODO PARA RECUPERAR DEL MAPA ---
    
    public IGenericService<?, ?> getService(int opt) {
        return servCache.get(opt);
    }
}