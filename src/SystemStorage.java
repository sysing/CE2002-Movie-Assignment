import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SystemStorage extends StorageHandler{
//------------------------------------------------------Data Members
	public static final String SEPARATOR1 = "|";
	public static final String SEPARATOR2 = ":";
	
//-------------------------------------------------------Member Functions
	
/**
 * Function to read all the prices from the file
 * @param filename the name of the file to read from
 * @return List of Prices for the different days
 * @throws IOException since the errors need to be on the top level
 */
		public ArrayList<Price> readPrice(String filename) throws IOException {
			// read String from text file
			ArrayList<String> stringArray = read(filename);
			ArrayList<Price> alr = new ArrayList<>() ;// to store Price data

	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR2);	// pass in the string to the string tokenizer using delimiter ","
	                while(star.hasMoreTokens()){
					String  pricetype = star.nextToken().trim();	// first token
					double  price = Double.parseDouble(star.nextToken().trim());	// second token
					
					// create Price object from file data
					Price p=new Price(pricetype,price);
					// add to Price list
					alr.add(p) ;
	                }
				}
			
			
				return alr ;
		
		}
		
/**
 * Function to save the updated values to the file
 * @param filename the file to save into
 * @param al List of prices to write
 * @throws IOException since the errors need to be on the top level
 */
		  public void saveupdatedPrice(String filename, ArrayList al) throws IOException {
		  		ArrayList<String> alw = new ArrayList<>() ;

		          for (int i = 0 ; i < al.size() ; i++) {
		  				Price p = (Price)al.get(i);
		  				StringBuilder st =  new StringBuilder() ;
		  			
		  					st.append(p.getPriceType().trim());
		  					st.append(SEPARATOR2);
		  					st.append(String.valueOf(p.getPrice()));
		  				
		  				
		  				alw.add(st.toString()) ;
		  			}
		  			write(filename,alw);
		  	}
 /**
  * Function to read the list of user defined holidays
  * @param filename the file name to read from 
  * @return List of holidays that need to be checked
  * @throws IOException since the errors need to be on the top level
  */
		public ArrayList<Holiday> readHoliday(String filename) throws IOException {
			// read String from text file
			ArrayList<String> stringArray = read(filename);
			ArrayList<Holiday> alr = new ArrayList<>() ;

	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter "|"
	                while(star.hasMoreTokens()){
	                int HolidayId=Integer.parseInt(star.nextToken().trim());//first token
					String  HolidayName = star.nextToken().trim();	// second token
					String  HolidayDate = star.nextToken().trim();	// third token

					
					Holiday h=new Holiday(HolidayId,HolidayName,HolidayDate);
					
					alr.add(h) ;
	                }
				}
			
			
				return alr ;
		
		}
			
	/**
	 * Function to update the List of Holidays into the file
	 * @param filename Name of the file to write into 
	 * @param al List of Holidays defined by the user
	 * @throws IOException since the errors need to be on the top level
	 */
	  public void saveupdatedHoliday(String filename, ArrayList al) throws IOException {
	  		ArrayList<String> alw = new ArrayList<>() ;// to store Professors data

	          for (int i = 0 ; i < al.size() ; i++) {
	  				Holiday h = (Holiday)al.get(i);
	  				StringBuilder st =  new StringBuilder() ;
	  						  			
	  				st.append(h.getHolidayId());
					st.append(SEPARATOR1);
					st.append(h.getHolidayName());
					st.append(SEPARATOR1);
					st.append(h.getHolidayDate());
	  				
	  				
	  				alw.add(st.toString()) ;
	  			}
	  			write(filename,alw);
	  	}
//--------------------------------------------------------------------------Methods to Implement
// These are not being used since we combine both holiday 
// and price in one class, hence require different functions for each.
	public ArrayList readObject() throws IOException, ParseException {return null;}
	public void saveObject(String filename, ArrayList al) throws IOException {}
}