package identity;

public class TestIdentity {

	
	
	public static void main(String[] args) {
		
		TestIdentity test = new TestIdentity();
		
		test.testEquals();
		//test.testHashCode();	
		
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
			System.out.println("Bedingung 1 fuer hashCode() erfuellt.");
		}
	}

	/**
	 * Diese Methode testet das Ueberschreiben der Methode equals() durch die Klassen Person und Student.
	 */
	private void testEquals() {
		
		// Check Grundbedingungen bei Vergleichen zwischen Instanzen der Klasse Person
		this.checkEqualsPerson();
		
		// Check Grundbedingungen bei Vergleichen zwischen Instanzen der Klasse Student
		this.checkEqualsStudent();
		
		// Check Grundbedingungen bei Vergleichen zwischen Instanzen beider Klassen, Person und Student
		this.checkBothClasses();
		
		Person anton = new Person("Anton");
		Person berta = new Person("Berta");
		Person anton2 = new Person("Anton");
		Person anton3 = new Person("Anton");

		/*
		if(!anton.equals(berta)) {
			System.out.println("Personen mit unterschiedlichem Namensattribut und unterschiedlichem"
					+ " Variablennamen sind verschieden.");
		}
		
		if(anton.equals(anton2)) {
			System.out.println("Personen mit unterschiedlichen Variablenname aber demselben"
					+ " Attribut-Namen werden als identisch angesehen.");
		}
		*/
		
		Student frieda = new Student("Frieda", 12345);
		Student frieda2 = new Student("Frieda", 34556);
		Student frieda3 = new Student("Frieda", 12345);
		Student hugo = new Student("Hugo", 12345);
		Student anton4 = new Student("Anton", 5678);
		Person mascha = new Student("Mascha", 12345);
	
		/*
		System.out.println("Frieda und Hugo sind gleiche Studenten: " + frieda.equals(hugo));
		System.out.println("Frieda und Frieda2 sind gleich: " + frieda.equals(frieda2));
		System.out.println("Frieda und Frieda3 sind gleich: " + frieda.equals(frieda3));
		
		System.out.println("Anton4 ist gleich anton: " + anton4.equals(anton));
		System.out.println("Mascha ist dieselbe Studentin, wie Frieda: " + mascha.equals(frieda));
		System.out.println(((Student)mascha).getMatNr());
		System.out.println("Mascha ist dieselbe Studentin, wie Frieda: " + ((Student)mascha).equals(frieda));
		*/
		
	}
	
	private void checkBothClasses() {
		
		Student hugo = new Student("Hugo", 12345);
		Person berta = new Person("Berta");
		
		Student antonStud = new Student("Anton", 5678);
		Person antonPers = new Person("Anton");
		
		// Vergleiche:
		
		//UNGLEICH
		// Student mit Person
		System.out.println("hugo ist gleich berta: " + hugo.equals(berta));
		
		// Person mit Student 
		System.out.println("berta ist gleich hugo: " + berta.equals(hugo));
		
		//GELICH
		// Student mit Person
		// Gibt false aus. Das liegt daran, dass antonStud Student ist und deswegen auch die Matrikelnummer 
		// gleich sein muss. Da die Person Anton aber keine besitzt, kann sie auch nicht gleich sein
		System.out.println("Student Anton ist gleich Person Anton: " + antonStud.equals(antonPers));
		
		//Person mit Student
		// Gibt true aus. Das liegt daran, dass anton eine Person ist und deshalb nur das Attribut name
		// verglichen wird. Die Namen sind bei beiden gleich, also werden die Objekte als identisch angesehen.
		System.out.println("Person Anton ist gleich Student Anton: " + antonPers.equals(antonStud));
		
		if(antonStud.equals(antonPers) != antonPers.equals(antonStud)) {
			System.out.println("Symmetrie nicht erfuellt.");
		}
		
		
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
		System.out.println("antonPers gehoert zum Typ Person: " + (antonPers instanceof Person));
		System.out.println(antonPers.getName() + " " + antonStud.getName());
	}

	private void checkEqualsStudent() {
		
		Student frieda = new Student("Frieda", 12345); 
		Student frieda2 = new Student("Frieda", 34556);
		Student frieda3 = new Student("Frieda", 12345);
		Student frieda4 = new Student("Frieda", 12345); 
		
		// Pruefe die Grundbedingungen: Reflexivitaet, Symmetrie, Transitivitaet und Konsistenz
		testat3.TestMethoden.assertBoolean(frieda.equals(frieda));
		
		testat3.TestMethoden.assertBoolean((frieda.equals(frieda3) && frieda3.equals(frieda)));
		
		testat3.TestMethoden.assertBoolean(frieda.equals(frieda3) && frieda3.equals(frieda4) 
											&& frieda.equals(frieda4));
		testat3.TestMethoden.assertBoolean(frieda.equals(frieda3));

		// For any non-null reference value x, x.equals(null) should return false
		testat3.TestMethoden.assertBoolean(!frieda.equals(null));
		
		if(testat3.TestMethoden.getErrorNr() == 0) {
			System.out.println("Die Grundbedingungen fuer die Methode equals bei Vergleichen zwischen"
					+ " den Instanzen der Klasse Person werden erfuellt.");
		}else {
			System.out.println("Beim Pruefen der Grundbedingungen ist/sind " + testat3.TestMethoden.getErrorNr() 
					+ " Fehler unterlaufen.");
		}
	}

	/**
	 * Diese Methode preuft, ob die Grundbedingungen, die an die Methode o.equals() gestellt werden,
	 * beim Uberschreiben beruecksichtigt wurden. 
	 * Dazu gehoeren Transitivitaet, Symmetrie, Reflexivitaet, Konsistenz und der Fall,
	 * dass x.equals(null) == false gelten muss.
	 */
	private void checkEqualsPerson() {

		Person anton = new Person("Anton");
		Person anton2 = new Person("Anton");
		Person anton3 = new Person("Anton");

		// Pruefe die Grundbedingungen: Reflexivitaet, Symmetrie, Transitivitaet und Konsistenz
		testat3.TestMethoden.assertBoolean(anton.equals(anton));
		
		testat3.TestMethoden.assertBoolean(anton.equals(anton2) && anton2.equals(anton));
		
		testat3.TestMethoden.assertBoolean(anton.equals(anton2) && anton2.equals(anton3) && anton.equals(anton3));
		
		testat3.TestMethoden.assertBoolean(anton.equals(anton2));

		// For any non-null reference value x, x.equals(null) should return false
		testat3.TestMethoden.assertBoolean(!anton.equals(null));
		
		if(testat3.TestMethoden.getErrorNr() == 0) {
			System.out.println("Die Grundbedingungen fuer die Methode equals bei Vergleichen zwischen"
					+ " den Instanzen der Klasse Person werden erfuellt.");
		}else {
			System.out.println("Beim Pruefen der Grundbedingungen ist/sind " + testat3.TestMethoden.getErrorNr() 
					+ " Fehler unterlaufen.");
		}
	}
}
