package modelo.servicos;

import modelo.entidades.AluguelCarro;
import modelo.entidades.Fatura;

import java.time.Duration;

public class ServicoAluguel {

    private Double getPrecoPorHora;
    private Double precoPorDia;

    private ServicoImpostoBrasil impostoBrasil;


    public ServicoAluguel(Double getPrecoPorHora, Double precoPorDia, ServicoImpostoBrasil impostoBrasil) {
        this.getPrecoPorHora = getPrecoPorHora;
        this.precoPorDia = precoPorDia;
        this.impostoBrasil = impostoBrasil;
    }

    public void processoFatura(AluguelCarro aluguelCarro) {
        double minutos = Duration.between(aluguelCarro.getRetirada(), aluguelCarro.getRetorno()).toMinutes();
        double horas = minutos / 60;

        double pagamentoBasico;
        if (horas <= 12.0) {
            pagamentoBasico = getPrecoPorHora * Math.ceil(horas);
        }
        else {
            pagamentoBasico = precoPorDia * Math.ceil(horas / 24.0);
        }

        aluguelCarro.setFatura(new Fatura(50.0, 10.0));
        double imposto = impostoBrasil.imposto(pagamentoBasico);

        aluguelCarro.setFatura(new Fatura(pagamentoBasico, imposto));
    }
}
