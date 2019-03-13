package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableCell;
import javax.swing.text.TableView.TableRow;

public class test extends JFrame {

	private static final long serialVersionUID = 1L;
	private List<String> colNames;
	private List<List<Object>> data;

	private JTable table;

	private Calendar shieldCalendar;

	public test() {
		super();
		initData();

		Container c = getContentPane();
		table = new JTable();
		table.setModel(new MTableModel());
		table.getColumnModel().getColumn(0).setCellRenderer( new MTableCellRenderer() );
		table.getColumnModel().getColumn(1).setCellRenderer( new MTableCellRenderer() );
		table.getColumnModel().getColumn(2).setCellRenderer( new MTableCellRenderer() );
		c.add(new JScrollPane(table));

		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void initData() {
		colNames = new ArrayList<String>();
		colNames.add("ID");
		colNames.add("Name");
		colNames.add("Date");

		data = new ArrayList<List<Object>>();
		for (int i = 0; i < 20; i++) {
			List<Object> record = new ArrayList<Object>();
			record.add(String.valueOf(i + 1));
			record.add("item" + (i + 1));
			Calendar c = Calendar.getInstance();
			c.set(2010, 3, i + 1);
			record.add(c);

			data.add(record);
		}

		shieldCalendar = Calendar.getInstance();
		shieldCalendar.set(2010, 3, 10);
		
		TableColumn tableColumn = table.getColumn("ID");
		DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
		backGroundColor.setBackground(Color.yellow);
		tableColumn.setCellRenderer(backGroundColor);
	}

	private class MTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return colNames.size();
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public String getColumnName(int column) {
			return colNames.get(column);
		}

		@Override
		public Object getValueAt(int row, int column) {
			return data.get(row).get(column);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	private class MTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			if (value instanceof Calendar) {
				Calendar cal = (Calendar) value;

				if (cal.after(shieldCalendar)) {
					setForeground(Color.red);
				} else if (cal.equals(shieldCalendar)) {
					setForeground(Color.blue);
				} else {
					setForeground(Color.green);
				}

				DateFormat f = DateFormat.getDateInstance(DateFormat.LONG);
				String s = f.format(cal.getTime());
				setText(s);

			} else {
				setForeground(Color.cyan);
			}
			return c;
		}
	}

	public static void main(String[] args) {
		new test();
	}
}
