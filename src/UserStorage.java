import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer; 

public class UserStorage extends StorageHandler{
//--------------------------------------------------------Data Members
	private String readFile, writeFile, user, password;
	public static final String SEPARATOR = " ";
	public static final String SEPARATOR1 = "|";
//---------------------------------------------------------Constructor
	/**
	 * Parameterized Constructor 
	 * @param readFile File to read into
	 * @param writeFile File to write into
	 * @param user the username 
	 * @param password the password
	 */
	public UserStorage(String readFile, String writeFile, String user, String password) {
		this.readFile = readFile;
		this.writeFile = writeFile;
		this.user=user;
		this.password=password;
	}
//---------------------------------------------------------Member Functions
	/**
	 * Function to read the file and check if the user and password is same
	 * @return true if both user name and password are same as in the file
	 */
	public boolean readFile(){
		boolean counter=false;
			try {
	            FileReader reader = new FileReader(readFile);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	 
	            String line;
	            
	            while ((line = bufferedReader.readLine()) != null) {
	            	StringTokenizer st = new StringTokenizer(line,SEPARATOR);
		                if (st.nextToken().equals(user) && st.nextToken().equals(password)) {
		                	counter=true;
		                	return counter;
		                }
		                else break;
	            }
	            reader.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 return counter;
	}
	
	/**
	 * Function to write the new username and password into the file
	 */
	public void writeFile() {
        try {
            FileWriter writer = new FileWriter(writeFile, true);
            writer.write(user + " " + password);
            writer.write("\r\n");   // write new line
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

//------------------------------------------------------------------------------Unimplemented Methods
public ArrayList readObject() throws IOException {return null;}
public void saveObject(String filename, ArrayList al) throws IOException {}
}
