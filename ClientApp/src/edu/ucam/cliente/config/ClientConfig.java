package edu.ucam.cliente.config;

import java.util.HashMap;

import edu.ucam.cliente.form.AsigForm;
import edu.ucam.cliente.form.MatriculaForm;
import edu.ucam.cliente.form.TitulacionForm;
import edu.ucam.cliente.interfaces.IForm;

public class ClientConfig {
	
	/**
	 * Variables de confitucaión para los puerto
	 */
	public static final String ip="127.0.0.1";
	public static final int comunicationPort = 8000;


    public static HashMap<Integer, IForm> initForms() {
        HashMap<Integer, IForm> forms = new HashMap<>();
        
        forms.put(1, new TitulacionForm());
        forms.put(2, new MatriculaForm());
        forms.put(3, new AsigForm());
        
        return forms;
    }

}
