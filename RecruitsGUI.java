/*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

public class RecruitsGUI extends JFrame
{
	//---------------------------------The Components------------------------------------
	private JSpinner spinMages = new JSpinner();
	private JSpinner spinArchers = new JSpinner();
	private JSpinner spinWarriors = new JSpinner();

	private JButton btnWarRec = new JButton();
	private JButton btnMagRec = new JButton();
	private JButton btnArcRec = new JButton();

	private JLabel lblWarriors = new JLabel();
	private JLabel lblArchers = new JLabel();
	private JLabel lblMages = new JLabel();

	private JSeparator jSeparator1 = new JSeparator();
	private JSeparator jSeparator2 = new JSeparator();

	private JPanel pnlRecruits = new JPanel();
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
    }
}
