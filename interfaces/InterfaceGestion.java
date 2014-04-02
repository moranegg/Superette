package interfaces;

public interface InterfaceGestion<TypeObjet1,TypeObjet2, TypeObjet3> {
	
	public void menuGeneral(TypeObjet1 tab1,TypeObjet2 tab2, TypeObjet3 tab3);
	public int menuChoix(TypeObjet1 tab1) throws Exception;
	public void ajouter(TypeObjet1 tab1,TypeObjet2 tab2, TypeObjet3 tab3);
	public void supprimer(TypeObjet1 tab1,TypeObjet2 tab2, TypeObjet3 tab3);
	public void editer(TypeObjet1 tab1,TypeObjet2 tab2, TypeObjet3 tab3);
	

	
}
