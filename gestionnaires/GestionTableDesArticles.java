package gestionnaires;
import connexions.ConnectionFichiers;
import tools.*;
import structures.*;
import metiers.*;
import iPane.ES;
import interfaces.InterfaceGestion;
import graphique.*;


/**
 * @author GruenepterMorane
 */
public class GestionTableDesArticles implements InterfaceGestion<TableArticle,TableDesCommandes,TableDesFactures> {
	
	private ConnectionFichiers<TableArticle> fichierArticle;
	
	
	public GestionTableDesArticles(String nomFichierPhysique)
	{
		this.fichierArticle = new ConnectionFichiers<TableArticle> (nomFichierPhysique);
	}
	/**
	 * fait une connexion vers le fichier qui sauvegarde la table des articles
	 * retourne la structure table des articles pour des fonction de lecture
	 * @return
	 */
	public TableArticle lire()
	{
		TableArticle tabArt =  fichierArticle.lire();
		if (tabArt==null) tabArt = new TableArticle();
		return tabArt;
	}
	/**
	 * fait une connexion vers le fichier qui sauvegarde la table des articles
	 * et ecrit les changement qui ont été effectué.
	 * @param lesArticles
	 */
	public void ecrire(TableArticle lesArticles)
	{
		fichierArticle.ecrire(lesArticles);
	}
	@Override
	/**
	 * procedure de gestion des articles
	 * la premiere méthode d'entrée dans la gestion des articles
	 * @param lesArticles
	 */
	public void menuGeneral(TableArticle lesArticles, TableDesCommandes lesCommandes, TableDesFactures lesFactures)
	{
		//FrameArticles FA = new FrameArticles(this);
		//FA.setVisible(true);
		
		
		int choix;
				
		do {
			try{
				choix = menuChoix(lesArticles);
				
					switch(choix)
					{ 
						case 1: ajouter(lesArticles, lesCommandes, lesFactures); break;
						case 2: supprimer(lesArticles, lesCommandes,lesFactures); break;
						case 3: editer(lesArticles, lesCommandes,lesFactures); break;
						case 4: editerPromo(lesArticles);break;
						case 0: break;
					}
			} catch(Exception e){
				ES.affiche("Gestion des articles", "Vous avez annulé, retour au menu précédent");
				choix =0;
			}
		} while(choix!=0);
	}
	@Override
	/**
	 * 
	 * @return
	 */
	public int menuChoix(TableArticle o) throws Exception
	{
		String st=("\t GESTION des Articles\n"+
				Tools.calculeEspace("Ajouter un nouvel Article",47)+"1\t\t\n"+
				Tools.calculeEspace("Supprimer un Article",48)+"2\t\t\n"+
				Tools.calculeEspace("Afficher tous les Articles",48)+"3\t\t\n"+
				Tools.calculeEspace("Afficher les Articles en promotion",39)+"4\t\t\n"+
				Tools.calculeEspace("Fin",62)+"0\t\t\n"+
		"saisissez maintenant:");
		return ES.saisie("Les articles",st, 0, 4);
	}
	@Override
	/**
	 * 
	 * @param lesArticles
	 */
	public void ajouter(TableArticle lesArticles,TableDesCommandes lesCommandes, TableDesFactures lesFactures)
	{
		String titre= "Ajouter un article";
		try{
			int code = ES.saisie(titre,"choisissez code article? ", 1, Integer.MAX_VALUE);
			if(!Tools.valider(code, lesArticles.getTable()))
			{
				String designation = ES.saisie(titre,"choisissez designation? ");
				float pu = ES.saisie(titre,"prix unitaire? ", 0, Float.MAX_VALUE);
				char promo = ES.saisie(titre, "Cet article est-il en Promotion? (O/N)").toLowerCase().charAt(0);
				if(promo == 'n'){
					AbstraitArticle nouveau = new Article(code, designation, pu);
					lesArticles.ajouter(nouveau);
					//ecrire(lesArticles);//je sauvegarde le nouveau fichier avec le nouvel article
				} else if(promo =='o'){
					float reduction = ES.saisie(titre,"Entrez la reduction appliquée:",0, 99.99f);
					int quantite =ES.saisie(titre,"Quantite mini pour appliquer la réduction:",1, Integer.MAX_VALUE);
					AbstraitArticle  nouveau = new ArticlePromo(code, designation, pu, reduction, quantite);
					lesArticles.ajouter(nouveau);
					//ecrire(lesArticles);//je sauvegarde le nouveau fichier avec le nouvel article
				}else{
					ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
				}
				
			} else {
				ES.affiche(titre,"Ce code existe déjà");
			}
		} catch(Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	@Override
	/**
	 * supprimer un article à la table des articles
	 * @param lesArticles
	 */
	public void supprimer(TableArticle lesArticles, TableDesCommandes lesCommandes, TableDesFactures lesFactures)
	{
		String titre = "Supprimer un article";
		if(lesArticles.taille()==0) {
			ES.affiche(titre,"Le stock est vide");
			return;
		}
		try{
			Integer code = ES.saisie(titre,"choisissez code article? ", 1, Integer.MAX_VALUE);
			if(Tools.valider(code, lesArticles.getTable()))
			{
				lesArticles.supprimer(code);
				//ecrire(lesArticles);//je sauvegarde la table après la suppression de l'article
				GestionTableDesCommandes.supprimeCascade(code, lesCommandes);
				//comment faire pour supprimer en cascade en enregistrent dans le fichier des commandes les modifs?
				ES.affiche(titre,"L'article "+code+" a été suprimé");
			} else {
				ES.affiche(titre,"Cet article n'existe pas");
			}
		} catch(Exception e){
			ES.affiche(titre, "Vous avez annulé, retour au menu précédent");
		}
	}
	@Override
	/**
	 * afficher tous les articles
	 */
	public  void editer(TableArticle lesArticles, TableDesCommandes lesCommandes, TableDesFactures lesFactures)
	{
		String titre= "Edition du catalogue des articles";
		if(lesArticles.taille()==0) {
			ES.affiche(titre,"Le stock est vide");
			return;
		}
		ES.affiche(titre,lesArticles.toString());
	}
	/**
	 * afficher tous les articles
	 */
	public void editerPromo(TableArticle lesArticles)
	{
		String titre= "Edition du catalogue des articles en promotion";
		if(!lesArticles.existePromo()) {
			ES.affiche(titre,"Aucun article est en promotion");
			return;
		}
		ES.affiche(titre,lesArticles.toStringPromo());
	}


}
