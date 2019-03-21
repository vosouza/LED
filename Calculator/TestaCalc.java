
/**
 * Aplicativo para testar as classes.
 * 
 * @author Prof. Julio Arakaki
 * @version 1.0
 */
public class TestaCalc {
    public static void main(String args[]){
        CalculadoraAvancada cb = new CalculadoraAvancada();
        
        double result1, result2, result3;
        
        result1 = cb.calcular(45, "/", 12);
        result2 = cb.calcular("1/x", 12);
        result3 = cb.calcular("fatorial", 5);
        
        System.out.println(45 + "/" + 12 + "=" + result1);
        System.out.println("1/" + 12 + "=" + result2);
        System.out.println("Fatorial" + 5 + "=" + result3);
    }
}
