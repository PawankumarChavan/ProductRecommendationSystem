package DBConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PathHelper {
	public static FileInputStream fin = null;
	public static File f = null;

	static {
		f = new File(".");
		String absPath = f.getAbsolutePath();
		String nabsPath = absPath.substring(0, absPath.length() - 1) + "Resources\\db.properties";

		try {
			fin = new FileInputStream(nabsPath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
