package Tucil1_13521173.src;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border;
import javax.imageio.*;
import java.Image;
import java.ImageIO;
import java.ImageIcon;
import java.util.Random;

public class GUI{
    // Constant Data
    private static final int cardWidth = 137;
    private static final int cardHeight = 200;

    private static final int buttonWidth = 100;
    private static final int buttonHeight = 90;

    private static int cardValue[] = new int[4];
    private static ImageIcon cardPics[] = new ImageIcon[14];

    private static void loadCardImage(){
        String folderPath = "img\\simpleCard\\";
        for(int i=0;i<=13;i++){
            String cardPath = folderPath + Integer.toString(i) + ".png";
            ImageIcon cardPic = new ImageIcon(cardPath);
            cardPic = resizeIcon(cardPic, cardWidth, cardHeight);
            cardPics[i] = cardPic;
        }
    }

    public static void main(String args[]){
        loadCardImage();

        // testing card pic, delete soon
        Random rand = new Random();
        for(int i=0;i<4;i++){
            cardValue[i] = rand.nextInt(14);
        }

        // Set Border
        Border border1 = BorderFactory.createLineBorder(Color.black, 1);

        // Set Card Image
        int cardGap = cardWidth + 10;
        JLabel cards[] = new JLabel[4];
        for(int i=0;i<4;i++){
            JLabel card = new JLabel();
            card.setIcon(cardPics[cardValue[i]]);
            card.setBounds(50 + cardGap*i, 50, cardWidth, cardHeight);
            card.setBorder(border1);
            cards[i] = card;
        }

        // Set Button
        JButton randomButton = new JButton("Random");
        randomButton.setBounds(650, 50, buttonWidth, buttonHeight);

        JButton solveButton = new JButton("Solve");
        solveButton.setBounds(650, 150, buttonWidth, buttonHeight);
        
        // Set Frame
        JFrame frame = new JFrame("24 Game");

        for(int i=0;i<4;i++){
            frame.add(cards[i]);
        }
        frame.add(randomButton);
        frame.add(solveButton);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
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

}