
package com.update;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateSalesCustomer extends JFrame{
    
    JButton b;
    JTextField tf1,tf2;
    JLabel label1,label2,label3;
    JComboBox cb;
    Connection con;
    Statement stmt;
    public UpdateSalesCustomer(){
    
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addCustomerIDs();
        showCustomer("C001");
        
        cb.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String CustID = cb.getSelectedItem().toString();
                    showCustomer(CustID);
                }
        });
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    }
    void addComponents(){
        label1 = new JLabel("请输入顾客编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("顾客名称：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("顾客联系方式：");
        label3.setFont(new Font("幼圆",Font.BOLD,18));
        tf1 = new JTextField();
        tf1.setFont(new Font("幼圆",Font.BOLD,18));

        tf2 = new JTextField();
        tf2.setFont(new Font("幼圆",Font.BOLD,18));
        b = new JButton("修改");
        b.setFont(new Font("幼圆",Font.BOLD,18));
        b.setBackground(Color.white);
        
        cb = new JComboBox();
        cb.setPreferredSize(new Dimension(100,40));
        cb.setFont(new Font("幼圆",Font.BOLD,18));
        
        label1.setBounds(100, 100, 200, 40);
        cb.setBounds(350, 100, 200, 40);
        label2.setBounds(100, 200, 200, 40);
        tf1.setBounds(350, 200, 200, 40);
        label3.setBounds(100,300,200,40);
        tf2.setBounds(350, 300, 200, 40);
        b.setBounds(270, 400, 200, 40);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String CustID = cb.getSelectedItem().toString();
                modifyCustomer(CustID);
            }
        });
        add(label1);
        add(label2);
        add(label3);
        add(tf1);
        add(tf2);
        add(cb);
        add(b);
  
    }
    void connectDB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=SAZ";
            String user = "SAZ";
            String password = "SAZ";
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            
        }catch(Exception ex){
            System.out.println("Connection failed!");
        }
    }
    
    void addCustomerIDs(){
        
        try{
            String sql = "select * from SALES.CUSTOMER";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb.addItem(rs.getString(1));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addDeptIDs");
        }
    }
    void showCustomer(String CustID){
        try{
        
            String sql = "select * from SALES.CUSTOMER where CUSTOMERID = \'" + CustID + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf1.setText(rs.getString(2));
                tf2.setText(rs.getString(3));
            }
            
        }catch(Exception ex){
            System.out.println("Error Occured in addCustomerIDs");
        }
        
    }

    void modifyCustomer(String CustID){
        
        try{
            String name = tf1.getText().trim();
            String info = tf2.getText().trim();
            PreparedStatement ps = con.prepareStatement("update SALES.CUSTOMER set CUSTOMERNAME = ?,CONTACTINFO = ? where CUSTOMERID = ?");
            ps.setString(1, name);
            ps.setString(2, info);
            ps.setString(3, CustID);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    public static void main(String[] args) {
        new UpdateSalesCustomer().setVisible(true);
    }
    
}
