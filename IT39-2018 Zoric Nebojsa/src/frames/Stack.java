package frames;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import dialogs.AddToStack;

@SuppressWarnings("serial")
public class Stack extends JFrame {

	public static JPanel contentPaneStack;
	private static DefaultListModel<String> dlmStack = new DefaultListModel<String>();
	private boolean adding = true;
	
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					Stack frame = new Stack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Stack() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setResizable(false);
		setTitle("Zoric Nebojsa,IT39/2018");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		contentPaneStack = new JPanel();
		contentPaneStack.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPaneStack);
		JList rectangleStack = new JList();
		
		JButton btnAddToStack = new JButton("Add to stack");
		btnAddToStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToStack add = new AddToStack();
				add.setLocationRelativeTo(null);
				adding = true;
				add.getBtnRemove().setVisible(false);
				add.getOkButton().setVisible(true);
				add.setVisible(true);
				if(add.isOK()) {
					dlmStack.add(0, add.getRec().toStringStack());
				}
			}
		});
		
		JButton btnRemoveFromStack = new JButton("Remove from stack");
		btnRemoveFromStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlmStack.size() == 0) {
					JOptionPane.showMessageDialog(null, "There is no data present in the list!", "Error", JOptionPane.ERROR_MESSAGE, null);
				} else {
					adding = false;
					String[] split = dlmStack.getElementAt(0).toString().split("[:|,|(|)]");
					AddToStack rmv = new AddToStack();
					rmv.setTitle("Remove from stack");
					rmv.setLocationRelativeTo(null);
					rmv.getTxtHeightField().setEditable(false);
					rmv.getTxtXField().setEditable(false);
					rmv.getTxtYField().setEditable(false);
					rmv.getTxtWidthField().setEditable(false);
					rmv.getTxtXField().setText(split[2]);
					rmv.getTxtYField().setText(split[3]);
					rmv.getTxtWidthField().setText(split[8]);
					rmv.getTxtHeightField().setText(split[6]);									
					rmv.getBtnRemove().setVisible(true);
					rmv.getOkButton().setVisible(false);
					rmv.setVisible(true);
					
					if(rmv.isRemove()) {
						dlmStack.remove(0);
					}
					adding = true;
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPaneStack = new GroupLayout(contentPaneStack);
		gl_contentPaneStack.setHorizontalGroup(
		    gl_contentPaneStack.createParallelGroup(Alignment.LEADING)
			    .addGroup(gl_contentPaneStack.createSequentialGroup()
			        .addContainerGap()
			        .addGroup(gl_contentPaneStack.createParallelGroup(Alignment.LEADING)
			            .addGroup(gl_contentPaneStack.createSequentialGroup()
			                .addComponent(btnAddToStack, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
			                .addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
			                .addComponent(btnRemoveFromStack, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
     		            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
			        .addContainerGap())
		);
		gl_contentPaneStack.setVerticalGroup(
		    gl_contentPaneStack.createParallelGroup(Alignment.LEADING)
			    .addGroup(gl_contentPaneStack.createSequentialGroup()
			        .addContainerGap()
			        .addGroup(gl_contentPaneStack.createParallelGroup(Alignment.BASELINE)
			            .addComponent(btnRemoveFromStack, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
			            .addComponent(btnAddToStack, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
			        .addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
			        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
			        .addContainerGap())
		);
		
	    scrollPane.setViewportView(rectangleStack);
		contentPaneStack.setLayout(gl_contentPaneStack);
		rectangleStack.setModel(dlmStack);		
	}

	public static DefaultListModel<String> getDlmStack() {
		return dlmStack;
	}

	public boolean isAdding() {
		
		return adding;
	}
	
	public void setAdding(boolean adding) {
		
		this.adding = adding;
	}
		
}
