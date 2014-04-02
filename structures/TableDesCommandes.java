package structures;

import interfaces.InterfaceStructure;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import tools.*;

public class TableDesCommandes implements InterfaceStructure<String, UneCommande>, Serializable
{
	
	private Hashtable<String, UneCommande> lesCommandes;
	private int numeroProchaineCommande;
	private DateUser dateDerniereCommande= null;
	
	/**
	 * Constructeur par défaut.
	 */
	public TableDesCommandes()
	{
		lesCommandes = new Hashtable<String, UneCommande>();
		numeroProchaineCommande= 1;
	}
	/**
	 * Constructeur avec param
	 */
	//à revoir avec date
	public TableDesCommandes(Hashtable<String, UneCommande> table)
	{
		lesCommandes = table;
		if(!lesCommandes.isEmpty())
		{
			numeroProchaineCommande = this.cleMaximale()+1;
		} else {
			numeroProchaineCommande= 1;
		}
			
	}
	/**
	 * getter du numero de la prochaine commande
	 * @return
	 */
	public int getNumeroProchaineCommande()
	{
		return numeroProchaineCommande;
	}
	/**
	 * getter des commandes
	 * @return la table des commandes
	 */
	public Hashtable<String, UneCommande> getLesCommandes()
	{
		return lesCommandes;
	}
	/**
	 * 
	 */
	public DateUser getDateDerniereCommande(){
		return dateDerniereCommande;
	}
	/**
	 * setter du numero de commande
	 */
	public void setNumeroProchaineCommande(int num)
	{
		this.numeroProchaineCommande = num;
	}
	/**
	 * setter de la table des commandes
	 */
	public void setTableDesCommandes( Hashtable<String, UneCommande> t)
	{
		this.lesCommandes = t;
	}
	/**
	 * setter de la date de la derniere commande
	 */
	public void setDateDerniereCommande(DateUser d)
	{
		dateDerniereCommande=d;
	}
	@Override
	/**
	 * ajouter une commande à la table des Commandes
	 * après vérification que le numéro de la commande est le même que la clé si il exite
	 * s'il n'existe pas, atribution de ce numero à la commande
	 * @param une nouvelle commande
	 */
	
	public void ajouter(UneCommande nouvelle)
	{
		if(this.dateDerniereCommande!=null){
			if( this.dateDerniereCommande.compare(nouvelle.getDateCommande()))
			{
				numeroProchaineCommande++;
			} else {
				this.dateDerniereCommande = nouvelle.getDateCommande();
				numeroProchaineCommande = 2;
			}
		}else {
			this.dateDerniereCommande = nouvelle.getDateCommande();
			numeroProchaineCommande = 2;
		}
		lesCommandes.put(nouvelle.getIdCommande(), nouvelle);
		
		/*
		if(nouvelle.getIdCommande()== ""+this.dateDerniereCommande.getAnnee()+
				this.dateDerniereCommande.getMois()+this.dateDerniereCommande.getJour()+numeroProchaineCommande)
		{
			lesCommandes.put(idCommande, nouvelle);
			
			numeroProchaineCommande++;
		} else if ((Integer)nouvelle.getNumeroCommande()== null){
			lesCommandes.put(numeroProchaineCommande, nouvelle);
			nouvelle.setNumeroCommande(numeroProchaineCommande);
			numeroProchaineCommande++;
		} else {
			// problem!! ne pas ajouter
		}
		*/
	}
	@Override
	/**
	 * supression d'une commande de la table des commande
	 */
	public void supprimer(String numCommande)
	{
		lesCommandes.remove(numCommande);
	}
	@Override
	/**
	 * retourne la commande de la clé demandé
	 */
	public UneCommande retourner(String numCommande)
	{
		return lesCommandes.get(numCommande);
	}
	@Override
	/**
	 * retourne la taille de la table
	 */
	public int taille()
	{
		return lesCommandes.size();
	}
	/**
	 *  renvoie la chaine de charactère de toutes les commandes dans la table des commandes.
	 *  après test si la table des commandes est vide, si vide renvoie "Il n'y a aucune commande"
	 */
	public String toString()
	{
		if(lesCommandes.isEmpty())
		{
			return ("Il n'y a aucune commande");
		}
			
		String totalTable = "*******Toutes les commandes:\n";
		
		Enumeration<UneCommande> em =lesCommandes.elements();
		while(em.hasMoreElements())
		{
			UneCommande c = em.nextElement();
			totalTable += c.toString() +"\n";
		}
		return totalTable;
	}
	/**
	 * retourne toutes les clés de la structure
	 * @return String des cles
	 */
	public String cle()
	{
		String total ="";
		Enumeration<String> em = lesCommandes.keys();
		while(em.hasMoreElements())
		{
			String idCommande = em.nextElement();
			total += "*** "+ idCommande +"  ";
		}
		return total;
	}
	/**
	 * @retunr la valeur de la clé maximale, donc la commande de plus haut numéro
	 */
	//problem!! est ce que j'ai besoin de cette méthode
	public int cleMaximale()
	{
		int max = 1;
		for(String s:lesCommandes.keySet())
		{
			Integer i=Integer.parseInt(s);
			if(max < i)
			{
				max = i;
			}
		}
		return max;
	}
	/**
	 * verification date commande
	 */
}
