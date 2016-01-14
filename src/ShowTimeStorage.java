import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShowTimeStorage extends StorageHandler{
//----------------------------------------------------------------Data Members
public static final String SEPARATOR = "|";
SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

//---------------------------------------------------------------Member Functions
/**
 * Function to read the list of Showtimes in a particular cineplex
 * @param cineplex the cineplex to search from.
 * @return List of ShowTimes in that cinpelex for all movies.
 * @throws IOException since the errors need to be on the top level
 * @throws ParseException since the errors need to be on the top level
 */
public ArrayList<ShowTime> readObject(String cineplex) throws IOException, ParseException {
	ArrayList<String> stringArray = read("showtime.txt");
	ArrayList<ShowTime> alr = new ArrayList<>() ;
	

	
    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);			
			String movieTitle = star.nextToken().trim();	
			String cineplexName=star.nextToken().trim();
			int cinemaId = Integer.parseInt(star.nextToken().trim());	
			String date = star.nextToken().trim();
			String time = star.nextToken().trim();
	
			ShowTime show = new ShowTime(movieTitle,cineplexName,cinemaId,date,time);
			
			if(show.getCineplexName().compareTo(cineplex)==0)
			alr.add(show);
		}   
		return alr ;
}


/**
 * This is an overloaded function which returns all the Show Times in all the cineplexs
 */
public ArrayList<ShowTime> readObject() throws IOException, ParseException {
	ArrayList<String> stringArray = read("showtime.txt");
	ArrayList<ShowTime> alr = new ArrayList<>() ;

    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);			
			String movieTitle = star.nextToken().trim();	
			String cineplexName=star.nextToken().trim();
			int cinemaId = Integer.parseInt(star.nextToken().trim());	
			String date = star.nextToken().trim();
			String time = star.nextToken().trim();
	
			ShowTime show = new ShowTime(movieTitle,cineplexName,cinemaId,date,time);
			
			alr.add(show);
		}   
		return alr ;
}
/**
 * Function to Save the List of ShowTimes to the file.
 */
public void saveObject(String filename, ArrayList al) throws IOException {
	ArrayList<String> alw = new ArrayList<>() ;

    for (int i = 0 ; i < al.size() ; i++) {
			ShowTime showtime = (ShowTime)al.get(i);
			StringBuilder st =  new StringBuilder() ;
				st.append(showtime.getMovieTitle());
				st.append(SEPARATOR);
				st.append(showtime.getCineplexName());
				st.append(SEPARATOR);
				st.append(showtime.getCinemaId());
				st.append(SEPARATOR);
				st.append(showtime.getStrDate());
				st.append(SEPARATOR);
				st.append(showtime.getStrTime());
				st.append(SEPARATOR);
			alw.add(st.toString()) ;
		}
		write(filename,alw);
}

}