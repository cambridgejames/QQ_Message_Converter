package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ImageIcon;

import objects.MessageBody;
import objects.PageSettings;

public class ExpressionMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawed = false;
	private String filePath;

	public ExpressionMessage() { super(MessageStyle.EXPRESSION_CONTENT); }
	public ExpressionMessage(String filePath) {
		super(MessageStyle.EXPRESSION_CONTENT);
		this.setFilePath(filePath);
	}

	public String getFilePath() { return filePath; }
	public ImageIcon getImageIcon() { return new ImageIcon(this.filePath); }
	public void setFilePath(String filePath) { this.filePath = filePath; }
	
	public String[] getAttributes() { return new String[] {"Picture Path"}; }
	public boolean isComplete() { return this.isDrawed; }

	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 1) { this.filePath = attributes[0]; return true; }
		else { return false; }
	}

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub
		this.isDrawed = true;
	}
}
