package src;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
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

    // Solver Data
    private static ArrayList<String> answerList = new ArrayList<String>();
    private static long executionTime;

    // Java Swing GUI Element
    private static Border border = BorderFactory.createLineBorder(Color.black, 1);
    private static JFrame frame = new JFrame("24 Game");
    private static JButton randomButton = new JButton("Random");
    private static JButton solveButton = new JButton("Solve");
    private static JLabel cards[] = new JLabel[4];
    private static ArrayList<JComboBox<String>> cardPicks = new ArrayList<JComboBox<String>>();

    // GUI Element that displayed after solve button clicked
    private static JLabel emptyCardText = new JLabel("Cannot use empty card!");
    private static JLabel solutionAmountText = new JLabel();
    private static JLabel timeText = new JLabel();
    private static JLabel solutionLabel = new JLabel();
    private static JTextArea solutionArea = new JTextArea();
    private static JButton saveButton = new JButton("Save");

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
        
        // Set Label
        setLabel(emptyCardText, Color.red, 12, 650, 230, buttonWidth + 20, 20);
        setLabel(solutionAmountText, Color.black, 20, 575, 450, 200, 50);
        setLabel(timeText, Color.black, 15, 550, 475, 250, 50);
        setLabel(solutionLabel, Color.black, 20, 250, 310, 250, 50);

        // Set Solution Area
        solutionArea.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        solutionArea.setBounds(50, 350, 490, 400);
        solutionArea.setBorder(border);
        solutionArea.setEditable(false);

        // TODO add Scroll mechanism
        // private static JScrollPane verticalScroll = new JScrollPane(solutionArea);
        // verticalScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Set Button
        setButton(randomButton, 650, 50, buttonWidth, buttonHeight, randomAction);
        setButton(solveButton, 650, 150, buttonWidth, buttonHeight, solveAction);
        setButton(saveButton, 605, 375, buttonWidth, buttonHeight, saveAction);

        // Set Frame
        for(int i=0;i<4;i++){
            frame.add(cards[i]);
            frame.add(cardPicks.get(i));
        }
        frame.add(randomButton);
        frame.add(solveButton);
        frame.add(emptyCardText);
        frame.add(solutionAmountText);
        frame.add(timeText);
        frame.add(solutionLabel);
        frame.add(saveButton);
        frame.add(solutionArea);

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

    private static void toggleAnswerLabel(boolean toggle){
        emptyCardText.setVisible(toggle);
        solutionAmountText.setVisible(toggle);
        timeText.setVisible(toggle);
        solutionLabel.setVisible(toggle);
        solutionArea.setVisible(toggle);
        saveButton.setVisible(toggle);
    }

    private static void reloadFrame(){
        toggleAnswerLabel(false);
        solutionArea.setText(null);
        for(int i=0;i<4;i++){
            cards[i].setIcon(cardPics[cardValue[i]]);
            cardPicks.get(i).setSelectedIndex(cardValue[i]);
        }
    }

    private static void setLabel(JLabel label, Color color, int fontsize, int x, int y, int width, int height){
        label.setForeground(color);
        label.setFont(new Font("Times New Roman", Font.PLAIN, fontsize));
        label.setBounds(x, y, width, height);
        label.setOpaque(false);
    }

    private static void setButton(JButton button, int x, int y, int width, int height, ActionListener action){
        button.setBounds(x, y, width, height);
        button.addActionListener(action);
    }

    public static void main(String args[]){
        loadCardImage();
        initFrame();
        reloadFrame();
    }

    public static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);  
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
            solutionArea.setText(null);
            boolean haveEmpty = false;
            for(int i=0;i<4;i++){
                if(cardValue[i] == 0){
                    haveEmpty = true;
                }
            }

            if(haveEmpty){
                emptyCardText.setVisible(true);
            }else{
                long timeStart = System.nanoTime();
                answerList = Solver.solve(cardValue);
                int solutionAmount = answerList.size();
                if(solutionAmount == 0){
                    solutionLabel.setText("No Solution");
                    solutionAmountText.setText("No Solution Found");
                }else{
                    solutionLabel.setText("Solutions");
                    solutionAmountText.setText(Integer.toString(solutionAmount) + " Solution Found");
                }

                for(int i=0;i<answerList.size();i++){
                    solutionArea.append(answerList.get(i) + "\n");
                }
                long timeEnd = System.nanoTime();
                executionTime = (timeEnd - timeStart) / 1000;
                timeText.setText("Execution Time : " + Long.toString(executionTime) + " microseconds");
                
                toggleAnswerLabel(true);
                emptyCardText.setVisible(false);
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

    private static ActionListener saveAction = new ActionListener(){
        public void actionPerformed(ActionEvent actionEvent){
            JFileChooser dialog = new JFileChooser();
            int choice = dialog.showSaveDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                String filepath = dialog.getSelectedFile().getAbsolutePath();
                writeToFile(filepath);
            }
        }
    };

    private static void writeToFile(String filepath){
        try{
            FileWriter fstream = new FileWriter(filepath,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("24 Game Solution\n");
            out.write("Used Card : \n");
            for(int i=0;i<4;i++){
                out.write(Solver.convertNumber(cardValue[i]));
                out.write(" ");
            }
            out.write("\n\n");
            out.write("Execution Time : " + Long.toString(executionTime) + " microseconds\n");
            int solutionAmount = answerList.size();
            if(solutionAmount > 0){
                out.write(Integer.toString(solutionAmount) + " Solution Found\n\n");
                out.write("Solutions : \n");
                for(int i=0;i<solutionAmount;i++){
                    out.write(answerList.get(i) + "\n");
                }
            }else{
                out.write("No Solution");
            }
            out.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}