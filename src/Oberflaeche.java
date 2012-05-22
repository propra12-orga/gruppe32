
import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JMenu;

import javax.swing.*;

import org.omg.CORBA.portable.InputStream;



public class Oberflaeche extends JFrame
{
	private static final long serialVersionUID = 1L;

	private HashMap<String, ImageIcon> imageIconHashMap;
		
	
	public Oberflaeche()
	{
		super("Bomberman V1");
	}
	
	/*Liest die Images ein - muss noch geändert werden zu nem relativen Pfad
	 * damit jeder Zugriff auf Bilder, sonst doof
	 */
	private void FuelleBilderbuch()
	{
		this.imageIconHashMap = new HashMap<String, ImageIcon>();
	   
	    this.imageIconHashMap.put(Konstanten.FELD_FESTE_MAUER, new ImageIcon("Bilder/unkaputtbar.gif"));
		this.imageIconHashMap.put(Konstanten.FELD_FREI, new ImageIcon("Bilder/frei.jpg"));
		this.imageIconHashMap.put(Konstanten.FELD_BOMBERMAN, new ImageIcon("Bilder/Bomberman.gif"));
	}
	
	/*
	*Frame und Panel initialisieren
	*Bidde MENUEBAR auch hier rain
	*/
	
	public void Initialisieren()
	{
		this.FuelleBilderbuch();
		
		this.setSize(Konstanten.FENSTER_BREITE, Konstanten.FENSTER_HOEHE);
		this.setResizable(false);
		
			
		this.addWindowListener(new WindowAdapter() 
		{
		    public void windowClosing(WindowEvent e) 
		    {
		        System.exit(0);
		    }
		});
	}

	/*
	 * Damit nach Neuzeichnung der Oberflaeche dann auch wieder alles
	 * sichtbar ist 
	 */
	private void FinalisiereFrame() {
		
		//this.update(getGraphics());
		this.pack();
		this.setVisible(true);
		//this.repaint();
		}	
	
	
	private JPanel GetPanel()
	{
		GridLayout layout = new GridLayout(0, Konstanten.FELD_ANZAHL_SPALTEN);
		layout.setHgap(1);
		layout.setVgap(1);
		
		JPanel p = new JPanel(layout);
		p.setBorder(BorderFactory.createLineBorder(Color.black, 5));

		return p;
	}
	
	public void ZeichneSpielfeld(Spielfeld spielfeld)
	{
		JPanel panelMitSpielfeld = this.GetPanel();
		
		panelMitSpielfeld.removeAll();
				
		for (int i = 0; i < Konstanten.FELD_ANZAHL_ZEILEN; i++)
		{
			for (int j = 0; j < Konstanten.FELD_ANZAHL_SPALTEN; j++)
			{
				ImageIcon aktuellesFeldIcon = new ImageIcon();
				String schluesselFeldInhalt = spielfeld.feldArray[i][j];
				aktuellesFeldIcon = this.imageIconHashMap.get(schluesselFeldInhalt);
				
				JLabel anzeigeLabel = new JLabel(aktuellesFeldIcon);
				panelMitSpielfeld.add(anzeigeLabel);
				
				//oder so:
				//panelMitSpielfeld.add(new JLabel(this.imageIconHashMap.get(spielfeld.feldArray[i][j])));
			}
		}
		
		this.add(panelMitSpielfeld);
		this.FinalisiereFrame();
			
	}
	
}
