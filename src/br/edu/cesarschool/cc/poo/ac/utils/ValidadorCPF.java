package br.edu.cesarschool.cc.poo.ac.utils;

public class ValidadorCPF {
	private ValidadorCPF(){
		
	}
	public static boolean isCpfValido(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        if (cpf.length() != 11) {
            return false;
        }

        if (!cpf.matches("[0-9]+")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }

        // Verifica se o primeiro dígito verificador está correto
        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }

        // Verifica se o segundo dígito verificador está correto
        return Character.getNumericValue(cpf.charAt(10)) == secondDigit;
    }
}
