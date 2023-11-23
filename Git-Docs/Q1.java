/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mirza Uzair Baig
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Contact {
    private String name;
    private String phoneNumber;
    private String address;

    public Contact(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name +", Address: " + address+", Phone: " + phoneNumber;
    }
}

public class Q1 extends JFrame {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private DefaultListModel<Contact> listModel = new DefaultListModel<>();
    private JList<Contact> contactList = new JList<>(listModel);
    private JTextField nameField = new JTextField(25);
    private JTextField phoneField = new JTextField(15);
    private JTextField addressField = new JTextField(30);

    public Q1() {
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneField);

        JButton addButton = new JButton("Add Contact");
        JButton editButton = new JButton("Edit Contact");
        JButton viewButton = new JButton("View Contact");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                Contact contact = new Contact(name, phone, address);
                contacts.add(contact);
                listModel.addElement(contact);

                nameField.setText("");
                phoneField.setText("");
                addressField.setText("");
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String name = nameField.getText();
                    String phone = phoneField.getText();
                    String address = addressField.getText();

                    Contact contact = contacts.get(selectedIndex);
                    contact.setName(name);
                    contact.setPhoneNumber(phone);
                    contact.setAddress(address);

                    listModel.set(selectedIndex, contact);
                }
            }
        });
        
viewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            Contact selectedContact = contacts.get(selectedIndex);
            String message = "Name: " + selectedContact.getName()
                              +"\nAddress: " + selectedContact.getAddress()+"\nPhone Number: " + selectedContact.getPhoneNumber();
            JOptionPane.showMessageDialog(Q1.this, message, "Contact Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

        contactList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Contact selectedContact = contacts.get(selectedIndex);
                    nameField.setText(selectedContact.getName());
                    phoneField.setText(selectedContact.getPhoneNumber());
                    addressField.setText(selectedContact.getAddress());
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(viewButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(contactList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Q1 addBook = new Q1();
            addBook.setVisible(true);
        });
    }
}
