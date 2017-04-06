
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class QueryEmpSales extends JFrame {

    JPanel p1, p2;
    JLabel label1, label2;
    JTextField tf1, tf2;
    JTable table; //表对象
    DefaultTableModel model; //表适用的表数据模型对象
    JButton b1, b2;

    Connection con; //数据库连接对象
    Statement stmt; //语句对象，用于操作查询语句
    Vector v; //矢量对象，用于存放一行数据，进行操作，相当于动态数组

    public  QueryEmpSales() {   //初始化窗体设置
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white); //窗体背景设置为白色
        this.setResizable(false);
        addComponents(); //调用自定义方法添加组件
        connectDB(); //先连接数据库
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void addComponents() { //自定义方法，添加组件

        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(0, 50));
        p1.setOpaque(false);
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(p1, BorderLayout.NORTH);

        p2 = new JPanel();
        p2.setBackground(new Color(150, 200, 100));
        this.add(p2, BorderLayout.CENTER);

        label1 = new JLabel("员工编号：");
        label2 = new JLabel("查询年月：");

        tf1 = new JTextField(8);
        tf1.setText("EE01");
        tf2 = new JTextField(8);

        p1.add(label1);
        p1.add(tf1);
        p1.add(label2);
        p1.add(tf2);

        b1 = new JButton("条件查询");
        b1.setPreferredSize(new Dimension(100, 20));
        b1.setBackground(Color.white);
        p1.add(b1);

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchByEmpID(tf1.getText().trim(), tf2.getText().trim());  //调用自定义方法查询指定编号或姓名的员工信息
            }
        });

        b2 = new JButton("查询所有员工");
        b2.setPreferredSize(new Dimension(130, 20));
        b2.setBackground(Color.white);

        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchAll();  //调用自定义方法查询指定编号或姓名的员工信息
            }
        });

        p1.add(b2);

        p2.setLayout(new BorderLayout(10, 10));
        table = new JTable();

        JScrollPane sp = new JScrollPane(table); //创建滚动面板，适用到表格上，这样表格超出范围，会自动添加滚动条
        p2.add(sp); //添加滚动面板到p2

        model = new DefaultTableModel(); //创建表模型，即表数据模型

        model.addColumn("员工编号"); //添加一列，列名为员工编号
        model.addColumn("支付年月");
        model.addColumn("当月工资");
        table.setModel(model); //将数据模型适用到表格

        //下面设置表格标题行居中显示
        JTableHeader th = table.getTableHeader();  //获取表的标题栏
        DefaultTableCellRenderer rr = (DefaultTableCellRenderer) th.getDefaultRenderer(); //获取表格的显示样式
        rr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);   //将水平居中设置为Center
    }

    //连接数据库
    void connectDB() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载第三方类
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=SAZ"; //数据库连接字符串
            String user = "SAZ"; //数据库连接用户名
            String password = "SAZ"; //数据库连接密码
            con = DriverManager.getConnection(url, user, password); //通过驱动管理器调用静态方法getConnection获取一个数据库连接
            stmt = con.createStatement(); //基于建立好的数据库连接返回一个语句对象，调用其方法执行语句操作数据库
        } catch (Exception e) {
            System.out.println("Connection Failed!");
        }
    }

    void searchByEmpID(String empID, String date) {
        try {
            //sql字符串存储要执行的查询语句
            String sql = "select * from HUMANRESOURCES.EMPLOYEEPAYHISTORY where EmployeeID=\'" + empID + "\' or SALARYOFDATE=\'" + date + "\'";
            ResultSet rs = stmt.executeQuery(sql); //调用Statement类的executeQuery方法返回结果集对象
            model.setRowCount(0); //清空表的数据列表
            while (rs.next()) { //next用于读取一行数据，若存在，返回true，否则返回false
                v = new Vector(); //创建动态数组Vector的对象
                v.add(rs.getString(1)); //从结果集获取第一列数据 1为列的索引，索引从1开始
                v.add(rs.getString(2)); //从结果集获取第二列数据
                v.add(rs.getString(3)); //从结果集获取第三列数据
                model.addRow(v); //将该行数据添加到表格
            }
        } catch (Exception e) {
            System.out.println("Error Occured in searchByEmpID");
        }
    }

    void searchAll() {
        try {
            //sql字符串存储要执行的查询语句
            String sql = "select * from HUMANRESOURCES.EMPLOYEEPAYHISTORY ";
            ResultSet rs = stmt.executeQuery(sql); //调用Statement类的executeQuery方法返回结果集对象
            model.setRowCount(0); //清空表的数据列表
            while (rs.next()) { //next用于读取一行数据，若存在，返回true，否则返回false
                v = new Vector(); //创建动态数组Vector的对象
                v.add(rs.getString(1)); //从结果集获取第一列数据 1为列的索引，索引从1开始
                v.add(rs.getString(2)); //从结果集获取第二列数据
                v.add(rs.getString(3)); //从结果集获取第三列数据
                model.addRow(v); //将该行数据添加到表格
            }
        } catch (Exception e) {
            System.out.println("Error Occured in searchByEmpID");
        }
    }

    public static void main(String[] args) {
        new QueryEmpSales().setVisible(true);
    }
}
