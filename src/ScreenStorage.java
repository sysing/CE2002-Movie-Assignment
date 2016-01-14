import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ScreenStorage extends StorageHandler{
	public static final String SEPARATOR = "|";
	@Override
	public ArrayList<Screen> readObject() throws IOException, ParseException {
		ArrayList<String> stringArray = (ArrayList)read("screen.txt");
		ArrayList<Screen> alr =new ArrayList<>();
		for(int i=0;i<stringArray.size();++i){
			String st = stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			int screenNo = Integer.parseInt(star.nextToken().trim());
			String showDate = star.nextToken().trim();
			Screen s = new Screen(screenNo,showDate);
			while(star.hasMoreTokens()){
				String seat = star.nextToken().trim();
				char rowNo = seat.charAt(0);
				int seatNo = Integer.parseInt(seat.substring(1));
				s.AssignSeat(rowNo, seatNo);
			}
			alr.add(s);
		}
		return alr;
	}
	@Override
	public void saveObject(String filename, ArrayList al) throws IOException {
		//  Auto-generated method stub
		ArrayList alw = new ArrayList() ;

	    for (int i = 0 ; i < al.size() ; i++) {
				Screen s = (Screen)al.get(i);
				StringBuilder st =  new StringBuilder() ;
					st.append(s.getScreenNo());
					st.append(SEPARATOR);
					st.append(s.getStrDate());
					st.append(SEPARATOR);
					ArrayList<String> seats = s.listFreeSeats();
					for(int j=0;j<seats.size();++j){
						st.append(seats.get(j));
						st.append(SEPARATOR);
					}
				alw.add(st.toString());
			}
			write(filename,alw);
	}
}
