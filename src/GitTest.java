/**
 * @author Pascal Attwenger (1200595)
 * Testing if git and eclipse work together how they should
 */
public class GitTest {
	private static Woamness allTheWoamKids = new Woamness();
	
	public static void main(String[] args) {
		if (allTheWoamKids.isWoam("Gerald")) {
			System.out.println("Gerald ist woam");
		}
		
		if (allTheWoamKids.isWoam("Pascal")) {
			System.out.println("Pascal ist woam");
		}
		
		if (allTheWoamKids.isWoam("Philipp")) {
			System.out.println("Philipp ist woam");
		}
		
		if (allTheWoamKids.isWoam("Vincent")) {
			System.out.println("Vincent ist woam");
		}
	}
}
