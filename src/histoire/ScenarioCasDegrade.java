package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Gaulois jean = new Gaulois("jean",-4);
		Etal letal = new Etal();
		letal.occuperEtal(jean, "caca", -2);
		letal.acheterProduit(-2, jean);
		System.out.println("Fin du test");
	}
}
