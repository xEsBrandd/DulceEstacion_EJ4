package model;

public class FuenteChocolate extends Maquina {
    private final double capacidadKg;

    public FuenteChocolate(String codigo, String marca, String modelo,
                            double tarifaDiaria, double capacidadKg) {
        super(codigo, marca, modelo, tarifaDiaria);

        if (!Double.isFinite(capacidadKg) || capacidadKg <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

        this.capacidadKg = capacidadKg;
    }

    @Override
    public String getCategoria() {
        return "Chocolate";
    }

    @Override
    public double calcularCosto(int dias) {
        return super.calcularCosto(dias) + 20 * capacidadKg * dias;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nCapacidad maxima: " + capacidadKg + " kg";
    }
}