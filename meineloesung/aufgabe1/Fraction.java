package testat3;
/**
 * Every instance of <code>Fraction</code> represents a fraction with numerator
 * and denominator.
 *
 * @author Lars Huning 
 */
public class Fraction {

   /**
    * Creates greatest common divisor for a and b.
    *
    * @param a number
    * @param b number
    * @return the greatest common divisor for a and b.
    */
   public static int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
   }
   
   /**
    * This method calculates the smallest common multiple (kleinstes gemeinsames Vielfaches) of
    * two intergers, using the formula with the ggT.
    * @param a number
    * @param b number
    * @return the smalles common multiple 
    */
   public static int kgV(int a, int b) {
	   int gcd = gcd(a, b);
	   return Math.abs(a*b)/gcd;
   }
   
   /**
    * This method returns an object of type Fraction that is parsed by a String. It cecks if the 
    * String is a correct fraction.
    * @param fractionString from which the fraction is parsed
    * @return the fraction of type Fraction
    */
   public static Fraction parseFraction(String fractionString) {
	   int denominatorInt = 1;
	   int numeratorInt = 0;
	   
	   String zahl = "-?\\d++";
	   String bruch = zahl + "/-?[1-9]++";
	   
	   if(fractionString.matches(bruch)) {
		   String[] fractionArray = fractionString.split("/");
		   denominatorInt = Integer.parseInt(fractionArray[1]);
		   numeratorInt = Integer.parseInt(fractionArray[0]);
	   }else {
		   System.err.println("Der String ist kein korrekter Bruch!");
		   System.exit(-1);	   
	   }
	   return new Fraction(numeratorInt, denominatorInt);
   }
   
   /**
    * Numerator of the Fraction
    */
   private final int numerator;
   
   /**
    * Denominator of the Fraction
    */
   private final int denominator;

   /**
    * Creates a Fraction object with numerator and denominator 1, reduces the
    * fraction with creation.
    *
    * @param numerator the numerator of the fraction
    */
   public Fraction(int numerator) {
      this(numerator, 1);
   }

   /**
    * Creates a Fraction object with numerator and denominator. Reduces the 
    * fraction in the constructor. 
    * Denominator == 0 is not allowed. In such a case, the program terminates
    * with an error message
    *
    * @param numerator   the numerator
    * @param denominator the denominator, must not be 0.
    */
   public Fraction(int numerator, int denominator) {
      if (denominator == 0) {
          System.out.println("denominator == 0 is not possible");
          System.out.println("Terminating program");
          System.exit(-1);
      }

      /*
       * creates greatest common divisior.
       */
      int gcd = Fraction.gcd(numerator, denominator);
      
      /*
       * check sign, make denominator positive --> the sign of the fraction
       * is always stored only in the numerator. 
       */
      if (denominator / gcd < 0) {
         gcd *= -1;
      }

      this.numerator = numerator / gcd;

      this.denominator = denominator / gcd;
   }

   /**
    * Divides this Fraction with another Fraction. Terminates the program in 
    * case numerator of another is zero (via constructor of the newly created
    * Fraction).
    *
    * @param another Fraction to divide this Fraction by
    * @return Quotient as a new Fraction
    *
    */
   public Fraction divide(Fraction another) {
      return new Fraction(this.numerator * another.denominator,
            this.denominator * another.numerator);
   }
   
   /**
    * Note: "Default" getters and setters do not always require JavaDoc, 
    * as their purpose is obvious 
    * @return the denominator
    */
   public int getDenominator() {
      return this.denominator;
   }

   public int getNumerator() {
      return this.numerator;
   }

   /**
    * Multiplies this Fraction with another Fraction
    *
    * @param factor Fraction to multiply this Fraction with
    * @return The product as a new Fraction
    */
   public Fraction multiply(Fraction factor) {
      return new Fraction(this.numerator * factor.numerator, this.denominator
            * factor.denominator);
   }

   /**
    * Multiplies this Fraction with all other Fraction instances given by
    * the parameter factors
    *
    * @param factors Fraction instances to multiply this Fraction with
    * @return The product as a new Fraction
    */
   public Fraction multiply(Fraction... factors) {
      Fraction result = this;
      
      //variable parameters may be treated like an array inside the method
      for (int i = 0; i < factors.length; i++) {
         result = result.multiply(factors[i]);
      }
      return result;
   }

   /**
    * Multiplies this Fraction with int n.
    *
    * @param n factor to multiply with
    * @return The product as a new Fraction
    */
   public Fraction multiply(int n) {
      return new Fraction(this.numerator * n, this.denominator);
   }

   /**
    * Returns a string representation of this Fraction such as
    * numerator/denominator. As the constructor has already made sure that 
    * a negative Fraction is represented by a negative numerator and a positive
    * denominator, negative fractions are always displayed with the minus sign
    * at the numerator (Advantage: consistent notation, for example if anyone
    * decides to parse the results of this method with a string parser)
    *
    * @return This Fraction as a String
    */
   public String toString() {
      return numerator + "/" + denominator;
   }
   
   /**
    * This method adds two fractions. 
    * @param addend is the fraction that is added on the other fraction.
    * @return new fraction instance, which is the sum of the addition.
    */
   public Fraction add(Fraction addend) {
	   
	   //first bring both fractions on the same denominator
	   //so first find the kgV
	   int kgV = kgV(this.denominator, addend.denominator);
	   int multA = kgV/this.denominator;
	   int multB = kgV/addend.denominator;
	   int newNominator = this.multiply(multA).numerator + addend.multiply(multB).numerator;
	   return new Fraction(newNominator, multA*this.denominator);
   }
   
   /**
    * This method subtracts two fractions. 
    * @param subtrahend is the fraction that subtracts its value from the other fraction.
    * @return new fraction instance, which is the result of the subtraction.
    */
   public Fraction subtract(Fraction subtrahend) {
	   
	   //first bring both fractions on the same denominator
	   //so first find the kgV
	   int kgV = kgV(this.denominator, subtrahend.denominator);
	   int multA = kgV/this.denominator;
	   int multB = kgV/subtrahend.denominator;
	   int newNominator = this.multiply(multA).numerator - subtrahend.multiply(multB).numerator;
	   return new Fraction(newNominator, multA*this.denominator);
   }
}
