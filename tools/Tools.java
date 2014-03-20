package tools;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;

import metiers.LigneDeCommande;
import structures.UneCommande;

/**
 * @author GruenepterMorane
 */
public class Tools implements Serializable {

	/**
	 * methode de saisie d'un entier après un message qui le demande,
	 * determine si l'entier choisi est bien dans les bornes possible
	 * @param msg
	 * @param inf
	 * @param sup
	 * @return
	 */
	public static int  saisie(String msg, int inf, int sup)
	{
		Scanner sc = new Scanner(System.in); 
		int ent;
		System.out.print(msg);
		do{
			
			ent =sc.nextInt();
			if((ent<= sup)&& (inf <= ent)){
				break;
			}
			System.out.println("la saisie est incorrecte, les bornes sont " + inf+ " , "+ sup);
			
		} while(true) ;
		
		return ent;
		
	}
	
	/**
	 * methode de validation si la cle existe dans une hashtable donnee
	 * @param codeElement
	 * @param tableDesElements
	 * @return
	 */
	public static boolean valider(Integer codeElement, Hashtable tableDesElements)
	{
		return tableDesElements.containsKey(codeElement);
	}
	/**
	 * methode de validation si la cle existe dans une hashtable donnee
	 * @param codeElement
	 * @param tableDesElements
	 * @return
	 */
	public static boolean valider(String codeElement, Hashtable tableDesElements)
	{
		return tableDesElements.containsKey(codeElement);
	}
	/**
	 * methode de validation si la code article existe dans la commande
	 * @param codeArticle
	 * @param UneCommande41 commande
	 * @return
	 */
	public static boolean validerLigneDeCommande(Integer codeArticle, UneCommande commande)
	{
		for(LigneDeCommande ldc : commande.getLesCommandes())
		{
			if(ldc.getCodeArticle()== codeArticle)
			{
				return true;
			}
		}
		return false;
	}
	public static String calculeEspace(String avant, int largeur)
	{
		int temp = avant.length();
		if(temp<largeur){
			temp =largeur-temp;
			for(int i =0; i<temp;i++)
			{
				avant+= " ";
			}
			
		}
		return avant;
	}

	public static boolean validerLigneDeCommande(int codeArticle, UneCommande commande){
			for (LigneDeCommande ldc : commande.getLesCommandes())
			{
				if(ldc.getCodeArticle()== codeArticle)
				{
					return true;
				}
			}
			return false;
	}

}
