
package com.update;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Update extends JFrame{
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
    
    
    public Update(){
    
        this.setTitle("数据库项目测试");
        this.setBounds(300, 100, 800, 600);
        this.setResizable(false);
        this.setLayout(new GridLayout(3,5));
        add();
    }
    public void add(){
        b1 = new JButton("修改员工信息");
        b1.setFont(new Font("黑体",Font.BOLD,14));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmployee().setVisible(true);
            }
        });
        
        b2 = new JButton("修改部门信息");
        b2.setFont(new Font("黑体",Font.BOLD,14));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmpDepartment().setVisible(true);
            }
        });
        
        
        b3 = new JButton("修改员工工资信息");
        b3.setFont(new Font("黑体",Font.BOLD,14));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmpSalary().setVisible(true);
            }
        });
        
        b4 = new JButton("修改离职员工");
        b4.setFont(new Font("黑体",Font.BOLD,14));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmpLeave().setVisible(true);
            }
        });
        
        b5 = new JButton("修改产品信息");
        b5.setFont(new Font("黑体",Font.BOLD,14));
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProduct().setVisible(true);
            }
        });
        
        b6 = new JButton("修改产品分类");
        b6.setFont(new Font("黑体",Font.BOLD,14));
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProductCategory().setVisible(true);
            }
        });
        
        b7 = new JButton("修改仓库");
        b7.setFont(new Font("黑体",Font.BOLD,14));
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProductInventory().setVisible(true);
            }
        });
        
        b8 = new JButton("修改采购历史");
        b8.setFont(new Font("黑体",Font.BOLD,14));
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProductPurchase().setVisible(true);
            }
        });
        
        b9 = new JButton("修改供应商信息");
        b9.setFont(new Font("黑体",Font.BOLD,14));
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProductSupper().setVisible(true);
            }
        });
        
        b10 = new JButton("修改客户信息");
        b10.setFont(new Font("黑体",Font.BOLD,14));
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateSalesCustomer().setVisible(true);
            }
        });
        
        b11 = new JButton("修改订单信息");
        b11.setFont(new Font("黑体",Font.BOLD,14));
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateSalesOrder().setVisible(true);
            }
        });
        
        b12 = new JButton("修改支付方式");
        b12.setFont(new Font("黑体",Font.BOLD,14));
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateSalesPayment().setVisible(true);
            }
        });
        
        b13 = new JButton("修改销售人员信息");
        b13.setFont(new Font("黑体",Font.BOLD,14));
        b13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateSalesPerson().setVisible(true);
            }
        });
        
        b14 = new JButton("修改区域信息");
        b14.setFont(new Font("黑体",Font.BOLD,14));
        b14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateSalesRegion().setVisible(true);
            }
        });
        
        b15 = new JButton("修改区域销售信息");
        b15.setFont(new Font("黑体",Font.BOLD,14));
        b15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateSalesRegionSales().setVisible(true);
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
        new Update().setVisible(true);
    }
}
