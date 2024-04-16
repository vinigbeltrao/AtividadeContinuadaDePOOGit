package br.edu.cesarschool.cc.poo.ac.passagem;
import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
public class VooDAO {

	private CadastroObjetos cadastro = new CadastroObjetos(Voo.class);
	private String obterIdUnico(Voo voo) {
		return voo.obterIdVoo();
	}
	public Voo buscar(String idVoo) {
		return (Voo)cadastro.buscar(idVoo);
	}
	/*
	 * Objetos são incluídos em arquivos físicos, gravados
	 * no diretório Cliente (o nome da classe de entidade), que fica
	 * no mesmo nível do diretório src, dentro do projeto JAVA.
	 * Cada objeto é incluído em um arquivo diferente, cujo nome
	 * é o id único do objeto em questão, e tem a extensão dat.
	 */
	public boolean incluir(Voo voo) {
		String idUnico = obterIdUnico(voo);
		Voo v = buscar(idUnico);
		if (v == null) {
			cadastro.incluir(voo, idUnico);
			return true;
		}
		return false;
	}
	public boolean alterar(Voo voo) {
		String idUnico = obterIdUnico(voo);
		Voo v = buscar(idUnico);
		if (v != null) {
			cadastro.alterar(voo, idUnico);
			return true;
		}
		return false;
	}

	public boolean excluir(String idVoo) {
		Voo v = buscar(idVoo);
		if (v != null) {
			cadastro.excluir(idVoo);
			return true;
		}
		return false;
	}
}




