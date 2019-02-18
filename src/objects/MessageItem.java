package objects;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class MessageItem implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum ItemStyle {STRING_CONTENT, IMAGE_CONTENT, FILE_RECEIVE_CONTENT, VOICE_CONTENT,
		CALL_CONTENT, FILE_CONTENT, MUSIC_CONTENT, VIDEO_CONTENT};
		
	private boolean isTogether = false;
	private boolean isAlone = false;
	private final ItemStyle itemstyle;
	private final int attributeNum;
	
	public MessageItem(ItemStyle itemstyle, int attributeNum) {
		this.itemstyle = itemstyle;
		this.attributeNum = attributeNum;
	}
	
	public boolean isTogether() { return isTogether; }
	public boolean isAlone() { return isAlone; }
	public ItemStyle getItemstyle() { return itemstyle; }
	public int getAttributeNum() { return attributeNum; }
	
	public void setTogether(boolean isTogether) { this.isTogether = isTogether; }
	public void setAlone(boolean isAlone) { this.isAlone = isAlone; }
	
	public abstract void paintMessage(Graphics g, Point p, int areaWidth);
}

class MessageTest extends MessageItem implements Serializable {
	private static final long serialVersionUID = 1L;

	public MessageTest(ItemStyle itemstyle, int attributeNum) {
		super(itemstyle, attributeNum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintMessage(Graphics g, Point p, int areaWidth) {
		// TODO Auto-generated method stub
		
	}
	
}
