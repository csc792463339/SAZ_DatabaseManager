
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Query extends JFrame {

    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JButton b6;
    JButton b7;
    JButton b8;
    JButton b9;
    JButton b10;
    JButton b11;
    JButton b12;
    JButton b13;
    JButton b14;
    JButton b15;

    public Query() {
        this.setBounds(300, 100, 800, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.setResizable(false);
        this.setLayout(new GridLayout(3,5,0,0));
        add();
    }

    void add() {
        b1 = new JButton("查看员工信息");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryEmployee().setVisible(true);
            }
        });

        b2 = new JButton("查看部门信息");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryEmpDepartment().setVisible(true);
            }
        });
        b3 = new JButton("查看员工工资历史");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryEmpSales().setVisible(true);
            }
        });
        b4 = new JButton("查看离职员工");
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryEmpLeave().setVisible(true);
            }
        });
        b5 = new JButton("查看产品信息");
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryProduct().setVisible(true);
            }
        });
        b6 = new JButton("查看产品分类");
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryProductCategory().setVisible(true);
            }
        });
        b7 = new JButton("查看仓库");
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryProductInventory().setVisible(true);
            }
        });
        b8 = new JButton("查看采购历史");
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryProductPurchase().setVisible(true);
            }
        });
        b9 = new JButton("查看供应商信息");
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryProductSupper().setVisible(true);
            }
        });
        b10 = new JButton("查看客户信息");
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QuerySalesCustomer().setVisible(true);
            }
        });
        b11 = new JButton("查看订单信息");
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QuerySalesOrder().setVisible(true);
            }
        });
        b12 = new JButton("查看支付方式");
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QuerySalesPayment().setVisible(true);
            }
        });
        b13 = new JButton("查看销售人员信息");
        b13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QuerySalesPerson().setVisible(true);
            }
        });
        b14 = new JButton("查看区域信息");
        b14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QuerySalesRegion().setVisible(true);
            }
        });
        b15 = new JButton("查看区域销售信息");
        b15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QuerySalesRegionSales().setVisible(true);
            }
        });

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(b8);
        this.add(b9);
        this.add(b10);
        this.add(b11);
        this.add(b12);
        this.add(b13);
        this.add(b14);
        this.add(b15);

    }

    public static void main(String[] args) {
        new Query().setVisible(true);
    }

}
