import java.util.HashMap;


public class Coolness extends HashMap<String, Boolean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4800064819289895485L;

	
	Coolness(){
		
		this.put("Vincent", true);
		this.put("Pascal", true);
		this.put("Gerald", true);
		this.put("Philipp", true);
		
	}

	
	boolean isCool(String s){
		return this.get(s);
	}

}
