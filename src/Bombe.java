import java.util.*; // hier waer evtl gut, dass nochma einzugrenzen


/**
 * 
 * @author Jeremiah
 *
 */


public class Bombe extends TimerTask
{
/*
	eine bombe legen:
		new Bombe(int locX, int locY, int strength, double delay, int owner)
			* <locX, locY> gibt die Position auf dem Spielfeld an
			* <strength> gibt die Reichweite der Explosion an (Radius)
			* <delay> gibt die Dauer an, bis die Bombe explodiert (derzeit in Sekunden, noch nicht final)
			* <owner> gibt an, welchem Spieler die Bombe gehoert (derzeit vom typ int, noch nicht final)
	
	eine bombe zuenden:
		.explode(int Owner)
<<<<<<< HEAD
			* <Owner> gibt hier an, wem die Punkte fuer die Explosion angerechnet werden sollen
=======
			* <Owner> gibt hier an, wem die Punkte für die Explosion angerechnet werden sollen
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	
	eine bombe aus dem spiel entfernen (ohne sie vorher zu zuenden):
		.destroy()
*/
    private final static int TimePerTick = 500; // Animationsgeschwindigkeit in Millisekunden?
    private final static int ConversionFactor = 1000; // um von Sekunden nach Millisekunden umzurechnen
    
    protected static Bombe[][] bombTable;

    private double delay;
    private int ticks;
    private int owner;
    private int strength;
    private Timer fuse;
    
    public static Spielfeld feld;
    public static Oberflaeche grafik;
    
    
    // aus gegenstand vllt nen interface machen um das problem mit der 
    // vererbung zu umgehen?
    private int x;
    private int y;
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int setX(int x)
    {
        return this.x = x;
    }
    
    public int setY(int y)
    {
        return this.y = y;
    }
    
    public void destroy()
    {
<<<<<<< HEAD
        // wenn die bombe hochgegangen ist, brennt die lunte nicht mehr
=======
        // wenn die bombe hochgegangen ist, brennt die lunte net mehr
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
        if (fuse!=null)
        {
                fuse.cancel();
                fuse = null;
        }
        // bombe aus allen speicherkonstrukten entfernen
        bombTable[this.getX()][this.getY()] = null;
        // feld.feldArray[this.getX()][this.getY()] = Konstanten.FELD_FREI;
        // evtl noch pro spieler gespeichert?
    }
    
    private boolean explode_child(int locX, int locY, int Owner)
    {
        if (bombTable[locX][locY]!=null)
        {
            bombTable[locX][locY].explode(Owner);
            return true;
        }
        if (feld.feldArray[locX][locY]==Konstanten.FELD_FESTE_MAUER) // wenn mauer, dann explosion hier stoppen
        {
            return false;
        }
        if (feld.feldArray[locX][locY]==Konstanten.FELD_BOMBERMAN) // wenn spieler, dann explosion weitergehen lassen
        {
            System.out.println("Spieler getrofffen");
            return true;
        }
        return true; // nix getroffen
    }
    
    public void explode(int Owner)
    {
        int locX = this.getX();
        int locY = this.getY();
        
		this.destroy();
        
        System.out.printf("Bombe(%d,%d) ist grad explodiert!\n",locX,locY);
        // druckwelle in positive y richtung
        for (locX = this.getX(), locY = this.getY(); locY < Math.min(this.getY()+this.strength,Konstanten.FELD_ANZAHL_SPALTEN); locY++)
        {
            if (!explode_child(locX,locY,Owner))
                break;
        }

        // druckwelle in negative y richtung
        for (locX = this.getX(), locY = this.getY()-1; locY>Math.max(this.getY()-this.strength,0); locY--)
        {
            if (!explode_child(locX,locY,Owner))
                break;
        }

        // druckwelle in positive x richtung
        for (locX = this.getX()+1, locY = this.getY(); locX<Math.min(this.getX()+this.strength,Konstanten.FELD_ANZAHL_ZEILEN); locX++)
        {
            if (!explode_child(locX,locY,Owner))
                break;
        }

        // druckwelle in negative x richtung
        for (locX = this.getX()-1, locY = this.getY(); locX>Math.max(this.getX()-this.strength,0); locX--)
        {
            if (!explode_child(locX,locY,Owner))
                break;
        }
        grafik.ZeichneSpielfeld(this.feld);
    }
    
    @Override
    public void run()
    {
        ticks--;
        /*
        Hier den Kram fuer die Animation rein?
        */
        /*if (feld.feldArray[this.x][this.y] != Konstanten.FELD_BOMBE)
        {
           feld.feldArray[this.x][this.y] = Konstanten.FELD_BOMBE;
           grafik.ZeichneSpielfeld(this.feld);
        }*/
        if (ticks<=0)
        {
            this.explode(owner);
        }
    }
    
    public static void init(Spielfeld s, Oberflaeche o)
    {
        bombTable = new Bombe[Konstanten.FELD_ANZAHL_ZEILEN][Konstanten.FELD_ANZAHL_SPALTEN];
        Bombe.feld = s;
        Bombe.grafik = o;
    }
    
    public Bombe(int locX, int locY, int strength, double delay, int owner)
    {
        bombTable[locX][locY] = this;
        // feld.feldArray[locX][locY] = Konstanten.FELD_BOMBE;
        // evtl noch pro spieler speichern?
		
        this.x = locX;
        this.y = locY;
        this.strength = strength;
        this.delay = delay;
        this.ticks = (int)(delay*ConversionFactor/TimePerTick);
        this.owner = owner;
        this.fuse = new Timer();
        this.fuse.schedule(this,1,1000);
    }
}