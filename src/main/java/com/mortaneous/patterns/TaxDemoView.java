/*
 * TaxDemoView.java
 */

package com.mortaneous.patterns;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.HashMap;
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
	
	protected JMenuBar menuBar;
	protected JMenu systemMenu;
	protected JMenuItem systemExitMI;
	
	protected JLabel amountLabel;
	protected JTextField amountTextField;
	protected JLabel stateLabel;
	protected JComboBox<String> stateCombo;
	protected JLabel taxLabel;
	protected JTextField taxTextField;
	protected JButton calculateButton;
	
	protected TaxCalculator taxCalculator;
	
	protected Map<String, String> stateCode;
	protected final String MINNESOTA = "Minnesota";
	protected final String CALIFORNIA = "California";
	protected final String NEWYORK = "New York";
	protected final String NEWMEXICO = "New Mexico";
	protected final String TEXAS = "Texas";

	protected Map<String, SalesTaxRate> taxRate;
	protected final SalesTaxRate rateMN = new StateTax(6.5/100, "MN");
	protected final SalesTaxRate rateCA = new StateTax(5.125/100, "CA");
	protected final SalesTaxRate rateNY = new StateTax(0.04, "NY");
	protected final SalesTaxRate rateNM = new StateTax(0.0325, "NM");
	protected final SalesTaxRate rateTX = new StateTax(0.05, "TX");
	
	public TaxDemoView()
	{
		setTitle("Tax Demo");
		setSize(320, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createMenu();
		createLayout();
		
		initializeData();
		
		updateTaxRate();
		updateAmount();
		updateTaxAmount();
	}
	
	protected void createMenu()
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
	
	protected void createLayout()
	{
		amountLabel = new JLabel("Amount ($)");

		amountTextField = new JTextField("0");
		amountTextField.addActionListener(this);
		
		stateLabel = new JLabel("State");

		stateCombo = new JComboBox<String>(new String[] { MINNESOTA,
														  CALIFORNIA,
														  NEWYORK,
														  NEWMEXICO,
														  TEXAS
														});
		stateCombo.addActionListener(this);

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
	
	protected void initializeData()
	{
		stateCode = new HashMap<String, String>();
		stateCode.put(MINNESOTA, "MN");
		stateCode.put(CALIFORNIA, "CA");
		stateCode.put(NEWYORK, "NY");
		stateCode.put(NEWMEXICO, "NM");
		stateCode.put(TEXAS, "TX");
		
		taxRate = new HashMap<String, SalesTaxRate>();
		taxRate.put(MINNESOTA, rateMN);
		taxRate.put(CALIFORNIA, rateCA);
		taxRate.put(NEWYORK, rateNY);
		taxRate.put(NEWMEXICO, rateNM);
		taxRate.put(TEXAS, rateTX);
		
		taxCalculator = new TaxCalculator();
	}
	
	protected void updateTaxRate()
	{
		taxCalculator.setTaxRate(taxRate.get((String)stateCombo.getSelectedItem()));
	}
	
	protected void updateAmount()
	{
		taxCalculator.setAmount(Double.parseDouble(amountTextField.getText()));
	}

	protected void updateTaxAmount()
	{
		taxTextField.setText(String.format("%4.2f", taxCalculator.getTaxAmount()));
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if(source == systemExitMI) {
			setVisible(false);
			System.exit(0);
		}
		else if(source == calculateButton) {
		}
		else if(source == stateCombo) {
			updateTaxRate();
			updateTaxAmount();
		}
		else if(source == amountTextField) {
			updateAmount();
			updateTaxAmount();
		}
		else {
			System.out.println("event.getSource() = " + event.getSource());
		}
	}
}