import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
public class Ticket implements DisplayInterface{
	//-----------------------------------------Data Members
	private long transactionID;
	private String typeOfMovie;
	private String classOfMovie;
	private double price;
	private String ticketNo;
	private String showTime;
	private int screenNo;
	private String venue;
	private String movie;
	private String showDate;
	//-----------------------------------------Member Methods
	//-----------------------------------------Constructor
	
	/**
	 * Constuctor to initialize an object of ticket
	 * @param TransactionID Transaction Id to check with the booking during history
	 * @param Type Type of Movie for the ticket
	 * @param ClassOfMovie Gold or Platinum or whatever.
	 * @param TicketNo Seat Number
	 * @param ShowDate Date of the Show
	 * @param ShowTime Time of the show
	 * @param Venue Cineplex venue
	 * @param Movie Name of the Movie
	 * @param isStudent true if student
	 * @param isElder true if above 65 yrs
	 */
	public Ticket(long TransactionID,String Type ,String ClassOfMovie,String TicketNo,String ShowDate,String ShowTime,String Venue,String Movie,boolean isStudent,boolean isElder){
		transactionID = TransactionID;
		typeOfMovie = Type;
		classOfMovie = ClassOfMovie;
		ticketNo = TicketNo;
		showDate = ShowDate;
		showTime = ShowTime;
		venue = Venue;
		movie = Movie;
		try {
			price = getPrice(isStudent,isElder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Constructor to initialize the Ticket. This is a Parameterized overloading
	 * @param TransactionID Transaction Id to check with the booking during history
	 * @param Type Type of Movie for the ticket
	 * @param ClassOfMovie Gold or Platinum or whatever.
	 * @param TicketNo Seat Number
	 * @param ShowDate Date of the Show
	 * @param ShowTime Time of the show
	 * @param Venue Cineplex venue
	 * @param Movie Name of the Movie
	 * @param Price The price of the ticket
	 */
	public Ticket(long TransactionID,String Type ,String ClassOfMovie,String TicketNo,String ShowDate,String ShowTime,String Venue,String Movie,double Price){
		transactionID = TransactionID;
		typeOfMovie = Type;
		classOfMovie = ClassOfMovie;
		ticketNo = TicketNo;
		showDate = ShowDate;
		showTime = ShowTime;
		venue = Venue;
		movie = Movie;
		price = Price;
	}
	/**
	 * Default Constructor
	 */
	public Ticket(){
		typeOfMovie = "Regular";
		classOfMovie = "Silver";
		price = 15.00;
		ticketNo = "";
		showTime = null;
		movie =null;
	}
	
	//-----------------------------------------Member Functions
	//-----------------------------------------Get-set Methods

	public String getType(){return typeOfMovie;}
	public String getClassOfMovie(){return classOfMovie;}
	public double getPrice(){return price;}
	public String getVenue(){return venue;}
	public String getTicketNo(){return ticketNo;}
	public int getscreenNo(){return screenNo;}
	public String getShowDate(){return showDate;}
	public String getShowTime(){return showTime;}
	public String getMovie(){return movie;}
	public long getTransactionID(){return transactionID;}
	
	public void setType(String Type){typeOfMovie= Type;}
	public void setClass(String Class){classOfMovie= Class;}
	public void setVenue(String Venue){venue= Venue;}
	public void setTicketNo(String TicketNo){ticketNo= TicketNo;}
	public void setPrice(double Price){price= Price;}
	public void setScreenNo(int ScreenNo){screenNo= ScreenNo;}
	public void setShowDate(String ShowDate){showTime= ShowDate;}
	public void setShowTime(String ShowTime){showTime= ShowTime;}
	public void setMovie(String Movie){movie = Movie; }
	public void setTranactionID(long TransactionID){transactionID = TransactionID;}
	
	
	//-----------------------------------------Other Methods
	/**
	 * Function to display all the details.
	 */
	public void display(){	
		System.out.printf("|     Movie: %-30s|\n",getMovie());
		System.out.printf("|     Show Date: %-26s|\n",getShowDate());
		System.out.printf("|     Show Time: %-26s|\n",getShowTime());		
		System.out.printf("|     Screen No: %-26s|\n",getscreenNo());
		System.out.print("|     Seats : ");

	}
	/**
	 * Function to compute the price of the Ticket from the price file.
	 * @param isStudent true if Student
	 * @param isElder true if more than 65 years
	 * @return Price of the Ticket
	 * @throws IOException since errors need to be handled on the top level
	 */
	private double getPrice(boolean isStudent,boolean isElder) throws IOException{
		//To find the day of the week
		int day=Integer.parseInt(showDate.substring(0, 2));
		int month=Integer.parseInt(showDate.substring(3, 5));
		int year=Integer.parseInt(showDate.substring(6));
		int hours=Integer.parseInt(showTime.substring(0, 2));
		int minutes=Integer.parseInt(showTime.substring(3));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day, hours, minutes, 0);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) ;
		
		//Load the list of holidays and the list of prices
		ArrayList<Holiday> ListOfHolidays = new ArrayList<>();
		SystemStorage ss = new SystemStorage();
		ListOfHolidays = ss.readHoliday("Holiday.txt");
		ArrayList<Price> prices = new ArrayList<>();
		if(typeOfMovie.compareTo("3")>0){
			prices = ss.readPrice("3DPrice.txt");
		}
		else{
			prices = ss.readPrice("NormalPrice.txt");
		}
/*The Array has the Following contents
 * 0.Student before 6pm:XX
 * 1.Student after 6pm:XX
 * 2.Senior Citizen:XX
 * 3.Adults:XX
 * 4.Weekend:XX
 * 5.Platinum Suites:XX
 * 6.Public Holiday:XX
 * We use this to Find the correct index to go to.
 */

		
		//Check if it is a Holiday
		for(int i=0;i<ListOfHolidays.size();++i){
			if(ListOfHolidays.get(i).getHolidayDate().equals(showDate)){
				return prices.get(6).getPrice();
			}
		}
		
		//Check if Class is Platinum
		if(classOfMovie.compareTo("Platinum")>0){
			return prices.get(5).getPrice();
		}
		
		//Check the Day of the week and if it is a student or Elder
		if(dayOfWeek ==1 || dayOfWeek == 7){
			return prices.get(4).getPrice();
		}
		else{
			if(hours<18){
				if(isStudent){
					return prices.get(0).getPrice();
				}
				else if(isElder){
					return prices.get(2).getPrice();
				}
				else{
					return prices.get(3).getPrice();
				}
			}
			else{
				if(isStudent){
					return prices.get(1).getPrice();
				}
				return prices.get(3).getPrice();
			}
		}
	}

}