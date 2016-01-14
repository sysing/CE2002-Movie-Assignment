public class Booking implements DisplayInterface{
	private String name,email;
	private long TID;
	private int number;
	private double price;
	
	public Booking(String name,String email, int number,double Price, long TID) {	
		this.name=name;
		this.email=email;
		this.number=number;
		this.TID = TID;
		this.price = Price;
	}
	
	public String getName() {return name;}
	public String getEmail() {return email;}
	public int getNumber() {return number;}
	public double getPrice() {return price;}
	public long getTID() {return TID;}
	public void setName(String Name){name =Name;}
	public void setEmail(String Email){email =Email;}
	public void setTID(long tId){TID =tId;}
	public void setNumber(int Number){number =Number;}
	public void setPrice(double Price){price = Price;}
	public void display(){
		System.out.println("+----------------Booking Details-------------+");
		System.out.printf("|        Transaction ID: %-19d |\n",getTID());
		System.out.printf("|        Name: %-30s|\n",getName());
		System.out.printf("|        Phone: %-29d|\n",getNumber());
		System.out.printf("|        Email: %-29s|\n",getEmail());
		System.out.println("+--------------------------------------------+");
	}
}
