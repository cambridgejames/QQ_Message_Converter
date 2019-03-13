package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import io.ObjectIO;
import io.fileIO;

public class JCompletionDlg extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String currentFolderPath = "";
	private String currentDocument = "";
	
	private JPanel contentPane;
	private JTable viewTable;
	private JComboBox<String> dateList = new JComboBox<String>();
	private JList<String> viewList = new JList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCompletionDlg frame = new JCompletionDlg();
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
	public JCompletionDlg() {
		super("Chat Record Editor");
		
		this.setSize(1000, 700);
		this.setMinimumSize(new Dimension(600, 400));
		
		init();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
		    UIManager.setLookAndFeel(new WindowsLookAndFeel());
		    SwingUtilities.updateComponentTreeUI(this);
		}catch(javax.swing.UnsupportedLookAndFeelException e){
		    e.printStackTrace();
		}
	}
	
	private void init() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
            	JFileChooser jfc = new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int value = jfc.showOpenDialog(null);
		        if (value == JFileChooser.APPROVE_OPTION) {
		        	loadDateList(jfc.getSelectedFile().getAbsolutePath());
		        }
			}
		});
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		mntmOpenFile.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmOpenFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setPreferredSize(new Dimension(460, 100));
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel selectPanel = new JPanel();
		leftPanel.add(selectPanel, BorderLayout.NORTH);
		selectPanel.setLayout(null);
		selectPanel.setPreferredSize(new Dimension(100, 30));
		
		JLabel lblDateOfMessages = new JLabel("Date Of Messages:");
		lblDateOfMessages.setBounds(10, 5, 115, 19);
		selectPanel.add(lblDateOfMessages);
		
		dateList.setMaximumRowCount(20);
		dateList.setBounds(135, 3, 268, 23);
		dateList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				String selectedValue = dateList.getSelectedItem().toString();
				if(!currentDocument.equals(selectedValue)) {
					viewList.setListData(fileIO.fileInputList(currentFolderPath + File.separator + selectedValue + ".html"));
					currentDocument = selectedValue;
					System.gc();	// Notify JVM for garbage collection.
				}
			}
		});
		selectPanel.add(dateList);
		
		JScrollPane scrollPane = new JScrollPane();
		leftPanel.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(viewList);
		
		Button btnInsert = new Button("Insert >>");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		leftPanel.add(btnInsert, BorderLayout.SOUTH);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane rightSplitPane = new JSplitPane();
		rightSplitPane.setBackground(Color.WHITE);
		rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		rightPanel.add(rightSplitPane);
		
		JScrollPane viewPane = new JScrollPane();
		rightSplitPane.setBackground(Color.WHITE);
		viewPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rightSplitPane.setLeftComponent(viewPane);
		
		viewTable = new JTable();
		String[] columnNames = {"Time", "Owner", "Portrait", "Style", "Content"};
		viewTable.setModel(new DefaultTableModel(new String[][] {}, columnNames) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { return false; }
	    });
		tableColumnInit(0, false, 100, 100, 100);
		tableColumnInit(1, false, 50, 50, 50);
		tableColumnInit(2, false, 60, 60, 60);
		tableColumnInit(3, false, 100, 100, 100);
		viewPane.setViewportView(viewTable);
		
		JPanel editorPanel = new JPanel();
		rightSplitPane.setRightComponent(editorPanel);
		editorPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel functionPanel = new JPanel();
		editorPanel.add(functionPanel, BorderLayout.NORTH);
		functionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		Button btnNew = new Button("New");
		functionPanel.add(btnNew);
		
		Button btnEdit = new Button("Edit");
		functionPanel.add(btnEdit);
		
		JScrollPane editPane = new JScrollPane();
		editPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		editorPanel.add(editPane, BorderLayout.CENTER);
	}

	private void tableColumnInit(int columnIndex, boolean resizable, int preferredWidth, int minWidth, int maxWidth) {
		viewTable.getColumnModel().getColumn(columnIndex).setResizable(resizable);
		viewTable.getColumnModel().getColumn(columnIndex).setPreferredWidth(preferredWidth);
		viewTable.getColumnModel().getColumn(columnIndex).setMinWidth(minWidth);
		viewTable.getColumnModel().getColumn(columnIndex).setMaxWidth(maxWidth);
	}
	
	private boolean loadDateList(String folderPath) {
		LinkedList<String> cache = null;
		try {
			cache = ObjectIO.inputDateList(folderPath);
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "No project profile exists in the specified folder.",
					"Error", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to load the property file.",
					"Error", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return false;
		}
		this.currentFolderPath = folderPath;
		while(!cache.isEmpty()) {
			this.dateList.addItem(cache.remove());
		}
		return true;
	}
	
}
