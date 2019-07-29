package allTogether;

import java.sql.SQLException;
import java.util.LinkedList;

public class Search {
	
	public static LinkedList<String[]> search(Database db, String searchFor) throws SQLException
	{
		LinkedList<String[]> ll = null;
		String subString;
		String[] intoLL = new String[4];
		if(searchFor.contains(" "))
		{
			subString = searchFor.substring(0, searchFor.indexOf(' '));
			searchFor = searchFor.substring(searchFor.indexOf(' '));
		}
		else 
		{
			subString = searchFor;
		}
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
		return ll;
	}

}
