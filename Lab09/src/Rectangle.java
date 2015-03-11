
public class Rectangle extends Shape {
	
	double length;
	double height;
	
	public Rectangle(double l, double h){
		length = l;
		height = h;
	}

	public double getArea(){
		return length*height;
	}
	
	public String toString(){
		return "Rectangle ("+length+"x"+height+")";
	}
}
