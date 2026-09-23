package model;
import java.util.Locale;



public abstract class Maquina {
    private final String codigo;
    private final String marca;
    private final String modelo;
    protected final double tarifaDiaria;
    private boolean disponible;

    protected Maquina(String codigo, String marca, String modelo, double tarifaDiaria) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }

        if (!Double.isFinite(tarifaDiaria) || tarifaDiaria <= 0) {
            throw new IllegalArgumentException("La tarifa debe ser mayor que cero.");
        }

        this.codigo = codigo.trim();
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public abstract String getCategoria();

    public double calcularCosto(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException("Los dias deben ser mayores que cero.");
        }

        return tarifaDiaria * dias;
    }

    void alquilar() {
        if (!disponible) {
            throw new IllegalStateException("La maquina ya esta alquilada.");
        }

        disponible = false;
    }

    void devolver() {
        if (disponible) {
            throw new IllegalStateException("La maquina ya esta disponible.");
        }

        disponible = true;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + "\nCategoria: " + getCategoria()
                + "\nMarca: " + marca
                + "\nModelo: " + modelo
                + "\nTarifa diaria: Q"
                + String.format(Locale.US, "%.2f", tarifaDiaria)
                + "\nEstado: " + (disponible ? "Disponible" : "Alquilada");
    }
}