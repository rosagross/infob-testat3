package identity;

public class TestIdentity {



	public static void main(String[] args) {

		TestIdentity test = new TestIdentity();
		System.out.println("Starte Tests...\n");
		System.out.println("Teste equals()...");
		test.testEquals();
		test.testHashCode();

		System.out.printf("%nInsgesamt sind %d Fehler aufgetreten.", testat3.TestMethoden.getErrorNr());
		System.out.println("...Tests beendet...");
	}

	/**
	 * Diese Methode testet das Ueberschreiben der Methode hashCode() durch die Klassen Person und Student.
	 * Sie testet auf Konsistenz.
	 */
	private void testHashCode() {
		// wenn die Methode zweimal auf dem selben Objekt ausgefuehrt wurde, muss zweimal derselbe
		// hashCode ausgegeben werden.

		// wenn x.equals(y), dann muss x.hashCode() == y.hashCode()
		Person x = new Person("x");
		Person x1 = new Person("x");

		if(x.equals(x1) && (x.hashCode() == x1.hashCode())) {
			System.out.println("\nBedingung fuer hashCode() erfuellt.");
		}
	}

	/**
	 * Diese Methode testet das Ueberschreiben der Methode equals() durch die Klassen Person und Student.
	 */
	private void testEquals() {
		int latestErrorNr = testat3.TestMethoden.getErrorNr();

		System.out.println("\nTeste equals()-Bedingungen zwischen Personen");
		// Check Grundbedingungen bei Vergleichen zwischen Instanzen der Klasse Person
		this.checkEqualsPerson();
		System.out.printf("Es sind %d Fehler aufgekommen.%n", testat3.TestMethoden.getErrorNr()-latestErrorNr);

		latestErrorNr = testat3.TestMethoden.getErrorNr();
		System.out.println("\nTeste equals()-Bedingungen zwischen Studenten");
		// Check Grundbedingungen bei Vergleichen zwischen Instanzen der Klasse Student
		this.checkEqualsStudent();
		System.out.printf("Es sind %d Fehler aufgekommen.%n", testat3.TestMethoden.getErrorNr()-latestErrorNr);

		latestErrorNr = testat3.TestMethoden.getErrorNr();
		System.out.println("\nTeste equals()-Bedingungen zwischen Person und Student (und umgekehrt)");
		// Check Grundbedingungen bei Vergleichen zwischen Instanzen beider Klassen, Person und Student
		this.checkBothClasses();
		System.out.printf("Es sind %d Fehler aufgekommen.%n", testat3.TestMethoden.getErrorNr()-latestErrorNr);

	}

	private void checkBothClasses() {

		Student hugo = new Student("Hugo", 12345);
		Person berta = new Person("Berta");
		Student hugo2 = new Student("Hugo", 12345);
		Student hugo3 = new Student("Hugo", 12345);

		Student antonStud = new Student("Anton", 5678);
		Person antonPers = new Person("Anton");
		Person antonPers2 = new Person("Anton");

		// Vergleiche:

		//UNGLEICH
		testat3.TestMethoden.assertBoolean(!hugo.equals(berta));
		testat3.TestMethoden.assertBoolean(!berta.equals(hugo));

		//GLEICH
		// Student mit Person
		// Gibt false aus. Das liegt daran, dass antonStud Student ist und deswegen auch die Matrikelnummer
		// gleich sein muss. Da die Person Anton aber keine besitzt, kann sie auch nicht gleich sein
		System.out.println("\nStudent Anton ist gleich Person Anton: " + antonStud.equals(antonPers));

		//Person mit Student
		// Gibt true aus. Das liegt daran, dass anton eine Person ist und deshalb nur das Attribut name
		// verglichen wird. Die Namen sind bei beiden gleich, also werden die Objekte als identisch angesehen.
		System.out.printf("Person Anton ist gleich Student Anton: %b", antonPers.equals(antonStud));

		checkSymmetry(antonStud, antonPers); //Fehler 1
		checkTransitivity(antonPers, antonPers2, antonStud);
		checkTransitivity(hugo, hugo2, hugo3);
		System.out.println(antonStud.equals(antonPers));
		checkTransitivity(antonStud, antonPers, antonPers2); //Fehler 2

		//CASTS

		// Von Person zu Student gecastet
		// Wirft einen Fehler, da der Compiler zur Laufzeit erst merkt, dass antonPers gar keine Matrikelnr hat..
		//System.out.println("Person Anton ist gleich Student Anton: " + ((Student)antonPers).equals(antonStud));

		// FUNKTIONIERT AUCH OHNE CAST!
		// Ist gleich, da nur der Name verglichen wird, und nicht die MatNr.
		System.out.println("Person Anton ist gleich (Person) Student Anton: " + (antonPers).equals((Person)antonStud));

		// Von Student zu Person gecastet
		// Trotz des Casts zu Person, geht das Programm in die equals Methode von Student und
		// gibt deshalb false aus, da antonPers keine MatrikelNr hat.
		// Da antonStud nicht nur von Typ Person sondern auch von Typ Student ist, wird
		// das equals von Student aufgerufen.
		System.out.println("(Person) Student Anton ist gleich Person Anton: " + ((Person)antonStud).equals(antonPers));
		System.out.println("(Person) Student Anton ist vom Typ Student: " + (antonStud instanceof Student));
		System.out.println("antonPers gehoert zum Typ Person: " + (antonPers instanceof Person));
		checkSymmetry((Person)antonStud, antonPers);

		// Transitivitaet
		checkTransitivity((Person)antonStud, antonPers, antonPers2); //duerfte nicht funktionieren
		checkTransitivity(antonPers, antonPers2, (Person)antonStud); // muesste funktionieren
	}

	private void checkEqualsStudent() {

		Student frieda = new Student("Frieda", 12345);
		Student frieda3 = new Student("Frieda", 12345);
		Student frieda4 = new Student("Frieda", 12345);
		Student hans = new Student("Hans", 78394);

		int latestErrorNr = testat3.TestMethoden.getErrorNr();

		checkReflexivity(frieda);

		//UNGLEICH Symmetrie Check
		checkSymmetry(frieda, hans);

		//GLEICH Symmetrie Check
		checkSymmetry(frieda, frieda3);

		checkTransitivity(frieda, frieda3, frieda4);

		// und pruefe Konsistenz
		testat3.TestMethoden.assertBoolean(frieda.equals(frieda3));

		// For any non-null reference value x, x.equals(null) should return false
		testat3.TestMethoden.assertBoolean(!frieda.equals(null));

		if(testat3.TestMethoden.getErrorNr() == 0) {
			System.out.println("Die Grundbedingungen fuer die Methode equals bei Vergleichen zwischen"
					+ " den Instanzen der Klasse Person werden erfuellt.");
		}else {
			System.out.printf("Beim Pruefen der Grundbedingungen ist/sind %d Fehler unterlaufen.%n", (testat3.TestMethoden.getErrorNr() - latestErrorNr));
		}
	}

	/**
	 * Diese Methode preuft, ob die Grundbedingungen, die an die Methode o.equals() gestellt werden,
	 * beim Uberschreiben beruecksichtigt wurden.
	 * Dazu gehoeren Transitivitaet, Symmetrie, Reflexivitaet, Konsistenz und der Fall,
	 * dass x.equals(null) == false gelten muss.
	 */
	private void checkEqualsPerson() {
		int latestErrorNr = testat3.TestMethoden.getErrorNr();
		Person anton = new Person("Anton");
		Person anton2 = new Person("Anton");
		Person anton3 = new Person("Anton");
		Person justus = new Person("Justus");

		checkReflexivity(anton);
		checkTransitivity(anton, anton2, anton3);
		checkSymmetry(anton, justus); //UNGLEICH
		checkSymmetry(anton, anton2); //GLEICH

		// .. und pruefe Konsistenz
		testat3.TestMethoden.assertBoolean(anton.equals(anton2));

		// For any non-null reference value x, x.equals(null) should return false
		testat3.TestMethoden.assertBoolean(!anton.equals(null));

		if(testat3.TestMethoden.getErrorNr() == 0) {
			System.out.println("Die Grundbedingungen fuer die Methode equals bei Vergleichen zwischen"
					+ " den Instanzen der Klasse Person werden erfuellt.\n");
		}else {
			System.out.printf("Beim Pruefen der Grundbedingungen ist/sind %d Fehler unterlaufen.%n",
					testat3.TestMethoden.getErrorNr()-latestErrorNr);
		}
	}

	/**
	 * Diese Methode ueberprueft das Reflexivitaetskriterium fuer die equals() Methode.
	 * @param a Objekt, das zum ueberpruefen verwendet wird
	 */
	private void checkReflexivity(Object a) {
		checkNullPointer(a);
		testat3.TestMethoden.assertBoolean(a.equals(a));

		if(!a.equals(a)) {
			System.out.println("Reflexivitaet-Kriterium nicht erfuellt.");
		}
	}

	/**
	 * Diese Methode ueberprueft das Transitivitaet fuer die equals() Methode.
	 * @param a, b, c Objekt, das zum ueberpruefen verwendet wird
	 */
	private void checkTransitivity(Object a, Object b, Object c) {
		checkNullPointer(a, b, c);
		testat3.TestMethoden.assertBoolean(a.equals(b) && b.equals(c) && a.equals(c));
		if(!(a.equals(b) && b.equals(c) && a.equals(c))) {
			System.out.println("Transitivitaet-Kriterium nicht erfuellt.");
		}
	}

	/**
	 * Diese Methode ueberprueft das Symmetrie fuer die equals() Methode.
	 * @param a, b Objekt, das zum ueberpruefen verwendet wird
	 */
	private void checkSymmetry(Object a, Object b) {
		checkNullPointer(a, b);
		testat3.TestMethoden.assertBoolean(a.equals(b) == b.equals(a));
		if(!(a.equals(b) == b.equals(a))) {
			System.out.println("Symmetrie-Kriterium nicht erfüllt!");
		}
	}

	/**
	 * Uberprueft, ob ein oder mehrere Objekte gleich null sind.
	 * @param objectsList Zu ueberpruefende Objekte
	 */
	private void checkNullPointer(Object... objectsList) {
		for(int i = 0; i < objectsList.length; i++) {
			if(objectsList[i] == null) {
				System.err.println("Object is not a non-null Instance! Exit Programm");
				System.exit((-1));
			}
		}
	}

}
