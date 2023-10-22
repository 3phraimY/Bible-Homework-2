
public class Main {
	public static void main(String[] args)
	{
		int[] disciples= new int[73];
		disciples[31] = 13;
		int[] people= new int[73];
		
		//equally distributing the 7.7 billion people into all ages 0 - 72
		for(int i=0; i< people.length; i++)
		{
			people[i] = 106944444;
		}
		System.out.println(howManyYears(disciples,people,2));
		System.out.println(howManyToTrainAtTime(disciples,people,50));
		
	}
	public static void printArray(int[] array)
	{
		for(int i =0; i<array.length; i++)
		{
			System.out.println(array[i]);
		}
		return;
	}
	//does the logic of dying and births
	//takes in number of years wanted to pass
	public static void YearsPass(int[] array, int years)
	{
		//repeats according to how many years
		for(int i =0; i< years; i++)
		{
			//moves each position one towards the end of the array
			for(int j = array.length -1; j>0; j--)
			{
				
				array[j] = array[j-1];
			}
			//fills the 0 age position with # of people age 30 / 2
			array[0] = array[30] / 2;
		}		
		return;		
	}
	//adds all values of arrays after the starting position
	public static long sumOfArray(int[] array, int start)
	{
		long sum = 0;
		for(int i=start; i<array.length; i++)
		{
			sum = sum + array[i];
		}
		return sum;
	}
	//performs one training cycle
	public static void trainDisciples(int[] disciples, int[] people, int numTrainedAtTime)
	{
		//has three years pass for the disciples and people arrays
		YearsPass(disciples, 3);
		YearsPass(people, 3);
		
		//total number of people trained by those disciples alive
		long numTrained = sumOfArray(disciples,21) * numTrainedAtTime;
		
		int index = 21;
		
		//assigns which people were trained
		//begins with those youngest able (21)
		//continues to assign older until total trained is 0
		while(numTrained > 0 && index<73)
		{
			if(numTrained >= people[index])
			{
				disciples[index] = people[index];
				numTrained = numTrained - people[index];
			}
			else
			{
				disciples[index] = (int)numTrained;
				numTrained = 0;
			}
			index++;
		}
	}
	//returns the number of years required to convert all people
	public static int howManyYears(int[] disciples, int[] people, int numTrainedAtTime)
	{
		//makes a copy of the arrays
		int[] disciplesTemp = disciples.clone();
		int[] peopleTemp = people.clone();
		int years = 0;
		
		//loops until the number of disciples is equal to total population
		while(sumOfArray(disciplesTemp,0) < sumOfArray(peopleTemp,0))
		{
			//does one disciple loop, adding three years each time
			trainDisciples(disciplesTemp,peopleTemp,numTrainedAtTime);
			years = years + 3;
			
		}
		return years;
	}
	//returns how many disciples each person would have to train at a time to meet a # of years
	public static int howManyToTrainAtTime(int[] disciples, int[] people, int yearsWanted)
	{
		
		int test = 2;
		
		//calls howManyYears until the number of years is less than or equal to the target # of years
		while(howManyYears(disciples,people,test) > yearsWanted)
		{
			test++;
		}
		return test;
	}
}
