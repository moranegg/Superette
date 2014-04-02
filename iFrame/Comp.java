package iFrame;

import java.awt.Component;

import javax.swing.JTextArea;

public class Comp extends Component{
	
	public Comp()
	{
		
	}

	public void add(JTextArea texte) {
		this.add(texte);
		this.isVisible();
		
	}
	
	public void remove()
	{
		this.remove();
	}
}
