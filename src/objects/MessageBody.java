package objects;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum Owner{SENDED, RECEIVED};
	public static enum MessageStyle{IMAGE_TEXT_CONTENT, PICTURES_CONTENT, EXPRESSION_CONTENT,
		SYSTEM_TIP_CONTENT, VIOCE_CONTENT, PHON_CALL_CONTENT, LIMI_EXPRESSION_CONTENT,
		TRANSMIT_CONTENT, LUCY_MONEY_CONTENT, TRANSFER_ACCOUNTS_CONTENT, FILE_CONTENT,
		AUDIO_CONTENT, VIDEO_CONTENT, WEB_LINK_CONTENT};
		
	private String nickname;
	private String portrait;
	private String sendingTime;
	private Owner owner;
	private final MessageStyle messageStyle;
	
	public MessageBody(MessageStyle messageStyle) {
		this.messageStyle = messageStyle;
	}

	public String getNickname() { return nickname; }
	public String getPortrait() { return portrait; }
	public String getSendingTime() { return sendingTime; }
	public Owner getOwner() { return owner; }
	public MessageStyle getMessageStyle() { return messageStyle; }

	public void setNickname(String nickname) { this.nickname = nickname; }
	public void setPortrait(String portrait) { this.portrait = portrait; }
	public void setSendingTime(String sendingTime) { this.sendingTime = sendingTime; }
	public void setOwner(Owner owner) { this.owner = owner; }

	public abstract String[] getAttributes();
	public abstract void paintMessage(Graphics g, PageSettings pageSetting);
}
