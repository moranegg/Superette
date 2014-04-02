package structures;

import interfaces.InterfaceStructure;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import metiers.*;


public class TableArticle implements InterfaceStructure<Integer,AbstraitArticle<Integer>>, Serializable{
	private Hashtable<Integer,AbstraitArticle<Integer>> lesArticles = new Hashtable<Integer,AbstraitArticle<Integer>>();
	
	/**
	 * Constructeur par defaut
	 */
	public TableArticle()
	{
		lesArticles.put(1, new Article(1, "DISK DUR", (float) 50.5));
		lesArticles.put(4, new Article(4, "Carte mère", 1000));
		lesArticles.put(5, new Article(5, "Carte réseau", (float) 24.7));
		lesArticles.put(12, new Article(12, "Boite 100CD", (float) 75.5));
		lesArticles.put(18, new Article(18, "MEMOIRE FLASH", 17));
		lesArticles.put(2, new ArticlePromo(2,"usb",100,15,10));
		lesArticles.put(3, new ArticlePromo(3,"souris verte",20,5,5));
	}
	/**
	 * Constructeur n°2
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public TableArticle(Article a, Article b, Article c, Article d)
	{
		lesArticles = new Hashtable<Integer, AbstraitArticle<Integer>>();
		lesArticles.put(a.getCode(),a);
		lesArticles.put(b.getCode(),b);
		lesArticles.put(c.getCode(),c);
		lesArticles.put(d.getCode(),d);
	}
	public Hashtable<Integer,AbstraitArticle<Integer>> getTable()
	{
		return lesArticles;
	}
	/**
	 * return toutes les lignes du tableau d'articles
	 * !! nouveau concept enumeration!!
	 */
	public String toString()
	{
		String table = "****************Liste des articles en catalogue \n";
		Enumeration<AbstraitArticle<Integer>> em =lesArticles.elements();
		while(em.hasMoreElements())
		{
			AbstraitArticle<Integer> a = em.nextElement();
			table += a.toString() +"\n";
		}
		return table;
	}
	/**
	 * return toutes les lignes du tableau d'articles
	 * !! nouveau concept enumeration!!
	 */
	public String toStringPromo()
	{
		String table = "****************Liste des articles promo en catalogue \n";
		Enumeration<AbstraitArticle<Integer>> em =lesArticles.elements();
		while(em.hasMoreElements())
		{
			AbstraitArticle a = em.nextElement();
			if(a instanceof ArticlePromo)
			{
				table += a.toString() +"\n";
			}
		}
		return table;
	}
	/**
	 * 
	 * @return vrai si il ya des articles en promotion dans la table
	 */
	public boolean existePromo()
	{
		
		Enumeration<AbstraitArticle<Integer>> em =lesArticles.elements();
		while(em.hasMoreElements())
		{
			AbstraitArticle<Integer> a = em.nextElement();
			if(a instanceof ArticlePromo)
			{
				return true;
			}
		}
		return false;
	}
	@Override
	/**
	 * trouve un article de code "code"
	 * @param index
	 * @return
	 */
	public AbstraitArticle<Integer> retourner(Integer code)
	{
		return lesArticles.get(code);
	}
	@Override
	/**
	 * ajout d'un article au tableau d'articles après un test si le code de l'article
	 * aAjouter n'existe pas dans la table des articles
	 * @param aAjouter
	 */
	public void ajouter(AbstraitArticle<Integer> aAjouter)
	{
		lesArticles.put(aAjouter.getCode(), aAjouter);
	}
	@Override
	/**
	 * supprime un article de code "code" du tableau des articles
	 * @param code
	 * @return
	 */
	public void supprimer(Integer code)
	{
		lesArticles.remove(code);
	}
	@Override
	/**
	 * 
	 * @return la taille du tableau
	 */
	public int taille()
	{
		return lesArticles.size();
	}
	/**
	 * retourne toutes les clés de la structure
	 * @return
	 */
	public String cle()
	{
		return lesArticles.keys().toString();
	}
	/**
	 * trouve le codeArticle de valeur la plus elvee dans le tableau d'articles
	 * @return
	 */
	public int codeArticleMax()
	{
		int max = 0;
		for(AbstraitArticle<Integer> a : lesArticles.values())
		{
			if(a.getCode()> max){
				max= a.getCode();
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @param code
	 * @return vrai si l'article de ce code existe
	 */
	public boolean articleExiste(int code)
	{
		return lesArticles.containsKey(code);
	}
}
