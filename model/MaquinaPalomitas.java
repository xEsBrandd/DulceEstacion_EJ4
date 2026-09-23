package model;

public class MaquinaPalomitas extends Maquina {
    private final int porcionesPorHora;
    private final boolean tieneCarrito;

    public MaquinaPalomitas(String codigo, String marca, String modelo,
                            double tarifaDiaria, int porcionesPorHora,
                            boolean tieneCarrito) {
        super(codigo, marca, modelo, tarifaDiaria);

        if (porcionesPorHora <= 0) {
            throw new IllegalArgumentException(
                    "Las porciones por hora deben ser mayores que cero.");
        }

        this.porcionesPorHora = porcionesPorHora;
        this.tieneCarrito = tieneCarrito;
    }

    @Override
    public String getCategoria() {
        return "Palomitas";
    }

    @Override
    public double calcularCosto(int dias) {
        double total = super.calcularCosto(dias);

        if (tieneCarrito) {
            total += 40.0 * dias;
        }

        return total;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nPorciones por hora: " + porcionesPorHora
                + "\nCarrito integrado: " + (tieneCarrito ? "Si" : "No");
    }
}