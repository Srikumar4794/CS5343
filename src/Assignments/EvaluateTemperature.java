package Assignments;
import java.util.Scanner;
public class EvaluateTemperature {

	public int convertScale(int tc)				 		//receives input in celsius.
	{
		int tf = tc * (9/5) + 32;
		return tf;										//returns temperature in fahrenheit.
	}
	public String findRange(int temp, String scale)
	{
		String description = new String();
		if(scale.equals("C"))
			temp = convertScale(temp);
		if(temp < 0)
		{
			description = "Extremely cold";
			return(description);
		}
		if(temp >= 0 && temp <= 32) 
		{
			description = "Very cold";
			return(description);
		}
		if(temp >= 33 && temp <= 50)
		{
			description = "Cold";
			return(description);
		}
		if(temp >= 51 && temp <= 70)
		{
			description = "Mild";
			return(description);
		}
		if(temp >= 71 && temp <= 90)
		{
			description = "Warm";
			return(description);
		}
		if(temp >= 91 && temp <= 100)
		{
			description = "Hot";
			return(description);
		}
		if(temp > 100)
		{
			description = "Very hot";
			return(description);
		}
		return "Out of Bounds";
	}
	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the temperature:");
		int temp = s.nextInt();
		System.out.println("Enter measurement scale:");
		String scale = s.next();
		EvaluateTemperature e = new EvaluateTemperature();
		String output = e.findRange(temp,scale);
		System.out.println(output);
		s.close();
		
	}


}
