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
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateTrain extends JInternalFrame implements ActionListener {

    JFrame f;
    JPanel p;
    JLabel source, destination, total_seats;
    JTextField tf_source, tf_destination, tf_total_seats;
    JButton btn_update;
    JLabel id;
    JTextField tf_id;
    JButton btn_search;

    public UpdateTrain() {
        super("Add Train", true, true, true);
        //f = new JFrame();
        p = new JPanel();
        source = new JLabel("From : ");
        destination = new JLabel("To : ");
        total_seats = new JLabel(" Total Seats : ");
        tf_source = new JTextField();
        tf_destination = new JTextField();
        tf_total_seats = new JTextField();
        btn_update = new JButton("Update Train");
        id = new JLabel("Train Id : ");
        tf_id = new JTextField();
        btn_search = new JButton("Search Train");
        id.setBounds(20, 20, 100, 30);
        tf_id.setBounds(140, 20, 100, 30);
        btn_search.setBounds(80, 60, 140, 40);
        source.setBounds(20, 100, 100, 30);
        destination.setBounds(20, 140, 100, 30);
        total_seats.setBounds(20, 180, 100, 30);
        tf_source.setBounds(140, 100, 100, 30);
        tf_destination.setBounds(140, 140, 100, 30);
        tf_total_seats.setBounds(140, 180, 100, 30);
        btn_update.setBounds(80, 220, 100, 40);

        p.add(id);
        p.add(tf_id);
        p.add(btn_search);
        p.add(source);
        p.add(destination);
        p.add(total_seats);
        p.add(tf_source);
        p.add(tf_destination);
        p.add(tf_total_seats);
        p.add(btn_update);
        btn_update.addActionListener(this);
        btn_search.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_search) {

            int x = 0;

            Connection con = ConnectionProvider.getConnection();

            String _id = tf_id.getText();

            try {

                PreparedStatement ps = con.prepareStatement("select * from trains where trainID = ?");
                ps.setString(1, _id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tf_source.setText(rs.getString("source"));
                    tf_destination.setText(rs.getString("destination"));
                    tf_total_seats.setText(rs.getString("numberofseats"));
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        //desktop.add(b);
        if (e.getSource() == btn_update) {

            int x = 0;

            Connection con = ConnectionProvider.getConnection();

            String Source = tf_source.getText();
            String Destination = tf_destination.getText();
            String TotalSeats = tf_total_seats.getText();

            try {

                PreparedStatement ps = con.prepareStatement("update trains set source ='" + Source + "',destination ='" + Destination + "',numberofseats ='" + TotalSeats + "'where trainID = " + tf_id.getText());

                ps.executeUpdate();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public static void main(String s[]) {
        System.out.println("connection ; " + ConnectionProvider.getConnection());

        new UpdateTrain();

    }

}