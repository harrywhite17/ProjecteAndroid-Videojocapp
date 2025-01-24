package com.iesebre.library20242025;

import java.util.Random;
import java.util.Scanner;

public class Data {

    /**
     * Diu si l'any que rep com a paràmetre és o no bixest (any de traspàs)
     * @param any el que volem avaluar
     * @return true si any és bixest i false en cas contrari
     */
    public static boolean esBixest(int any){

        return any>=1582 && (any%4==0 && any%100!=0 || any%400==0);
    }

    /**
     * Indica si la data rebuda és o no correcta
     * @param dia un número que representa el dia de la data
     * @param mes un número que representa el mes de la data
     * @param any un número que representa l'any de la data
     * @return true si la data és correcta i false en cas contrari
     */
    public static boolean dataCorrecta(int dia, int mes, int any){
        //Vector que conté la durada dels 12 mesos d'un any
        int[] mesos={31,28,31,30,31,30,31,31,30,31,30,31};

        //Mirem si l'any és bixest per modificar la durada del febrer
        if(esBixest(any)) mesos[1]=29;

        //Mirem si el més correcte
        if(mes<1 || mes>12) return false;

        //Mirem si el dia és correcte
        return dia>=1 && dia<=mesos[mes-1];
    }
    public static void mostraVarisCaracters(int numCaracters){

        for (int i = 0; i < numCaracters; i++) {
            System.out.format("%c", retornaCaracter());
        }
    }

    public static char retornaCaracter() {
        //Generem un caracter aleatori entre ' ' i '}'
        Random r=new Random();
        return (char)(r.nextInt('}'-' '+1)+' ');
    }

    public static int obtenirEnterMajorOIgual(String instruccions, String informacioSiIncorrecte, int valorInicialMinim) {
        Scanner ent = new Scanner(System.in);
        int numCaracters;
        do{
            System.out.println(instruccions);
            numCaracters= ent.nextInt();
            if(numCaracters<valorInicialMinim) System.out.println(informacioSiIncorrecte);
            else break;
        }while(true);
        return numCaracters;
    }
    public static int[] tractaParametres(int... params){
        //Tractament de casos "especials"
        if(params==null) return null;
        if(params.length==0) return null;

        //Tractament de vectors en caselles
        int suma, max, min;

        max=min=suma=params[0];     //tractem la primera casella
        //Recorrem el vector a partir de la segona casella
        for (int i = 1; i < params.length; i++) {
            //Actualitzem el màxim si cal
            if(params[i]>max) max=params[i];
            //Actualitzem el mínim si cal
            if(params[i]<min) min=params[i];
            //Sumen el valor de la casella actual
            suma+=params[i];
        }

        return new int[]{suma,params.length,max,min};
    }
    /**
     * Obtenim la data de demà
     * @param dia un número que representa el dia de la data
     * @param mes un número que representa el mes de la data
     * @param any un número que representa l'any de la data
     * @return Un vector d'enters contenint el dia, mes i any de demà si la data rebuda és correcta, sinó retornem null
     */
    public static int[] diaSeguent(int dia, int mes, int any){
        if(!dataCorrecta(dia,mes,any)) return null;

        if(dataCorrecta(dia+1,mes,any)) return new int[]{dia+1, mes ,any};
        if(dataCorrecta(1,mes+1,any)) return new int[]{1, mes+1 ,any};
        return new int[]{1,1,any+1};
    }


    public static int comparaDates(int dia1, int mes1, int any1, int dia2, int mes2, int any2) {
        if (!dataCorrecta(dia1, mes1, any1) || !dataCorrecta(dia2, mes2, any2)) {
            return -2;
        }

        if (any1 > any2 || (any1 == any2 && mes1 > mes2) || (any1 == any2 && mes1 == mes2 && dia1 > dia2)) {
            return 1;
        } else if (any1 == any2 && mes1 == mes2 && dia1 == dia2) {
            return 0;
        } else {
            return -1;
        }
    }


    public static int diferenciaDies(int dia1, int mes1, int any1, int dia2, int mes2, int any2) {
        if (!dataCorrecta(dia1, mes1, any1) || !dataCorrecta(dia2, mes2, any2)) {
            return -1;
        }

        switch (comparaDates(dia1, mes1, any1, dia2, mes2, any2)) {
            case 0:
                return 0;
            case 1:
                int temp=dia1;
                dia1=dia2;
                dia2=temp;
                temp=mes1;
                mes1=mes2;
                mes2=temp;
                temp=any1;
                any1=any2;
                any2=temp;

            case -1:
                int dies = 0;
                while (any1 < any2 || mes1 < mes2 || dia1 < dia2) {
                    dia1++;
                    dies++;
                    if (dia1 > 31) {
                        dia1 = 1;
                        mes1++;
                        if (mes1 > 12) {
                            mes1 = 1;
                            any1++;
                        }
                    }
                }
                return dies;
            default:
                return -1;
        }


    }


}
