/**
 * 
 * @author Archie
 *
 */
public class Programmstart {

	//Ab in die Logik 
	
	public static void main(String[] args) {
		try
		{
			Logik logik =  new Logik();
			
			logik.StarteLogik("");
		}
		catch (Exception exc)
		{
                    System.out.println("Exception Caught!");
		}
	}
}
