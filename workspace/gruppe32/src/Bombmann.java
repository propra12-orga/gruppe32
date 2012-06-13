/**
 * Hauptlogik von Steurung des Bombmanns wird hier implementiert.
 * @author Jingwen
 *
 */
public class Bombmann extends Gegenstand {
	
	/**
	 * option for initial positions, for 2 players, we have 2 initial places, 
	 * one is left up in the "feld" and another is right up. 
	 */
	public static final String INIT_LEFT_UP = "leftup";
	public static final String INIT_RIGHT_UP = "rightup";
	//public static final String INIT_LEFT_DOWN = "leftdown";
	//public static final String INIT_RIGHT_DOWN = "rightdown";
	
	private String ID;
	private int blood;//live blood of the bombmann
	private Spielfeld s; // Referenz auf das Spielfeld



	/**
	 * setze Bombermann moeglichst links oben
	 * @param sf
	 * @throws BombMannException
	 */
	
	public Bombmann(Spielfeld sf) 
		throws BombMannException
	{
		this(sf, "1", INIT_LEFT_UP, 0, 0);
	}
	

	public Bombmann(Spielfeld sf, String id, int x, int y) 
		throws BombMannException
	{
		this(sf, id, INIT_LEFT_UP, x, y);
	}

	/**
	 * setze Bombmann auf vorgegebenen Platz auf dem Spielfeld.
	 * Falls der vorgegebener Platz von anderem Objekt besetzt wird, 
	 * wird der nächstmögliche freie Platz rechts unten gesucht.
	 * @param sf Referenz auf spielfeld
	 * @param id Name fuer den Bombmann 
	 * @param initPos option name for the initial position: leftup or rightup 
	 * @param x Angegebene Zeile
	 * @param y Angegebene Spalte
	 * @throws BombMannException @see {@link Bombmann#initialize(int, int)}
	 */
	public Bombmann(Spielfeld sf, String id, String initPos, int x, int y) 
			throws BombMannException
	{
		this.ID = id;
		this.s = sf;
		this.blood = Konstanten.MAX_BOMBMANN_BLOOD;
		initPosWithOption(initPos, x, y, ID);
	}



	private void initPosWithOption(String initPos, int x, int y, String ID) 
		throws BombMannException
	{
		if(initPos.equalsIgnoreCase(INIT_LEFT_UP)) {
			initPosLeftUp(x, y, ID);
		}
		else if (initPos.equalsIgnoreCase(INIT_RIGHT_UP)){
			initPosRightUp(x, y, ID);			
		}
		else {
			throw new BombMannException("invalid initial position option: " + initPos
					 + "\n Please choose: " + INIT_LEFT_UP + " or " + INIT_RIGHT_UP);
		}
	}

	/**
	 * Bombmann initializieren höchstmöglich linkem Platz.
	 * Wirft exception, falls kein freier Platzvorhanden.
	 * 
	 * @param x Koordinate fuer Zeilen
	 * @param y Koordinate fuer Spalten
	 * @throws BombMannException @see {@link BombMannException}
	 */
	private void initPosLeftUp(int x, int y, String ID) 
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
			this.s.feldArray[x][y] = BombMannController.getBombmannPic(ID);
			//System.out.println("this.s.feldArray[x][y]" + this.s.feldArray[x][y]);
		}
		else throw new BombMannException("No place for bombMann on this field: x =" 
						+ x + " y =" + y);
		//System.out.println("InitPosLeftUP");
	}
	/**
	 * zweiter Spieler: Bombmann initializieren höchstmöglich rechtemm Platz.
	 * Wirft exception, falls kein freier Platzvorhanden.
	 * 
	 * @param x Koordinate fuer Zeilen
	 * @param y Koordinate fuer Spalten
	 * @throws BombMannException @see {@link BombMannException}
	 */
	private void initPosRightUp(int x, int y, String ID) 
		throws BombMannException
	{
		int pseudoy =  Konstanten.FELD_ANZAHL_SPALTEN - 1  - y;
		
		int s = x + pseudoy;
		while (this.s.feldArray[x][y] != Konstanten.FELD_FREI 
				&& x <= Konstanten.FELD_ANZAHL_ZEILEN 
				&& pseudoy <= Konstanten.FELD_ANZAHL_SPALTEN 
				&& x + pseudoy <= s) {
			//System.out.println("in x = " + x +" pseudoy =" + pseudoy + " y =" + y + " s = " + s);
			s ++;
			x = s;
			pseudoy = 0;
			for (; this.s.feldArray[x][y] != Konstanten.FELD_FREI && x > 0; x --) {
				pseudoy ++;
				y --;
				//System.out.println("in x = " + x +" pseudoy =" + pseudoy + " y =" + y + " s = " + s);
			}
		}
		s = 0;
		if (this.s.feldArray[x][y] == Konstanten.FELD_FREI) {
			this.setX(x);
			this.setY(y);
			this.s.feldArray[x][y] = BombMannController.getBombmannPic(ID);
			//System.out.println("this.s.feldArray[x][y]" + this.s.feldArray[x][y]);
		}
		else throw new BombMannException("No place for bombMann on this field: x =" 
						+ x + " pseudoy =" + pseudoy);
		//System.out.println("initPosRightUp");
	}


	public void moveLeft()
	{
		if (this.isDead()) {
			return;
		}
		
		int x = this.getX();
		int y = this.getY();
		if (y > 1) {
                        if (this.s.begehbar(x, y-1)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x][y - 1] =BombMannController.getBombmannPic(ID);
				this.setY(y - 1);
			}
		}
	}
	public void moveRight()
	{
		if (this.isDead()) {
			return;
		}
		
		int x = this.getX();
		int y = this.getY();
		
		if (y < Konstanten.FELD_ANZAHL_SPALTEN - 1) {
			if (this.s.begehbar(x, y+1)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x][y + 1] =  BombMannController.getBombmannPic(ID);
				this.setY(y + 1);
			}
		}
	}
	public void moveUp()
	{
		if (this.isDead()) {
			return;
		}
		
		int x = this.getX();
		int y = this.getY();
		if (x > 1) {
			if (this.s.begehbar(x-1, y)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x - 1][y] = BombMannController.getBombmannPic(ID);
				this.setX(x - 1);
			}
		}
	}
	public void moveDown()
	{
		if (this.isDead()) {
			return;
		}
	
		int x = this.getX();
		int y = this.getY();
		if (x < Konstanten.FELD_ANZAHL_ZEILEN - 1) {
			if (this.s.begehbar(x+1, y)==true) {
				this.s.feldArray[x][y] = Konstanten.FELD_FREI;
				this.s.feldArray[x + 1][y] = BombMannController.getBombmannPic(ID);
				this.setX(x + 1);
			}
		}
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	/**
	 * bombmann hurt by the bomb and loose one blood
	 */
	protected void hurt()
	{
		this.blood--;
		if (this.isDead()) {
			bury();
		}
		else {
			System.out.println("Player [" + getID() + "] is hurt and have blood:" + this.blood );
		}
	}
	
	
	public boolean isDead() 
	{
		return this.blood <= 0;
	}


	/**
	 * destroy method for the bombmann w.r.t. display
	 */
	private void bury() {
		this.s.feldArray[this.getX()][this.getY()] = Konstanten.FELD_DEADMANN;
		System.out.println("Player [" + this.getID() + "] is dead.");
	}


}
