package edu.ucam.logic;

public class ProtocolResponse {
	
	public enum Status { 
        OK,  
        PREOK, 
        FAILED  
    }

    private Status status;
    private String idEnvio;   //<number>
    private int codigo;       //<código respuesta>
    private String mensaje;   //<información adicional>

    // Constructor limpio
    public ProtocolResponse(Status status, String idEnvio, int codigo, String mensaje) {
        this.status = status;
        this.idEnvio = idEnvio;
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    // ProtocolResponse --> Response String
    // <tipo> <number> <código> <mensaje>
    public String toProtocolString() {
        return status.toString() + " " + idEnvio + " " + codigo + " " + mensaje;
    }
    
    // Getters
    public Status getStatus() { return status; }
    public String getMensaje() { return mensaje; }

}
