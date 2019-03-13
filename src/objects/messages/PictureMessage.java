package objects.messages;

import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class PictureMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawed = false;
	private String picturePath = "";

	public PictureMessage() { super(MessageStyle.PICTURES_CONTENT); }
	public PictureMessage(String picturePath) {
		super(MessageStyle.PICTURES_CONTENT);
		this.setPicturePath(picturePath);
	}
	
	public String getPicturePath() { return picturePath; }
	public void setPicturePath(String picturePath) { this.picturePath = picturePath; }

	public String[] getAttributes() { return new String[] {"Picture Path"}; }
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 1 && new File(attributes[0]).exists()) {
			this.picturePath = attributes[0];
			return true;
		}
		return false;
	}

	public boolean isComplete() { return this.isDrawed; }

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}
}
