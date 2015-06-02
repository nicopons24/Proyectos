package kukabuka.twitter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import kukabuka.config.Idioma;
import kukabuka.config.Token;
import kukabuka.gui.VentanaPin;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class Autorizacion {

	private Token token = Token.getInstance();
	private Idioma idioma = Idioma.getInstance();
	private File f = new File(System.getProperty("user.home")+"/auth_file.txt".replace("\\","/"));
	private VentanaPin vPin;
	private RequestToken requestToken;
    private AccessToken accessToken;
    private String url;
    private Twitter OAuthTwitter;
    
    public Autorizacion() {
    	f.deleteOnExit();
    	this.vPin = null;
    	this.requestToken = null;
    	this.accessToken = null;
    	this.url = null;
    	this.OAuthTwitter = null;
    	configuracion();
    	if (!f.exists()){
    		this.vPin = new VentanaPin();
    		abreNavegador();
    	}
    	else {
    		JOptionPane.showMessageDialog(null, idioma.getProperty("Twitter1"), "", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
    private void configuracion() {
    	ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(token.getProperty("oauth.consumerKey"))
                .setOAuthConsumerSecret(token.getProperty("oauth.consumerSecret"));
        OAuthTwitter = new TwitterFactory(configBuilder.build()).getInstance();
    }
	
    private void abreNavegador() {
    	try {
            requestToken = OAuthTwitter.getOAuthRequestToken();
            url = requestToken.getAuthorizationURL();
            Desktop.getDesktop().browse(new URI(url));
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    public void compruebaPin() {
    	String pin = vPin.getTxtPin().getText();
        if (pin.length() > 0) {
            try {
                accessToken = OAuthTwitter.getOAuthAccessToken(requestToken, pin);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        } else {
            try {
                accessToken = OAuthTwitter.getOAuthAccessToken(requestToken);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        creaFicheroAutorizacion();
    }

	private void creaFicheroAutorizacion() {
		if(accessToken!=null){
            FileOutputStream fileOS = null;
            String content = accessToken.getToken() + "\n" + accessToken.getTokenSecret();
            try {
            	fileOS = new FileOutputStream(f);
            	//Si el archivo no existe, se crea
            	if (!f.exists()) {
            		f.createNewFile();
            	}
            	//Se obtiene el contenido en Bytes
            	byte[] contentInBytes = content.getBytes();
            	fileOS.write(contentInBytes);
            	fileOS.flush();
            	fileOS.close();
            } catch (IOException e) {} 
            finally {
            	try {
            		if (fileOS != null) {
            			fileOS.close();
            		}
            	} catch (IOException e) {}
            	JOptionPane.showMessageDialog(vPin, idioma.getProperty("Twitter2"), "", JOptionPane.INFORMATION_MESSAGE);
            	vPin.dispose();
            }
		}
	}  
}
