


import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.*;

/**
 * @author Archie, Thorsten Beckers
 *
 */

public class Oberflaeche extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<String, ImageIcon> imageIconHashMap;
		
	public Oberflaeche()
	{
		super("Bomberman V1");
	}
	/**
	 * Images, Icons, Grafiken werden geladen
	 */

	private void FuelleBilderbuch()
	{
		this.imageIconHashMap = new HashMap<String, ImageIcon>();
		
        this.imageIconHashMap.put(Konstanten.FELD_BOMBE, new ImageIcon("Bilder/Bombe.png"));
		this.imageIconHashMap.put(Konstanten.FELD_FESTE_MAUER, new ImageIcon("Bilder/unkaputtbar.gif"));
		this.imageIconHashMap.put(Konstanten.FELD_FREI, new ImageIcon("Bilder/frei.jpg"));
		this.imageIconHashMap.put(Konstanten.FELD_BOMBERMAN, new ImageIcon("Bilder/Bomberman.gif"));
		this.imageIconHashMap.put(Konstanten.FELD_BOMBERMAN2, new ImageIcon("Bilder/Bomberman2.gif"));
		this.imageIconHashMap.put(Konstanten.FELD_DEADMANN, new ImageIcon("Bilder/Deadman.gif"));//pictures could be enhanced.
		this.imageIconHashMap.put(Konstanten.FELD_AUSGANG, new ImageIcon("Bilder/ausgang.gif"));
	}
	/**
	 * die Oberfläche wird gezeichnet
	 */
	public void Initialisieren(ActionListener al)
	{
		this.FuelleBilderbuch();
		
		this.setSize(Konstanten.FENSTER_BREITE, Konstanten.FENSTER_HOEHE);
		this.setResizable(false);
		this.setJMenuBar(ErzeugeMenuBar(al));
			
		this.addWindowListener(new WindowAdapter() 
		{
		    public void windowClosing(WindowEvent e) 
		    {
		        System.exit(0);
		    }
		});
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
                                if (Bombe.bombTable[i][j]==null)
                                {
                                    aktuellesFeldIcon = this.imageIconHashMap.get(schluesselFeldInhalt);
                                } else {
                                    aktuellesFeldIcon = this.imageIconHashMap.get(Konstanten.FELD_BOMBE);
                                }
				
				JLabel anzeigeLabel = new JLabel(aktuellesFeldIcon);
				panelMitSpielfeld.add(anzeigeLabel);
				
				//oder so:
				//panelMitSpielfeld.add(new JLabel(this.imageIconHashMap.get(spielfeld.feldArray[i][j])));
			}
		}
		
		this.add(panelMitSpielfeld);
		this.pack();
		this.setVisible(true);	

		
			
	}
	
	/**
	 * @author Thorsten Beckers
	 * @param al
	 * @return
	 */
	
	
	//TODO: the menu has no listener.
	private JMenuBar ErzeugeMenuBar(ActionListener al)
	{

		JMenuBar menuBar;
		JMenu menu;
		JMenu subMenu;
		JMenu subSubMenu;
		JMenuItem menuItem;

				
	    
		menuBar = new JMenuBar();
		
		menu = new JMenu("Optionen");

		menuBar.add(menu);
		
		
		
		subMenu = new JMenu("New Game");

		menuItem = new JMenuItem(Konstanten.MENU_1P);
		menuItem.addActionListener(al);
		subMenu.add(menuItem);
		
		menuItem = new JMenuItem(Konstanten.MENU_2P);
		menuItem.addActionListener(al);
		subMenu.add(menuItem);
		
		menuItem = new JMenuItem(Konstanten.MENU_3P);
		subMenu.add(menuItem);
		
		menuItem = new JMenuItem(Konstanten.MENU_4P);
		subMenu.add(menuItem);
		
		menu.add(subMenu);
		
		
		
		subMenu = new JMenu("Statistics");
		
		menuItem = new JMenuItem("Level1 high score list");
		subMenu.add(menuItem);
		
		menuItem = new JMenuItem("Level2 high score list");
		subMenu.add(menuItem);
		menu.add(subMenu);
		
		
		
		
		subMenu = new JMenu("Options");
		
		subSubMenu = new JMenu("Levels");
		
		menuItem = new JMenuItem("Level 1");
		subSubMenu.add(menuItem);
		
		menuItem = new JMenuItem("Level 2");
		subSubMenu.add(menuItem);
		
		subMenu.add(subSubMenu);
		menu.add(subMenu);
		
		/* waere schoener, wenn der Audio Branch
		 * ne Checkbox haette fuer Sound On/Off
		 */
		subSubMenu = new JMenu("Audio");
		
		menuItem = new JMenuItem("Sound Off");
		subSubMenu.add(menuItem);
		
		menuItem = new JMenuItem("Sound On");
		subSubMenu.add(menuItem);
		
		subMenu.add(subSubMenu);
		menu.add(subMenu);
		
		
		menuItem = new JMenuItem("Quit");
		menu.add(menuItem);
		
		
		
		
		menu = new JMenu("Aid");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Manual");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Upgrading");
		menu.add(menuItem);
		
		
		return menuBar;
		
		}



}
