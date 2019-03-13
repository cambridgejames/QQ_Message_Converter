package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.PageSettings;
import objects.ThreeMessageBody;

public class WebLinkMessage extends ThreeMessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawed;

	public WebLinkMessage() { super(MessageStyle.WEB_LINK_CONTENT); }
	public WebLinkMessage(String iconPath, String title, String content) {
		super(MessageStyle.WEB_LINK_CONTENT, iconPath, title, content);
	}

	public boolean isComplete() { return this.isDrawed; }
	public String[] getAttributes() { return new String[] {"Icon Path", "Title", "Content"}; }
	
	public String getIconPath() { return super.getStr0(); }
	public String getTitle() { return super.getStr1(); }
	public String getContent() { return super.getStr2(); }
	
	public void setIconPath(String iconPath) { super.setStr0(iconPath);; }
	public void setTitle(String title) { super.setStr1(title);; }
	public void setContent(String content) { super.setStr2(content);; }
	
	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}
}
