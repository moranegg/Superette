package metiers;

import java.io.Serializable;
import tools.*;

public class Facture implements Serializable {
	private DateUser dateFacture;
	private String numero;
	private String facture;
	/**
	 * constructeur avec param
	 * @param dateFacture
	 * @param numero
	 * @param facture
	 */
	public Facture( DateUser dateFacture, String numero, String facture)
	{
		this.dateFacture=dateFacture;
		this.numero=numero;
		this.facture=facture;
	}
	/**
	 * constructeur par défaut
	 */
	public Facture()
	{
		
	}
	/**
	 * chaîne de caractères qui designe la facture
	 */
	public String toString()
	{
		return ("\t\t FACTURE DE LA COMMANDE N°"+numero + "a été facturée le: "+ dateFacture.toString()+"\n"+facture);
	}
	/**
	 * renvoie la date à laquele cette facture a été créer
	 * @return
	 */
	public DateUser getDateFacture() {
		return dateFacture;
	}
	/**
	 * setter de la date de la facturation
	 * @param dateFacture
	 */
	public void setDateFacture(DateUser dateFacture) {
		this.dateFacture = dateFacture;
	}
	/**
	 * renvoie le numaro de la facture
	 * @return
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * setter du numero de la facture
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * renvoie le String facture
	 * @return
	 */
	public String getFacture() {
		return facture;
	}
	/**
	 * setteur du String facture
	 * @param facture
	 */
	public void setFacture(String facture) {
		this.facture = facture;
	}
	/**
	 * méthode qui renvoie si l'on peut supprimer la facture
	 * @return
	 */
	public boolean supprimable()
	{
		int nbJourAuthorisation=7;
		DateUser aujourdhui = new DateUser();
		if(dateFacture.ajouterNombreJours(nbJourAuthorisation).avant(aujourdhui))
		{
			//si la date de la facturisation de la commande, en ajoutant le nbJourAuthorisation, est avant la date d'aujourdhui
			//alors la l'authorisation de la suppression est possible
			return true;
		}else{
			//si elle est après, la date de dateFacture.ajouterNombreJours(nbJourAuthorisation) est la date après laquelle 
			//la suppression est authorisee
			return false;
		}
	}
}
