import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.util.List;
//import java.util.ArrayList;

public class ShowTime implements DisplayInterface{
private String movieTitle;
private String cineplexName;
private int cinemaId;
private Date date;
private Date time;
private String strTime;
private String strDate;

public ShowTime(String movieTitle,String cineplexName,int cinemaId,String strDate,String strTime ){
this.movieTitle=movieTitle;
this.cinemaId=cinemaId;
this.cineplexName=cineplexName;
this.strTime=strTime;
this.strDate= strDate;
}
public ShowTime(String movieTitle,String cineplexName,int cinemaId,String strTime ){
this.movieTitle=movieTitle;
this.cinemaId=cinemaId;
this.cineplexName=cineplexName;
this.strTime=strTime;
}


public void showSeats(){}
public String getMovieTitle(){return movieTitle;}
public String getCineplexName(){return cineplexName;}
public int getCinemaId(){return cinemaId;}
public String getStrTime(){return strTime;}
public String getStrDate(){return strDate;}


public void setMovieTitle(String movieTitle){this.movieTitle=movieTitle;}
public void setCineplexName(String cineplexName){this.cineplexName=cineplexName;}
public void setCinemaId(int cinemaId){this.cinemaId=cinemaId;}
public void setTime(String strTime){this.strTime=strTime;}
public void setDate(String strDate){this.strDate=strDate;}

public void display(){
	System.out.println("Cineplex:" +this.getCineplexName());		
	System.out.println("Hall Number: "+this.getCinemaId());		
	System.out.println("Movie Name: "+this.getMovieTitle());
	System.out.println("Time: " + this.strTime);
	System.out.println("---------------------------------------");		
}
}

