package objects;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private static enum Owner{SENDED, RECEIVED};
	private static enum ItemStyle{IMAGE_TEXT_CONTENT, PICTURES_CONTENT, EXPRESSION_CONTENT,
		SYSTEM_TIP_CONTENT, VIOCE_CONTENT, PHON_CALL_CONTENT, LIMI_EXPRESSION_CONTENT,
		TRANSMIT_CONTENT, LUCY_MONEY_CONTENT, TRANSFER_ACCOUNTS_CONTENT, FILE_CONTENT,
		AUDIO_CONTENT, VIDEO_CONTENT, WEB_LINK_CONTENT};
		
	private String nickname;
	private String portrait;
	private String sendingTime;
	private Owner owner;
	private final int attributeNum;
	private final ItemStyle itemStyle;
	
	public MessageBody(int attributeNum, ItemStyle itemStyle) {
		this.attributeNum = attributeNum;
		this.itemStyle = itemStyle;
	}

	public String getNickname() { return nickname; }
	public String getPortrait() { return portrait; }
	public String getSendingTime() { return sendingTime; }
	public Owner getOwner() { return owner; }
	public int getAttributeNum() { return attributeNum; }
	public ItemStyle getItemStyle() { return itemStyle; }

	public void setNickname(String nickname) { this.nickname = nickname; }
	public void setPortrait(String portrait) { this.portrait = portrait; }
	public void setSendingTime(String sendingTime) { this.sendingTime = sendingTime; }
	public void setOwner(Owner owner) { this.owner = owner; }

	public abstract void paintMessage(Graphics g, Point p, int screenWidth);
}
