import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TicketStorage extends StorageHandler{
//----------------------------------------------------------Data Members
	public static final String SEPARATOR = "|";
//----------------------------------------------------------Member Functions
	/**
	 * Function to Read List of Tickets from a file
	 * @return List of all tickets in the file
	 * @throws IOException since the errors must be handled only at the top level
	 * @throws ParseException since the errors must be handled only at the top level
	 */
	public ArrayList<Ticket> readObject() throws IOException,ParseException {
		// read String from text file
		ArrayList<String> stringArray = read("ticket.txt");
		ArrayList<Ticket> alr = new ArrayList<>();

        for (int i = 0 ; i <stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				long TID = Long.parseLong(star.nextToken().trim());
				String  type = star.nextToken().trim();	// first token
				String  screenClass = star.nextToken().trim();	// second token
				String seat = star.nextToken().trim();	
				String date = star.nextToken().trim();
				String time = star.nextToken().trim();
				String cineplex = star.nextToken().trim();
				String movie = star.nextToken().trim();
				double price=Double.parseDouble(star.nextToken().trim());
				int screenNo = Integer.parseInt(star.nextToken().trim());
				Ticket t=new Ticket(TID,type,screenClass,seat,date,time,cineplex,movie,price);
				t.setScreenNo(screenNo);
				alr.add(t);
			}
			return alr ;
	}
	/**
	 * Function to  Write the List of tickets to the file
	 * @param filename Filename to write to 
	 * @param al List of Tickets to be written
	 */
	public void saveObject(String filename, ArrayList al) throws IOException {
		ArrayList<String> alw = new ArrayList<>() ;
		for (int i = 0 ; i < al.size() ; i++) {
			Ticket ticket= (Ticket)al.get(i);
			StringBuilder st =  new StringBuilder() ;
				st.append(ticket.getTransactionID());
				st.append(SEPARATOR);
				st.append(ticket.getType());
				st.append(SEPARATOR);
				st.append(ticket.getClassOfMovie());
				st.append(SEPARATOR);
				st.append(ticket.getTicketNo());
				st.append(SEPARATOR);
				st.append(ticket.getShowDate());
				st.append(SEPARATOR);
				st.append(ticket.getShowTime());
				st.append(SEPARATOR);
				st.append(ticket.getVenue());
				st.append(SEPARATOR);
				st.append(ticket.getMovie());
				st.append(SEPARATOR);
				st.append(ticket.getPrice());
				st.append(SEPARATOR);
				st.append(ticket.getscreenNo());
				st.append(SEPARATOR);
				alw.add(st.toString()) ;
		}
		write(filename,alw);
	}

//--------------------------------------------------------------------Unimplemented Methods
	public void saveFile() {}
	public void print(ArrayList a) {}
	
	
}