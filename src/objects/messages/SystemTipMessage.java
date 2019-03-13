package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class SystemTipMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawed = false;
	private String content = "";

	public SystemTipMessage() { super(MessageStyle.SYSTEM_TIP_CONTENT); }
	public SystemTipMessage(String content) {
		super(MessageStyle.SYSTEM_TIP_CONTENT);
		this.content = content;
	}
	
	public String getContent() { return this.content; }
	public void setContent(String content) { this.content = content; }
	
	public String[] getAttributes() { return new String[] {"Content"}; }
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 1) { this.content = attributes[0]; return true; }
		else { return false; }
	}

	public boolean isComplete() { return this.isDrawed; }

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub
		// this.isDrawed = true;
	}
}
