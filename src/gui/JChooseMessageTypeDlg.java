package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JChooseMessageTypeDlg extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JLabel styleTipTitle = new JLabel("Message Type Specification:");
	private JTextArea styleTip = new JTextArea();
	private JPanel buttonPane = new JPanel();
	private Button okButton = new Button("OK");
	private Button cancelButton = new Button("Cancel");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JChooseMessageTypeDlg dialog = new JChooseMessageTypeDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		JScrollPane scrollPane = new JScrollPane();
	/**
	 * Create the dialog.
	 */
	public JChooseMessageTypeDlg() {
		this.setTitle("Choose the Type of Message");
		this.setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		this.init();
		
		try{
		    UIManager.setLookAndFeel(new WindowsLookAndFeel());
		    SwingUtilities.updateComponentTreeUI(this);
		}catch(javax.swing.UnsupportedLookAndFeelException e){
		    e.printStackTrace();
		}
	}
	
	private void init() {
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		{
			JList<String> styleList = new JList<String>();
			styleList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					System.out.println(styleTip.getPreferredSize());
				}
			});
			styleList.setModel(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				String[] values = new String[] {"图文 - String/Images Message", "表情 - Expression Message", "图片 - Image Message",
						"系统提示 - System Tip", "厘米秀 - Limi Show Expression", "红包 - Lucky Money", "音/视频通话 - Phone Call",
						"转账 - Transfer Accounts", "转发消息 - Transmit Messages", "语音消息 - Voice Message", "音频文件 - Audio",
						"文档 - File", "视频文件 - Video", "网页链接 - Web Link"};
				public int getSize() { return values.length; }
				public String getElementAt(int index) { return values[index]; }
			});
			scrollPane.setViewportView(styleList);
		}

		contentPanel.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BorderLayout(0, 0));

		styleTipTitle.setVerticalAlignment(SwingConstants.TOP);
		styleTipTitle.setBorder(new EmptyBorder(0, 5, 0, 0));
		styleTipTitle.setPreferredSize(new Dimension(162, 20));
		rightPanel.add(styleTipTitle, BorderLayout.NORTH);

		rightPanel.add(styleTip);
		styleTip.setBorder(new EmptyBorder(0, 5, 0, 5));
		styleTip.setPreferredSize(new Dimension(200, 25));
		styleTip.setLineWrap(true);
		styleTip.setTabSize(4);
		styleTip.setBackground(SystemColor.control);

		buttonPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 0));

		okButton.setPreferredSize(new Dimension(80, 25));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);

		cancelButton.setPreferredSize(new Dimension(80, 25));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

}
