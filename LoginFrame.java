import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
public class LoginFrame  extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    public LoginFrame() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));

        add(new JLabel("Email:"));
        emailField=new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField=new JPasswordField();
        add(passwordField);

        loginButton=new JButton("Login");
        add(loginButton);
        
        loginButton.addActionListener(e -> {
            String email=emailField.getText();
            String password=new String(passwordField.getPassword());
            User checkUser=UserController.validateUser(email,password);
            
            if (checkUser!=null) {
                JOptionPane.showMessageDialog(null, "Login i suksesshëm!");
                openDashboard(checkUser);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Gabim! Kredencialet janë të pasakta.");
            }
        });
        
        setVisible(true);
    }
    
    private void openDashboard(User user) {
        if (user.role.equals("Admin")) {
            new DashboardFrame(new AdminController().getDashboardData(),user.role);
        } else if (user.role.equals("Team Leader")) {
            new DashboardFrame(new TeamLeaderController().getDashboardData(),user.role);
        } else {
            new DashboardFrame(new ProgrammerController().getDashboardData(),user.role);
        }
    }
    
}


