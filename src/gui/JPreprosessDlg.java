package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.BorderFactory;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;

import io.ObjectIO;
import io.fileIO;

public class JPreprosessDlg extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String currentDocument = null;
	private String currentFolderPath = null;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JPanel mainPanel = new JPanel();
	private JPanel functionPanel = new JPanel();
	private JPanel informationPanel = new JPanel();
	private JList<String> dateList = new JList<String>();
	private JScrollPane dateListPane = new JScrollPane(dateList);
	private JPanel viewPanel = new JPanel();
	
	private JLabel rawFileLabel = new JLabel("HTML file:");
	private JLabel destFolderLabel = new JLabel("Destination folder:");
	private JLabel listLabel = new JLabel("Date list:");
	private JTextField rawFileInput = new JTextField("C:\\Users\\pjq\\Desktop\\傻婷儿(1175469766).htm");
	private JTextField destFolderInput = new JTextField("C:\\Users\\pjq\\Desktop");
	private Button rawFileBrowse = new Button("Browse");
	private Button destFolderBrowse = new Button("Browse");
	private Button startAnalysis = new Button("Analysis");
	
	private JLabel viewLabel = new JLabel("Message content:");
	private JTextPane viewArea = new JTextPane();
	private JScrollPane viewAreaPane = new JScrollPane(viewArea);
	
	public JPreprosessDlg(String filePath, String folderPath) {
		super("Import message record");
		if(filePath != null && folderPath != null) {
			rawFileInput.setText(filePath);
			destFolderInput.setText(folderPath);
		}
		
		this.setSize(800, 600);
		this.setMinimumSize(new Dimension(600, 400));
		
		init(350);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init(int leftPanelWidth) {
		this.menuSetup();
		this.setJMenuBar(menuBar);
		this.add("Center", mainPanel);
		this.buttonSetup();
		this.listSetup();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		mainPanel.add("West", functionPanel);
		mainPanel.add("Center", viewPanel);
		
		functionPanel.setPreferredSize(new Dimension(leftPanelWidth, 100));
		functionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		functionPanel.setLayout(new BorderLayout());
		functionPanel.add("North", informationPanel);
		functionPanel.add("Center", dateListPane);
		
		informationPanel.setPreferredSize(new Dimension(leftPanelWidth, 175));
		informationPanel.setLayout(null);
		informationPanel.add(rawFileLabel);
		informationPanel.add(rawFileInput);
		informationPanel.add(rawFileBrowse);
		informationPanel.add(destFolderLabel);
		informationPanel.add(destFolderInput);
		informationPanel.add(destFolderBrowse);
		informationPanel.add(startAnalysis);
		informationPanel.add(listLabel);
		rawFileInput.setEditable(false);
		destFolderInput.setEditable(false);
		rawFileInput.setBackground(Color.WHITE);
		destFolderInput.setBackground(Color.WHITE);
		rawFileLabel.setBounds(0, 0, 200, 25);
		rawFileInput.setBounds(0, 25, 280, 25);
		rawFileBrowse.setBounds(290, 25, 50, 25);
		destFolderLabel.setBounds(0, 55, 200, 25);
		destFolderInput.setBounds(0, 80, 280, 25);
		destFolderBrowse.setBounds(290, 80, 50, 25);
		startAnalysis.setBounds(0, 120, 340, 25);
		listLabel.setBounds(0, 150, 150, 25);
		
		viewPanel.setLayout(new BorderLayout());
		viewPanel.add("North", viewLabel);
		viewPanel.add("Center", viewAreaPane);
		viewLabel.setPreferredSize(new Dimension(100, 25));
		viewArea.setEditable(false);
		viewArea.setContentType("text/html");
	}
	
	private void menuSetup() {
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		JMenuItem lodeProperties = new JMenuItem("Load Property File...");
		lodeProperties.setMnemonic(KeyEvent.VK_L);
		lodeProperties.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		fileMenu.add(lodeProperties);
		
		lodeProperties.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	JFileChooser jfc = new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int value = jfc.showOpenDialog(null);
		        if (value == JFileChooser.APPROVE_OPTION) {
		        	loadDateList(jfc.getSelectedFile().getAbsolutePath());
		        }
            }
        });
	}
	
	private void buttonSetup() {
		rawFileBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Webpage *.htm *.html", "htm", "html");
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(filter);
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int value = jfc.showOpenDialog(null);
		        if (value == JFileChooser.APPROVE_OPTION) {
		        	rawFileInput.setText(jfc.getSelectedFile().getAbsolutePath());
		        }
			}
		});
		destFolderBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser jfc = new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int value = jfc.showOpenDialog(null);
		        if (value == JFileChooser.APPROVE_OPTION) {
		        	destFolderInput.setText(jfc.getSelectedFile().getAbsolutePath());
		        }
			}
		});
		startAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(!messageAnalysis()) {
					JOptionPane.showMessageDialog(null, "The specified file may not be processed correctly.",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
				System.gc();	// Notify JVM for garbage collection.
			}
		});
	}
	
	private void listSetup() {
		dateList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(dateList.getSelectedIndex() != -1) {
					if(me.getClickCount() == 1 && dateList.getSelectedValue() != currentDocument) {
						String selectedValue = dateList.getSelectedValue();
						viewArea.setText(fileIO.fileInputAll(currentFolderPath + File.separator + selectedValue + ".html"));
						currentDocument = selectedValue;
						System.gc();	// Notify JVM for garbage collection.
					}
				}
			}
		});
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
		this.dateList.setListData(cache.toArray(new String[0]));
		this.currentFolderPath = folderPath;
		this.viewArea.setText("");
		return true;
	}
	
	public boolean messageAnalysis() {
		String filePath = rawFileInput.getText();
		String folderPath = destFolderInput.getText();
		if(filePath.length() == 0 || folderPath.length() == 0) {
			JOptionPane.showMessageDialog(this, "File or folder paths cannot be empty.",
					"Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		JProgressDlg progressDlg = new JProgressDlg(this, filePath, folderPath);
		if(!progressDlg.getNUmberOfLine()) { return false; }
		progressDlg.setVisible(true);
		if(!progressDlg.getSolution()) { return false; }
		if(!loadDateList(progressDlg.getFolderPath())) { return false; }
		return true;
	}
	
	public static void main(String[] args) {
		FontUIResource fontResource = new FontUIResource(new Font("微软雅黑", Font.PLAIN, 12));
	    for(Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if(value instanceof FontUIResource) {
	            UIManager.put(key, fontResource);
	        }
	    }
	    
	    JPreprosessDlg preprosessDlg;
		if(args.length == 0) {
			preprosessDlg = new JPreprosessDlg(null, null);
			preprosessDlg.setVisible(true);
		}
	}
}
