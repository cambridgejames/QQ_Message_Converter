package objects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MessageBody implements Serializable{
	private static final long serialVersionUID = 1L;
	private static enum Owner {SENDED, RECEIVED};
	
	private Owner owner;
	private String nickname;
	private String portrait;
	private String sendingTime;
	private List<String> messageList = new LinkedList<String>();
	
	public MessageBody() {}
	public MessageBody(Owner owner, String nickname, String portrait, String sendingTime) {
		this.setOwner(owner);
		this.setNickname(nickname);
		this.setPortrait(portrait);
		this.setSendingTime(sendingTime);
	}
	public MessageBody(MessageBody messageBody) {
		this.setOwner(messageBody.getOwner());
		this.setNickname(messageBody.getNickname());
		this.setPortrait(messageBody.getPortrait());
		this.setSendingTime(messageBody.getSendingTime());
	}
	
	public Owner getOwner() { return owner; }
	public String getNickname() { return nickname; }
	public String getPortrait() { return portrait; }
	public String getSendingTime() { return sendingTime; }
	public List<String> getMessageList() { return messageList; }
	
	public void setOwner(Owner owner) { this.owner = owner; }
	public void setNickname(String nickname) { this.nickname = nickname; }
	public void setPortrait(String portrait) { this.portrait = portrait; }
	public void setSendingTime(String sendingTime) { this.sendingTime = sendingTime; }
	public void setMessageList(List<String> messageList) { this.messageList = messageList; }
	
	public void insert(String messageItem) {
		this.messageList.add(messageItem);
	}
	
	@Override
	public String toString() {
		return this.messageList.toString();
	}
}
