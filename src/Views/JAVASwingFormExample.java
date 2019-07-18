package Views;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class JAVASwingFormExample {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    JFileChooser choix = new JFileChooser();

    String cotisation="";
    String rapprochement = "" ;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JAVASwingFormExample window = new JAVASwingFormExample();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public JAVASwingFormExample() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        Image img = Toolkit.getDefaultToolkit().getImage("image.jpg");
        JButton fichier1 = new JButton("fichier cotisation");
        fichier1.setBounds(65, 87, 150, 30);
        fichier1.setBackground(Color.CYAN);

        JButton fichier2 = new JButton("fichier rapprochement");
        fichier2.setBounds(250, 87, 150, 30);
        fichier2.setBackground(Color.CYAN);

        frame = new JFrame();
        frame.setBounds(100, 100, 430, 289);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{

            frame.setContentPane( new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\it.dev\\Documents\\NetBeansProjects\\rapprochement\\src\\Views\\images.jpg") ) ) ) );
        }
        catch (IOException e){

        }


        JLabel lblName = new JLabel("Fichier 1");
        lblName.setBounds(65, 31, 46, 14);

        frame.getContentPane().add(fichier1);
        frame.getContentPane().add(fichier2);

        JLabel lblPhone = new JLabel("Fichier 2");
        lblPhone.setBounds(65, 68, 46, 14);
        frame.getContentPane().add(lblPhone);

        frame.getContentPane().add(choix);

        JButton btnClear = new JButton("Quitter");
        btnClear.setBounds(312, 150, 89, 23);
        frame.getContentPane().add(btnClear);

        JButton btnSubmit = new JButton("Faire le rapprochement");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(65, 150, 89, 23);

        btnSubmit.addActionListener((p)->{
                TransactionServiceRap transactionServiceRap = new TransactionServiceRap();
                transactionServiceRap.generateCsvFile(cotisation,rapprochement);
        });

        fichier1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFile();
                cotisation = choix.getSelectedFile().getAbsolutePath();
            }
        });

        fichier2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFile();
                rapprochement = choix.getSelectedFile().getAbsolutePath();
            }
        });

        frame.getContentPane().add(btnSubmit);
        frame.getContentPane().setLayout(null);


//        textField_1 = new JTextField();
//        textField_1.setBounds(128, 65, 86, 20);
//        frame.getContentPane().add(textField_1);
//        textField_1.setColumns(10);
//
//        JLabel lblEmailId = new JLabel("Email Id");
//        lblEmailId.setBounds(65, 115, 46, 14);
//        frame.getContentPane().add(lblEmailId);
//
//        textField_2 = new JTextField();
//        textField_2.setBounds(128, 112, 247, 17);
//        frame.getContentPane().add(textField_2);
//        textField_2.setColumns(10);
//
//        JLabel lblAddress = new JLabel("Address");
//        lblAddress.setBounds(65, 162, 46, 14);
//        frame.getContentPane().add(lblAddress);
//
//        JTextArea textArea_1 = new JTextArea();
//        textArea_1.setBounds(126, 157, 212, 40);
//        frame.getContentPane().add(textArea_1);
//
//
//
//        JButton btnClear = new JButton("Clear");
//
//        btnClear.setBounds(312, 387, 89, 23);
//        frame.getContentPane().add(btnClear);
//
//        JLabel lblSex = new JLabel("Sex");
//        lblSex.setBounds(65, 228, 46, 14);
//        frame.getContentPane().add(lblSex);
//
//        JLabel lblMale = new JLabel("Male");
//        lblMale.setBounds(128, 228, 46, 14);
//        frame.getContentPane().add(lblMale);
//
//        JLabel lblFemale = new JLabel("Female");
//        lblFemale.setBounds(292, 228, 46, 14);
//        frame.getContentPane().add(lblFemale);
//
//        JRadioButton radioButton = new JRadioButton("");
//        radioButton.setBounds(337, 224, 109, 23);
//        frame.getContentPane().add(radioButton);
//
//        JRadioButton radioButton_1 = new JRadioButton("");
//        radioButton_1.setBounds(162, 224, 109, 23);
//        frame.getContentPane().add(radioButton_1);
//
//        JLabel lblOccupation = new JLabel("Occupation");
//        lblOccupation.setBounds(65, 288, 67, 14);
//        frame.getContentPane().add(lblOccupation);
//
//        JComboBox comboBox = new JComboBox();
//        comboBox.addItem("Select");
//        comboBox.addItem("Business");
//        comboBox.addItem("Engineer");
//        comboBox.addItem("Doctor");
//        comboBox.addItem("Student");
//        comboBox.addItem("Others");
//        comboBox.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//            }
//        });
//        comboBox.setBounds(180, 285, 91, 20);
//        frame.getContentPane().add(comboBox);



    }

    public  void showFile(){
        MonFiltre mft = new MonFiltre(new String[]{"csv"},"les fichiers csv (*.csv)");

                choix.addChoosableFileFilter(mft);
           int retour = choix.showOpenDialog(frame);

        if(retour==JFileChooser.APPROVE_OPTION){
            // un fichier a été choisi (sortie par OK)
            // nom du fichier  choisir

            String path =  choix.getSelectedFile().getName();
            System.out.println( mft.accept(choix.getSelectedFile()));
            System.out.println(path);

            // chemin absolu du fichier choisi
           rapprochement = choix.getSelectedFile().getAbsolutePath();
            System.out.println( rapprochement);

        } else{

        }


    }

}
