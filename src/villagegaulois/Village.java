package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private Marche marche;
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
				if(etals[i].isEtalOccupe() && etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			int nb_vide = 0;
			StringBuilder chaine = new StringBuilder();
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nb_vide ++;
				}
			}
			if(nb_vide != 0) {
				chaine.append("Il reste " + nb_vide + " etals non utilises dans le marche.");
			}
			return chaine.toString();
		}
	}
	

	public Village(String nom, int nbVillageoisMaximum, int nb_etals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nb_etals);
		for(int i = 0; i < nb_etals;  i++) {
			this.marche.etals[i] = new Etal();
		}
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
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche à vendre " + nbProduit + " " + produit + ".\n");
		int indice_etal = marche.trouverEtalLibre();
		marche.utiliserEtal(indice_etal, vendeur, produit, nbProduit);
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " a l'etal num " + (indice_etal + 1) + ".\n");
		return chaine.toString();
	}
	
	 public String rechercherVendeursProduit(String produit) {
		 StringBuilder chaine = new StringBuilder();
		 String nom = "\n";
		 int prod = 0;
		 if(marche.etals != null) {
			 for(int i = 0; i < marche.etals.length; i++) {
				 if(marche.etals[i].isEtalOccupe() && marche.etals[i].contientProduit(produit)) {
					 prod += 1;
					 nom += "- " + marche.etals[i].getVendeur().getNom() + "\n";
				 }
			 }
			 if(prod == 0) {
				 chaine.append("Il n'y a pas de vendeur qui propose des fleurs au marché.\n");
			 }
			 else if(prod == 1) {
				 chaine.append("Seul le vendeur " + nom + " propose des fleurs au marché.\n");
			 }
			 else {
				 chaine.append("Les vendeurs qui proposent des fleurs sont :" + nom + "\n");
			 }
		 }
		 return chaine.toString();
	 }
	 
	 public Etal rechercherEtal(Gaulois vendeur) {
		 Etal e = marche.trouverVendeur(vendeur);
		 return e;
	 }
	 
	 public String partirVendeur(Gaulois vendeur) {
		 StringBuilder chaine = new StringBuilder();
		 Etal e = marche.trouverVendeur(vendeur);
		 chaine.append(e.libererEtal());
		 return chaine.toString();
	 }
	 
	 public String afficherMarche() {
		 StringBuilder chaine = new StringBuilder();
		 chaine.append("Le marché du village " + this.getNom() + " possede plusieurs etals : \n");
		 chaine.append(marche.afficherMarche());
		 return chaine.toString();
	 }
}