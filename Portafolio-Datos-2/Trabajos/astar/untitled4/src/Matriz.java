public class Matriz {
    int SizeX = 20;
    int SizeY = 20;
    int [][] Matrix;
    int ResX;
    int ResY;
    Bola ball;
    Posicion inicio;
    Posicion destino;

    public Matriz() {
    }

    public Matriz(int sizeX, int sizeY, int resX, int resY) {
        SizeX = sizeX;
        SizeY = sizeY;
        ResX = resX;
        ResY = resY;
        Matrix = new int[SizeX][SizeY];
    }

    public void iniciar(){
        for(int i = 0; i < (SizeX-1); i++){
            for(int j = 0; j < (SizeY-1); j++){
                Matrix[j][i] = 0;
            }
        }
        //inicio

        ball = new Bola(Matrix, (ResX/SizeX), 1);
        ball.posInicial(SizeX, SizeY);

        inicio = new Posicion(ball.posx, ball.posy);



        //destino
        int posx = (int) Math.floor(Math.random()*(SizeX-1));
        int posy = (int) Math.floor(Math.random()*(SizeY-1));

        destino = new Posicion(posx, posy);

        Matrix[posx][posy] = 0;

        obstaculos();

    }

    public void printM(){
        String linea = "";
        for(int i = 0; i<= this.SizeX-1; i++){
            for(int j = 0; j<= this.SizeY-1; j++){
                linea += "  ";
                linea += (this.Matrix[i][j]);
                linea += "  ";
            }
            System.out.println(linea);
            linea = "";
        }
    }

    public void obstaculos(){
        int cantidad = (int) ((SizeX * SizeY)* 0.15);
        int i = 0;
        while(i <= cantidad){
            int posx = (int) Math.floor(Math.random()*(SizeX-1));
            int posy = (int) Math.floor(Math.random()*(SizeY-1));
            if(Matrix[posx][posy] == 0){
                Matrix[posx][posy] = 1;
                i++;
            }
        }
    }
}
