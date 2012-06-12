
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
  
/**
 * startet Oberflaeche, Spielfeld, Bombmann 
 * und prueft die aktuelle Spielsituation
 * @author Archie
 *
 */
public class Logik implements KeyListener
{
	private Spielfeld spielfeld;
	private Oberflaeche oberflaeche;	
	private Bombmann bombmann;
	
	private SpielSituation spielSituation;
	
	public Logik()
	{
	}
	
	public void StarteLogik()
	{
		this.oberflaeche = new Oberflaeche();
		this.oberflaeche.Initialisieren();
		this.oberflaeche.addKeyListener(this);
		
		this.spielfeld = new Spielfeld();
		this.spielfeld.LeseSpielfeldDatei("feld_Meilenstein1.xml");
		
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
		
        this.spielSituation = SpielSituation.Laeuft;
		//Bombmann initialisierung Ende.
		this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
                
	}
	
	
 
	/**
	 * Bombmann Steuerung via Tastatur, 
	 * nach move wird Spielfeld neu gezeichnet
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
		
		//Bombmann Steuerung.
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
	    
	    this.PruefeSpielzug();
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



   //pruefen, ob der Spielzug erlaubt
	private void PruefeSpielzug()
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