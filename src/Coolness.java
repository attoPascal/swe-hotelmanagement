import java.util.Hashtable;

/**
 * Coolness is cool
 * @author Pascal Attwenger (1200595)
 */
public class Coolness extends Hashtable<String, Boolean> {
	private static final long serialVersionUID = 1L;

	public Coolness() {
		this.put("Pascal", true);
		this.put("Vincent", false);
		this.put("Gerald", false);
		this.put("Philipp", false);
	}
	
	public boolean isCool(String name) {
		return this.get(name);
	}
}
