import java.util.ArrayList;
import java.util.List;


public class Screen {
	//-----------------------------------------Data Members
	private int numSeats;
	private int seatsFree;
	private Seat[][] seats;
	private String strDate;
	private int screenNo; // Primary key
	//-----------------------------------------Members Functions
	//-----------------------------------------Constructor
	public Screen(int ScreenNo,String StrDate){
		screenNo = ScreenNo;
		strDate = StrDate;
		numSeats = 200;
		seatsFree = 200;
		seats = new Seat[10][20];
		for(int i=0;i<10;++i){
			for(int j=0;j<20;++j){
				String seatId = String.valueOf((char)(i+65));
				seatId.concat(String.valueOf(j));
				seats[i][j]=new Seat(seatId,false);
			}
		}
	}
	
	//-----------------------------------------Get-Set Methods
	public int getSeatsFree(){return seatsFree;}
	public int getScreenNo() {return screenNo;}
	public int getNumSeats() {return numSeats;}
	public String getStrDate() {return strDate;}
	public void setStrDate(String StrDate){strDate = StrDate;}
	//-----------------------------------------Other Methods
	public void printScreen(){
		for(int i=0;i<10;++i){
			System.out.print(((char)(i+65))+": ");
			for(int j=0;j<10;++j){
				if(seats[i][j].isAssigned()){
					System.out.print("(xx) ");
				}
				else{
					System.out.print("("+String.format("%02d",(j+1))+") ");
				}
			}
			for(int j=0;j<3;++j){
				System.out.print(" ");
			}
			for(int j=10;j<20;++j){
				if(seats[i][j].isAssigned()){
					System.out.print("(xx) ");
				}
				else{
					System.out.print("("+String.format("%02d",(j+1))+") ");
				}
			}
			
			
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
		for(int i=0;i<50;++i){
			System.out.print("-");
		}
		System.out.print("SCREEN");
		for(int i=0;i<50;++i){
			System.out.print("-");
		}
		System.out.println("");
	}
	
	public boolean AssignSeat(char row,int seatNo){
		if(seats[(int)row-65][seatNo-1].isAssigned()){
			return false;
		}
		else{
			seats[(int)row-65][seatNo-1].AssignSeat();
			seatsFree--;
			return true; 
		}
	}
	public boolean UnAssignSeat(char row,int seatNo){
		if(!seats[(int)row-65][seatNo-1].isAssigned()){
			return false;
		}
		else{
			seats[(int)row-65][seatNo-1].unAssignSeat();
			seatsFree++;
			return true;
		}
	}
	public ArrayList<String> listFreeSeats(){
		ArrayList<String> list= new ArrayList<>();
		for(int i=0;i<10;++i){
			for(int j=0;j<20;++j){
				if(seats[i][j].isAssigned()){
					String seatID = String.valueOf((char)(i+65)).concat(String.valueOf(j+1));
					list.add(seatID);
				}
			}
		}
		return list;
	}
}
