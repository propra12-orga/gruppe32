
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
  /*von hier aus geht's los,
   * Spielfeld machen und Oberflaeche machen
   * Spielfeld als untere Schicht, 
   * auf der die Oberfläche "gebaut" wird
   */
public class Logik implements KeyListener
{
	private Spielfeld spielfeld;
	private Oberflaeche oberflaeche;	
<<<<<<< HEAD
	private Bombmann bombmann;
=======
>>>>>>> bec834a38603c1de4dd6926ecc6b6b3837be76d1
	
	public Logik()
	{
	}
	
	public void StarteLogik()
	{
		this.oberflaeche = new Oberflaeche();
		this.oberflaeche.Initialisieren();
		this.oberflaeche.addKeyListener(this);
		
		this.spielfeld = new Spielfeld();
		this.spielfeld.SetzeSpielfeldMeilenstein1();
<<<<<<< HEAD
		//Bombmann initialisierung
		try {
			this.bombmann = new Bombmann(this.spielfeld);
		}
		catch (BombMannException e)
		{
			System.out.println(e.getMessage());
			//System.exit(0);
		}
		//Bombmann initialisierung Ende.
		this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
		
=======
		
		this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
>>>>>>> bec834a38603c1de4dd6926ecc6b6b3837be76d1
	}
	
	
 //Hier Spielfeld neu zeichnen nach KeyEvent zett.Beh. aktualisiere Spielfeld nach Zug.
 //vorerst nur keyPressed nötig	
	
	@Override
	public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
<<<<<<< HEAD
		
		//Bombmann steuerung.
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            this.bombmann.moveRight();
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
	    	this.bombmann.moveLeft();
	    } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
	    	this.bombmann.moveUp();
	    } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
	    	this.bombmann.moveDown();
	    }
	    this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
	  //Bombmann steuerung Ende.
=======
>>>>>>> bec834a38603c1de4dd6926ecc6b6b3837be76d1
	}

	@Override
	public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	}



// und prüfen, ob dann der Spielzug auch klar geht
	private Boolean PruefeSpielzug()
	{
	//TODO 
		return true;
	}
}