import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws InterruptedException {

                Matriz M1 = new Matriz(20,20, 1000,1000);
                M1.iniciar();

                Astar A = new Astar();
                LinkedList L1 = A.pathfinder(M1.Matrix, M1.inicio, M1.destino);


            }
        }









        /*int [][] maze = {{0, 1, 0, 0, 1, 0, 0, 0, 0, 0},{0, 1, 0, 0, 1, 0, 0, 0, 0, 0},{0, 1, 0, 0, 1, 0, 0, 0, 0, 0},{0, 1, 0, 0, 1, 0, 0, 0, 0, 0},{0, 1, 0, 0, 1, 0, 0, 0, 0, 0},

                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 1, 0, 1, 1, 0, 0, 0, 0, 0},

        {0, 1, 0, 1, 1, 0, 0, 0, 0, 0},

    {0, 1, 0, 1, 1, 0, 0, 0, 0, 0},

            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};


        Posicion start = new Posicion(0, 0);

        Posicion end = new Posicion(7, 6);
        Astar A = new Astar();
        LinkedList L1 = A.pathfinder(maze, start, end);
        System.out.println();




        *//*for(int i = 0; i< 3; i++) {
            M1.ball.mover(M1.ball.speed, 0);
            Thread.sleep(3000);
            M1.printM();
            System.out.println("=====================================================================================");
            System.out.println("=====================================================================================");
            System.out.println("=====================================================================================");
        }*//*
    }
}
*/