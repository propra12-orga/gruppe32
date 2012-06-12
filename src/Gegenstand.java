/**
 * Alle Gegenstaende, inklusive Bombmann, Monster, Bomb, Mauer etc...
 * Alle Gegenstaende, inklusive Bombmann, Monster, Bomb, sogar Mauer, wenn Sie wollen.
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
