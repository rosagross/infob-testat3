package testat3;

public class Calculator {

	/**
	 * Fehlerbehebung fuer args:
	 * args muss genau 3 argumente haben
	 * Eintrag args[0] muss ein bruch sein und args[1] muss ein bruch oder faktor sein.
	 * 
	 * @param args die Argumente, die der User beim Programmstart eingibt
	 */
	public static void main(String[] args) {
		
		if(args.length != 3) {
			System.err.println("Es wurden nicht 3 Argumente eingegeben!"
					+ " (Bsp. Eingabe: 1/3 \"*\" 1/4) oder 1/3 + 1/4");
		}else if(checkInput(args)){
			
			//Wenn die Eingabe korrekt war, dann muessen die brueche in Fraction types umgewandelt werden
			Fraction term1 = Fraction.parseFraction(args[0]);
			Fraction term2 = Fraction.parseFraction(args[2]);
			
			// Schliesslich muss die jeweilige Rechenoperation durchgefuehrt werden
			switch(args[1]) {
			case ("+"):
				printSolution(term1.add(term2));
				break;
			case ("*"):
				printSolution(term1.multiply(term2));
				break;
			case ("-"):
				printSolution(term1.subtract(term2));
				break;
			case ("/"):
				printSolution(term1.divide(term2));
				break;
			}
		}
	}
	
	/**
	 * Diese Methode gibt die Loesung der Aufgabe aus und ueberprueft, ob
	 * eine 0 oder eine ganze Zahl herausgekommen ist.
	 * @param solution der aus der Berechnung reultierte Bruch vom Typ Fraction
	 */
	public static void printSolution(Fraction solution) {
		System.out.print("Das Ergebnis ist: ");
		if(solution.getNumerator() == 0) {
			System.out.println(0);
		}else if(solution.getDenominator() == solution.getNumerator()) {
			System.out.println(1);
		}else if(solution.getDenominator() == 1) {
			System.out.println(solution.getNumerator());
		}else {
			System.out.println(solution.toString());
		}
	}
	
	/**
	 * Diese Methode prueft, ob der String aus einer korrekten Rechenanweisung besteht, die nach dem Muster
	 * (1/3 "*" 1/4) erfolgen sollte.
	 * @param calcInput der Input, der vom User eingegeben wird.
	 * @return false, wenn der Input nicht korrekt ist. Ist er korrekt, wird true zurueck gegeben.
	 */
	public static boolean checkInput(String[] calcInput) {
		return checkFraction(calcInput[0]) && checkOperator(calcInput[1]) && checkFraction(calcInput[2]);
	}	
	
	/**
	 * In dieser Methode wird geprueft, ob ein korrekter Operator eingegeben wurde.
	 * 
	 * @param operation eine Rechenanweisung
	 * @return Wahrheitswert ueber Korrektheit
	 */
	private static boolean checkOperator(String operation) {
		String rechenzeichen = "\\+|-|/|\\*";
		
		if(!operation.matches(rechenzeichen)) {
			System.err.println("Fehler! Kein korrektes Rechenzeichen. Moegliche Rechenoperationen"
					+ " sind: \"*\", /, + und -.");
			return false;
		}
		return true;
	}

	/**
	 * Diese Methode preuft, ob ein korrekter Bruch eingeben wurde.
	 * @param fraction der fragliche Bruch
	 * @return Wahrheitswert ueber Korrektheit
	 */
	public static boolean checkFraction(String fraction) {
		String zahl = "-?\\d++";
		String bruch = zahl + "/-?[1-9]++";
		
		if(!fraction.matches(bruch)) {
			System.err.println("Fehler! Bitte einen gueltigen Bruch eingeben. Eine korrekte Eingabe"
					+ "waere zum Beispiel: 1/2 oder -1/2.");
			if(fraction.matches(zahl + "/" + zahl)) {
				System.err.println("Es darf nicht durch 0 geteilt werden!");
			}
			return false;
		}
		return true;
	}
}
