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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class UpdateSalesOrder extends JFrame{
    
        
    JButton b;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10;
    JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11;
    JComboBox cb;
    Connection con;
    Statement stmt;
    
    public UpdateSalesOrder(){
    
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);
        this.setLayout(null);
        
        addComponents(); //调用自定义方法添加组件
        connectDB(); //调用自定义方法连接数据库
        addOrderIDs(); //调用自定义方法添加员工编号到下拉列表
        
        showOrder("o001"); //先显示的是E001的信息
        
        cb.addItemListener(new ItemListener() { //为下拉列表添加Item监听器

            @Override
            public void itemStateChanged(ItemEvent e) {
                String OrderID = cb.getSelectedItem().toString(); //获取选中的员工编号
                showOrder(OrderID); //显示该员工信息
            }
        });
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
     void addComponents() {

        label1 = new JLabel("请选择订单的编号：");
        label1.setFont(new Font("幼圆",Font.BOLD,16));
        label2 = new JLabel("订单日期：");
        label2.setFont(new Font("幼圆",Font.BOLD,16));
        label3 = new JLabel("产品编号：");
        label3.setFont(new Font("幼圆",Font.BOLD,16));
        label4 = new JLabel("价格：");
        label4.setFont(new Font("幼圆",Font.BOLD,16));
        label5 = new JLabel("数量：");
        label5.setFont(new Font("幼圆",Font.BOLD,16));
        label6 = new JLabel("总额：");
        label6.setFont(new Font("幼圆",Font.BOLD,16));
        label7 = new JLabel("销售人员编号：");
        label7.setFont(new Font("幼圆",Font.BOLD,16));
        label8 = new JLabel("区域编号：");
        label8.setFont(new Font("幼圆",Font.BOLD,16));
        label9 = new JLabel("支付方式：");
        label9.setFont(new Font("幼圆",Font.BOLD,16));
        label10 = new JLabel("存货清单编号：");
        label10.setFont(new Font("幼圆",Font.BOLD,16));
        label11= new JLabel("顾客编号：");
        label11.setFont(new Font("幼圆",Font.BOLD,16));
        


        tf1 = new JTextField();
        tf1.setFont(new Font("幼圆",Font.BOLD,16));
        tf2 = new JTextField();
        tf2.setFont(new Font("幼圆",Font.BOLD,16));
        tf3 = new JTextField();
        tf3.setFont(new Font("幼圆",Font.BOLD,16));
        tf4 = new JTextField();
        tf4.setFont(new Font("幼圆",Font.BOLD,16));
        tf5 = new JTextField();
        tf5.setFont(new Font("幼圆",Font.BOLD,16));
        tf6 = new JTextField();
        tf6.setFont(new Font("幼圆",Font.BOLD,16));
        tf7 = new JTextField();
        tf7.setFont(new Font("幼圆",Font.BOLD,16));
        tf8 = new JTextField();
        tf8.setFont(new Font("幼圆",Font.BOLD,16));
        tf9 = new JTextField();
        tf9.setFont(new Font("幼圆",Font.BOLD,16));
        tf10 = new JTextField();
        tf10.setFont(new Font("幼圆",Font.BOLD,16));

        
        cb = new JComboBox();
        cb.setFont(new Font("幼圆",Font.BOLD,16));
        cb.setPreferredSize(new Dimension(100, 30)); //设置首选大小

        b = new JButton("修改");
        b.setFont(new Font("幼圆",Font.BOLD,16));
        label1.setBounds(100, 10, 200, 30);
        cb.setBounds(350, 10, 200, 30);
        label2.setBounds(100, 50, 200, 30);
        tf1.setBounds(350, 50, 200, 30);
        label3.setBounds(100, 90, 200, 30);
        tf2.setBounds(350, 90, 200, 30);
        label4.setBounds(100, 130, 200, 30);
        tf3.setBounds(100, 130, 200, 30);
        label5.setBounds(100, 170, 200, 30);
        tf4.setBounds(350, 170, 200, 30);
        label6.setBounds(100, 210, 200, 30);
        tf5.setBounds(350, 210, 200, 30);
        label7.setBounds(100, 250, 200, 30);
        tf6.setBounds(350, 250, 200, 30);
        label8.setBounds(100, 290, 200, 30);
        tf7.setBounds(350, 290, 200, 30);
        label9.setBounds(100, 330, 200, 30);
        tf8.setBounds(350, 330, 200, 30);
        label10.setBounds(100, 370, 200, 30);
        tf9.setBounds(350, 370, 200, 30);
        label11.setBounds(100, 410, 200, 30);
        tf10.setBounds(350, 410, 200, 30);
        b.setBounds(260,500, 200, 30);
        b.setBackground(Color.white);

        this.add(label1);
        this.add(cb);
        this.add(label2);
        this.add(tf1);
        this.add(label3);
        this.add(tf2);
        this.add(label4);
        add(label5);
        add(tf4);
        add(label6);
        add(tf5);
        add(tf6);
        add(tf7);
        add(tf8);
        add(tf9);
        add(tf10);
        add(label7);
        add(label8);
        add(label9);
        add(label10);
        add(label11);
      
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String OrderID = cb.getSelectedItem().toString();
                modifyOrder(OrderID);  //调用方法修改指定员工编号的信息，并更新数据库
            }
        });
        this.add(b);
    }

    void connectDB() {
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

    void addOrderIDs() {
        try {
            String sql = "select * from SALES.ORDERDETAILS";  
            ResultSet rs = stmt.executeQuery(sql); 

            while (rs.next()) {
                cb.addItem(rs.getString(1)); //获取员工编号，添加到下拉列表框
            }
        } catch (Exception e) {
            System.out.println("Error Occured in addOrderIDs");
        }
    }

    void showOrder(String OrderID) {
        //数据库里字符串值两端加单引号，所以这里通过转义字符实现加单引号
        try {
            String sql = "select * from SALES.ORDERDETAILS where ORDERID=\'" + OrderID + "\'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tf1.setText(rs.getString(2)); 
                tf2.setText(rs.getString(3)); 
                tf3.setText(rs.getString(4));
                tf4.setText(rs.getString(5));
                tf5.setText(rs.getString(6));
                tf6.setText(rs.getString(7));
                tf7.setText(rs.getString(8));
                tf8.setText(rs.getString(9));
                tf9.setText(rs.getString(10));
                tf10.setText(rs.getString(11));
                
            }
        } catch (Exception e) {
            System.out.println("Error Occured in addOrderIDs");
        }
    }

    void modifyOrder(String OrderID) {
        try {

            String date = tf1.getText().trim();
            String ProID = tf2.getText().trim();
            String price = tf3.getText().trim();
            String quantity = tf4.getText().trim();
            String total = tf5.getText().trim();
            String salesID = tf6.getText().trim();
            String regionID = tf7.getText().trim();
            String payID = tf8.getText().trim();
            String ivenID = tf9.getText().trim();
            String custID = tf10.getText().trim();
            
            //使用PreparedStatement可以设置参数，参数值用？号代替
            PreparedStatement ps = con.prepareStatement("update SALES.ORDERDETAILS set ORDERDATE=?,PRODUCTID=?,PRICE=?,QUANTITY=?,TOTAL=?,SALESPERSONID=?,REGIONID=?,PAYMENTMODEID=?,INVENTORYID=?,CUSTOMERID=? where ORDERID=?");
            ps.setString(1, date); //设置第一个参数的值为name变量的值，这里类型都是字符串，数据库也是，故用setString即可
            ps.setString(2, ProID);
            ps.setString(3, price);
            ps.setString(4, quantity);
            ps.setString(5, total);
            ps.setString(6, salesID);
            ps.setString(7, regionID);
            ps.setString(8, payID);
            ps.setString(9, ivenID);
            ps.setString(10, custID);
            ps.setString(11, OrderID);
            
            
            ps.executeUpdate(); //调用executeUpdate将修改更新到数据库

            JOptionPane.showMessageDialog(null,"修改成功!"); //修改成功后弹出对话框提示修改成功
            
        } catch (Exception e) {
            System.out.println("Error Occured in Update");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new UpdateSalesOrder().setVisible(true);
    }
    
}
