/*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

public class UpgradesGUI extends JFrame
{
	//---------------------------------The Components------------------------------------
	private JCheckBox cbCity1 = new JCheckBox();
    private JCheckBox cbCity11 = new JCheckBox();
    private JCheckBox cbCity2 = new JCheckBox();
    private JCheckBox cbCity22 = new JCheckBox();
    private JCheckBox cbCity3 = new JCheckBox();
    private JCheckBox cbCity33 = new JCheckBox();
    private JCheckBox cbCity4 = new JCheckBox();
    private JCheckBox cbCity44 = new JCheckBox();

	private JLabel lblArchers = new JLabel();
	private JLabel lblWarrios = new JLabel();
	private JLabel lblMages = new JLabel();
	private JLabel lblCost = new JLabel();
	private JLabel lblCost1 = new JLabel();

	private JPanel pnlUnits = new JPanel();
	private JPanel pnlOptions = new JPanel();
	private JPanel pnlCities = new JPanel();

	private JLabel lblCity1 = new JLabel();
	private JLabel lblCity1D = new JLabel();
	private JLabel lblCity1G = new JLabel();

	private JLabel lblCity2 = new JLabel();
	private JLabel lblCity2D = new JLabel();
	private JLabel lblCity2G = new JLabel();

	private JLabel lblCity3 = new JLabel();
	private JLabel lblCity3D = new JLabel();
	private JLabel lblCity3G = new JLabel();

	private JLabel lblCity4 = new JLabel();
	private JLabel lblCity4D = new JLabel();
	private JLabel lblCity4G = new JLabel();

	/*private JCheckBox cbWSwords = new JCheckBox();
	private JCheckBox cbWArmour = new JCheckBox();

	private JCheckBox cbAArrows = new JCheckBox();
	private JCheckBox cbAArmour = new JCheckBox();

	private JCheckBox cbMStaff = new JCheckBox();
	private JCheckBox cbMRobes = new JCheckBox();*/

	private JSeparator lineHorizontal4 = new JSeparator();
	private JSeparator lineHorizontal5 = new JSeparator();

	private JTextField txtCost = new JTextField();
	private JTextField txtCost1 = new JTextField();

	private JButton btnUpgrade = new JButton();
	private JButton btnUpgrade1 = new JButton();

	//JTabbedPane paneUpgrades = new JTabbedPane();
	private JFrame frame = new JFrame();

	//read below to understand
	public CheckListener one = new CheckListener(1);
    public CheckListener two = new CheckListener(2);
	public CheckListener three = new CheckListener(3);
	public CheckListener four = new CheckListener(4);
	public CheckListener five = new CheckListener(5);
	public CheckListener six = new CheckListener(6);
	public CheckListener seven = new CheckListener(7);
	public CheckListener eight = new CheckListener(8);
	//-----------------------------------------------------------------------------------

    public UpgradesGUI()
    {
        /*lblArchers.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblArchers.setText("Archers");

        lblWarrios.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblWarrios.setText("Warriors");

        lblMages.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblMages.setText("Mages");

        cbWSwords.setText("Steel Swords");

        cbAArmour.setText("Leather Armour");

        cbAArrows.setText("Iron Arrows");

        cbWArmour.setText("Iron Armour");

        cbMStaff.setText("Apprentice Staff");

        cbMRobes.setText("Apprentice Robes");

        GroupLayout pnlOptionsLayout = new GroupLayout(pnlOptions);

        pnlOptions.setLayout(pnlOptionsLayout);

        pnlOptionsLayout.setHorizontalGroup
        (
            pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionsLayout.createSequentialGroup()
                .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cbWArmour)
                            .addComponent(cbWSwords)))
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblWarrios)))
                .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cbAArmour)
                            .addComponent(cbAArrows)))
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblArchers)))
                .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cbMStaff)
                            .addComponent(cbMRobes))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlOptionsLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMages)
                        .addGap(44, 44, 44))))
        );

        pnlOptionsLayout.setVerticalGroup
        (
            pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addComponent(cbMRobes)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMStaff))
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addComponent(cbWArmour)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbWSwords))
                    .addGroup(pnlOptionsLayout.createSequentialGroup()
                        .addGroup(pnlOptionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblWarrios)
                            .addComponent(lblArchers)
                            .addComponent(lblMages))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAArmour)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAArrows)))
                .addContainerGap())
        );

        lblCost.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblCost.setText("Total Cost");

        txtCost.setEditable(false);

        btnUpgrade.setText("Upgrade");

        GroupLayout pnlUnitsLayout = new GroupLayout(pnlUnits);

        pnlUnits.setLayout(pnlUnitsLayout);

        pnlUnitsLayout.setHorizontalGroup
        (
            pnlUnitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnitsLayout.createSequentialGroup()
                .addGroup(pnlUnitsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, pnlUnitsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lineHorizontal4, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, pnlUnitsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, pnlUnitsLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(pnlUnitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUnitsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnUpgrade, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCost, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlUnitsLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblCost)))))
                .addContainerGap())
        );

        pnlUnitsLayout.setVerticalGroup
        (
            pnlUnitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnitsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineHorizontal4, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCost)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpgrade)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        paneUpgrades.addTab("Units", pnlUnits);*/

        lblCity1.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblCity1.setText("Valkarth");

        cbCity1.setText("Stone Wall");

        cbCity11.setText("Marketplace");

        lblCity1G.setText("Gold : 1.0");

        lblCity1D.setText("Def   : 1.0");

        lblCity2.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblCity2.setText("The Rim");

        cbCity22.setText("Marketplace");

        cbCity2.setText("Stone Wall");

        lblCity2G.setText("Gold : 1.0");

        lblCity2D.setText("Def   : 1.0");

        lblCity3.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblCity3.setText("Dither Elm");

       	cbCity33.setText("MarketPlace");

        cbCity3.setText("Stone Wall");

        lblCity3G.setText("Gold : 1.0");

        lblCity3D.setText("Def   : 1.0");

		lblCity4.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblCity4.setText("Balroth");

		cbCity44.setText("Marketplace");

        cbCity4.setText("Stone Wall");

        lblCity4G.setText("Gold : 1.0");

        lblCity4D.setText("Def   : 1.0");

        lblCost1.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        lblCost1.setText("Total Cost");

        txtCost1.setEditable(false);
        txtCost1.setHorizontalAlignment(JTextField.CENTER);
        txtCost1.setText("0");

        btnUpgrade1.setText("Upgrade");

        GroupLayout pnlCitiesLayout = new GroupLayout(pnlCities);

        pnlCities.setLayout(pnlCitiesLayout);

        pnlCitiesLayout.setHorizontalGroup
        (
            pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCitiesLayout.createSequentialGroup()
                .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlCitiesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lineHorizontal5, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.LEADING, pnlCitiesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(cbCity11)
                                .addComponent(lblCity1)
                                .addComponent(cbCity1)
                                .addComponent(lblCity1D)
                                .addComponent(lblCity1G))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(cbCity22)
                                .addComponent(lblCity2)
                                .addComponent(cbCity2)
                                .addComponent(lblCity2D)
                                .addComponent(lblCity2G))
                            .addGap(12, 12, 12)
                            .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(cbCity33)
                                .addComponent(lblCity3)
                                .addComponent(cbCity3)
                                .addComponent(lblCity3D)
                                .addComponent(lblCity3G))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                            .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(cbCity44)
                                .addComponent(lblCity4)
                                .addComponent(cbCity4)
                                .addComponent(lblCity4D)
                                .addComponent(lblCity4G))))
                    .addGroup(pnlCitiesLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnUpgrade1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCost1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCitiesLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblCost1)))))
                .addContainerGap())
        );

        pnlCitiesLayout.setVerticalGroup
        (
            pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCitiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCitiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCitiesLayout.createSequentialGroup()
                        .addComponent(lblCity2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity22)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCity2D)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCity2G))
                    .addGroup(pnlCitiesLayout.createSequentialGroup()
                        .addComponent(lblCity3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity33)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCity3D)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCity3G))
                    .addGroup(pnlCitiesLayout.createSequentialGroup()
                        .addComponent(lblCity4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity44)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCity4D)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCity4G))
                    .addGroup(pnlCitiesLayout.createSequentialGroup()
                        .addComponent(lblCity1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity11)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCity1D)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCity1G)))
                .addGap(5, 5, 5)
                .addComponent(lineHorizontal5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCost1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCost1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpgrade1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        //paneUpgrades.addTab("Cities", pnlCities);

        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(pnlCities, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        layout.setVerticalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(pnlCities, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        btnUpgrade1.addActionListener(new UpgradeListener());
		//below needed to be down to access a certain method in each one
        cbCity1.addActionListener(one);
        cbCity11.addActionListener(two);
        cbCity2.addActionListener(three);
        cbCity22.addActionListener(four);
        cbCity3.addActionListener(five);
        cbCity33.addActionListener(six);
        cbCity4.addActionListener(seven);
        cbCity44.addActionListener(eight);
    }

    public class CheckListener implements ActionListener
    {
    	int value = -1;
    	boolean checked;

    	public CheckListener(int choice)
    	{
    		value = choice;
    		checked = false;
    	}

    	public void actionPerformed(ActionEvent e)
    	{
    		try
    		{
	    		int previousAmount = Integer.parseInt(txtCost1.getText());

	    		if(checked == false)
	    		{
	    			txtCost1.setText("" + (previousAmount + GameGUI.finance.getCost(value)));
					checked = true;
	    		}
	    		else
	    		{
	    			txtCost1.setText("" + (previousAmount - GameGUI.finance.getCost(value)));
					checked = false;
	    		}
    		}
    		catch(Exception ex)
    		{
    			JOptionPane.showMessageDialog(null, "The city is destroyed.");
    		}
    	}

    	public void checkedChanged()
    	{
    		if(checked)
    		{
    			checked = false;
    		}
    		else
    		{
    			checked = true;
    		}
    	}
    }

    private class UpgradeListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		try
    		{

    		int x = -1;
			int y = -1;
			ColorPanel panelPointer = null;

			if(GameGUI.finance.getValue() >= Integer.parseInt(txtCost1.getText()))
			{
	    		if(cbCity1.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("Valkarth")).getThisRow();
					y = (ColorPanel.playerTowns.get("Valkarth")).getThisColumn();

	    			double defence = ((double)Math.round(purchase(true, x, y) * 10)) / 10;

	    			one.checkedChanged();

	    			lblCity1D.setText("Def   : " + defence);

	    			if(lblCity1D.getText().equals("Def   : 2.0"))
	    			{
	    				cbCity1.setEnabled(false);
	    			}
	    		}

	    		if(cbCity11.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("Valkarth")).getThisRow();
					y = (ColorPanel.playerTowns.get("Valkarth")).getThisColumn();

	    			double interest = ((double)Math.round(purchase(false, x, y) * 10)) / 10;

	    			two.checkedChanged();

	    			lblCity1G.setText("Gold : " + interest);

	    			if(lblCity1G.getText().equals("Gold : 2.0"))
	    			{
	    				cbCity11.setEnabled(false);
	    			}
	    		}

	    		if(cbCity2.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("The Rim")).getThisRow();
					y = (ColorPanel.playerTowns.get("The Rim")).getThisColumn();

	    			double defence = ((double)Math.round(purchase(true, x, y) * 10)) / 10;

	    			three.checkedChanged();

	    			lblCity2D.setText("Def   : " + defence);

	    			if(lblCity2D.getText().equals("Def   : 2.0"))
	    			{
	    				cbCity2.setEnabled(false);
	    			}
	    		}

	    		if(cbCity22.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("The Rim")).getThisRow();
					y = (ColorPanel.playerTowns.get("The Rim")).getThisColumn();

	    			double interest = ((double)Math.round(purchase(false, x, y) * 10)) / 10;

	    			four.checkedChanged();

	    			lblCity2G.setText("Gold : " + interest);

	    			if(lblCity2G.getText().equals("Gold : 2.0"))
	    			{
	    				cbCity22.setEnabled(false);
	    			}
	    		}

	    		if(cbCity3.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("Dither Elm")).getThisRow();
					y = (ColorPanel.playerTowns.get("Dither Elm")).getThisColumn();

	    			double defence = ((double)Math.round(purchase(true, x, y) * 10)) / 10;

	    			five.checkedChanged();

	    			lblCity3D.setText("Def   : " + defence);

	    			if(lblCity3D.getText().equals("Def   : 2.0"))
	    			{
	    				cbCity3.setEnabled(false);
	    			}
	    		}

	    		if(cbCity33.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("Dither Elm")).getThisRow();
					y = (ColorPanel.playerTowns.get("Dither Elm")).getThisColumn();

	    			double interest = ((double)Math.round(purchase(false, x, y) * 10)) / 10;

	    			six.checkedChanged();

	    			lblCity3G.setText("Gold : " + interest);

	    			if(lblCity3G.getText().equals("Gold : 2.0"))
	    			{
	    				cbCity33.setEnabled(false);
	    			}
	    		}

	    		if(cbCity4.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("Balroth")).getThisRow();
					y = (ColorPanel.playerTowns.get("Balroth")).getThisColumn();

	    			double defence = ((double)Math.round(purchase(true, x, y) * 10)) / 10;

	    			seven.checkedChanged();

	    			lblCity4D.setText("Def   : " + defence);

	    			if(lblCity4D.getText().equals("Def   : 2.0"))
	    			{
	    				cbCity4.setEnabled(false);
	    			}
	    		}

	    		if(cbCity44.isSelected())
	    		{
	    			x = (ColorPanel.playerTowns.get("Balroth")).getThisRow();
					y = (ColorPanel.playerTowns.get("Balroth")).getThisColumn();

	    			double interest = ((double)Math.round(purchase(false, x, y) * 10)) / 10;

	    			eight.checkedChanged();

	    			lblCity4G.setText("Gold  : " + interest);

	    			if(lblCity4G.getText().equals("Gold  : 2.0"))
	    			{
	    				cbCity44.setEnabled(false);
	    			}
	    		}

	    		GameGUI.finance.earn(-(Integer.parseInt(txtCost1.getText())));

	    		cbCity1.setSelected(false);
			    cbCity11.setSelected(false);
			    cbCity2.setSelected(false);
			    cbCity22.setSelected(false);
			    cbCity3.setSelected(false);
			    cbCity33.setSelected(false);
			    cbCity4.setSelected(false);
			    cbCity44.setSelected(false);

			    txtCost1.setText("0");

			    String oldMoney = new String("" + GameGUI.finance.getValue());
				String newMoney = new String("");
				int numberOfChars = oldMoney.length();

				for(int i = (3 + numberOfChars); i < 8; i++)
				{
					newMoney += " ";
				}

				newMoney += oldMoney + " GP";

				GameGUI.lblGold.setText("" + newMoney);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nice try, but you do not have enough gold!");
			}
	    }

    	catch(Exception ex)
    	{
    		JOptionPane.showMessageDialog(null, "City is destroyed");
    	}
    }
    }

    public double purchase(boolean defence, int row, int column)
    {
    	ColorPanel panelPointer = GameGUI.myHalf[row][column];
		double newAmount = -1;

    	if(defence)
    	{
			panelPointer.setLocationDefence(panelPointer.getLocationDefence() + 0.2);
			newAmount = panelPointer.getLocationDefence();
    	}
    	else
    	{
    		panelPointer.setInterestRate(panelPointer.getInterestRate() + 0.2);
    		newAmount = panelPointer.getInterestRate();
    	}

    	return newAmount;
    }
}
