/**
 * Calculadora que implementa as 5 operaçoes basicas (+, -, *, /, %).
 * 
 * @author Prof. Julio Arakaki 
 * @version 1.0 (20190301)
 */
public class CalculadoraBasica {
    /* Os atributos são necessários numa calculadora???
     * private double operando1;
     * private char operador;
     * private double operando2;
     */

    protected double adicao(double a, double b){
        return (a+b);
    }

    protected double subtracao(double a, double b){
        return (a-b);
    }

    protected double multiplicacao(double a, double b){
        return (a*b);
    }

    protected double divisao(double a, double b){
        return (a/b);
    }

    protected double resto(double a, double b){
        return (a%b);
    }

    public double calcular(double a, String op, double b){
        double res=0;

        switch (op) {
            case "+":
                res = adicao(a, b);
                break;
            case "-":
                res = subtracao(a, b);
                break;
            case "*":
                res = multiplicacao(a, b);
                break;
            case "/":
                res = divisao(a, b);
                break;
            case "%":
                res = resto(a, b);
                break;
        }
        return res;
    }
}
