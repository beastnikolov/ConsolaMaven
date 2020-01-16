
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {
    public String inicializar() {
        String buzon = "";
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);
        try {
            buzon = teclado.readLine();
        } catch (Exception e) {
            System.out.append("Entrada incorrecta.\n Torna a provar: ");
            return inicializar();
        } return buzon;
    }

    public int entero() {
        int valor=0;
        try {
            valor = Integer.parseInt(inicializar());
        } catch (Exception e) {
            System.out.append("Numero enter incorrecte.\nTorna a provar: ");
            return entero();
        }
        return valor;
    }

    public double real() {
        double valor=0;
        try {
            valor = Double.parseDouble(inicializar());
        } catch (Exception e) {
            System.out.append("Numero Real incorrecte.\nTorna a provar: ");
            return real();
        }
        return valor;
    }

    public String cadena() {
        String valor = inicializar();
        return valor;
    }

    public char caracter() {
        String valor = inicializar();
        return valor.charAt(0);
    }
}

