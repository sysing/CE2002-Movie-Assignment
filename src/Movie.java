import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable,DisplayInterface {
	public static final int MAX_REVIEW = 10; //define max number of reviews
	private int movieId;
	private String movieTitle ;
	private String type ;
	private String rating;
	private String status ;
	private String synopsis ;
	private String director ;
	private String cast;
	private int ticketSales;
	private int totalRating;
	private float avgRating;
	private String duration;
	 // typically rating can only be added with a review, unless it is reset using setRating()
	private ArrayList<Review> review;

	public Movie(int MovieId,String Movietitle, String Type, String Rating, String Status, String Synopsis,String Director, String Cast,String Duration){
		this.movieId=MovieId;
		this.movieTitle=Movietitle;
		this.type=Type;
		this.rating = Rating;
		this.status=Status;
		this.synopsis=Synopsis;
		this.director=Director;
		this.cast=Cast;
		this.duration =Duration;
	} 
	
	public Movie(int MovieId,String Movietitle, String Type, String Rating, String Status, String Synopsis,String Director, String Cast, String TicketSales,String TotalRating, String RatingNum,String Duration)  {
		this.movieId=MovieId;
		this.movieTitle=Movietitle;
		this.type=Type;
		this.rating = Rating;
		this.status=Status;
		this.synopsis=Synopsis;
		this.director=Director;
		this.cast=Cast;
		this.ticketSales = Integer.valueOf(TicketSales);
		this.totalRating=Integer.valueOf(TotalRating);
		this.duration= Duration;

	} 
	
	public Movie(){
		this.movieId=0;
		this.movieTitle="";
		this.type="";
		this.status="";
		this.synopsis="";
		this.director="";
		this.cast="";
		this.ticketSales= 0;
		this.totalRating=0;
		this.avgRating=0;
	}
	
	public int getMovieId() { return movieId ; }
	public String getMovietitle() { return movieTitle ; }
	public String getType() { return type ; }
	public String getRating(){return rating;}
	public String getStatus() { return status ; }
	public String getSynopsis() { return synopsis ; }
	public String getDirector() { return director ; }
	public String getCast() { return cast ; }
	public int getTicketSales(){return ticketSales;}
	public float getAvgRating() { return avgRating ; }
	public String getDuration(){return duration;}
	public ArrayList<Review> getReviews(){return review;}
	public void setMovietitle(String Movietitle) { this.movieTitle=Movietitle; }
	public void setType(String Type) { this.type=Type; }
	public void setRating(float AvgRating) { this.avgRating=AvgRating; }
	public void setStatus(String Status) { this.status=Status; }
	public void setSynopsis(String Synopsis) { this.synopsis=Synopsis; }
	public void setDirector(String Director) { this.director=Director; }
	public void setCast(String Cast) { this.cast=Cast; }
	public void setRating(String rating) {this.rating = rating;}
	public void setTicketSales(int TicketSales){this.ticketSales = TicketSales;}
	public void incTicketSales(int tickets){this.ticketSales +=tickets;}
	public void setReview(ArrayList<Review> Review){ 
		review = Review;
		avgRating = 0;
		for(int i=0;i<Review.size();++i){avgRating+= Review.get(i).getReviewRating();}
		avgRating/=Review.size();
	}
	public void addReview(String ReviewText, int ReviewRating){ // construct a new review and add as movie attribute.
		Review custReview =  new Review(ReviewText, ReviewRating,movieId) ;
		if (review==null){
		review = new ArrayList<>();
		}
		review.add(custReview);
		addRating(custReview.getReviewRating());
	} 
	public void addRating(int rating){
		this.totalRating += rating;
		avgRating = totalRating/review.size();
	}

	public boolean equals(Object o) {
		if (o instanceof Movie) {
			Movie m = (Movie)o;
			return (getMovietitle().equals(m.getMovietitle()));
		}
		return false;
	}
	public void display(){
		System.out.println("Movie ID: "+getMovieId());
		System.out.println("Movie Name: "+getMovietitle());
		System.out.println("Movie Type: "+getType());
		System.out.println("Duration: "+getDuration()+" minutes");
		System.out.println("Average Rating: "+getAvgRating());
		System.out.println("Ticket Sales : "+ getTicketSales());
		System.out.println("Director: "+getDirector());
		System.out.println("Cast: "+getCast());
		System.out.println("Synopsis");
		System.out.println(getSynopsis());
		System.out.println("-----------------------------------------------------------------");
	}
}