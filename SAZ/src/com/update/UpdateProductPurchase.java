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

public class UpdateProductPurchase extends JFrame{
    
    JButton b;
    JTextField tf1,tf2;
    JLabel label1,label2,label3,label4;
    JComboBox cb1,cb2;
    Connection con;
    Statement stmt;
    public UpdateProductPurchase(){
    
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addPurchaseIDs();
        addProductIDs();
        showPurchase("EP02","P009");
        
        cb1.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String PurID = cb1.getSelectedItem().toString();
                    String ProID = cb2.getSelectedItem().toString();
                    showPurchase(PurID,ProID);
                }
        });
        cb2.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String PurID = cb1.getSelectedItem().toString();
                    String ProID = cb2.getSelectedItem().toString();
                    showPurchase(PurID,ProID);
                }
        });        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     void addComponents(){
        label1 = new JLabel("请输入购物人员编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("产品编号：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("数量：");
        label3.setFont(new Font("幼圆",Font.BOLD,18));
        label4 = new JLabel("存货清单：");
        label4.setFont(new Font("幼圆",Font.BOLD,18));
        tf1 = new JTextField();
        tf1.setFont(new Font("幼圆",Font.BOLD,18));
        tf2 = new JTextField();
        tf2.setFont(new Font("幼圆",Font.BOLD,18));
        
        b = new JButton("修改");
        b.setFont(new Font("幼圆",Font.BOLD,18));
        b.setBackground(Color.white);
        
        cb1 = new JComboBox();
        cb1.setPreferredSize(new Dimension(100,40));
        cb1.setFont(new Font("幼圆",Font.BOLD,18));
        cb2 = new JComboBox();
        cb2.setPreferredSize(new Dimension(100,40));
        cb2.setFont(new Font("幼圆",Font.BOLD,18));
        
        label1.setBounds(100, 100, 200, 40);
        cb1.setBounds(350, 100, 200, 40);
        label2.setBounds(100, 200, 200, 40);
        cb2.setBounds(350, 200, 200, 40);
        label3.setBounds(100, 300, 200, 40);
        tf1.setBounds(350, 300, 200, 40);
        label4.setBounds(100, 400, 200, 40);
        tf2.setBounds(350, 400, 200, 40);
        b.setBounds(270, 500, 200, 40);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String PurID = cb1.getSelectedItem().toString();
                String ProID = cb2.getSelectedItem().toString();
                modifyPurchase(PurID,ProID);  //调用方法修改指定员工编号的信息，并更新数据库
            }
        });
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(tf1);
        add(tf2);
        add(cb1);
        add(cb2);
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
    
    void addPurchaseIDs(){
        
        try{
            String sql = "select * from PRODUCTION.PURCHASE";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb1.addItem(rs.getString(1));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addPurchaseIDs");
        }
    }
    void addProductIDs(){
        
        try{
            String sql = "select * from PRODUCTION.PURCHASE";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb2.addItem(rs.getString(2));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addPurchaseIDs");
        }
    }
    void showPurchase(String PurID,String ProID){
        try{
        
            String sql = "select * from PRODUCTION.PURCHASE where PURCHASEPERSONID = \'" + PurID + "\' and PRODUCTID = \'" + ProID + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf1.setText(rs.getString(3));
                tf2.setText(rs.getString(4));

            }
            
        }catch(Exception ex){
            System.out.println("Error Occured in addPurchaseIDs");
        }
        
    }

    void modifyPurchase(String PurID,String ProID){
        
        try{

            String quantity = tf1.getText().trim();
            String ivenID = tf2.getText().trim();
            PreparedStatement ps = con.prepareStatement("update PRODUCTION.PURCHASE set QUANTITY = ?, INVENTORYID = ? where PURCHASEPERSONID = ? and PRODUCTID = ?");
            ps.setString(1, quantity);
            ps.setString(2, ivenID);
            ps.setString(3,PurID );
            ps.setString(4,ProID);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    public static void main(String[] args) {
        new UpdateProductPurchase().setVisible(true);
    }
}
