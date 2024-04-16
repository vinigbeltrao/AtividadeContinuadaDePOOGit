package br.edu.cesarschool.cc.poo.ac.cliente;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;
import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
public class ClienteMediator {
	private static ClienteMediator instancia;
	private ClienteMediator(){
	}
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	public Cliente buscar(String cpf) {
		return clienteDAO.buscar(cpf);
	}
	
	public String validar(Cliente cliente) {
		if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
			return "CPF errado";
		}
		if (!StringUtils.checarCaracteres(cliente.getNome())) {
			return "nome errado";
		}
		if (!(cliente.getSaldoPontos()>=0)) {
			return "saldo errado";
		}
		else {
			return null;
		}
	}
	public String incluir(Cliente cliente) {
		if (validar(cliente)!=null) {
			return null;				//CHECAR "RETURN NULL" ESTA ESCRITO "RETORNA-LA"???
		}
		else {
			if (!clienteDAO.incluir(cliente)) {
				return "Cliente ja existente";
			}
			else {
				return null;
			}
		}
	}
	public String alterar(Cliente cliente) {
		if (validar(cliente)!=null) {
			return null;				//CHECAR "RETURN NULL" ESTA ESCRITO "RETORNA-LA"???
		}
		else {
			if(!clienteDAO.alterar(cliente)) {
				return "Cliente inexistente";
			}
			else {
				return null;
			}
		}
	}
	public String excluir(String cpf) {
		if(!ValidadorCPF.isCpfValido(cpf)) {
			return "CPF errado";
		}
		else {
			if(!clienteDAO.excluir(cpf)) {
				return "Cliente inexistente";
			}
			else {
				return null;
			}
		}
	}
	
	//metodo singleton
	public static ClienteMediator obterInstancia() {
		if (instancia == null) {
			instancia = new ClienteMediator();
		}
		return instancia;
	}
}

