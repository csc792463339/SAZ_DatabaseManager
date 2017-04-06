
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Report_2 extends JFrame {

    JButton b1;
    JButton b2;
   
    public Report_2() {
        this.setBounds(500,300, 200, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.setResizable(false);
        this.setLayout(new GridLayout(3,5));
        add();
    }

    void add() {
        b1 = new JButton("查看销售额最高的产品信息");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_2_1().setVisible(true);
            }
        });

        b2 = new JButton("查看销售额最低的产品信息");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report_2_2().setVisible(true);
            }
        });
      

        this.add(b1);
        this.add(b2);
     

    }

    public static void main(String[] args) {
        new Report_2().setVisible(true);
    }

}
