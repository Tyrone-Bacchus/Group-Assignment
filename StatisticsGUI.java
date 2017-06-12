/*By: Coding Team
 *Integrated Software Development
 *CEO: Mr. Ryan
 *Due: Thursday, January 12, 2012 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

public class StatisticsGUI extends JFrame
{
	//---------------------------------The Components------------------------------------
	private JButton btnExit = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JLabel lblStatistics = new JLabel();
    private JSeparator lineHorizontal = new JSeparator();
    public JTextArea taStatistics = new JTextArea();
	//-----------------------------------------------------------------------------------

    public StatisticsGUI()
    {
        lblStatistics.setText("Statistics");

        taStatistics.setColumns(20);
        taStatistics.setEditable(false);
        taStatistics.setRows(5);
        jScrollPane1.setViewportView(taStatistics);

        btnExit.setText("Exit");

        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatistics)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lineHorizontal, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.setVerticalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExit)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lineHorizontal, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatistics))
                        .addGap(10, 10, 10)))
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExit.addActionListener(new ExitListener());
    }

    private class ExitListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		setVisible(false);
    	}
    }
}
