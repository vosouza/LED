import java.io.*;

public class QuadraticEquationTest {

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      
      String input;
      double a, b, c, solution1, solution2;
      
      System.out.print("Valor de a: ");
      input = in.readLine();
      a = Double.parseDouble(input);
      
      System.out.print("Valor de b: ");
      input = in.readLine();
      b = Double.parseDouble(input);
      
      System.out.print("Valor de c: ");
      input = in.readLine();
      c = Double.parseDouble(input);
      
      System.out.println("a = " + a + ", b = " + b + ", c = " + c);
         
      QuadraticEquation quad = new QuadraticEquation(a, b, c);

      if (a == 0)
         System.out.println("Não é uma equação quadrática.");
      else if (quad.hasSolution() == false)
         System.out.println("Não possui solução.");
      else {
         solution1 = quad.calculateSolution1();
         solution2 = quad.calculateSolution2();
         System.out.println("Solução 1 = " + solution1);
         System.out.println("Solução 2 = " + solution2);
      }
   }
}