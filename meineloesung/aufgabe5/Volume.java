package geometryTask;

public class Volume extends Geometry{
    private double[] point1;
    private double[] point2;

    /**
     * Konstruktor Ueberprueft ob Punkte der gleichen Dimension
     * sind und erstellt Volume mit den uebergebenen Punkten
     * @param Punkte die das Volumen aufspannen sollen
    **/
    public Volume(Point eins, Point zwei){
    	super(eins.dimensions());
        assert(eins.dimensions() == zwei.dimensions()): "Fehler! Ungleiche Dimensionen.";
        point1 = eins.koordinates();
        point2 = zwei.koordinates();
    }
    
    //**********************************************************************************************************************
    
    /**
     *Methode errechnet das Volumen des Objects Volumen
     *@return das Volumen des Objects als double
    **/
    @Override
    public double volume(){
        double[] minimum = minimum(point1, point2);
        double[] maximum = maximum(point1, point2);

        double result = 1;
        for(int i = 0; i < this.point1.length; i++){
            result *= (maximum[i]-minimum[i]);
        }
        return result;
    }
    
    /**
     * Methode erstellt ein Volumen aus einem Volumen und einem Volumen oder Point
     * @param Geometry Form die mit this zu neuem Geometry werden soll
     * @return neues Geometry, hier Volume
     */
    @Override
    public Geometry encapsulate(Geometry other) {
    	//Wenn die beiden Objekte unterschiedliche Dimensionen haben 
    	//ist das erstellen eines gemeinsamen Geometry nicht sinnvoll, daher null-Rückgabe
    	if(this.dimensions() != other.dimensions()) {
    		return null;
    		
    	//Wenn das übergebene Objekt ein Volumen (oder Rectangle) ist werden von allen vier Punkten, 
    	//die maximalen und minimalen Koordinaten gesucht und mit diesen zwei Punkten das neue Volume aufgespannt
    	} else if(other instanceof Volume){
    		double[] minimum = minimum(minimum(this.getPoint1(), this.getPoint2()), minimum(((Volume) other).getPoint1(), ((Volume) other).getPoint2()));
    		double[] maximum = maximum(maximum(this.getPoint1(), this.getPoint2()), maximum(((Volume) other).getPoint1(), ((Volume) other).getPoint2()));
    		
    		//Wenn die dimension 2 ist, wird (minimal) ein Rectangle erstellt
    		if(minimum.length == 2) {
    			Rectangle rectangle = new Rectangle(new Point2D(minimum[0], minimum[1]), new Point2D(maximum[0], maximum[1]));
    			return rectangle;
    			
    		} else {
    			Volume volume = new Volume(new Point(minimum), new Point(maximum));
        		return volume;
    		}
    		
    		
    	//Wenn das übergebene Objekt ein Point ist, werden von den drei Punkten 
    	//die maximalen und minimalen Koordinaten gesucht und mit den zwei resultierenden Punkten das neue Volumen aufgespannt
    	} else {
    		double[] minimum = minimum(minimum(this.getPoint1(), this.getPoint2()), ((Point)other).koordinates());
    		double[] maximum = maximum(maximum(this.getPoint1(), this.getPoint2()), ((Point)other).koordinates());
    		
    		//Wenn die Dimension der Objekte gleich zwei ist, soll statt eines Volumes ein Rectangle erstellt werden
    		if(minimum.length == 2) {
    			Rectangle rectangle = new Rectangle(new Point2D(minimum[0], minimum[1]), new Point2D(maximum[0], maximum[1]));
    			return rectangle;
    			
    		//Erstellung eines Volumes bei dimension != 2
    		} else {
    			Volume volume = new Volume(new Point(minimum), new Point(maximum));
        		return volume;
    		}
    		
    	}
    }
    
    
    //**************************************************************************************************************************
    
    
    
    /**
     * Getter für Attribut point1
     */
    public double[] getPoint1() {
    	return point1;
    }
    
    /**
     * Getter für Attribut point2
     */
    public double[] getPoint2() {
    	return point2;
    }
}
