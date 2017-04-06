
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Report extends JFrame {

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

    public Report() {
        this.setBounds(100, 150, 1200, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new GridLayout(3, 4));
        add();
    }

    void add() {
        b1 = new JButton("查看月平均销售额");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_1().setVisible(true);
            }
        });

        b2 = new JButton("查看销售额最高/最低的产品");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_2().setVisible(true);
            }
        });
        b3 = new JButton("查看产品指定年月份的销售报告");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_3().setVisible(true);
            }
        });
        b4 = new JButton("查看每个产品的每月销售报告");
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_4().setVisible(true);
            }
        });
        b5 = new JButton("查看销售金额高于平均销售额的产品");
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_5().setVisible(true);
            }
        });
        b6 = new JButton("查看所有年份的销售总额");
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_6().setVisible(true);
            }
        });
        b7 = new JButton("查看销售人员所有年份的销售总额");
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_7().setVisible(true);
            }
        });
        b8 = new JButton("查看销售人员的总销售额并排名");
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_8().setVisible(true);
            }
        });
        b9 = new JButton("查看一个月中没有任何销售的销售人员");
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_9().setVisible(true);
            }
        });
        b10 = new JButton("查看在一年中没有任何销售的销售人员");
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_10().setVisible(true);
            }
        });
        b11 = new JButton("查看每个客户的销售报告和排名");
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_11().setVisible(true);
            }
        });
        b12 = new JButton("查看一年内每月的销售总额");
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_12().setVisible(true);
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

    }

    public static void main(String[] args) {
        new Report().setVisible(true);
    }

}
