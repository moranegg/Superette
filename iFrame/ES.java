package iFrame;
import java.awt.Color;
import java.awt.Component;


import javax.swing.*;

/**
 * 
 * @author GruenpeterMorane
 *
 */
public class ES {
	
	
	/**
	 * méthode d'affichage d'un message dans le frame en param
	 * @param msg
	 * @param titre- le title de la fenêtre 
	 * @param frame
	 */
	public static void  affiche(String titre, String msg) 
	{

        String[] aEcrire= msg.split("\n");
		JTextArea texte = new JTextArea(aEcrire.length,aEcrire[0].length());
		texte.setBackground(Color.white);
		texte.setForeground(Color.BLUE);
		texte.append(msg);
		Comp comp = new Comp();
		comp.add(texte);
		
	}
	/**
	 * méthode de saisie d'un entier  dans une fenêtre de dialogue qui le demande
	 * l'entrée est lu en tant que String et avec la méthode Interger.parse(entrée)
	 * on récupère l'entier entrée.
	 * si  cette entrée n'est pas numérique l'exception NumberFormatException est levée 
	 * et traité dans la méthode en demandant une nouvelle entrée.
	 * si l'entier choisi est bien dans les bornes possible=> il est retourné
	 * sinon une fenètre d"alerte" est affichée et la saisie est redemandée. 
	 * @param titre
	 * @param msg
	 * @param inf
	 * @param sup
	 * @return
	 */
	public static int  saisie(String titre, String msg, int inf, int sup) throws Exception
	{
		String[] aEcrire= msg.split("\n");
		JTextArea texte = new JTextArea(aEcrire.length,aEcrire[0].length());
		texte.setBackground(Color.white);
		texte.append(msg);
		String temp = JOptionPane.showInputDialog(null, texte, titre,JOptionPane.QUESTION_MESSAGE);
		
		int ent;
		do{
			try{
				if (temp == null)
				{
					throw new NullPointerException();	
				}
				ent =Integer.parseInt(temp);
				if((ent<= sup) && (inf <= ent))	
				{
					break;
				}
				JOptionPane.showMessageDialog(null,"Les bornes de saisie sont entre " + inf+ " et "+ sup, titre,JOptionPane.WARNING_MESSAGE);
				temp = JOptionPane.showInputDialog(null,texte , titre,JOptionPane.QUESTION_MESSAGE);
				
			
				
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null,"cette entrée n'est pas un entier", "attention exception",JOptionPane.ERROR_MESSAGE);
				temp = JOptionPane.showInputDialog(null,texte, titre,JOptionPane.QUESTION_MESSAGE);
				
				//throw new NumberFormatException();
				//-- si on veut traiter cette exception au retour, propager en cascade
	
			}//if(JOptionPane.)		
		} while(true);
		return ent;
		
		
		
	}
	
	/**
	 * méthode de saisie d'un float dans une fenêtre de dialogue qui le demande
	 * l'entrée est lu en tant que String et avec la méthode Float.parse(entrée)
	 * on récupère l'entrée en type float.
	 * si  cette entrée n'est pas numérique l'exception NumberFormatException est levée 
	 * et traité dans la méthode en demandant une nouvelle entrée.
	 * si le float choisi est bien dans les bornes possible=> il est retourné
	 * sinon une fenètre d"alerte" est affichée et la saisie est redemandée. 
	 * @param titre
	 * @param msg
	 * @param inf
	 * @param sup
	 * @return
	 */
	public static float  saisie(String titre,String msg, float inf, float sup)  throws NullPointerException
	{
		String[] aEcrire= msg.split("\n");
		JTextArea texte = new JTextArea(aEcrire.length,aEcrire[0].length());
		texte.setBackground(Color.white);
		texte.append(msg);
		String temp = JOptionPane.showInputDialog(null,texte, titre,JOptionPane.QUESTION_MESSAGE);
		float flo;
		
		do{
			try{
				if (temp == null)
				{
					throw new NullPointerException();	
				}
				flo =Float.parseFloat(temp);
				if((flo<= sup)&& (inf <= flo))	
				{
					break;
				}
				affiche(titre ,"Les bornes de saisie sont entre " + inf+ " et "+ sup);
				temp = JOptionPane.showInputDialog(null,texte, titre,JOptionPane.QUESTION_MESSAGE);
			} catch (NumberFormatException  e){
				affiche(titre,"cette entrée n'est pas un entier");
				temp = JOptionPane.showInputDialog(null,texte, titre,JOptionPane.QUESTION_MESSAGE);
			}
			
		} while(true) ;
		return flo;
		
	}

	/**
	 * méthode de saisie dune chaîne de caratères  dans une fenêtre de dialogue qui le demande
	 * sur l'entrée on applique la méthode trim() qui enlève les espaces pour vérifier que l'entrée
	 * saisite n'est pas que des espaces.
	 * si  cette entrée est vide une fenètre d"alerte" est affichée et la saisie est redemandée.
	 * sinon l'entrée est renvoyer.
	 * @param titre
	 * @param msg
	 * @param inf
	 * @param sup
	 * @return
	 */
	public static String  saisie(String titre, String msg) throws NullPointerException
	{
		String[] aEcrire= msg.split("\n");
		JTextArea texte = new JTextArea(aEcrire.length,aEcrire[0].length());
		texte.setBackground(Color.white);
		texte.append(msg);
		String temp;
		do{
			temp = JOptionPane.showInputDialog(null,texte, titre,JOptionPane.QUESTION_MESSAGE).trim();
			
			
			if((temp==null)){
				throw new NullPointerException();	
			}
			
		}while(temp.isEmpty());
		return temp;
	}
	
	public static void main(String [] args) throws Exception
	{
		affiche("titre1","c'est un essaie");
		try {
			int i = saisie("les entiers","un entier svp?", 1, 10);
			affiche("titre2",""+i); 
		}catch(NullPointerException e){
			
		}
		try{
			float f = saisie("les floats","un float svp", 0.1f, 1.1f);
			affiche("titre3",""+f);
		}catch(NullPointerException e){
			
		}
		try{
			String mp = saisie("les strings","saisie d'un String");
			affiche("titre4",mp);
		}catch(NullPointerException e){
			
		}
	}
	
}
