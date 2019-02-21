package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class PageSettings {
	private int areaWidth;
	private int areaHeight;
	private int colNumber;
	
	private Point datum = new Point(12, 12);
	private int colIndex = 0;
	
	public PageSettings(int areaWidth, int areaHeight, int colNumber) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.colNumber = colNumber;
	}
	
	public int currentColLeft(int minHeight) {
		int areaLeftHeight = this.areaHeight - 24 - datum.y;
		return areaLeftHeight >= minHeight ? areaHeight : 0;
	}
	
	public Point updateDatum(int height) {
		Point oldDatum = new Point();
		if(currentColLeft(height) == 0) {
			if (++colIndex == colNumber) { return null; }
			oldDatum.setLocation(areaWidth / colNumber * colIndex + 12, 12);
		}
		else {
			oldDatum.setLocation(datum);
		}
		datum.setLocation(oldDatum.x + height, oldDatum.y);
		return oldDatum;
	}
	
	public void resetArea() { resetArea(this.areaWidth, this.areaHeight, this.colNumber); }
	public void resetArea(int areaWidth, int areaHeight, int colNumber) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.colNumber = colNumber;
		this.datum.setLocation(12, 12);
		this.colIndex = 0;
	}
	
	public void setColNumber(int colNumber) { this.colNumber = colNumber; }

	public int getAreaWidth() { return areaWidth; }
	public int getAreaHeight() { return areaHeight; }
	public int getColNumber() { return colNumber; }
	
	public BufferedImage getImagePage() {
		BufferedImage image = new BufferedImage(this.areaWidth, this.areaHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
		g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g2d.setPaint(Color.DARK_GRAY);
		for(int index = 1; index < this.colNumber; index++) {
			int offset = areaWidth / colNumber * index;
			g2d.drawLine(offset, 12, offset, areaHeight - 12);
		}
		return image;
	}
}
