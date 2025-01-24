package com.iesebre.library20242025;

class Temps {
    private int dia;
    private int mes;
    private int any;
    private int hora;
    private int minut;
    private int segon;
    private boolean dataHora;

    public Temps() {
        this.dataHora = false; // Default value
    }

    public Temps(int dia, int mes, int any, int hora, int minut, int segon, boolean dataHora) {
        this.dia = dia;
        this.mes = mes;
        this.any = any;
        this.hora = hora;
        this.minut = minut;
        this.segon = segon;
        this.dataHora = dataHora;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAny() {
        return any;
    }

    public int getHora() {
        return hora;
    }

    public int getMinut() {
        return minut;
    }

    public int getSegon() {
        return segon;
    }

    public boolean isDataHora() {
        return dataHora;
    }

    // Setters
    public boolean setDia(int dia) {
        if (dia < 1 || dia > 31) {
            System.out.println("Invalid dia: " + dia);
            return false;
        }
        this.dia = dia;
        return true;
    }

    public boolean setMes(int mes) {
        if (mes < 1 || mes > 12) {
            System.out.println("Invalid mes: " + mes);
            return false;
        }
        this.mes = mes;
        return true;
    }

    public boolean setAny(int any) {
        if (any < 0) {
            System.out.println("Invalid any: " + any);
            return false;
        }
        this.any = any;
        return true;
    }

    public boolean setHora(int hora) {
        if (hora < 0 || hora >= 23) {
            System.out.println("Invalid hour: " + hora);
            return false;
        }
        this.hora = hora;
        return true;
    }

    public boolean setMinut(int minut) {
        if (minut < 0 || minut >= 59) {
            System.out.println("Invalid minute: " + minut);
            return false;
        }
        this.minut = minut;
        return true;
    }

    public boolean setSegon(int segon) {
        if (segon < 0 || segon >= 59) {
            System.out.println("Invalid second: " + segon);
            return false;
        }
        this.segon = segon;
        return true;
    }

    public void setDataHora(boolean dataHora) {
        this.dataHora = dataHora;
    }

    public void incrementaTemps(int segons) {
        this.segon += segons;
        while (this.segon >= 60) {
            this.segon -= 60;
            this.minut++;
        }
        while (this.minut >= 60) {
            this.minut -= 60;
            this.hora++;
        }
        while (this.hora >= 24) {
            this.hora -= 24;
            this.dia++;
        }
    }

    public void mostraTemps() {
        System.out.println("Temps{" +
                "dia=" + dia +
                ", mes=" + mes +
                ", any=" + any +
                ", hora=" + hora +
                ", minut=" + minut +
                ", segon=" + segon +
                ", dataHora=" + dataHora +
                '}');
    }

    public void mostra() {
        if (dataHora) return;
        String.format("%02d/%02d/%04d %02d:%02d:%02d", dia, mes, any, hora, minut, segon);
    }

    public void canviaTemps(int dia, int mes, int any, int hora, int minut, int segon) {
        this.dia = dia;
        this.mes = mes;
        this.any = any;
        this.hora = hora;
        this.minut = minut;
        this.segon = segon;
    }

    public static boolean esBixest(int any) {
        return any >= 1582 && (any % 4 == 0 && any % 100 != 0 || any % 400 == 0);
    }

    public static boolean dataCorrecta(Temps temps) {
        int dia = temps.dia;
        int mes = temps.mes;
        int any = temps.any;

        int[] mesos = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (esBixest(any)) mesos[1] = 29;

        if (mes < 1 || mes > 12) return false;

        return dia >= 1 && dia <= mesos[mes - 1];
    }

    public static Temps diaSeguent(Temps temps) {
        if (!dataCorrecta(temps)) return null;

        int dia = temps.dia;
        int mes = temps.mes;
        int any = temps.any;

        if (dataCorrecta(new Temps(dia + 1, mes, any, 0, 0, 0, false))) {
            return new Temps(dia + 1, mes, any, 0, 0, 0, false);
        }
        if (dataCorrecta(new Temps(1, mes + 1, any, 0, 0, 0, false))) {
            return new Temps(1, mes + 1, any, 0, 0, 0, false);
        }
        return new Temps(1, 1, any + 1, 0, 0, 0, false);
    }

    public static int comparaDates(Temps temps1, Temps temps2) {
        if (!dataCorrecta(temps1) || !dataCorrecta(temps2)) {
            return -2;
        }

        if (temps1.any > temps2.any || (temps1.any == temps2.any && temps1.mes > temps2.mes) || (temps1.any == temps2.any && temps1.mes == temps2.mes && temps1.dia > temps2.dia)) {
            return 1;
        } else if (temps1.any == temps2.any && temps1.mes == temps2.mes && temps1.dia == temps2.dia) {
            return 0;
        } else {
            return -1;
        }
    }

    public static int diferenciaDies(Temps temps1, Temps temps2) {
        if (!dataCorrecta(temps1) || !dataCorrecta(temps2)) {
            return -1;
        }

        if (comparaDates(temps1, temps2) == 1) {
            Temps temp = temps1;
            temps1 = temps2;
            temps2 = temp;
        }

        int dies = 0;
        while (comparaDates(temps1, temps2) != 0) {
            temps1 = diaSeguent(temps1);
            dies++;
        }
        return dies;
    }
}
class proves {
    public static void main(String[] args) {
        // Create an instance of Temps
        Temps temps = new Temps(15, 8, 2023, 14, 30, 45, true);

        // Test mostraTemps method
        System.out.println("Initial time:");
        temps.mostraTemps();

        // Test canviaTemps method
        System.out.println("\nChanging time to 1st January 2024, 12:00:00:");
        temps.canviaTemps(1, 1, 2024, 12, 0, 0);
        temps.mostraTemps();

        // Test incrementaTemps method
        System.out.println("\nIncrementing time by 3600 seconds (1 hour):");
        temps.incrementaTemps(3600);
        temps.mostraTemps();

        // Test setters
        System.out.println("\nTesting setters:");
        System.out.println("Set dia to 31: " + temps.setDia(31));
        System.out.println("Set mes to 12: " + temps.setMes(12));
        System.out.println("Set any to 2025: " + temps.setAny(2025));
        System.out.println("Set hora to 23: " + temps.setHora(23));
        System.out.println("Set minut to 59: " + temps.setMinut(59));
        System.out.println("Set segon to 59: " + temps.setSegon(59));
        temps.mostraTemps();

        // Test setDataHora method
        System.out.println("\nSetting dataHora to false:");
        temps.setDataHora(false);
        temps.mostraTemps();
    }
}