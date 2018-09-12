import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
/**
 * Displays an image which is read from a file location
 *
 * @Silvia Zeamer
 * @5-15-2018
 */
public class ImagePanel extends JPanel
{
    Image img; 
    
    /**
     * Constructor which takes an imageFile name and sets up a JPanel
     * containing the image.
     * 
     * @params String imageFile the file name of the image to display
     */
    public ImagePanel(String imageFile){
        super();
        initImagePanel(imageFile);
    }
    
    /**
     * Helper method which reads an image file name and sets up the image
     * in addition to setting the appearance of the panel
     * 
     * @params String imageFile
     * @return void
     */
    public void initImagePanel(String imageFile){
        try{
            img = ImageIO.read(new File(imageFile));
            
        }
        catch(IOException e){
            System.out.println("Image not found");
        }
        
        setBackground(new Color(19,63,132));
        setPreferredSize(new Dimension(1500,1000));
    }
    /**
     * Overrides paintComponent to add the image read in initImagePanel
     * to the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (this.getWidth() - img.getWidth(null)) / 2;
        int y = (this.getHeight() - img.getHeight(null)) / 2;
        g.drawImage(img, x, y, this);
    }
}
