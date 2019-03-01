
/**
 * Calculadora avancada herdada da Calculadora basica
 * Implementa novas operações e as 5 operaçoes basicas 
 * (+, -, *, /, %).
 * 
 * @author Prof. Julio Arakaki 
 * @version 1.0 (20190301)
 */
import java.lang.*;
public class CalculadoraAvancada extends CalculadoraBasica {   
    public double calcular(String op, double a){

        double res=0;

        switch(op) {
            case "sqrt":
            res = Math.sqrt(a);
            break;
            case "1/x":
            res = divisao(1, a);
            break;
            case "sin":
            a = Math.toRadians(a); 
            res = Math.sin(a);
            break;
            case "fatorial":
            int x = 1;
            for(int i = (int) a; i > 0; i--){
                x = x * (int)multiplicacao((double)x, (double)i);
            }
            res = x;
        }

        return res;
    }
}
