package objects.messages;

import java.awt.Graphics;
import java.io.Serializable;

import objects.PageSettings;
import objects.ThreeMessageBody;

public class VideoMessage extends ThreeMessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawed;

	public VideoMessage() { super(MessageStyle.VIDEO_CONTENT); }
	public VideoMessage(String iconPath, String fileName, String fileSize) {
		super(MessageStyle.VIDEO_CONTENT, iconPath, fileName, fileSize);
	}

	public boolean isComplete() { return this.isDrawed; }
	public String[] getAttributes() { return new String[] {"Icon Path", "File Name", "File Size"}; }
	
	public String getIconPath() { return super.getStr0(); }
	public String getFileName() { return super.getStr1(); }
	public String getFileSize() { return super.getStr2(); }
	
	public void setIconPath(String iconPath) { super.setStr0(iconPath);; }
	public void setFileName(String fileName) { super.setStr1(fileName);; }
	public void setFileSSize(String fileSize) { super.setStr2(fileSize);; }

	public void paintMessage(Graphics g, PageSettings pageSetting) {
		// TODO Auto-generated method stub

	}
}
