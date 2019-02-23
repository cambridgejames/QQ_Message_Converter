package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class PicturesMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;

	public PicturesMessage() {
		super(MessageStyle.PICTURES_CONTENT);
	}

	public String[] getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}
}
