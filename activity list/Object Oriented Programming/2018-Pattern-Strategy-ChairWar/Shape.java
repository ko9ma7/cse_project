
public abstract class Shape {

	public abstract void rotate();
	public abstract void playSound();
	
	public static void main(String[] args) {
		Shape shape1 = new Square(); shape1.rotate(); shape1.playSound();
		Shape shape2 = new Circle(); shape2.rotate(); shape2.playSound();
		Shape shape3 = new Triangle(); shape3.rotate(); shape3.playSound();
		Shape shape4 = new Amoeba(); shape4.rotate(); shape4.playSound();
	}

}
