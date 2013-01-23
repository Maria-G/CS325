package assignments.safe;

public interface Safe {

	public static final String BLANK_DISPLAY = "      ";
	public static final String OPEN_DISPLAY = " OPEN ";
	public static final int DISPLAY_LENGTH = 6;
	public static final String ERROR_DISPLAY = "ERROR ";

	public boolean isLocked();

	public String readDisplay();

	public void enter(char c);

}
