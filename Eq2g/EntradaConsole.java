import java.util.Scanner;

/**
 * Escreva a descrição da classe EntradaConsole aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class EntradaConsole implements IEntrada
{
    Scanner scan = new Scanner( System.in );

    public double lerParametroA(){ 
        System.out.print("Forneca Parametro a:");
        return scan.nextDouble();
    }
    public double lerParametroB(){
        System.out.print("Forneca Parametro b:");
        return scan.nextDouble();
    }
    public double lerParametroC(){
        System.out.print("Forneca Parametro c:");
        return scan.nextDouble();
    }
}
