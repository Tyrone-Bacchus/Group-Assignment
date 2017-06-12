 /*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;

 import java.awt.event.*;
 import java.util.*;

public class GameGUI extends JFrame
{
	public static GameGUI game;
	public static UpgradesGUI upgrades;
	public static RecruitsGUI recruits;
	public static StatisticsGUI statistics;
	public static InstructionGUI instruction;

	public static void main(String args[])
    {
        try
        {
        	/* Set the Nimbus look and feel */
	        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	         */
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex)
        {
       	}

		if(game != null)
    	{
    		game.dispose();
    		upgrades.dispose();
    		recruits.dispose();
    		statistics.dispose();
    		instruction.dispose();
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "The year is 512 of the Starfall Age. " +
	       		"A land blessed with a wealth beyond imagination born from the ashes\nof the Starfall that marked the beginning of the new age. " +
	       			"Arcanite, a mineral said to be the essence of\nthe Gods themselves was that wealth. " +
	       				"The mineral emanated with the powers of the Arcane which quickly\ndrew the attention of the many mages from across the land. " +
	       					"Once its applications in weapons were\ndiscovered a race for its collection had begun. " +
	       						"But all blessings come with burdens and curses... The sharing\n of the newfound wealth was uneven and unfair. " +
	       							"And so those left out raised their swords in a rage of jealousy\nand vowed to end the King's tyranny and rule the world with the powers of Arcanite." +
	       								"\n\nYou take a knee as the king begins to speak...\n\nHow many years has it been since the start of all this? " +
	       									"When the land was not yet divided? To this day I regret\nmy actions as a King. " +
	       										"For without me this world may still be united. My wife kidnapped, and the armoury raided\nof a weapon that even the Gods fear. " +
	       											"I had hoped for a resolution without further bloodshed, but my wishes for\npeace have gone unanswered. " +
	       												"So my choices of paths to take have dwindled down to but one. A war to end it all.\nA war to save this world. " +
	       													"A war to save my wife... " +
	       														"I thought myself a wise man, and honourable King worthy\nof the throne, but alas my presumptions got the best of my role as a leader. " +
	       															"And so in this, our darkest time,\nI ask you to lead our people to victory. " +
	       																"I know I have no right to ask this of you for it should be I who performs\nthis task, but I feel that my time has ended. " +
	       																	"So go forth and save our people. And as the fires of battle burn,\nand the war drums keep bellowing their chant, we shall stand firm. " +
	       																		"But be wary as our time is limited,\nfor the Arcanite weapon of legend is brewing a storm that will mark our end unless we stop it.");
    	}

		game = new GameGUI();														upgrades = new UpgradesGUI();
		game.setTitle("Starfall 512: The Rebellion");								upgrades.setTitle("Upgrades");
		game.setDefaultCloseOperation(GameGUI.EXIT_ON_CLOSE);						upgrades.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		game.pack();																upgrades.pack();
		game.setResizable(false);													upgrades.setResizable(false);
		game.setLocationRelativeTo(null);											upgrades.setLocationRelativeTo(null);
		game.setVisible(true);														upgrades.setVisible(false);

		recruits = new RecruitsGUI();												statistics = new StatisticsGUI();
		recruits.setTitle("Recruits");												statistics.setTitle("Statistics");
		recruits.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);			statistics.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		recruits.pack();															statistics.pack();
		recruits.setResizable(false);												statistics.setResizable(false);
		recruits.setLocationRelativeTo(null);										statistics.setLocationRelativeTo(null);
		recruits.setVisible(false);													statistics.setVisible(false);

		instruction = new InstructionGUI();
		instruction.setTitle("Instructions");
		instruction.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		instruction.pack();
		instruction.setResizable(false);
		instruction.setLocationRelativeTo(null);
		instruction.setVisible(false);
    }

	private AIArmy enemy;
    public static ColorPanel myHalf[][];
	public static ColorPanel AIHalf[][];

	public static Store finance;

	public static int dayCounter;
	public static int turnCounter;

	public boolean moveTo, split, scout;
	public int splitTracker = 0;

	//---------------------------------- RECORDS Statistics -----------------------------

	private static StatisticsRecords data = new StatisticsRecords();

	//---------------------------------The Components------------------------------------
	private JButton btnUpgrades = new JButton();
	private JButton btnRecruits = new JButton();
	private JButton btnStatistics = new JButton();
	private JButton btnConfirm = new JButton();
	private JButton btnNextDay = new JButton();
	private JButton btnClearEvents = new JButton();

	private static ColorPanel highlightedPanel = new ColorPanel(0 , -1, -1, false);

	public static JLabel lblGold = new JLabel();
	public static JLabel lblPopulation = new JLabel();

	private JPanel pnlMap = new JPanel(new BorderLayout());
	private JPanel pnlCommands = new JPanel();
	private JPanel pnlActions = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JPanel jPanel1 = new JPanel();

	private JLabel lblDays = new JLabel();
	private JLabel lblTurn = new JLabel();
	private JLabel lblEvents = new JLabel();

	private JMenuItem miNewGame = new JMenuItem();
	private JMenuItem miQuit = new JMenuItem();
	private JMenuItem miHelp = new JMenuItem();
	private JMenuItem miAbout = new JMenuItem();

	private JSeparator daySep = new JSeparator();
	private JSeparator eventsSep = new JSeparator();

	private JMenu mnuFile = new JMenu();
	private JMenu mnuInfo = new JMenu();

	private JComboBox comboActions = new JComboBox();

	private JTabbedPane paneOptions = new JTabbedPane();

	private JScrollPane jScrollPane1 = new JScrollPane();

	private JTextArea taEvents = new JTextArea();

	private JMenuBar barMenu = new JMenuBar();
	//-----------------------------------------------------------------------------------

    public GameGUI()
    {
    	//the instantiation
		dayCounter = 1;
		turnCounter = 1;

		finance = new Store();

    	myHalf = new ColorPanel[5][7];	//player's territory
		AIHalf = new ColorPanel[5][7];	//AI's territory

    	finance = new Store();

        //the map generation
        generateMap();

        JPanel westPanel = new JPanel(new GridLayout(5, 7));	//the player's side

		for (int i = 0; i < 5; i++)
		{
			westPanel.add(new ColorPanel(6, i, 0, true));

			for (int j = 0; j < 7; j++)
			{
				westPanel.add(myHalf[i][j]);
				myHalf[i][j].addMouseListener(new ClickListener(myHalf[i][j]));
			}
		}

		JPanel eastPanel = new JPanel(new GridLayout(5, 7));	//the AI's side

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				eastPanel.add(AIHalf[i][j]);
				AIHalf[i][j].addMouseListener(new ClickListener(AIHalf[i][j]));
			}

			eastPanel.add(new ColorPanel(7, i, 14, false));
		}

		JPanel riverPanel = new JPanel(new GridLayout(5, 1));	//if you don't know, leave

		for(int i = 0; i < 5; i++)
		{
			riverPanel.add(new ColorPanel(8, -1, -1, false));
		}

		//the map
		pnlMap.setPreferredSize(new Dimension(816, 240));
		pnlMap.add(westPanel, BorderLayout.WEST);
		pnlMap.add(riverPanel, BorderLayout.CENTER);
		pnlMap.add(eastPanel, BorderLayout.EAST);

        GroupLayout pnlMapLayout = new GroupLayout(pnlMap);

        btnUpgrades.setText("Upgrades");
        btnUpgrades.setPreferredSize(new Dimension(100, 100));

        btnRecruits.setText("Recruits");
        btnRecruits.setPreferredSize(new Dimension(100, 100));
        btnRecruits.setEnabled(false);

        btnStatistics.setText("Statistics");
        btnStatistics.setPreferredSize(new Dimension(100, 100));

        GroupLayout pnlCommandsLayout = new GroupLayout(pnlCommands);

        pnlCommands.setLayout(pnlCommandsLayout);

        pnlCommandsLayout.setHorizontalGroup
        (
            pnlCommandsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlCommandsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpgrades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecruits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStatistics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlCommandsLayout.setVerticalGroup
        (
            pnlCommandsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pnlCommandsLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCommandsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpgrades, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRecruits, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStatistics, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        paneOptions.addTab("Commands", pnlCommands);

        comboActions.setModel(new DefaultComboBoxModel(new String[] { "Move To", "Scout", "Split" }));

        btnConfirm.setText("Confirm");

        GroupLayout pnlActionsLayout = new GroupLayout(pnlActions);

        pnlActions.setLayout(pnlActionsLayout);

        pnlActionsLayout.setHorizontalGroup
        (
            pnlActionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActionsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConfirm, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(comboActions, GroupLayout.Alignment.LEADING, 0, 315, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlActionsLayout.setVerticalGroup
        (
            pnlActionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboActions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );

        paneOptions.addTab("Actions", pnlActions);

        lblDays.setText("Day : 1");

        btnNextDay.setText("Next Day");

        lblTurn.setText("Turn: 1");

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);

        jPanel3.setLayout(jPanel3Layout);

        jPanel3Layout.setHorizontalGroup
        (
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblDays)
                    .addComponent(daySep, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTurn))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNextDay, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3Layout.setVerticalGroup
        (
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDays)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(daySep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTurn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnNextDay)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        paneOptions.addTab("Time", jPanel3);

        btnClearEvents.setText("Clear Events");

        lblPopulation.setFont(new Font("Courier New", 1, 13)); // NOI18N
        lblPopulation.setForeground(new Color(51, 153, 0));
        lblPopulation.setText(" 28/100 P");

        lblEvents.setText("Game Events");

        taEvents.setColumns(20);
        taEvents.setEditable(false);
        taEvents.setLineWrap(true);
        taEvents.setRows(5);
        taEvents.setText("Welcome Player!\n");
        jScrollPane1.setViewportView(taEvents);

        lblGold.setFont(new Font("Courier New", 1, 13)); // NOI18N
        lblGold.setForeground(new Color(255, 102, 0));
        lblGold.setText(" " + finance.getValue() + " GP");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);

        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup
        (
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eventsSep, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblEvents)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                .addComponent(lblGold)
                                .addGap(18, 18, 18)
                                .addComponent(lblPopulation)
                                .addGap(18, 18, 18)))
                        .addComponent(btnClearEvents))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.setVerticalGroup
        (
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEvents)
                            .addComponent(lblPopulation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGold, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventsSep, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(btnClearEvents, GroupLayout.Alignment.TRAILING))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mnuFile.setText("File");

        miNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
        miNewGame.setText("New Game");
        mnuFile.add(miNewGame);

        miQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
        miQuit.setText("Quit");
        mnuFile.add(miQuit);

        barMenu.add(mnuFile);

        mnuInfo.setText("Info");

        miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
        miHelp.setText("Help");
        mnuInfo.add(miHelp);

        miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        miAbout.setText("About");
        mnuInfo.add(miAbout);

        barMenu.add(mnuInfo);

        setJMenuBar(barMenu);

        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlMap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        layout.setVerticalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, 0, 159, Short.MAX_VALUE)
                    .addComponent(paneOptions, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        enemy = new AIArmy();

        // Implementing Listeners
        miNewGame.addActionListener(new NewGameListener());
        miHelp.addActionListener(new HelpListener());
        miQuit.addActionListener(new ExitListener());
        btnUpgrades.addActionListener(new UpgradesListener());
        btnRecruits.addActionListener(new RecruitsListener());
        btnStatistics.addActionListener(new StatisticsListener());
        btnClearEvents.addActionListener(new ClearListener());
        btnConfirm.addActionListener(new ConfirmListener());
        btnNextDay.addActionListener(new NextDayListener());

        // End implementing Listeners
    }

    // Listeners - do not modify
    private class NewGameListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			ColorPanel.playerArmies = new boolean[5][14];
			ColorPanel.enemyArmies = new boolean[5][14];

			ColorPanel.playerTowns = new HashMap <String, ColorPanel.Coordinates> ();
			ColorPanel.enemyTowns = new HashMap <String, ColorPanel.Coordinates> ();

			String args[] = {};
			main(args);
		}
    }

    private class HelpListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			instruction.setVisible(true);
		}
    }

    private class ExitListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	private class ClickListener extends MouseAdapter
	{
		ColorPanel panel;


		public ClickListener(ColorPanel p)
		{
			panel = p;
		}

		public void mouseClicked(MouseEvent e)
		{
			boolean counterHide = false;

			if(panel.getConfirmed() == true)
			{
				highlightedPanel.setConfirmed();

				turnCounter++;

				int newTotalArmyCount = 0;

				if(moveTo)
				{
					splitTracker = highlightedPanel.getUnitStorage().size();

					highlightedPanel.setPlayerOccupies(false);

					split = true;
					moveTo = false;
				}

				if(scout)
				{
					counterHide = true;

					try
					{
						if(panel.getUnitStorage().peek().isPlayerSide() == false)
						{
							panel.setAIOccupies(true);
						}
					}
					catch(Exception ex)
					{
					}

					scout = false;
				}
				else if(split)
				{
					split(highlightedPanel, panel, splitTracker);

					if(panel.getUnitStorage().peek().isPlayerSide())
					{
						ColorPanel.playerArmies[panel.getRow()][panel.getColumn()] = true;
						ColorPanel.enemyArmies[panel.getRow()][panel.getColumn()] = false;
						panel.setPlayerOccupies(true);
						panel.setAIOccupies(false);
						enemy.removeAITown(panel);
						enemy.removeAIArmy(panel);

						if(panel.getImage() == Resources.bCity)
						{
							String destroyedTown = new String("");

							for(String temp : ColorPanel.enemyTowns.keySet())
							{
								if(panel.getRow() == ColorPanel.enemyTowns.get(temp).getThisRow() &&
									panel.getColumn() == ColorPanel.enemyTowns.get(temp).getThisColumn())
								{
									destroyedTown = temp;
									break;
								}
							}

							ColorPanel.enemyTowns.remove(destroyedTown);
							panel.setImage(Resources.bCityDestroyed);

							finance.earn(2000);

							String amount = new String("" + finance.getValue() + " GP");
							String gold = new String("");

							for(int i = amount.length(); i < 8; i++)
							{
								gold += " ";
							}
							gold += amount;

							lblGold.setText(gold);

							data.setAmountofMoney(gold);
							statistics.taStatistics.setText("" + data.toString());

							if(ColorPanel.enemyTowns.size() == 0)
							{
								gameOver("You Win!");
							}
						}
					}
					else
					{
						ColorPanel.playerArmies[panel.getRow()][panel.getColumn()] = false;
						ColorPanel.enemyArmies[panel.getRow()][panel.getColumn()] = true;
						panel.setPlayerOccupies(false);
						panel.setAIOccupies(false);
					}

					if(highlightedPanel.getUnitStorage().isEmpty())
					{
						ColorPanel.playerArmies[highlightedPanel.getRow()][highlightedPanel.getColumn()] = false;
						highlightedPanel.setPlayerOccupies(false);
					}

					update();

					split = false;
					splitTracker = 0;
				}

				highlightedPanel.setClicked();
				highlightedPanel = new ColorPanel(0, panel.getRow(), panel.getColumn(), false);

				lblTurn.setText("Turn: " + turnCounter);

				if(lblTurn.getText().equals("Turn: 5"))
				{
					btnConfirm.setEnabled(false);
				}
			}

			if(highlightedPanel != panel)
			{
				moveTo = false;
				scout = false;
				split = false;
				splitTracker = 0;

				if(ColorPanel.confirmClicked)
				{
					highlightedPanel.setConfirmed();
				}

				highlightedPanel.setClicked();
				panel.setClicked();
				highlightedPanel = panel;
				taEvents.setText(taEvents.getText() + panel.getTile());

				if(panel.getUnitStorage().size() > 0 && (panel.getUnitStorage().peek().isPlayerSide()
					|| counterHide))
				{
					taEvents.setText(taEvents.getText() + "There are the following units placed here:"
														+ "\nWarrior: " + panel.getUnitStorage().warriorAmount()
														+ "\nArcher: " + panel.getUnitStorage().archerAmount()
														+ "\nMage: " + panel.getUnitStorage().mageAmount() + "\n");
				}

				if(panel.getImage() == Resources.rCity)
				{
					btnRecruits.setEnabled(true);
				}
				else
				{
					btnRecruits.setEnabled(false);
				}
			}
			else
			{
				moveTo = false;
				scout = false;
				split = false;
				splitTracker = 0;

				btnRecruits.setEnabled(false);

				if(ColorPanel.confirmClicked)
				{
					highlightedPanel.setConfirmed();
				}

				panel.setClicked();
				highlightedPanel = new ColorPanel(0, panel.getRow(), panel.getColumn(), false);
			}
		}
	}

    private class UpgradesListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		upgrades.setVisible(true);
    	}
    }

    private class RecruitsListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		recruits.setVisible(true);
    	}
    }

    private class StatisticsListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		statistics.setVisible(true);
    	}
    }

    private class ClearListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			taEvents.setText("");
		}
	}

	private class ConfirmListener implements ActionListener
	{
        public void actionPerformed(ActionEvent e)
       	{
            UnitStorage temp = highlightedPanel.getUnitStorage();
            String option = (String)(comboActions.getSelectedItem());

            if (temp.isEmpty() || !(temp.peek().isPlayerSide()))
            {
                JOptionPane.showMessageDialog(GameGUI.this, "Please select a tile which your army occupies.",
                                              "Invalid Tile Selected", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
				if(option.equals("Move To"))
				{
					moveTo = true;
					scout = false;
					split = false;
				}
				else if(option.equals("Scout"))
				{
					moveTo = false;
					scout = true;
					split = false;
				}
				else if(option.equals("Split"))
				{
					moveTo = false;
					scout = false;
					split = true;

					while(splitTracker < 1)
					{
						try
						{
							splitTracker = Integer.parseInt(
								JOptionPane.showInputDialog(null, "Enter how many units to relocate"));

							if(splitTracker > temp.size())
							{
								splitTracker = 0;
								int tooHigh = 1/0;
							}
						}
						catch(Exception ex)
						{
						}
					}
				}

				highlightedPanel.setConfirmed();
            }
        }
    }

    private class NextDayListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		upgrades.setVisible(false);
    		recruits.setVisible(false);
    		statistics.setVisible(false);
    		instruction.setVisible(false);

    		btnRecruits.setEnabled(false);

			turnCounter = 1;
			dayCounter++;

			data.setNumofDays(dayCounter);
			statistics.taStatistics.setText("" + data.toString());

			lblTurn.setText("Turn: " + turnCounter);
			lblDays.setText("Day : " + dayCounter);

			btnConfirm.setEnabled(true);

			for(int r = 0; r < 5; r++)
			{
				for(int c = 0; c < 14; c++)
				{
					if(ColorPanel.enemyArmies[r][c])
					{
						if(c < 7)
						{
							myHalf[r][c].setAIOccupies(false);
						}
						else
						{
							AIHalf[r][c - 7].setAIOccupies(false);
						}
					}
				}

			}

			finance.incomeTax();


			String amount = new String("" + finance.getValue() + " GP");
			String gold = new String("");



			for(int i = amount.length(); i < 8; i++)
			{
				gold += " ";
			}
			gold += amount;

			lblGold.setText(gold);

			data.setAmountofMoney(gold);
			statistics.taStatistics.setText("" + data.toString());

			enemy.run();
			update();

			if(lblDays.getText().equals("Day : 100"))
			{
				gameOver("Out of Time!");
			}
    	}
    }
	// End Listeners

	public void generateMap()
	{
		Random gen = new Random();

		int mytowns = 4, AItowns = 4, c = 0;

		// generate user towns
		while (true)	//in case 3 or less towns are generated
		{
			for (int i = 0; i < 5; i++)	//rows
			{
				for (int j = 0; j < 6; j++)	//columns
				{
					int value = gen.nextInt(21);

					if (value < 3 && myHalf[i][j] == null)
					{
						mytowns--;
						myHalf[i][j] = new ColorPanel(1, i, j, true);

						/*below is to make plains around each city
						 *try-catch in case of arrayOutOfBoundError!*/

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i - 1][j] = new ColorPanel(3, i - 1, j, true);
							}
							else
							{
								myHalf[i - 1][j] = new ColorPanel(5, i - 1, j, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i - 1][j + 1] = new ColorPanel(3, i - 1, j + 1, true);
							}
							else
							{
								myHalf[i - 1][j + 1] = new ColorPanel(5, i - 1, j + 1, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i][j + 1] = new ColorPanel(3, i, j + 1, true);
							}
							else
							{
								myHalf[i][j + 1] = new ColorPanel(5, i, j + 1, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i + 1][j + 1] = new ColorPanel(3, i + 1, j + 1, true);
							}
							else
							{
								myHalf[i + 1][j + 1] = new ColorPanel(5, i + 1, j + 1, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i + 1][j] = new ColorPanel(3, i + 1, j, true);
							}
							else
							{
								myHalf[i + 1][j] = new ColorPanel(5, i + 1, j, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i + 1][j - 1] = new ColorPanel(3, i + 1, j - 1, true);
							}
							else
							{
								myHalf[i + 1][j - 1] = new ColorPanel(5, i + 1, j - 1, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i][j - 1] = new ColorPanel(3, i, j - 1, true);
							}
							else
							{
								myHalf[i][j - 1] = new ColorPanel(5, i, j - 1, true);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i - 1][j - 1] = new ColorPanel(3, i - 1, j - 1, true);
							}
							else
							{
								myHalf[i - 1][j - 1] = new ColorPanel(5, i - 1, j - 1, true);
							}
						}
						catch(Exception ex){}
					}

					if (!(mytowns > 0))	//break out of columns check
					{
						break;
					}
				}

				if (!(mytowns > 0))	//break out of rows check
				{
					break;
				}
			}

			if (!(mytowns > 0))	//break out of overall loop
			{
				break;
			}
		}

		/*generate enemy towns
		 *same comments for player's side, as it is basically the same code*/
		while (true)
		{
			for (int i = 0; i < 5; i++)
			{
				for (int j = 0; j < 6; j++)
				{
					int value = gen.nextInt(21);

					if (value < 5 && AIHalf[i][j + 1] == null)
					{
						AItowns--;
						AIHalf[i][j + 1] = new ColorPanel(2, i, j + 8, false);

						/*below is to make plains around each city
						 *try-catch in case of arrayOutOfBoundError!*/

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i - 1][j + 1] = new ColorPanel(3, i - 1, j + 8, false);
							}
							else
							{
								AIHalf[i - 1][j + 1] = new ColorPanel(5, i - 1, j + 8, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i - 1][j + 2] = new ColorPanel(3, i - 1, j + 9, false);
							}
							else
							{
								AIHalf[i - 1][j + 2] = new ColorPanel(5, i - 1, j + 9, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i][j + 2] = new ColorPanel(3, i, j + 9, false);
							}
							else
							{
								AIHalf[i][j + 2] = new ColorPanel(5, i, j + 9, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i + 1][j + 2] = new ColorPanel(3, i + 1, j + 9, false);
							}
							else
							{
								AIHalf[i + 1][j + 2] = new ColorPanel(5, i + 1, j + 9, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i + 1][j + 1] = new ColorPanel(3, i + 1, j + 8, false);
							}
							else
							{
								AIHalf[i + 1][j + 1] = new ColorPanel(5, i + 1, j + 8, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i + 1][j] = new ColorPanel(3, i + 1, j + 7, false);
							}
							else
							{
								AIHalf[i + 1][j] = new ColorPanel(5, i + 1, j + 7, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i][j] = new ColorPanel(3, i, j + 7, false);
							}
							else
							{
								AIHalf[i][j] = new ColorPanel(5, i, j + 7, false);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);
							if(c == 0)
							{
								AIHalf[i - 1][j] = new ColorPanel(3, i - 1, j + 7, false);
							}
							else
							{
								AIHalf[i - 1][j] = new ColorPanel(5, i - 1, j + 7, false);
							}
						}
						catch(Exception ex){}
					}

					if (!(AItowns > 0))
					{
						break;
					}
				}

				if (!(AItowns > 0))
				{
					break;
				}
			}

			if (!(AItowns > 0))
			{
				break;
			}
		}

		int uncrossable = 3;
		//generate other player tiles
		for (int i = 0; i < 5; i++)
		{
			myHalf[i][6] = new ColorPanel(5, i, 6, true);

			for (int j = 0; j < 6; j++)
			{
				int value = gen.nextInt(7);

				if (myHalf[i][j] == null)
				{
					if (value < 4)
					{
						myHalf[i][j] = new ColorPanel(3, i, j, true);
					}
					else if (value < 6 && uncrossable > 0 && !diagonal("myHalf", i, j))
					{
						c = gen.nextInt(2);

						if(c == 0)
						{
							myHalf[i][j] = new ColorPanel(4, i, j, true);
						}
						else
						{
							myHalf[i][j] = new ColorPanel(9, i, j, true);
						}

						uncrossable--;
					}
					else	//in case uncrossable is chosen, and the max has been reached
					{
						myHalf[i][j] = new ColorPanel(5, i, j, true);
					}
				}
			}
		}

		uncrossable = 3;
		//generate other enemy tiles
		for (int i = 0; i < 5; i++)
		{
			AIHalf[i][0] = new ColorPanel(5, i, 7, false);

			for (int j = 1; j < 7; j++)
			{
				int value = gen.nextInt(7);

				if (AIHalf[i][j] == null)
				{
					if (value < 4)
					{
						AIHalf[i][j] = new ColorPanel(3, i, j + 7, false);
					}
					else if (value < 6 && uncrossable > 0 && !diagonal("AIHalf", i, j))
					{
						c = gen.nextInt(2);

						if(c == 0)
						{
							AIHalf[i][j] = new ColorPanel(4, i, j + 7, false);
						}
						else
						{
							AIHalf[i][j] = new ColorPanel(9, i, j + 7, false);
						}

						uncrossable--;
					}
					else	//in case lakes is uncrossable, and the max has been reached
					{
						AIHalf[i][j] = new ColorPanel(5, i, j + 7, false);
					}
				}
			}
		}
	}

	public boolean diagonal(String side, int i, int j)
	{
		boolean check;

		if(side.equals("myHalf"))
		{
			check = true;
		}
		else
		{
			check = false;
		}

		try
		{
			if(check && myHalf[i + 1][j - 1].isUncrossable())
			{
				return true;
			}
			else if(!check && AIHalf[i + 1][j - 1].isUncrossable())
			{
				return true;
			}
		}
		catch(Exception ex)
		{
		}

		try
		{
			if(check && myHalf[i + 1][j + 1].isUncrossable())
			{
				return true;
			}
			else if(!check && AIHalf[i + 1][j + 1].isUncrossable())
			{
				return true;
			}
		}
		catch(Exception ex)
		{
		}

		try
		{
			if(check && myHalf[i - 1][j + 1].isUncrossable())
			{
				return true;
			}
			else if(!check && AIHalf[i - 1][j + 1].isUncrossable())
			{
				return true;
			}
		}
		catch(Exception ex)
		{
		}

		try
		{
			if(check && myHalf[i - 1][j - 1].isUncrossable())
			{
				return true;
			}
			else if(!check && AIHalf[i - 1][j - 1].isUncrossable())
			{
				return true;
			}
		}
		catch(Exception ex)
		{
		}

		return false;
	}

	public ColorPanel getHighLightedPanel()
	{
		return highlightedPanel;
	}

	public void split(ColorPanel start, ColorPanel end, int units)
	{
		UnitStorage temp = start.getUnitStorage();
		UnitStorage moved = new UnitStorage();

		for (int i = 1;	i <= units; i++)
		{
			moved.addUnit(temp.remove());
		}

		start.setUnitStorage(temp);	// Updates the UnitStorage in the ColorPanel that the units came from
		merge(moved, end);			// Merge (add 'moved' into what's in 'end') end is destination
	}

	public void merge(UnitStorage added, ColorPanel destination)
	{
		// added = queue being added on
		UnitStorage temp = destination.getUnitStorage();

		if ((temp.size() == 0) || (temp.peek().isPlayerSide() == added.peek().isPlayerSide()))	// If there is no units, or if they are on the same side
		{
			// merges the queue being added with the queue in 'destination'
			while (! added.isEmpty())
			{
				temp.addUnit(added.remove());
			}
		}
		else if (temp.peek().isPlayerSide() != added.peek().isPlayerSide())		// If the units are from opposing sides
		{
			// The units now fight!
			temp = battle(added, temp, destination);
		}

		// Updates the UnitStorage in the panel where the units have moved to
		destination.setUnitStorage(temp);
	}

	public UnitStorage battle(UnitStorage attacker, UnitStorage defender, ColorPanel dest)
	{
		while (! defender.isEmpty())
	 	{
	 		while (! attacker.isEmpty())
			{
				Unit attUnit = attacker.peek();
				Unit defUnit = defender.peek();

				defUnit.setArea(dest.getLocationDefence());
		   		attUnit.attack(defUnit);
		   		defUnit.setArea(1.0);

		 		if (defUnit.getHP() <= 0)
	   			{
	    				defender.remove();
	   				break;
		   		}

		   		defUnit.attack(attUnit);

	   			if (attUnit.getHP() <= 0)
		   		{
	   				attacker.remove();
		    			break;
		   		}
			}

			if (attacker.isEmpty())
			{
				break;
			}
	 	}

	 	if (!defender.isEmpty())
	 	{
	  		return defender;
	 	}

	  	return attacker;
	}

	static public void gameOver(String outcome)
	{
		if(outcome.equals("You Win!"))
		{
			JOptionPane.showMessageDialog(null, "VICTORY\n\nMy heart goes out to you my friend as this war would have been a fools game without you. " +
				"You've saved\nthis world, the lives of our people, and my wife. For this you have my thanks, and my crown. " +
					"Though you\nmay have not realised it, you are worthier a person than I ever could wish to be. " +
						"You have led these\npeople into battle, you should be more than capable to lead them into a bright future.");
		}
		else if(outcome.equals("You Lose!"))
		{
			JOptionPane.showMessageDialog(null, "DEFEAT\n\nI was a fool to trust in you. Now all is lost and it cannot be undone. " +
				"They have secured their victory,\nand thus we are left in ruin. As for you, you are free to leave, but at this point there is no freedom\nto be had. " +
					"No matter where you go it is their land now, and you will be hunted. " +
						"As for me, I shall remain\nhere and greet their scouts with sword in hand and die like the hero you could never become.\n\nGood riddance!");
		}
		else if(outcome.equals("Out of Time!"))
		{
			JOptionPane.showMessageDialog(null, "DEFEAT\n\nAnd so the world was scorched by the great storm brought forth by the mighty Arcanite weapon. " +
				"Your forces\nattempted a swift retreat by were simply burned to ash where they stood. " +
					"You are now alone in this\nnew world, and is only a matter of time before they come for you.");
		}

		ColorPanel.playerArmies = new boolean[5][14];
		ColorPanel.enemyArmies = new boolean[5][14];

		ColorPanel.playerTowns = new HashMap <String, ColorPanel.Coordinates> ();
		ColorPanel.enemyTowns = new HashMap <String, ColorPanel.Coordinates> ();

		String args[] = {};
		main(args);
	}

	public void update()
	{
		int newTotalArmyCount = 0;

		for(int r = 0; r < 5; r++)
		{
			for(int c = 0; c < 14; c++)
			{
				if(ColorPanel.playerArmies[r][c])
				{
					if(c < 7)
					{
						newTotalArmyCount += myHalf[r][c].getUnitStorage().size();
					}
					else
					{
						newTotalArmyCount += AIHalf[r][c - 7].getUnitStorage().size();
					}
				}
			}
		}

		String pop = new String("" + newTotalArmyCount + "/100 P");
		String actPop = new String("");

		for(int i = pop.length(); i < 9; i++)
		{
			actPop += " ";
		}

		actPop += pop;

		lblPopulation.setText(actPop);

		data.setPopulation(newTotalArmyCount);
		statistics.taStatistics.setText("" + data.toString());

	}

	static private class RecruitsGUI extends JFrame
	{
		//---------------------------------The Components------------------------------------
		JTextField spinMages = new JTextField();
		JTextField spinArchers = new JTextField();
		JTextField spinWarriors = new JTextField();

		JButton btnWarRec = new JButton();
		JButton btnMagRec = new JButton();
		JButton btnArcRec = new JButton();

		JLabel lblWarriors = new JLabel();
		JLabel lblArchers = new JLabel();
		JLabel lblMages = new JLabel();

		JSeparator jSeparator1 = new JSeparator();
		JSeparator jSeparator2 = new JSeparator();

		JPanel pnlRecruits = new JPanel();
		//-----------------------------------------------------------------------------------

	    public RecruitsGUI()
	    {
	    	btnWarRec.setText("Recruit");
	        btnMagRec.setText("Recruit");
	        btnArcRec.setText("Recruit");

	        lblWarriors.setFont(new Font("Tahoma", 1, 11)); // NOI18N
	        lblWarriors.setText("Warriors");

	        lblArchers.setFont(new Font("Tahoma", 1, 11)); // NOI18N
	        lblArchers.setText("Archers");

	        lblMages.setFont(new Font("Tahoma", 1, 11)); // NOI18N
	        lblMages.setText("Mages");

	        jSeparator1.setOrientation(SwingConstants.VERTICAL);
			jSeparator2.setOrientation(SwingConstants.VERTICAL);

	        spinWarriors.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	        GroupLayout pnlRecruitsLayout = new GroupLayout(pnlRecruits);

	        pnlRecruits.setLayout(pnlRecruitsLayout);

	        pnlRecruitsLayout.setHorizontalGroup
	        (
	            pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                .addGap(14, 14, 14)
	                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                    .addComponent(lblWarriors)
	                    .addComponent(lblArchers)
	                    .addComponent(lblMages))
	                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                    .addComponent(spinMages)
	                    .addComponent(spinArchers)
	                    .addComponent(spinWarriors, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
	                .addGap(10, 10, 10)
	                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
	                .addGap(14, 14, 14)
	                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                    .addComponent(btnWarRec)
	                    .addComponent(btnArcRec)
	                    .addComponent(btnMagRec))
	                .addContainerGap())
	        );

	        pnlRecruitsLayout.setVerticalGroup
	        (
	            pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(GroupLayout.Alignment.TRAILING, pnlRecruitsLayout.createSequentialGroup()
	                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                    .addComponent(jSeparator1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
	                    .addComponent(jSeparator2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
	                    .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                        .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addComponent(spinWarriors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(btnWarRec))
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                                .addContainerGap()
	                                .addComponent(lblWarriors)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
	                        .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                                .addComponent(btnArcRec)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(btnMagRec))
	                            .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                                        .addGap(4, 4, 4)
	                                        .addComponent(lblArchers))
	                                    .addGroup(pnlRecruitsLayout.createSequentialGroup()
	                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(spinArchers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(pnlRecruitsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addComponent(spinMages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(lblMages))))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());

	        getContentPane().setLayout(layout);

	        layout.setHorizontalGroup
	        (
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(pnlRecruits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        layout.setVerticalGroup
	        (
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(pnlRecruits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );

	        btnWarRec.addActionListener(new RecruitListener("War"));
	        btnMagRec.addActionListener(new RecruitListener("Mag"));
	        btnArcRec.addActionListener(new RecruitListener("Arc"));
	    }

	    private class RecruitListener implements ActionListener
		{
			String unitType = new String("");

			public RecruitListener(String type)
			{
				unitType = type;
			}

			public void actionPerformed(ActionEvent e)
			{
				int amount = -1;

				if(unitType.equals("War"))
				{
					try
					{
						amount = Integer.parseInt(spinWarriors.getText());
					}
					catch(Exception ex)
					{
					}
				}
				else if(unitType.equals("Mag"))
				{
					try
					{
						amount = Integer.parseInt(spinMages.getText());
					}
					catch(Exception ex)
					{
					}
				}
				else if(unitType.equals("Arc"))
				{
					try
					{
						amount = Integer.parseInt(spinArchers.getText());
					}
					catch(Exception ex)
					{
					}
				}

				if(amount <= 0)
				{
					JOptionPane.showMessageDialog(null, "Invalid Transaction.");
					return;
				}

				String currentSize = (lblPopulation.getText().trim()).substring(0, lblPopulation.getText().trim().length() - 6);

				if((Integer.parseInt(currentSize) + amount) > 100)
				{
					JOptionPane.showMessageDialog(null, "Invalid population count.");
					return;
				}

				if (unitType.equals("War") && amount * 100 <= finance.getValue())
				{
					for(int i = 1; i <= amount; i++)
					{
						finance.buyWarrior();
						highlightedPanel.getUnitStorage().addUnit(new Warrior(0, 0, true));
					}

					highlightedPanel.setPlayerOccupies(true);
					ColorPanel.playerArmies[highlightedPanel.getRow()][highlightedPanel.getColumn()] = true;

					String amountW = new String("" + finance.getValue() + " GP");
					String gold = new String("");

					for(int i = amountW.length(); i < 8; i++)
					{
						gold += " ";
					}
					gold += amountW;

					lblGold.setText(gold);

					data.setAmountofMoney(gold);
					statistics.taStatistics.setText("" + data.toString());
				}
				else if (unitType.equals("Arc") && amount * 150 <= finance.getValue())
				{
					for(int i = 1; i <= amount; i++)
					{
						finance.buyArcher();
						highlightedPanel.getUnitStorage().addUnit(new Archer(0, 0, true));
					}

					highlightedPanel.setPlayerOccupies(true);
					ColorPanel.playerArmies[highlightedPanel.getRow()][highlightedPanel.getColumn()] = true;

					String amountA = new String("" + finance.getValue() + " GP");
					String gold = new String("");

					for(int i = amountA.length(); i < 8; i++)
					{
						gold += " ";
					}
					gold += amountA;

					lblGold.setText(gold);

					data.setAmountofMoney(gold);
					statistics.taStatistics.setText("" + data.toString());
				}
				else if (unitType.equals("Mag") && amount * 200 <= finance.getValue())
				{
					for(int i = 1; i <= amount; i++)
					{
						finance.buyMage();
						highlightedPanel.getUnitStorage().addUnit(new Mage(0, 0, true));
					}

					highlightedPanel.setPlayerOccupies(true);
					ColorPanel.playerArmies[highlightedPanel.getRow()][highlightedPanel.getColumn()] = true;

					String amountM = new String("" + finance.getValue() + " GP");
					String gold = new String("");

					for(int i = amountM.length(); i < 8; i++)
					{
						gold += " ";
					}
					gold += amountM;

					lblGold.setText(gold);

					data.setAmountofMoney(gold);
					statistics.taStatistics.setText("" + data.toString());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Can not afford");
				}

				int newTotalArmyCount = 0;

				for(int r = 0; r < 5; r++)
				{
					for(int c = 0; c < 14; c++)
					{
						if(ColorPanel.playerArmies[r][c])
						{
							if(c < 7)
							{
								newTotalArmyCount += myHalf[r][c].getUnitStorage().size();
							}
							else
							{
								newTotalArmyCount += AIHalf[r][c - 7].getUnitStorage().size();
							}
						}
					}
				}

				String pop = new String("" + newTotalArmyCount + "/100 P");
				String actPop = new String("");

				for(int i = pop.length(); i < 9; i++)
				{
					actPop += " ";
				}

				actPop += pop;

				lblPopulation.setText(actPop);

				data.setPopulation(newTotalArmyCount);
				statistics.taStatistics.setText("" + data.toString());

			}
		}
	}
}

