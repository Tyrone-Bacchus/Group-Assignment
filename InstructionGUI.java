/*By: Information Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.tree.DefaultMutableTreeNode;
 import javax.swing.tree.DefaultTreeModel;
 import javax.swing.event.TreeSelectionEvent;
 import javax.swing.event.TreeSelectionListener;
 import javax.swing.tree.TreeSelectionModel;

public class InstructionGUI extends JFrame
{
	private JTree tree;
	private JTextArea infoArea = new JTextArea(20, 20);

	public InstructionGUI()
	{
	JPanel eastPanel = new JPanel();
	JPanel westPanel = new JPanel();

	DefaultMutableTreeNode top = new DefaultMutableTreeNode("Instruction Manual");
	createNodes(top);

	infoArea.setLineWrap(true);
	infoArea.setWrapStyleWord(true);

	tree = new JTree(top);
	JScrollPane treeView = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane scroll = new JScrollPane(infoArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	infoArea.setEditable(false);

	westPanel.add(tree);
	eastPanel.add(scroll);

	Container container = getContentPane();
	container.add(eastPanel, BorderLayout.EAST);
	container.add(westPanel, BorderLayout.WEST);

	tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	tree.addTreeSelectionListener(new NodeListener());
	}

	private void createNodes(DefaultMutableTreeNode top)
	{
		DefaultMutableTreeNode folder1 = null;
		DefaultMutableTreeNode folder1content = null;
		DefaultMutableTreeNode folder2 = null;
		DefaultMutableTreeNode folder2content = null;
		DefaultMutableTreeNode folder3 = null;
		DefaultMutableTreeNode folder3content = null;
		DefaultMutableTreeNode folder4 = null;
		DefaultMutableTreeNode folder4content = null;



		//Parent Tree
		folder1 = new DefaultMutableTreeNode("How to Play");
		top.add(folder1);

		folder1content = new DefaultMutableTreeNode("Commands");
		folder1.add(folder1content);

		folder1content = new DefaultMutableTreeNode("Actions");
		folder1.add(folder1content);

		folder1content = new DefaultMutableTreeNode("Time");
		folder1.add(folder1content);


		//Parent
		folder2 = new DefaultMutableTreeNode("Units");
		top.add(folder2);

		//Children of Characters Tree
		folder2content = new DefaultMutableTreeNode("Warriors");
		folder2.add(folder2content);

		folder2content = new DefaultMutableTreeNode("Archers");
		folder2.add(folder2content);

		folder2content = new DefaultMutableTreeNode("Mages");
		folder2.add(folder2content);

		//Parent Tree
		/*folder3 = new DefaultMutableTreeNode("Uprades and Items");
		 *top.add(folder3);*/
		//Parent Tree

		folder4 = new DefaultMutableTreeNode("About");
		top.add(folder4);
	}

	private class NodeListener implements TreeSelectionListener
	{
		public void valueChanged(TreeSelectionEvent e)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			tree.getLastSelectedPathComponent();
			Object nodeValue = null;

			try
			{
				nodeValue = node.getUserObject();
			}
			catch(Exception ex)
			{
			}

			if(node == null)
			{
				infoArea.setText("");
			}

			else if(nodeValue.equals("Instruction Manual"))
			{
				infoArea.setText("This manual is to be read to understand the full feature that are present" +
					" throughout the game.");
			}

			else if(nodeValue.equals("How to Play"))
			{
				infoArea.setText("Read the following sections to familiarize yourself with the gameplay.");
			}

			else if(nodeValue.equals("Commands"))
			{
				infoArea.setText("The 'Commands' Section consists of three buttons: Upgrades, Unit, and Statistics: " +
					"  \n o The ‘Upgrades’ button allows the Player to: set up Markets, improve City Defense/Offence, " +
						"and improve Player Defense/Offense. \n o The ‘Units’ button is another upgrade, but it is " +
							"specifically designed for the Player’s troops. In here, the Player can find and buy certain " +
								"upgrading weaponry/skills/defense systems for the Player’s different troop types which " +
									"include the Warriors, Mages, and Archers.\n o The ‘Statistics’ button opens a " +
										"window that displays various figures such as Player’s money, troop numbers, etc.");

			}

			else if(nodeValue.equals("Actions"))
			{

				infoArea.setText("The 'Actions' Section consists of a list containing three Player actions: " +
									"\n o The ‘Move to’ option allows the player to select troops and move them across the map. " +
										 "The player must select a square in which at least one army member is present and click " +
										 	 "the ‘Confirm’ button to finalize his decision. He/she should then select the " +
										 	 	 "appropriate square either north, south, east , or west of the army’s current " +
										 	 	 	 "position. The user can also battle with this option.\n o The ‘Scout’ option " +
										 	 	 	 	 "is very similar to the ‘move to’ option. With this the player can check " +
										 	 	 	 "his/her immediate surrounding without the risk of battle. The army remains " +
										 	 	 "in its original square.\n o Plainly stated, the ‘Split’ option allows the player " +
										     "to split his army into two parts. After selecting a square that has troops, the player " +
									     "inputs the number of members he/she would like to divide.");

			}

			else if(nodeValue.equals("Time"))
			{
				infoArea.setText("The ‘Time’ tab is self-explanatory and it indicates the number of days passed since you were " +
					 "granted control of the army. It also keeps track of the number of turns per day and each day has a maximum " +
					 	 "of 4 turns. Once this maximum is achieved, the player is forced to click on the ‘next day’ button to " +
					 	 	 "proceed with the game.");
			}

			else if(nodeValue.equals("Units"))
			{
				infoArea.setText("The following sections deal with the units present in the game");
			}

			else if(nodeValue.equals("Warriors"))
			{
			//Text to appear when Warrior node is clicked
				infoArea.setText("Bravest, loyal, and without doubt the fiercest troop in the military, these soldiers "+
					"are the backbone of every successful army. While these troops aren’t the most capable in terms of " +
						"offence, they make it up with their infuriating defence and deadly accuracy. If you want a " +
							"solid base in your army inclusive of the best of the best men, these are them.");
			}

			else if(nodeValue.equals("Archers"))
			{
				infoArea.setText("The Archers are trained at the highest standards at a rigorous university for this " +
					"specific art. They have a medium attack and defense stat. They are well rounded soldiers and are " +
						"useful in all facets of a battle. Use them wisely to inflict damage on your opponent from all " +
							 "sides and bring them crumbling to their knees.");
			}

			else if(nodeValue.equals("Mages"))
			{
				infoArea.setText("Mages are trained at Elemtor Magic Academy, which uses a unique method to teach magic " +
					"at a very rapid pace, saving year’s worth of time. Mages are masters of battle magic, using it to deal "+
						 "massive damage to their foes. However, this comes at a price - a weak and fragile physical body. " +
						 	 "Use these units to wreak havoc with the foe and arrange a meeting with their maker… in no time flat. ");
			}

			else if (nodeValue.equals("About"))
			{
				infoArea.setText("Integrated Software Development (ISD) is a small company that is extremely proficient " +
					"in writing relatively small, helpful programs for bigger companies. And so it made absolute sense to " +
						"accept the offer made from Activision to design and implement a strategy game." +
							" The rationale was that our game is intended to be a sort of ‘tester’ game. If the design of " +
								"our turn-based, conquering game turns out to be a success, Activision will take it" +
									"further and create one of their signature and impressive games by using our underlying " +
										"code and building tremendously on it. We will continue to monitor the progress " +
											 "of the game once it is released to Activision to aid with graphics, logic behind " +
											 	"some of the game play and such. ");
			}
		}
	}
}

