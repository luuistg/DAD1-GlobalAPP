package edu.ucam.ui;

import java.io.IOException;
import java.util.Scanner;

import edu.ucam.controller.ClientController;

public class Interface {
	
	/*
	 * Menú para el usaario
	 * Captura la entrada del usuario y la manda al -> ClientController
	 * Muestra las respuestas del servidor
	 */
	
	private ClientController controller;
    private Scanner teclado;

    public Interface(ClientController controller) {
        this.controller = controller;
        this.teclado = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("========================================");
        System.out.println("      CLIENTE UNIVERSIDAD - UCAM        ");
        System.out.println("========================================");
        System.out.println("Conectando al servidor...");

        try {
        	
            controller.ejecutar();
            System.out.println("Conexión establecida.");
            System.out.println("Instrucciones:");
            System.out.println(" - Escribe 'HELP' para ayuda");
            System.out.println(" - Escribe 'SALIR' para cerrar.");
            System.out.println("----------------------------------------");

            buclePrincipal();

        } finally {
            controller.desconectar();
            System.out.println("Aplicación finalizada.");
        }
    }

    private void buclePrincipal() {
        boolean salir = false;
        
        while (!salir) {
            // 1. Prompt
            System.out.print("Command > ");
            String entrada = teclado.nextLine().trim();

            if (entrada.isEmpty()) continue;

            try {
                String respuesta = controller.enviarComando(entrada);

                System.out.println("Server -> " + respuesta);

                // 4. Control de salida local
                if (respuesta.contains("Adios")) {
                    salir = true;
                }

            } catch (IOException e) {
                System.err.println("Error de comunicación: " + e.getMessage());
                salir = true;
            }
        }
    }

}
