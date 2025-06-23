package com.java.java.utils;

/**
 * Utility class for validating Brazilian CPF (Cadastro de Pessoas Físicas) numbers.
 */
public class CPFValidator {

    /**
     * Default constructor for CPFValidator.
     */
    public CPFValidator() {}

    /**
     * Validates a given CPF string.
     *
     * This method removes non-numeric characters, checks for correct length,
     * prevents sequences of repeated digits, and verifies both check digits
     * according to the CPF validation algorithm.
     *
     * @param cpf The CPF string to validate (may contain formatting like dots or dashes).
     * @return true if the CPF is valid; false otherwise.
     */
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = calcularDigito(soma);
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = calcularDigito(soma);
        return cpf.charAt(9) == Character.forDigit(primeiroDigito, 10) &&
                cpf.charAt(10) == Character.forDigit(segundoDigito, 10);
    }

    /**
     * Calculates a CPF check digit from the provided sum.
     *
     * @param soma The weighted sum of digits used to calculate the check digit.
     * @return The calculated check digit (0–9).
     */
    private static int calcularDigito(int soma) {
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

}
