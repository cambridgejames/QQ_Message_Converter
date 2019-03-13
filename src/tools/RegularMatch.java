package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularMatch {
	public static enum lineStyle {DOCUMENT_DESCRIPTOR, DELIVERY_DATE, MESSAGE_CONTENT};
	
	private static Pattern pureDate = Pattern.compile("[0-9]{4}-[0,1][0-9]-[0-3][0-9]");
	private static Pattern datePattern = Pattern.compile("<tr><td style=.*>日期: ((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|"
			+ "[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|"
			+ "[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|"
			+ "[2468][048]|[3579][26])00))-02-29))</td></tr>");
	private static Pattern messagePattern = Pattern.compile("<tr><td><div style=color.*><div style=.*>.*</div>([01]?\\d|"
			+ "2[0-3]):[0-5]?\\d:[0-5]?\\d</div><div style=.*>.*</div></td></tr>");
	
	private static String dattojpgPattern = "(<IMG src=\"\\S+_files/\\{([0-9]|[A-z]){8}-([0-9]|[A-z]){4}-([0-9]|"
			+ "[A-z]){4}-([0-9]|[A-z]){4}-([0-9]|[A-z]){12}\\}\\.)dat\">";
	
	public static String messageAnalysis(String currentLine, lineStyle style) {
		Matcher matcher;
		switch (style) {
		case DELIVERY_DATE:
			matcher = datePattern.matcher(currentLine);
			break;
		case MESSAGE_CONTENT:
			matcher = messagePattern.matcher(currentLine);
			break;
		default:
			return null;
		}
		if(matcher.find()) { return matcher.group(); }
		else { return null; }
	}
	
	public static String getDate(String deliveryDate) {
		Matcher matcher = pureDate.matcher(deliveryDate);
		if(matcher.find()) { return matcher.group(); }
		else { return null; }
	}
	
	public static String dat2jpgHtml(String currentLine) {
		return currentLine.replaceAll(dattojpgPattern, "$1jpg\">");
	}
	
	public static String dat2jpgFile(String filePath) {
		int index = filePath.lastIndexOf(".dat");
		return index < 0 ? filePath : filePath.substring(0, index) + ".jpg";
	}
}
