import java.awt.Color;
import java.util.ArrayList;

public class Labirinto {

	
	private Color[][] matrizCor;
	private ArrayList<Pixel> listaBloqueios;
	private Pixel origem;
	private Pixel destino;
	private int w;
	private int h;

	public Labirinto(int[] pixels, int w, int h) {
		this.w = w;
		this.h = h;
		this.matrizCor = new Color[w][h];
		this.listaBloqueios = new ArrayList<Pixel>();
		montarMatriz(pixels);
		this.origem = descobreOrigem();
		this.destino = descobreDestino();
		this.listaBloqueios = descobreBloqueios();
	}

	private void montarMatriz(int[] pixels) {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color atual = new Color(pixels[w * j + i]);
				matrizCor[i][j] = atual;
			}
		}
	}

	public int[] montarVetor(Color[][] matriz, int w, int h) {
		int[] pixels = new int[w * h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				pixels[w * j + i] = matriz[i][j].getRGB();
			}
		}
		return pixels;
	}

	private Pixel descobreOrigem() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (matrizCor[i][j].getRed() == 255
						&& matrizCor[i][j].getGreen() == 0
						&& matrizCor[i][j].getBlue() == 0)
					return new Pixel(i, j);
			}
		}
		return null;
	}

	private Pixel descobreDestino() {
		for (int i = w - 1; i >= 0; i--) {
			for (int j = h - 1; j >= 0; j--) {
				if (matrizCor[i][j].getRed() == 255
						&& matrizCor[i][j].getGreen() == 0
						&& matrizCor[i][j].getBlue() == 0)
					return new Pixel(i, j);
			}
		}
		return null;
	}

	private ArrayList<Pixel> descobreBloqueios() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (matrizCor[i][j].getRed() <= 1
						&& matrizCor[i][j].getGreen() <= 1
						&& matrizCor[i][j].getBlue() <= 1)
					listaBloqueios.add(new Pixel(i, j));
			}
		}
		return listaBloqueios;
	}

	public int[] marcarCaminho(ArrayList<Pixel> listaCaminho) {
		for (Pixel quadrado : listaCaminho) {
			matrizCor[quadrado.getX()][quadrado.getY()] = new Color(255, 0, 0);
		}

		return montarVetor(matrizCor, w, h);

	}

	public Pixel getOrigem() {
		return origem;
	}

	public Pixel getDestino() {
		return destino;
	}

	public ArrayList<Pixel> getListaBloqueios() {
		return listaBloqueios;
	}
}
