package metiers;

import java.io.Serializable;
import tools.*;
import structures.*;


public class LigneDeCommande implements Serializable {
	private Integer codeArticle;
	private int quantite;

	
	/**
	 * Constructeur par defaut
	 */
	public LigneDeCommande(){}
	/**
	 * Constructeur avec param
	 * @param ca
	 * @param q
	 */
	public LigneDeCommande(int ca, int q){
		this.codeArticle=ca;
		this.quantite =q;
		
	}
	/**
	 * getter de codeArticle
	 * @return
	 */
	public Integer getCodeArticle()
	{
		return codeArticle;
	}
	/**
	 * getter de quantite
	 * @return
	 */
	public int getQuantite()
	{
		return quantite;
	}
	/**
	 * setter du code article
	 * @param ca
	 */
	public void setCodeArticle(int ca)
	{
		this.codeArticle = ca;
	}
	/**
	 * setter de quantite
	 * @param q
	 */
	public void setQuantite(int q)
	{
		this.quantite = q;
	}
	
	/**
	 * return String la ligne de commande
	 */
	public String toString()
	{
		return "code:  "+this.codeArticle +"     quantite:   "+ this.quantite;
	}
	
	public String facturer(TableArticle lesArticles)
	{
		
		AbstraitArticle<Integer> art = lesArticles.retourner(this.codeArticle);
		float pu = (float) art.getPrixUnitaires();
		String desg = art.getDesignation();
			
		String total = ""+Tools.calculeEspace(""+codeArticle, 50)
				+Tools.calculeEspace("***"+ desg,50) +Tools.calculeEspace("***"+ quantite,50)
				+Tools.calculeEspace("***"+ pu, 50) +Tools.calculeEspace("***"+ art.prixFacture(quantite),50)+"\t";
		if(lesArticles.retourner(this.codeArticle) instanceof ArticlePromo)
		{
			total +=((ArticlePromo) lesArticles.retourner(this.codeArticle)).stringFacture();
		}
		return total+"\n";
		
		
	}


}
