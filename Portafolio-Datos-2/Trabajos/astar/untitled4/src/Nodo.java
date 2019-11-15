import java.util.LinkedList;

public class Nodo {
    Nodo parent;
    Posicion Pos;

    int g = 0;
    int h = 0;
    int f = 0;

    public Nodo(Nodo parent, Posicion pos) {
        this.parent = parent;
        this.Pos = new Posicion(pos.posx, pos.posy);
        this.g = 0;
        this.h = 0;
        this.f = 0;
    }


    public void astar(int [][] maze,Posicion start,Posicion end){
        Nodo start_node = new Nodo(null, start);

        start_node.g = start_node.h = start_node.f = 0;

        Nodo end_node = new Nodo(null, end);

        end_node.g = end_node.h = end_node.f = 0;

        LinkedList open_list=new LinkedList();
        LinkedList closed_list=new LinkedList();


        while (open_list.size() > 0){
            Nodo current_node = (Nodo) open_list.get(0);
            int current_index=0;
            for(int index=0;index<open_list.size()-1;index++){
                current_node= (Nodo) open_list.get(index);
                current_index=index;
            }

            open_list.remove(current_index);
            closed_list.addLast(current_node);

            if(current_node.Pos.posx==end_node.Pos.posx&&current_node.Pos.posy==end_node.Pos.posy){


            }



        }






    }

}
