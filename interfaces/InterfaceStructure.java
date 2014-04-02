package interfaces;

import java.io.Serializable;

/**
 *  une interface- variables constante finale et que des méthodes abstraite
 *  le même principe de la classe abstraite mais ce n'est pas un héritage
 *  parce que chaque fois on doit reécrire tous
 * @author MG
 *
 * @param <TypeCle>
 * @param <TypeObjet>
 */
public interface InterfaceStructure<TypeCle,TypeObjet> {
	
	public TypeObjet retourner(TypeCle cle);
	
	public void supprimer(TypeCle cle);
	
	public void ajouter(TypeObjet objet);
	
	public int taille();
	
	
}
