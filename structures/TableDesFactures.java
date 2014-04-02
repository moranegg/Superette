package structures;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import metiers.*;

import interfaces.InterfaceStructure;

public class TableDesFactures implements InterfaceStructure<String , Facture>, Serializable{
	private Hashtable<String,Facture> tabFacture = new Hashtable<String,Facture>();
	/**
	 * constructeur par défaut
	 */
	public TableDesFactures(){
		
	}
	/**
	 * constructeur avec param
	 * @param tabFacture
	 */
	public TableDesFactures(Hashtable<String,Facture> tabFacture){
		this.tabFacture=tabFacture;
		
	}
	/**
	 * retourne la facture avec la cle en param
	 */
	@Override
	public Facture retourner(String cle) {
		return tabFacture.get(cle);
	}
	/**
	 * supprime la facture avec la cle en param (il faut faire les tests en dehors de cette méthode)
	 */
	@Override
	public void supprimer(String cle) {
		tabFacture.remove(cle);
		
	}
	/**
	 * ajoute la facture en param à la table des factures
	 */
	@Override
	public void ajouter(Facture actuelle) {
		tabFacture.put(actuelle.getNumero(), actuelle);
		
	}
	/**
	 * renvoie la taille de la table des factures
	 */
	@Override
	public int taille() {
		return tabFacture.size();
	}
	/**
	 * getteur de la table des factures
	 * @return
	 */
	public Hashtable<String,Facture> getLesFactures() {
		return tabFacture;
	}

	/**
	 * retourne toutes les clés de la structure
	 * @return String des cles
	 */
	public String cle()
	{
		String total ="";
		Enumeration<String> em = tabFacture.keys();
		while(em.hasMoreElements())
		{
			String idCommande = em.nextElement();
			total += "*** "+ idCommande +"  ";
		}
		return total;
	}
	

}
