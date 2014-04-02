package graphique;

import gestionnaires.GestionGenerale;
import gestionnaires.GestionTableDesArticles;
import gestionnaires.GestionTableDesCommandes;
import gestionnaires.GestionTableFacture;
import iPane.ES;
import structures.TableArticle;
import structures.TableDesCommandes;
import structures.TableDesFactures;
import tools.Tools;

public class ClientJava {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GestionGenerale GG= new GestionGenerale();
		GraphiqueFrame GF = new GraphiqueFrame(GG);
		GF.setVisible(true);
		
	}

}
