package allTogether;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Search {
	
	public static List<String[]> search(Database db, String searchFor) throws SQLException
	{
		List<String[]> ll = new ArrayList<String[]>();
		String subString;
		String[] intoLL = new String[4];
		if(searchFor.contains("&&")) 
		{
			String string1 = searchFor.substring(0, searchFor.indexOf("&&"));
			String string2 = searchFor.substring(searchFor.indexOf("&&" + 1));
			List<String[]> list1 = Search.search(db, string1);
			List<String[]> list2 = Search.search(db, string2);
			for(int i = 0; i < list1.size(); i++)
			{
				ll.add(list1.get(i));
			}
			for(int i = 0; i < list2.size(); i++)
			{
				ll.add(list2.get(i));
			}
			return ll;
		}
		else 
		{
			while(searchFor.contains(" "))
			{
				subString = searchFor.substring(0, searchFor.indexOf(" "));
				searchFor = searchFor.substring(searchFor.indexOf(" ") + 1);
				db.search(subString);
				db.getRS().first();
				intoLL[0] = db.getRS().getString("index");
				intoLL[1] = db.getRS().getString("url");
				intoLL[2] = db.getRS().getString("title");
				intoLL[3] = db.getRS().getString("description");
				ll.add(intoLL);
				intoLL = new String[4];
				while(db.getRS().next())
				{
					boolean alreadyThere = false;
					intoLL[0] = db.getRS().getString("index");
					intoLL[1] = db.getRS().getString("url");
					intoLL[2] = db.getRS().getString("title");
					intoLL[3] = db.getRS().getString("description");
					
					for(int i = 0; i < intoLL.length; i++)
					{
						String[] obj = ll.get(i);
						if(intoLL[1] == obj[1])
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
			db.getRS().first();
			intoLL[0] = db.getRS().getString("index");
			intoLL[1] = db.getRS().getString("url");
			intoLL[2] = db.getRS().getString("title");
			intoLL[3] = db.getRS().getString("description");
			ll.add(intoLL);
			intoLL = new String[4];
			while(db.getRS().next())
			{
				intoLL[0] = db.getRS().getString("index");
				intoLL[1] = db.getRS().getString("url");
				intoLL[2] = db.getRS().getString("title");
				intoLL[3] = db.getRS().getString("description");
				ll.add(intoLL);
				intoLL = new String[4];
			}
		}
		return ll;
	}

}
