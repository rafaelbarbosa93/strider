import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {
	 private BufferedImage imagem;
	 private String urlEntrada;
	 private String urlSaida;
	 
	 public Imagem(String urlEntrada, String urlSaida) throws IOException{
	        this.urlEntrada = urlEntrada;
	        this.urlSaida = urlSaida;
	        imagem = ImageIO.read(new File(this.urlEntrada));      
	 }
	 
	 public int getWidth(){
	    	return imagem.getWidth();
	 }
	    
	 public int getHeight(){
	    	return imagem.getHeight();
	 }
	 
	 public int[] getPixels(){
	        return  imagem.getRGB(0, 0, imagem.getWidth(), imagem.getHeight(), null, 0, imagem.getWidth());
	 }
	    
	public BufferedImage getImagem() {
			return imagem;
	}
		 
	 public void montarImagem(int[] pixels, int w, int h) throws IOException{
		 imagem.setRGB(0, 0, w, h, pixels, 0, w);
		 ImageIO.write(imagem, "PNG", new File(urlSaida));
	 }
	 
}
