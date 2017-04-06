package com.update;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdateProduct extends JFrame{
    JLabel label1,label2,label3,label4,label5,label6;
    JButton b;
    JComboBox cb;
    JTextField tf1,tf2,tf4,tf5;
    JTextArea tr;
    Connection con;
    Statement stmt;
    public UpdateProduct(){
    
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);
        this.setLayout(null);
        
        addComponents(); //调用自定义方法添加组件
        connectDB(); //调用自定义方法连接数据库
        addProductIDs(); //调用自定义方法添加员工编号到下拉列表
        
        showProduct("P001");
        
        cb.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                String proID = cb.getSelectedItem().toString();
                showProduct(proID);
            }
        });
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    void addComponents(){
        label1 = new JLabel("请选择产品编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,18));
        label2 = new JLabel("产品名称：");
        label2.setFont(new Font("幼圆",Font.BOLD,18));
        label3 = new JLabel("部门编号：");
        label3.setFont(new Font("幼圆",Font.BOLD,18));
        label4 = new JLabel("性能简介：");
        label4.setFont(new Font("幼圆",Font.BOLD,18));
        label5 = new JLabel("产品价格：");
        label5.setFont(new Font("幼圆",Font.BOLD,18));
        label6 = new JLabel("供应商编号："); 
        label6.setFont(new Font("幼圆",Font.BOLD,18));
        
        b = new JButton("修改");
        b.setFont(new Font("幼圆",Font.BOLD,18));
        cb = new JComboBox();
        cb.setFont(new Font("幼圆",Font.BOLD,18));
        tf1 = new JTextField();
        tf1.setFont(new Font("幼圆",Font.BOLD,18));
        tf2 = new JTextField();
        tf2.setFont(new Font("幼圆",Font.BOLD,18));
        tf4 = new JTextField();
        tf4.setFont(new Font("幼圆",Font.BOLD,18));
        tf5 = new JTextField();
        tf5.setFont(new Font("幼圆",Font.BOLD,18));
        
        tr = new JTextArea();
        tr.setLineWrap(true);
        this.add(new JScrollPane(tr));
        tr.setFont(new Font("幼圆",Font.BOLD,14));
        
        b.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String proID = cb.getSelectedItem().toString();
                modifyProduct(proID);
            }
            
        });
        label1.setBounds(100, 50, 200, 30);
        cb.setBounds(360, 50, 200, 30);
        label2.setBounds(100, 120, 200, 30);
        tf1.setBounds(360, 120, 200, 30);
        label3.setBounds(100,190, 200, 30);
        tf2.setBounds(360, 190, 200, 30);
        label4.setBounds(100, 260, 200, 30);
        tr.setBounds(360, 260, 250, 50);
        label5.setBounds(100, 380, 200, 30);
        tf4.setBounds(360, 380, 200, 30);
        label6.setBounds(100,450, 200,30);
        tf5.setBounds(360, 450, 200, 30);
        b.setBounds(260, 520, 200, 30);
        b.setBackground(Color.white);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(tf1);
        add(tf2);
        add(tr);
        add(tf4);
        add(tf5);
        add(cb);
        add(b);
        
        
    }
    void connectDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=SAZ";
            String user = "SAZ";
            String password = "SAZ";
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Connection Failed!");
        }
    }
    void addProductIDs(){
        try {
            String sql = "select * from PRODUCTION.PRODUCT";  
            ResultSet rs = stmt.executeQuery(sql); 

            while (rs.next()) {
                cb.addItem(rs.getString(1)); //获取员工编号，添加到下拉列表框
            }
        } catch (Exception e) {
            System.out.println("Error Occured in addProductIDs");
        }
    }
    void showProduct(String proID){
        try {
            String sql = "select * from PRODUCTION.PRODUCT where PRODUCTID=\'" + proID + "\'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tf1.setText(rs.getString(2)); 
                tf2.setText(rs.getString(3));
                tr.setText(rs.getString(4));
                tf4.setText(rs.getString(5));
                tf5.setText(rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("Error Occured in addProductIDs");
        }
    }
    void modifyProduct(String proID){
        try {

            String proname = tf1.getText().trim();
            String cateID = tf2.getText().trim();
            String techInfo = tr.getText().trim();
            String price = tf4.getText().trim();
            String suppID = tf5.getText().trim();
            //使用PreparedStatement可以设置参数，参数值用？号代替
            PreparedStatement ps = con.prepareStatement("update PRODUCTION.PRODUCT set PRODUCTNAME = ?,CATEGORYID = ?,TECHNOLOGYINFORMATION = ?,PRICE = ?,SUPPLIERID = ? where PRODUCTID=?");
            ps.setString(1, proname); //设置第一个参数的值为name变量的值，这里类型都是字符串，数据库也是，故用setString即可
            ps.setString(2, cateID);
            ps.setString(3, techInfo);
            ps.setString(4, price);
            ps.setString(5, suppID);
            ps.setString(6, proID);
            ps.executeUpdate(); //调用executeUpdate将修改更新到数据库

            JOptionPane.showMessageDialog(null,"修改成功!"); //修改成功后弹出对话框提示修改成功
            
        } catch (Exception e) {
            System.out.println("Error Occured in Update");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new UpdateProduct().setVisible(true);
    }
}
