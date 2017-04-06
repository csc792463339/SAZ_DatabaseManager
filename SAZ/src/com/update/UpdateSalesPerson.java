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

public class UpdateSalesPerson extends JFrame{
    
    JButton b;
    JTextField tf1;
    JLabel label1,label2,label3;
    JComboBox cb1,cb2;
    Connection con;
    Statement stmt;
    public UpdateSalesPerson(){
    
         this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addSalesPersonIDs();
        addSalesDate();
        showSalesPerson("ES01","2013/9");
        
        cb1.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String SpID = cb1.getSelectedItem().toString();
                    String date = cb2.getSelectedItem().toString();
                    showSalesPerson(SpID,date);
                }
        });
        cb2.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String SpID = cb1.getSelectedItem().toString();
                    String date = cb2.getSelectedItem().toString();
                    showSalesPerson(SpID,date);
                }
        });        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
     void addComponents(){
        label1 = new JLabel("请输入销售人员编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("销售日期：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("月销售额：");
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
                String SpID = cb1.getSelectedItem().toString();
                String date = cb2.getSelectedItem().toString();
                modifySalesPerson(SpID,date);  //调用方法修改指定员工编号的信息，并更新数据库
            }
        });
        add(label1);
        add(label2);
        add(label3);
        add(tf1);
        add(cb2);
        add(cb1);
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
    
    void addSalesPersonIDs(){
        
        try{
            String sql = "select * from SALES.SALESPERSON";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb1.addItem(rs.getString(1));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addDeptIDs");
        }
    }
    
    void addSalesDate(){
        
        try{
            String sql = "select * from SALES.SALESPERSON";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb2.addItem(rs.getString(2));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addSalesPersonIDs");
        }
    }
    
    void showSalesPerson(String SpID,String date){
        try{
        
            String sql = "select * from SALES.SALESPERSON where SALESPERSONID = \'" + SpID + "\' and SALESDATE = \'" + date + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf1.setText(rs.getString(3));
            }
            
        }catch(Exception ex){
            System.out.println("Error Occured in addSalesPersonIDs");
        }
        
    }

    void modifySalesPerson(String SpID,String date){
        
        try{
            String salesmonth = tf1.getText().trim();
            PreparedStatement ps = con.prepareStatement("update SALES.SALESPERSON set SALESOFMONTH = ? where SALESPERSONID = ? and SALESDATE = ?");
            
            ps.setString(1,salesmonth);
            ps.setString(2,SpID);
            ps.setString(3, date);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    
    public static void main(String[] args) {
        new UpdateSalesPerson().setVisible(true);
    }
    
}
