package modelo.servicos;

public class ServicoImpostoBrasil implements ServicoImposto {

    public double imposto(double quantia) {
        if (quantia <= 100) {
            return quantia * 0.2;
        }
        else {
            return quantia * 0.15;
        }
    }
}
