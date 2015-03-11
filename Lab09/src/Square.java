
public class Square extends Shape{

	double height;
		
	public Square(double l) {
		height = l;
	}	
	
	public double getArea(){return height*height;}
	
	public String toString(){
		return "Square ("+height+")";
	}
}
