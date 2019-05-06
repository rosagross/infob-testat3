package geometryTask;

/**
 *Every Point represents a n-dimensional point in space
**/
public class Point extends Geometry{
    /**
     *Speichert double array mit den Koordinaten des Point
    **/
    private double[] koordinaten;

    /**Konstruktor speichert Uebergebenen double-array in Attribut koordinaten und
     * die Laenge in super-Attribut dimension
     * @param  double-array der die Koordinaten des Point enthaelt
    **/
    public Point(double[] koordinaten){
    	super(koordinaten.length);
        this.koordinaten = koordinaten;
    }
    /**
     * Ueberschreibt volume() aus der Oberklasse
     * da ein Punkt kein Volumen besitzen kann, wird immer 0 zurueckgegeben
     * @return volume = 0
    **/
    @Override
    public double volume(){
        double volume = 0;
        return volume;
    }

    /**
     * Methode gibt Geometry (Volume oder rectangle) zurück, dass beide Objecte (this and other) minimal umspannt.
     * @param Object das zusammen mit this umspannt werden soll
     * @return minimale Geometry Form (Volume oder Rectangle)
    **/
    @Override
    public Geometry encapsulate(Geometry other){
        if(other.dimensions() != this.dimensions()){
            return null;
        } else if(other instanceof Volume){
        	double[] minimum = minimum(this.koordinates(), minimum(((Volume) other).getPoint1(), ((Volume) other).getPoint2()));
    		double[] maximum = maximum(this.koordinates(), maximum(((Volume) other).getPoint1(), ((Volume) other).getPoint2()));
    		
    		//Wenn die Dimension 2 ist, wird (aufgrund des constraints minimal) ein Rectaangle erstellt
    		if(minimum.length == 2) {
    			Rectangle rectangle = new Rectangle(new Point2D(minimum[0], minimum[1]), new Point2D(maximum[0], maximum[1]));
    			return rectangle;
    		} else {
    			Volume volume = new Volume(new Point(minimum), new Point(maximum));
        		return volume;
    		}
    	//Wenn das übergeben Objekt ein Punkt ist, werden ebenfalls die 
    	//maximalen und minimalen Koordinaten gefunden und deren Volume oder Rectangle erstellt
        } else {
            double[] minimum = minimum(this.koordinates(), ((Point)other).koordinates());
            double[] maximum = maximum(this.koordinates(), ((Point)other).koordinates());
            
            //Wenn die Dimension 2 ist, wird (aufgrund des constraints minimal) ein Rectaangle erstellt
            if(minimum.length == 2) {
            	Rectangle rectangle = new Rectangle(new Point2D(minimum[0], minimum[1]), new Point2D(maximum[0], maximum[1]));
    			return rectangle;
    		} else {
    			Volume volume = new Volume(new Point(minimum), new Point(maximum));
        		return volume;
            }
        }
    }
    
    
    //****************************************************************************************************************************
    
    /**
     * Getter für koordinaten
     * @return Koordinaten double-Array des Points
     */
    public double[] koordinates() {
    	return koordinaten;
    }
    
    /**
     * Getter für die Werte des Arrays koordinaten als String
     * @return String der Werte des double-Array
     */
    public String getOutKoordinates() {
    	String result = "" + koordinaten[0];
    	for(int i = 1; i < koordinaten.length; i++) {
    		result += ", " + koordinaten[i];
    	}
    	return result;
    }

}
