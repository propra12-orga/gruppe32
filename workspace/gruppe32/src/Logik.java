
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * startet Oberflaeche, Spielfeld, Bombmann 
 * und prueft die aktuelle Spielsituation
 * @author Archie, Jingwen
 *
 */
public class Logik implements KeyListener, ActionListener, ItemListener
{
	private Spielfeld spielfeld;
	private Oberflaeche oberflaeche;	
	
	public Logik()
	{
	}
	
	/**
	 * (re)start the game.
	 * 
	 * @param menuText {@code Konstanten.MENU_1P} for starting with 1 player,
	 *                 {@code Konstanten.MENU_2P} for starting with 2 players,
	 *                 anything else, e.g. "" for starting with default setting: 1 player
	 */
	public void StarteLogik(String menuText)
	{
		if(this.oberflaeche!= null) {
			this.oberflaeche.dispose();
			this.oberflaeche = null;
		}
		if (this.spielfeld != null) {
			this.spielfeld = null;
			System.gc();
		}
		this.oberflaeche = new Oberflaeche();
		this.oberflaeche.Initialisieren((ActionListener)this);
		this.oberflaeche.addKeyListener(this);
		
		this.spielfeld = new Spielfeld();
		this.spielfeld.LeseSpielfeldDatei("feld_Meilenstein1.xml");
		//Bombmann initialisierung
		BombMannController.initController();
		try {
			//After the menu bar is implemented, it can use
			//the following lines for corresponding selection of 
			// 1 player or 2 players. 
			if(menuText.equalsIgnoreCase(Konstanten.MENU_1P)) {
				BombMannController.initPlayers(this.spielfeld, 1);
			}
			else if (menuText.equalsIgnoreCase(Konstanten.MENU_2P)){
				BombMannController.initPlayers(this.spielfeld, 2);
			}
			else {		//default 1 player game
				BombMannController.initPlayers(this.spielfeld, 1);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
        // bombe initialisieren
        Bombe.init(this.spielfeld,this.oberflaeche);
		
		//Bombmann initialisierung Ende.
		this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
                
	}
	
	/**
	 * Bombmann Steuerung via Tastatur, 
	 * nach jeder Bombermanbewegung
	 * wird Spielfeld neu gezeichnet
	 */
	
 	@Override
	public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
		//Bombmann steuerung.
		int keycode = e.getKeyCode();
	    if (BombMannController.containsActionKey(keycode)) {
            BombMannController.execute(keycode);
	    }
	    	
	   /* this part is moved into bombmannController.execute() because the position must be determined by the bombmann.
	    if (e.getKeyCode() == KeyEvent.VK_SPACE )
            {
                Bombe foo = new Bombe(this.bombmann.getX(),this.bombmann.getY(),3,3.0,0);
            }
	   */
	    this.oberflaeche.ZeichneSpielfeld(this.spielfeld); 
	    
		  //Bombmann Steuerung Ende.
	}

	@Override
	public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	}


@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent arg0) {
	String menuText =  arg0.getActionCommand();
	
	//only handle the menu option for "restarting the game with different number of players".
	if(menuText.equalsIgnoreCase(Konstanten.MENU_1P) || menuText.equalsIgnoreCase(Konstanten.MENU_2P)
			||menuText.equalsIgnoreCase(Konstanten.MENU_3P) || menuText.equalsIgnoreCase(Konstanten.MENU_4P))
	{
		StarteLogik(menuText);
	}

}


}