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

public class UpdateSalesRegionSales extends JFrame{

    JButton b;
    JTextField tf1;
    JLabel label1,label2,label3;
    JComboBox cb1,cb2;
    Connection con;
    Statement stmt;
    
    public UpdateSalesRegionSales(){
    
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addRegionIDs();
        addSalesYears();
        showRegionSales("R001","2012");
        
        cb1.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String ReID = cb1.getSelectedItem().toString();
                    String year = cb2.getSelectedItem().toString();
                    showRegionSales(ReID,year);
                }
        });
        cb2.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String ReID = cb1.getSelectedItem().toString();
                    String year = cb2.getSelectedItem().toString();
                    showRegionSales(ReID,year);
                }
        });        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     
    }
        
     void addComponents(){
        label1 = new JLabel("请输入区域编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("销售年份：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("年销售额：");
        label3.setFont(new Font("幼圆",Font.BOLD,18));
        
        tf1 = new JTextField();
        tf1.setFont(new Font("幼圆",Font.BOLD,18));
        
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
        label3.setBounds(100, 300, 100, 40);
        tf1.setBounds(350, 300, 200, 40);
        b.setBounds(270, 400, 200, 40);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ReID = cb1.getSelectedItem().toString();
                String year = cb2.getSelectedItem().toString();
                modifyRegionSales(ReID,year);  //调用方法修改指定员工编号的信息，并更新数据库
            }
        });
        add(label1);
        add(label2);
        add(label3);
        add(tf1);
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
    
    void addRegionIDs(){
        
        try{
            String sql = "select * from SALES.REGIONSALES";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb1.addItem(rs.getString(1));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addRegionIDs");
        }
    }
    
    void addSalesYears(){
        
        try{
            String sql = "select * from SALES.REGIONSALES";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb2.addItem(rs.getString(2));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addRegionIDs");
        }
    }
    
    void showRegionSales(String ReID,String year){
        try{
        
            String sql = "select * from SALES.REGIONSALES where REGIONID = \'" + ReID + "\' and SALESYEAR = \'" + year + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf1.setText(rs.getString(3));
            }
            
        }catch(Exception ex){
            System.out.println("Error Occured in addRegionIDs");
        }
        
    }

    void modifyRegionSales(String ReID,String year){
        
        try{
            String aoy = tf1.getText().trim();
            PreparedStatement ps = con.prepareStatement("update SALES.REGIONSALES set AMOUNTOFYEAR = ? where REGIONID = ? and SALESYEAR = ?");
            ps.setString(1, aoy);
            ps.setString(2,ReID);
            ps.setString(3,year);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    public static void main(String[] args) {
        new UpdateSalesRegionSales().setVisible(true);
    }
}
