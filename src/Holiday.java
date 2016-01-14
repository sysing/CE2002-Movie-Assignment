public class Holiday {
	private int HolidayId;
	private String HolidayName;
	private String HolidayDate ;

	
	public Holiday(int HolidayId,String HolidayName,String HolidayDate){
		this.HolidayId=HolidayId;
		this.HolidayName=HolidayName;
		this.HolidayDate=HolidayDate;
	}
	//get set method
	public int getHolidayId(){return HolidayId;}
	public String getHolidayName() { return HolidayName ; }
	public String getHolidayDate() { return HolidayDate ; }
	
	public void setHolidayName(String HolidayName) { 	this.HolidayName=HolidayName;}
	public void setHolidayDate(String HolidayDate) { 	this.HolidayDate=HolidayDate; }
	
	
	public boolean equals(Object o) {
		if (o instanceof Holiday) {
			Holiday h = (Holiday)o;
			return (getHolidayName().equals(h.getHolidayName()));
		}
		return false;
	}

}