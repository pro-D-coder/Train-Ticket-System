/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket.reservation.system;

/**
 *
 * @author ajv15
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JInternalFrame;
import javax.swing.*;

public class BookTicket extends JFrame implements ActionListener {

    JLabel customer_name, seat_number, numberofseats;
    JTextField txt_c_name;
    JPanel seat_panel;
    JPanel p;
    JLabel source, destination, seatnumber, name, price;
    JTextField tf_source, tf_destination, tf_seatnumber, tf_name, tf_price;
    JButton btn_book;
    String ID;

    public BookTicket(String id, String Source, String Destination, String totalseats) {
        //super("Book Ticket", true, true, true);
        p = new JPanel();
        ID = id;
        source = new JLabel("From : ");
        source.setText(Source);
        destination = new JLabel("To : ");
        destination.setText(Destination);
        name = new JLabel("Customer Name");
        seatnumber = new JLabel(" Enter Seat Number ");
        price = new JLabel("Price : ");
        tf_source = new JTextField();
        tf_name = new JTextField();
        tf_source.setText(Source);
        tf_destination = new JTextField();
        tf_destination.setText(Destination);
        tf_seatnumber = new JTextField();
        tf_price = new JTextField();
        String y = "1000";
        tf_price.setText(y);
        btn_book = new JButton("Book Ticket");
        source.setBounds(20, 20, 100, 30);
        destination.setBounds(20, 60, 100, 30);
        seatnumber.setBounds(20, 100, 100, 30);
        name.setBounds(20, 140, 100, 30);
        price.setBounds(20, 180, 100, 30);
        tf_source.setBounds(140, 20, 100, 30);
        tf_destination.setBounds(140, 60, 100, 30);
        tf_seatnumber.setBounds(140, 100, 100, 30);
        tf_name.setBounds(140, 140, 100, 30);
        tf_price.setBounds(140, 180, 100, 30);

        btn_book.setBounds(80, 220, 120, 30);
        p.add(source);
        p.add(destination);
        p.add(seatnumber);
        p.add(tf_source);
        p.add(tf_destination);
        p.add(tf_seatnumber);
        p.add(btn_book);
        p.add(name);
        p.add(tf_name);
        p.add(price);
        p.add(tf_price);
        btn_book.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);
        //JInternalFrame f = new JInternalFrame();

        setVisible(true);
        setSize(600, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_book) {

            int x = 0;

            Connection con = ConnectionProvider.getConnection();
            String Price = tf_price.getText();
            //String Destination = tf_destination.getText();
            String SeatNumber = tf_seatnumber.getText();
            String CName = tf_name.getText();

            try {

                PreparedStatement ps = con.prepareStatement("insert into ticket(trainID,seatNumber,cName,total_price) values(?,?,?,?)");

                ps.setString(1, ID);
                ps.setString(2, SeatNumber);
                ps.setString(3, CName);
                ps.setString(4, Price);

                ps.executeUpdate();
                x++;
                if (x > 0) {
                    JOptionPane.showMessageDialog(btn_book, "Ticket Book");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(btn_book, "Something went Wrong");
        }

    }

}
