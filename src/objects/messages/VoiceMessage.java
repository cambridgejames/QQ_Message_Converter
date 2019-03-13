package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class VoiceMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean isDraw = false;
	private String timeLength = "";
	
	public VoiceMessage() { super(MessageStyle.VIOCE_CONTENT); }
	public VoiceMessage(String timLength) {
		super(MessageStyle.VIOCE_CONTENT);
		this.timeLength = timLength;
	}
	
	public String getTimeLength() { return this.timeLength; }
	public void setTimeLength(String timeLength) { this.timeLength = timeLength; }

	public String[] getAttributes() { return new String[] {"Time Length"}; }
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 1) { this.timeLength = attributes[0]; return true; }
		else { return false; }
	}

	public boolean isComplete() { return this.isDraw; }

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}
}
