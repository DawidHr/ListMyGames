import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableGames extends AbstractTableModel {
	int rowCount;
	ArrayList<games> opis;

	public TableGames() {
		// TODO Auto-generated constructor stub
	}

	public TableGames(int rowCount, ArrayList<games> opis) {
		this.rowCount = rowCount;
		this.opis = opis;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return opis.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		games opis1 = opis.get(arg0);
		String wyswietl;
		if (arg1 == 0) {
			wyswietl = opis1.getTitle();
		} else {
			wyswietl = opis1.getType();
		}
	//	System.out.println(opis1.getTitle() + " " + arg1);
		return wyswietl;
	}

	public String getColumnName(int c) {
		if (c == 0)
			return "Tytu³";
		else
			return "Typ";
	}

}
