package allTogether;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search {
	
	public static List<String[]> search(Database db, String searchFor) throws SQLException
	{
		List<String[]> ll = new ArrayList<String[]>();
		String subString;
		String[] intoLL = new String[4];
		ResultSet rs = null;
		if(searchFor.contains("&&")) 
		{
			String string1 = searchFor.substring(0, searchFor.indexOf("&&") - 1);
			
			String string2 = searchFor.substring(searchFor.indexOf("&&") + 3);
			System.out.println(string1 + " : " + string2);
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
		return ll;
	}

}
