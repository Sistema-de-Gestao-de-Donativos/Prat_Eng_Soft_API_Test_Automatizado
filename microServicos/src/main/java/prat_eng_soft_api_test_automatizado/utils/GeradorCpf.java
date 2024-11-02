package prat_eng_soft_api_test_automatizado.utils;

import java.util.Random;

public class GeradorCpf {

    public static String gerarCpf() {
        Random random = new Random();
        int[] cpf = new int[11];

        // Gerar os 9 primeiros dígitos
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcular o primeiro dígito verificador
        cpf[9] = calcularDigitoVerificador(cpf, 10);

        // Calcular o segundo dígito verificador
        cpf[10] = calcularDigitoVerificador(cpf, 11);

        // Formatar o CPF
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2],
                cpf[3], cpf[4], cpf[5],
                cpf[6], cpf[7], cpf[8],
                cpf[9], cpf[10]);
    }

    public static String gerarCpfSemFormatacao() {
        Random random = new Random();
        int[] cpf = new int[11];

        // Gerar os 9 primeiros dígitos
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcular o primeiro dígito verificador
        cpf[9] = calcularDigitoVerificador(cpf, 10);

        // Calcular o segundo dígito verificador
        cpf[10] = calcularDigitoVerificador(cpf, 11);

        // Formatar o CPF
        return String.format("%d%d%d%d%d%d%d%d%d%d%d",
                cpf[0], cpf[1], cpf[2],
                cpf[3], cpf[4], cpf[5],
                cpf[6], cpf[7], cpf[8],
                cpf[9], cpf[10]);
    }

    private static int calcularDigitoVerificador(int[] cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += cpf[i] * (pesoInicial - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

}
