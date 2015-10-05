
public class Pixel {

    private int custoG = 0;
    private int custoH = 9999;
    private int x = 0;
    private int y = 0;
    private Pixel pai = null;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCustoF() {
        return custoG + custoH;
    }

    public int getCustoG() {
        return custoG;
    }

    public void setCustoG(int custoG) {
        this.custoG = custoG;
    }

    public int getCustoH() {
        return custoH;
    }

    public void setCustoH(int custoH) {
        this.custoH = custoH;
    }

    public int getX() {
        return x;
    }

    public void setPosicaoX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setPosicaoY(int y) {
        this.y = y;
    }

    public Pixel getPai() {
        return pai;
    }

    public void setPai(Pixel pai) {
        this.pai = pai;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Pixel)obj).getX() == x && ((Pixel)obj).getY()==y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }
}