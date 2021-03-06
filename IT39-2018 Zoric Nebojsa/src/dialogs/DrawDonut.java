package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DrawDonut extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInnerRadius;
	private JTextField txtOuterRadius;
	private boolean isOkDonut;
	
	public static void main(String[] args) {
		
		try {
			DrawDonut dialog = new DrawDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DrawDonut() {
		
		setTitle("Draw donut");
		setBounds(100, 100, 326, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblInnerRadius = new JLabel("Inner radius:");
		JLabel lblOuterRadius = new JLabel("Outer radius:");
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		txtOuterRadius = new JTextField();
		txtOuterRadius.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
		    gl_contentPanel.createParallelGroup(Alignment.LEADING)
			    .addGroup(gl_contentPanel.createSequentialGroup()
			        .addContainerGap()
			        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			            .addComponent(lblInnerRadius)
			            .addComponent(lblOuterRadius))
			        .addGap(58)
			        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			            .addComponent(txtOuterRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			        .addContainerGap(156, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
		    gl_contentPanel.createParallelGroup(Alignment.LEADING)
			    .addGroup(gl_contentPanel.createSequentialGroup()
			        .addGap(40)
			        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
			            .addComponent(lblInnerRadius)
			            .addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			        .addGap(46)
			        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
			            .addComponent(lblOuterRadius)
			            .addComponent(txtOuterRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			        .addContainerGap(90, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Draw a donut");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Integer.parseInt(txtInnerRadius.getText());
							Integer.parseInt(txtOuterRadius.getText());
						} catch(NumberFormatException exe) {
							JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						}
						if(Integer.parseInt(txtInnerRadius.getText()) < 0 || Integer.parseInt(txtOuterRadius.getText()) < 0) {
							JOptionPane.showMessageDialog(null, "Both fields must contain numbers greater than 0!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else if(txtInnerRadius.getText().trim().equals("") || txtOuterRadius.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "All fields must be filled in!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else if(Integer.parseInt(txtInnerRadius.getText()) >= Integer.parseInt(txtOuterRadius.getText())) {
							JOptionPane.showMessageDialog(null, "Inner radius can't be greater than or equal to the outer radius!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else {
							isOkDonut = true;
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtInnerRadius() {
		
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		
		this.txtInnerRadius = txtInnerRadius;
	}

	public JTextField getTxtOuterRadius() {
		
		return txtOuterRadius;
	}

	public void setTxtOuterRadius(JTextField txtOuterRadius) {
		
		this.txtOuterRadius = txtOuterRadius;
	}

	public boolean isOkDonut() {
		
		return isOkDonut;
	}
	
	public void setOkDonut(boolean isOkDonut) {
		
		this.isOkDonut = isOkDonut;
	}
	

}
