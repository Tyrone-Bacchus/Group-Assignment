 /*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.Random;

public class GameGUIP extends JFrame
{
	public static void main(String args[])
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex)
        {
       	}

		GameGUIP window = new GameGUIP();
		window.setTitle("Starfall 512: The Rebellion");
		window.setDefaultCloseOperation(GameGUIP.EXIT_ON_CLOSE);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
    }

	public final static ColorPanel myHalf[][] = new ColorPanel[5][7];	//player's territory
	public final static ColorPanel AIHalf[][] = new ColorPanel[5][7];	//AI's territory
	//---------------------------------The Components------------------------------------
	private JButton btnClear = new JButton("Clear Events");
    private JButton btnConfirm = new JButton("Confirm");
    private JButton btnNextDay = new JButton("Next Day");
    private JButton btnStatistics = new JButton("Statistics");
    private JButton btnUnits = new JButton("Units");
    private JButton btnUpgrades = new JButton("Upgrades");
    private JComboBox comboActions = new JComboBox();
    private JLabel lblGold = new JLabel("Gold");
    private JLabel lblActions = new JLabel("Actions");
    private JLabel lblCommand = new JLabel("Commands");
    private JLabel lblGameEvents = new JLabel("Game Events");
    private JLabel lblPopulation = new JLabel("Population");
    private JLabel lblTime = new JLabel("Time");
    private JMenu mnuFile = new JMenu("File");
    private JMenu mnuInfo = new JMenu("Info");
    private JMenuBar barMenu = new JMenuBar();
    private JMenuItem mnuAbout = new JMenuItem("About");
    private JMenuItem mnuHelp = new JMenuItem("Help");
    private JMenuItem mnuNewGame = new JMenuItem("New Game");
    private JMenuItem mnuQuit = new JMenuItem("Quit");
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JSeparator sepHorizontal = new JSeparator();
    private JSeparator sepHorizontal2 = new JSeparator();
    private JSeparator sepVertical1 = new JSeparator();
    private JSeparator sepVertical2 = new JSeparator();
    private JSeparator sepVertical3 = new JSeparator();
    private JTextArea taEvents = new JTextArea("Welcome, Player!");
    private JTextField txtDay = new JTextField("Day #: 1");
    private JTextField txtGold = new JTextField("000000000");
    private JTextField txtPopulation = new JTextField("00000000");
    private JTextField txtTurn = new JTextField("Turn #: 1");
	//-----------------------------------------------------------------------------------

    public GameGUIP()
    {
        //the layout

        //the map generation
        generateMap();

        JPanel westPanel = new JPanel(new GridLayout(5, 7));	//the player's side

		for (int i = 0; i < 5; i++)
		{
			westPanel.add(new ColorPanel(6));

			for (int j = 0; j < 7; j++)
			{
				westPanel.add(myHalf[i][j]);
				myHalf[i][j].addMouseListener(new TestListener(myHalf[i][j], 1));
			}
		}

		JPanel eastPanel = new JPanel(new GridLayout(5, 7));	//the AI's side

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				eastPanel.add(AIHalf[i][j]);
				AIHalf[i][j].addMouseListener(new TestListener(AIHalf[i][j], 2));
			}

			eastPanel.add(new ColorPanel(7));
		}

		JPanel riverPanel = new JPanel(new GridLayout(5, 1));	//if you don't know, leave

		for(int i = 0; i < 5; i++)
		{
			riverPanel.add(new ColorPanel(8));
		}

		//the map
		JPanel mapPanel = new JPanel(new BorderLayout());
		mapPanel.setPreferredSize(new Dimension(816, 240));
		mapPanel.add(westPanel, BorderLayout.WEST);
		mapPanel.add(riverPanel, BorderLayout.CENTER);
		mapPanel.add(eastPanel, BorderLayout.EAST);

		// THIS PART GETS REALLY CRAZY, I SUGGEST NOT EDITING THE LOCATIONS IN THIS PART OR
		// RESIZING THINGS BECAUSE IT COULD DISTORT THE WHOLE GUI. CONTACT ME BEFORE YOU MAKE ANY CHANGES
		// IN THE GUI INTERFACE.

        GroupLayout mapPanelLayout = new GroupLayout(mapPanel);

        mapPanelLayout.setHorizontalGroup
        (
            mapPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
           		.addGap(0, 816, Short.MAX_VALUE)
        );

        mapPanelLayout.setVerticalGroup
        (
            mapPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            	.addGap(0, 240, Short.MAX_VALUE)
        );

        sepVertical1.setOrientation(SwingConstants.VERTICAL);

        comboActions.setModel(new DefaultComboBoxModel(new String[] { "Move To", "Assault", "Scout", "Split" }));

        txtTurn.setEditable(false);
        txtTurn.setForeground(new Color(0, 51, 255));
        txtTurn.setOpaque(false);

        sepVertical2.setOrientation(SwingConstants.VERTICAL);

        txtDay.setEditable(false);
        txtDay.setForeground(new Color(0, 153, 0));
        txtDay.setOpaque(false);

        taEvents.setColumns(20);
        taEvents.setEditable(false);
        taEvents.setRows(5);
        taEvents.setOpaque(false);
        jScrollPane1.setViewportView(taEvents);

        txtGold.setEditable(false);
        txtGold.setForeground(new Color(255, 153, 0));
        txtGold.setOpaque(false);

        txtPopulation.setEditable(false);
        txtPopulation.setOpaque(false);

        sepVertical3.setOrientation(SwingConstants.VERTICAL);

        mnuNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
        mnuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));

        mnuFile.add(mnuNewGame);
        mnuFile.add(mnuQuit);
        mnuInfo.add(mnuAbout);
        mnuInfo.add(mnuHelp);
        barMenu.add(mnuFile);
        barMenu.add(mnuInfo);
        setJMenuBar(barMenu);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(mapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(sepHorizontal, GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnStatistics, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnUnits, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnUpgrades, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addComponent(sepVertical1, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(comboActions, 0, 91, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addComponent(sepVertical2, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTurn)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnNextDay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtDay)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(148, 148, 148)
                                        .addComponent(lblActions)
                                        .addGap(85, 85, 85)
                                        .addComponent(lblTime)))
                                .addGap(120, 120, 120)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblGameEvents)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sepVertical3, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
                                                .addGap(11, 11, 11)
                                                .addComponent(lblGold)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtGold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblPopulation)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPopulation, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(sepHorizontal2, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClear))
                                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                                .addGap(37, 37, 37))))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblCommand)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCommand)
                            .addComponent(lblActions)
                            .addComponent(lblTime))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sepHorizontal, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(comboActions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnConfirm)
                                    .addGap(16, 16, 16))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(sepVertical1, GroupLayout.Alignment.LEADING)
                                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnUpgrades)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnUnits)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnStatistics))))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnNextDay)
                                    .addGap(19, 19, 19)
                                    .addComponent(txtTurn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(sepVertical2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClear)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sepVertical3)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblGameEvents)
                                        .addComponent(lblGold)
                                        .addComponent(txtGold, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPopulation)
                                        .addComponent(txtPopulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sepHorizontal2, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

		// Implementing Listeners
        mnuQuit.addActionListener(new ExitListener());
        // End implementing Listeners
    }

    // Listeners - do not modify
    private class ExitListener implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
			System.exit(0);
		}
	}

	private static class TestListener extends MouseAdapter
	{
		ColorPanel panel;
		String side;

		public TestListener(ColorPanel p, int s)
		{
			panel = p;

			if (s == 1)
			{
				side  = " on the player's side.";
			}
			else
			{
				side  = " on the Ai's side.";
			}
		}

		public void mouseClicked(MouseEvent e)
		{
			JOptionPane.showMessageDialog(null, panel.getTile() + side);
		}
	}

	// End Listeners
	public static void generateMap()
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
						myHalf[i][j] = new ColorPanel(1);

						/*below is to make plains around each city
						 *try-catch in case of arrayOutOfBoundError!*/

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i - 1][j] = new ColorPanel(3);
							}
							else
							{
								myHalf[i - 1][j] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i - 1][j + 1] = new ColorPanel(3);
							}
							else
							{
								myHalf[i - 1][j + 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i][j + 1] = new ColorPanel(3);
							}
							else
							{
								myHalf[i][j + 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i + 1][j + 1] = new ColorPanel(3);
							}
							else
							{
								myHalf[i + 1][j + 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i + 1][j] = new ColorPanel(3);
							}
							else
							{
								myHalf[i + 1][j] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i + 1][j - 1] = new ColorPanel(3);
							}
							else
							{
								myHalf[i + 1][j - 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i][j - 1] = new ColorPanel(3);
							}
							else
							{
								myHalf[i][j - 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								myHalf[i - 1][j - 1] = new ColorPanel(3);
							}
							else
							{
								myHalf[i - 1][j - 1] = new ColorPanel(5);
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
						AIHalf[i][j + 1] = new ColorPanel(2);

						/*below is to make plains around each city
						 *try-catch in case of arrayOutOfBoundError!*/

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i - 1][j + 1] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i - 1][j + 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i - 1][j + 2] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i - 1][j + 2] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i][j + 2] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i][j + 2] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i + 1][j + 2] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i + 1][j + 2] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i + 1][j + 1] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i + 1][j + 1] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i + 1][j] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i + 1][j] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);

							if(c == 0)
							{
								AIHalf[i][j] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i][j] = new ColorPanel(5);
							}
						}
						catch(Exception ex){}

						try
						{
							c = gen.nextInt(2);
							if(c == 0)
							{
								AIHalf[i - 1][j] = new ColorPanel(3);
							}
							else
							{
								AIHalf[i - 1][j] = new ColorPanel(5);
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
			myHalf[i][6] = new ColorPanel(5);

			for (int j = 0; j < 6; j++)
			{
				int value = gen.nextInt(7);

				if (myHalf[i][j] == null)
				{
					if (value < 4)
					{
						myHalf[i][j] = new ColorPanel(3);
					}
					else if (value < 6 && uncrossable > 0)
					{
						c = gen.nextInt(2);

						if(c == 0)
						{
							myHalf[i][j] = new ColorPanel(4);
						}
						else
						{
							myHalf[i][j] = new ColorPanel(9);
						}

						uncrossable--;
					}
					else	//in case lakes is chosen, and the max has been reached
					{
						myHalf[i][j] = new ColorPanel(5);
					}
				}
			}
		}

		uncrossable = 3;
		//generate other enemy tiles
		for (int i = 0; i < 5; i++)
		{
			AIHalf[i][0] = new ColorPanel(5);

			for (int j = 1; j < 7; j++)
			{
				int value = gen.nextInt(7);

				if (AIHalf[i][j] == null)
				{
					if (value < 4)
					{
						AIHalf[i][j] = new ColorPanel(3);
					}
					else if (value < 6 && uncrossable > 0)
					{
						c = gen.nextInt(2);

						if(c == 0)
						{
							AIHalf[i][j] = new ColorPanel(4);
						}
						else
						{
							AIHalf[i][j] = new ColorPanel(9);
						}

						uncrossable--;
					}
					else	//in case lakes is chosen, and the max has been reached
					{
						AIHalf[i][j] = new ColorPanel(5);
					}
				}
			}
		}
	}
}
