public class Seat {

String seatId;
boolean assigned;
public Seat(){}

public Seat(String seatId,boolean assigned){
	this.seatId=seatId;
	this.assigned=assigned;
}
public String getSeatID(){return seatId;}
public boolean isAssigned(){return assigned;}
public void unAssigned(){assigned=false;}
public void AssignSeat(){assigned = true;}
public void unAssignSeat(){ assigned = false;}
}

