package br.edu.cesarschool.cc.poo.ac.passagem;
import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
public class BilheteVipDAO {
	
	private CadastroObjetos cadastro = new CadastroObjetos(BilheteVip.class);
	private String obterIdUnico(BilheteVip bilheteVip) {
		return bilheteVip.gerarNumero();
	}
	public BilheteVip buscar(String numeroBilheteVip) { 		//XXXXXX BilheteVip buscar
		return (BilheteVip)cadastro.buscar(numeroBilheteVip);  //XXXXXX (BilheteVip) Vipcadastro
	}
	/*
	 * Objetos são incluídos em arquivos físicos, gravados
	 * no diretório Cliente (o nome da classe de entidade), que fica
	 * no mesmo nível do diretório src, dentro do projeto JAVA.
	 * Cada objeto é incluído em um arquivo diferente, cujo nome
	 * é o id único do objeto em questão, e tem a extensão dat.
	 */
	public boolean incluir(BilheteVip bilheteVip) {
		String idUnico = obterIdUnico(bilheteVip);
		BilheteVip bilVip = buscar(idUnico);
		if (bilVip == null) {
			cadastro.incluir(bilheteVip, idUnico);
			return true;
		}
		return false;
	}
	public boolean alterar(BilheteVip bilheteVip) {
		String idUnico = obterIdUnico(bilheteVip);
		BilheteVip bilVip = buscar(idUnico);
		if (bilVip != null) {
			cadastro.alterar(bilheteVip, idUnico);
			return true;
		}
		return false;
	}

	public boolean excluir(String numeroBilheteVip) {
		BilheteVip bilVip = buscar(numeroBilheteVip);
		if (bilVip != null) {
			cadastro.excluir(numeroBilheteVip);
			return true;
		}
		return false;
	}
}

