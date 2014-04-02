package gestionnaires;

import iPane.ES;
import interfaces.InterfaceGestion;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import tools.*;
import metiers.*;
import structures.*;


import connexions.ConnectionFichiers;

public class GestionTableFacture implements InterfaceGestion<TableDesFactures, TableDesCommandes,TableArticle>, Serializable {
	private ConnectionFichiers<TableDesFactures> fichierDesFactures;
	
	/**
	 * constructeur avec param
	 * @param nomFichierFacture
	 */
	public GestionTableFacture(String nomFichierFacture)
	{
		fichierDesFactures = new  ConnectionFichiers<TableDesFactures>(nomFichierFacture);
	}
	/**
	 * lecture du fichier qui sauvgarde la table des factures
	 * @return
	 */
	public TableDesFactures lire()
	{
		TableDesFactures tabFact =  fichierDesFactures.lire();
		if (tabFact==null) tabFact = new TableDesFactures();
		return tabFact;
	}
	/**
	 * ecriture de la table des factures en param dans le fichier des factures
	 * @param tabFact
	 */
	public void ecrire(TableDesFactures tabFact)
	{
		fichierDesFactures.ecrire(tabFact);
	}
	/**
	 * menu général qui renvoie vers la méthode choisi par l'utilisateur
	 */
	@Override
	public void menuGeneral(TableDesFactures lesFactures, TableDesCommandes lesCommandes,TableArticle lesArticles) 
	{
		int choix;
		
		do {
			try{
				
				choix = menuChoix(lesFactures);
				switch(choix)
				{ 
					case 1: ajouter(lesFactures, lesCommandes, lesArticles ); break;//editer une facture, donc on ajoute une facture
					case 2: supprimer(lesFactures, lesCommandes, lesArticles); break;//supprimer une facture
					case 3: editer(lesFactures, lesCommandes, lesArticles); break;//visualiser une facture
					case 4: facturerToutes(lesFactures, lesCommandes, lesArticles); break;//editer toutes les comamndes en facture
					
					case 0: break;
				}
			} catch(Exception e){
				ES.affiche("Gestion des factures", "Vous avez annulé, retour au menu précédent");
				choix=0;
			}
		} while(choix!=0);
		
	}
	/**
	 * affichage plus choix
	 */
	@Override
	public int menuChoix(TableDesFactures tab1) throws Exception {
		String st=("\tGESTION de LA TABLE des FACTURES\n"+
				Tools.calculeEspace("EDITER une Facture ", 45) +"1\t\t\n"+ 
				Tools.calculeEspace("SUPPRIMER une Facture ", 43)+"2\n"+
				Tools.calculeEspace("VISUALISER une Factures", 45)+"3\n"+
				Tools.calculeEspace("EDITER toutes les Factures", 45)+"4\n"+
				Tools.calculeEspace("FIN ",77)+"0\n"+"saisissez maintenant:");	
		return ES.saisie("Les Factures",st, 0,4);
	}

	/**
	 * ajout d'une facture à la table des facture, après vérification qu'il y a au moins une commande à facturer
	 * et vérification si la commande choisi n'a pas déjà été facturé
	 */
	@Override
	public void ajouter(TableDesFactures lesFactures, TableDesCommandes lesCommandes,TableArticle lesArticles)
	{
		String titre = ("editer une facture");
		String afficher = GestionTableDesCommandes.listeDesCommandes(lesCommandes);
		afficher+= "Quelle Commande voulez vous facturer?";
		try{
			String idCommande = ES.saisie(titre,afficher);
			if(Tools.valider(idCommande, lesCommandes.getLesCommandes()))
			{
				UneCommande commandeActuelle = lesCommandes.retourner(idCommande);
				//verification de la situation de la commande
				if(!commandeActuelle.getFacturee())
				{
					//j'ajoute la facture dans la table des facture et je change l'etat de facturee en true
					//par la procédure:
					transferComVersFact(lesFactures, commandeActuelle, lesArticles);
				} else {
					ES.affiche(titre, "Cette commande est déjà facturée");
				}
			}else {
				ES.affiche(titre, "votre saisie ne correspond pas à une commande");
			}
		}catch (Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
		
	}
	/**
	 * suppression d'une facture de la table des factures après test supprimable()
	 */
	@Override
	public void supprimer(TableDesFactures lesFactures, TableDesCommandes lesCommandes,TableArticle lesArticles) 
	{
		String titre = ("supprimer une facture");
		String afficher = listeDesFactures(lesFactures);
		afficher+= "/n Quelle Facture voulez vous supprimer?";
		try{	
			String idFacture= ES.saisie(titre,afficher);
			if(Tools.valider(idFacture, lesFactures.getLesFactures()))
			{
				Facture actuelle = lesFactures.retourner(idFacture);
				//verification de la situation de la facture
				if(actuelle.supprimable())
				{
					String reponse =ES.saisie(titre, "êtes vous sûr de supprimer? (o/n)");
					if(reponse=="o")
					{
						lesFactures.supprimer(idFacture);
					}
				} else {
					ES.affiche(titre, "Cette facture ne peut être supprimée : le delais minimum pour ce faire n'est pas expiré");
				}
			}else{
				ES.affiche(titre, "Aucune facture a cet identifiant");
			}
		}catch (Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	/**
	 * liste toutes les factures dans la structure
	 * @param lesFactures
	 * @return
	 */
	private String listeDesFactures(TableDesFactures lesFactures) {
		if (lesFactures.taille()!=0)
		{
			String liste = "*****************Liste des numéros de commandes passées\n" + lesFactures.cle()+"\n";
			return liste;
		} else {
			return "Il n'y a aucune facture pour le moment";
		}
	}
	/**
	 * visualisation d'une facture
	 * choix de la facture et affichage
	 */
	@Override
	public void editer(TableDesFactures lesFactures, TableDesCommandes lesCommandes,TableArticle lesArticles)
	{
		String titre = "visualisation d'une facture";
		String afficher = listeDesFactures(lesFactures);
		afficher+= "/n Quelle Facture voulez vous visualiser?";
		try{
			String idFacture= ES.saisie(titre,afficher);
			if(Tools.valider(idFacture, lesFactures.getLesFactures()))
			{
				ES.affiche(titre, lesFactures.retourner(idFacture).toString());
			} else {
				ES.affiche(titre,"ceci n'est pas une facture");
			}
		}catch (Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	/**
	 * facturation de toutes les factures qui n'ont pas été facturées
	 * @param lesFactures
	 * @param lesCommandes
	 * @param lesArticles
	 */
	public void facturerToutes(TableDesFactures lesFactures, TableDesCommandes lesCommandes,TableArticle lesArticles)
	{
		String titre = "Facturisation de toutes les commandes";
		if(lesCommandes.taille()!=0)
		{
			try{
				String reponse =ES.saisie(titre, "êtes-vous sûr de vouloir facturer toutes les commandes?(o/n)");
				
				if(reponse.toLowerCase().charAt(0)=='o'){
					Enumeration<String> em = lesCommandes.getLesCommandes().keys();
					while(em.hasMoreElements())
					{
						String idCommande = em.nextElement();
						UneCommande commandeActuelle = lesCommandes.retourner(idCommande);
						//verification de la situation de la commande
						if(!commandeActuelle.getFacturee())
						{
							//j'ajoute la facture dans la table des facture et je change l'etat de facturee en true
							//par la procédure:
							transferComVersFact(lesFactures, commandeActuelle, lesArticles);
						}
						
					}
				}else{
					ES.affiche(titre, "retour au menu sans facturisation");
				}
			}catch (Exception e){
				ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
			}
	
		} else {
			ES.affiche(titre,"aucune commande n'a été créé");
		}
		
	}
	
	/**
	 * procedure de creation de facture à partir d'une commande et son ajout à la table des factures
	 * il n'y a aucun test dans cette procédure, il faut l'appeler seulement après tests
	 * elle est en static pour laiisser la posibilite de l'appeler de gestion des commandes
	 * @param lesFactures
	 * @param commande
	 * @param lesArticles
	 */
	public static void transferComVersFact(TableDesFactures lesFactures, UneCommande commande,TableArticle lesArticles)
	{
		String f = commande.facturer(lesArticles);
		DateUser d = new DateUser();
		Facture acutelle =new Facture(d,commande.getIdCommande(),f);
		
		lesFactures.ajouter(acutelle);
		ES.affiche("facturation", "la facture n°"+commande.getIdCommande()+" à été ajoutée à la table des factures");
		commande.setFacturee(true);
	}
}
