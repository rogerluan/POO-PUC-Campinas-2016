import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Book {

	protected final DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
	private String title;
	
	public Book(String title) {
		super();
		setTitle(title);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
}
