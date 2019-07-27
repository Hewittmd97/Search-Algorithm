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
		Database db = new Database();
		String[] input = null;
		List<String[]> list = new ArrayList<String[]>();
		try 
		{
			input = ParseFile.readIn();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		/*for(int i = 0; i < input.length; i++)
		{
			System.out.println(i + ": " + input[i] + "\n");
		}*/
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
		int group = 0;
		String restOfString = "";
		for(int i = 0; i < inArrForm.length; i++)
		{
			String[][] temp;
			temp = CircularShift.shift(inArrForm[i]);
			for(int j = 0; j < temp.length; j++)
			{
				restOfString = "";
				for(int m = 1; m < temp[j].length; m++)
				{
					restOfString += temp[j][m] + " ";
				}
				db.add(temp[j][0], restOfString, input[group], input[group + 1], input[group + 2]);
			}
			group += 3;
		}
	}
}
//int total = 0;
//shifted = new String[total][];
//String[][] shifted;
//String[][] sorted;

/*for(int i = 0; i < inArrForm.length; i++)
{
	total += inArrForm[i].length;
}

total = 0;*/

/*for(String[] strTemp : temp)
{
	shifted[total] = strTemp;
	total++;
}*/

/*File file = new File("all.txt");
BufferedWriter writer = new BufferedWriter(new FileWriter(file));
for(int i = 0; i < shifted.length; i++)
{
	for(int j = 0; j < shifted[i].length; j++)
	{
		writer.write(shifted[i][j] + " ");
	}
	writer.write("\n");
}
writer.close();*/
