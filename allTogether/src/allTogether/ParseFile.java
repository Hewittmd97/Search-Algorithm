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
		/*
		long lineCount;
		try(Stream<String> linesStream = Files.lines(new File("descriptors.txt").toPath()))
		{
			lineCount = linesStream.count();
		}
		int lC = (int) lineCount;
		for(int i = 0; i < lC; i++)
		{
			list.add(reader.readLine());
		}
		*/
		BufferedReader reader = new BufferedReader(new FileReader(new File("descriptors.txt")));
		List<String> list = new ArrayList<String>();
		while(reader.ready())
		{
			list.add(reader.readLine());
		}
		String[] all = list.toArray(new String[0]);
		reader.close();
		return all;
	}

}
