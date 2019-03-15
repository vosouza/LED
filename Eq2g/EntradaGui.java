import javax.swing.JOptionPane;

/**
 * Escreva a descrição da classe EntradaGui aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class EntradaGui implements IEntrada
{
    public double lerParametroA(){
        double a = Double.parseDouble(JOptionPane.showInputDialog("Forneca Parametro a"));
        return a;
    }
    public double lerParametroB(){
        double b = Double.parseDouble(JOptionPane.showInputDialog("Forneca Parametro b"));
        return b;
    }
    public double lerParametroC(){
        double c = Double.parseDouble(JOptionPane.showInputDialog("Forneca Parametro c"));
        return c;
    }
}
