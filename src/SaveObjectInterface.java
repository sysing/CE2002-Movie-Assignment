import java.io.IOException;
import java.util.ArrayList;

public interface SaveObjectInterface {
	public abstract void saveObject(String filename,ArrayList al) throws IOException;
}
