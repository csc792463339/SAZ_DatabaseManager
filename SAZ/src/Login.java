
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

    JButton b1, b2;
    JTextField t1;
    JLabel l1, l2, l3, l4;
    JPasswordField t2;

    private Image image;

    public Login() {
        this.setBounds(500, 200, 481, 291);
        this.setDefaultCloseOperation(2);
        this.setResizable(false);

        l1 = new JLabel("登录名");
        l1.setBounds(50, 138, 50, 30);
        this.add(l1);

        l2 = new JLabel("密码");
        l2.setBounds(58, 163, 50, 30);
        this.add(l2);

        l3 = new JLabel("(C)保留所有权利");
        l3.setBounds(360, 240, 120, 20);
        this.add(l3);

        l4 = new JLabel("Welcome!");
        l4.setBounds(380, 220, 120, 20);
        this.add(l4);

        b1 = new JButton("登  录");
        b1.setBounds(120, 205, 100, 32);
        b1.setBackground(new Color(9, 163, 220));
        b1.setForeground(Color.white);
        this.add(b1);

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String user = t1.getText().trim();
                char[] passwords = t2.getPassword();
                String password1 = "csc";
                String password2 = "qd";
                String password3 = "lq";
                String password4 = "zl";

                if (user.equals("csc") && password1.equals(new String(passwords))) {
                    new Frame().setVisible(true);
                    dispose();
                } else if (user.equals("qd") && password2.equals(new String(passwords))) {
                    new Frame().setVisible(true);
                    dispose();
                } else if (user.equals("lq") && password3.equals(new String(passwords))) {
                    new Frame().setVisible(true);
                    dispose();
                } else if (user.equals("zl") && password4.equals(new String(passwords))) {
                    new Frame().setVisible(true);
                    dispose();
                } else {
                    l4.setText("密码错误！");
                }
            }
        });

        t1 = new JTextField();
        t1.setBounds(95, 140, 150, 25);
        this.add(t1);
        t2 = new JPasswordField();
        t2.setEchoChar('●');
        t2.setBounds(95, 165, 150, 25);
        this.add(t2);

        JPanel contentPane = new JPanel() {
            @Override
            public void paint(Graphics g) {
                ImageIcon icon = new ImageIcon("ang.jpg");
                image = icon.getImage();
                g.drawImage(image, 0, 0, null);
            }
        };
        this.add(contentPane);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        Login SAZ = new Login();
    }
}
