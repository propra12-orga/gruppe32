import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Controll all the bombmanns in the game. 
 * 
 *
 * @author jingwen
 *
 */
public class BombMannController {
	
	private static Map<String, Bombmann> allBombManns; // Maintain a map of bombmann name(ID) to its object.
	/**
	 * Maintain the keycode <-> action maps, action includes: move left, right etc.
	 *  
	 * We use the coding <BombmannName>_<action> to denote what does a key means.
	 * E.g. If the key VK_LEFT("left arrow") is pressed, then it means the player "1" wants to  "move left". 
	 * "put" means put bomb at the position of the bomb man.
	 */
	private static Map<Integer, String> actionMap; 
	
	/**
	 * maintain the map of bombmann name <-> picture for it
	 * By default, it is DEFAULT_PLAYER1_ID <-> Konstanten.FELD_BOMBERMAN
	 *                   DEFAULT_PLAYER2_ID <->  Konstanten.FELD_BOMBERMAN2
	 */
	private static Map<String, String> pictureMap;

	/********************************************************************
	 * defaul settings, these could be read from a file.
	 * 
	 */
	public static final String DEFAULT_PLAYER1_ID = "1";
	public static final String DEFAULT_PLAYER2_ID = "2";
	//here is a default map for the actions w.r.t. the first player, 
	public static final Map<Integer, String> FIRST_PLAYER_KEYS;
	static {
		Map<Integer, String> map = new HashMap<Integer, String>();
	    map.put(KeyEvent.VK_LEFT, DEFAULT_PLAYER1_ID + "_left");
	    map.put(KeyEvent.VK_RIGHT, DEFAULT_PLAYER1_ID + "_right");
	    map.put(KeyEvent.VK_UP, DEFAULT_PLAYER1_ID + "_up");
	    map.put(KeyEvent.VK_DOWN, DEFAULT_PLAYER1_ID + "_down");
	    map.put(KeyEvent.VK_ENTER, DEFAULT_PLAYER1_ID + "_put");
	   
	    FIRST_PLAYER_KEYS = Collections.unmodifiableMap(map);
	}
	
	//here is a default map for the actions w.r.t. the second player, 
	public static final Map<Integer, String> SECOND_PLAYER_KEYS;
	static {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(KeyEvent.VK_A, DEFAULT_PLAYER2_ID + "_left");
	    map.put(KeyEvent.VK_D, DEFAULT_PLAYER2_ID + "_right");
	    map.put(KeyEvent.VK_W, DEFAULT_PLAYER2_ID + "_up");
	    map.put(KeyEvent.VK_S, DEFAULT_PLAYER2_ID + "_down");
	    map.put(KeyEvent.VK_SPACE, DEFAULT_PLAYER2_ID + "_put");
	    SECOND_PLAYER_KEYS = Collections.unmodifiableMap(map);
	}
	/************************************** 
	 * default setting end.
	 */
	
	//Singleton contructor
	private static BombMannController bmcontroller;
	public static BombMannController initController() {
		if (bmcontroller == null) {
			bmcontroller = new BombMannController();
		}
		return bmcontroller;
	}
	private BombMannController() {
		allBombManns = new HashMap<String, Bombmann>();
		actionMap = new HashMap<Integer, String>();
		pictureMap = new HashMap<String, String>();
	}
	/**
	 * Initialize the players, now it only support 2 players.
	 * @param sp Spielfeld reference
	 * @param numberOfPlayers int
	 * @throws BombMannException from {@link generatePlayer1()} or {@link generatePlayer2()}}
	 */
	public static void initPlayers(Spielfeld sp, int numberOfPlayers)
		throws BombMannException 
	{
		cleanup();
		if (numberOfPlayers == 1) {
			generatePlayer1(sp);
		}
		else if (numberOfPlayers == 2) {
			generatePlayer1(sp);
			generatePlayer2(sp);
		}
		else {
			throw new BombMannException("Modus with more than 2 players is not yet implemented.");
		}
	}
	/**
	 * clean up all the data structures.
	 */
	protected static void cleanup() {
		allBombManns.clear();
		actionMap.clear();
		pictureMap.clear();
	}
	/**
	 * Generate the first player at the left up position.
	 * This could be rewritten depending on how you want to call the generate method.
	 * An example of rewritten is the method {@link generatePlayer2(Spielfeld sf)}
	 * @param sf
	 * @return the generated {@code Bombmann} object
	 * @throws BombMannException
	 */
	private static Bombmann generatePlayer1(Spielfeld sf)
		throws BombMannException
	{ 
		String id = DEFAULT_PLAYER1_ID;
		pictureMap.put(id, Konstanten.FELD_BOMBERMAN);
		actionMap.putAll(FIRST_PLAYER_KEYS);
		
		Bombmann b = generateNewBombMannWithControllKeys(sf, id, Bombmann.INIT_LEFT_UP, 0, 0);

		allBombManns.put(id, b);
		sf.feldArray[b.getX()][b.getY()]=BombMannController.getBombmannPic(b.getID());
		System.out.println("Player1 generated");
		return b; 
	}
	/**
	 * This is a dummy method for generating the second player.
	 * It uses the method {@link generatePlayer1()} as pattern and could be rewritten.
	 * @param sf
	 * @return the generated {@code Bombmann} object
	 * @throws BombMannException
	 */
	private  static Bombmann generatePlayer2(Spielfeld sf)
			throws BombMannException
	{ 
		String id = DEFAULT_PLAYER2_ID;
		pictureMap.put(id, Konstanten.FELD_BOMBERMAN2);
		actionMap.putAll(SECOND_PLAYER_KEYS);
		
		Bombmann b = generateNewBombMannWithControllKeys(sf, id, Bombmann.INIT_RIGHT_UP, 0, Konstanten.FELD_ANZAHL_SPALTEN-1);
		
		allBombManns.put(id, b);
		sf.feldArray[b.getX()][b.getY()]=BombMannController.getBombmannPic(b.getID());
		System.out.println("Player2 generated");
		return b;
	}
	
	/*
	 * Generate a Bombmann object with the given bombmann name as ID,
	 *                                     position option 
	 */
	/**
	 * Generate a bombmann object with the given parameters as followed.
	 * 
	 * 
	 * @param sf reference to the Spielfeld
	 * @param id, bombmann name as ID. 
	 * @param pos, position option, either as possible as left up in the playground, or as possible as right up in the playground.  
	 * @param x, starting row of bombmann, if not free, then using the pos option to get the optimized row as up as possible.
	 * @param y, starting column of bomb man, if not free, using the pos option to find either the most left or most right free column.
	 * @return the generated Bombmann.
	 * @throws BombMannException, when there is already someone with the same name exist, 
	 * 							or number of wanted bombman is more than MAX_BOMBMANN_NUMBER
	 * 							or the given position is out of range.  
	 */
	private static Bombmann generateNewBombMannWithControllKeys(Spielfeld sf, String id, String pos, int x, int y)
			throws BombMannException
	{
			if(allBombManns.containsKey(id)) {
				throw new BombMannException("Bombmann: " + id + " already exist.");
			}
			else if (getNumberOfBombmanns() > Konstanten.MAX_BOMBMANN_NUMBER){
				throw new BombMannException("We can only allow maximal 4 players.");
			}
			else if (x < 0 || x > Konstanten.FELD_ANZAHL_ZEILEN){
				throw new BombMannException("x is bigger than the allowed number of rows.");
			}
			else if (y < 0 || y > Konstanten.FELD_ANZAHL_SPALTEN) {
				throw new BombMannException("y is bigger than the allowed number of rows.");
			}
			
			
			//System.out.println("generate" + id + pos + x + y);
			return new Bombmann(sf, id, pos, x, y);

	}
	

	/**
	 * 
	 * @return the number of registered Bombmann in {@code allBombManns}
	 */
	public static  int getNumberOfBombmanns() {
		return allBombManns.size();
	}

	/**
	 * 
	 * @param name of the bombmann
	 * @return the corresponding Bombmann object w.r.t. given name,
	 *         NULL if no such name registered here.
	 */
	public static Bombmann getBombmann(String name) {
		return allBombManns.get(name);
	}

	/**
	 * Looking up the pictureMap and find the picture name mapped to the given Bombmann name.
	 * 
	 * @param ID name(ID) of the bombmann
	 * @return the corresponding picture name for the bombmann, 
	 * 			e.g. {@code  Konstanten.FELD_BOMBERMAN2}for {@code DEFAULT_PLAYER2_ID}
	 */
	public static String getBombmannPic(String ID) {
		return pictureMap.get(ID);
	}
	/**
	 * Looking up the pictureMap and find the Bombmann name mapped to the given picture name.
	 * 
	 * @param pic picture name (e.g. {@code DEFAULT_PLAYER2_ID}) 
	 * @return the corresponding bombmann name 
	 */
	public static String getBombmannIdFromPic(String pic) {
		String name = DEFAULT_PLAYER1_ID;
		for(Iterator<String> itBN = allBombManns.keySet().iterator(); itBN.hasNext();) {
			name = itBN.next();
			if (getBombmannPic(name).equalsIgnoreCase(pic)) {
				return name;
			}
		}
		return name;
	}
	/**
	 * Lookup the action map and see if it contains the given Keycode as reference to particular action w.r.t. bombmann.
	 * 
	 * @param key, the integer number of keycode from the KeyEvent.
	 * @return true, if the key is used for controlling (registered in the actionMap).
	 */
	public static boolean containsActionKey(int key) {
		return actionMap.containsKey(key);
	}
	/**
	 * Execute the corresponding action w.r.t. the given keycode.
	 * 
	 * First, it looks up the action map to find out the action command
	 * Then, it analyze the action command and separate the part for bombmann ID and the rest for its action.
	 * Last, it fetch the bombmann object w.r.t. the ID and let the object execute the corresponding action.
	 * Note that the dead bombmann will not execute anything.
	 * 
	 * @param keycode from the KeyEvent.
	 */
	public static void execute(int keycode) 
	{
		//System.out.println("keycode: "+ keycode);
		String command = actionMap.get(keycode);
		Bombmann b;
		String playername = command.substring(0, command.indexOf('_'));
		b = getBombmann(playername);

		String action = command.substring(command.indexOf('_')+1, command.length());
		
		if(b.isDead()) {
			System.out.println("Dead player [" + b.getID() + "] cannot execute " + action);
			return;
		}

		
		//System.out.println("command: " + command + "; playername: " + playername + "; action: " + action + "; Bombmann: " + b.getID());
	    if (action.equalsIgnoreCase("right" ) ) { 
            b.moveRight();
	    } else if (action.equalsIgnoreCase("left") ) {
	    	b.moveLeft();
	    } else if (action.equalsIgnoreCase("up") ) {
	    	b.moveUp();
	    } else if (action.equalsIgnoreCase("down") ) {
	    	b.moveDown();
	    } else if (action.equalsIgnoreCase("put")) {
	    	new Bombe(b.getX(),b.getY(),3,3.0,0);
	    }
	}
	/**
	 * the Bombmann with ID (id) is hurt by the bomb.
	 * @param id 
	 */
	protected static void explodeAtBombmann(String id)
	{
		Bombmann b = getBombmann(id);
		b.hurt();
	}
	/**
	 * 
	 * @param x row
	 * @param y column
	 * @return the Bombmann name(ID) if at the position <x, y> there is a bombmann,
	 *         otherwise ""
	 */
	public static String whichBombMann(int x, int y) {
		String name;
		Bombmann b; 
		String result = "";
		for (Iterator<String> it = allBombManns.keySet().iterator(); it.hasNext();) {
			name = it.next();
			b = allBombManns.get(name);
			if(b.getX() == x && b.getY() == y) {
				result = b.getID();
				break;
			}
			
		}
		return result;
	}

}
