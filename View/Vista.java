package view;
import java.util.Locale;
import java.util.Scanner;
import java.util.Locale;
import java.util.Scanner;

public class Vista {
    private final Scanner entrada;

    public Vista() {
        entrada = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int mostrarMenu() {
        System.out.println("\nDULCE ESTACION");
        System.out.println("1. Registrar maquina");
        System.out.println("2. Consultar inventario");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolucion");
        System.out.println("6. Reporte general");
        System.out.println("0. Salir");

        return leerEntero("Seleccione una opcion:");
    }

    public String leerTexto(String mensaje) {
        System.out.println(mensaje);
        return entrada.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException error) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            try {
                double numero = Double.parseDouble(leerTexto(mensaje));

                if (Double.isFinite(numero)) {
                    return numero;
                }

                System.out.println("Debe ingresar un numero finito.");
            } catch (NumberFormatException error) {
                System.out.println("Ingrese un numero. Use punto para los decimales.");
            }
        }
    }

    public boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje + " (s/n)");

            if (respuesta.equalsIgnoreCase("s")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Debe responder s o n.");
        }
    }

    public void mostrarMonto(String mensaje, double monto) {
        System.out.printf(Locale.US, "%s Q%.2f%n", mensaje, monto);
    }
}