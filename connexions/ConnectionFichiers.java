package connexions;

import iPane.ES;

import java.io.*;


public class ConnectionFichiers<TypeStructure> {
	
	private String nomPhysique; //nom du fichier avec lequel on peut travailler
	
	/**
	 * constructeur avec param
	 * @param nomPhysique
	 */
	public ConnectionFichiers(String nomPhysique)
	{
		this.nomPhysique= nomPhysique;
	}
	/**
	 * constructeur par déffaut
	 */
	public ConnectionFichiers()
	{}
	/**
	 * getter du nom physique qui lie le logiciel avec le fichier
	 * @return
	 */
	public String getNomPhysique() {
		return nomPhysique;
	}
	/**
	 * setter du nomPhysique qui lie le logiciel avec le fichier
	 * @param nomPhysique
	 */
	public void setNomPhysique(String nomPhysique) {
		this.nomPhysique = nomPhysique;
	}
	/**
	 * méthode de lecture du fichier qui suvegarde la structure sur laquelle ont veut travailler
	 * @return
	 */
	public TypeStructure lire()
	{
		TypeStructure tab = null;
		try {
			FileInputStream fis = new FileInputStream(this.nomPhysique) ;
			ObjectInputStream fichIn = new ObjectInputStream(fis);
			
			tab = (TypeStructure)fichIn.readObject();
			fichIn.close();
		} catch (FileNotFoundException e ) {
			ES.affiche("lecture fichier","nouveau fichier à créer");
		} catch (IOException e){
			ES.affiche("lecture fichier","problem de lecture");
		} catch (ClassNotFoundException e) {
			ES.affiche("lecture fichier","le type de la structure n'est pas compatible avec le fichier");
		}
		return tab;
	}
	
	/**
	 * méthode d'écriture de la structure qui a été travailler dnas le fichier
	 * consiste à sauvegarder une nouvelle version de cette structure et à éffacer la plus ancienne
	 * @return
	 */
	public void ecrire(TypeStructure tab)
	{
		
		try {
			
			FileOutputStream fos = new FileOutputStream(this.nomPhysique);
			ObjectOutputStream fichOut = new ObjectOutputStream(fos);
			fichOut.writeObject((TypeStructure)tab);
			ES.affiche("écriture fichier","** SAUVEGARDE du FICHIER ** "+nomPhysique);
			fichOut.close();
		} catch (FileNotFoundException e ) {
			ES.affiche("écriture fichier","nouveau fichier à créer");
		} catch (IOException e){
			ES.affiche("écriture fichier","problem d'ecriture");
		} 
	}
	
	


}
