package Tucil1_13521173.src;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border.*;
import javax.imageio.*;
import java.image;
import java.Image;
import java.ImageIO;
import java.ImageIcon;

public class GUI{
    public static void main(String args[]){
        int cardValue[] = new int[4];

        // Constant Data
        int cardWidth = 137;
        int cardHeight = 200;

        int buttonWidth = 100;
        int buttonHeight = 90;

        // Set Border
        Border border1 = BorderFactory.createLineBorder(Color.black, 1);

        // Load Card Image Data
        String cardPath = "2_of_clubs.png";
        ImageIcon cardPic = new ImageIcon(cardPath);
        cardPic = resizeIcon(cardPic, cardWidth, cardHeight);

        // Set Card Image
        int cardGap = cardWidth + 10;
        JLabel cards[] = new JLabel[4];
        for(int i=0;i<4;i++){
            JLabel card = new JLabel();
            card.setIcon(cardPic);
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

    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }

}