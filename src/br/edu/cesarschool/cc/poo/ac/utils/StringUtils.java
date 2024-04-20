package br.edu.cesarschool.cc.poo.ac.utils;

public class StringUtils {
	private StringUtils() {
		
	}
	public static boolean isVaziaOuNula(String stringRecebida) {
	    if (stringRecebida == null) {
	        return true;
	    }
	    if (stringRecebida.trim().isEmpty()) {
	        return true;
	    }
	    return false;
	}

	public static boolean checarCaracteresCliente(String stringRecebida) {
        if (!isVaziaOuNula(stringRecebida) && stringRecebida.trim().length() >= 2) {
            return true;
        } else {
            return false;
        }
    }
	public static boolean checarCaracteresCIA(String stringRecebida) {
        if (!isVaziaOuNula(stringRecebida) && stringRecebida.trim().length() == 2) {
            return true;
        } else {
            return false;
        }
    }
	
}
