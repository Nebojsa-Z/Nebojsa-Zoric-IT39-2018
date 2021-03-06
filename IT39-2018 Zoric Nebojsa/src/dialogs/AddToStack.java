package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import classes.Rectangle;
import frames.Stack;

@SuppressWarnings("serial")
public class AddToStack extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean isOK;
	private boolean remove;
	private Rectangle rec;
	private JButton okButton = new JButton("Insert");
	private JButton btnRemove = new JButton("Remove");
	
	public static void main(String[] args) {
		try {
			final AddToStack dialog = new AddToStack();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			dialog.txtX.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AddToStack() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100,100,330,353);
		setTitle("Add a Rectangle");
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblXCoordinate = new JLabel("X coordinate:");		
		JLabel lblYCoordinate = new JLabel("Y coordinate:");		
		JLabel lblWidth = new JLabel("Height:");		
		JLabel lblHeight = new JLabel("Width:");
		
		txtX = new JTextField();
		txtX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtY.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtHeight.requestFocus();
				}
			}
		});
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtWidth.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtX.requestFocus();
				}
			}
		});
		txtY.setColumns(10);
	    txtWidth = new JTextField();
		txtWidth.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtHeight.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtY.requestFocus();
				}
			}
		});
		txtWidth.setColumns(10);
		txtHeight = new JTextField();
		txtHeight.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					txtX.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_UP) {
					txtWidth.requestFocus();
				}
			}
		});
		txtHeight.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
		    gl_contentPanel.createParallelGroup(Alignment.LEADING)
			    .addGroup(gl_contentPanel.createSequentialGroup()
			        .addContainerGap()
			        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			            .addComponent(lblYCoordinate)
			            .addComponent(lblWidth)
			            .addComponent(lblXCoordinate)
			            .addComponent(lblHeight))
			        .addGap(51)
			        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			        	.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			        .addContainerGap(58, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinate)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinate)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove = true;
					dispose();
				}
			});

			buttonPane.add(btnRemove);
			{
				Stack add = new Stack();
				if(add.isAdding()) {
					okButton.addKeyListener(new KeyAdapter() {
						
						@Override						
						public void keyPressed(KeyEvent e) {
							if(e.getKeyCode() == KeyEvent.VK_ENTER)
							{
								okButton.getAction();
							}
						}
					});
					okButton.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							
							boolean number = true;
							try {
								validate(getTxtX(),getTxtY(),getTxtWidth(),getTxtHeight());
							} catch(NumberFormatException n) {
								number = false;
							}
						if(txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()
								|| txtWidth.getText().trim().isEmpty() || txtHeight.getText().trim().isEmpty()) {
							isOK = false;
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE, null);
						} else if(number == false) {
							JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "Error", JOptionPane.ERROR_MESSAGE, null);
							getToolkit().beep();
							isOK = false;
						} else if(Float.parseFloat(getTxtWidth()) < 0 ||
						Float.parseFloat(getTxtHeight()) < 0 ) {
							JOptionPane.showMessageDialog(null, "Width and height cannot be negative", "Error", JOptionPane.ERROR_MESSAGE, null);
							getToolkit().beep();
							isOK = false;
						} else {
							setRec(new Rectangle(Float.parseFloat(getTxtX()),Float.parseFloat(getTxtY()),Float.parseFloat(getTxtWidth()),Float.parseFloat(getTxtHeight())));
							isOK = true;
							dispose();
						}
						}
						});
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				} else {
					btnRemove.getAction();
					}
				}
				
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void validate(String x, String y, String width, String height) {
		String exp1 ="^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$";
		String exp2 = "^(([1-9])([0-9]+)?([.][0-9]*)?|[.][0-9]+)$";
        if(!x.matches(exp1) || !y.matches(exp1) || !width.matches(exp2) || !height.matches(exp2)){  
        	throw new NumberFormatException();
        }
	}
	
	public String getTxtX() {
		
		return txtX.getText();
	}
	
	public JTextField getTxtXField() {
		
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		
		this.txtX = txtX;
	}

	public String getTxtY() {
		
		return txtY.getText();
	}
	
	public JTextField getTxtYField() {
		
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		
		this.txtY = txtY;
	}

	public String getTxtWidth() {
		
		return txtWidth.getText();
	}
	
	public JTextField getTxtWidthField() {
		
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		
		this.txtWidth = txtWidth;
	}

	public String getTxtHeight() {
		
		return txtHeight.getText();
	}
	
	public JTextField getTxtHeightField() {
		
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		
		this.txtHeight = txtHeight;
	}

	public Rectangle getRec() {
		
		return rec;
	}

	public void setRec(Rectangle rec) {
		
		this.rec = rec;
	}

	public boolean isOK() {
		
		return isOK;
	}

	public void setOK(boolean isOK) {
		
		this.isOK = isOK;
	}

	public boolean isRemove() {
		
		return remove;
	}

	public void setRemove(boolean remove) {
		
		this.remove = remove;
	}

	public JButton getOkButton() {
		
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	
	public JButton getBtnRemove() {
		
		return btnRemove;
	}
	
	public void setbtnRemove(JButton btnRemove) {
		
		this.btnRemove = btnRemove;
	}

	
	
	
}
