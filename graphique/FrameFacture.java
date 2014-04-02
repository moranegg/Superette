package graphique;

import javax.swing.JInternalFrame;

public class FrameFacture extends JInternalFrame{
	private javax.swing.JMenu affichage;
    
    private javax.swing.JMenuBar articlesMenu;
    private javax.swing.JMenu creation;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu suppression;
    private JpanelChoix choixPanel;
    
    public FrameFacture() {
        initComponents();
        this.setVisible(true);
        
    }
    
    public void initComponents(){
	    
	    choixPanel = new JpanelChoix();
	    articlesMenu = new javax.swing.JMenuBar();
	    affichage = new javax.swing.JMenu();
	    jMenu1 = new javax.swing.JMenu();
	    jMenu2 = new javax.swing.JMenu();
	    creation = new javax.swing.JMenu();
	    suppression = new javax.swing.JMenu();
	
	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
	    this.setBackground(new java.awt.Color(204, 204, 255));
	    
	
	    choixPanel.setBackground(new java.awt.Color(153, 204, 255));
	    choixPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "choix", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 10), new java.awt.Color(0, 0, 0))); // NOI18N
	    choixPanel.setFont(new java.awt.Font("Calibri Light", 1, 11)); // NOI18N
	
	    javax.swing.GroupLayout choixPanelLayout = new javax.swing.GroupLayout(choixPanel);
	    choixPanel.setLayout(choixPanelLayout);
	    choixPanelLayout.setHorizontalGroup(
	        choixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGap(0, 311, Short.MAX_VALUE)
	    );
	    choixPanelLayout.setVerticalGroup(
	        choixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGap(0, 205, Short.MAX_VALUE)
	    );
	
	    affichage.setText("affichage");
	
	    jMenu1.setText("les commandes");
	    affichage.add(jMenu1);
	
	    jMenu2.setText("les Factures");
	    affichage.add(jMenu2);
	
	    articlesMenu.add(affichage);
	
	    creation.setText("facturation");
	    articlesMenu.add(creation);
	
	    suppression.setText("suppression");
	    articlesMenu.add(suppression);
	
	    this.setJMenuBar(articlesMenu);
	
	    javax.swing.GroupLayout articlesFrameLayout = new javax.swing.GroupLayout(this.getContentPane());
	    this.getContentPane().setLayout(articlesFrameLayout);
	    articlesFrameLayout.setHorizontalGroup(
	        articlesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(choixPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    );
	    articlesFrameLayout.setVerticalGroup(
	        articlesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(choixPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    );
	
	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGap(0, 400, Short.MAX_VALUE)
	        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(30, 30, 30)
	                .addComponent(this)
	                .addGap(31, 31, 31)))
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGap(0, 300, Short.MAX_VALUE)
	        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(13, 13, 13)
	                .addComponent(this)
	                .addGap(14, 14, 14)))
	    );
	
	    pack();
	}// </editor-fold>                        



}
