package objects.messages;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import objects.MessageBody;
import objects.PageSettings;

public class ImageTextMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static enum ItemStyle{IMAGE_ITEM, TEXT_ITEM};
	
	private ArrayList<ImageTextItem> messageContent = new ArrayList<ImageTextItem>();

	public ImageTextMessage(MessageStyle messageStyle) {
		super(MessageStyle.IMAGE_TEXT_CONTENT);
	}
	
	public void insert(String content, ItemStyle itemstyle) {
		messageContent.add(new ImageTextItem(itemstyle, content));
	}
	
	public String[] getAttributes() { return new String[] {"Content"}; }

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		Point datum = pageSetting.updateDatum(0);
		// TODO Auto-generated method stub
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