import java.util.Scanner;
public class Scores 
{
	public int computeAverage(int[] X)
	{
		int sum = 0,X_size;
		X_size = X.length;
		for(int i=0; i < X_size; i++)
			sum += X[i];
		return(sum/X_size);
	}
	public static void main(String args[])
	{
		Scores s = new Scores();
		String names[] = new String[10];
		int scores[][] = new int[10][5];
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<10; i++)
		{
			System.out.print("Enter the name of the student:\n");
			names[i] = scan.next();
			System.out.print("Enter the 5 quiz scores:");
			int j=0;
			while(j<5)
			{
				scores[i][j] = scan.nextInt();
				j++;
			}
		}
		scan.close();
		for(int i=0; i<10; i++)
		{
			System.out.println("Average quiz score of " + names[i] + "is " + s.computeAverage(scores[i]));
		}
	}

}
