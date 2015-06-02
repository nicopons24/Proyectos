package kukabuka.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import kukabuka.config.Idioma;
import kukabuka.config.Token;
import kukabuka.gui.VentanaTweet;
import kukabuka.model.Receta;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterController {

	private Token token = Token.getInstance();
	private Idioma idioma = Idioma.getInstance();
	private VentanaTweet vTweet;
	private Twitter twitter;
	private String tokenKey, tokenSecret;
	private ConfigurationBuilder configBuilder;
	private boolean isAutenticated;
	private Receta receta;
	
	public TwitterController(Receta r) {
		this.twitter = null;
		this.receta = r;
		this.tokenKey = new String();
		this.tokenSecret = new String();
		this.configBuilder = new ConfigurationBuilder();
		this.vTweet = null;
		this.isAutenticated = lecturaFicheroAutorizacion();
	}
	
	public void lanzaVentana() {
		if (isAutenticated) {
			configuracion();
			this.vTweet = new VentanaTweet(receta.getNombre());
		}
		else {
			JOptionPane.showMessageDialog(null, idioma.getProperty("Twitter3"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void configuracion() {
		configBuilder.setDebugEnabled(true)
        	.setOAuthConsumerKey(token.getProperty("oauth.consumerKey"))
        	.setOAuthConsumerSecret(token.getProperty("oauth.consumerSecret"))
        	.setOAuthAccessToken(this.tokenKey)
        	.setOAuthAccessTokenSecret(this.tokenSecret);
		twitter = new TwitterFactory(configBuilder.build()).getInstance();		
	}

	private boolean lecturaFicheroAutorizacion() {
		boolean b = true;
		File archivo = null;
        FileReader fileR = null;
        BufferedReader lecturaFichero = null;
        try {
            // Apertura del fichero y creacion de BufferedReader
            archivo = new File(System.getProperty("user.home")+"/auth_file.txt".replace("\\","/"));
            fileR = new FileReader(archivo);
            lecturaFichero = new BufferedReader(fileR);
            // Lectura del fichero
            String linea = new String();
            int n = 1;
            while ((linea = lecturaFichero.readLine()) != null) {
                if (n == 1) { //La primera linea es el Access Token
                    this.tokenKey = linea;
                } else if (n == 2) { //La segunda linea es el Access Token Secret
                    this.tokenSecret = linea;
                }
                n++;
            }
        } catch (IOException e) {
        	b = false;
		} finally {
        	// CIERRE FICHERO
            try {
                if (null != fileR) {
                    fileR.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return b;
	}
	
	public void escribirTweet() {
		if (isAutenticated) {
			String tweet = vTweet.getTextArea().getText();
			if (!tweet.equals("")){
				try {
		            //Escribir tweet
		            Status tweetEscrito = twitter.updateStatus(tweet);
		        } catch (TwitterException e) {
		        	JOptionPane.showMessageDialog(vTweet, idioma.getProperty("Twitter4"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		        	vTweet.dispose();
		        }
				JOptionPane.showMessageDialog(vTweet, idioma.getProperty("Twitter5"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
				vTweet.dispose();
			}
			else {
				JOptionPane.showMessageDialog(vTweet, idioma.getProperty("Twitter6"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public Twitter getTwitter() {
		return twitter;
	}
}