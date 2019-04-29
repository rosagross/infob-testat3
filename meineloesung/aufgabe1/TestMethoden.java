package testat3;

public class TestMethoden {
	
	private static int numberErrors;
	/**
	 * Diese Methode prueft, ob eine Methode die Aufgabe richtig ausgefuehrt hat. Tritt ein Fehler auf, wird 
	 * der Fehler gemerkt.
	 * @param stimmts der zu pruefende Term
	 * @param methodenName der Name der Methode fuer die Ausgabe
	 */
	public void checkMethode(boolean stimmts, String methodenName) {
		if(stimmts) {
			System.out.println("\nDie Methode " + methodenName + " wurde erfolgreich getestet!");
		}else {
			System.out.println("Fehler in der Methode " + methodenName + "!");
			numberErrors++;
		}
	}
	
	/**
	 * Getter Methode, um auf die Anzahl der Fehler zugreifen zu koennen
	 * @return aktuelle Anzahl an Fehlern
	 */
	public static int getErrorNr() {
		return numberErrors;
	}
	
	/**
	 * Diese Methode gibt den aktuellen Stand an Fehlern waehrend des Tests aus
	 */
	public static void printErrorNr() {
		System.out.printf("Es sind %d Fehler waehrend des Testens aufgetreten", numberErrors);
	}
	
	/**
	 * Diese Methode check ob ein statement, das beim Testen ueberprueft wird, richtig ist. 
	 * Ist es true, kommt eine positive beliebige Message uber den Erfolg des Testens.
	 * Faellt das Ergebnis negativ aus, wird eine Standart Fehlernachricht ausgegeben.
	 * @param statement der zu Ueberpruefende Term
	 * @param goodMessage eine beliebige Nachricht, die den Erfolg des Testens mitteilt
	 */
	public static void checkStatement(boolean statement, String goodMessage) {
		if(statement) {
			System.out.println(goodMessage);
		}else {
			System.out.println("Term nicht korrekt!");
			numberErrors++;
		}
	}
	
	/**
	 *  Methode fuer das Uberpruefen von Integern
	 *  @param a Wert der ueberprueft werden soll
	 *  @param b der korrekte Wert  
	 */
	public static void assertInt(int a, int b) {
		if(a != b) {
			System.out.println("Fehler! es ist nicht der richtige Wert!");
			numberErrors++;
		}
	}
	
	/**
	 * Methode zum Uberpruefen von Boolean. Wenn Boolean false, wird eine Fehlernachricht
	 * ausgeprintet.
	 * @param b der zu ueberpruefende Wert
	 */
	public static void assertBoolean(boolean b) {
		if(!b) {
			System.out.println("Fehler! Boolean ist false!");
			numberErrors++;
		}
	}
}

