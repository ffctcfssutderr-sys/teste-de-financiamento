import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class FinancingCalculator {

    public static void main(String[] args) {
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(ptBr);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   SIMULADOR DE FINANCIAMENTO (PRICE)   ");
        System.out.println("=========================================");

        System.out.print("Digite o valor do bem (R$): ");
        double assetValue = scanner.nextDouble();

        System.out.print("Digite o valor da entrada (R$): ");
        double downPayment = scanner.nextDouble();

        System.out.print("Digite a taxa de juros anual (%): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Digite o prazo do financiamento (em meses): ");
        int months = scanner.nextInt();


        double fundedAmount = assetValue - downPayment;

        if (fundedAmount <= 0) {
            System.out.println("\n❌ Erro: O valor da entrada não pode ser maior ou igual ao valor do bem.");
            return;
        }


        double monthlyInterestRate = Math.pow(1 + (annualInterestRate / 100), 1.0 / 12.0) - 1;


        double installment = fundedAmount *
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months)) /
                (Math.pow(1 + monthlyInterestRate, months) - 1);

        double totalPaid = installment * months;
        double totalInterest = totalPaid - fundedAmount;

        // Exibição dos Resultados de forma profissional
        System.out.println("\n=========================================");
        System.out.println("          RESULTADO DA SIMULAÇÃO         ");
        System.out.println("=========================================");
        System.out.printf("Valor do Bem:          %s\n", currencyFormat.format(assetValue));
        System.out.printf("Entrada:               %s\n", currencyFormat.format(downPayment));
        System.out.printf("Valor Financiado:      %s\n", currencyFormat.format(fundedAmount));
        System.out.printf("Prazo:                 %d meses\n", months);
        System.out.printf("Taxa de Juros Mensal:  %.2f%%\n", monthlyInterestRate * 100);
        System.out.println("-----------------------------------------");
        System.out.printf("VALOR DA PARCELA:      %s/mês\n", currencyFormat.format(installment));
        System.out.printf("Total de Juros Pagos:  %s\n", currencyFormat.format(totalInterest));
        System.out.printf("CUSTO TOTAL DO BEM:    %s\n", currencyFormat.format(totalPaid + downPayment));
        System.out.println("=========================================");

        scanner.close();
    }
}
