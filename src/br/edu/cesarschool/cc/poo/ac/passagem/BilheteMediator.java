package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;
import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import java.time.LocalDateTime;
public class BilheteMediator {
	private BilheteDAO bilheteDAO = new BilheteDAO();
	private BilheteVipDAO bilheteVipDAO = new BilheteVipDAO();
	private VooMediator vooMediator = VooMediator.obterInstancia();
	private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
	private static BilheteMediator instancia;
	private BilheteMediator(){
	}
	public Bilhete buscar(String numeroBilhete) {
		return bilheteDAO.buscar(numeroBilhete);
	}
	public BilheteVip buscarVip(String numeroBilhete) {
		return bilheteVipDAO.buscar(numeroBilhete);
	}
	public String validar(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
		if(!ValidadorCPF.isCpfValido(cpf)) {
			return "CPF errado";
		}
		String resultadoRecebido = vooMediator.validarCiaNumero(ciaAerea, numeroVoo);
		if(resultadoRecebido != null) {
			return resultadoRecebido;
		}
		if(!(preco>0)) {
			return "Preco errado";
		}
		if(!(pagamentoEmPontos>=0)) {
			return "Pagamento pontos errado";
		}
		if(!(preco>=pagamentoEmPontos)) {
			return "Preco menor que pagamento em pontos";
		}
		LocalDateTime agora = LocalDateTime.now();
        LocalDateTime umaHoraNoFuturo = agora.plusHours(1);
        if (dataHora.isBefore(umaHoraNoFuturo)) {
            return "data hora invalida";
        }
        
        return null;
	}
	public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        String mensagemErro = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
        if (mensagemErro != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemErro);
        }

        Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
        String idVoo = voo.obterIdVoo();
        Voo vooEncontrado = vooMediator.buscar(idVoo);
        if (vooEncontrado == null) {
            return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
        }

        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
        }

        double valorNecessarioPontos = pagamentoEmPontos * 20;
        if (cliente.getSaldoPontos() < valorNecessarioPontos) {
            return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
        }

        Bilhete bilhete = new Bilhete(cliente, vooEncontrado, preco, pagamentoEmPontos, dataHora);
        boolean inclusaoSucesso = bilheteDAO.incluir(bilhete);
        if (!inclusaoSucesso) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
        }
        
        cliente.debitarPontos(valorNecessarioPontos);
        cliente.creditarPontos(bilhete.obterValorPontuacao());

        String mensagemAlterarCliente = clienteMediator.alterar(cliente);
        if (mensagemAlterarCliente != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemAlterarCliente);
        }

        return new ResultadoGeracaoBilhete(bilhete, null, null);
    }

    public ResultadoGeracaoBilhete gerarBilheteVip(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {
        String mensagemErro = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
        if (mensagemErro != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemErro);
        }

        if (bonusPontuacao <= 0 || bonusPontuacao > 100) {
            return new ResultadoGeracaoBilhete(null, null, "Bonus errado");
        }

        Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
        String idVoo = voo.obterIdVoo();
        Voo vooEncontrado = vooMediator.buscar(idVoo);
        if (vooEncontrado == null) {
            return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
        }

        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
        }

        double valorNecessarioPontos = pagamentoEmPontos * 20;
        if (cliente.getSaldoPontos() < valorNecessarioPontos) {
            return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
        }

        BilheteVip bilheteVip = new BilheteVip(cliente, vooEncontrado, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
        boolean inclusaoSucesso = bilheteVipDAO.incluir(bilheteVip);
        if (!inclusaoSucesso) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
        }
        cliente.debitarPontos(valorNecessarioPontos);
        cliente.creditarPontos(bilheteVip.obterValorPontuacaoVip());

        String mensagemAlterarCliente = clienteMediator.alterar(cliente);
        if (mensagemAlterarCliente != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemAlterarCliente);
        }

        return new ResultadoGeracaoBilhete(null, bilheteVip, null);
    }
	
	public static BilheteMediator obterInstancia() {
		if (instancia == null) {
			instancia = new BilheteMediator();
		}
		return instancia;
	}
}
