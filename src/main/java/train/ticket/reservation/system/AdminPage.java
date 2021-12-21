/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket.reservation.system;

/**
 *
 * @author ajv15
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AdminPage implements ActionListener {

    JDesktopPane desktop;
    JFrame f;
    JPanel panel, top_panel;
    JLabel l = new JLabel();
    JMenu bus, customer, logout, exit;
    JMenuItem addTrain, viewTrain, removeTrain, updateTrain, viewCustomers, exit_admin, logout_admin;

    public AdminPage() {
        f = new JFrame();
        panel = new JPanel();
        top_panel = new JPanel();
        JMenuBar mb = new JMenuBar();
        bus = new JMenu("Train");
        customer = new JMenu("Customers");
        logout = new JMenu("Logout");
        exit = new JMenu("Exit");
        mb.setBackground(Color.blue);
        bus.setForeground(Color.white);
        customer.setForeground(Color.white);
        logout.setForeground(Color.white);
        exit.setForeground(Color.white);
        addTrain = new JMenuItem("Add");
        addTrain.setBackground(Color.WHITE);
        viewTrain = new JMenuItem("View Trains");
        removeTrain = new JMenuItem("Remove Train");
        updateTrain = new JMenuItem("Update Train");
        exit_admin = new JMenuItem("Exit");
        logout_admin = new JMenuItem("Logout");
        viewCustomers = new JMenuItem("View All Customers");
        desktop = new JDesktopPane();
        f.setContentPane(desktop);
        bus.add(addTrain);
        bus.add(viewTrain);
        bus.add(removeTrain);
        bus.add(updateTrain);
        customer.add(viewCustomers);
        exit.add(exit_admin);
        logout.add(logout_admin);
        mb.add(bus);
        mb.add(customer);
        mb.add(logout);
        mb.add(exit);
        f.setJMenuBar(mb);
        addTrain.addActionListener(this);
        viewTrain.addActionListener(this);
        removeTrain.addActionListener(this);
        updateTrain.addActionListener(this);
        viewCustomers.addActionListener(this);
        exit_admin.addActionListener(this);
        logout_admin.addActionListener(this);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setTitle("Welcome ");
        f.setSize(300, 300);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTrain) {

            AddTrain a = new AddTrain();
            desktop.add(a);

        }
        if (e.getSource() == viewTrain) {
            ViewTrain v = new ViewTrain();
            desktop.add(v);
        }
        if (e.getSource() == removeTrain) {
            RemoveTrain r = new RemoveTrain();
            desktop.add(r);
        }
        if (e.getSource() == updateTrain) {
            UpdateTrain u = new UpdateTrain();
            desktop.add(u);
        }
        if (e.getSource() == logout_admin) {
            new Registration();
            f.dispose();

        }
        if (e.getSource() == exit_admin) {
            f.dispose();

        }
    }

}
