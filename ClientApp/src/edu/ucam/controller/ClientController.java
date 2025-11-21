package edu.ucam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
	
	/*
	 * Mantiene la conexión
	 * 
	 * Si recibe un PREOK abre una conexión Utilizando DataTransferClient, para el envio del objeto
	 */
	
	private String ip;
    private int puerto;
    
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    
    public ClientController(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
    }
	
    public void ejecutar() {
        
        try {
        	
        	 this.socket = new Socket(ip, puerto);
        	 
             this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
             this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             
  
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
    
    public String enviarComando(String comando) throws IOException {
    	
    	if (comando.trim().equalsIgnoreCase("HELP")) {
            return generarTextoAyuda();
        }
    	
        if (socket == null || socket.isClosed()) {
            throw new IOException("No hay conexión con el servidor.");
        }

        pw.println(comando); pw.flush();

        //Esperando Respuesta
        String respuesta = br.readLine();
        
        // TODO: AQUÍ ES DONDE DETECTAREMOS EL "PREOK" EN EL FUTURO
        // Si respuesta.startsWith("PREOK") -> iniciarTransferenciaDatos(...)

        if (respuesta == null) {
            throw new IOException("El servidor cerró la conexión inesperadamente.");
        }

        return respuesta;
    }
    
    private String generarTextoAyuda() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- LISTA DE COMANDOS DISPONIBLES ---\n");
        sb.append("Recuerda incluir un ID numérico al inicio (ej: 1 USER ...)\n\n");
        
        // Comandos de Sesión (Entrega Parcial)
        sb.append("SESIÓN:\n");
        sb.append(" - <id> USER <nombre>      -> Enviar usuario\n");
        sb.append(" - <id> PASS <clave>       -> Enviar contraseña\n");
        sb.append(" - <id> EXIT               -> Cerrar sesión\n");
        
        // Comandos de Títulos (Entrega Parcial)
        sb.append("\nGESTIÓN TÍTULOS:\n");
        sb.append(" - <id> ADDTIT             -> Añadir titulación (requiere datos)\n");
        sb.append(" - <id> GETTIT <id_tit>    -> Obtener datos de una titulación\n");
        
        // Nota para comandos futuros
        sb.append("\nOTROS:\n");
        sb.append(" - help                    -> Muestra esta ayuda (Local)\n");
        sb.append("------------------------------------------");
        
        return sb.toString();
    }

    public void desconectar() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

}
