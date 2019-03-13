package gui.unit;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import objects.MessageBody.MessageStyle;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextPane;
import java.awt.Dimension;

public class ImageTextEditUnit extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ImageTextEditUnit(MessageStyle messageStyle) {
		setMinimumSize(new Dimension(300, 250));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setPreferredSize(new Dimension(93, 25));
		add(btnNewButton, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPane, BorderLayout.CENTER);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 10));
		scrollPane.setColumnHeaderView(verticalStrut);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}
}
