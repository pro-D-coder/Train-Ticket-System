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
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class SelectTrain  extends JInternalFrame implements ActionListener  {
    JPanel select_train,list_of_available_trains;
    JDesktopPane desktop;
    ResultSetTableModel tableModel;
    JLabel source,destination;
    JTextField tf_source,tf_destination;
    JButton btn_check;
    JTable available_trains;
     DefaultTableModel dm=new DefaultTableModel();
     String[] col ={"TrainID","Source","Destination","TotalSeats"};
      private JPanel northPanel = new JPanel();
    //for creating the Center Panel
    private JPanel centerPanel = new JPanel();
    //for creating the label
    private JLabel northLabel = new JLabel("THE LIST FOR THE BUSES");
    //for creating the button
    private JButton printButton;
    //for creating the table
    private JTable table;
    //for creating the TableColumn
    private TableColumn column = null;
    //for creating the JScrollPane
    private JScrollPane scrollPane;
    
    
    

    public SelectTrain() {
        super("Select Train",true,true,true);
        //f = new JFrame();
        select_train = new JPanel();
         list_of_available_trains = new JPanel();
         source = new JLabel("From : ");
         destination = new JLabel("To : ");
           tf_source = new JTextField();
         tf_destination = new JTextField();
         desktop = new JDesktopPane();
         setContentPane(desktop); 
          dm.addColumn("Id");
        dm.addColumn("source");
        dm.addColumn("destination");
        dm.addColumn("total seats");
        dm.setColumnIdentifiers(col);
        available_trains = new JTable(dm);
        //dm.setColumnIdentifiers(col);
         Container cnt = getContentPane();
         btn_check = new JButton("Check");
         source.setBounds(20,20,100,20);
         destination.setBounds(20,60,100,20);
         tf_source.setBounds(120,20,100,20);
         tf_destination.setBounds(120,60,100,20);
          btn_check.setBounds(100,100,80,40);
           
         select_train.add(source);
         select_train.add(tf_source);
         select_train.add(destination);
         select_train.add(tf_destination);
         select_train.add(btn_check);
         list_of_available_trains.setBackground(Color.BLUE);
         //list_of_available_trains.add(available_trains);
         add(select_train);
        
         btn_check.addActionListener(this);
         select_train.setSize(1000,300);
         select_train.setLayout(null);
         
         setVisible(true);
         setLayout(new GridLayout(1,2));
         setSize(600,900);
         
        
        
    }
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_check) {
            JFrame in = new JFrame("list of available trains");
            String Source = tf_source.getText();
            String Destination = tf_destination.getText();
            Connection con = ConnectionProvider.getConnection();
            String Query = "Select trainID,source,destination,numberofseats from trains where source ='" + Source + "' and destination ='"+Destination +"';";
            System.out.println(Query);
           try {
              
            tableModel = new ResultSetTableModel(/*JDBC_DRIVER,*/con, Query);
            //for setting the Query
            try {
                tableModel.setQuery(Query);
            } catch (SQLException sqlException) {
            }
        } catch (ClassNotFoundException classNotFound) {
            System.out.println(classNotFound.toString());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.toString());
        }
         //Container cp = in.getContentPane();
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
        in.add(northPanel);

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
        in.add(centerPanel);
        //add(list_of_available_trains);
        //list_of_available_trains.setLayout(new FlowLayout());
        //list_of_available_trains.setSize(500,500);

        //for adding the actionListener to the button
        /*printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Thread runner = new Thread() {
					public void run() {
						try {
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							//prnJob.setPrintable(new PrintingItems(DEFAULT_QUERY));
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
		});*/
        //for setting the visible to true
           //desk.top.add(b);
           in.setVisible(true);
           in.setSize(600, 600);
        table.addMouseListener(new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int index = table.getSelectedRow();
            String id =   tableModel.getValueAt(index, 0).toString();
            String s = tableModel.getValueAt(index, 1).toString();
            String d = tableModel.getValueAt(index, 2).toString();
            String t = tableModel.getValueAt(index, 3).toString();
            //String selectedCellValue = (String) table.getValueAt(table.getSelectedRow() , table.getSelectedColumn());
            System.out.println(id +" "+s+" "+d+" "+t);
           BookTicket b = new BookTicket(id,s,d,t);
          // desktop.add(b);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override 
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseClicked(MouseEvent e) {
        }
    });
           
        }
        
     }
    public static void main(String s[])  
    {  
           System.out.println("connection"+ ConnectionProvider.getConnection());

        new SelectTrain();
        
    } 
    
}
