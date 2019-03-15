
/**
 * Classe que representa uma equacao do 2o Grau.
 * 
 * @author Julio Arakaki 
 * @version 1.0 20180315
 */

public class Eq2g {
    
    // Atributos (variaveis de instancia)
    private double a, b, c;

    /**
     * @param a
     * @param b
     * @param c
     */
    public Eq2g(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @return the b
     */
    public double getB() {
        return b;
    }

    /**
     * @return the c
     */
    public double getC() {
        return c;
    }

    /**
     * @param a the a to set
     */
    private void setA(double a) {
        this.a = a;
    }

    /**
     * @param b the b to set
     */
    private void setB(double b) {
        this.b = b;
    }

    /**
     * @param c the c to set
     */
    private void setC(double c) {
        this.c = c;
    }

    /**
     * Calcula o valor de delta
     * @return valor de delta
     */
    private double calcularDelta() {
        return Math.pow(getB(),2)-(4.0*getA()*getC());
    }

    /**
     * Calcula valor da raiz 1
     * @return raiz 1
     */
    public double calcularX1(){
        double x1 = ((-1)*getB() + Math.sqrt(calcularDelta())/(2.0* getA())); 
    
        return x1;

    }

    /**
     * Calcula valor da raiz 2
     * @return raiz 2
     */
    public double calcularX2(){
        double x2 = ((-1)*getB() - Math.sqrt(calcularDelta())/(2.0* getA()));
       
        return x2;

    }
    
    public String toString() {
        return "";
    }
}
