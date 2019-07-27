package allTogether;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Foundation {
	
	public static void main(String[] args) throws IOException
	{
		String[] input = null;
		List<String[]> list = new ArrayList<String[]>();
		try 
		{
			input = ParseFile.readIn();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		for(int i = 0; i < input.length; i++)
		{
			if((i + 1) % 3 == 0)
			{
				list.add(LineStorage.storeDataNoGroup(input[i]));
			}
		}
		String[][] inArrForm;
		inArrForm = new String[list.size()][];
		for(int i = 0; i < list.size(); i++)
		{
			inArrForm[i] = list.get(i);
		}
		for(int i = 0; i < inArrForm.length - 1; i++)
		{
			System.out.println(i + ": " + inArrForm[i][0]);
		}
		String[][] shifted;
		String[][] sorted;
		int total = 0;
		for(int i = 0; i < inArrForm.length; i++)
		{
			total += inArrForm[i].length;
		}
		shifted = new String[total][];
		total = 0;
		for(int i = 0; i < inArrForm.length; i++)
		{
			String[][] temp;
			temp = CircularShift.shift(inArrForm[i]);
			for(String[] strTemp : temp)
			{
				shifted[total] = strTemp;
				total++;
			}
		}
		File file = new File("all.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int i = 0; i < shifted.length; i++)
		{
			for(int j = 0; j < shifted[i].length; j++)
			{
				System.out.print(shifted[i][j] + " ");
				writer.write(shifted[i][j] + " ");
			}
			System.out.println("");
			writer.write("\n");
		}
		writer.close();
		//sorted = Alphabetize.sort(shifted, group);
		//sorted = Alphabetize.removeNoise(sorted, group);
		//Output.display(sorted, group);
	}

}
