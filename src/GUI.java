package src;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.border.*;

public class GUI{
    // Constant Data
    private static final int cardWidth = 137;
    private static final int cardHeight = 200;
    private static final int cardGap = cardWidth + 10;

    private static final int buttonWidth = 100;
    private static final int buttonHeight = 70;

    private static Random rand = new Random();

    // Card Data
    private static int cardValue[] = new int[4];
    private static ImageIcon cardPics[] = new ImageIcon[14];

    // Java Swing GUI Element
    private static JFrame frame = new JFrame("24 Game");
    private static JButton randomButton = new JButton("Random");
    private static JButton solveButton = new JButton("Solve");
    private static JLabel cards[] = new JLabel[4];
    private static JLabel emptyCardText = new JLabel();
    private static Border border = BorderFactory.createLineBorder(Color.black, 1);
    private static ArrayList<JComboBox<String>> cardPicks = new ArrayList<JComboBox<String>>();
    private static JLabel solutionText = new JLabel();
    private static JLabel timeText = new JLabel();

    private static void initFrame(){
        // Set Card Image
        for(int i=0;i<4;i++){
            JLabel card = new JLabel();
            card.setBounds(50 + cardGap*i, 50, cardWidth, cardHeight);
            card.setBorder(border);
            cards[i] = card;
        }

        // Set Card Pick List
        for(int i=0;i<4;i++){
            String options[] = new String[14];
            for(int j=0;j<=13;j++){
                String option;
                if(j == 0)option = "Empty";
                else if(j == 1)option = "As";
                else if(j == 11)option = "Jack";
                else if(j == 12)option = "Queen";
                else if(j == 13)option = "King";
                else option = Integer.toString(j);
                options[j] = option;
            }
            JComboBox<String> optionList = new JComboBox<String>(options);
            optionList.setBounds(50 + cardGap*i, 260, cardWidth, 50);
            optionList.addActionListener(pickAction);
            cardPicks.add(optionList);
        }
        
        // Set Label for empty card solve button
        emptyCardText.setText("Cannot use empty card!");
        emptyCardText.setForeground(Color.red);
        emptyCardText.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        emptyCardText.setBounds(650, 230, buttonWidth + 5, 20);
        emptyCardText.setOpaque(false);

        // Set Label for display how much solution
        solutionText.setForeground(Color.black);
        solutionText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        solutionText.setBounds(250, 310, 200, 50);
        solutionText.setOpaque(false);

        // Set Label for execution time
        timeText.setText("No Solution Found");
        timeText.setForeground(Color.black);
        timeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        timeText.setBounds(250, 335, 200, 50);
        timeText.setOpaque(false);

        // Set Button
        randomButton.setBounds(650, 50, buttonWidth, buttonHeight);
        solveButton.setBounds(650, 150, buttonWidth, buttonHeight);

        randomButton.addActionListener(randomAction);
        solveButton.addActionListener(solveAction);

        // Set Frame
        for(int i=0;i<4;i++){
            frame.add(cards[i]);
            frame.add(cardPicks.get(i));
        }
        frame.add(randomButton);
        frame.add(solveButton);
        frame.add(emptyCardText);
        frame.add(solutionText);
        frame.add(timeText);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static void loadCardImage(){
        String folderPath = "src\\img\\simpleCard\\";
        for(int i=0;i<=13;i++){
            String cardPath = folderPath + Integer.toString(i) + ".png";
            ImageIcon cardPic = new ImageIcon(cardPath);
            cardPic = resizeIcon(cardPic, cardWidth, cardHeight);
            cardPics[i] = cardPic;
        }
    }

    private static void reloadFrame(){
        emptyCardText.setVisible(false);
        solutionText.setVisible(false);
        timeText.setVisible(false);
        for(int i=0;i<4;i++){
            cards[i].setIcon(cardPics[cardValue[i]]);
            cardPicks.get(i).setSelectedIndex(cardValue[i]);
        }
    }

    public static void main(String args[]){
        loadCardImage();
        initFrame();
        reloadFrame();
    }

    public static BufferedImage readImage(String imagepath){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(imagepath));
        } catch(Exception e) {
            System.out.println("Image not found");
        }
        return img;
    }

    public static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }
    
    private static ActionListener randomAction = new ActionListener(){
        public void actionPerformed(ActionEvent e){ 
            for(int i=0;i<4;i++){
                cardValue[i] = rand.nextInt(1, 14);
            }
            reloadFrame();
        } 
    };

    private static ActionListener solveAction = new ActionListener(){
        public void actionPerformed(ActionEvent actionEvent){
            // Check if have empty card
            boolean haveEmpty = false;
            for(int i=0;i<4;i++){
                if(cardValue[i] == 0){
                    haveEmpty = true;
                }
            }

            if(haveEmpty){
                emptyCardText.setVisible(true);
            }else{
                long timeStart = System.currentTimeMillis();
                ArrayList<String> answerList = Solver.solve(cardValue);
                int solutionAmount = answerList.size();
                if(solutionAmount == 0){
                    solutionText.setText("No Solution Found");
                }else{
                    solutionText.setText(Integer.toString(solutionAmount) + " Solution Found");
                }
                long timeEnd = System.currentTimeMillis();
                long executionTime = timeEnd - timeStart;
                timeText.setText("Execution Time : " + Long.toString(executionTime) + " ms");
                timeText.setVisible(true);
                solutionText.setVisible(true);;
            }
        }
    };

    private static ActionListener pickAction = new ActionListener(){
        public void actionPerformed(ActionEvent actionEvent){
            JComboBox<String> pickBox = (JComboBox) actionEvent.getSource();
            int pickBoxCoordinate = pickBox.getX();
            int index = (pickBoxCoordinate - 50) / cardGap;
            cardValue[index] = pickBox.getSelectedIndex();
            reloadFrame();
        }
    };
}