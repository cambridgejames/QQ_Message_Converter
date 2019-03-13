package objects;

import java.io.Serializable;

public abstract class ThreeMessageBody extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String str0;
	private String str1;
	private String str2;

	public ThreeMessageBody(MessageStyle messageStyle) { super(messageStyle); }
	public ThreeMessageBody(MessageStyle messageStyle, String str0, String str1, String str2) {
		super(messageStyle);
		this.setStr0(str0);
		this.setStr1(str1);
		this.setStr2(str2);
	}

	public String getStr0() { return str0; }
	public String getStr1() { return str1; }
	public String getStr2() { return str2; }
	public void setStr0(String str0) { this.str0 = str0; }
	public void setStr1(String str1) { this.str1 = str1; }
	public void setStr2(String str2) { this.str2 = str2; }
	
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 3) {
			this.setStr0(attributes[0]);
			this.setStr1(attributes[1]);
			this.setStr2(attributes[2]);
			return true;
		}
		else { return false; }
	}
}
