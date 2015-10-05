import java.util.ArrayList;
/*
Algoritmo A* desenvolvido de forma sequencial. 

Descrição do A* pode ser encontrada em:
https://pt.wikipedia.org/wiki/Algoritmo_A*
*/
public class BuscaProfundidade {

	private Pixel[][] labirinto;
	private Pixel origem;
	private Pixel destino;
	private ArrayList<Pixel> listaAberta;
	private ArrayList<Pixel> listaFechada;
	private ArrayList<Pixel> listaCaminho;
	private ArrayList<Pixel> listaBloqueios;

	public BuscaProfundidade(Pixel[][] labirinto, Pixel origem, Pixel destino) {
		this.labirinto = labirinto;
		this.origem = origem;
		this.destino = destino;
		listaAberta = new ArrayList<Pixel>();
		listaFechada = new ArrayList<Pixel>();
		listaCaminho = new ArrayList<Pixel>();
		listaBloqueios = new ArrayList<Pixel>();
	}

	public boolean iniciarPesquisa() {
		if (getLabirinto() == null) {
			return false;
		}

		if (getOrigem() == getDestino()) {
			return true;
		}

		listaAberta.add(getOrigem());
		if (pesquisar()) {
			return salvarCaminho();
		}
		return false;
	}

	private boolean pesquisar() {

		while (!listaFechada.contains(destino) || !listaAberta.isEmpty()) {
			Pixel atual = listaAberta.get(0);
			for (int i = 1; i < listaAberta.size(); i++) {
				if (atual.getCustoF() > listaAberta.get(i).getCustoF()) {
					atual = listaAberta.get(i);
				}
			}

			listaFechada.add(atual);
			listaAberta.remove(atual);

			if (atual == destino) {
				return true;
			}

			int x;
			int y;

			x = atual.getX();
			y = atual.getY();

			int direita = x + 1;
			int esquerda = x - 1;
			int acima = y - 1;
			int abaixo = y + 1;

			if (direita < labirinto[0].length) {
				Pixel adjacenteDireita = labirinto[direita][y];
				if (!listaFechada.contains(adjacenteDireita)
						&& !listaBloqueios.contains(adjacenteDireita)) {
					int custoG = atual.getCustoG() + 1;
					int custoH = Math.abs(destino.getX()
							- adjacenteDireita.getX())
							+ Math.abs(destino.getY() - adjacenteDireita.getY());
					if (!listaAberta.contains(adjacenteDireita)) {
						adjacenteDireita.setPai(atual);
						listaAberta.add(adjacenteDireita);
						adjacenteDireita.setCustoG(custoG);
						adjacenteDireita.setCustoH(custoH);
					} else {
						if (adjacenteDireita.getCustoH() > custoH) {
							adjacenteDireita.setPai(atual);
							adjacenteDireita.setCustoG(custoG);
							adjacenteDireita.setCustoH(custoH);
						}
					}
				}
			}

			if (esquerda >= 0) {
				Pixel adjacenteEsquerda = getLabirinto()[esquerda][y];
				if (!listaFechada.contains(adjacenteEsquerda)
						&& !listaBloqueios.contains(adjacenteEsquerda)) {
					int custoG = atual.getCustoG() + 1;
					int custoH = Math.abs(getDestino().getX()
							- adjacenteEsquerda.getX())
							+ Math.abs(getDestino().getY()
									- adjacenteEsquerda.getY());
					if (!listaAberta.contains(adjacenteEsquerda)) {
						adjacenteEsquerda.setPai(atual);
						listaAberta.add(adjacenteEsquerda);
						adjacenteEsquerda.setCustoG(custoG);
						adjacenteEsquerda.setCustoH(custoH);
					} else {
						if (adjacenteEsquerda.getCustoH() > custoH) {
							adjacenteEsquerda.setPai(atual);
							adjacenteEsquerda.setCustoG(custoG);
							adjacenteEsquerda.setCustoH(custoH);
						}
					}
				}
			}

			if (acima >= 0) {
				Pixel adjacenteAcima = getLabirinto()[x][acima];
				if (!listaFechada.contains(adjacenteAcima)
						&& !listaBloqueios.contains(adjacenteAcima)) {
					int custoG = atual.getCustoG() + 1;
					int custoH = Math.abs(getDestino().getX()
							- adjacenteAcima.getX())
							+ Math.abs(getDestino().getY()
									- adjacenteAcima.getY());

					if (!listaAberta.contains(adjacenteAcima)) {
						adjacenteAcima.setPai(atual);
						listaAberta.add(adjacenteAcima);
						adjacenteAcima.setCustoG(custoG);
						adjacenteAcima.setCustoH(custoH);
					} else {
						if (adjacenteAcima.getCustoH() > custoH) {
							adjacenteAcima.setPai(atual);
							adjacenteAcima.setCustoG(custoG);
							adjacenteAcima.setCustoH(custoH);
						}
					}
				}
			}

			if (abaixo < labirinto.length) {
				Pixel adjacenteAbaixo = labirinto[x][abaixo];
				if (!listaFechada.contains(adjacenteAbaixo)
						&& !listaBloqueios.contains(adjacenteAbaixo)) {
					int custoG = atual.getCustoG() + 1;
					int custoH = Math.abs(getDestino().getX()
							- adjacenteAbaixo.getX())
							+ Math.abs(getDestino().getY()
									- adjacenteAbaixo.getY());

					if (!listaAberta.contains(adjacenteAbaixo)) {
						adjacenteAbaixo.setPai(atual);
						listaAberta.add(adjacenteAbaixo);
						adjacenteAbaixo.setCustoG(custoG);
						adjacenteAbaixo.setCustoH(custoH);
					} else {
						if (adjacenteAbaixo.getCustoH() > custoH) {
							adjacenteAbaixo.setPai(atual);
							adjacenteAbaixo.setCustoG(custoG);
							adjacenteAbaixo.setCustoH(custoH);
						}
					}
				}
			}

			if (listaAberta.isEmpty()) { 
				return false;
			}
		}
		return true; 
	}

	
	private boolean salvarCaminho() {
		Pixel corrente = getDestino();

		if (corrente == null) {
			return false;
		}

		while (corrente != null) {
			listaCaminho.add(corrente);
			corrente = corrente.getPai();
		}
		return true;

	}

	public void addBloqueio(Pixel bloqueio) {
		listaBloqueios.add(bloqueio);
	}

	public Pixel[][] getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(Pixel[][] labirinto) {
		this.labirinto = labirinto;
	}

	public Pixel getOrigem() {
		return origem;
	}

	public void setOrigem(Pixel origem) {
		this.origem = origem;
	}

	public Pixel getDestino() {
		return destino;
	}

	public void setDestino(Pixel destino) {
		this.destino = destino;
	}

	public ArrayList<Pixel> getListaCaminho() {
		return listaCaminho;
	}

	public ArrayList<Pixel> getListaBloqueios() {
		return listaBloqueios;
	}
}