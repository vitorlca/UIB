package br.unit.uib;

import static br.unit.uib.Constantes.*;

import java.util.Scanner;

import br.unit.uib.entidades.Cliente;
import br.unit.uib.entidades.Conta;
import br.unit.uib.util.SenhaUtil;


public class UIBMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner leteclado = new Scanner(System.in);
		
		
		Conta[] contas = new Conta[TOTAL_CONTAS];
		
		int indice = 0;
		int opcao = -1;
		
		System.out.println("Bem vindo ao UIB!");
		System.out.println("-----------------");
		
		do {
			imprimeMenu();
			
			opcao = leteclado.nextInt();
			
			switch (opcao) {
			case ABRIR_CONTA: {
				Conta conta = montaConta();
				contas[indice] = conta;
				System.out.println("O numero da sua conta �: " + conta.getNumero());
				indice++;
				break;
			}case CONSULTAR_SALDO: {
				Conta conta = buscarConta(contas);
				if(conta != null) {
					System.out.println(conta.getSaldoDaConta());
				}
				break;
			}case CREDITAR: {
				System.out.println("Digite o valor � creditar:");
				double valor = leteclado.nextDouble();
				
				Conta conta = buscarConta(contas);
				if(conta != null) {
					conta.creditar(valor);
				}
				
				break;
			}case DEBITAR: {
				System.out.println("Digite o valor � debitar:");
				double valor = leteclado.nextDouble();
				
				Conta conta = buscarConta(contas);
				if(conta != null) {
					conta.debitar(valor);
				}
				
				break;
			}case TRANSFERIR: {
				System.out.println("Digite o valor � transferir:");
				double valor = leteclado.nextDouble();
				
				Conta conta = buscarConta(contas);
				if(conta != null) {
					Conta contaDestino = buscarContaDestino(contas);
					if(contaDestino != null) {
						conta.transferir(contaDestino, valor);
					}
				}
				
				break;
			}case SAIR: {
				System.out.println("Obrigado pela preferencia!");
				System.out.println("--------------------------");
				break;
			}
			default:
				System.out.println("comando invalido! Selecione novamente");
				break;
			}
			
		}while(opcao != SAIR);
		
	}
	
	private static void imprimeMenu() {
		System.out.println("[1] - Abrir Conta\r\n" + 
				"[2] - consulta saldo\r\n" + 
				"[3] - creditar em conta\r\n" + 
				"[4] - debitar em conta\r\n" + 
				"[5] - Transferir\r\n" + 
				"[6] - sair");
	}
	
	private static Cliente montaCliente() {
		Scanner leteclado = new Scanner(System.in);
		
		System.out.println("Digite o nome do Cliente:");
		String nome = leteclado.next();
		
		System.out.println("Digite o CPF do Cliente:");
		String cpf = leteclado.next();
		
		Cliente cliente = new Cliente(nome, cpf);
		
		return cliente;
	}
	
	private static Conta montaConta() {
		Scanner leteclado = new Scanner(System.in);
		Cliente cliente = montaCliente();
		
		System.out.println("Digite o sua senha:");
		String senha = leteclado.next();
		String senhaHash = SenhaUtil.gerahash(senha);
		
		System.out.println("Digite o valor do deposito inicial:");
		double saldoInicial = leteclado.nextDouble();
		
		String numero = Conta.gerarNumero();

		Conta conta = new Conta(numero, senhaHash, saldoInicial, cliente);

		return conta;
	}
	
	private static Conta buscarConta(Conta[] contas) {
		Scanner leteclado = new Scanner(System.in);
		
		System.out.println("Digite o numero da conta:");
		String numero = leteclado.next();
		
		System.out.println("Digite o sua senha:");
		String senha = leteclado.next();
		String senhaHash = SenhaUtil.gerahash(senha);
		
		for (Conta conta : contas) {
			if(conta != null) {
				if(conta.getNumero().equals(numero)//
						&& conta.getSenha().equals(senhaHash)) {
					return conta;
				}
			}
		}
		System.out.println("Conta "+ numero + "n�o encontrada!");
		return null;
		
		
	}
	
	private static Conta buscarContaDestino(Conta[] contas) {
		Scanner leteclado = new Scanner(System.in);
		
		System.out.println("Digite o numero da conta de destino:");
		String numero = leteclado.next();
		
		
		for (Conta conta : contas) {
			if(conta != null) {
				if(conta.getNumero().equals(numero)) {
					return conta;
				}
			}
		}
		System.out.println("Conta "+ numero + "n�o encontrada!");
		return null;
		
		
	}
}


