
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class StorageHandler implements ReadObjectInterface,SaveObjectInterface{
/**
 * Function to read the objects in the file	
 * @param fileName file to read from
 * @return The List of Objects read from the file
 * @throws IOException since the errors need to be on the top level
 */
	  public static ArrayList<String> read(String fileName) throws IOException {
			ArrayList<String> data = new ArrayList<>() ;
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    try {
		      while (scanner.hasNextLine()){
		        data.add(scanner.nextLine());
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return data;
		  }
	  /**
	   * Function to write the list of objects to the file
	   * @param fileName the file to write in
	   * @param data List of data to write.
	   * @throws IOException since the errors need to be on the top level
	   */
	    public static void write(String fileName, ArrayList<String> data) throws IOException  {
	    PrintWriter out = new PrintWriter(new FileWriter(fileName));

	    try {
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
	    }
	    finally {
	      out.close();
	    }
	  }
	   
//----------------------------------------------------Methods to be implemented in their Inherited classes	    
	  public abstract ArrayList readObject() throws IOException, ParseException;
	  public abstract void saveObject(String filename, ArrayList al) throws IOException;
}
