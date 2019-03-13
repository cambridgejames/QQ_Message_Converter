package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class TransmitMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDraw;
	private String content;
	private int messageNumber;

	public TransmitMessage() { super(MessageStyle.TRANSMIT_CONTENT); }
	public TransmitMessage(String content, int messageNUmber) {
		super(MessageStyle.TRANSMIT_CONTENT);
		this.setContent(content);
		this.setMessageNumber(messageNUmber);
	}

	public int getMessageNumber() { return messageNumber; }
	public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public boolean isComplete() { return this.isDraw; }
	public String[] getAttributes() { return new String[] {"Content", "Message Number"}; }
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 2) {
			try { this.setMessageNumber(Integer.parseInt(attributes[1])); }
			catch (Exception e) { e.printStackTrace(); return false; }
			this.setContent(attributes[0]);
			return true;
		}
		else { return false; }
	}

	@Override
	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}

}
