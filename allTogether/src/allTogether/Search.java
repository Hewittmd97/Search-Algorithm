package allTogether;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search {
	
	public static List<String[]> search(Database db, String searchFor, List<String[]> list, boolean firstCall) throws SQLException
	{
		List<String[]> ll = new ArrayList<String[]>();
		String subString;
		String[] intoLL = new String[4];
		ResultSet rs = null;
		try
		{
			list.get(0);
			if(searchFor.contains("&&"))
			{
				throw new ArithmeticException("Random Error");
			}
			while(searchFor.contains(" "))
			{
				String word = searchFor.substring(0, searchFor.indexOf(' '));
				searchFor = searchFor.substring(searchFor.indexOf(' ') + 1);
				for(int i = 0; i < list.size(); i++)
				{
					String[] temp = list.get(i);
					if(temp[3].contains(word))
					{
						ll.add(temp);
					}
				}
			}
			for(int i = 0; i < list.size(); i++)
			{
				String[] temp = list.get(i);
				if(temp[3].contains(searchFor))
				{
					ll.add(temp);
				}
			}
			//this branch will never be triggered on first call, so do not change next line.
			return ll;
		}
		catch(Exception e)
		{
			if(searchFor.contains("&&")) 
			{
				String string1 = searchFor.substring(0, searchFor.indexOf("&&") - 1);
				
				String string2 = searchFor.substring(searchFor.indexOf("&&") + 3);
				System.out.println(string1 + " : " + string2);
				List<String[]> list1 = Search.search(db, string1, null, false);
				List<String[]> list2 = Search.search(db, string2, list1, false);
				if(firstCall == true)
				{
					String[] lastly = new String[1];
					lastly[0] = "";
					for(int i = 0; i < list2.size(); i++)
					{
						String[] temp = list2.get(i);
						lastly[0] += (temp[0] + " : " + temp[1] + " : " + temp[2] + " : " + temp[3] + "\n");
					}
					List<String[]> finalist = new ArrayList<String[]>();
					finalist.add(lastly);
					return finalist;
				}
				else 
				{
					return list2;
				}
			}
			else 
			{
				while(searchFor.contains(" "))
				{
					subString = searchFor.substring(0, searchFor.indexOf(" "));
					searchFor = searchFor.substring(searchFor.indexOf(" ") + 1);
					db.search(subString);
					rs = db.getRS();
					rs.first();
					intoLL[0] = rs.getString("index");
					intoLL[1] = rs.getString("url");
					intoLL[2] = rs.getString("title");
					intoLL[3] = rs.getString("description");
					ll.add(intoLL);
					intoLL = new String[4];
					while(rs.next())
					{
						boolean alreadyThere = false;
						intoLL[0] = rs.getString("index");
						intoLL[1] = rs.getString("url");
						intoLL[2] = rs.getString("title");
						intoLL[3] = rs.getString("description");
						
						for(int i = 0; i < intoLL.length; i++)
						{
							String[] obj = ll.get(i);
							if(intoLL[1].contains(obj[1]))
							{
								alreadyThere = true;
								break;
							}
						}
						if(alreadyThere == true)
						{
							intoLL = new String[4];
							continue;
						}
						ll.add(intoLL);
						intoLL = new String[4];
					}
				}
				subString = searchFor;
				db.search(subString);
				rs = db.getRS();
				rs.first();
				intoLL[0] = rs.getString("index");
				intoLL[1] = rs.getString("url");
				intoLL[2] = rs.getString("title");
				intoLL[3] = rs.getString("description");
				ll.add(intoLL);
				intoLL = new String[4];
				while(rs.next())
				{
					boolean alreadyThere = false;
					intoLL[0] = rs.getString("index");
					intoLL[1] = rs.getString("url");
					intoLL[2] = rs.getString("title");
					intoLL[3] = rs.getString("description");
					for(int i = 0; i < ll.size(); i++)
					{
						String[] obj = ll.get(i);
						if(intoLL[1].contains(obj[1]))
						{
							alreadyThere = true;
							break;
						}
					}
					if(alreadyThere == true)
					{
						intoLL = new String[4];
						continue;
					}
					ll.add(intoLL);
					intoLL = new String[4];
				}
			}
		}
		if(firstCall == true)
		{
			String[] lastly = new String[1];
			lastly[0] = "";
			for(int i = 0; i < ll.size(); i++)
			{
				String[] temp = ll.get(i);
				lastly[0] += (temp[0] + " : " + temp[1] + " : " + temp[2] + " : " + temp[3] + "\n");
			}
			List<String[]> finalist = new ArrayList<String[]>();
			finalist.add(lastly);
			System.out.println(lastly[0]);
			return finalist;
		}
		else
		{
			return ll;
		}
	}
}
