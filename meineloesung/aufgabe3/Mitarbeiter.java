package identity;

/**
 * Diese Klasse ist nur dazu da, zu testen wie die unueberschriebene Version 
 * von equals und hashCode funktioniert.
 * 
 * @author Rosa
 *
 */
public class Mitarbeiter {
	
	private String name;
	private int persoID;
	
	public Mitarbeiter(String name, int persoID) {
		this.name = name;
		this.persoID = persoID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getID() {
		return this.persoID;
	}
}
