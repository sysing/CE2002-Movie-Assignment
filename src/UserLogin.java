
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
public class UserLogin {

//-------------------------------------------------Data Members	
	public Admin check=null;
	private ArrayList<Movie> ListOfMovies;
	private ArrayList<ShowTime> ListOfShowTimes;
	private ArrayList<Screen> ListOfScreen ;
	private ArrayList<Booking> ListOfBooking;
	private ArrayList<Ticket> ListOfTickets;
	public Scanner input = new Scanner(System.in);
//------------------------------------------------User Functions
	
	/**
	 * This is to load the data member into the files so that we reduce the amount of read-write files.
	 */
	private void loadData(){
		MovieStorage ms = new MovieStorage();
		ScreenStorage ss = new ScreenStorage();
		BookingStorage bs = new BookingStorage();
		TicketStorage ts = new TicketStorage();
		try {
			ListOfScreen = ss.readObject();
		} catch (IOException e) {
			
			System.out.println("Screen.txt not Found!");
		} catch (ParseException e) {
			System.out.println("Unable to Parse Screen.txt! Corrupt File!");
		}
		try {
			ListOfMovies = ms.readObject();
		} catch (IOException e) {
			System.out.println("Movie File Not Found!" + e.getMessage());
		}
		try {
			ListOfBooking= bs.readObject();
		} catch (IOException e) {
			System.out.println("Booking File Not Found!" + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Unable to Parse Booking.txt! Corrupt File!");
		}
		try {
			ListOfTickets= ts.readObject();
		} catch (IOException e) {
			System.out.println("Ticket File Not Found!" + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Unable to Parse Ticket.txt! Corrupt File!");
		}
		
	}
	
	/**
	 * Function to print the Menu
	 */
	private void printMenuOptions(){
	    System.out.println("+----Welcome To Blitzkrieg Cinemas----+");
	    System.out.println("|          1.Staff                    |");
	    System.out.println("|          2.Customer                 |");
	    System.out.println("|          3.Exit                     |");
	    System.out.println("+-------------------------------------+");
	}
	/**
	 * Function to print the Admin Menu
	 */
	private void printAdminMainOptions(){
		System.out.println("+----------------------------WELCOME ADMIN----------------------------+");
	    System.out.println("|    1.Create/Update/Remove Movie Listing                             |");
	    System.out.println("|    2.Create/Update/Remove cinema showtime and movie to be shown     |");
	    System.out.println("|    3.Configure System                                               |");
	    System.out.println("|    0.Log out                                                        |");
	    System.out.println("+---------------------------------------------------------------------+");
	}
	/**
	 * Function to add a movie object.
	 * @return true if movie has been created.
	 */
	private boolean createMovie(){
		System.out.println();
		System.out.println("Enter movie:");
	    String movietitle=input.nextLine();	    
		System.out.println("Enter type(3D,2D):");
		String type=input.nextLine();		
		System.out.println("Enter rating(PG13,U,SC18):");
		String rating=input.nextLine();				
		int status=0;						
		try{
			System.out.println();
			System.out.println("Enter status:");
			System.out.println("1) Coming Soon");
			System.out.println("2) Showing Now");
			System.out.println("3) Preview");
			do{try{
		    status=input.nextInt();
		    
		    break;
			}catch(InputMismatchException e){
				System.out.println("Please Try Again!");
				input.nextLine();
				status=-1;
			}}while(status==-1);
		    if(status!=1 &&status!=2 && status!=3){
		    	System.out.println("Please enter a value input!");
				return false;
		    }
		    }
			catch(InputMismatchException e){
				System.out.println("Please enter a value input!");	
				input.next();
				return false;
			}
		input.nextLine();				
	    System.out.println("Enter synopsis:");
	    String synopsis=input.nextLine();	    
	    System.out.println("Enter director:");
	    String director=input.nextLine();	    
	    System.out.println("Enter cast:");
	    String cast=input.nextLine();
	    int duration=0;
	    do{
	    	try{
	    System.out.println("Enter Duration(in Mins):");
	    duration=input.nextInt();
	    if(duration==0)
	    	continue;
	    	}catch (InputMismatchException e){
				System.out.println("Please enter a value input!");
				duration=-1;
				input.nextLine();
				continue;
	    	}
	    break;
	    }while(duration==-1);
	    try {
			check.CreateMovie(movietitle,type,rating,status,synopsis,director,cast,String.valueOf(duration));
			return true;
		} catch (ParseException e) {
			System.out.println("Movie Parse Error! Unable to create the Movie.");
		}
	    return false;
	}
	/**
	 * Function to edit the attributes of the object Movie
	 */
	private void editMovie(){
		int choice;
		System.out.println();
		System.out.println("+---------------------Edit Movie---------------------+");
		System.out.println("|            1.Show movies showing now               |");
		System.out.println("|            2.Show archived movies                  |");
		System.out.println("|            3.Show coming soon movies               |");
		System.out.println("|            4.Show preview soon movies              |");
		System.out.println("|            5.Show all movies                       |");
		System.out.println("+----------------------------------------------------+");
		do{try{
		choice= input.nextInt();
		break;
		}catch(InputMismatchException e){
			choice=-1;
			System.out.println("Please Try Again!");
			input.nextLine();
		}}while(choice==-1);
        try {
			check.ShowMovie(choice);
		} catch (ParseException e) {
			System.out.println("Unable to parse the Movie File!");
		}
        
        do{try{
    		choice= input.nextInt();
    		break;
    		}catch(InputMismatchException e){
    			choice=-1;
    			System.out.println("Please Try Again!");
    			input.nextLine();
    		}}while(choice==-1);
            try {
    			check.ShowMovie(choice);
            }catch(ParseException e){
            	System.out.println("Unable to parse Movie!");
            }
		if (choice==0)
			return;
		check.ChooseEditMovie(choice);
		int category=0;
		do{try{
	    System.out.println("Choose category to edit or 0 to go back:");			    
	    category=input.nextInt();
	    break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			category=-1;
			input.nextLine();
			continue;	
    	}}while(category==-1);
		
	    if(category!=0)
	    	check.UpdateMovie(category, choice);
	}
	
	/**
	 * Function to remove the Movie.
	 */
	private void removeMovie(){
		int codeToRemove=0;
		System.out.println();
		try {
			check.ShowMovie(1);
			check.ShowMovie(3);
			check.ShowMovie(4);
		} catch (ParseException e) {
			System.out.println("Unable to parse Movie file!");
		}//show all movies
		do{try{
		System.out.println("Enter movie code or 0 to return to previous menu:");				
		codeToRemove=input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			codeToRemove = -1;
			input.nextLine();
			continue;
		}}while(codeToRemove==-1);
		input.nextLine();
		if(codeToRemove!=0)
			check.RemoveMovie(codeToRemove);
	}
	/**
	 * Function to add a object of show time for a movie.
	 */
	private void createShowTime(){
		  System.out.println();
		  System.out.println("All current showtime");
		  try {
			  check.showShowTime();
		      }
		  catch (IOException e) {
			  System.out.println("Showtime File Not Found!");
		  	  } catch (ParseException e) {
			System.out.println("Unable to parse showtime!");
		}
		  System.out.println();
		  input.nextLine();
		  int cineplexId=0;
		  do{try{
		  	System.out.println("Enter cineplex");
		  	System.out.println("1.BZ Jurong Point");
			System.out.println("2.BZ Marina Square");
			System.out.println("3.BZ Suntec City");
			System.out.println("0.Return to previous menu");
			cineplexId=input.nextInt();
			break;
		  }catch(InputMismatchException e){
			System.out.println("Please Try Again!");
			cineplexId=-1;
			input.nextLine();
		  }}while(cineplexId==-1);
			
			if(cineplexId==0)
				return;
			int cinemaId=0,movieId=0;
			input.nextLine();
			do{try{
			System.out.println("Enter Screen No: ");
			cinemaId=input.nextInt();
			break;
			}catch (InputMismatchException e){
				System.out.println("Please enter a value input!");
				cinemaId=-1;
				input.nextLine();
				continue;
			}}while(cinemaId==-1);
			
			//show movies showing now
			try {
				check.ShowMovie(1); //Shows only running movies
				System.out.println();
				check.ShowMovie(4);//Show only Preview Movies
			} catch (ParseException e) {
				System.out.println("Unable to parse!");
			}
			input.nextLine();
			do{try{
			System.out.println("Enter movie ID: ");
			movieId=input.nextInt();
			break;
			}catch (InputMismatchException e){
				System.out.println("Please enter a value input!");
				movieId=-1;
				input.nextLine();
				continue;
			}}while(movieId==-1);
			input.nextLine();
			System.out.println("Enter time in hh:mm");
			String time=input.nextLine();
			
			try {
				check.createShowTime(cineplexId, cinemaId, movieId, time);
			} catch (ParseException e) {
				System.out.println("Unable to parse Showtime!");
			}
	}
	/**
	 * Function to update the attributes of the show time of a movie.
	 */
	private void updateShowTime(){
		int choice=0;
		try {
			System.out.println();
			check.showShowTime();
		} catch (IOException e) {
			System.out.println("Showtime File Not Found!");
		} catch (ParseException e) {
			System.out.println("Unable to parse Showtime!");
		}
		input.nextLine();
		do{try{
		System.out.println("Enter show time ID you wish to edit: ");
		System.out.println("OR");
		System.out.println("Enter 0 to return to previous menu");
		choice=input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			choice=-1;
			input.nextLine();
			continue;
		}}while(choice ==-1);
		if (choice==0)
			return;
		try {
			check.EditShowTime(choice);
		} catch (ParseException e) {
			System.out.println("Unable to parse showtime!");
		}			  
		return;
	  
	}
	/**
	 * Function to remove a show time of a movie.
	 */
	private void removeShowTime(){
		int choice=0;
		System.out.println("All current showtime");
		 try {
		 check.showShowTime();
		 } 	catch (IOException e) {
			 System.out.println("Showtime file not found!");
		 } catch (ParseException e) {
			 System.out.println("Unable to Parse showtime!");
		 }
		input.nextLine();
		do{try{
		System.out.println("Enter show time ID you wish to delete: ");
		System.out.println("OR");
		System.out.println("Enter 0 to return to previous menu");
		choice=input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			choice =-1;
			input.nextLine();
			continue;
		}}while(choice==-1);
		
		if (choice==0)
			return;
		try {
			check.DeleteShowTime(choice);
		} catch (ParseException e) {
			System.out.println("Unable to parse Showtime!");
		}		
		
	}
	/**
	 * Function to display the movies that are showing or being previewed
	 */
	private void displayShowingMovies(){
		//System.out.println("//////////////////////////////////////////////////");
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("|%-8s| %-30s| %-6s| %-15s|","Movie Id","Name","Rating","Status");
		System.out.println();
		System.out.println("+-----------------------------------------------------------------+");
		//System.out.println("// Movie Id	Movie Name	Rating 	Status//");
		//System.out.println("//----------------------------------------------//");
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getStatus().equals("Now Showing")||temp.getStatus().equals("Preview")){
				System.out.printf("|   %-5d| %-30s|   %-3.2f| %-15s|",temp.getMovieId(),temp.getMovietitle(),temp.getAvgRating(),temp.getStatus());
				System.out.println();
				//System.out.println("//    "+temp.getMovieId()+"		"+temp.getMovietitle()+"	"+temp.getAvgRating()+"	"+temp.getStatus()+"	//");
			}
		}
		System.out.println("+-----------------------------------------------------------------+");
	}
	/**
	 * Function to go through the List of Movies and pick the Movie
	 * @return object movie you select.
	 */
	private Movie chooseMovie(){
		int movieChoice =0;
		displayShowingMovies();
		do{try{
		System.out.println("Please Choose the Movie ID:");
		movieChoice = input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			movieChoice=-1;
			input.nextLine();
			continue;
		}}while(movieChoice==-1);
		int movieI = 0;
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getMovieId()== movieChoice){
				return temp;
			}
		}
		return null;
	}
	/**
	 * Function to choose the cineplex location
	 * @return Location of the cineplex  
	 */
	private String chooseCinePlex(){
		int cineplexChoice=0;
		System.out.println("+------------------------------+");
		System.out.printf("|%-3s| %-25s|","Sno","Venue");
		System.out.println();
		System.out.println("+------------------------------+");
		System.out.printf("| %-2d| %-25s|",1,"BZ Jurong Point");
		System.out.println();
		System.out.printf("| %-2d| %-25s|",2,"BZ Marina Square");
		System.out.println();
		System.out.printf("| %-2d| %-25s|",3,"BZ Suntec City");
		System.out.println();
		System.out.println("+------------------------------+");
		do{try{
		System.out.println("Choose CinePlex:");
		cineplexChoice = input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			cineplexChoice=-1;
			input.nextLine();
			continue;
		}}while(cineplexChoice==-1);
		String Cineplex= null;
		switch(cineplexChoice){
		case 1:
			Cineplex= "BZ Jurong Point";
			break;
		case 2:
			Cineplex= "BZ Marina Square";
			break;
		case 3:
			Cineplex= "BZ Suntec City";
			break;
		}
		return Cineplex;
	}
	/**
	 * This loads the showTimes available in a particular cinemplex
	 * @param CinePlex Cineplex which we want the show times
	 */
	private void loadShowTimes(String CinePlex){
		ShowTimeStorage sts = new ShowTimeStorage();
		try {
			ListOfShowTimes = sts.readObject(CinePlex);
			
		} catch (IOException e) {
			System.out.println("Showtime File Not Found!");
		} catch (ParseException e) {
			System.out.println("Unable to parse Showtime!");
		}
		return;
	}
	/**
	 * Function to assign the seats in a cineplex for a movie and return the showtime
	 * @param CinePlex cineplex selected
	 * @param movie movie selected
	 * @return List of showtime which are alloted
	 */
	private ArrayList<ShowTime> loadMovieShowTime(String CinePlex,Movie movie){
		loadShowTimes(CinePlex);
		ArrayList<ShowTime>ListOfMovieTimes = new ArrayList<>();
		for(int i=0;i<ListOfShowTimes.size();++i){
			ShowTime temp = (ShowTime) ListOfShowTimes.get(i);
			if(temp.getMovieTitle().compareTo(movie.getMovietitle())==0){
				ListOfMovieTimes.add(temp);
			}
		}
		return ListOfMovieTimes;
	}
	/**
	 * Function to choose the showTime from a list of showtimes in a cineplex
	 * @param CinePlex cineplex selected
	 * @param movie selected moive
	 * @param ShowDate Date for which the movie is aired
	 * @return Object with the selected showtime details
	 */
	private ShowTime chooseShowTime(String CinePlex , Movie movie,String ShowDate){
		ArrayList<ShowTime> ListOfMovieTimes = loadMovieShowTime(CinePlex, movie);
		int showTimeChoice=0;
		if(ListOfMovieTimes.size()!=0){
		for(int i=0;i<ListOfMovieTimes.size();++i){
			System.out.println("("+(i+1)+") Show Time: "+((ShowTime)ListOfMovieTimes.get(i)).getStrTime());
		}
		do{try{
		System.out.println("Choose ShowTime:");
		showTimeChoice = input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			showTimeChoice=-1;
			input.nextLine();
			continue;
		}}while(showTimeChoice==-1);
		ListOfMovieTimes.get(showTimeChoice-1).setDate(ShowDate);
		return ListOfMovieTimes.get(showTimeChoice-1);
		}
		else{
			System.out.println("Sorry No Shows Available!");
			return null;
		}
	}
	/**
	 * Function to get the screen in a cineplex
	 * @param screenNo the hall number where the movie will happen
	 * @param ShowDate the date on which the movie will be aired
	 * @return the status of the screen with all the free and filled seats.
	 */
	private Screen chooseScreen(int screenNo,String ShowDate){
		if(ListOfScreen.size()!=0){
			for(int i=0;i<ListOfScreen.size();++i){
				if((ListOfScreen.get(i).getScreenNo()== screenNo)&&(ListOfScreen.get(i).getStrDate().equals(ShowDate)))
					return ListOfScreen.get(i);
			}
		}
		ListOfScreen.add(new Screen(screenNo, ShowDate));
		return ListOfScreen.get(ListOfScreen.size()-1);
	}
	/**
	 * Function to be able to choose the seats to assign for a screen and return the ticket details
	 * @param screen selected cinema id which has the status of free and assigned seats
	 * @param noOfTickets the number of tickets to assign
	 * @param movie details of the movie airing
	 * @param showTime details of the showtime airing
	 * @return The list of details of the tickets for the seats assigned. 
	 */
	private ArrayList<Ticket> chooseTickets(Screen screen , int noOfTickets,Movie movie,ShowTime showTime){
		int seatno=0;
		char rowNo,details;
		Calendar cal = Calendar.getInstance();
		String TID = String.valueOf(screen.getScreenNo()).concat(String.valueOf(cal.get(Calendar.YEAR))).concat(String.format("%02d",cal.get(Calendar.MONTH))).concat(String.format("%02d",cal.get(Calendar.DAY_OF_MONTH))).concat(String.format("%02d",cal.get(Calendar.HOUR_OF_DAY))).concat(String.format("%02d",cal.get(Calendar.MINUTE))); 
		long TransID = Long.valueOf(TID.toString());
		if(screen.getSeatsFree() <noOfTickets){
			
			System.out.println("There is only"+screen.getSeatsFree()+" seats free! Please try again!" );
		}else{
		ArrayList<Ticket> ListOfTickets = new ArrayList<>();
		for(int i=0;i<noOfTickets;++i){
		screen.printScreen();
		System.out.println("Enter the Row character:");
		rowNo= input.next().charAt(0);
		do{try{
		System.out.println("Enter the Seat Number:");
		seatno = input.nextInt();
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			seatno=-1;
			input.nextLine();
			continue;
		}}while(seatno==-1);
		boolean isStudent = false, isElder = false;
		if(screen.AssignSeat(rowNo, seatno)){
			input.nextLine();
			do{
			System.out.println("Are You a Student(Y/N)");
			details =input.next().charAt(0);
			if(details=='Y'){
				isStudent = true;
				break;
			}else{
				System.out.println("Are you above 65? (Y/N)");
				details = input.next().charAt(0);
				if(details=='Y'){
					isElder = true;
					break;
				}
			}
			}while(details!='Y'||details!='N');
			Ticket ticket = new Ticket(TransID,movie.getType(), "Platinum", String.valueOf(rowNo).concat(String.valueOf(seatno)),showTime.getStrDate() , showTime.getStrTime(), showTime.getCineplexName(), showTime.getMovieTitle(), isStudent, isElder);
			ticket.setScreenNo(screen.getScreenNo());
			System.out.println("Seat Assigned!");
			ListOfTickets.add(ticket);
		}else{
			System.out.println("Please Try Again!");
			i--;
		}
		}
		if(updateScreen(screen)){
			return ListOfTickets;	
		}
		}
		return null;
	}
	/**
	 * Function to update the screen(Add to the list of screens which is written to the file
	 * @param screen the screen to update
	 * @return true if update was successful
	 */
	private boolean updateScreen(Screen screen){
		ScreenStorage ss = new ScreenStorage();
		for(int i=0;i<ListOfScreen.size();++i){
			if(ListOfScreen.get(i).getScreenNo()==screen.getScreenNo()){
				ListOfScreen.set(i, screen);
				break;
			}
		}
		try {
			ss.saveObject("screen.txt", ListOfScreen);
			return true;
		} catch (IOException e){
			System.out.println("Screen File Not Found!");
		}
		return false;
	}
	/**
	 * Function to compute the total price of the tickets
	 * @param tickets tickets for the movie
	 * @return price to be paid for the tickets 
	 */
	private double calculatePrice(ArrayList<Ticket> tickets){
		float total= 0;
		for(int i=0;i<tickets.size();++i){
			total+=tickets.get(i).getPrice();
		}
		return total;
	}
	/**
	 * Function to create the Booking invoice for the purchased tickets
	 * @param tickets tickets that were bought
	 * @return object with details of the booking
	 */
	private Booking chooseBooking(ArrayList<Ticket> tickets){
		String name,email;
		int number=0;
		input.nextLine();
		System.out.println("Please Enter Your Details");
		System.out.println("NAME: ");
		name = input.nextLine();
		do{
		System.out.println("Email: ");
		email = input.nextLine();
		if(email.indexOf('@')>0 &&email.indexOf('.')>0)
			break;
		}while(true);
		do{try{
		System.out.println("Phone Number: ");
		number = input.nextInt();
		if(number/10000000<=0)
			continue;
		break;
		}catch (InputMismatchException e){
			System.out.println("Please enter a value input!");
			number=-1;
			input.nextLine();
			continue;
		}}while(number==-1);
		TicketStorage ts = new TicketStorage();
		try {
			ListOfTickets.addAll(tickets);
			ts.saveObject("ticket.txt",ListOfTickets);
		} catch (IOException e) {
			System.out.println("Ticket File not found!");
		}
		Booking booking = new Booking(name, email, number,calculatePrice(tickets),tickets.get(0).getTransactionID());
		return booking;
	}
	/**
	 * Function to Print the Invoice of the Booking the tickets
	 * @param booking the booking details to be printed
	 * @param tickets the ticket details to be printed
	 */
	private void printBooking(Booking booking,ArrayList<Ticket> tickets){
		// Create pretty print of Booking
		booking.display();
		System.out.println("+--------------Ticket Details--------------+");
		tickets.get(0).display();	
		for(int i=0;i<tickets.size();++i){
			System.out.print(tickets.get(i).getTicketNo()+" ");
		}
		System.out.println();
		System.out.println("+-----------------Payment Details-------------------+");
		System.out.println("   Total Price: $"+calculatePrice(tickets)+" /-      ");
		System.out.println("|  Thanks For Shopping with us!Hope to see you soon!| ");
		System.out.println("+---------------------------------------------------+");
		
	}
	/**
	 * Function to display the Movies with a full description.
	 */
	private void displayDetailMovie(){
		System.out.println("+----------------Movies on Show!----------------+");
		System.out.println("|-----------------------------------------------|");
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getStatus().equals("Now Showing")||temp.getStatus().equals("Preview")){
				temp.display();	
			}
		}
	}
	
	/**
	 * Function to update the attributes of the movie.
	 * @param movie the object of the movie which will be updated in the movie
	 */
	private void updateList(Movie movie){
		for(int i=0;i<ListOfMovies.size();++i){
			if(ListOfMovies.get(i).getMovieId()==movie.getMovieId()){
				ListOfMovies.set(i, movie);
				break;
			}
		}
		MovieStorage ms  = new MovieStorage();
		try {
			ms.saveObject("movie.txt", ListOfMovies);
		} catch (IOException e) {
			System.out.println("Movie File not Found!");
		}
	}
	
	/**
	 *Function to display the reviews for a movie 
	 * @param MovieId The movie for which the reviews must be displayed
	 */
	private void displayReview(int MovieId){
		for(int i=0;i<ListOfMovies.size();++i){
			if(ListOfMovies.get(i).getMovieId()==MovieId){
				if(ListOfMovies.get(i).getReviews().size()==0){
					System.out.println("Sorry There are no Reviews :( !");
				}
				else{
					System.out.println("Reviews for "+ListOfMovies.get(i).getMovietitle());
					for(int j=0;j<ListOfMovies.get(i).getReviews().size();++j){
						System.out.println("Rating: "+ ListOfMovies.get(i).getReviews().get(j).getReviewRating());
						System.out.println("Review: "+ ListOfMovies.get(i).getReviews().get(j).getReviewText());
						System.out.println("----------------------------------------------------------");
					}
				}
			}
		}
	}
	/**
	 * Function to update the movie Sales into the Movies and also to store into the file
	 * @param movie the movie for which the ticketsales increases
	 * @param ticketSales the number of tickets to increase by
	 */
	private void updateMovieSales(Movie movie , int ticketSales){
		for(int i=0;i<ListOfMovies.size();++i){
			if(movie.getMovieId()==ListOfMovies.get(i).getMovieId()){
				ListOfMovies.get(i).incTicketSales(ticketSales);
				MovieStorage ms = new MovieStorage();
				try {
					ms.saveObject("movie.txt", ListOfMovies);
				} catch (IOException e) {
					System.out.println("Movie File not found!");
				}
			}
		}
	}
	
	/**
	 * Function to update the Booking List and to store into the file 
	 * @param booking
	 */
	private void updateBooking(Booking booking){
		BookingStorage bs = new BookingStorage();
		ListOfBooking.add(booking);
		try {
			bs.saveObject("booking.txt", ListOfBooking);
		} catch (IOException e) {
			System.out.println("Booking File Not Found!");
		}
	}
	/**
	 * Function to show the booking history given their phone number
	 * @param phoneNo number to retreive the numbers
	 */
	private void checkBooking(int phoneNo){
		boolean flag= true;
		  for(int i=0;i<ListOfBooking.size();++i){
			  if(ListOfBooking.get(i).getNumber()==phoneNo){
				  flag = false;
				  ListOfBooking.get(i).display();
				  System.out.println("-----------------Ticket Details-----------------");
				  for(int j=0;j<ListOfTickets.size();++j){
					  if(ListOfTickets.get(j).getTransactionID()==ListOfBooking.get(i).getTID()){
						  ListOfTickets.get(j).display();
						  System.out.println(ListOfTickets.get(j).getTicketNo());
						  System.out.println("------------------------------------------------");
					  }
				  }
			  }
		  }
		  if(flag){
			  System.out.println("Sorry! No such Number exists!");
		  }
	}
	/**
	 * Function to search the list of movies
	 * @param movieSearch the string to search for.
	 */
	private void searchMovie(String movieSearch){
		boolean flag =true;
	for(int i=0;i<ListOfMovies.size();++i){
		if(ListOfMovies.get(i).getMovietitle().contains(movieSearch)&&((ListOfMovies.get(i).getStatus().equals("Now Showing"))||(ListOfMovies.get(i).getStatus().equals("Preview")))){
			flag = false;
			ListOfMovies.get(i).display();
		}
	}
	if(flag){
		System.out.println("Sorry! Such a movie name does not exist.");
	}
	System.out.println();
	}
	
	/**
	 * Function to print the top five sales
	 */
	private void listTopFiveSales(){
		ArrayList<Movie> MoviesArray= new ArrayList<>();
		for(int i=0;i<ListOfMovies.size();++i){
			if(ListOfMovies.get(i).getStatus().equals("Now Showing")||ListOfMovies.get(i).getStatus().equals("Preview")){
				MoviesArray.add(ListOfMovies.get(i));
			}
		}
		for(int i=0;i<MoviesArray.size();++i){
			for(int j=i+1;j<MoviesArray.size();++j){
				if(MoviesArray.get(i).getTicketSales() < MoviesArray.get(j).getTicketSales()){
					Movie temp = MoviesArray.get(i);
					MoviesArray.set(i, MoviesArray.get(j));
					MoviesArray.set(j,temp);
				}
			}
		}
		System.out.println("+------------------------------------------------------------------+");
		System.out.printf("|%-3s| %-30s| %-12s| %-15s|","Sno","Name","Ticket Sales","Status");
		System.out.println();
		System.out.println("+------------------------------------------------------------------+");
		for(int i=0;i<MoviesArray.size()&&i<5;++i){
			System.out.printf("| %-2d| %-30s|%6s%-7d| %-15s|\n",(i+1),MoviesArray.get(i).getMovietitle(),"",MoviesArray.get(i).getTicketSales(),MoviesArray.get(i).getStatus());
		}
		System.out.println("+------------------------------------------------------------------+");
		System.out.println();
	}
	
	/**
	 * Function to List the top five rating
	 */
	private void listTopFiveRating(){
		ArrayList<Movie> MoviesArray= new ArrayList<>();
		for(int i=0;i<ListOfMovies.size();++i){
			if(ListOfMovies.get(i).getStatus().equals("Now Showing")||ListOfMovies.get(i).getStatus().equals("Preview")){
				MoviesArray.add(ListOfMovies.get(i));
			}
		}
		for(int i=0;i<MoviesArray.size();++i){
			for(int j=i+1;j<MoviesArray.size();++j){
				if(MoviesArray.get(i).getAvgRating()<MoviesArray.get(j).getAvgRating()){
					Movie temp = MoviesArray.get(i);
					MoviesArray.set(i, MoviesArray.get(j));
					MoviesArray.set(j,temp);
				}
			}
		}
		System.out.println("+------------------------------------------------------------+");
		System.out.printf("|%-3s| %-30s| %-6s| %-15s|","Sno","Name","Rating","Status");
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		for(int i=0;i<MoviesArray.size()&&i<5;++i){
			System.out.printf("| %-2d| %-30s|   %-3.2f| %-15s|\n",(i+1),MoviesArray.get(i).getMovietitle(),MoviesArray.get(i).getAvgRating(),MoviesArray.get(i).getStatus());
		}
		System.out.println("+------------------------------------------------------------+");
		System.out.println();
	}
	/**
	 * Function to check if a date is valid and is after the current date
	 * @param dateToValidate
	 * @return true if valid
	 */
	private boolean checkShowDate(String dateToValidate){
		if(dateToValidate == null){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		try {
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			if(date.after(new Date())){
				return true;
			}
			else{
				System.out.println("This date is already gone!Sorry!");
				return false;
			}
		
		} catch (ParseException e) {
			System.out.println("Please enter a valid Date!");
			return false;
		}	
	}
	/**
	 * Main UI of the User.
	 * @throws ParseException since we have already checked all the places.
	 */
	public void Login() throws ParseException {
		Scanner input = new Scanner(System.in);
		String filename = "admin1.txt" ;
	    String username,password;
	    int choice=0,choice1;
	    int mainOption=0,subOption=0;
	    loadData();
//	    login function
	    do{
	    	this.printMenuOptions();
	    	do{try{
			  System.out.print("Choice: ");
			  choice = input.nextInt();
			  break;
	    	}catch(InputMismatchException e){
	    		System.out.println("Enter a correct Value");
	    		choice=-1;
	    		input.nextLine();
	    		continue;
	    	}}while(choice==-1);
			  switch(choice){
			  case 1:
				  do{
					System.out.println("+----------------------------------+");
		        	System.out.println("|           Log in                 |");
		        	System.out.println("|----------------------------------+");
		        	System.out.print("|          Username:");
		      	    username = input.next();
		      	    System.out.println("|----------------------------------|");
		      	    System.out.print("|        Password: ");
		      	    password = input.next();
		      	    System.out.println("+----------------------------------+");
		      	    check = new Admin(username, password);
		      	    if (check.auth()!=true)
		      	    		System.out.println("Please enter a valid id/password");
		      	   
		        }while(check.auth()!=true);		  

			    if(check.auth()){ 
			        System.out.println("You are logged in");
			        System.out.println();
			    do{	    
			    printAdminMainOptions();
			    do{try{
			    	System.out.println("Choice: ");
			    mainOption = input.nextInt();
			    break;
			    }catch(InputMismatchException e){
			    	System.out.println("Please Try Again!");
			    	mainOption=-1;
			    	input.nextLine();
			    }}while(mainOption==-1);
			    input.nextLine();
				switch(mainOption){
				
				case 1:
					do{   System.out.println();
						  System.out.println("+----------------------------------+");
				    	  System.out.println("|       1.Create movie             |");
						  System.out.println("|       2.Update movie             |");
						  System.out.println("|       3.Remove Movie             |");
						  System.out.println("|       0.Return to previous menu  |");
						  System.out.println("+----------------------------------+");
						  do{try{
						  System.out.print("Choice:");
						  subOption = input.nextInt();
						  break;
						  }catch(InputMismatchException e){
							  System.out.println("Please Try Again!");
							  subOption=-1;
							  input.nextLine();
						  }}while(subOption==-1);
						  
						  
					switch(subOption){
					//create movie
					case 1:
						if(!createMovie())
							continue;
						else
							createShowTime();
			            break;
			            	            
			        //edit movie    
					case 2:
						editMovie();
					    break;
					//delete movie (set movie to end of showing)    
					case 3:
						removeMovie();
						break;
					//return admin main menu
					case 4:
						break;
					case 0:
						break;
					default:
			 			System.out.println("Please enter a valid choice");
			 			break;
					}
					}while(subOption!=0);
					break;
				
					
				//create update remove ShowTime
				case 2:
					  do{
					  System.out.println();
					  System.out.println("+----------------------------------+");
			    	  System.out.println("|  1.Create showtime for movie     |");
					  System.out.println("|  2.Update showtime for movie     |");
					  System.out.println("|  3.Remove showTime for movie     |");
					  System.out.println("|  0.Return to previous menu       |");
					  System.out.println("+----------------------------------+");
					  do{try{
						  System.out.print("Choice:");
						  choice1 = input.nextInt();
					  break;
					  }catch(InputMismatchException e){
						  choice1=-1;
						  System.out.println("Please Try Again!");
						  continue;
					  }}while(choice1==-1);
					  input.nextLine();
					  
					  switch(choice1){			  
					  //create ShowTime
					  case 1:			   
						  createShowTime();
						  break;
					  //update ShowTime	
					  case 2:
						  updateShowTime();
						  break;
					  //remove ShowTime
					  case 3:
						  System.out.println();
						  removeShowTime();
						  break;
							
					  case 4:
						  break;
					  case 0:
						  break;
					default:
						 System.out.println("Please enter a valid option");
						 break;
							 }		
					  }	while(choice1!=0);
					  break;
				case 3:	
					// Configure
					int tickettype;
					int choice13;
					do
					{
						System.out.println("+---------------------------------------------+");
						System.out.println("|    Please choose setting to configure:      |");
						System.out.println("|            1.Ticket Price Rate              |");
						System.out.println("|            2.Public Holidays                |");
						System.out.println("|            0.Back                           |");
						System.out.println("+---------------------------------------------+");
					do{try{
						System.out.print("Choice: ");
						choice13 = input.nextInt();
						break;
					}catch(InputMismatchException e){
						System.out.println("Please Try Again!");
						choice13=-1;
						input.nextLine();
						continue;
					}}while(choice13==-1);
					input.nextLine();
					switch(choice13)
					{
					case 1:
						do
						{
							Admin p=new Admin();
							System.out.println("+-------------------------------------+");
							System.out.println("|          1)Normal Price             |");
							System.out.println("+-------------------------------------+");
							System.out.printf("|  %-25s | %-6s |\n","Price Type","Price");
							p.ShowNormalPrice();
							System.out.println("+-------------------------------------+");
							System.out.println("|          2)3D Price                 |");
							System.out.println("+-------------------------------------+");
							System.out.printf("|  %-25s | %-6s |\n","Price Type","Price");
							p.Show3DPrice();
							System.out.println("+-------------------------------------+");
						    System.out.println("|          3) Back                    |");
							System.out.println("+-------------------------------------+");
							do{try{
								System.out.println("Please select index for the type of ticket price you want to change:");
								tickettype=input.nextInt();
							break;
							}catch(InputMismatchException e){
								tickettype=-1;
								System.out.println("Please Try Again!");
								input.nextLine();
								continue;
							}}while(tickettype==-1);
							input.nextLine();
							if(tickettype==1)
							{
								System.out.println("+------------Normal Price-------------+");
								System.out.printf("|  %-25s | %-6s |\n","Price Type","Price");
								p.ShowNormalPrice();
								System.out.println("+-------------------------------------+");
								p.ShowPriceAttribute(tickettype);
								int ticketattribute=0;
								do{try{
								System.out.println("Select Attribute to change:");
								ticketattribute=input.nextInt();
								break;
								}catch(InputMismatchException e){
									ticketattribute=-1;
									System.out.println("Please Try Again!");
									input.nextLine();
									continue;
								}}while(ticketattribute==-1);
								input.nextLine();
								p.UpdateNormalPrice(ticketattribute);
								System.out.println("Ticket Price Updated");
							}		
							if(tickettype==2)
							{
								System.out.println("+--------------3D Price---------------+");
								System.out.printf("|  %-25s | %-6s |\n","Price Type","Price");
								p.Show3DPrice();
								System.out.println("+-------------------------------------+");
								p.ShowPriceAttribute(tickettype);
								int ticketattribute=0;
								do{try{
									System.out.println("Select Attribute to change:");
								ticketattribute=input.nextInt();
								break;
								}catch(InputMismatchException e){
									ticketattribute=-1;
									System.out.println("Please Try Again!");
									input.nextLine();
									continue;
								}}while(ticketattribute==-1);
								input.nextLine();
								p.Update3DPrice(ticketattribute);
								System.out.println("Ticket Price Updated");
							}	
						}while(tickettype<3);
						break;
					case 2:
						int holidaychoice;
						do
						{
							Admin holiday=new Admin();
							System.out.println("+----Current List of Holiday-------------+");
							System.out.printf("|%-2s|%-25s| %-8s|\n","ID","Name","Date");
							System.out.println("|----------------------------------------|");
							holiday.ShowHoliday();
							System.out.println("+----------------------------------------+");
							System.out.println();
							System.out.println("+-----------------------------------+");
							System.out.println("|     1.Create New Public Holiday   |");
							System.out.println("|     2.Remove a Public Holiday     |");
							System.out.println("|     3.Back                        |");
							System.out.println("+-----------------------------------+");
							do{try{
							System.out.println("Enter Choice:");
							holidaychoice=input.nextInt();
							break;
							}catch(InputMismatchException e){
								holidaychoice=-1;
								System.out.println("Please Try Again!");
								input.nextLine();
								continue;
							}}while(holidaychoice==-1);
							input.nextLine();
							switch(holidaychoice)
							{
							case 1:
								System.out.println("Please enter a Date(dd-mm-yyyy):");
								String holidaydate=input.nextLine();
								System.out.println("Please enter Holiday Name:");
								String holidayname=input.nextLine();
								Admin d=new Admin();
								d.CreateHoliday(holidayname, holidaydate);
								System.out.println("Holiday Created!");
								break;
							case 2:
								Admin rh=new Admin();
								System.out.println("+----Current List of Holiday-------------+");
								System.out.printf("|%-2s|%-25s| %-8s|\n","ID","Name","Date");
								System.out.println("|----------------------------------------|");
								holiday.ShowHoliday();
								System.out.println("+----------------------------------------+");
								System.out.println();
								int idtoremove=0;
								do{try{
								System.out.println("Enter holiday id to remove:");
								idtoremove=input.nextInt();
								break;
								}catch(InputMismatchException e){
									idtoremove=-1;
									System.out.println("Please Try Again!");
									input.nextLine();
									continue;
								}}while(idtoremove==-1);
								rh.RemoveHoliday(idtoremove);
								System.out.println("Holiday Removed");
								break;
							
							}		
						}while(holidaychoice<3);
						break;
					case 0:
						break;
					default:
			 			System.out.println("Please enter a valid choice");
					}
					}while(choice13!=0);
					break;	
				case 0:
					break;
				default:
		 			System.out.println("Please enter a valid choice");
		 			break;
				}
		
			}while(mainOption!=0);
	    
			    }
			break;
			
		case 2:
			  int custOption=0;
			  do{
			  System.out.println();
			  System.out.println("+--------------------MAIN MENU--------------------+");
			  System.out.println("|        1) Make a booking                        |");
			  System.out.println("|        2) View history                          |");
			  System.out.println("|        3) List top 5 Movies by ticket sales     |");
			  System.out.println("|        4) List top 5 Movies by Rating           |");
			  System.out.println("|        5) Search/List Movies                    |");
			  System.out.println("|        6) View all Movies with information      |");
			  System.out.println("|        7) Rate and Review a Movie               |");
			  System.out.println("|        0) Exit                                  |");
			  System.out.println("+-------------------------------------------------+");
			  do{try{
			  System.out.println("Please enter your choice");
			  custOption=input.nextInt();
			  break;
			  }catch(InputMismatchException e){
				  System.out.println("Please Try Again!");
				  custOption =-1;
				  input.nextLine();
				  continue;
			  }}while(custOption==-1);
			  if(custOption==0){
				  System.out.println("Program Terminating...");
				  System.exit(0);
			  }
			  else if(custOption==1){
				Movie movie=null;
				String Cineplex=null;
				do{
					System.out.println();
					System.out.println("+-----------Booking Menu-----------+");
					System.out.println("|       1) Choose by Movie         |");
					System.out.println("|       2) Choose by CinePlex      |");
					System.out.println("+----------------------------------+");
					do{try{
					System.out.println("Choice: ");
					subOption = input.nextInt();
					break;
					}catch(InputMismatchException e){
						System.out.println("Please Try Again!");
						subOption=-1;
						input.nextLine();
						continue;
					}}while(subOption==-1);
					if(subOption==1){
					//Display Showing movies
					movie=chooseMovie();
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					Cineplex = chooseCinePlex();
					// Choose Date
					}
				else if(subOption==2){
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					Cineplex = chooseCinePlex();
					//Display Showing movies
					movie=chooseMovie();
				}
				else{
					continue;
				}
				}while(subOption>2 && movie!=null && Cineplex!=null);
					//ShowDate
					String showDate;
					int noOfTickets=0;
					do{
					System.out.println("Enter the date for Shows(dd-mm-yyyy):");
					showDate = input.next();
					}while(!checkShowDate(showDate));
					// Display Showtimes and Availablity of seats
					ShowTime showTime = chooseShowTime(Cineplex,movie,showDate);
					if(showTime==null){
						System.out.println();
						continue;
					}
					//Go to Seating arrangement to book the seats
					System.out.println("Please Choose your Seats ");
					int screenNo = showTime.getCinemaId();
					Screen screen = chooseScreen(screenNo,showDate);
					System.out.println("No of free seats: "+screen.getSeatsFree());
					do{try{
					System.out.println("Enter the No of Tickets: ");
					noOfTickets = input.nextInt();
					break;
					}catch(InputMismatchException e){
						System.out.println("Please try Again!");
						noOfTickets=-1;
						input.nextLine();
					}
					}while(noOfTickets==-1);
					ArrayList<Ticket> tickets= chooseTickets(screen,noOfTickets,movie,showTime);
					// Book Tickets and Get Details for ticket and print inventory.
					Booking booking  = chooseBooking(tickets);
					updateMovieSales(movie,tickets.size());
					updateBooking(booking);
					printBooking(booking,tickets);
				}
			  else if (custOption ==2){
				  int phoneNo=0;
				  input.nextLine();
				  do{
				  do{try{
				  System.out.println("Enter you Phone No or 0 to go back: ");
				  phoneNo = input.nextInt();
				  break;
				  }catch(InputMismatchException e){
					  System.out.println("Please Try Again!");
					  phoneNo=-1;
					  input.nextLine();
					  continue;
				  }}while(phoneNo==-1);
				  if(phoneNo!=0)
					  checkBooking(phoneNo);
				  }while(phoneNo!=0);
			  }
			  else if( custOption==3){
				  listTopFiveSales();
				  input.nextLine();
			  }
			  else if( custOption==4){
				  listTopFiveRating();
				  input.nextLine();
			  }
			  else if( custOption==5){
				  String movieSearch;
				  System.out.println("+-----------Search Movies-------------+");
				  input.nextLine();
				  do{
				  System.out.println("Enter the name of the Movie to Search or 0 to exit: ");
				  movieSearch = input.nextLine();
				  if(!movieSearch.equals("0"))
					  searchMovie(movieSearch);
				  }while(!movieSearch.equals("0"));
			  }
			  else if( custOption==6){
					int movieID,choice61=1;
					do{
					displayDetailMovie();
					do{try{
						System.out.println("Enter the Movie ID for Seeing thier Reviews or 0 to go back!");
						movieID = input.nextInt();
					break;
					}catch(InputMismatchException e){
						movieID=-1;
						System.out.println("Please Try Again!");
						input.nextLine();
					}}while(movieID!=-1);
					if(movieID!=0){
					do{
						displayReview(movieID);
						System.out.println("Press 0 to go back!");
						try{
						choice61 = input.nextInt();
						break;
						}catch(InputMismatchException e){
							System.out.println("Please Try Again!");
							input.nextLine();
							choice=-1;
						}
					}while(choice61!=0);
					}
					}while(movieID!=0);
			  }
			  else if (custOption==7){
				//Display All Movies
				int Rating;
				Movie movie = chooseMovie();
				do{try{
				System.out.println("Enter Rating for Movie(1-5)");
				Rating = input.nextInt();
				break;
				}catch(InputMismatchException e){
					System.out.println("Please Try Again!");
					input.nextLine();
					Rating =-1;
				}}while(Rating==-1);
				if(Rating<=5&& Rating>=1){
					System.out.println("Enter Review(Only 1 Para): ");
					input.nextLine();
					String review = input.nextLine();
					movie.addReview(review, Rating);
					updateList(movie);
					System.out.println("Thanks for the Review!");
				}
			}
			  }while(custOption!=0);
			break;
		case 3:
			System.out.println("Program terminating...");
			System.exit(0);
			break;
		default:
			System.out.println("Please enter a valid choice");
			break;
		}
		}while(choice<=1 || choice >3);
	 }//End of Login
}
