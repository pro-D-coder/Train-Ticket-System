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
import javax.swing.*;

public class AddTrain extends JInternalFrame implements ActionListener {
    JPanel p;
    JLabel source, destination, total_seats;
    JTextField tf_source, tf_destination, tf_total_seats;
    JButton btn_add;

    public AddTrain() {
        //f = new JFrame();
        super("Add Train", true, true, true);
        p = new JPanel();
        source = new JLabel("From : ");
        destination = new JLabel("To : ");
        total_seats = new JLabel(" Total Seats : ");
        tf_source = new JTextField();
        tf_destination = new JTextField();
        tf_total_seats = new JTextField();
        btn_add = new JButton("Add Train");
        source.setBounds(20, 20, 100, 30);
        destination.setBounds(20, 60, 100, 30);
        total_seats.setBounds(20, 100, 100, 30);
        tf_source.setBounds(140, 20, 100, 30);
        tf_destination.setBounds(140, 60, 100, 30);
        tf_total_seats.setBounds(140, 100, 100, 30);
        btn_add.setBounds(80, 140, 120, 30);
        p.add(source);
        p.add(destination);
        p.add(total_seats);
        p.add(tf_source);
        p.add(tf_destination);
        p.add(tf_total_seats);
        p.add(btn_add);
        btn_add.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);
        

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add) {

            int x = 0;

            Connection con = ConnectionProvider.getConnection();
            String Source = tf_source.getText();
            String Destination = tf_destination.getText();
            String TotalSeat = tf_total_seats.getText();

            try {

                PreparedStatement ps = con.prepareStatement("insert into trains(source,destination,numberofseats) values(?,?,?)");
                ps.setString(1, Source);
                ps.setString(2, Destination);
                ps.setString(3, TotalSeat);
                ps.executeUpdate();
                x++;
                if (x > 0) {
                    JOptionPane.showMessageDialog(btn_add, "Train  Added Successfully");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(btn_add, "Something went Wrong");
        }
       

    }

}
