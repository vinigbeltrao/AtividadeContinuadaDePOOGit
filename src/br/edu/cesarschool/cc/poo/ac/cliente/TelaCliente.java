package br.edu.cesarschool.cc.poo.ac.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TelaCliente {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));
    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();

    public void inicializaTelasCadastroCliente() {
        while (true) {
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            switch (opcao) {
                case 1:
                    processaInclusao();
                    break;
                case 2:
                    processaAlteracao();
                    break;
                case 3:
                    processaExclusao();
                    break;
                case 4:
                    processaBusca();
                    break;
                case 5:
                    System.out.println("Saindo do cadastro de clientes");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!!");
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir");
        System.out.println("2- Alterar");
        System.out.println("3- Excluir");
        System.out.println("4- Buscar");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }

    private void processaInclusao() {
        Cliente cliente = capturaCliente();
        String retorno = clienteMediator.incluir(cliente);
        if (retorno == null) {
            System.out.println("Cliente incluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void processaAlteracao() {
        System.out.print("Digite o CPF: ");
        String cpf = lerString();
        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
        } else {
            cliente = capturaCliente(cpf); // Re-use cpf, capture other details
            String retorno = clienteMediator.alterar(cliente);
            if (retorno == null) {
                System.out.println("Cliente alterado com sucesso!");
            } else {
                System.out.println(retorno);
            }
        }
    }

    private void processaExclusao() {
        System.out.print("Digite o CPF: ");
        String cpf = lerString();
        String retorno = clienteMediator.excluir(cpf);
        if (retorno == null) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void processaBusca() {
        System.out.print("Digite o CPF: ");
        String cpf = lerString();
        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
        } else {
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Saldo de Pontos: " + cliente.getSaldoPontos());
        }
    }

    private Cliente capturaCliente() {
        System.out.print("Digite o CPF: ");
        String cpf = lerString();
        return capturaCliente(cpf);
    }

    private Cliente capturaCliente(String cpf) {
        System.out.print("Digite o nome: ");
        String nome = lerString();
        System.out.print("Digite o saldo de pontos: ");
        double saldoPontos = ENTRADA.nextDouble();
        return new Cliente(cpf, nome, saldoPontos);
    }

    private String lerString() {
        try {
            return ENTRADA_STR.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
