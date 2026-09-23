package model;

public class MaquinaAlgodon extends Maquina {
    private final int potenciaVatios;

    public MaquinaAlgodon(String codigo, String marca, String modelo,
                          double tarifaDiaria, int potenciaVatios) {
        super(codigo, marca, modelo, tarifaDiaria);

        if (potenciaVatios <= 0) {
            throw new IllegalArgumentException(
                    "La potencia debe ser mayor que cero.");
        }

        this.potenciaVatios = potenciaVatios;
    }

    @Override
    public String getCategoria() {
        return "Algodon";
    }

    @Override
    public double calcularCosto(int dias) {
        double total = super.calcularCosto(dias);

        if (potenciaVatios > 1000) {
            total += 60;
        }

        return total;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nPotencia: " + potenciaVatios + " W";
    }
}