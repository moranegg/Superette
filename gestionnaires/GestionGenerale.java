package gestionnaires;

import graphique.ClientJava;
import graphique.GraphiqueFrame;
import structures.TableArticle;
import structures.TableDesCommandes;
import structures.TableDesFactures;

/**
 * Classe de gestion du programme qui est en relation avec l'interface graphique
 * @author MG
 *
 */
public class GestionGenerale {
	private GestionTableDesArticles GTA;
	private GestionTableDesCommandes GTC;
	private GestionTableFacture GTF;
	
	//platforme graphique
	private GraphiqueFrame GF;
		
	//les structures
	private TableArticle lesArticles;
	private TableDesCommandes lesCommandes;
	private TableDesFactures lesFactures;
	
	/**
	 * constructeur par defaut
	 */
	public GestionGenerale()
	{
		GTA = new GestionTableDesArticles( "fichierDesArticles");
		GTC= new GestionTableDesCommandes("fichierDesCommandes");
		GTF= new GestionTableFacture("fichierDesFactures");
		
		lesArticles = GTA.lire();
		lesCommandes = GTC.lire();
		lesFactures= GTF.lire();
		
		
		
	}
	/**
	 * consructeur avec param, en parametre les differents fichiers
	 * @param fichierArticles
	 * @param fichierCommandes
	 * @param fichierFactures
	 */
	public GestionGenerale(String fichierArticles, String fichierCommandes,String fichierFactures)
	{
		GTA = new GestionTableDesArticles( fichierArticles);
		GTC= new GestionTableDesCommandes(fichierCommandes);
		GTF= new GestionTableFacture(fichierFactures);
		
		lesArticles = GTA.lire();
		lesCommandes = GTC.lire();
		lesFactures= GTF.lire();
		
	}
	/**
	 * l'ecriture des structures des donnees dans les fichiers
	 */
	public void ecrire()
	{
		GTA.ecrire(lesArticles);
		GTC.ecrire(lesCommandes);
		GTF.ecrire(lesFactures);
	}
	
	/**
	 * fait appel au gestionnaire des articles
	 */
	public void appelGestionArticles()
	{
		GTA.menuGeneral(lesArticles, lesCommandes, lesFactures);
	}
	/**
	 * fait appel au gestionnaire des commandes
	 */
	public void appelGestionCommandes()
	{
		GTC.menuGeneral(lesCommandes, lesArticles,  lesFactures);
	}
	/**
	 * fait appel au gestionnaire des factures
	 */
	public void appelGestionFactures()
	{
		GTF.menuGeneral(lesFactures, lesCommandes,lesArticles);
	}
}
