import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;

public class Spielfeld
{
	public String[][] feldArray;
	
	/**
	 * Hier wird das Spielfeld initialisiert
	 */
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
<<<<<<< HEAD
		
	}
        
	/**
	 * Aufbau des ersten Levels wird aus XML Datei eingelesen via DOM
	 * @param dateiPfad
	 */
	public void LeseSpielfeldDatei(String dateiPfad)
	{
		this.Initialisieren();
		
		try
		{
		
			File fXmlFile = new File(dateiPfad);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	 
			NodeList nList = doc.getElementsByTagName("zelle");
				 
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
	 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE)
			   {
	 
			      Element eElement = (Element) nNode;
	 
			      String xWert = this.getTagValue("koordinate_x", eElement);
			      String yWert = this.getTagValue("koordinate_y", eElement);
		          String inhaltWert = this.getTagValue("inhalt", eElement);
		          
		          this.SetzeInhaltInArray(xWert, yWert, inhaltWert);
			   }
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void SetzeInhaltInArray(String x, String y, String inhalt)
	{
		this.feldArray[Integer.parseInt(x)][Integer.parseInt(y)] = this.GetInhaltKonstanteFromString(inhalt);
	}
	
   private String GetInhaltKonstanteFromString(String inhalt) {
		if (inhalt.equalsIgnoreCase(Konstanten.FELD_FESTE_MAUER))
		{
			return Konstanten.FELD_FESTE_MAUER;
		}
		if (inhalt.equalsIgnoreCase(Konstanten.FELD_BOMBERMAN))
		{
			return Konstanten.FELD_BOMBERMAN;
		}
		if (inhalt.equalsIgnoreCase(Konstanten.FELD_FREI))
		{
			return Konstanten.FELD_FREI;
		}
		if (inhalt.equalsIgnoreCase(Konstanten.FELD_AUSGANG))
		{
			return Konstanten.FELD_AUSGANG;
		}
		//TODO: Bei weiteren Konstanten hier aufbohren
		
		return Konstanten.FELD_FREI;
	}


   private String getTagValue(String sTag, Element eElement)
   {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	    Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
   }
		   
    public boolean begehbar(int x, int y)
    {
        return (
        		((feldArray[x][y]==Konstanten.FELD_FREI) || (feldArray[x][y]==Konstanten.FELD_AUSGANG))
        		&&
        		(Bombe.bombTable[x][y]==null));
    }
    
    public boolean IsAusgang(int x, int y)
    {
        return (feldArray[x][y]==Konstanten.FELD_AUSGANG);
    }
        
	
=======
		//und nat�rlich dem Bomberman einen Startplatz geben 
		//this.feldArray[1][1] = Konstanten.FELD_BOMBERMAN;
		// wir machen es jetzt separat in Bombmann
	}
        
        public boolean begehbar(int x, int y)
        {
            return ((feldArray[x][y]==Konstanten.FELD_FREI)&&(Bombe.bombTable[x][y]==null));
        }
        
	// setze unkaputtbar-Maeuerchen 
>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
	public void SetzeSpielfeldMeilenstein1()
	{
		this.Initialisieren();
	
		/*	
		 * SCHREIBKRAM FUER LEVEL 1 ERSPART
		 * for (int i = 0; i < 1; i++)
			for (int j = 0; j < Konstanten.FELD_ANZAHL_SPALTEN; j++)
			{
			this.feldArray[i][j] = Konstanten.FELD_FESTE_MAUER;
			this.feldArray[j][i] = Konstanten.FELD_FESTE_MAUER;
			
			System.out.print("<zelle><koordinate_x>");
			System.out.print(i);
			System.out.println("</koordinate_x>");
			System.out.print("<koordinate_y>");
			System.out.print(j);
			System.out.println("</koordinate_y>");
			System.out.println("<inhalt>FELD_FESTE_MAUER</inhalt></zelle>");
			
			System.out.print("<zelle><koordinate_x>");
			System.out.print(j);
			System.out.println("</koordinate_x>");
			System.out.print("<koordinate_y>");
			System.out.print(i);
			System.out.println("</koordinate_y>");
			System.out.println("<inhalt>FELD_FESTE_MAUER</inhalt></zelle>");
			};
		
		for (int i = 0; i < Konstanten.FELD_ANZAHL_ZEILEN-2; i++)
			for (int j = 19; j < 20; j++)
			{
				this.feldArray[i][j] = Konstanten.FELD_FESTE_MAUER;
				
				System.out.print("<zelle><koordinate_x>");
				System.out.print(i);
				System.out.println("</koordinate_x>");
				System.out.print("<koordinate_y>");
				System.out.print(j);
				System.out.println("</koordinate_y>");
				System.out.println("<inhalt>FELD_FESTE_MAUER</inhalt></zelle>");
			}
		for (int i = 0; i < Konstanten.FELD_ANZAHL_ZEILEN; i++)
			for (int j = 19; j < 20; j++)
			{
				this.feldArray[j][i] = Konstanten.FELD_FESTE_MAUER;
				
				System.out.print("<zelle><koordinate_x>");
				System.out.print(j);
				System.out.println("</koordinate_x>");
				System.out.print("<koordinate_y>");
				System.out.print(i);
				System.out.println("</koordinate_y>");
				System.out.println("<inhalt>FELD_FESTE_MAUER</inhalt></zelle>");
			}
		
		for (int m = 2; m < Konstanten.FELD_ANZAHL_ZEILEN; m+=3)
			for (int n = 2; n < Konstanten.FELD_ANZAHL_SPALTEN; n+=3)
			{
				this.feldArray[m][n] = Konstanten.FELD_FESTE_MAUER;
<<<<<<< HEAD
				
				System.out.print("<zelle><koordinate_x>");
				System.out.print(m);
				System.out.println("</koordinate_x>");
				System.out.print("<koordinate_y>");
				System.out.print(n);
				System.out.println("</koordinate_y>");
				System.out.println("<inhalt>FELD_FESTE_MAUER</inhalt></zelle>");
				
			}*/

		//test codes, please delete before release ----------
		//this.feldArray[1][1] = Konstanten.FELD_FESTE_MAUER;
		//this.feldArray[2][1] = Konstanten.FELD_FESTE_MAUER;
		//this.feldArray[0][2] =  Konstanten.FELD_FREI;
		//System.out.println("Mauer initialized.");
		//test codes end----------

	}


=======
			}

		//test codes, please delete before release ----------
		//this.feldArray[1][1] = Konstanten.FELD_FESTE_MAUER;
		//this.feldArray[2][1] = Konstanten.FELD_FESTE_MAUER;
		//this.feldArray[0][2] =  Konstanten.FELD_FREI;
		//System.out.println("Mauer initialized.");
		//test codes end----------

	}
	

>>>>>>> 7604a93fa1f456f9bb4f4074357bd3658e3df8f0
}
