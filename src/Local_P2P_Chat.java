import javax.swing.*;
import java.awt.*;

public class Local_P2P_Chat extends JFrame{    
	private static final long serialVersionUID = 1L;
	static final String HEADER = "LAN P2P Chat";
	static final String NODE_A_ID = "Node A";
	static final String NODE_B_ID = "Node B";
	static final int BOUNDS_X = 660, BOUNDS_Y = 389, BOUNDS_W = 130, BOUNDS_H = 28;
	
	public static void main(String[] args) {		
		new Local_P2P_Chat(HEADER);
	}
	
	Local_P2P_Chat(String header) {
		super(header);
	    CardLayout nodesCardLayout = new CardLayout(5, 5);
	    JPanel nodesContainer = new JPanel(nodesCardLayout);
	    Node_Panel nodeA = new Node_Panel();
	    Node_Panel  nodeB = new Node_Panel();
	    JButton nodeB_Btn = new JButton("CHANGE NODE");
        JButton nodeA_Btn = new JButton("CHANGE NODE"); 

        nodeA_Btn.setBounds(BOUNDS_X, BOUNDS_Y, BOUNDS_W, BOUNDS_H);
        nodeB_Btn.setBounds(BOUNDS_X, BOUNDS_Y, BOUNDS_W, BOUNDS_H);
        nodeA.add(nodeB_Btn);
        nodeB.add(nodeA_Btn);
        nodesContainer.add(nodeA, NODE_A_ID);       
        nodesContainer.add(nodeB, NODE_B_ID);

        nodeA_Btn.addActionListener(e -> nodesCardLayout.show(nodesContainer, NODE_A_ID));
        nodeB_Btn.addActionListener(e -> nodesCardLayout.show(nodesContainer, NODE_B_ID));

        nodesCardLayout.show(nodesContainer, NODE_A_ID);
        this.add(nodesContainer);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setVisible(true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (this.getWidth() / 2), 
                                      middle.y - (this.getHeight() / 2));
        this.setLocation(newLocation);
	}
}