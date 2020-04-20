import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import javax.swing.DefaultListModel;
import org.mariuszgromada.math.mxparser.*;



public class MyFrame extends JFrame{


    private JPanel mainPanel;
    private JScrollPane scrollContainerPane;
    private JTextArea historyTextArea;
    private JTextField inputFormula;
    private JList<Operations> functionsList;
    private JButton evalButton;
    private JPanel Panel;


    JMenuBar menuBar;
    JMenu menu;
    JMenuItem reset;
    JMenuItem exit;

    private String lastInput = "";
    private String lastResult = "";


    public MyFrame(){
        super( "Calculator" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocation(150,50);
        setLayout(new GridLayout());
        setContentPane(this.mainPanel);

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        reset = new JMenuItem("Reset");
        exit = new JMenuItem("Exit");
        menu.add(reset);
        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);
        setResizable(true);

        functions();
    }

    public void functions(){
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                historyTextArea.setText("");
                inputFormula.setText("");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        inputFormula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                Evaluate();
            }


        });

        evalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Evaluate();
            }
        });

        inputFormula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    inputFormula.setText(lastInput);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    inputFormula.setText("");
                }
            }
        });

        functionsList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2){
                    Operations getOp = (Operations) functionsList.getSelectedValue();
                    String text = inputFormula.getText();
                    if (!getOp.getName().equals("Last result")) {
                        inputFormula.setText(text + getOp.getShortName());
                    }
                    else{
                        inputFormula.setText(text + lastResult);
                    }

                    inputFormula.requestFocus();
                    if (getOp.getShortName().contains("()")){
                        inputFormula.setCaretPosition(inputFormula.getText().length()-1);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}
            @Override
            public void mouseExited(MouseEvent mouseEvent) {}


        });
    }

    public void Evaluate() {
        lastInput = inputFormula.getText();
        Expression expression = new Expression(lastInput);
        if (expression.checkSyntax()) {
            Double result = expression.calculate();
            lastResult = String.valueOf(result);
            inputFormula.setText(lastResult);
            String showInHistory = MessageFormat.format("{0} = {1,number}\n\n", lastInput, result);
            historyTextArea.append(showInHistory);

        } else {
            String errorMessage = expression.getErrorMessage();
            JOptionPane.showMessageDialog(null, "Wrong input", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        Operations sine = new Operations("Sine", "sin()");
        Operations cosine = new Operations("Cosine", "cos()");
        Operations square  = new Operations("Square", "sqrt()");
        Operations signum  = new Operations("Signum", "sgn()");
        Operations logarithm = new Operations("Natural logarithm", "ln()");

        Operations pi = new Operations("Pi number", "pi");
        Operations euler = new Operations("Euler's number", "e");
        Operations phi = new Operations("Golden ratio", "[phi]");

        Operations plus = new Operations("Addiction", "+");
        Operations minus = new Operations("Subtraction", "-");
        Operations star = new Operations("Multiplication", "*");

        Operations lastR = new Operations("Last result", "");

        DefaultListModel<Operations> listModel = new DefaultListModel<>();

        listModel.addElement(sine);
        listModel.addElement(cosine);
        listModel.addElement(square);
        listModel.addElement(signum);
        listModel.addElement(logarithm);
        listModel.addElement(pi);
        listModel.addElement(euler);
        listModel.addElement(phi);
        listModel.addElement(plus);
        listModel.addElement(minus);
        listModel.addElement(star);
        listModel.addElement(lastR);


        functionsList = new JList<>(listModel);
    }
}