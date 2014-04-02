package gestionnaires;
/**
 * @author GruenepterMorane
 */
import tools.*;
import structures.*;
import metiers.*;
import iPane.ES;
import interfaces.InterfaceGestion;


public class GestionDUneCommande  implements InterfaceGestion<UneCommande, TableArticle, TableDesFactures>{
	@Override
	public void menuGeneral(UneCommande commandeCourante, TableArticle lesArticles, TableDesFactures lesFactures)
	{
		int choix;
		
		do{
			try{
				choix = menuChoix(commandeCourante);
				switch(choix)
					{
						case 0: break;
						case 1: ajouter( commandeCourante, lesArticles, lesFactures); break;// ligne de commande
						case 2:	afficheCommande(commandeCourante); break;//afficher commande
						case 3:	editer(commandeCourante, lesArticles, lesFactures);break;//facture	
						case 4: supprimer(commandeCourante, lesArticles, lesFactures);
					}
			} catch(Exception e){
				ES.affiche("gestion d'une commande", "Vous avez annulé, retour au menu précédent");
				choix=0;
			}
		}while(choix!=0);
		
	
	}
	@Override
	/**
	 * @throws Exception, si l'utilisateur decide d'annuler, cette exception est traité plus haut
	 */
	public  int menuChoix(UneCommande commande) throws Exception
	{	
		String titre = "Commande n°"+ commande.getIdCommande();
		String st=("\t GESTION d'une COMMANDE\n"+
				Tools.calculeEspace("Saisir une ligne de COMMANDE",44)+"1\t\t\n"+
				Tools.calculeEspace("AFFICHER la commande en cours",41)+"2\n"+
				Tools.calculeEspace("Editer la facture",63)+"3\n"+
				Tools.calculeEspace("Suprimer Ligne de Commande",43)+"4\n"+
				Tools.calculeEspace("Fin",72)+"0\n"+
		"saisissez maintenant:");
		return ES.saisie(titre,st, 0, 4);
	}
	

	@Override
	/**
	 * ajout d'une instance ligne de commande à la commande
	 * vérification de la présence le l'article saisie dans le tableau d'article
	 * s'il existe => vérification si cet artcle est en promotion
	 * si oui => affichage de la quantite minimale pour bénéficier de la promotion
	 * création d'une ligneDeCommande directement ajouter dans la commande 
	 * @param uneCommande
	 * @param lesArticles
	 * @param obect o(serie5)
	 *
	 */
	public void ajouter(UneCommande uneCommande, TableArticle lesArticles,TableDesFactures lesFactures)
	{
		String titre = "Ajouter ligne de commande";
		String listeArticles = listeDesArticles(lesArticles);
		try{
			int choixArticle = ES.saisie(titre,listeArticles+"\nsaisir code produit: ", 0, lesArticles.codeArticleMax() );
			if(Tools.valider(choixArticle, lesArticles.getTable()))
			{
				if(lesArticles.retourner(choixArticle) instanceof ArticlePromo)
				{
					ArticlePromo a=  (ArticlePromo) lesArticles.retourner(choixArticle);
					ES.affiche(titre,"Cet article est en promotion!! Quantite minimum:"+a.getQuantiteMin());
				}
				int quantite = ES.saisie(titre,"la quantitée: ",1, Integer.MAX_VALUE);
				LigneDeCommande ligne = new LigneDeCommande(choixArticle,quantite);
				uneCommande.ajouter(ligne);
			} else {
				ES.affiche(titre,"CET ARTICLE N'EXISTE PAS");
				
			}
		} catch(Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	/**
	 * methode interne, pour avoir la liste des articles en stock
	 * @param lesArticles
	 * @return la liste de tous les articles pour affichage
	 */
	public String listeDesArticles(TableArticle lesArticles)
	{
		if(lesArticles.taille()==0) return "Il n'y a aucun article en stock";
		return lesArticles.toString();
	}
	/**
	 * pour afficher la commande en cours
	 * @param uneCommande
	 */
	public void afficheCommande(UneCommande uneCommande)
	{
		String titre="Affichage commande";
		if(uneCommande.taille()>0)
		{
			
			ES.affiche(titre,"***************Voici votre commande en cours\n"+uneCommande.toString());
		} else {
			ES.affiche(titre,"votre commande est vide pour le moment");
		}
	}
	@Override	
	/**
	 * imprime la facture de la commande en cours
	 * @param uneCommande
	 * @param lesArticles
	 * @param obect o(serie5)
	 */
	public void editer(UneCommande uneCommande, TableArticle lesArticles,TableDesFactures lesFactures)
	{
		String titre="Facturer commande";
		if(uneCommande.taille()>0)
		{
			ES.affiche(titre,uneCommande.facturer(lesArticles));
			GestionTableFacture.transferComVersFact(lesFactures, uneCommande, lesArticles);
		} else {
			ES.affiche(titre, "la commande est vide");
		}
	}

	@Override
	/**
	 * Supprime une instance ligne de commande dans une commande après un choix du code article 
	 * @param uneCommande
	 * @param lesArticles
	 * @param obect o(serie5)
	 */
	public void supprimer(UneCommande uneCommande, TableArticle lesArticles,TableDesFactures lesFactures)
	{
		String titre="Supprimer ligne de commande par article";
		if(!uneCommande.getLesCommandes().isEmpty())
		{
			try{
				int choixArticle = ES.saisie(titre,uneCommande.toString()+"\nsaisir code article à supprimer: ", 0, lesArticles.codeArticleMax() );
				if(Tools.validerLigneDeCommande(choixArticle, uneCommande))
				{
					uneCommande.supprimer(choixArticle);
					ES.affiche(titre,"La ligne de commande a été supprimé");
				}
			} catch(Exception e){
				ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
			}
			
		} else {
			ES.affiche(titre,"pas de Ligne de commande à supprimer");
		}
	}
}
