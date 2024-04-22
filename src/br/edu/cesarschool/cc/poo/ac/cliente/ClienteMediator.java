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
		if (StringUtils.isVaziaOuNula(cliente.getNome()) || !(cliente.getNome().trim().length() >= 2)) {
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
		String resultadoValidacao = validar(cliente);
        if (resultadoValidacao != null) {
            return resultadoValidacao;
        }
        if (!clienteDAO.incluir(cliente)) {
        	return "Cliente ja existente";
        }
			
        return null;
			
		
	}
	public String alterar(Cliente cliente) {
		String resultadoValidacao = validar(cliente);
        if (resultadoValidacao != null) {
            return resultadoValidacao;
        }
		if(!clienteDAO.alterar(cliente)) {
			return "Cliente inexistente";
		}
		
		return null;
			
		
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

