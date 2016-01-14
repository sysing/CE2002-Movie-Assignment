
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin {
private String username;
private String password;
private String Movietitle ;
private String Type ;
private String Rating ;
private String Status ;
private String Synopsis ;
private String Director ;
private String Cast;

public Admin(){
	
}
public Admin(String user, String pass){
    username = user;
    password = pass;
}

public boolean auth(){
	UserStorage s =new UserStorage("admin1.txt","admin1.txt",username,password);
    if(s.readFile())
        return true;
    else
        return false;
}
public void writefile(){
	UserStorage s =new UserStorage("admin1.txt","admin1.txt",username,password);
	s.writeFile();
}


public void CreateMovie(String movietitle,String type,String rating,int status,String synopsis,String director,String cast,String duration) throws ParseException{
	try {
		
	    
		ArrayList al=new ArrayList();
		StorageHandler ms=new MovieStorage();
		al=ms.readObject();	
		int movieId=(al.size())+1;
		String strStatus="";
		if(status==1){
			strStatus="Coming Soon";			
		}
		else if(status==2){
			strStatus="Now Showing";			
		}
		else if(status==3){
			strStatus="Preview";
		}
		
		
		Movie m1 = new Movie(movieId,movietitle,type,rating,strStatus,synopsis,director,cast,duration);
		al.add(m1);		
		ms.saveObject("movie.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}


public void ShowMovie(int choice) throws ParseException{
	try {
		ArrayList al=new ArrayList();
		StorageHandler ms=new MovieStorage();
		al=ms.readObject();	
		
		//show current movies
		if(choice==1){
			System.out.println("+------------MOVIES ON SHOW--------------+");
			System.out.println("+----------------------------------------+");
			System.out.printf("|%-8s| %-30s|\n","Movie ID","Name");
			System.out.println("+----------------------------------------+");
			for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				if(m.getStatus().compareTo("Now Showing")==0)
				System.out.printf("|    %-4d| %-30s|\n",m.getMovieId(),m.getMovietitle());		
			}
			System.out.println("+----------------------------------------+");
		}
		
		//show archived movies
		else if(choice==2){
			System.out.println("+-------------MOVIES ARCHIVED------------+");
			System.out.println("+----------------------------------------+");
			System.out.printf("|%-8s| %-30s|\n","Movie ID","Name");
			System.out.println("+----------------------------------------+");
			for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				if(m.getStatus().compareTo("End of Showing")==0)
					System.out.printf("|    %-4d| %-30s|\n",m.getMovieId(),m.getMovietitle());
				
			}
			System.out.println("+----------------------------------------+");
		}
		//show coming soon movies
		else if(choice==3){
			System.out.println("+-----------MOVIES COMING SOON-----------+");
			System.out.println("+----------------------------------------+");
			System.out.printf("|%-8s| %-30s|\n","Movie ID","Name");
			System.out.println("+----------------------------------------+");
			for (int i = 0 ; i < al.size() ; i++) {
					Movie m = (Movie)al.get(i);
					if(m.getStatus().compareTo("Coming Soon")==0)
						System.out.printf("|    %-4d| %-30s|\n",m.getMovieId(),m.getMovietitle());
					
				}		
			System.out.println("+----------------------------------------+");
		}
		else if(choice==4){
			System.out.println("+------------MOVIES ON PREVIEW-----------+");
			System.out.println("+----------------------------------------+");
			System.out.printf("|%-8s| %-30s|\n","Movie ID","Name");
			System.out.println("+----------------------------------------+");
			for (int i = 0 ; i < al.size() ; i++) {
					Movie m = (Movie)al.get(i);
					if(m.getStatus().compareTo("Preview")==0)
						System.out.printf("|    %-4d| %-30s|\n",m.getMovieId(),m.getMovietitle());
					
				}
			System.out.println("+----------------------------------------+");	
			}
		//show all movies
		else if(choice==5){
			System.out.println("+-----------------------ALL MOVIES-----------------------+");
			System.out.println("+--------------------------------------------------------+");
			System.out.printf("|%-8s| %-30s|%-15s|\n","Movie ID","Name","Status");
			System.out.println("+--------------------------------------------------------+");
			for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				System.out.printf("|    %-4d| %-30s|%-15s|\n",m.getMovieId(),m.getMovietitle(),m.getStatus());
			}		
			System.out.println("+--------------------------------------------------------+");	
		}
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}



public void ChooseEditMovie(int moviechoice){
	try {
		Scanner input=new Scanner(System.in);
		ArrayList al=new ArrayList();
		MovieStorage ms=new MovieStorage();
		al = ms.readObject() ;
		
		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(moviechoice-1);
				if (m.getMovieId()==moviechoice){
				System.out.println("Movie Id: "+m.getMovieId());
				System.out.println(i+1+")Movie Title: "+m.getMovietitle());
				System.out.println(i+2+")Movie Type "+m.getType());
				System.out.println(i+3+")Movie Rating: "+m.getRating());
				System.out.println(i+4+")Movie Status: "+m.getStatus());
				System.out.println(i+5+")Synopsis: "+m.getSynopsis());
				System.out.println(i+6+")Director: "+m.getDirector());
				System.out.println(i+7+")Cast: "+m.getCast());
				}
				break;				
		}
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}
public void UpdateMovie(int attribute,int moviechoice){
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		MovieStorage ms=new MovieStorage();
		// read file containing Professor records.
		al = ms.readObject() ;
		int id=0;
		String mo=null,ty=null,ra=null,st=null,sy=null,di=null,ca= null,dur=null;		
		
				Movie m = (Movie)al.get(moviechoice-1);
				id=m.getMovieId();
				mo=m.getMovietitle();
				ty=m.getType();
				ra=m.getRating();
				st=m.getStatus();
				sy=m.getSynopsis();
				di=m.getDirector();
				ca=m.getCast();
				dur=m.getDuration();
				switch(attribute){
				
				case 1:				
					System.out.println("Enter Movie Title: ");
					String movietitle=input.nextLine();
					mo=mo.replace(mo, movietitle);	
					break;		
				case 2:
					System.out.println("Enter Type: ");
					String type=input.nextLine();
					ty=ty.replace(ty, type);
					break;
				case 3:				
					System.out.println("Enter Rating: ");
					String rating=input.nextLine();
					ra=ra.replace(ra,rating);
					break;
				case 4:
					System.out.println("Enter Status: ");
					String status=input.nextLine();
					st=st.replace(st, status);
					break;
				case 5:
					System.out.println("Enter Synopsis: ");
					String synopsis=input.nextLine();
					sy=sy.replace(sy, synopsis);
					break;
				case 6:
					System.out.println("Enter Director: ");
					String director=input.nextLine();
					di=di.replace(di, director);
					break;
				case 7:
					System.out.println("Enter Cast: ");
					String cast=input.nextLine();
					ca=ca.replace(ca, cast);
					break;
				case 8:
					System.out.println("Enter Duration(in Mins): ");
					String duration=input.nextLine();
					dur=dur.replace(dur,duration);
					break;
				default:
					break;
				}
		
		al.remove(moviechoice-1);
		Movie newmovie=new Movie(id,mo,ty,ra,st,sy,di,ca,dur);
		al.add(moviechoice-1, newmovie);		
		ms.saveupdatedMovie("movie.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void RemoveMovie(int moviechoice){
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		MovieStorage ms=new MovieStorage();
		al = ms.readObject();
		int id=0;
		String mo=null,ty=null,ra=null,st=null,sy=null,di=null,ca= null,dur=null;
				Movie m = (Movie)al.get(moviechoice-1);
				id=m.getMovieId();
				mo=m.getMovietitle();
				ty=m.getType();
				ra=m.getRating();
				st=m.getStatus();
				sy=m.getSynopsis();
				di=m.getDirector();
				ca=m.getCast();
				dur=m.getDuration();
				st=st.replace(st, "End of Showing");
		Movie newmovie=new Movie(id,mo,ty,ra,st,sy,di,ca,dur);
		try {
			DeleteShowTime(mo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		al.set(moviechoice-1, newmovie);
		
		// write Movie record/s to file.
		ms.saveupdatedMovie("movie.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}



public void showShowTime() throws IOException, ParseException{
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	al = sts.readObject() ;	
	
	for(int i=0;i<al.size();i++){
	ShowTime st=(ShowTime)al.get(i);
	System.out.println("Showtime ID :" +(i+1));
	st.display();
	}	
	}
	

public void createShowTime(int cineplexId,int cinemaId,int movieId,String strTime) throws ParseException{
	try {
	Scanner input = new Scanner(System.in);
	ArrayList movieal=new ArrayList();
	ArrayList showtimeal=new ArrayList();
	
	//get movie name from id
	StorageHandler ms=new MovieStorage();
	movieal = ms.readObject();
	Movie movie=(Movie)movieal.get(movieId-1);
	String movieTitle=movie.getMovietitle();
	
	//get cineplex from userinput
	String cineplex="";
	if(cineplexId==1) {
		cineplex= "BZ Jurong Point";}
	else if(cineplexId==2){
		cineplex= "BZ Marina Square";}
	else if(cineplexId==3){
		cineplex="BZ Suntec City";		
	}
	
	StorageHandler sts=new ShowTimeStorage();
	showtimeal=sts.readObject();
	ShowTime st = new ShowTime(movieTitle,cineplex,cinemaId,strTime);	
	showtimeal.add(st);		
	sts.saveObject("showtime.txt", showtimeal);		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
	}


public void EditShowTime(int showTimeId) throws ParseException{
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	String strchoice="";

	try {
		al = sts.readObject() ;
	} catch (IOException e) {
		e.printStackTrace();
	}
	
		Scanner input = new Scanner(System.in);
		
		//get the ShowTime user wants to edit
		ShowTime st=(ShowTime)al.get(showTimeId-1);
		st.display();
		System.out.println("Enter the field you wish to edit :");
		int choice=input.nextInt();
		input.nextLine();
		
		//let user choose which field he wants to edit
		switch(choice){
			case 1:			
				System.out.println("Enter new cineplex");
				System.out.println("1) BZ Jurong Point");
				System.out.println("2) BZ Marina Square");
				System.out.println("3) BZ Suntec City");
				choice=input.nextInt();
				input.nextLine();
				
				//get cineplex from userinput
				String strCineplex="";
				if(choice==1)
					strCineplex="BZ Jurong Point";
				else if(choice==2)
					strCineplex="BZ Marina Square";
				else if(choice==3)
					strCineplex="BZ Suntec City";
				st.setCineplexName(strCineplex);
				System.out.println("Cineplex changed!");
				break;
		
		
			//user decides to edit hall number of ShowTime
			case 2:			
				System.out.println("Enter new hall number");
				choice=input.nextInt();
				input.nextLine();
				st.setCinemaId(choice);
				System.out.println("Hall number changed!");
				break;
			
			//user decides to edit movie of ShowTime
			case 3:
				System.out.println("Enter ID of the new movie");
				//ShowMovie shows all movies with 'Showing Now' status
				ShowMovie(1);
				ArrayList movieal=new ArrayList();
				StorageHandler ms=new MovieStorage();
				try {movieal = ms.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				choice=input.nextInt();
				input.nextLine();
			
			//get movie title according to user's input of movieId
			Movie movie=(Movie)movieal.get(choice-1);
			String movieTitle=movie.getMovietitle();
			st.setMovieTitle(movieTitle);
			System.out.println("Movie changed!");
			break;
			
		case 4:
			System.out.println("Enter new time in hh:mm");
			String inputTime=input.nextLine();
			Date time = (Date)timeFormat.parse(inputTime);
			st.setTime(inputTime);
			System.out.println("Date changed!");
			break;
			
		default:
			System.out.println("Please enter a valid choice");
		}
		
		al.set(showTimeId-1, st);
		
		try {
			sts.saveObject("showtime.txt", al);
		} catch (IOException e) {
			e.printStackTrace();
		}

}


public void DeleteShowTime(int showTimeId) throws ParseException{
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	String strchoice="";
	try {
		al = sts.readObject() ;
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	al.remove(showTimeId-1);
		
		try {
			sts.saveObject("showtime.txt", al);
		} catch (IOException e) {
			e.printStackTrace();
		}
}

public void DeleteShowTime(String MovieTitle) throws ParseException{
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	String strchoice="";
	try {
		al = sts.readObject() ;
	} catch (IOException e) {
		e.printStackTrace();
	}
	for(int i=0;i<al.size();++i){
		if(((ShowTime)al.get(i)).getMovieTitle().equals(MovieTitle)){
			al.remove(i);
		}
	}
		try {
			sts.saveObject("showtime.txt", al);
		} catch (IOException e) {
			e.printStackTrace();
		}
}

public void ShowNormalPrice(){
	SystemStorage ss = new SystemStorage();
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readPrice("NormalPrice.txt") ;
		
		for (int i = 0 ; i < al.size() ; i++) {
				Price p = (Price)al.get(i);
				System.out.printf("|  %-25s | %-6.2f |\n",p.getPriceType(),p.getPrice());
		}
		
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void Show3DPrice(){
	SystemStorage ss = new SystemStorage();
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readPrice("3DPrice.txt") ;
		
		for (int i = 0 ; i < al.size() ; i++) {
				Price p = (Price)al.get(i);
				System.out.printf("|  %-25s | %-6.2f |\n",p.getPriceType(),p.getPrice());
				
		}
		
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void ShowPriceAttribute(int tickettype){
	SystemStorage ss = new SystemStorage();
	try {
		ArrayList al=new ArrayList();
		ArrayList al2=new ArrayList();
		// read file containing Professor records.
		al = ss.readPrice("NormalPrice.txt") ;
		al2 =ss.readPrice("3DPrice.txt") ;
		if(tickettype==1){
			for (int i = 0 ; i < al.size() ; i++) {
				Price p = (Price)al.get(i);
				System.out.println(i+1+") "+p.getPriceType());
				
		}
		}
		if(tickettype==2){
			for (int i = 0 ; i < al.size() ; i++) {
				Price p = (Price)al2.get(i);
				System.out.println(i+1+") "+p.getPriceType());		
		}
		}
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void UpdateNormalPrice(int attribute){
	SystemStorage ss = new SystemStorage();
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readPrice("NormalPrice.txt");
		String ty=null, pr=null;
        double price=0;
		for (int i = 0 ; i < al.size() ; i++) {
				Price p = (Price)al.get(attribute-1);
				ty=p.getPriceType();
				pr=String.valueOf(p.getPrice());
				
				System.out.println("Enter New Price: ");
				String newprice=input.nextLine();
				pr=pr.replace(pr, newprice);
				
			    price=Double.parseDouble(pr);
				
				break;
			
		}
		al.remove(attribute-1);
		Price newPrice=new Price(ty,price);
		al.add(attribute-1, newPrice);
		
		// write Professor record/s to file.
		ss.saveupdatedPrice("NormalPrice.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void Update3DPrice(int attribute){
	SystemStorage ss = new SystemStorage();
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readPrice("3DPrice.txt");
		String ty=null, pr=null;
        double price=0;
		for (int i = 0 ; i < al.size() ; i++) {
				Price p = (Price)al.get(attribute-1);
				ty=p.getPriceType();
				pr=String.valueOf(p.getPrice());
				
				System.out.println("Enter New Price: ");
				String newprice=input.nextLine();
				pr=pr.replace(pr, newprice);
				
			    price=Double.parseDouble(pr);
				
				break;
			
		}
		al.remove(attribute-1);
		Price newPrice=new Price(ty,price);
		al.add(attribute-1, newPrice);
		
		// write Professor record/s to file.
		ss.saveupdatedPrice("3DPrice.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void CreateHoliday(String HolidayName,String HolidayDate ){
	SystemStorage ss = new SystemStorage();
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readHoliday("Holiday.txt") ;
		int HolidayId=(al.size())+1;
		for (int i = 0 ; i < al.size() ; i++) {
				Holiday d1 = (Holiday)al.get(i);
				
		}
		
		Holiday h= new Holiday(HolidayId,HolidayName,HolidayDate);
		// al is an array list containing Professor objs
		al.add(h);
		
		// write Professor record/s to file.
		ss.saveupdatedHoliday("Holiday.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void ShowHoliday(){
	SystemStorage ss = new SystemStorage();
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readHoliday("Holiday.txt") ;
		for (int i = 0 ; i < al.size() ; i++) {
				Holiday h = (Holiday)al.get(i);
				System.out.printf("|%-2d|%-25s| %-8s|\n",h.getHolidayId(),h.getHolidayName(),h.getHolidayDate());
		}
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void RemoveHoliday(int holidayid){
	SystemStorage ss = new SystemStorage();
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		// read file containing Professor records.
		al = ss.readHoliday("Holiday.txt") ;
		int id=0;
		String hn=null,hd=null;

		for (int i = 0 ; i < al.size() ; i++) {
				Holiday h = (Holiday)al.get(holidayid-1);
				id=h.getHolidayId();
				hn=h.getHolidayName();
				hd=h.getHolidayDate();
				break;
			
		}
		al.remove(holidayid-1);
		
		// write Professor record/s to file.
		ss.saveupdatedHoliday("Holiday.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

}
