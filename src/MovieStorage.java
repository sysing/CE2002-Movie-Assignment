import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MovieStorage extends StorageHandler{
	public static final String SEPARATOR = " ";
	public static final String SEPARATOR1 = "|";
	
	public ArrayList<Movie> readObject() throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>)read("movie.txt");
		ArrayList<Movie> alr = new ArrayList<>();// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter ","
                while(star.hasMoreTokens()){
                int movieId=Integer.parseInt(star.nextToken().trim());
				String  movietitle = star.nextToken().trim();	
				String  type = star.nextToken().trim();	
				String  rating = star.nextToken().trim();	
				String  status = star.nextToken().trim();	
				String  synopsis = star.nextToken().trim();	
				String  director = star.nextToken().trim();	
				String  cast = star.nextToken().trim();	
				String duration = star.nextToken().trim();
				int ticketSales = Integer.parseInt(star.nextToken().trim());
				Movie m = new Movie(movieId,movietitle,type,rating,status,synopsis,director,cast,duration);
				m.setTicketSales(ticketSales);
				alr.add(m) ;
                }
			}
        ArrayList<Review> ReviewList = this.readReview();
        for(int i=0;i<alr.size();++i){
        	ArrayList<Review> reviews = new ArrayList<>();
        	for(int j=0;j<ReviewList.size();++j){
        		if(alr.get(i).getMovieId()==ReviewList.get(j).getMovieID()){
        			reviews.add(ReviewList.get(j));
        		}
        	}
        	alr.get(i).setReview(reviews);
        }
			return alr ;
	}
	
	public ArrayList<Review> readReview(){
		ArrayList<Review> ReviewList = new ArrayList<>();
		ArrayList<String> stringArray = new ArrayList<>();
		try {
			stringArray = (ArrayList<String>)read("review.txt");
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter ","
             while(star.hasMoreTokens()){
				int  MovieID = Integer.parseInt(star.nextToken().trim());	
				int  ReviewRating = Integer.parseInt(star.nextToken().trim());	
				String  ReviewText = star.nextToken().trim();
				Review r = new Review(ReviewText, ReviewRating, MovieID);
				ReviewList.add(r) ;
             }
			}		
			return ReviewList ;
		
	}
	
	public void saveupdatedMovie(String filename, List al) throws IOException {
	  		ArrayList<String> alw = new ArrayList<>() ;
	  		ArrayList<Review> reviews = new ArrayList<>();
	          for (int i = 0 ; i < al.size() ; i++) {
	  				Movie m = (Movie)al.get(i);
	  				if(m.getReviews()!=null){
	  					if(m.getReviews().size()!=0){
						for(int j=0;j<m.getReviews().size();++j){
							reviews.add(m.getReviews().get(j));
						}
	  					}
						}
	  					StringBuilder st =  new StringBuilder() ;
  						st.append(m.getMovieId());
  						st.append(SEPARATOR1);	  					
	  					st.append(m.getMovietitle().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getType().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getRating().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getStatus().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getSynopsis().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getDirector().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getCast().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getDuration().trim());
	  					st.append(SEPARATOR1);
	  					st.append(String.valueOf(m.getTicketSales()).trim());
	  					st.append(SEPARATOR1);
	  				alw.add(st.toString()) ;
	  			}
	          	if(reviews.size()!=0){
			    	saveReview("review.txt", reviews);
			    	}
	  			write(filename,alw);
	 }

	 public void printMovieTitle(){
			try {
				ArrayList al=new ArrayList();
				MovieStorage ms=new MovieStorage();
				al = ms.readObject() ;
				
				for (int i = 0 ; i < al.size() ; i++) {
						Movie m = (Movie)al.get(i);
						System.out.println(i+1+") "+m.getMovietitle());
						
				}
				
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	}
	
	public void saveObject(String filename, ArrayList al) throws IOException {
			ArrayList<String> alw = new ArrayList<>() ;
			ArrayList<Review> review = new ArrayList<>();
		    for (int i = 0 ; i < al.size() ; i++) {
					Movie m = (Movie)al.get(i);
					
					StringBuilder st =  new StringBuilder() ;
						st.append(m.getMovieId());
						st.append(SEPARATOR1);
						st.append(m.getMovietitle().trim());
						st.append(SEPARATOR1);
						st.append(m.getType().trim());
						st.append(SEPARATOR1);
						st.append(m.getRating().trim());
						st.append(SEPARATOR1);
						st.append(m.getStatus().trim());
						st.append(SEPARATOR1);
						st.append(m.getSynopsis().trim());
						st.append(SEPARATOR1);
						st.append(m.getDirector().trim());
						st.append(SEPARATOR1);
						st.append(m.getCast().trim());
						st.append(SEPARATOR1);
						st.append(m.getDuration().trim());
	  					st.append(SEPARATOR1);
	  					st.append(String.valueOf(m.getTicketSales()).trim());
	  					st.append(SEPARATOR1);
	  					if(m.getReviews()!=null){
	  					if(m.getReviews().size()!=0){
	  						for(int j=0;j<m.getReviews().size();++j){
	  							review.add(m.getReviews().get(j));
	  						}
	  					}
	  					}
					alw.add(st.toString()) ;
				}
		    saveReview("review.txt", review);
			write(filename,alw);
	}
	public void  saveReview(String fileName ,ArrayList<Review> reviews) throws IOException{
		 ArrayList<String> alw = new ArrayList<>() ;
		 for (int i = 0 ; i < reviews.size() ; i++) {
				Review r = reviews.get(i);
				StringBuilder st =  new StringBuilder() ;
					st.append(r.getMovieID());
					st.append(SEPARATOR1);
					st.append(r.getReviewRating());
					st.append(SEPARATOR1);
					st.append(r.getReviewText().trim());
					st.append(SEPARATOR1);
				alw.add(st.toString());
			}
			write(fileName,alw);
	 }

}
