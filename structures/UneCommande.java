package structures;

import tools.*;
import metiers.*;
import interfaces.InterfaceStructure;
import java.io.Serializable;
import java.util.Vector;


public class UneCommande  implements InterfaceStructure<Integer,LigneDeCommande>, Serializable
{
	private Vector<LigneDeCommande> lesLigneDeCommandes; // tableauDeLigneDeCommande
	private String idCommande;//pas initialiser!!!
	private DateUser dateCommande;
	private boolean facturee;
	
	/**
	 * Constructeur par defaut
	 */
	public UneCommande()
	{
		lesLigneDeCommandes = new Vector<LigneDeCommande>();
		dateCommande = new DateUser();
		facturee =false;
	}
	/**
	 * constructeur n°2
	 * @param num
	 */
	public UneCommande(int num, DateUser d)
	{
		lesLigneDeCommandes = new Vector<LigneDeCommande>();
		dateCommande =d;
		idCommande = ""+dateCommande.getAnnee()+dateCommande.getMois()+dateCommande.getJour()+num;
		facturee= false;
	}
	/**
	 * getter du vecteur
	 */
	public Vector<LigneDeCommande> getLesCommandes()
	{
		return lesLigneDeCommandes;
	}
	/**
	 * setter du vecteur
	 */
	public void setLesCommandes( Vector<LigneDeCommande> nouveau)
	{
		lesLigneDeCommandes = nouveau;
	}
	/**
	 * 
	 * @return
	 */
	public  DateUser getDateCommande()
	{
		return this.dateCommande;
	}
	/**
	 * 
	 * @return
	 */
	public String getIdCommande()
	{
		return this.idCommande;
	}
	/**
	 * 
	 * @param d
	 */
	public void setDateUser(DateUser d)
	{
		this.dateCommande = d;
	}
	/**
	 * setter du numeroDeCommande
	 * @param numero
	 */
	public void setNumeroCommande(String numero)
	{
		this.idCommande = numero;
	}
	/**getter de l'état de la commande, vrai si elle a été facturée
	 * 
	 */
	public boolean getFacturee(){
		return this.facturee;
	}
	/**
	 * setter de l'état de la commande, vrai si elle a été facturée 
	 */
	public void setFacturee(boolean b){
		this.facturee=b;
	}
	@Override
	/**
	 * retrouve la ligne de commande d'index "index"
	 * @param index
	 * @return LigneDeCommande
	 */
	public LigneDeCommande retourner(Integer index)
	{
		return lesLigneDeCommandes.get(index);
	}
	@Override
	/**
	 * ajoute une ligne de commande à la commande
	 * @param lig
	 */
	public void ajouter(LigneDeCommande lig)
	{
		lesLigneDeCommandes.add(lig);
	}
	@Override
	/**
	 * supprime une ligne de commande tel que cette ligne contient l'article de code en paramètre
	 * @param index
	 *
	 */
	public void supprimer(Integer code)
	{
		int found = -1;
		for(int i=0; i<lesLigneDeCommandes.size();i++)
		{
			found = lesLigneDeCommandes.get(i).getCodeArticle();
			if(found== code)
			{
				lesLigneDeCommandes.remove(i);
				
			}
		}
	}
	@Override
	/**
	 * 
	 * @return la taille de la commande
	 */
	public int taille()
	{
		return lesLigneDeCommandes.size();
	}
	
	/**
	 * return String de toutes les lignes de la commande
	 */
	public String toString()
	{
		String all = "commande numero: "+ idCommande +" date de commande: "+ dateCommande.toString()+"\n";
		for(LigneDeCommande li : lesLigneDeCommandes)
		{
			all += li.toString() +"\n";
		}
		return all;
	}
	/**
	 * retourne la chaine de characteres de la facture de la commande en cours
	 * @param lesArticles
	 * @return
	 */
	public String facturer(TableArticle lesArticles)
	{
		String entete= "\t\t FACTURE DE LA COMMANDE N°"+idCommande + "faite le "+ dateCommande.toString()+"\n"
		+Tools.calculeEspace("***Code",50)+Tools.calculeEspace("***DESIGNATION",30)
		+Tools.calculeEspace("***QUANTITE",50)+Tools.calculeEspace("***PU(HT)",30)
		+Tools.calculeEspace("***TOTAL(HT)",30)
				+"\n________________________________________________________________________________________________________\n";
		String details="";
		String pied;
		float total=0;
		float TTC ;
		for(int i=0; i<lesLigneDeCommandes.size();i++)
		{
			details = details + "***"+ lesLigneDeCommandes.get(i).facturer(lesArticles);
			
			total += lesArticles.retourner(lesLigneDeCommandes.get(i).getCodeArticle()).prixFacture(lesLigneDeCommandes.get(i).getQuantite());
			
		}
			
		TTC =(float)((total*19.6)/100 )+ total;
		pied = "\t\t\t\t\t\t\t TOTAL HORS TAXE:"+total+"\n" +
				"\t\t\t\t\t\t\t TVA 19.6% :"+(total*19.6)/100+"\n"+
				"\t\t\t\t\t\t\t TOTAL Tout TAXE:"+TTC+"\n";
		return entete+details+pied;
	}

}
