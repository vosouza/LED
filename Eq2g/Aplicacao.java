
/**
 * Escreva a descrição da classe Aplicacao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Aplicacao {
    public static void main(String args[]){
        double a = 0, b = 0, c = 0;
         int x = 5;
        
        IEntrada et = new EntradaGui();
        a = et.lerParametroA();
        b = et.lerParametroB();
        c = et.lerParametroC();
        
        // instancia um objeto da classe
        Eq2g eq = new Eq2g(a, b, c); 
        
        Eq2g vetEq[] = new Eq2g[x]; // cria vetor de x posicoes
        
    
    }
}
