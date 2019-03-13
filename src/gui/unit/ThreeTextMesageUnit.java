package gui.unit;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

public class ThreeTextMesageUnit extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textField_0;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public ThreeTextMesageUnit() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 10));
		
		JPanel panel_0 = new JPanel();
		mainPanel.add(panel_0);
		panel_0.setLayout(new GridLayout(0, 1, 0, 10));
		
		JLabel label_0 = new JLabel("New label");
		panel_0.add(label_0);
		
		textField_0 = new JTextField();
		textField_0.setColumns(10);
		panel_0.add(textField_0);
		
		JButton button_0 = new JButton("Browse...");
		button_0.setPreferredSize(new Dimension(93, 25));
		panel_0.add(button_0);
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 10));
		
		JLabel label_1 = new JLabel("New label");
		panel_1.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_1.add(textField_1);
		
		JButton button_1 = new JButton("Browse...");
		button_1.setPreferredSize(new Dimension(93, 25));
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		mainPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 10));
		
		JLabel label_2 = new JLabel("New label");
		panel_2.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_2.add(textField_2);
		
		JButton button_2 = new JButton("Browse...");
		button_2.setPreferredSize(new Dimension(93, 25));
		panel_2.add(button_2);

	}

}
