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

public class RemoveTrain extends JInternalFrame implements ActionListener {

    JPanel p;
    JLabel id;
    JTextField tf_id;
    JButton btn_delete;

    public RemoveTrain() {
        super("Add Trains", true, true, true);
        //f =  new JFrame();
        p = new JPanel();
        id = new JLabel("Trains Id : ");
        tf_id = new JTextField();
        btn_delete = new JButton("Delete Trains");
        id.setBounds(20, 20, 100, 30);
        tf_id.setBounds(140, 20, 100, 30);
        btn_delete.setBounds(80, 60, 80, 40);
        p.add(id);
        p.add(tf_id);
        p.add(btn_delete);
        btn_delete.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_delete) {

            Connection con = ConnectionProvider.getConnection();

            try {

                PreparedStatement ps = con.prepareStatement("delete from trains where trainID =" + tf_id.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(btn_delete, "Deleted Succesfully");

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(btn_delete, "Something went Wrong");
        }

    }

}
