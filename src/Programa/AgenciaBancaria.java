package Programa;

import java.util.ArrayList;
import java.util.Scanner;

public class AgenciaBancaria {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {

        System.out.println("-------------------------");
        System.out.println("-----Seja bem-vindo------");
        System.out.println("*Selecione uma operação*");
        System.out.println("-------------------------");
        System.out.println("| Opção 1 - Criar Conta  |");
        System.out.println("| Opção 2 - Depositar    |");
        System.out.println("| Opção 3 - Sacar        |");
        System.out.println("| Opção 4 - Transferir   |");
        System.out.println("| Opção 5 - Listar       |");
        System.out.println("| Opção 6 - Sair         |");
        //System.out.println("| Opção 7 - Alterar Conta|");

        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                sacar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                listarContas();
                break;
            case 6:
                System.out.println("Obrigado por usar a nossa agência");
                System.exit(0);
            default:
                System.out.println("Selecione uma opção válida");
                operacoes();
                break;
        }
    }
    //Método criar conta
    public static void criarConta() {
        System.out.println("Nome: ");
        String nome = input.next();

        System.out.println("CPF: ");
        String cpf = input.next();

        System.out.println("Email: ");
        String email = input.next();

        Cliente pessoa = new Cliente(nome, cpf, email);
        Conta conta = new Conta(pessoa);
        contasBancarias.add(conta);
        System.out.println("Sua conta foi criada com sucesso!");

        operacoes();
    }
    //Método encontrar conta
    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if(contasBancarias.size() > 0) {
            for(Conta c: contasBancarias) {
                if(c.getNumeroConta() == numeroConta) {
                    conta = c;
                }
            }
        }
        return conta;
    }

    //Método depositar
    public static void depositar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();
        Conta conta = encontrarConta(numeroConta);
        //validação da conta
        if(conta != null) {
            System.out.println("Qual valor deseja depositar? ");
            Double valorDeposito = input.nextDouble();
            conta.depositar(valorDeposito);
            System.out.println("Valor depositado com sucesso");
        }else {
            System.out.println("Conta não encontrada");
        }
        operacoes();
    }
    //Método sacar
    public static void sacar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);
        //validação da conta
        if(conta != null) {
            System.out.println("Qual valor deseja sacar? ");
            Double valorSaque = input.nextDouble();
            conta.sacar(valorSaque);
        }else {
            System.out.println("Conta não encontrada");
        }
        operacoes();
    }

    public static void transferir() {
        System.out.println("Número da conta do remetente: ");
        int numeroContaRemetente = input.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);
        //verificação
        if(contaRemetente != null) {
            System.out.println("Número da conta do destinatário: ");
            int numeroContaDestinario = input.nextInt();

            Conta contaDestinatario = encontrarConta(numeroContaDestinario);

            if(contaDestinatario != null) {
                System.out.println("Valor da transferência: ");
                Double valor = input.nextDouble();

                contaRemetente.transferir(contaDestinatario, valor);
            } else {
                System.out.println("A conta para depósito não foi encontrada");
            }
        } else {
            System.out.println("Conta para transferência não encontrada");
        }
        operacoes();
    }
    //Listar contas
    public static void listarContas() {
        if(contasBancarias.size() > 0) {
            for(Conta conta: contasBancarias) {
                System.out.println(conta);
            }
        } else {
            System.out.println("Não há contas cadastradas");
        }
        operacoes();
    }

}
