package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame{


    private JPanel mainPanel;
    private JScrollPane scrollContainerPane;
    private JTextArea historyTextArea;
    private JTextField inputFormula;
    private JList functionsList;
    private JButton evalButton;


    JMenuBar menuBar;
    JMenu menu;
    JMenuItem reset;
    JMenuItem exit;

    String lastInput = "none";
    String lastResult = "";


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

        functionsList.setSize(300, 450);
        functionsList.setVisible(true);

        Operations sine = new Operations("sine", "sin()");
        Operations cosine = new Operations("cosine", "cos()");
        Operations square  = new Operations("square", "sqrt()");
        Operations signum  = new Operations("signum", "sgn()");
        Operations logarithm = new Operations("natural logarithm", "ln()");

        Operations pi = new Operations("PI", "pi");
        Operations euler = new Operations("Euler's number", "e");
        Operations phi = new Operations("Golden ratio", "[phi]");

        Operations plus = new Operations("Addiction", "+");
        Operations minus = new Operations("Subtraction", "-");
        Operations star = new Operations("Multiplication", "*");

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

        functionsList = new JList(listModel);
        functionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);


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
    }

    public void Evaluate() {

    }

}
