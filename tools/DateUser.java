package tools;

import java.io.Serializable;
import java.util.*;
/**
* La classe DateUser reprsente un objet date, qui designe un jour specific en contenant trois entiers le jour, le mois et l'annee de la date. 
* 
* 
* @author Morane Gruenpeter
* @version 13/10/2013
*/
public class DateUser implements Serializable
{
   private int jour;
   private int mois;
   private int annee;
   /**
    *  lesJours est un tableau d'entiers qui nous donne la valeur par defaut de nombre des jours par mois. 
    *  sachant que la case 0 est pour Janvier et la case 11 est pour Decembre.
    */
   static int [] lesJours = {31,28,31,30,31,30,31,31,30,31,30,31};
   /**
    * constructeur n°1- constructeur par defaut. initialize la date à la date d'aujourd'hui.
    * exo1.2- le constructeur par defaut qui cree l'objet Calendar 
    * et initialize la date par defaut etant la date du jour
    * dans calendar les mois commence à 0, donc j'augmente l'entier de cal.get(Calendar.MONTH)+1
    */
   public DateUser()
   {
       Calendar cal = Calendar.getInstance();
       this.jour = cal.get(Calendar.DAY_OF_MONTH);
       this.mois = cal.get(Calendar.MONTH)+1;
       this.annee = cal.get(Calendar.YEAR);
   }
   /**
    * constructeur n°2- constructeur avec parametres.
    * @param int j pour le jour
    * @param int m pour le mois
    * @param int a pour l'annee
    */
   public DateUser(int j, int m, int a)
   {
       this.jour =j;
       this.mois =m;
       this.annee= a;
   }
   /**
    * setter de la valeur jour de l'objet courant avec l'entier en parametre
    * @param int j- nouvelle valeur de jour
    */
   public void setJour(int j)
   {
       this.jour = j;
   }
   /**
    * setter de la valeur mois de l'objet courant avec l'entier en parametre
    * @param int m- nouvelle valeur de mois
    */
   public void setMois(int m)
   {
       this.mois = m;
   }
   /**
    * setter de la valeur annee de l'objet courant avec l'entier en parametre
    * @param int a- nouvelle valeur de l'annee
    */
   public void setAnnee(int a)
   {
       this.annee = a;
   }
   
   /**
    * retourne la valeur du jour de l'objet courant
    * @return int jour
    */
   public int getJour()
   {
       return this.jour;
   }
   
   /**
    * retourne la valeur du mois de l'objet courant
    * @return int mois
    */
   public int getMois()
   {
       return this.mois;
   }
   /**
    * retourne la valeur de l'annee de l'objet courant
    * @return int annee
    */
   public int getAnnee()
   {
       return this.annee;
   }
   /**
    * retourne vrai si les entiers en parametres sont acceptable comme date
    * en retrouvant tous les cas possible des mois de l'annee
    * @param j- pour jour
    * @param m- pour mois
    * @param a- pour annee
    * @return boolean date est correct
    */
   public static boolean validerDate(int j, int m, int a)
   {
       switch (m)
       {
       case 4: case 6 : case 9 : case 11: return (j<31);
           
       case 2: if((anneeBissextile(a) && j<30) || (!(anneeBissextile(a)) && j<29))return true;
               else return false;
       default : return true;
       }
       
       
   }
   /**
    * retourne vrai si l'annee est bissextile et faux sinon.
    * @return boolean si bissextile
    */
   private static boolean anneeBissextile(int annee)
   {
       
       if ((annee%4==0 && !(annee % 100==0)) || annee %400==0)
       {
           return true;
       }
       return false;
   }
   
   /**
    * change l'objet date courant avec la valeur de la date d'hier 
    * 
    */
   public void hierChange()
   {
       if(this.jour>1){
           this.jour --;
           return;
       }
       
       switch (this.mois)
       {
       case 2 : case 4 : case 5 : case 6: case 7 : case 8 : case 9 : case 10: case 11: case 12 :
           {
           this.mois --;
           this.jour = lesJours[this.mois-1];
           } break;
       case 3 : 
           {
           this.mois--;
           if(  !(anneeBissextile(this.annee))) this.jour = lesJours[this.mois-1];
           else this.jour = 29;
           
               
           } break;
       case 1 : 
           {
           this.jour =31;
           this.mois =12;
           this.annee--;
           } break;
       }// fin switch
           
   }
   
   /**
    * retourne une nouvelle date d'hier de l'objet courant en un nouvel objet date
    * 
    * @return DateUser hier
    */
   public DateUser hier()
   {
       int j = this.jour;
       int m = this.mois;
       int a = this.annee;
       DateUser d =  new DateUser(j, m, a);
       d.hierChange();
       return d;
   }
   /**
    * change la valeur de l'objet courant date en valeur de lendemain
    */
   public void lendemainChange()
   {
       switch (mois)
       {
       case 2 : 
       {
           if((!(anneeBissextile(annee)) && (this.jour == 28))||
                                   ((anneeBissextile(annee)) && (this.jour == 29)))
           {
               this.jour =1;
               this.mois ++;
           } else 
           {
               this.jour++;
           }
           
       } break;
       case 12 : 
       {
           if(lesJours[this.mois- 1]== this.jour)
           {
               this.jour =1;
               this.mois =1;
               this.annee ++;
           } 
           else this.jour++; 
       } break;
       default :
       {
           if(lesJours[this.mois- 1]== this.jour)
           {
               this.jour =1;
               this.mois ++;
           } else this.jour ++;
       }break;
       }// fin switch
           
   }
   /**
    * retourne une nouvelle date du lendemain de l'objet courant en un nouvel objet date
    * 
    * @return DateUser lendemain
    */
   public DateUser lendemain()
   {
       int j = this.jour;
       int m = this.mois;
       int a = this.annee;
       DateUser dateDuLendemain=  new DateUser(j, m, a);
       dateDuLendemain.lendemainChange();
       return dateDuLendemain;
   }
   
   
   /**
    * retourne une chaine de caracteres de l'objet date courant
    * @return String
    */
   public String toString()
   {
       return (" " + this.jour +"/"+this.mois +"/"+ this.annee );
   }
   /**
    * retourne vrai si l'objet courent est avant l'objet en parametre et faux sinon.
    * @param DateUser d , la date avec laquelle on compare
    * @return boolean estAvant
    */
   public boolean avant(DateUser d)
   {
       if(this.annee < d.annee){
           return true;
       } else if (this.annee == d.annee){
           if(this.mois < d.mois){
               return true;
           } else if (this.mois== d.mois){
               if(this.jour < d.jour){
                   return true;
               }
           }
       }
       return false;
   }
   /**
    * retourne l'entier qui designe l'age par rapport a la date(de naissance) pointée
    * prenant en consideration le mois et le jour de la naissance.
    * @return int age
    */
   public int age()
   {
       DateUser maintenant= new DateUser();
       if((this.mois > maintenant.getMois()) || (this.mois == maintenant.getMois() && this.jour> maintenant.getJour()))
       {
    	   return (maintenant.annee -this.annee - 1);
       }

       return (maintenant.annee -this.annee);
   }
   /**
    * comparaison de deux date
    */
   public boolean compare(DateUser d){
	   if((this.annee == d.getAnnee())&& (this.mois ==d.getMois())&& ( this.jour== d.getJour())){
		   return true;
	   }
	   return false;
   }
   
   public DateUser ajouterNombreJours(int nbj){
	   DateUser d=this;
	   for(int i=0; i<nbj;i++)
	   {
		  d.lendemain(); 
	   }
	   return d;
   }
}
