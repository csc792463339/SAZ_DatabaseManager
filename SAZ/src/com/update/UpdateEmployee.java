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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class UpdateEmployee extends JFrame{
    JLabel label1,label2,label3,label4,label5,label6;
    JTextField tf1,tf2,tf3,tf4;
    JButton b1;
    JRadioButton rb1, rb2;
    Connection con;
    Statement stmt;
    JComboBox cb;
    
    
    public UpdateEmployee(){
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white); //窗体背景设置为白色
        this.setResizable(false);
        this.setLayout(null);
        addComponents(); //调用自定义方法添加组件
        connectDB(); //先连接数据库
        addEmployeeIDs();
        showEmployee("EE01");
        cb.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                String empID = cb.getSelectedItem().toString();
                showEmployee(empID);
            }
        });
        
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void addComponents(){
        label1 = new JLabel("请选择员工编号：");
        label1.setFont(new Font("幼圆",Font.BOLD, 18));
        label2 = new JLabel("幼圆：");
        label2.setFont(new Font("幼圆",Font.BOLD, 18));
        label3 = new JLabel("职位：");
        label3.setFont(new Font("",Font.BOLD, 18));
        label4 = new JLabel("所在部门编号：");
        label4.setFont(new Font("幼圆",Font.BOLD, 18));
        label5 = new JLabel("电话号码："); 
        label5.setFont(new Font("幼圆",Font.BOLD, 18));
        label6 = new JLabel("性别：");
        label6.setFont(new Font("幼圆",Font.BOLD, 18));
        
        rb1 = new JRadioButton("男");
        rb1.setFont(new Font("幼圆",Font.BOLD, 18));
        rb1.setOpaque(false);
        rb2 = new JRadioButton("女");
        rb2.setFont(new Font("幼圆",Font.BOLD, 18));
        rb2.setOpaque(false);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        
        tf1 = new JTextField();
        tf1.setFont(new Font("幼圆",Font.BOLD,18));
        tf2 = new JTextField();
        tf2.setFont(new Font("幼圆",Font.BOLD,18));
        tf3 = new JTextField();
        tf3.setFont(new Font("幼圆",Font.BOLD,18));
        tf4 = new JTextField();
        tf4.setFont(new Font("幼圆",Font.BOLD,18));
        
        cb = new JComboBox();
        cb.setPreferredSize(new Dimension(100,30));
        cb.setFont(new Font("幼圆",Font.BOLD,18));
        b1 = new JButton("修改");
        b1.setFont(new Font("幼圆",Font.BOLD,18));
        
        
        label1.setBounds(100, 50, 200, 40);
        cb.setBounds(350, 50, 200, 40);
        label2.setBounds(100, 120, 200, 40);
        tf1.setBounds(350, 120, 200, 40);
        label3.setBounds(100,190,200,40);
        tf2.setBounds(350, 190, 200, 40);
        label4.setBounds(100, 260, 200, 40);
        tf3.setBounds(350,260,200,40);
        label5.setBounds(100, 330, 200, 40);
        tf4.setBounds(350,330,200,40);
        label6.setBounds(100,400,200,40);
        rb1.setBounds(300,400,200,40);
        rb2.setBounds(420,400,100,40);
        b1.setBounds(250,500,200, 40);
        
        
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String empID = cb.getSelectedItem().toString();
                modifyEmployee(empID);
            }
        });
        b1.setBackground(Color.WHITE);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(tf1);
        this.add(tf2);
        this.add(tf3);
        this.add(tf4);
        this.add(cb);
        this.add(b1);
        this.add(rb1);
        this.add(rb2);
      
    }
    public void connectDB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=SAZ";
            String user = "SAZ";
            String password = "SAZ";
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        }catch(Exception ex){
            System.out.println("Connection Failed");
        }
    }
    void addEmployeeIDs(){
        try{
            String sql = "select * from HUMANRESOURCES.EMPLOYEE";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                cb.addItem(rs.getString(1));
            }
        }catch (Exception e) {
            System.out.println("Error Occured in addEmpIDs");
        }
    }
    void showEmployee(String empID){
        try{
            String sql = "select * from HUMANRESOURCES.EMPLOYEE where EMPLOYEEID = \'" + empID + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                tf1.setText(rs.getString(2));
                tf2.setText(rs.getString(6));
                tf3.setText(rs.getString(5));
                tf4.setText(rs.getString(7));
                if(rs.getString(3).equals("M")){
                    rb1.setSelected(true);
                }else{
                    rb2.setSelected(true);
                }
            }
        }catch(Exception ex){
            System.out.println("Error Occured in addEmployeeIDs");
        }
    }
    void modifyEmployee(String empID){
        try {

            String name = tf1.getText().trim();
            String title = tf2.getText().trim();
            String DepID = tf3.getText().trim();
            String phone = tf4.getText().trim();
            String gender = "M";
            if (rb2.isSelected()) {
                gender = "F";
            }

            //使用PreparedStatement可以设置参数，参数值用？号代替
            PreparedStatement ps = con.prepareStatement("update HUMANRESOURCES.EMPLOYEE set NAME = ? ,TITLE = ? ,GENDER = ? ,DEPARTMENTID = ? ,PHONE = ? where EMPLOYEEID=?");
            ps.setString(1, name); //设置第一个参数的值为name变量的值，这里类型都是字符串，数据库也是，故用setString即可
            ps.setString(2, title);
            ps.setString(3, gender);
            ps.setString(4, DepID);
            ps.setString(5, phone);
            ps.setString(6, empID);
            ps.executeUpdate(); //调用executeUpdate将修改更新到数据库

            JOptionPane.showMessageDialog(null,"修改成功!"); //修改成功后弹出对话框提示修改成功
            
        } catch (Exception e) {
            System.out.println("Error Occured in Update");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new UpdateEmployee().setVisible(true);
    }
}
