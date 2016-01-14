public class Review {
	private String reviewText;
	private int reviewRating;
	private int movieID;
	
	public Review(String ReviewText, int ReviewRating,int MovieID) {
		this.reviewText = ReviewText;
		this.reviewRating = ReviewRating;
		this.movieID = MovieID;
	}
	public String getReviewText(){return reviewText;}
	public int getMovieID(){return movieID;}
	public int getReviewRating () {return reviewRating;}
	
	public void setReviewRating(int Rating){reviewRating =Rating;}
	public void setReviewText(String review){reviewText =review;}
	public void setMovieID(int MovieID){movieID =MovieID;}
	
}
