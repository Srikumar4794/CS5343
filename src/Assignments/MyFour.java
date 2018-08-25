package Assignments;
public class MyFour<T>
{
	T item1,item2,item3,item4;
	MyFour(T a,T b,T c, T d)
	{
		item1 = a;
		item2 = b;
		item3 = c;
		item4 = d;
	}
	public boolean allEquals()
	{
		if(item1.equals(item2) && item2.equals(item3) && item3.equals(item4))
			return(true);
		else
			return(false);
	}
	public void shiftLeft()
	{
		T temp = item1;
		item1  = item2;
		item2  = item3;
		item3  = item4;
		item4  = temp;
	}
	public String itemsConcat()
	{
		return(item1.toString() + ", " + item2.toString() + ", " + item3.toString() + ", " + item4.toString());
	}
	public static void main(String args[])
	{
		MyFour<String> obj1 = new MyFour<>("Welcome","Welcome","Welcome","Welcome");
		System.out.println("Result of equating 4 items of obj1: " + obj1.allEquals());
		MyFour<Integer> obj2 = new MyFour<>(10,15,20,25);
		System.out.println("Result of equating 4 items of obj2: " + obj2.allEquals());
		System.out.println("Befoore shifting left:" + obj2.itemsConcat());
		obj2.shiftLeft();
		System.out.println("After shifting left:" + obj2.itemsConcat());
	}
}
