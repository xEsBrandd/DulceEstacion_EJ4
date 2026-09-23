package controller;

import model.*;
import view.Vista;
import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

public class Controlador {
    private final DulceEstacion modelo;
    private final Vista vista;

    public Controlador(DulceEstacion modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            try {
                int opcion = vista.mostrarMenu();

                switch (opcion) {
                    case 1:
                        registrarMaquina();
                        break;

                    case 2:
                        vista.mostrarMensaje(modelo.consultarInventario());
                        break;

                    case 3:
                        cotizarAlquiler();
                        break;

                    case 4:
                        confirmarAlquiler();
                        break;

                    case 5:
                        registrarDevolucion();
                        break;

                    case 6:
                        vista.mostrarMensaje(modelo.generarReporte());
                        break;

                    case 0:
                        salir = true;
                        vista.mostrarMensaje("Programa finalizado.");
                        break;

                    default:
                        vista.mostrarMensaje("Opcion invalida.");
                }
            } catch (IllegalArgumentException error) {
                vista.mostrarMensaje(error.getMessage());
            } catch (IllegalStateException error) {
                vista.mostrarMensaje(error.getMessage());
            } catch (NoSuchElementException error) {
                vista.mostrarMensaje("Fin de entrada. Programa finalizado.");
                salir = true;
            }
        }
    }

    private void registrarMaquina() {
        vista.mostrarMensaje("\n1. Maquina de palomitas");
        vista.mostrarMensaje("2. Maquina de algodon");
        vista.mostrarMensaje("3. Fuente de chocolate");

        int categoria = vista.leerEntero("Seleccione la categoria:");

        if (categoria < 1 || categoria > 3) {
            vista.mostrarMensaje("Categoria invalida.");
            return;
        }

        String codigo = vista.leerTexto("Codigo de inventario:");
        String marca = vista.leerTexto("Marca:");
        String modeloMaquina = vista.leerTexto("Modelo:");
        double tarifa = vista.leerDecimal("Tarifa diaria:");

        Maquina maquina;

        switch (categoria) {
            case 1:
                int porciones = vista.leerEntero("Porciones por hora:");
                boolean carrito = vista.leerSiNo("Tiene carrito integrado?");

                maquina = new MaquinaPalomitas(
                        codigo, marca, modeloMaquina, tarifa, porciones, carrito);
                break;

            case 2:
                int potencia = vista.leerEntero("Potencia en vatios:");

                maquina = new MaquinaAlgodon(
                        codigo, marca, modeloMaquina, tarifa, potencia);
                break;

            default:
                double capacidad = vista.leerDecimal("Capacidad maxima en kilogramos:");

                maquina = new FuenteChocolate(
                        codigo, marca, modeloMaquina, tarifa, capacidad);
        }

        modelo.registrarMaquina(maquina);
        vista.mostrarMensaje("Maquina registrada correctamente.");
    }

    private void cotizarAlquiler() {
        String codigo = vista.leerTexto("Codigo de inventario:");
        Maquina maquina = modelo.buscarMaquina(codigo);
        int dias = vista.leerEntero("Cantidad de dias:");

        double total = modelo.cotizar(codigo, dias);

        vista.mostrarMensaje(maquina.toString());
        vista.mostrarMensaje("Dias: " + dias);
        vista.mostrarMonto("Total de la cotizacion:", total);
    }

    private void confirmarAlquiler() {
        String codigo = vista.leerTexto("Codigo de inventario:");
        Maquina maquina = modelo.buscarMaquina(codigo);

        if (!maquina.estaDisponible()) {
            vista.mostrarMensaje("La maquina ya esta alquilada.");
            return;
        }

        int dias = vista.leerEntero("Cantidad de dias:");
        double total = modelo.cotizar(codigo, dias);

        vista.mostrarMensaje(maquina.toString());
        vista.mostrarMensaje("Dias: " + dias);
        vista.mostrarMonto("Total a pagar:", total);

        boolean confirmar = vista.leerSiNo("Desea confirmar el alquiler y el cobro?");

        if (confirmar) {
            double cobrado = modelo.confirmarAlquiler(codigo, dias);
            vista.mostrarMonto("Alquiler confirmado. Monto cobrado:", cobrado);
        } else {
            vista.mostrarMensaje("Operacion cancelada. No se realizaron cambios.");
        }
    }

    private void registrarDevolucion() {
        String codigo = vista.leerTexto("Codigo de inventario:");

        modelo.registrarDevolucion(codigo);
        vista.mostrarMensaje("Devolucion registrada. No se realizo otro cobro.");
    }
}