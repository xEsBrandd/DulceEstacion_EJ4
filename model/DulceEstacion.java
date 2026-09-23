package model;

import java.util.ArrayList;
import java.util.Locale;

public class DulceEstacion {
    private final ArrayList<Maquina> maquinas;
    private double ingresosAcumulados;

    public DulceEstacion() {
        maquinas = new ArrayList<>();
        ingresosAcumulados = 0;
    }

    public void registrarMaquina(Maquina maquina) {
        if (maquina == null) {
            throw new IllegalArgumentException("Debe proporcionar una maquina.");
        }

        for (Maquina existente : maquinas) {
            if (existente.getCodigo().equals(maquina.getCodigo())) {
                throw new IllegalArgumentException("El codigo ya esta registrado.");
            }
        }

        if (!maquina.estaDisponible()) {
            throw new IllegalArgumentException(
                    "Una maquina nueva debe estar disponible.");
        }

        maquinas.add(maquina);
    }

    public Maquina buscarMaquina(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }

        for (Maquina maquina : maquinas) {
            if (maquina.getCodigo().equals(codigo.trim())) {
                return maquina;
            }
        }

        throw new IllegalArgumentException("No existe una maquina con ese codigo.");
    }

    public String consultarInventario() {
        if (maquinas.isEmpty()) {
            return "No hay maquinas registradas.";
        }

        String inventario = "";

        for (Maquina maquina : maquinas) {
            inventario += maquina.toString() + "\n\n";
        }

        return inventario;
    }

    public double cotizar(String codigo, int dias) {
        Maquina maquina = buscarMaquina(codigo);
        double total = maquina.calcularCosto(dias);

        if (!Double.isFinite(total)) {
            throw new IllegalArgumentException("El monto es demasiado grande.");
        }

        return total;
    }

    public double confirmarAlquiler(String codigo, int dias) {
        Maquina maquina = buscarMaquina(codigo);

        if (!maquina.estaDisponible()) {
            throw new IllegalStateException("La maquina ya esta alquilada.");
        }

        double total = cotizar(codigo, dias);

        if (!Double.isFinite(ingresosAcumulados + total)) {
            throw new IllegalArgumentException(
                    "El monto acumulado es demasiado grande.");
        }

        maquina.alquilar();
        ingresosAcumulados += total;

        return total;
    }

    public void registrarDevolucion(String codigo) {
        Maquina maquina = buscarMaquina(codigo);
        maquina.devolver();
    }

    public String generarReporte() {
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Palomitas");
        categorias.add("Algodon");
        categorias.add("Chocolate");

        for (Maquina maquina : maquinas) {
            if (!categorias.contains(maquina.getCategoria())) {
                categorias.add(maquina.getCategoria());
            }
        }

        String reporte = "REPORTE GENERAL\n";
        int totalDisponibles = 0;
        int totalAlquiladas = 0;

        for (String categoria : categorias) {
            int registradas = 0;
            int disponibles = 0;
            int alquiladas = 0;

            for (Maquina maquina : maquinas) {
                if (maquina.getCategoria().equals(categoria)) {
                    registradas++;

                    if (maquina.estaDisponible()) {
                        disponibles++;
                    } else {
                        alquiladas++;
                    }
                }
            }

            totalDisponibles += disponibles;
            totalAlquiladas += alquiladas;

            reporte += "\nCategoria: " + categoria
                    + "\nRegistradas: " + registradas
                    + "\nDisponibles: " + disponibles
                    + "\nAlquiladas: " + alquiladas + "\n";
        }

        reporte += "\nTotal registradas: " + maquinas.size()
                + "\nTotal disponibles: " + totalDisponibles
                + "\nTotal alquiladas: " + totalAlquiladas
                + "\nIngresos acumulados: Q"
                + String.format(Locale.US, "%.2f", ingresosAcumulados);

        return reporte;
    }
}