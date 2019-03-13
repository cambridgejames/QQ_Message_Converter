package gui.unit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class SingleTextMessageUnit extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField content;

	/**
	 * Create the panel.
	 */
	public SingleTextMessageUnit() {
		setMinimumSize(new Dimension(300, 250));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		
		JLabel label = new JLabel("New label");
		panel.add(label);
		
		content = new JTextField();
		panel.add(content);
		content.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse...");
		btnBrowse.setPreferredSize(new Dimension(93, 25));
		panel.add(btnBrowse);

	}
}
