/*
 * TaxDemoView.java
 */

package com.mortaneous.patterns.strategy;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class TaxDemoView extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 764663435101176011L;
	
	private JMenuBar menuBar;
	private JMenu systemMenu;
	private JMenuItem systemExitMI;
	
	private JLabel amountLabel;
	private JTextField amountTextField;
	private JLabel stateLabel;
	private JComboBox<String> stateCombo;
	private JLabel taxLabel;
	private JTextField taxTextField;
	private JButton calculateButton;
	
	public TaxDemoView()
	{
		setTitle("Tax Demo");
		setSize(320, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createMenu();
		createLayout();
	}
	
	public void createMenu()
	{
		systemExitMI = new JMenuItem("Exit", KeyEvent.VK_X);
		systemExitMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		systemExitMI.addActionListener(this);
		
		systemMenu = new JMenu("System");
		systemMenu.setMnemonic(KeyEvent.VK_S);
		systemMenu.add(systemExitMI);

		menuBar = new JMenuBar();
		menuBar.add(systemMenu);
		setJMenuBar(menuBar);
	}
	
	public void createLayout()
	{
		amountLabel = new JLabel("Amount ($)");

		amountTextField = new JTextField();
		//amountTextField.setSize(new Dimension(250, (int)(amountTextField.getPreferredSize().getHeight())));

		stateLabel = new JLabel("State");

		stateCombo = new JComboBox<String>();

		taxLabel = new JLabel("Tax");

		taxTextField = new JTextField();
		taxTextField.setEditable(false);

		calculateButton = new JButton("<< Calculate");
		
		Container pane = getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//
		// horizontal group
		//
		GroupLayout.SequentialGroup hori = layout.createSequentialGroup();
		hori.addGroup(layout.createParallelGroup()
				.addComponent(amountLabel)
				.addComponent(stateLabel)
				.addComponent(taxLabel))
			.addGap(20)
			.addGroup(layout.createParallelGroup()
				.addComponent(amountTextField)
				.addComponent(stateCombo)
				.addComponent(taxTextField))
			.addGap(5)
			.addComponent(calculateButton);
		layout.setHorizontalGroup(hori);
		//GroupLayout.ParallelGroup hori = layout.createParallelGroup();
		
		
		//
		// vertical group
		//
		GroupLayout.SequentialGroup vert = layout.createSequentialGroup();
		vert.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
				.addComponent(amountLabel)
				.addComponent(amountTextField))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
				.addComponent(stateLabel)
				.addComponent(stateCombo))
			.addPreferredGap(UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, true)
				.addComponent(taxLabel)
				.addComponent(taxTextField)
				.addComponent(calculateButton));
		layout.setVerticalGroup(vert);
		
		//pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == systemExitMI) {
			setVisible(false);
			System.exit(0);
		}
	}
}