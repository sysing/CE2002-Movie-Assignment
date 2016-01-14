
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

public class Storage {
	
//--------------------------------------------------------------Data Members
	private String readFile, writeFile, user, password;
	public static final String SEPARATOR = " ";
	public static final String SEPARATOR1 = "|";
	
//--------------------------------------------------------------Constructor
	public Storage(String readFile, String writeFile, String user, String password) {
		this.readFile = readFile;
		this.writeFile = writeFile;
		this.user=user;
		this.password=password;
	}
//---------------------------------------------------------------Member Functions
	
	/**
	 * Function to read the file and check if the user name and password are the same password.
	 * @return true is same.
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
	 * Function to write the new username and password.
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
	/**
	 * Function to read the attributes in the movie file.
	 * @param filename the filename to read from
	 * @return List of movies found in the movie files.
	 * @throws IOException since finding an error must come on the top class.
	 */

    // an example of reading
	public static ArrayList<Movie> readMovie(String filename) throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList)read(filename);
		ArrayList<Movie> alr = new ArrayList<>() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter ","
                while(star.hasMoreTokens()){
                int  movieid = Integer.parseInt(star.nextToken().trim());
				String  movietitle = star.nextToken().trim();	// first token
				String  type = star.nextToken().trim();	// second token
				String  rating = star.nextToken().trim();	// third token
				String  status = star.nextToken().trim();	// fourth token
				String  synopsis = star.nextToken().trim();	// fifth token
				String  director = star.nextToken().trim();	// sixth token
				String  cast = star.nextToken().trim();	// seventh token
				String  ticketSales = star.nextToken().trim();	// eight token
				String  totalRating = star.nextToken().trim();	// ninth token
				String  ratingNum = star.nextToken().trim();	// seventh token
				String  duration = star.nextToken().trim();	// tenth token
				// create Professor object from file data
				Movie m = new Movie(movieid, movietitle, type, ratingNum, status, synopsis, director, cast, ticketSales	, totalRating, ratingNum, duration);
				// add to Professors list	
				alr.add(m) ;
                }
			}
		
		
			return alr ;
	
	}

  // an example of saving
public static void saveMovie(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				if(i==al.size()-1){
					st.append("Movie: "+m.getMovietitle().trim());
					st.append(SEPARATOR1);
					st.append("Type: "+m.getType().trim());
					st.append(SEPARATOR1);
					st.append("Rating: ").append(m.getRating().trim());
					st.append(SEPARATOR1);
					st.append("Status: ").append(m.getStatus().trim());
					st.append(SEPARATOR1);
					st.append("Synopsis: ").append(m.getSynopsis().trim());
					st.append(SEPARATOR1);
					st.append("Director: ").append(m.getDirector().trim());
					st.append(SEPARATOR1);
					st.append("Cast: ").append(m.getCast().trim());
					st.append(SEPARATOR1);
				}
				else{
					st.append(m.getMovietitle().trim());
					st.append(SEPARATOR1);
					st.append(m.getType().trim());
					st.append(SEPARATOR1);
					st.append(m.getRating().trim());
					st.append(SEPARATOR1);
					st.append(m.getStatus().trim());
					st.append(SEPARATOR1);
					st.append(m.getSynopsis().trim());
					st.append(SEPARATOR1);
					st.append(m.getDirector().trim());
					st.append(SEPARATOR1);
					st.append(m.getCast().trim());
					st.append(SEPARATOR1);	
				}
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

  /** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  {
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

  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
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
  
  // an example of saving
  public static void saveupdatedMovie(String filename, List al) throws IOException {
  		List alw = new ArrayList() ;// to store Professors data

          for (int i = 0 ; i < al.size() ; i++) {
  				Movie m = (Movie)al.get(i);
  				StringBuilder st =  new StringBuilder() ;
  			
  					st.append(m.getMovietitle().trim());
  					st.append(SEPARATOR1);
  					st.append(m.getType().trim());
  					st.append(SEPARATOR1);
  					st.append(m.getRating().trim());
  					st.append(SEPARATOR1);
  					st.append(m.getStatus().trim());
  					st.append(SEPARATOR1);
  					st.append(m.getSynopsis().trim());
  					st.append(SEPARATOR1);
  					st.append(m.getDirector().trim());
  					st.append(SEPARATOR1);
  					st.append(m.getCast().trim());
  					st.append(SEPARATOR1);	
  				
  				alw.add(st.toString()) ;
  			}
  			write(filename,alw);
  	}
}
