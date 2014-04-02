package metiers;

import java.io.Serializable;



/**
 * une classe abstraite est une classe pour laquelle on ne peut pas créer d'instance
 * donc la classe abstraite seule n'a pas de sens! 
 * aussi la classe abstraite avec une seule
 * classe fille n'a pas de sens...
 * car on veut définir un comportement commun pour les classes qui sont dérivées de la classes abstraite
 * méthodes abstraites- méthodes polymorphes
 * on impose une classe abstraite pour que ce comportement commun soit défini
 * obligatoirement les méthodes dans la classe abstraite doivent être implementer par ses
 * classes filles.
 * 
 * une classe générique
 * public abstract class AbstraitArticle<TypeCode>{
 * private TypeCode;}//comme pour les hashtable
 * TypeCode est un type générique qui peut être différent d'une classe fille à une autre
 * quelquechose de paramètré que l'on pourrait utilisé
 * de la série 5.1 le code de la commande ne serait plus un int
 * 
 * je ne peux pas faire un new pour une classe abstraite  
 *  
 *  
 * @author MG
 *
 */
public abstract class AbstraitArticle<TypeCode> implements Serializable {
	protected TypeCode codeArticle;
	protected String designation;
	protected float prixUnitaire;
	
	/**
	 * Constructeur par defaut
	 */
	public AbstraitArticle()
	{}
	/**
	 * Constructeur avec param
	 * @param c
	 * @param d
	 * @param pu
	 */
	public AbstraitArticle(TypeCode c, String d, float pu)
	{
		this.codeArticle = c;
		this.designation =d;
		this.prixUnitaire = pu;
	}
	/**
	 * getter du code article
	 * @return
	 */
	public TypeCode getCode()
	{
		return codeArticle;
	}
	/**
	 * getter de la designation de l'article
	 * @return
	 */
	public String getDesignation()
	{
		return designation;
	}
	/**
	 * getter du prix unitaires de l'article
	 * @return
	 */
	public double getPrixUnitaires()
	{
		return prixUnitaire;
	}
	/**
	 * setter du code de l'article
	 * @param c
	 */
	public void setCode(TypeCode c)
	{
		this.codeArticle=c;
	}
	/**
	 * setter de la designation de l'article
	 * @param d
	 */
	public void setDesignation(String d)
	{
		this.designation = d;
	}
	/**
	 * setter du prix unitaire de l'article
	 * @param pu
	 */
	public void setPrixUnitaire(float pu)
	{
		this.prixUnitaire=pu;
	}
	/**
	 * Méthode abstraite- implementation obligatoire.
	 * Oblige les classes filles à avoir cette méthode
	 * return String de l'article courant
	 */
	public abstract String toString();
	/**
	 * Méthode abstraite- implementation obligatoire.
	 * Oblige les classes filles à avoir cette méthode
	 * @param quantite
	 * @return
	 */
	public abstract float  prixFacture(int quantite);
	

}
