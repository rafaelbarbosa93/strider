import java.io.IOException;
/*
Dado um labirinto em formato .png é preciso encontrar o caminho que liga os dois pontos vermelhos.
Para resolver esse problema foi utilizado tecnicas de processamento de imagens digitais,
transformando a imagem de entrada em uma matriz de pixels. Para encontrar o caminho utilizou um algoritmo de busca em profundidade
mais expessificamente A*. 
@input: labirinto.png
@output: labirintoResolvido.png

Obs.: Todo o código foi desenvolvido pelo autor. 
*/
public class Principal {

	public static void main(String[] args) throws IOException {

		String urlEntrada = "labirinto.png";
		String urlSaida = "labirintoResolvido.png";
		Imagem imagem = new Imagem(urlEntrada, urlSaida);

		Pixel[][] labirinto = new Pixel[imagem.getWidth()][imagem.getHeight()];
		for (int i = 0; i < labirinto.length; i++) {
			for (int j = 0; j < labirinto[0].length; j++) {
				labirinto[i][j] = new Pixel(i, j);
			}
		}
		Labirinto l = new Labirinto(imagem.getPixels(), imagem.getWidth(),
				imagem.getHeight());
		Pixel origem = labirinto[l.getOrigem().getX()][l.getOrigem().getY()];
		Pixel destino = labirinto[l.getDestino().getX()][l.getDestino().getY()];
		BuscaProfundidade bp = new BuscaProfundidade(
				labirinto, origem, destino);
		for (Pixel bloqueio : l.getListaBloqueios()) {
			bp.addBloqueio(bloqueio);
		}

		boolean pesquisaOk = bp.iniciarPesquisa();
		if (pesquisaOk) {
			System.out.println("Caminho foi encontrado.");
			
			int[] r = l.marcarCaminho(bp.getListaCaminho());
			imagem.montarImagem(r, imagem.getWidth(), imagem.getHeight());
			
		} else {
			System.out.println("Caminho nao foi encontrado.");
		}
	}
}
