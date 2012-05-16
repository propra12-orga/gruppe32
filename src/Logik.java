
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
		
		this.oberflaeche.ZeichneSpielfeld(this.spielfeld);
	}
	
	
 //Hier Spielfeld neu zeichnen nach KeyEvent zett.Beh. aktualisiere Spielfeld nach Zug.
 //vorerst nur keyPressed nötig	
	
	@Override
	public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
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