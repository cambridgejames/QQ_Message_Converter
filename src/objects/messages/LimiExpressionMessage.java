package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class LimiExpressionMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum IconStyle {SINGLE_ICON, MULTI_ICON};

	private boolean isDrawed;
	private IconStyle iconStyle;
	private String content;
	
	public LimiExpressionMessage() { super(MessageStyle.LIMI_EXPRESSION_CONTENT); }
	public LimiExpressionMessage(IconStyle iconStyle, String content) {
		super(MessageStyle.PHON_CALL_CONTENT);
		this.setIconStyle(iconStyle);
		this.setContent(content);
	}
	
	public IconStyle getIconStyle() { return iconStyle; }
	public void setIconStyle(IconStyle iconStyle) { this.iconStyle = iconStyle; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public boolean isComplete() { return this.isDrawed; }
	public String[] getAttributes() { return new String[] {"Icon Style", "Content"}; }
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 2) {
			try { this.setIconStyle(IconStyle.valueOf(attributes[0])); }
			catch (Exception e) { e.printStackTrace(); return false; }
			this.setContent(attributes[1]);
			return true;
		}
		else { return false; }
	}

	@Override
	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}

}
