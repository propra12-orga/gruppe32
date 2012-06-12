
/**
<<<<<<< HEAD
 * Hauptlogik der Steuerung des Bombmanns wird hier implementiert.
=======
 * Hauptlogik von Steurung des Bombmanns wird hier implementiert.
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
 * @author Jingwen
 *
 */
public class Bombmann extends Gegenstand {


	
	public Spielfeld s; // Referenz auf das Spielfeld
	
	/**
<<<<<<< HEAD
	 * @param sf
	 * @throws BombMannException
	 */

=======
	 * Bombmann konstruktuieren mit moeglichst links oben initialen Platz
	 * @param sf Referenz auf spielfeld 
	 * @throws BombMannException @see {@link Bombmann#initialize(int, int)}
	 */
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	public Bombmann(Spielfeld sf) 
		throws BombMannException
	{
		this.s = sf;
		initialize(0, 0);
	}
	
	/**
<<<<<<< HEAD
	 * setze Bombmann auf vorgegebenen Platz auf dem Spielfeld.
	 * Falls der vorgegebener Platz von anderem Objekt besetzt wird, 
	 * wird der nächstmögliche freie Platz rechts unten gesucht.
=======
	 * Bombmann konstruktuieren mit vorgegebenen Platz auf dem Spielfeld.
	 * Falls der angegebene Platz von anderem Objekt besetzt wird, 
	 * wird es weiter nach recht-unten freien Platz suchen.
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	 * 
	 * @param sf Referenz auf spielfeld 
	 * @param x Angegebene Zeile
	 * @param y Angegebene Spalte
	 * @throws BombMannException @see {@link Bombmann#initialize(int, int)}
	 */
	public Bombmann(Spielfeld sf, int x, int y) 
		throws BombMannException
	{
		super();
		this.s = sf;
		initialize(x, y);
	}
	
	/**
<<<<<<< HEAD
	 * Bombmann initializieren höchstmöglich linkem Platz.
	 * Wirft exception, falls kein freier Platzvorhanden.
=======
	 * Bombmann initializieren von moeglichst obenlinks, wo noch frei ist.
	 * Wirft eine Ausnahme, falls keinen freien Platz gibt auf dem Bild.
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	 * 
	 * @param x Koordinator fuer Zeilen
	 * @param y Koordinator fuer Spalten
	 * @throws BombMannException @see {@link BombMannException}
	 */
	private void initialize(int x, int y) 
		throws BombMannException
	{
		
		int s = x + y;
		while (this.s.feldArray[x][y] != Konstanten.FELD_FREI 
				&& x <= Konstanten.FELD_ANZAHL_ZEILEN 
				&& y <= Konstanten.FELD_ANZAHL_SPALTEN 
				&& x + y <= s) {
			s ++;
			x = s;
			y = 0;
			for (; this.s.feldArray[x][y] != Konstanten.FELD_FREI && x > 0; x --) {
				y ++;
			}
		}
		s = 0;
		if (this.s.feldArray[x][y] == Konstanten.FELD_FREI) {
			this.setX(x);
			this.setY(y);
			this.s.feldArray[x][y] = Konstanten.FELD_BOMBERMAN;
		}
		else throw new BombMannException("No place for bombMann on this field: x =" 
						+ x + " y =" + y);
		
	}

	public void moveLeft()
	{
		int x = this.getX();
		int y = this.getY();
<<<<<<< HEAD
		if (y > 1)
		{
	        if (this.s.begehbar(x, y-1)==true)
	        {
	        	this.s.feldArray[x][y] = Konstanten.FELD_FREI;
	        	
	        	if (this.s.IsAusgang(x, y-1))
	        	{
					this.s.feldArray[x][y - 1] = Konstanten.FELD_BOMBERMAN_AUSGANG;
	        	}
	        	else
	        	{
	        		this.s.feldArray[x][y - 1] = Konstanten.FELD_BOMBERMAN;
	        	}
	        	this.setY(y - 1);
	        }
		}
	}
	
=======
		if (y > 1) {
                        if (this.s.begehbar(x, y-1)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x][y - 1] = Konstanten.FELD_BOMBERMAN;
				this.setY(y - 1);
			}
		}
	}
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	public void moveRight()
	{
		int x = this.getX();
		int y = this.getY();
		if (y < Konstanten.FELD_ANZAHL_SPALTEN - 1) {
<<<<<<< HEAD
			if (this.s.begehbar(x, y+1)==true)
	        {
	        	this.s.feldArray[x][y] = Konstanten.FELD_FREI;
	        	
	        	if (this.s.IsAusgang(x, y+1))
	        	{
					this.s.feldArray[x][y + 1] = Konstanten.FELD_BOMBERMAN_AUSGANG;
	        	}
	        	else
	        	{
	        		this.s.feldArray[x][y + 1] = Konstanten.FELD_BOMBERMAN;
	        	}
	        	this.setY(y + 1);
	        }
		}
	}
	
=======
			if (this.s.begehbar(x, y+1)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x][y + 1] = Konstanten.FELD_BOMBERMAN;
				this.setY(y + 1);
			}
		}
				
	}
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	public void moveUp()
	{
		int x = this.getX();
		int y = this.getY();
		if (x > 1) {
<<<<<<< HEAD
			if (this.s.begehbar(x-1, y)==true)
	        {
	        	this.s.feldArray[x][y] = Konstanten.FELD_FREI;
	        	
	        	if (this.s.IsAusgang(x-1, y))
	        	{
					this.s.feldArray[x-1][y] = Konstanten.FELD_BOMBERMAN_AUSGANG;
	        	}
	        	else
	        	{
	        		this.s.feldArray[x-1][y] = Konstanten.FELD_BOMBERMAN;
	        	}
	        	this.setX(x-1);
	        }
		}
	}
	

=======
			if (this.s.begehbar(x-1, y)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x - 1][y] = Konstanten.FELD_BOMBERMAN;
				this.setX(x - 1);
			}
		}
	}
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	public void moveDown()
	{
		int x = this.getX();
		int y = this.getY();
		if (x < Konstanten.FELD_ANZAHL_ZEILEN - 1) {
<<<<<<< HEAD
			if (this.s.begehbar(x+1, y)==true)
	        {
	        	this.s.feldArray[x][y] = Konstanten.FELD_FREI;
	        	
	        	if (this.s.IsAusgang(x+1, y))
	        	{
					this.s.feldArray[x+1][y] = Konstanten.FELD_BOMBERMAN_AUSGANG;
	        	}
	        	else
	        	{
	        		this.s.feldArray[x+1][y] = Konstanten.FELD_BOMBERMAN;
	        	}
	        	this.setX(x+1);
	        }
		}
	}
	/**
	 * @return
	 */

	public Boolean IsInAusgang()
	{	//TODO Abfrage "neues Spiel? ja/nein"
		return (this.s.feldArray[this.getX()][this.getY()] == Konstanten.FELD_BOMBERMAN_AUSGANG);
	}

	/**
	 * @return
	 */
	public Boolean BManntot()
	{	//TODO Abfrage "neues Spiel? ja/nein" && implementieren, wann BManntot = true
		return (true);
	}
	
}
	

=======
			if (this.s.begehbar(x+1, y)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x + 1][y] = Konstanten.FELD_BOMBERMAN;
				this.setX(x + 1);
			}
		}
	}


}
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
