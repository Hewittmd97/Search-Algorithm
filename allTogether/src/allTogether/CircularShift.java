package allTogether;


// returns the Circular Shift of the specified lineNum 
// each line has multiple groupings specified by group
// returns null if groupNum is too large for lineNum
// returns null if lineNum is too large
// and line storage does not have another line

public class CircularShift {

	public static String[][][] shift(String[][] storage, int group)
	{
        String[][][] word = new String [Control.MAX_LINES][Control.MAX_WORDS][Control.MAX_WORDS];
	    if(storage[group].length > 1) {
            word[group] = new String[Control.MAX_LINES][storage[group].length];
            String temp = "";
            String temp2 = "";
            for (int i = 0; i < storage[group].length; i++) {
                temp = storage[group][0];
                for (int j = 0; j < storage[group].length; j++) {
                    temp2 = storage[group][(j + 1) % storage[group].length];
                    storage[group][(j + 1) % storage[group].length] = temp;
                    temp = temp2;
                }
                for (int j = 0; j < storage[group].length; j++) {
                    word[group][i][j] = storage[group][j];
                }
            }
        }
	    else
        {
            word[group] = new String[1][1];
            word[group][0][0] = storage[group][0];
        }
		return word;
	}
}
