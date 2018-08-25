package Assignments;
public class TestSquare
{
	public static void main(String args[])
	{
		Square s1 = new Square();
		Square s2 = new Square(10);
		System.out.println("Area of square s1 is: " + s1.getArea());
		System.out.println("Area of square s2 is: " + s2.getArea());
	}
}
