package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import io.ObjectIO;
import io.fileIO;
import tools.RegularMatch;
import tools.RegularMatch.lineStyle;

public class JProgressDlg extends JDialog implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private boolean solution;
	private String filePath;
	private String folderPath;
	private LinkedList<String> dateList = new LinkedList<String>();
	
	private JProgressBar progressBar;
	private Button cancelButton = new Button("Cancel");
	
	private Thread analysisThread;
	private BufferedReader bufferedReader;
	private SimpleDateFormat df = new SimpleDateFormat("YYYYMMDDHHmmss");
	
	public JProgressDlg(JPreprosessDlg parentDlg, String filePath, String folderPath) {
		super(parentDlg, "File Analyzer", true);
		this.filePath = filePath;
		this.folderPath = folderPath;
		this.setSize(500, 115);
		this.setResizable(false);
		this.setLocationRelativeTo(parentDlg);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		this.setLayout(null);
		
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		progressBar.setStringPainted(true);

		this.add(progressBar);
		this.add(cancelButton);
		
		progressBar.setBounds(10, 10, 462, 25);
		cancelButton.setBounds(205, 40, 70, 25);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				analysisThread.interrupt();
				dispose();
			}
		});
	}
	
	public boolean getNUmberOfLine() {
		try {
			FileReader fileReader = new FileReader(filePath);
			LineNumberReader lineReader = new LineNumberReader(fileReader);
			lineReader.skip(Long.MAX_VALUE);
			this.progressBar.setMaximum(lineReader.getLineNumber());
			lineReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "The specified file was not found.",
					"Error", JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading file " + filePath + ".",
					"Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void setVisible(boolean visible) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() { public void run() { analysis(); } }, 50);
		super.setVisible(visible);
	}
	
	public boolean getSolution() {
		return this.solution;
	}
	
	public String getFolderPath() {
		return this.folderPath;
	}
	
	private boolean analysis() {
		this.analysisThread = new Thread(this);
		this.analysisThread.start();
		return true;
	}

	public void run() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			this.dispose();
			return;
		}
		
		int currentNumber = 0;
		String currentDate;
		String currentLine;
		String currentRecord;
		String folderName = this.filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));
		String oldPath = this.filePath.substring(0, filePath.lastIndexOf(File.separator)) + File.separator + folderName + "_files";
		folderPath += File.separator + folderName + "_splitDocument_" + df.format(new Date(System.currentTimeMillis()));
		
		try {
			bufferedReader = new BufferedReader(fileReader);
			FileOutputStream fos = null;
			OutputStreamWriter osw = null;
			BufferedWriter bw = null;
			
			new File(folderPath).mkdirs();
			
			setTitle("Copying resource files...");
			fileIO.copyDir(oldPath, folderPath + File.separator + folderName + "_files");
			
			setTitle("The content of the message is being analyzed...");
			while((currentLine = bufferedReader.readLine()) != null && !Thread.currentThread().isInterrupted()) {
				if((currentRecord = RegularMatch.messageAnalysis(currentLine, lineStyle.DELIVERY_DATE)) != null) {
					if(bw != null) bw.close();
					if(osw != null) osw.close();
					if(fos != null) fos.close();
					currentDate = RegularMatch.getDate(currentRecord);
					fos = new FileOutputStream(folderPath + File.separator + currentDate + ".html", true);
					osw = new OutputStreamWriter(fos, "UTF-8");
					bw = new BufferedWriter(osw);
					dateList.add(currentDate);
					currentLine = currentLine.replace(currentRecord, "");
				}
				if((currentRecord = RegularMatch.messageAnalysis(currentLine, lineStyle.MESSAGE_CONTENT)) != null) {
					bw.write(RegularMatch.dat2jpgHtml(currentRecord) + "\n");
					currentLine = currentLine.replace(currentRecord, "");
				}
				progressBar.setValue(++currentNumber);
			}

			if(bw != null) bw.close();
			if(osw != null) osw.close();
			if(fos != null) fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.dispose();
			return;
		}
		
		try {
			ObjectIO.outputDateList(dateList, folderPath);
		} catch (Exception e) {
			e.printStackTrace();
			this.dispose();
			return;
		}
		
		setTitle("File Analyzer");
		this.solution = true;
		this.dispose();
	}
}
