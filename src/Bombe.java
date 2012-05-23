import java.util.*; // hier waer evtl gut, dass nochma einzugrenzen

public class Bomb extends Gegenstand
{
/*
eine bombe legen:
new Bombe(int locX, int locY, int strength, double delay, int owner)
* <locX, locY> gibt die Position auf dem Spielfeld an
* <strength> gibt die Reichweite der Explosion an (Radius)
* <delay> gibt die Dauer an, bis die Bombe explodiert (derzeit in Sekunden,
noch nicht final)
* <owner> gibt an, welchem Spieler die Bombe gehoert (derzeit vom typ int,
noch nicht final)

eine bombe zuenden:
.explode(int Owner)
* <Owner> gibt hier an, wem die Punkte für die Explosion angerechnet werden
sollen

eine bombe aus dem spiel entfernen (ohne sie vorher zu zuenden):
.destroy()
*/
private final static TimePerTick = 1000; // Animationsgeschwindigkeit in
Millisekunden?
private final static ConversionFactor = 1000; // um von Sekunden nach
Millisekunden umzurechnen

private static Bomb[][] bombTable;

private double delay;
private int ticks;
private int owner;
private int strength;
private Timer fuse;

static
{
bombTable = new
Bomb[Konstanten.FELD_ANZAHL_ZEILEN-0][Konstanten.FELD_ANZAHL_SPALTEN-0];
}

public void destroy()
{
// wenn die bombe hochgegangen ist, brennt die lunte net mehr
if (fuse!=null)
{
fuse.cancel()
fuse = null;
}
// bombe aus allen speicherkonstrukten entfernen
bombTable[this.getX()][this.getY()] = null;
Spielfeld.feldArray[this.getX()][this.getY()] = Konstante.FELD_FREI;
// evtl noch pro spieler gespeichert?
}

private boolean explode_child(int locX, int locY, int Owner)
{
switch (Spielfeld.feldArray[locX][locY])
{
case Konstante.FELD_BOMBE:
bombTable[locX][locY].explode(Owner);
return true;
/* case FELD_MAUER:
// wenn zerstoerbar, die entsprechende wand kaputt machen
// danach, nicht mehr weiter in diese richtung "explodieren"
return false;
case POWERUP:
// powerup zerstören
// danach, nicht mehr weiter in diese richtung "explodieren"
return false;
*/
case Konstante.FELD_BOMBERMAN: // case PLAYER_2:
// dem getroffenen spieler einen lebenspunkt abziehen, dem
besitzer
// der ersten bombe einen punkt gutschreiben
return true;
default:
return false;
}
}

public void explode(int Owner)
{
int locX = this.getX();
int locY = this.getY();

this.destroy();

// druckwelle in positive y richtung
for (locX = this.getX(), locY = this.getY(); locY <
min(this.getY()+this.strength,Konstanten.FELD_ANZAHL_SPALTEN); locY++)
{
if (!explode_child(locX,locY,Owner))
break;
}

// druckwelle in negative y richtung
for (locX = this.getX(), locY = this.getY();
locY>max(this.getY()-this.strength,0); locY--)
{
if (!explode_child(locX,locY,Owner))
break;
}

// druckwelle in positive x richtung
for (locX = this.getX(), locY = this.getY();
locX<min(this.getX()+this.strength,Konstanten.FELD_ANZAHL_ZEILEN); locX++)
{
if (!explode_child(locX,locY,Owner))
break;
}

// druckwelle in negative x richtung
for (locX = this.getX(), locY = this.getY();
locX>max(this.getX()-this.strength,0); locX--)
{
if (!explode_child(locX,locY,Owner))
break;
}
}

private void run()
{
ticks--;
/*
Hier den Kram fuer die Animation rein?
*/
if (ticks<=0)
{
this.explode(owner);
}
}

public Bombe(int locX, int locY, int strength, double delay, int owner)
{
bombTable[locX][locY] = this;
Spielfeld.feldArray[locX][locY] = Konstanten.FELD_BOMBE;
// evtl noch pro spieler speichern?

this.setX(locX);
this.setY(locY);
this.strength = strength;
this.delay = delay;
this.ticks = (int)(delay*ConversionFactor/TimePerTick);
this.owner = owner;
this.fuse = new Timer();
this.fuse.schedule(this,1000,1000);
}
};
