package metiers;

import java.io.Serializable;

import tools.Tools;

public class Article extends AbstraitArticle<Integer> implements Serializable{
	
	/**
	 * constructeur avec paramètres, fait appel à la classe abstraite AbstraitArticle
	 * @param c
	 * @param d
	 * @param pu
	 */
	public Article(Integer c, String d, float pu)
	{
		super(c,d,pu);
		
	}
	/**
	 * Constructeur par defaut
	 */
	public Article()
	{}
	/**
	 *   Méthode abstraite- implementation obligatoire.
	 */
	@Override
	public String toString(){
		String s = Tools.calculeEspace(("code:"+codeArticle),15);
		s += Tools.calculeEspace(("designation: "+ designation),50);
		s+= Tools.calculeEspace(("prix: "+ prixUnitaire),40);
	
		return s;
		
	}
	/**
	 * Méthode abstraite- implementation obligatoire.
	 */
	@Override
	public float prixFacture(int quantite) {
		return prixUnitaire*quantite;
		
	}

}
