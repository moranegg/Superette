package metiers;

import java.io.Serializable;

import tools.Tools;

public class ArticlePromo extends AbstraitArticle<Integer> implements Serializable{
	private float reduction;
	private int quantiteMinimale;
	/**
	 * constructeur par défaut
	 */
	public ArticlePromo()
	{
		super();
	}
	/**
	 * constructeur avec paramètres, fait appel à la classe abstraite AbstraitArticle
	 * avec super()
	 * @param c
	 * @param d
	 * @param pu
	 * @param reduc
	 * @param qm
	 */
	public ArticlePromo(Integer c, String d, float pu, float reduc, int qm)
	{
		super(c,d,pu);
		this.reduction=reduc;
		this.quantiteMinimale=qm;
	}
	/**
	 * Méthode abstraite- implementation obligatoire.
	 */
	@Override
	public String toString() {
		String s = Tools.calculeEspace(("code:"+codeArticle),15);
		s += Tools.calculeEspace(("designation: "+ designation),50);
		s+= Tools.calculeEspace(("prix: "+ prixUnitaire),40);
		s += Tools.calculeEspace((" **PROMO Réduction:"+reduction),60);
		s+=Tools.calculeEspace((" Quantité minimale:"+quantiteMinimale ),60);
		return s;
		
	}
	/**
	 * getteur de la réduction de l'article
	 * @return
	 */
	public float getReduction()
	{
		return reduction;
	}
	/**
	 * getteur de la quantitee minimale nécessaire pour apliquer la réduction 
	 * @return
	 */
	public int getQuantiteMin()
	{
		return quantiteMinimale;
	}
	/**
	 * setteur de la réduction
	 * @param nouvelle
	 */
	public void setReduction(float nouvelle)
	{
		this.reduction=nouvelle;
	}
	/**
	 * setteur de la quantité minimale
	 * @param nouvelle
	 */
	public void setQuantiteMin(int nouvelle)
	{
		this.quantiteMinimale=nouvelle;
	}
	/**
	 * retourne un morceau de String pour la facture d'article en promotion
	 * @return
	 */
	public String stringFacture()
	{
		return ("-->>remise:"+reduction+ "si qtite  >="+quantiteMinimale );
		
	}
	/**
	 * Méthode abstraite- implementation obligatoire.
	 * 
	 */
	@Override
	public float prixFacture(int quantite) {
		if(quantite< quantiteMinimale)
		{
			return prixUnitaire*quantite;
		} else {
			return quantite*prixUnitaire*(1-reduction/100);
		}
	}

}
