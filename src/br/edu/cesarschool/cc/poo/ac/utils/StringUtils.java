package br.edu.cesarschool.cc.poo.ac.utils;

public class StringUtils {
	private StringUtils() {
		
	}
	public static boolean isVaziaOuNula(String stringRecebida) {
		if (stringRecebida.trim() == "") {
			return true;
		}
		else {
			return false;
		}	
	}
	public static boolean checarCaracteres(String stringRecebida) {
        if (!isVaziaOuNula(stringRecebida) && stringRecebida.trim().length() >= 2) {
            return true;
        } else {
            return false;
        }
    }
	
}
