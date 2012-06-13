/**
 * ableitende Klasse fuer alle 
 * Spielgegenstaende, wie Monster, Bomberman, zerstoerbare Mauer etc...
 * @author jingwen
 *
 */
public class Gegenstand {

	private int x; //Zeilen
	private int y; //Spalten
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	protected void setX(int x) {
		this.x = x;
	}
	protected void setY(int y) {
		this.y = y;
	}
	
	
}
