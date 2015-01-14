/**
 * Klem's Minecraft Chat Interceptor
 * 			Developed for
 * Maurin Entertainement Industries
 * v1.1.5
 */
package klem.gzdoom.utils;

import klem.gzdoom.resources.Resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Class regroupant des m�thode utiliaire appel�es �a et la
 * @author e402685
 *
 */
public class Tools {

	/**
	 * prend un fichier en entr�e et renvoie son centenu sous forme de chaine
	 * @param fileName
	 * @return
	 */
	public static String getFileContentAsString(String fileName) {
		System.out.println("Opening file : "+fileName);
		StringBuilder builder = new StringBuilder();
		
		String line;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Resources.class.getResourceAsStream(fileName), "UTF-8"));
			while((line = br.readLine()) != null) {
			builder.append(line+"\n");	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();
	}

    /**

     @param String only withour preceeding slashes.
                     Will be appended to {@link Constants#CWD}
     @return File content as string;
     */
	public static String getContentAsString(String path) {
        String fullPath = Constants.CWD + path;
        System.out.println("Reading ini... "+fullPath);
		StringBuilder output = new StringBuilder();
		try {
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath)));

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				output.append(inputLine);
                output.append("\n");
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	 public static Properties load(String propsName) throws Exception {
	        Properties props = new Properties();
	        URL url = ClassLoader.getSystemResource(propsName);
	        props.load(url.openStream());
	        return props;
	    }

}
