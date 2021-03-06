package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import classes.Circle;
import classes.Donut;
import classes.Line;
import classes.Point;
import classes.Rectangle;
import classes.Shape;
import dialogs.DrawCircle;
import dialogs.DrawDonut;
import dialogs.DrawRectangle;
import modifyDialogs.ModifyCircle;
import modifyDialogs.ModifyDonut;
import modifyDialogs.ModifyLine;
import modifyDialogs.ModifyPoint;
import modifyDialogs.ModifyRectangle;
import panels.PanelDrawing;

@SuppressWarnings("serial")
public class Drawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField txtBcColor;
	public static Color shapeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private JTextField txtFillColor;
	private int lastSelected = -1;
	private PanelDrawing pnlDrawing = new PanelDrawing();
	private Point firstClick;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					Drawing frame = new Drawing();
					frame.setLocationRelativeTo(null);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Drawing() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		setResizable(false);
		setTitle("Zoric Nebojsa,IT39/2018");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		final JRadioButton rdbtnSelect = new JRadioButton("Select");
		rdbtnSelect.addItemListener(new ItemListener() {
			
			@SuppressWarnings("deprecation")			
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					pnlDrawing.setCursor(new Cursor(HAND_CURSOR));
				} else {
					pnlDrawing.setCursor(new Cursor(DEFAULT_CURSOR));
				}
			}
		});
		final JRadioButton rdbtnDraw = new JRadioButton("Draw");
		pnlDrawing.setLayout(null);
		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing,BorderLayout.CENTER);
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(UIManager.getColor("Button.background"));
		panelNorth.setBorder(null);
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		JPanel subpanelNorthLeft = new JPanel();
		subpanelNorthLeft.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Shapes", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNorth.add(subpanelNorthLeft);
		
		final JToggleButton tgbPoint = new JToggleButton("Point");
		buttonGroup.add(tgbPoint);
		subpanelNorthLeft.add(tgbPoint);
		
		final JToggleButton tgbLine = new JToggleButton("Line");
		buttonGroup.add(tgbLine);
		subpanelNorthLeft.add(tgbLine);
		
		final JToggleButton tgbRectangle = new JToggleButton("Rectangle");
		buttonGroup.add(tgbRectangle);
		subpanelNorthLeft.add(tgbRectangle);
		
		final JToggleButton tgbCircle = new JToggleButton("Circle");
		buttonGroup.add(tgbCircle);
		subpanelNorthLeft.add(tgbCircle);
		
		final JToggleButton tgbDonut = new JToggleButton("Donut");
		buttonGroup.add(tgbDonut);
		subpanelNorthLeft.add(tgbDonut);
		
		JPanel subpanelNorthRight = new JPanel();
		subpanelNorthRight.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Color picker", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNorth.add(subpanelNorthRight);
		
		final JButton btnBackgroundColor = new JButton("Background color");
		btnBackgroundColor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shapeColor = JColorChooser.showDialog(null, "Select outer line color:", shapeColor);
				if(shapeColor == null) {
					shapeColor = Color.BLACK;
					txtBcColor.setBackground(Color.BLACK);
				}
				txtBcColor.setBackground(shapeColor);
			}
		});
		
		JButton btnFillColor = new JButton("Fill color");
		btnFillColor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				fillColor = JColorChooser.showDialog(null, "Select fill color: ", fillColor);
				if(fillColor == null) {
					fillColor = Color.WHITE;
					txtBcColor.setBackground(Color.WHITE);
				}
				txtFillColor.setBackground(fillColor);
				
			}
		});
		
		txtBcColor = new JTextField();
		txtBcColor.setEnabled(true);
		txtBcColor.setEditable(false);
		txtBcColor.setBackground(shapeColor);
		txtBcColor.setColumns(10);		
		txtFillColor = new JTextField();
		txtFillColor.setEditable(false);
		txtFillColor.setColumns(10);
		txtFillColor.setBackground(fillColor);
		GroupLayout gl_subpanelNorthRight = new GroupLayout(subpanelNorthRight);
		gl_subpanelNorthRight.setHorizontalGroup(
		     gl_subpanelNorthRight.createParallelGroup(Alignment.LEADING)
			      .addGroup(gl_subpanelNorthRight.createSequentialGroup()
			           .addGap(5)
			           .addComponent(btnBackgroundColor)
			           .addPreferredGap(ComponentPlacement.RELATED)
			           .addComponent(txtBcColor, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
			           .addPreferredGap(ComponentPlacement.UNRELATED)
			           .addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
			           .addPreferredGap(ComponentPlacement.UNRELATED)
			           .addComponent(txtFillColor, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
			           .addContainerGap())
		);
		gl_subpanelNorthRight.setVerticalGroup(
		     gl_subpanelNorthRight.createParallelGroup(Alignment.LEADING)
			      .addGroup(gl_subpanelNorthRight.createSequentialGroup()
			          .addGap(5)
			          .addGroup(gl_subpanelNorthRight.createParallelGroup(Alignment.BASELINE)
			               .addComponent(txtBcColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			               .addComponent(txtFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			               .addComponent(btnFillColor)
			               .addComponent(btnBackgroundColor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
		);
		subpanelNorthRight.setLayout(gl_subpanelNorthRight);
		
		JPanel panelSouthLeft = new JPanel();
		contentPane.add(panelSouthLeft, BorderLayout.SOUTH);
		
		JPanel subpanelSouth = new JPanel();
		subpanelSouth.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Regime", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelSouthLeft.add(subpanelSouth);
		
		buttonGroup_1.add(rdbtnSelect);
		subpanelSouth.add(rdbtnSelect);
		
		JPanel panelSouthRight = new JPanel();
		panelSouthRight.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSouthLeft.add(panelSouthRight);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lastSelected == -1) {
					JOptionPane.showMessageDialog(null, "No figure is selected!","Error!", JOptionPane.ERROR_MESSAGE, null);
					return;
				} 
				Shape shapeToModify = pnlDrawing.getShapes().get(lastSelected);
				if(shapeToModify instanceof Point) {
					ModifyPoint changePoint = new ModifyPoint();
					changePoint.fillTheFields((Point)shapeToModify);
					changePoint.setVisible(true);
				} else if(shapeToModify instanceof Line) {
					ModifyLine changeLine = new ModifyLine();
					changeLine.fillTheFields((Line)shapeToModify);
					changeLine.setVisible(true);
				} else if(shapeToModify instanceof Rectangle) {
					ModifyRectangle changeRectangle = new ModifyRectangle();
					changeRectangle.fillTheFields((Rectangle)shapeToModify);
					changeRectangle.setVisible(true);
				} else if(shapeToModify instanceof Donut) {
					ModifyDonut changeDonut = new ModifyDonut();
					changeDonut.fillTheFields((Donut)shapeToModify);
					changeDonut.setVisible(true);
				} else if(shapeToModify instanceof Circle) {
					ModifyCircle changeCircle = new ModifyCircle();
					changeCircle.fillTheFields((Circle)shapeToModify);
					changeCircle.setVisible(true);
				} 
				pnlDrawing.repaint();
			}
		});
		panelSouthRight.add(btnModify);		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lastSelected == -1) {
					JOptionPane.showMessageDialog(null, "No figure is selected!","Error!", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(answer == 0 & pnlDrawing.getShapes().size() > lastSelected) {
					pnlDrawing.getShapes().remove(lastSelected);
					pnlDrawing.repaint();
					lastSelected = -1;
				}
			}
		});
		panelSouthRight.add(btnDelete);
		JButton btnClearThePanel = new JButton("Clear the panel");
		btnClearThePanel.addActionListener(new ActionListener() {
		   
			public void actionPerformed(ActionEvent e) {
				
				pnlDrawing.getShapes().clear();
				pnlDrawing.repaint();
			}
		});
		panelSouthRight.add(btnClearThePanel);
		
		pnlDrawing.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				super.mouseClicked(e);
				Point click = new Point(e.getX(),e.getY());
				for(int i =0;i<pnlDrawing.getShapes().size();i++) {
					lastSelected = -1;
					pnlDrawing.getShapes().get(i).setSelected(false);
					pnlDrawing.repaint();
				}
				if(rdbtnSelect.isSelected()) {
					for(int i = 0; i<pnlDrawing.getShapes().size();i++) {
						if(pnlDrawing.getShapes().get(i).contains(e.getX(),e.getY())) {
							lastSelected = i;
						}
					}
					if(lastSelected!=-1) {
						pnlDrawing.getShapes().get(lastSelected).setSelected(true);;
					}
				} else {
					if(tgbPoint.isSelected()) {
						Point p = new Point(e.getX(),e.getY());
						p.setColor(shapeColor);
						pnlDrawing.getShapes().add(p);
					} else if(tgbLine.isSelected()) {
						if(firstClick == null) {
							firstClick = new Point(e.getX(),e.getY());
							return;
						}
						Point secondClick = new Point(e.getX(),e.getY());
						Line l = new Line(firstClick,secondClick,shapeColor);
						pnlDrawing.getShapes().add(l);
						firstClick = null;
					} else if(tgbRectangle.isSelected()) {
						DrawRectangle drawRectangle = new DrawRectangle();
						drawRectangle.setVisible(true);
						
						if(drawRectangle.isOK()) {
							Rectangle r = new Rectangle(e.getX(),e.getY(),Float.parseFloat(drawRectangle.getTxtwidth().getText()),Float.parseFloat(drawRectangle.getTxtHeight().getText()));
							r.setSurfaceColor(fillColor);
							r.setColor(shapeColor);
							pnlDrawing.getShapes().add(r);
						}
					} else if(tgbCircle.isSelected()) {
						DrawCircle drawCircle = new DrawCircle();
						drawCircle.setVisible(true);
						if(drawCircle.isOk()) {
							Circle c = new Circle(click,Integer.parseInt(drawCircle.getTxtRadius().getText()));
							c.setColor(shapeColor);
							c.setSurfaceColor(fillColor);
							pnlDrawing.getShapes().add(c);
						}
					} else if(tgbDonut.isSelected()) {
						DrawDonut drawDonut = new DrawDonut();
						drawDonut.setVisible(true);
						
						if(drawDonut.isOkDonut()) {
							Donut d = new Donut(click,Integer.parseInt(drawDonut.getTxtOuterRadius().getText()),Integer.parseInt(drawDonut.getTxtInnerRadius().getText()));
							d.setColor(shapeColor);
							d.setSurfaceColor(fillColor);
							pnlDrawing.getShapes().add(d);
						}
					}
				}
				pnlDrawing.repaint();
			}
		});
		buttonGroup_1.add(rdbtnDraw);
		subpanelSouth.add(rdbtnDraw);
		rdbtnDraw.setSelected(true);
		tgbPoint.setSelected(true);
	}	
}
