
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
<<<<<<< HEAD
  
/**
 * startet Oberflaeche, Spielfeld, Bombmann 
 * und prueft die aktuelle Spielsituation
 * @author Archie
 *
 */
=======
  /*von hier aus geht's los,
   * Spielfeld machen und Oberflaeche machen
   * Spielfeld als untere Schicht, 
   * auf der die Oberfl�che "gebaut" wird
   */
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
public class Logik implements KeyListener
{
	private Spielfeld spielfeld;
	private Oberflaeche oberflaeche;	
	private Bombmann bombmann;
<<<<<<< HEAD
	
	private SpielSituation spielSituation;
=======
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	
	public Logik()
	{
	}
	
	public void StarteLogik()
	{
		this.oberflaeche = new Oberflaeche();
		this.oberflaeche.Initialisieren();
		this.oberflaeche.addKeyListener(this);
		
		this.spielfeld = new Spielfeld();
<<<<<<< HEAD
		this.spielfeld.LeseSpielfeldDatei("feld_Meilenstein1.xml");
		
=======
		this.spielfeld.SetzeSpielfeldMeilenstein1();
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
		//Bombmann initialisierung
		try {
			this.bombmann = new Bombmann(this.spielfeld);
		}
		catch (BombMannException e)
		{
			System.out.println(e.getMessage());
			//System.exit(0);
		}
                // bombe initialisieren
                Bombe.init(this.spielfeld,this.oberflaeche);
		
<<<<<<< HEAD
        this.spielSituation = SpielSituation.Laeuft;
=======
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
		//Bombmann initialisierung Ende.
		this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
                
	}
	
	
<<<<<<< HEAD
 
	/**
	 * Bombmann Steuerung via Tastatur, 
	 * nach move wird Spielfeld neu gezeichnet
	 */
=======
 //Hier Spielfeld neu zeichnen nach KeyEvent zett.Beh. aktualisiere Spielfeld nach Zug.
 //vorerst nur keyPressed n�tig	
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	
	@Override
	public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
		
<<<<<<< HEAD
		//Bombmann Steuerung.
=======
		//Bombmann steuerung.
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            this.bombmann.moveRight();
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
	    	this.bombmann.moveLeft();
	    } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
	    	this.bombmann.moveUp();
	    } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
	    	this.bombmann.moveDown();
	    } else if (e.getKeyCode() == KeyEvent.VK_SPACE )
            {
                Bombe foo = new Bombe(this.bombmann.getX(),this.bombmann.getY(),3,3.0,0);
            }
	    this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
<<<<<<< HEAD
	    
	    this.PruefeSpielzug();
=======
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	  //Bombmann steuerung Ende.
	}

	@Override
	public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	}



<<<<<<< HEAD
   //pruefen, ob der Spielzug erlaubt
	private void PruefeSpielzug()
=======
// und pr�fen, ob dann der Spielzug auch klar geht
	private Boolean PruefeSpielzug()
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	{
		if (this.bombmann.IsInAusgang())
		{
			this.spielSituation = SpielSituation.GewonnenSpieler1;
			
			this.GewonnenSituationReaktion();
		}
	}

private void GewonnenSituationReaktion()
{
	System.out.println("Gewinnsituation eingetreten!");
}
}