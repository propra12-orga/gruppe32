

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;

public class Spielfeld
{
	public String[][] feldArray;
	
	//erstmal alles mit freienFeldern vollpacken
	public void Initialisieren()
	{
		feldArray = new String[Konstanten.FELD_ANZAHL_ZEILEN][Konstanten.FELD_ANZAHL_SPALTEN];
		
		for (int i = 0; i < Konstanten.FELD_ANZAHL_ZEILEN; i++)
		{
			for (int j = 0; j < Konstanten.FELD_ANZAHL_SPALTEN; j++)
			{
				feldArray [i][j] = Konstanten.FELD_FREI;
			}
		}
		//und natürlich dem Bomberman einen Startplatz geben 
<<<<<<< HEAD
		//this.feldArray[1][1] = Konstanten.FELD_BOMBERMAN;
		// wir machen es jetzt separat in Bombmann
=======
		this.feldArray[1][1] = Konstanten.FELD_BOMBERMAN;
>>>>>>> bec834a38603c1de4dd6926ecc6b6b3837be76d1
	}
	// setze unkaputtbar-Mäuerchen 
	public void SetzeSpielfeldMeilenstein1()
	{
		this.Initialisieren();
	
		for (int i = 0; i < 1; i++)
			for (int j = 0; j < Konstanten.FELD_ANZAHL_SPALTEN; j++)
			{
			this.feldArray[i][j] = Konstanten.FELD_FESTE_MAUER;
			this.feldArray[j][i] = Konstanten.FELD_FESTE_MAUER;
			};
		
		for (int i = 0; i < Konstanten.FELD_ANZAHL_ZEILEN-2; i++)
			for (int j = 19; j < 20; j++)
			{
				this.feldArray[i][j] = Konstanten.FELD_FESTE_MAUER;
				
			}
		for (int i = 0; i < Konstanten.FELD_ANZAHL_ZEILEN; i++)
			for (int j = 19; j < 20; j++)
			{
				this.feldArray[j][i] = Konstanten.FELD_FESTE_MAUER;
				
			}
		
		for (int m = 2; m < Konstanten.FELD_ANZAHL_ZEILEN; m+=3)
			for (int n = 2; n < Konstanten.FELD_ANZAHL_SPALTEN; n+=3)
			{
				this.feldArray[m][n] = Konstanten.FELD_FESTE_MAUER;
			}
<<<<<<< HEAD

		//test codes, please delete before release ----------
		//this.feldArray[1][1] = Konstanten.FELD_FESTE_MAUER;
		//this.feldArray[2][1] = Konstanten.FELD_FESTE_MAUER;
		//this.feldArray[0][2] =  Konstanten.FELD_FREI;
		//System.out.println("Mauer initialized.");
		//test codes end----------

	}
	

=======
		
	}
	

	// HIER BITTE DAS BOMBERMÄNNEKEN LAUFEN LASSEN!! 
	

	
>>>>>>> bec834a38603c1de4dd6926ecc6b6b3837be76d1
}
