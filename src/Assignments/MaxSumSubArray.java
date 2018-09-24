
public class MaxSumSubArray 
{
	public int findMaxSum(int[] arr)
	{
		int i=0, j=0, maxSum=0, sum=0;
		for(i=0;i<arr.length;i++)
		{
			if(arr[i] < 0)
				sum=0;				 //If the array element is negative, skip it and make sum = 0. 
			else
			{
				sum = sum + arr[i];
				if(sum > maxSum)     //for each number added, check if the sum exceeds maximum sum.
					maxSum = sum;
			}
		}
		return(maxSum);
	}
	public static void main(String args[])
	{
		int arr[] = {-2,5,2,-17,-8,-13,-5,-2};
		MaxSumSubArray m =new MaxSumSubArray();
		System.out.println(m.findMaxSum(arr));
	}
}
