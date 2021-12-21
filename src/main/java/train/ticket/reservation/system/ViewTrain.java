/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket.reservation.system;

/**
 *
 * @author ajv15
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class ViewTrain extends JInternalFrame {

    private JPanel northPanel = new JPanel();
    //for creating the Center Panel
    private JPanel centerPanel = new JPanel();
    //for creating the label
    private JLabel northLabel = new JLabel("THE LIST FOR THE ITEMS");
    //for creating the button
    private JButton printButton;
    //for creating the table
    private JTable table;
    //for creating the TableColumn
    private TableColumn column = null;
    //for creating the JScrollPane
    private JScrollPane scrollPane;

    //for creating an object for the ResultSetTableModel class
    private ResultSetTableModel tableModel;
    private Connection DATABASE_URL = ConnectionProvider.getConnection();
    
    private static final String DEFAULT_QUERY = "select * from trains";

    public ViewTrain() {
        super("All Trains", true, true, true);
        //available_trains.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        available_trains.setFillsViewportHeight(true);
        Container cp = getContentPane();
        try {
            tableModel = new ResultSetTableModel(/*JDBC_DRIVER,*/DATABASE_URL, DEFAULT_QUERY);
            //for setting the Query
            try {
                tableModel.setQuery(DEFAULT_QUERY);
            } catch (SQLException sqlException) {
            }
        } catch (ClassNotFoundException classNotFound) {
            System.out.println(classNotFound.toString());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.toString());
        }
        //for setting the table with the information
        table = new JTable(tableModel);
        //for setting the size for the table
        table.setPreferredScrollableViewportSize(new Dimension(990, 200));
        //for setting the font
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        //for setting the scrollpane to the table
        scrollPane = new JScrollPane(table);

        //for setting the size for the table columns
        northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        //for setting the layout to the panel
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //for adding the label to the panel
        northPanel.add(northLabel);
        //for adding the panel to the container
        cp.add("North", northPanel);

        //for setting the layout to the panel
        centerPanel.setLayout(new BorderLayout());
        //for creating an image for the button
        //ImageIcon printIcon = new ImageIcon(ClassLoader.getSystemResource("images/Print16.gif"));
        //for adding the button to the panel
        printButton = new JButton("print the trains");
        //for setting the tip text
        printButton.setToolTipText("Print");
        //for setting the font to the button
        printButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        //for adding the button to the panel
        centerPanel.add(printButton, BorderLayout.NORTH);
        //for adding the scrollpane to the panel
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        //for setting the border to the panel
        centerPanel.setBorder(BorderFactory.createTitledBorder("Items:"));
        //for adding the panel to the container
        cp.add("Center", centerPanel);

        //for adding the actionListener to the button
        printButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
				Thread runner = new Thread() {
					public void run() {
						try {
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable(new PrintingTrains(DEFAULT_QUERY, DATABASE_URL));
							if (!prnJob.printDialog())
								return;
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							prnJob.print();
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
						catch (PrinterException ex) {
							System.out.println("Printing error: " + ex.toString());
						}
					}
				};
				runner.start();
			}
		});
        //for setting the visible to true
        setVisible(true);
        //to show the frame
        pack();
    }

    public static void main(String s[]) {
        System.out.println("connection" + ConnectionProvider.getConnection());

        new SelectTrain();

    }

}
