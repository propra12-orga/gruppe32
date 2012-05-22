import java.util.*; // hier waer evtl gut, dass nochma einzugrenzen

public class Bomb
{
    private final static TimePerTick = 1000; // in millisekunden
    
    private static Bomb[][] bombTable;
    private int locX;
    private int locY;
    private double delay;
    private int ticks;
    private int owner;
    private Timer fuse;
    
    static
    {
        bombTable = new Bomb[maxX-minX][maxY-minY];
    }
    
    private boolean explode_child(int x, int y, int Owner)
    {
        switch (field[x][y]) // <===== hier brauche ich informationen ueber das spielfeld
        {
            case BOMB: 
                bombTable[x][y].explode(Owner);
                return true;
            case DESTRUCTABLE:
                // wenn zerstoerbar, die entsprechende wand kaputt machen
                // danach, nicht mehr weiter in diese richtung "explodieren"
                return false;
            case POWERUP:
                // powerup zerstören
                // danach, nicht mehr weiter in diese richtung "explodieren"
                return false;
            case PLAYER_1: case PLAYER_2:
                // dem getroffenen spieler einen lebenspunkt abziehen, dem besitzer
                // der ersten bombe einen punkt gutschreiben
                return true;
            default:
                return false;
        }
    }
    
    private void explode(int Owner)
    {
        int x = locX;
        int y = locY;
        
        Bomb[x][y] = null;

        // druckwelle in positive y richtung
        for (x = locX, y = locY; y < min(locY+strength,maxY); y++)
        {
            if (!explode_child(x,y,Owner))
                break;
        }

        // druckwelle in negative y richtung
        for (x = locX, y = locY; y>max(locY-strength,minY); y--)
        {
            if (!explode_child(x,y,Owner))
                break;
        }

        // druckwelle in positive x richtung
        for (x = locX, y = locY; x<min(locX+strength,maxX); x++)
        {
            if (!explode_child(x,y,Owner))
                break;
        }

        // druckwelle in negative        x richtung
        for (x = locX, y = locY; x>max(locX-strength,minX); x--)
        {
            if (!explode_child(x,y,Owner))
                break;
        }
    }
    
    public void run()
    {
        ticks--;
        /*
        Hier den Kram fuer die Animation rein?
        */
        if (ticks<=0)
        {
            explode(owner);
        }
    }
    
    private Bombe(int locX, int locY, int strength, double delay, int owner)
    {
        Bomb[locX][locY] = this;
        this.locX = locX;
        this.locY = locY;
        this.strength = strength;
        this.delay = delay;
        this.ticks = (int)(delay/TimePerTick);
        this.owner = owner;
        this.fuse = new Timer();
        this.fuse.schedule(this,1000,1000);
    }
};