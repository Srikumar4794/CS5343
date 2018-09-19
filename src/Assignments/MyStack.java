package Assignments;
import java.util.ArrayList;
import java.util.Scanner;

public class MyStack 
{
	int top = -1;
	ArrayList<Character> stack_list = new ArrayList<Character>();
	public void push(char val)
	{
		stack_list.add(val);
		top++;
	}
	public char pop()
	{
		if(!isEmpty())
		{
			char top_val = stack_list.get(top);
			stack_list.remove(top);
			top--;
			return(top_val);
		}
		else
		{
			System.out.println("Empty stack");
			return('\0');
		}
	}
	
	public boolean isEmpty()
	{
		if(top >= 0)
			return(false);
		else
			return(true);
	}

	public void printStack()
	{
		if(!isEmpty())
		for(int i= top; i >=0; i--)									  //Print the stack values from top to bottom of stack.	
		{	
			System.out.print(stack_list.get(i));						
		}
		else
			System.out.println("Empty.");
	}
	
	public void checkParenthesis(char[] charArray)
	{
	    ArrayList<Character> close_symbols = new ArrayList<>();		   //Define an array of closing parentheses symbols.	
	    close_symbols.add('}');
	    close_symbols.add(']');
	    close_symbols.add(')');
	    boolean chk_flag = true;
	    for(int i=0; i< charArray.length; i++)
	    {
	    	if((close_symbols.contains(charArray[i])) != true)         //if the expression does not contain any closing brackets,
	    	{
	    		this.push(charArray[i]);							   //Push the character into the stack.	
	    	}
	    	else
	    	{
	    		switch(charArray[i])								   //Check if the closing bracket has a match at the top of stack.	
	    		{
	    			case '}' :
	    				if(this.pop() != '{' )
	    					chk_flag = false;						  //flag set to false if there is a mismatch.	
	    				break;
	    			case ')' :
	    				if(this.pop() != '(' )
	    					chk_flag = false;
	    				break;
	    			case ']' :
	    				if(this.pop() != '[' )
	    					chk_flag = false;
	    				break;
	    		}
	    		if(!chk_flag)
	    			break;											  //Break the loop on single mismatch.	
	    	}
	    }
	}


	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		MyStack stack_input = new MyStack();
		System.out.println("Enter your input expression:");
		String input_str = s.nextLine();
		char[] charArray = input_str.toCharArray();
		stack_input.checkParenthesis(charArray);
	    if(stack_input.isEmpty())									 //Balanced expression only if the stack is empty.
	    	System.out.println("Balanced parenthesis in the expression.");
	    else
	    	System.out.println("Unbalanced parentheses in the expression.");
	    System.out.print("Stack values from the top after checking expression: ");
	    stack_input.printStack();
	    s.close();

	}
}
