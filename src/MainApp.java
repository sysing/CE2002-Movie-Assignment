import java.text.ParseException;

public class MainApp {
	public static void main(String agrs[]){
		UserLogin userLogin = new UserLogin();
		try {
			userLogin.Login();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}