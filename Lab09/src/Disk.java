
public class Disk extends Shape {

	double radius;
	
	public Disk(double r) {
		radius = r;
	}

		public double getArea(){
			return Math.PI*radius*radius;
		}
		
		public String toString(){
			return "Disk ("+radius+")";
		}
}
