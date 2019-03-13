package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JFormattedTextField;

public class JCompletionDlg1 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane = new JPanel();
	private JSplitPane mainSplitPane = new JSplitPane();
	
	private JPanel leftPanel = new JPanel();
	private JPanel selectPanel = new JPanel();
	private JLabel lblDateOfMessages = new JLabel("Date of Messages");
	private JComboBox<String> dateList = new JComboBox<String>();
	
	private JPanel functionPanel = new JPanel();
	private JPanel generalPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton btnNew = new JButton("New");
	private JButton btnEdit = new JButton("Edit");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnMoveUp = new JButton("Move Up");
	private JButton btnMoveDown = new JButton("Move Down");
	
	private JPanel attributePanel = new JPanel();
	private JPanel labelPanel = new JPanel();
	private JLabel lblNickname = new JLabel("Nickname");
	private JLabel lblPortrait = new JLabel("Portrait");
	private JLabel lblSendingTime = new JLabel("Sending Time");
	private JLabel lblOwner = new JLabel("Owner");
	private JLabel lblRemark = new JLabel("Remark");
	private JLabel lblMessageStyle = new JLabel("Message Style");
	private JPanel editPanel = new JPanel();
	private JTextField edtNickname = new JTextField();
	private JPanel edtPortrait = new JPanel();
	private JPanel portraitView = new JPanel();
	private JButton btnPortraitBrowse = new JButton("Browse...");
	private JComboBox<String> edtOwner = new JComboBox<String>();
	private JTextField edtRemark = new JTextField();
	private JComboBox<String> edtMessageStyle = new JComboBox<String>();
	
	private JScrollPane specialScrollPane = new JScrollPane();
	
	private JScrollPane rightScrollPane = new JScrollPane();
	private JTable viewTable = new JTable();
	private final JFormattedTextField edtSendingTime = new JFormattedTextField();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCompletionDlg1 frame = new JCompletionDlg1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCompletionDlg1() {
		super("Chat Record Editor");
		
		this.setSize(1000, 700);
		this.setMinimumSize(new Dimension(600, 400));
		
		this.menuSetup();
		this.init();
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
		    UIManager.setLookAndFeel(new WindowsLookAndFeel());
		    SwingUtilities.updateComponentTreeUI(this);
		}catch(javax.swing.UnsupportedLookAndFeelException e){
		    e.printStackTrace();
		}
	}
	
	private void menuSetup() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnNew.setPreferredSize(new Dimension(300, 26));
		mnFile.add(mnNew);
		
		JMenuItem mntmStringimageMessage = new JMenuItem("String/Image Message");
		mntmStringimageMessage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		mnNew.add(mntmStringimageMessage);
		
		JMenuItem mntmDate = new JMenuItem("Date");
		mntmDate.setPreferredSize(new Dimension(200, 26));
		mnNew.add(mntmDate);
		
		JMenuItem mntmLoadRecord = new JMenuItem("Load Record...");
		mntmLoadRecord.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		mnFile.add(mntmLoadRecord);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		mnFile.add(mntmOpenFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
	
	private void init() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(mainSplitPane, BorderLayout.CENTER);
		mainSplitPane.setRightComponent(rightScrollPane);
		
		leftPanel.setPreferredSize(new Dimension(400, 10));
		mainSplitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		selectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		selectPanel.setPreferredSize(new Dimension(10, 35));
		selectPanel.setSize(new Dimension(0, 30));
		leftPanel.add(selectPanel, BorderLayout.NORTH);
		selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.X_AXIS));
		
		selectPanel.add(lblDateOfMessages);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(10, 0));
		selectPanel.add(horizontalStrut);
		
		dateList.setMinimumSize(new Dimension(32, 25));
		dateList.setPreferredSize(new Dimension(32, 25));
		selectPanel.add(dateList);
		
		leftPanel.add(functionPanel, BorderLayout.CENTER);
		functionPanel.setLayout(new BorderLayout(0, 0));
		
		functionPanel.add(generalPanel, BorderLayout.NORTH);
		generalPanel.setLayout(new BorderLayout(0, 0));
		
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		generalPanel.add(buttonPanel, BorderLayout.NORTH);
		
		buttonPanel.add(btnNew);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnCancel);
		buttonPanel.add(btnMoveUp);
		buttonPanel.add(btnMoveDown);
		
		attributePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		generalPanel.add(attributePanel, BorderLayout.CENTER);
		attributePanel.setLayout(new BoxLayout(attributePanel, BoxLayout.X_AXIS));
		
		labelPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		labelPanel.setMinimumSize(new Dimension(100, 10));
		labelPanel.setMaximumSize(new Dimension(100, 32767));
		labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		attributePanel.add(labelPanel);
		labelPanel.setLayout(new GridLayout(0, 1, 0, 10));
		
		labelPanel.add(lblNickname);
		labelPanel.add(lblPortrait);
		labelPanel.add(lblSendingTime);
		labelPanel.add(lblOwner);
		labelPanel.add(lblRemark);
		labelPanel.add(lblMessageStyle);
		
		editPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
		attributePanel.add(editPanel);
		editPanel.setLayout(new GridLayout(0, 1, 0, 10));
		
		editPanel.add(edtNickname);
		edtNickname.setColumns(10);
		
		edtPortrait.setPreferredSize(new Dimension(10, 25));
		editPanel.add(edtPortrait);
		edtPortrait.setLayout(new BorderLayout(10, 0));
		
		portraitView.setBackground(Color.WHITE);
		portraitView.setPreferredSize(new Dimension(25, 25));
		edtPortrait.add(portraitView, BorderLayout.WEST);
		
		edtPortrait.add(btnPortraitBrowse);
		editPanel.add(edtSendingTime);
		editPanel.add(edtOwner);
		editPanel.add(edtRemark);
		edtRemark.setColumns(10);
		
		editPanel.add(edtMessageStyle);
		
		functionPanel.add(specialScrollPane, BorderLayout.CENTER);
		
		String[] columnNames = {"Time", "Style", "Content", "Remarks"};
		viewTable.setModel(new DefaultTableModel(new String[][] {}, columnNames) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { return false; }
	    });
		tableColumnInit(0, false, 100, 100, 100);
		tableColumnInit(1, false, 100, 100, 100);
		tableColumnInit(3, true, 60, 60, Integer.MAX_VALUE);
		rightScrollPane.setViewportView(viewTable);
	}

	private void tableColumnInit(int columnIndex, boolean resizable, int preferredWidth, int minWidth, int maxWidth) {
		viewTable.getColumnModel().getColumn(columnIndex).setResizable(resizable);
		viewTable.getColumnModel().getColumn(columnIndex).setPreferredWidth(preferredWidth);
		viewTable.getColumnModel().getColumn(columnIndex).setMinWidth(minWidth);
		viewTable.getColumnModel().getColumn(columnIndex).setMaxWidth(maxWidth);
	}
}
