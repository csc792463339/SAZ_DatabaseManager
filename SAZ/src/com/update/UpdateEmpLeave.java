
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

public class UpdateEmpLeave extends JFrame{
    
    JButton b;
    JTextField tf1,tf2;
    JLabel label1,label2,label3;
    JComboBox cb;
    Connection con;
    Statement stmt;

    public UpdateEmpLeave(){
    
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addEmployeeIDs();
        showEmployee("EI03");
        
        cb.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String EmpID = cb.getSelectedItem().toString();
                    showEmployee(EmpID);
                }
        });
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    void addComponents(){
        label1 = new JLabel("请输入员工编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("姓名：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("离职时间：");
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
        label3.setBounds(100, 300, 100, 40);
        tf2.setBounds(350, 300, 200, 40);
        b.setBounds(270, 400, 200, 40);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String EmpID = cb.getSelectedItem().toString();
                modifyEmpHistory(EmpID);  //调用方法修改指定员工编号的信息，并更新数据库
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
    
    void addEmployeeIDs(){
        
        try{
            String sql = "select * from HUMANRESOURCES.EMPLOYEEHISTORY";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb.addItem(rs.getString(1));
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addEmployeeIDs");
        }
    }
    void showEmployee(String EmpID){
        try{
        
            String sql = "select * from HUMANRESOURCES.EMPLOYEEHISTORY where EMPLOYEEID = \'" + EmpID + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf1.setText(rs.getString(2));
                tf2.setText(rs.getString(3));
            }
            
        }catch(Exception ex){
            System.out.println("Error Occured in addDeptIDs");
        }
        
    }

    void modifyEmpHistory(String EmpID){
        
        try{
            String name = tf1.getText().trim();
            String leavedate = tf2.getText().trim();
            PreparedStatement ps = con.prepareStatement("update HUMANRESOURCES.EMPLOYEEHISTORY set name = ? ,leavedate = ? where EMPLOYEEID = ?");
            ps.setString(1, name);
            ps.setString(2,leavedate);
            ps.setString(3, EmpID);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    public static void main(String[] args) {
        new UpdateEmpLeave().setVisible(true);
    }
}
