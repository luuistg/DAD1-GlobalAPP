package edu.ucam.cliente.form;

import java.util.Scanner;

import edu.ucam.cliente.interfaces.IForm;

public abstract class GenericForm implements IForm{
	
	
	
	protected String pedirTextoNoVacio(Scanner sc, String mensaje) {
        String texto = "";
        while (texto.trim().isEmpty()) {
            System.out.print(mensaje);
            texto = sc.nextLine();
            if (texto.trim().isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            }
        }
        return texto;
    }

    protected int pedirEntero(Scanner sc, String mensaje) {
        int numero = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                String input = sc.nextLine();
                numero = Integer.parseInt(input);
                if (numero > 0) {
                    valido = true;
                } else {
                    System.out.println("Debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número válido.");
            }
        }
        return numero;
    }

}
