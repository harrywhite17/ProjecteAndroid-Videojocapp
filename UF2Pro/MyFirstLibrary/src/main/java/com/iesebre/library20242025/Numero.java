package com.iesebre.library20242025;

import java.util.Scanner;

public class Numero {

    /**
     * Genera un número enter inferior al que rebem com a paràmetre i major o igual que 0.
     * @param text un text que diu el que hem de fer
     * @param num el valor superior a introduir
     * @return retorna un enter entre 0 i num menys 1
     */
    public static int demanaValor(String text, int num){
        Scanner entrada = new Scanner(System.in);
        int valor;
        do {
            System.out.format("\nIntroduix %s a consultar (0-%d):\n", text, num-1);
            valor = entrada.nextInt();
        } while (valor < 0 || valor >= num);

        return valor;
    }

    /**
     * Calcula la suma dels números naturals fins al número donat.
     * @param n el número fins al qual es calcula la suma
     * @return la suma dels números naturals fins a n, o -1 si n és negatiu
     */
    public static int sumaNaturals(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 0;
        } else {
            return n + sumaNaturals(n - 1);
        }
    }


    public static int[] vectorDigits(int numero) {

        boolean negatiu = numero < 0;

        if (negatiu) {
            numero *= -1;
        }

        int[] resultat = new int[Integer.toString(numero).length()];

        int index = resultat.length - 1;

        while (numero > 0) {
            resultat[index] = numero % 10;
            index--;
            numero /= 10;
        }

        if (negatiu) {
            resultat[0] *= -1;
        }
        return resultat;

    }

    public static int numVector(int[] digits) {

        int index = digits.length - 1;
        int pot10=1;
        int resultat = 0;

        while (digits[index] == 0) {
            resultat += digits[index] * pot10;
            index--;
            pot10*=10;
        }
        return resultat;
    }

    public static double potenciaRec(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent > 0) {
            return base * potenciaRec(base, exponent - 1);
        } else {
            return 1 / potenciaRec(base, -exponent);
        }
    }
}
