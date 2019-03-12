public class QuadraticEquation {
   private double myA;     // a
   private double myB;     // b
   private double myC;     // c
   private double myDelta; // b*b - 4*a*c
   
   public QuadraticEquation(double a, double b, double c) {
      myA = a;
      myB = b;
      myC = c;
      calculateDelta();
   }
   
   public double calculateSolution1() {
      return (-myB + Math.sqrt(myDelta))/(2.0*myA);
   }
   
   public double calculateSolution2() {
      return (-myB - Math.sqrt(myDelta))/(2.0*myA);
   }
   
   public boolean hasSolution() {
      if (myDelta < 0) 
        return false;
      else 
        return true;
   }

   private void calculateDelta() {
      myDelta = myB*myB - 4.0*myA*myC;
   }
}