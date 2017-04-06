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

public class UpdateEmpSalary extends JFrame{
    JButton b;
    JTextField tf;
    JLabel label1,label2,label3;
    JComboBox cb1,cb2;
    Connection con;
    Statement stmt;
    
    
    public UpdateEmpSalary(){
        
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        
        addComponents();
        connectDB();
        addEmployeeIDs();
        addEmployeeDate();
        showEmployee("ES01","2011/7");
        
        cb1.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String EmpID = cb1.getSelectedItem().toString();
                    String date = cb2.getSelectedItem().toString();
                    showEmployee(EmpID,date);
                }
        });
        cb2.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    String EmpID = cb1.getSelectedItem().toString();
                    String date = cb2.getSelectedItem().toString();
                    showEmployee(EmpID,date);
                }
        });

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    void addComponents(){
        label1 = new JLabel("请输入员工编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("工资日期：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("薪水：");
        label3.setFont(new Font("幼圆",Font.BOLD,18));
        
        tf = new JTextField();
        tf.setFont(new Font("幼圆",Font.BOLD,18));
        
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
        tf.setBounds(350, 300, 200, 40);
        b.setBounds(270, 400, 200, 40);
        b.setBackground(Color.white);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String EmpID = cb1.getSelectedItem().toString();
                String date = cb1.getSelectedItem().toString();
                modifyEmpSalary(EmpID,date);  //调用方法修改指定员工编号的信息，并更新数据库
            }
        });
        
        add(label1);
        add(label2);
        add(label3);
        add(tf);
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
    void addEmployeeIDs(){
        try{
            String sql = "select * from HUMANRESOURCES.EMPLOYEEPAYHISTORY";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                cb1.addItem(rs.getString(1));
            }
            
            
        }catch(Exception ex){
            
            System.out.println("Error Occured in addEmployeeIDs");
        
        }
    }
    
    void addEmployeeDate(){
        try{
            String sql = "select * from HUMANRESOURCES.EMPLOYEEPAYHISTORY";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                cb2.addItem(rs.getString(2));
            }
            
            
        }catch(Exception ex){
            
            System.out.println("Error Occured in addEmployeeIDs");
        
        }
    }
    
    void showEmployee(String EmpID,String date){
        try{
            String sql = "select * from HUMANRESOURCES.EMPLOYEEPAYHISTORY where EMPLOYEEID = \'" + EmpID +"\' and SALARYOFDATE = \'" + date + "\'";
            
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf.setText(rs.getString(3));
            }
        }catch(Exception e){
            System.out.println("Error Occured in addEmployeeSalary");
        }
    }

    void modifyEmpSalary(String EmpID,String date){
        
        try{

            String salary = tf.getText().trim();
            PreparedStatement ps = con.prepareStatement("update HUMANRESOURCES.EMPLOYEEPAYHISTORY set SALARY = ?  where EMPLOYEEID = ? and SALARYOFDATE = ?");
            ps.setString(1, salary);
            ps.setString(2, EmpID);
            ps.setString(3,date);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功!"); 
        }catch(Exception ex){
            System.out.println("Error Occured in Update");
        }
        
    }
    public static void main(String[] args) {
        new UpdateEmpSalary().setVisible(true);
    }
}
