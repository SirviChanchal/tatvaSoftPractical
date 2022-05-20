import java.text.SimpleDateFormat;
import java.util.Date;

public class Task2 {

	public static String format(Date currentDate, Date lastDate, int counter) {
		counter++;
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMMyyyy");
		StringBuilder response = new StringBuilder();
		response.append(formatter.format(currentDate));
		System.out.println(response);

		if (counter >= 10000 || currentDate.after(lastDate)) {
			counter = 0;
		}
		response.append(String.format("%04d", counter));
		return response.toString();
	}

	public static void main(String[] args) {

		Date current = new Date();
		Date last = new Date();

		Task2 task2 = new Task2();
		System.out.println(task2.format(current, last, 5));

	}
}
