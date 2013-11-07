/**
 * @author Pascal Attwenger (1200595)
 * Testing if git and eclipse work together how they should
 */
public class GitTest {
	private static Coolness allTheCoolKids = new Coolness();
	
	public static void main(String[] args) {
		if (allTheCoolKids.isCool("Gerald")) {
			System.out.println("Gerald ist cool");
		}
		
		if (allTheCoolKids.isCool("Pascal")) {
			System.out.println("Pascal ist cool");
		}
		
		if (allTheCoolKids.isCool("Philipp")) {
			System.out.println("Philipp ist cool");
		}
		
		if (allTheCoolKids.isCool("Vincent")) {
			System.out.println("Vincent ist cool");
		}
	}
}
