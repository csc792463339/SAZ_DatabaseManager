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

public class UpdateProductCategory extends JFrame{
    JButton b;
    JTextField tf;
    JLabel label1,label2;
    JComboBox cb;
    Connection con;
    Statement stmt;
    public UpdateProductCategory(){
        
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addCategoryIDs();
        showCategory("C001");
        
        cb.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String CateID = cb.getSelectedItem().toString();
                    showCategory(CateID);
                }
        });
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    }
    void addComponents(){
        label1 = new JLabel("请输入产品编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("产品名称：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        
        tf = new JTextField();
        tf.setFont(new Font("幼圆",Font.BOLD,18));
        
        b = new JButton("修改");
        b.setFont(new Font("幼圆",Font.BOLD,18));
        b.setBackground(Color.white);
        
        cb = new JComboBox();
        cb.setPreferredSize(new Dimension(100,40));
        cb.setFont(new Font("幼圆",Font.BOLD,18));
        
        label1.setBounds(100, 100, 200, 40);
        cb.setBounds(350, 100, 200, 40);
        label2.setBounds(100, 250, 200, 40);
        tf.setBounds(350, 250, 250, 40);
        b.setBounds(270, 400, 200, 40);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String CateID = cb.getSelectedItem().toString();
                modifyCategory(CateID);
            }
        });
        add(label1);
        add(label2);
        add(tf);
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
    
    void addCategoryIDs(){
        
        try{
            String sql = "select * from PRODUCTION.CATEGORY";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb.addItem(rs.getString(1));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addDeptIDs");
        }
    }
    void showCategory(String CateID){
        try{
        
            String sql = "select * from PRODUCTION.CATEGORY where CATEGORYID = \'" + CateID + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf.setText(rs.getString(2));
            }
            
        }catch(Exception ex){
            System.out.println("Error Occured in addDeptIDs");
        }
        
    }

    void modifyCategory(String CateID){
        
        try{
            String name = tf.getText().trim();
            PreparedStatement ps = con.prepareStatement("update PRODUCTION.CATEGORY set CATEGORYNAME = ? where CATEGORYID = ?");
            ps.setString(1, name);
            ps.setString(2, CateID);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    public static void main(String[] args) {
        new UpdateProductCategory().setVisible(true);
    }
    
}
