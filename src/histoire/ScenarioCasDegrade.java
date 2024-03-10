package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Gaulois jean = new Gaulois("jean",-4);
		Etal letal = new Etal();
		System.out.println(letal.acheterProduit(-2, jean));
		System.out.println("Fin du test");		
	}
}
