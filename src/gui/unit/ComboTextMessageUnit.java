package gui.unit;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class ComboTextMessageUnit extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public ComboTextMessageUnit() {
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
		
		JComboBox comboBox_0 = new JComboBox();
		panel_0.add(comboBox_0);
		
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

	}

}
