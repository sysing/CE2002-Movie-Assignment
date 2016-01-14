import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BookingStorage extends StorageHandler{
	public static final String SEPARATOR = " ";
	public static final String SEPARATOR1 = "|";
	public BookingStorage(){
	}
	@Override
	public ArrayList<Booking> readObject() throws IOException, ParseException {
		ArrayList<String> stringArray = (ArrayList<String>) read("booking.txt");
		ArrayList<Booking> alr = new ArrayList<>();

        for (int i = 0 ; i <stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter ","
				String  name = star.nextToken().trim();	// first token
				String  email = star.nextToken().trim();	// second token
				int  number = Integer.parseInt(star.nextToken().trim()); // third token
				long TID = Long.parseLong(star.nextToken().trim());
				double Price = Double.parseDouble(star.nextToken().trim());
				Booking b = new Booking(name,email,number,Price,TID);
				alr.add(b);
			}
			return alr ;
	}

	@Override
	public void saveObject(String filename, ArrayList al) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> alw = new ArrayList<>() ;
	    for (int i = 0 ; i < al.size() ; i++) {
				Booking b = (Booking)al.get(i);
				StringBuilder st =  new StringBuilder() ;
					st.append(b.getName().trim());
					st.append(SEPARATOR1);
					st.append(b.getEmail().trim());
					st.append(SEPARATOR1);
					st.append(String.valueOf(b.getNumber()).trim());
					st.append(SEPARATOR1);
					st.append(String.valueOf(b.getTID()).trim());
					st.append(SEPARATOR1);
					st.append(String.valueOf(b.getPrice()).trim());
					st.append(SEPARATOR1);
				alw.add(st.toString()) ;
			}
		write(filename,alw);
	}


}