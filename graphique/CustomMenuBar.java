package graphique;

import javax.swing.*;

public class CustomMenuBar extends JMenuBar{

	public CustomMenuBar(String premier, String deuxieme, String troisieme, String sous1, String sous2) {
		JMenu jMenu1, jMenu2, jMenu3, jMenuSous1, jMenuSous2;
		jMenu1 = new JMenu();
		jMenu2 = new JMenu();
		jMenu3= new JMenu();
		jMenuSous1= new JMenu();
		jMenuSous2= new JMenu();
		jMenu1.setText("tous les articles");
        this.add(jMenu1);
        this.add(jMenu2);
        this.add(jMenu3);
        jMenu1.add(jMenuSous1);
        jMenu1.add(jMenuSous2);
        
        this.setVisible(true);
	}

}
