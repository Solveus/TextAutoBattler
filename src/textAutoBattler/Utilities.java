package textAutoBattler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class Utilities {
    public static final String resourcesPath = "/textAutoBattler/resources/";
    public static final String delimeter = "//";
    public static final String TAB = "    ";
    public static final String weaponPath = "weapons.dat";
    public static final String charactersPath = "players.dat";

    // parse and return it as array strings
    public static Vector<String[]> parseFile(InputStream path, String dlmtr) {
        Vector<String[]> list = null;
        String line;
        String delimeter;

        if (dlmtr == null || dlmtr.equals("")) {
            delimeter = " ";
        }  else {
            delimeter = dlmtr;
        }

        // try read file
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(path));

            list = new Vector<>();
            while ((line = in.readLine()) != null) {
                list.add(line.split(delimeter));
            }
            in.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}
