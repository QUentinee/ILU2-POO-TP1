package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	public class Marche {
		private Etal[] etals;
		
		public Marche(int nb_etal) {
			this.etals = new Etal[nb_etal];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			Etal etals_prod[] = new Etal[etals.length];
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit) == true) {
					etals_prod[i] = etals[i];
				}
			}
			return etals_prod;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public void afficherMarche() {
			int nb_vide = 0;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				}
				else {
					nb_vide ++;
				}
			}
			System.out.println("Il reste" + nb_vide + "etals non utilisés dans le marché");
			System.out.println("");
		}
	}
	

	public Village(String nom, int nbVillageoisMaximum, int nb_etals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche(nb_etals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur + "cherche à vendre" + nbProduit + produit);
		int indice_etal = .trouverEtalLibre();
		chaine.append("Le vendeur" + vendeur + "vend des " + produit + "à l'étal n°" + indice_etal);
		return chaine.toString();
	}
	
	 public String rechercherVendeursProduit(String produit) {
		 
	 }
}