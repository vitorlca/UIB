package br.unit.uib.entidades;
import java.util.UUID;

public class Conta {
	//atributos
	private Cliente cliente;
	private String numero;
	private double saldoDaConta;
	private String senha;
	
	
	//construtores
	public Conta() {
	}
	
	public Conta(String numero, String senha, Double saldoDaConta, Cliente cliente) {
		this.saldoDaConta = saldoDaConta;
		this.numero = numero;
		this.senha = senha;
		this.cliente = cliente;
	}
	
	
	//metodos
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente; //o this e- um resolvedor de conflitos, ele vai se referir a um atributo que tem o msm nome dele
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public double getSaldoDaConta() {
		return saldoDaConta;
	}
	public void setSaldoDaConta(double saldoDaConta) {
		this.saldoDaConta = saldoDaConta;
	}
	
	public void creditar(double valor) {
		this.saldoDaConta = (this.saldoDaConta + valor);
	}
	
	public void debitar(double valor) {
		this.saldoDaConta = (this.saldoDaConta - valor);
	}
	public void transferir (Conta ct2, double valor) {
		debitar(valor);
		ct2.creditar(valor);	
	}
	
	public static String gerarNumero() {
		return UUID.randomUUID().toString();
	}
	
	public String toString() {
		return "Conta [cliente=" + cliente.getNome() 
		+ ", numero=" + numero  
		+ ", saldoDaConta=" + saldoDaConta;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
