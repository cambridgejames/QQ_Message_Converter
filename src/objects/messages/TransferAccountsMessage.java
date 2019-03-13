package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.MessageBody;
import objects.PageSettings;

public class TransferAccountsMessage extends MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawed;
	private String amount;
	private String content;

	public TransferAccountsMessage() { super(MessageStyle.TRANSFER_ACCOUNTS_CONTENT); }
	public TransferAccountsMessage(String amount, String content) {
		super(MessageStyle.TRANSFER_ACCOUNTS_CONTENT);
		this.setAmount(amount);
		this.setContent(content);
	}
	
	public boolean isComplete() { return this.isDrawed; }
	
	public String getAmount() { return amount; }
	public void setAmount(String amount) { this.amount = amount; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public String[] getAttributes() { return new String[] {"Amount", "Content"}; }
	public boolean setAttributes(String[] attributes) {
		if(attributes.length == 2) {
			this.setAmount(attributes[0]);
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
