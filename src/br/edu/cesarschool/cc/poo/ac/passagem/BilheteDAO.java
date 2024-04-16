package br.edu.cesarschool.cc.poo.ac.passagem;
import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
public class BilheteDAO {

	private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);
	private String obterIdUnico(Bilhete bilhete) {
		return bilhete.gerarNumero();
	}
	public Bilhete buscar(String numeroBilhete) {		//XXXXXX Bilhete buscar
		return (Bilhete)cadastro.buscar(numeroBilhete); //XXXXXX (Bilhete) cadastro
	}
	/*
	 * Objetos são incluídos em arquivos físicos, gravados
	 * no diretório Cliente (o nome da classe de entidade), que fica
	 * no mesmo nível do diretório src, dentro do projeto JAVA.
	 * Cada objeto é incluído em um arquivo diferente, cujo nome
	 * é o id único do objeto em questão, e tem a extensão dat.
	 */
	public boolean incluir(Bilhete bilhete) {
		String idUnico = obterIdUnico(bilhete);
		Bilhete bil = buscar(idUnico);
		if (bil == null) {
			cadastro.incluir(bilhete, idUnico);
			return true;
		}
		return false;
	}
	public boolean alterar(Bilhete bilhete) {
		String idUnico = obterIdUnico(bilhete);
		Bilhete bil = buscar(idUnico);
		if (bil != null) {
			cadastro.alterar(bilhete, idUnico);
			return true;
		}
		return false;
	}

	public boolean excluir(String numeroBilhete) {
		Bilhete bil = buscar(numeroBilhete);
		if (bil != null) {
			cadastro.excluir(numeroBilhete);
			return true;
		}
		return false;
	}
}


