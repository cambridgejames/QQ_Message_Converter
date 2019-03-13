package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import objects.MessageBody;
import objects.PageSettings;

public class ImageTextMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static enum ItemStyle{IMAGE_ITEM, TEXT_ITEM};
	
	private ArrayList<ImageTextItem> messageContent = new ArrayList<ImageTextItem>();

	public ImageTextMessage() {
		super(MessageStyle.IMAGE_TEXT_CONTENT);
	}
	
	public boolean insert(String content, ItemStyle itemstyle) {
		return messageContent.add(new ImageTextItem(itemstyle, content));
	}
	
	public String delete() { return delete(messageContent.size() - 1); }
	public String delete(int index) {
		if(messageContent.size() == 0) { return null; }
		return messageContent.remove(index).toString();
	}
	
	public String[] getAttributes() { return new String[] {"Content"}; }

	public boolean isComplete() {
		// TODO Auto-generated method stub4
		return false;
	}

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		//Point datum = pageSetting.updateDatum(0);
		// TODO Auto-generated method stub
	}

	@Override
	public boolean setAttributes(String[] attributes) {
		// TODO Auto-generated method stub
		return false;
	}
}

class ImageTextItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ImageTextMessage.ItemStyle itemstyle;
	private String content;
	
	public ImageTextItem(ImageTextMessage.ItemStyle itemStyle, String content) {
		this.setItemstyle(itemStyle);
		this.setContent(content);
	}

	public ImageTextMessage.ItemStyle getItemstyle() { return itemstyle; }
	public String getContent() { return content; }

	public void setItemstyle(ImageTextMessage.ItemStyle itemstyle) { this.itemstyle = itemstyle; }
	public void setContent(String content) { this.content = content; }

	public String toString() {
		switch(this.itemstyle) {
		case IMAGE_ITEM:
			return "<IMAGE>" + this.content + "</IMAGE>";
		case TEXT_ITEM:
			return this.content;
		default:
			return "";
		}
	}
}
