
public abstract class Shape implements Comparable<Shape>{
	
		public abstract double getArea();
	
		public int compareTo(Shape s){
			double areaS = s.getArea();
			double areaThis = getArea();
			if (areaThis < areaS)
				return -1;
			else if (areaThis == areaS)
				return 0;
			else
				return +1;
		}
	
			public abstract String toString();
		
}
