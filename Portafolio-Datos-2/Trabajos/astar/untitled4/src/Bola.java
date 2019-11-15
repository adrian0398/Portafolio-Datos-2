public class Bola {
    int posx;
    int posy;
    int speed;
    int tamaño;
    int[][] M1;

    public Bola() {
    }

    public Bola(int[][] Matrix, int tamaño, int speed) {
        this.M1 = Matrix;
        this.tamaño = tamaño;
        this.speed = speed;
    }

    public void mover(int moverX, int moverY){
        M1[posx][posy] = 0;
        posx += moverX;
        posy += moverY;
        this.colocar();
    }

    public void colocar(){
        M1[posx][posy] = 1;
    }

    public void posInicial(int SizeX, int SizeY){
        posx = (int) Math.floor(Math.random()*(SizeX-1));
        posy = (int) Math.floor(Math.random()*(SizeY-1));
        this.colocar();
    }

}
