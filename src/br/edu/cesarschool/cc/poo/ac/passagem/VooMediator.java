package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
public class VooMediator {
	private static VooMediator instancia;
	private VooDAO vooDAO = new VooDAO();
	private VooMediator(){
	}

	public Voo buscar(String IdVoo) {
		return vooDAO.buscar(IdVoo);
	}
	
	private static final String[] AEROPORTOS_VALIDOS = {
	        "GRU", "CGH", "GIG", "SDU", "REC", "CWB", "POA", "BSB", "SSA", "FOR", "MAO", "SLZ", "CNF", 
	        "BEL", "JPA", "PNZ", "CAU", "FEN", "SET", "NAT", "PVH", "BVB", "FLN", "AJU", "PMW", "MCZ", 
	        "MCP", "VIX", "GYN", "CGB", "CGR", "THE", "RBR", "VCP", "RAO"
	    };

	public String validar(Voo voo) {
		if (!isAeroportoValido(voo.getAeroportoOrigem())) {
			return "Aeroporto origem errado";
		}
		if (!isAeroportoValido(voo.getAeroportoDestino())) {
			return "Aeroporto destino errado";
		}
		if (voo.getAeroportoOrigem().equalsIgnoreCase(voo.getAeroportoDestino())) {
			return "Aeroporto origem igual a aeroporto destino";
		}
		
		return validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo());	
	    }
	public boolean isAeroportoValido(String aeroporto) {
		if (aeroporto != null) {
			for (String valido : AEROPORTOS_VALIDOS) {
				if (valido.equalsIgnoreCase(aeroporto.trim())) {
					return true;
				}
			}
		}
		return false;
	}
	public String validarCiaNumero(String companhiaAerea, int numeroVoo) {
		if (companhiaAerea == null || companhiaAerea.length()!= 2) {
			return "CIA aerea errada";
    	}

		if (!(numeroVoo >= 1000 && numeroVoo <= 9999)) {
			return "Numero voo errado";
		}
		
		return null;
	}
	
	public String incluir(Voo voo) {
		String resultadoValidacao = validar(voo);
		
		if (resultadoValidacao!=null) {
			return resultadoValidacao;
		}
		
		if(!vooDAO.incluir(voo)) {
			return "Voo ja existente";
		}
		return null;
	}
	public String alterar(Voo voo) {
		String resultadoValidacao = validar(voo);
		if (resultadoValidacao!=null) {
			return resultadoValidacao;
		}
		if(!vooDAO.alterar(voo)) {
			return "Voo inexistente";
		}
		return null;
	}
	public String excluir(String idVoo) {
		if(StringUtils.isVaziaOuNula(idVoo)) {
			return "Id voo errado";
		}
		if(!vooDAO.excluir(idVoo)) {
			return "Voo inexistente";
		}
		return null;
	}
	public static VooMediator obterInstancia() {
			if (instancia == null) {
				instancia = new  VooMediator();
			}
			return instancia;
		}
}
