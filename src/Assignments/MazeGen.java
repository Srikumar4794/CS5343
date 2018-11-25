import java.util.Scanner;
import java.util.Random;
public class MazeGen 
{
	DisjSets disj ;
	boolean[] wallBroken;
	int num_walls;
	int rows,cols;
	
	public MazeGen(int r,int c)
	{
		rows = r;
		cols = c;
		disj = new DisjSets(rows * cols);
		num_walls = rows*cols*2 - cols;                         //total number of walls in the maze initially.
		wallBroken = new boolean[num_walls];                    //boolean array to indicate whether a wall is broken or exists between cells.
	}
	
	private void drawMaze()
	{
		int size = rows*cols;
		System.out.print("\n");
		for(int i=0; i< cols; i++)
			System.out.print(" ___");
		System.out.print("\n ");
		for(int i=0; i<size;i++)
		{			
			if(i%cols == 0 && i!=0)								//drawing the left border wall for start of each row.
				System.out.print("\n|");
			else
				if(i==0)
					System.out.print("");                       //open the wall for the starting point of the maze. 
			
			if(wallBroken[i] == false || (size-i)<=cols )       //drawing the horizontal walls if not broken or if the cell belongs to the bottom row.
				System.out.print("___");
			else
				System.out.print("   ");

			if(wallBroken[size-cols+i] == false && i!=size-1)   //drawing the vertical walls if wall not broken, except for the last cell in the maze.
				System.out.print("|");
			else
				System.out.print(" ");
	    }
	}
	
	private void connectCells(int wallnum,int size)
	{
		int cell1,cell2;
		
		if(wallnum < size - cols )                                          //if the wall is horizontal.
		{
			cell1 = wallnum ;
			cell2 = cell1 + cols;
		}
		else																//if the wall is vertical.
		{
			cell1 = wallnum - size + cols ;
			cell2 = cell1 + 1;
		}
		
		if(areCellsConnected(cell1,cell2) && (cell2 % cols != 0))     //if cells are already connected, do not break walls. Also when cell is in last column.
		{
			disj.union(disj.find(cell1),disj.find(cell2));
			wallBroken[wallnum] = true;
		}
	}
	
	private boolean areCellsConnected(int c1,int c2)
	{
		return disj.find(c1) != disj.find(c2);
	}
	
	public void generateMaze()
	{
		int size = rows * cols;
		
		Random r = new Random();
		while(disj.find(0) != disj.find(size-1))
		{
			int wall;
			wall = r.nextInt(size*2 - rows - cols);
			if(wallBroken[wall] == true)
				continue;
			connectCells(wall,size);
		}
		System.out.println("Root of 0th cell: " + disj.find(0) );
		System.out.println("Root of "+ (size-1) + "th cell: " + disj.find(size-1));	
	}			
	public static void main(String args[])
	{
		int i=0,rows,cols;
		int dimensions[] = new int[2];
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the dimensions of the maze you want:");
		while(i<2)
		{
			dimensions[i] = s.nextInt();
			i++;
		}
		rows = dimensions[0];
		cols = dimensions[1];
		MazeGen m = new MazeGen(rows,cols);
		m.generateMaze();
		m.drawMaze();
		s.close();

	}
}
