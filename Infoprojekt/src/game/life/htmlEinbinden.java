package game.life;
import java.awt.Toolkit; 
import java.io.IOException; 
import java.net.MalformedURLException; 
import java.net.URL; 

import javax.swing.JEditorPane; 
import javax.swing.JFrame; 

import org.newdawn.slick.Graphics;

import game.objects.Label; 

public class htmlEinbinden extends JFrame { 

    private static final long serialVersionUID = 1L; 

    JEditorPane htmlPane; 
    Label label;

    public htmlEinbinden() { 
    	label = new Label(200,200,200,200,true);
        htmlPane = new JEditorPane(); 
        htmlPane.setContentType("text/php"); 

        this.add(htmlPane); 
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize()); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true); 
    } 

    public void setURL(String URL) { 
        try { 
            URL url = new URL(URL); 
            htmlPane.setPage(url); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
    
    public void draw(Graphics g){
    	label.draw(g);
    }

} 