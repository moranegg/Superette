package gestionnaires;
import iPane.ES;
import interfaces.InterfaceGestion;
import java.util.Iterator;
import tools.*;
import structures.*;
import metiers.*;
import connexions.ConnectionFichiers;



/**
 * @author GruenepterMorane
 */
public class GestionTableDesCommandes implements InterfaceGestion<TableDesCommandes, TableArticle, TableDesFactures>{
	private ConnectionFichiers<TableDesCommandes> fichierDesCommandes;
	
	public GestionTableDesCommandes(String nomFichierPhysique)
	{
		this.fichierDesCommandes= new ConnectionFichiers<TableDesCommandes> (nomFichierPhysique);
	}
	/**
	 * fait une connexion vers le fichier qui sauvegarde la table des articles
	 * retourne la structure table des articles pour des fonction de lecture
	 * @return
	 */
	public TableDesCommandes lire()
	{
		TableDesCommandes tabArt =  fichierDesCommandes.lire();
		if (tabArt==null) tabArt = new TableDesCommandes();
		return tabArt;
	}
	/**
	 * fait une connexion vers le fichier qui sauvegarde la table des articles
	 * et ecrit les changement qui ont été effectué.
	 * @param lesArticles
	 */
	public void ecrire(TableDesCommandes lesCommandes)
	{
		fichierDesCommandes.ecrire(lesCommandes);
	}	
	@Override
	/**
	 * la premiere méthode d'entrée dans la gestion des commandes
	 * @param lesCommandes
	 * @param lesArticles
	 */
	public void menuGeneral(TableDesCommandes lesCommandes, TableArticle lesArticles, TableDesFactures lesFactures)
	{
		int choix;
		
		do {
			try{
				
				choix = menuChoix(lesCommandes);
				switch(choix)
				{ 
					case 1: ajouter(lesCommandes, lesArticles, lesFactures); break;
					case 2: supprimer(lesCommandes, lesArticles, lesFactures); break;
					case 3: visualisationDUneCommande(lesCommandes); break;
					case 4: editer(lesCommandes, lesArticles, lesFactures); break;
					case 5: facturer(lesCommandes, lesArticles, lesFactures); break;
					case 0: break;
				}
			} catch(Exception e){
				ES.affiche("Gestion des commande", "Vous avez annulé, retour au menu précédent");
				choix=0;
			}
		} while(choix!=0);
	}
	@Override
	/**
	 * affichage des possibilités de choix et le retour du choix (int)
	 * @param lesCommandes
	 * @return choix
	 */
	public int menuChoix(TableDesCommandes lesCommandes) throws Exception
	{	
		int numeroNouvelleCommande =  lesCommandes.getNumeroProchaineCommande();
		String st=("\t GESTION des Commandes\n"+
				Tools.calculeEspace("CREATION de la COMMANDE N°  "+ numeroNouvelleCommande, 45) +"1\t\t\n"+
				Tools.calculeEspace("SUPPRESSION d'une COMMANDE",43)+"2\n"+
				Tools.calculeEspace("VISUALISATION d'une COMMANDE", 45)+"3\n"+
				Tools.calculeEspace("VISUALISATION toutes les COMMANDES",40)+"4\n"+
				Tools.calculeEspace("EDITION FACTURE",60)+"5\n"+
			Tools.calculeEspace("Fin",77)+"0\n"+
		"saisissez maintenant:");
		return ES.saisie("Les commandes",st, 0,5);
	}
		

	@Override
	/**
	 * supprimer une Commande après visualisation des numéro de commandes existant
	 * 
	 * @param t
	 * @param c
	 */
	public  void supprimer(TableDesCommandes lesCommandes, TableArticle lesArticles,TableDesFactures lesFactures)
	{
		String titre = "Supprimer une commande";
		 
		if(lesCommandes.taille()==0) {
			ES.affiche(titre,"aucune commande n'a été passé");
			return;
		}
		
		try{
			String code =ES.saisie(titre,listeDesCommandes(lesCommandes)+"vous voulez supprimer quelle commande?");
			if (Tools.valider(code, lesCommandes.getLesCommandes()))
			{
				UneCommande commandeActuelle = lesCommandes.retourner(code);
				if(commandeActuelle.getFacturee())
				{
					lesCommandes.supprimer(code);
					ES.affiche(titre,"La commande n°"+code+" a été suprimé");
				}else
				{
					ES.affiche(titre, "cette commande n'a pas été encore facturée");
				}
			} else {
				ES.affiche(titre,"aucune commande a ce numéro");
			}
		} catch(Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
		

	}
	

	
	/**
	 * liste tous les numéro de commandes passées
	 * @param lesCommandes
	 * @return String liste
	 */
	public static String listeDesCommandes(TableDesCommandes lesCommandes)
	{
		String liste = "*****************Liste des numéros de commandes passées\n" + lesCommandes.cle()+"\n";

		return liste;
	}
	/**
	 * affiche une seule commande après choix et vérification de la commande voulu
	 * @param les Commandes
	 */
	public  void visualisationDUneCommande(TableDesCommandes lesCommandes)
	{
		String titre = "Visualisation d'une commande";
		if(lesCommandes.taille()==0) {
			ES.affiche(titre,"aucune commande n'a été passé");
			return;
		}
		try{
			String code =ES.saisie(titre,listeDesCommandes(lesCommandes)+"quelle commande voulez-vous visualiser ?");
			if (Tools.valider(code, lesCommandes.getLesCommandes()) )
			{
				
				ES.affiche(titre,"*********** CONTENU Commande "+ code+"\n"+
								lesCommandes.retourner(code).toString());
			} else {
				ES.affiche(titre,"aucune commande a ce numéro");
			}
		} catch(Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	@Override
	/**
	 * Affichage de toutes les commandes dans la table des commandes
	 * @param lesCommandes
	 */
	public void editer(TableDesCommandes lesCommandes, 
			TableArticle lesArticles,TableDesFactures lesFactures)
	{
		String titre = "Visualisation de toutes les commande";
		if(lesCommandes.taille()!=0)
		{
			ES.affiche(titre,lesCommandes.toString());
		} else {
			ES.affiche(titre,"aucune commande n'a été créé");
		}
		
	}
	/**
	 * facturation de la commande choisi par l'utilisateur après une vérification que cette le numéro de commande 
	 * correspond à une commande existante.
	 * @param lesArticles
	 * @param lesCommandes
	 */
	public static void facturer(TableDesCommandes lesCommandes, TableArticle lesArticles, TableDesFactures lesFactures)
	{
		String titre = "Facturer une commande";
		if(lesCommandes.taille()==0) {
			ES.affiche(titre,"aucune commande n'a été passé");
			return;
		}
		try{
			String code =ES.saisie(titre,listeDesCommandes(lesCommandes)+"\nvous voulez facturer quelle commande?");
			if (Tools.valider(code, lesCommandes.getLesCommandes()) )
			{
				UneCommande commandeActuelle = lesCommandes.retourner(code);
				//verification de l'etat de la commande
				if(!commandeActuelle.getFacturee())
				{
					GestionTableFacture.transferComVersFact(lesFactures,commandeActuelle  , lesArticles);
				}else {//cette commande a déjà été facturée, visualisation seulement!!! 
					ES.affiche(titre, "cette commande a déjà été facturée\n"+commandeActuelle.facturer(lesArticles));
				}
				
			} else {
				ES.affiche(titre,"aucune commande a ce numéro");
			}
		} catch(Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	/**
	 * methode de suppression des commandes d'un article supprimé en cascade.
	 * @param code
	 * @param lesCommandes
	 */
	public static void supprimeCascade(int code, TableDesCommandes lesCommandes)
	{
		
		for(UneCommande commande : lesCommandes.getLesCommandes().values())
		{
			Iterator<LigneDeCommande> it = commande.getLesCommandes().iterator();
			while(it.hasNext())
			{
				LigneDeCommande ldc= it.next();
				if( ldc.getCodeArticle()==code)
				{
					it.remove();
				}
			}
		}
		
		
	}
	@Override
	/**
	 * nous renvoie vers la gestion d'une commande
	 */
	public void ajouter(TableDesCommandes lesCommandes, TableArticle lesArticles, TableDesFactures lesFactures)
	{
		int num; 
		DateUser dateDAujourdhui = new DateUser();
		if(lesCommandes.getDateDerniereCommande()!=null){
			if(dateDAujourdhui.compare(lesCommandes.getDateDerniereCommande()))
			{
				num =lesCommandes.getNumeroProchaineCommande();
			}else {
				num = 1;
				
			}
		}else {
			num=1;
		}
		
		UneCommande commandeCourante = new UneCommande(num, dateDAujourdhui);
		new GestionDUneCommande().menuGeneral(commandeCourante, lesArticles, lesFactures);
		
		if(commandeCourante.taille()>0)
		{
			lesCommandes.ajouter(commandeCourante);
			lesCommandes.setDateDerniereCommande(dateDAujourdhui);
			if(commandeCourante.getFacturee())
			//la commande a été facturée pendant le menu d'une commande, mais pouvait être modifié depuis
			{
				// donc on change la facture dans les factures avec la bonne facture(plus récente)
				GestionTableFacture.transferComVersFact(lesFactures, commandeCourante, lesArticles);
			}
		}
	}


}
