package allTogether;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {

	public static String[] readIn() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("descriptors.txt")));
		List<String> list = new ArrayList<String>();
		while(reader.ready())
		{
			list.add(reader.readLine());
		}
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).contains(","))
			{
				list.get(i).replaceAll(",", "");
			} 
			if(list.get(i).contains("-"))
			{
				list.get(i).replaceAll("-", " ");
			}
			if(list.get(i).contains("&"))
			{
				list.get(i).replaceAll("&", " ");
			}
			if(list.get(i).contains("*"))
			{
				list.get(i).replaceAll("*", " ");
			}
			if(list.get(i).contains(":"))
			{
				list.get(i).replaceAll(":", " ");
			}
			if(list.get(i).contains(";"))
			{
				list.get(i).replaceAll(";", " ");
			}
			if(list.get(i).contains("\""))
			{
				list.get(i).replaceAll("\"", " ");
			}
			if(list.get(i).contains("/"))
			{
				list.get(i).replaceAll("/", " ");
			}
			if(list.get(i).contains("."))
			{
				list.get(i).replaceAll(".", " ");
			}
			if(list.get(i).contains("     "))
			{ 
				list.get(i).replace("     ", " ");
			}
			if(list.get(i).contains("    "))
			{
				list.get(i).replace("    ", " ");
			}
			if(list.get(i).contains("   "))
			{
				list.get(i).replace("   ", " ");
			}
			if(list.get(i).contains("  "))
			{
				list.get(i).replace("  ", " ");
			}
		}
		String[] all = list.toArray(new String[0]);
		reader.close();
		return all;
	}

}
