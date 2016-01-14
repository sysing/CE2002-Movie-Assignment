import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class SeatingReadWrite {
	private final String Address="/Users/PratyumJagannath/Desktop/NTU STUFF/Object oriented Programming/Assignment/screenTest.txt";
	
	public void PrintSeats(List<String> lines){
			for (String line : lines) {
                System.out.println(line);
            }
	}
	
	public int SeatsFilled(List<String> lines){
		int count = 0;
		String findStr ="xx";
		for (String line : lines) {
		int lastIndex = 0;
		while(lastIndex != -1){

		    lastIndex = line.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
	}
		System.out.println(count);
		return count;
	}
	
	public List<String> SeatsConfig(){
		try {
			List<String> lines = Files.readAllLines(Paths.get(Address),
			        Charset.defaultCharset());
			return lines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void BookSeats(char row , int seatNo){
		int rowNo = (int)row - 65;
		try {
			List<String> lines = Files.readAllLines(Paths.get(Address),
			        Charset.defaultCharset());
			 String temp = lines.get(rowNo).replace(String.format("%02d", seatNo), "xx");
			 lines.remove(rowNo);
			 lines.add(rowNo, temp);
			for (String line : lines) {
                System.out.println(line);
            }
			Write(lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Write(List<String> lines) {
		try {
			File file = new File(Address);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (String line : lines) {
                bw.write(line+"\n");
            }
			bw.close();

			System.out.println("Writing Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
