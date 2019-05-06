package geometryTask;

public class Test{

    public static void main(String[] args){
        System.out.println("Starte Tests \n");

        Test test = new Test();
        test.testAll();

        System.out.println("\nTests erfolgreich abgeschlossen");
    }

    public void testAll(){
    	this.testPoint();
        this.testVolume();
    }
    
    /**
     * Methode testet alle Methoden von Point
     */
    public void testPoint() {
    	//Testet den Konstruktor von Point
    	Point eins = new Point(new double[] {3,5,8,1});
    	System.out.println(eins.getOutKoordinates());
    	assert(eins.getOutKoordinates().matches("3.0, 5.0, 8.0, 1.0")): "Fehler 1";
    	
    	//Testet die Funktion volume()
    	assert(eins.volume() == 0):"Fehler 2";
    	
    	//Testet die Funktion encapsulate()
    	Point zwei = new Point(new double[] {6,2,0});
    	Point drei = new Point(new double[] {3,7,5});
    	Point vier = new Point(new double[] {1,4,3});
    	Point funf = new Point(new double[] {6,0});
    	
    	Volume volume = new Volume(drei, vier);
    	Point2D point2D = new Point2D(4, 5);
    	
    	assert()
    }
    
    
    public void testVolume(){
        Point eins = new Point(new double[] {3, 4, 8});
        Point zwei = new Point(new double[] {7, 2});
        Volume volume1 = new Volume(eins, zwei);
        System.out.println("Das Volumen von volume1 ist: " + volume1.volume());
        System.out.println("Die Dimension ist: " + volume1.dimensions());
    }
}
