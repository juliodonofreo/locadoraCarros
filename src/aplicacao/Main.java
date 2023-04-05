package aplicacao;

import modelo.entidades.AluguelCarro;
import modelo.entidades.Veiculo;
import modelo.servicos.ServicoAluguel;
import modelo.servicos.ServicoImpostoBrasil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel ");
        System.out.print("Modelo do carro: ");
        String modeloCarro = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime retirada = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime retorno = LocalDateTime.parse(sc.nextLine(), fmt);

        AluguelCarro aluguelCarro = new AluguelCarro(retirada, retorno, new Veiculo(modeloCarro));
        System.out.print("Entre com o preço por hora: ");
        double precoHora= sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double precoDia = sc.nextDouble();

        ServicoAluguel servicoAluguel = new ServicoAluguel(precoHora, precoDia, new ServicoImpostoBrasil());

        servicoAluguel.processoFatura(aluguelCarro);

        System.out.println("FATURA: ");
        System.out.printf("Pagamento básico: R$%.2f\n", aluguelCarro.getFatura().getPagamentoBasico());
        System.out.printf("Imposto:  R$%.2f\n",aluguelCarro.getFatura().getImposto());
        System.out.printf("Pagamento total: R$%.2f\n", aluguelCarro.getFatura().getTotalPagamento());


        sc.close();
    }
}
