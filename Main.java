import model.*;
import view.Vista;
import controller.Controlador;
// Principal.java
public class Principal {
    public static void main(String[] args) {
        DulceEstacion modelo = new DulceEstacion();

        modelo.registrarMaquina(
                new MaquinaPalomitas("P01", "Pop", "A", 100, 120, true));

        modelo.registrarMaquina(
                new MaquinaPalomitas("P02", "Pop", "B", 100, 100, false));

        modelo.registrarMaquina(
                new MaquinaAlgodon("A01", "Sweet", "A", 120, 1000));

        modelo.registrarMaquina(
                new MaquinaAlgodon("A02", "Sweet", "B", 120, 1200));

        modelo.registrarMaquina(
                new FuenteChocolate("C01", "Choco", "A", 150, 2.5));

        modelo.registrarMaquina(
                new FuenteChocolate("C02", "Choco", "B", 150, 4));

        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);

        controlador.iniciar();
    }
}