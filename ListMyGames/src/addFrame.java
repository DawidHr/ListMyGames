import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class addFrame extends JPanel {
JFrame frame;
BazaSqlLite db;

JPanel panelUp = new JPanel();
JPanel panelCenter = new JPanel();
JPanel panelDown = new JPanel();

JLabel textLabel = new JLabel("Dodawanie GIER");
JLabel text2Label = new JLabel("Tytu³: ");
JLabel text3Label = new JLabel("Gatunek");

JTextField gameNameField = new JTextField(20);
JComboBox<String> gameTypeComboBox = new JComboBox<String>();
JButton save = new JButton("Zapisz");


boolean ok;
JDialog dialog;

{
	gameTypeComboBox.addItem("Strategiczna");
	gameTypeComboBox.addItem("MMO");
	gameTypeComboBox.addItem("Strzelanka");
	gameTypeComboBox.addItem("FPS");
	gameTypeComboBox.addItem("RPG");
	gameTypeComboBox.addItem("www");
	gameTypeComboBox.addItem("Zrêcznoœciowa");
	gameTypeComboBox.addItem("Przygodowa");
	gameTypeComboBox.addItem("Symulator");
}

	addFrame() {
		
	}
	addFrame(JFrame frame) {
		this.frame=frame;
		main();
		frame.setSize(400, 200);
	}
	addFrame(JFrame frame, BazaSqlLite db) {
		this.frame=frame;
		this.db = db;
		main();
	}
	
	private void main() {
		setPanelUp();
		setPanelCenter();
		setPanelDown();
		frame.add(panelUp, BorderLayout.NORTH);
		frame.add(panelCenter, BorderLayout.CENTER);
		frame.add(panelDown, BorderLayout.SOUTH);
	}
	private void setPanelUp() {
		panelUp.add(textLabel, BorderLayout.CENTER);
	}
	
	private void setPanelCenter() {
		panelCenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.fill =GridBagConstraints.BOTH;
		panelCenter.add(text2Label, c);
		c.gridx=1;
		panelCenter.add(gameNameField, c);
		c.gridx=0;
		c.gridy=1;
		panelCenter.add(text3Label, c);
		c.gridx=1;
		panelCenter.add(gameTypeComboBox, c);
	}
	
	private void setPanelDown() {
		panelDown.add(save);
		save.addActionListener(listener);
	}
	
	ActionListener listener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String type1 = gameTypeComboBox.getSelectedItem().toString();
			System.out.println(type1);
			db.dodawanieRekordu(gameNameField.getText(), type1);
			panelUp.setVisible(false);
			panelCenter.setVisible(false);
			panelDown.setVisible(false);
			MainPanel main = new MainPanel(frame, db);
		}
	};
	
	
	
/*
public boolean showDialog(Component parent, String title) {
	ok=false;
	Frame owner;
	if(parent instanceof Frame) 
	owner = (Frame) parent;
	else
	owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
	
	if(dialog == null || dialog.getOwner() != owner) {
		dialog = new JDialog(owner, true);
		dialog.add(this);
		dialog.getRootPane().setDefaultButton(save);
		dialog.pack();
	}
	
	dialog.setTitle(title);
	dialog.setVisible(true);
	return ok;
}*/
}
