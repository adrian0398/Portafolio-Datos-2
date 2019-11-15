
import java.util.LinkedList;

    public class Astar {

        public Astar() {
        }

        public LinkedList pathfinder(int[][] Maze, Posicion start, Posicion end) throws InterruptedException {
            Nodo start_node = new Nodo(null, start);
            Nodo end_node = new Nodo(null, end);
            start_node.g = 0;
            start_node.h = 0;
            start_node.f = 0;
            end_node.g = 0;
            end_node.h = 0;
            end_node.f = 0;

            LinkedList open_list = new LinkedList();
            LinkedList closed_list = new LinkedList();

            open_list.add(start_node);

            while(open_list.size() > 0){
                System.out.println("///////////////////////////////////////");
                System.out.println("///////////////////////////////////////");
                printLista(open_list);
                System.out.println("///////////////////////////////////////");
                System.out.println("///////////////////////////////////////");
                Nodo current_node = (Nodo) open_list.get(0);
                int current_index = 0;

                for(int i = 0; i<open_list.size(); i++){
                    Nodo tmp = (Nodo) open_list.get(i);
                    if(tmp.f < current_node.f){
                        current_node = tmp;
                        current_index = i;
                        System.out.println("============Entré Aquí===================");
                        System.out.println("============"+ current_index + "===================");

                    }
                }

                open_list.remove(current_index);
                closed_list.add(current_node);

                if(current_node.Pos.posx==end_node.Pos.posx&&current_node.Pos.posy==end_node.Pos.posy){
                    LinkedList path = new LinkedList();
                    Nodo current = current_node;
                    while(current != null){
                        path.add(current.Pos);
                        current = current.parent;
                    }

                    printPath(path);
                    return invierte(path);
                }

                LinkedList children = new LinkedList();
                Posicion[] Posiciones = {new Posicion(0 , -1), new Posicion(0,1), new Posicion(-1,0), new Posicion(1,0), new Posicion(-1,-1),
                        new Posicion(-1,1), new Posicion(1, -1), new Posicion(1,1)};

                for(int i = 0; i<Posiciones.length; i++){
                    Posicion node_position = new Posicion(current_node.Pos.posx + Posiciones[i].posx, current_node.Pos.posy + Posiciones[i].posy);

                    if(node_position.posx > (Maze.length - 1) || node_position.posx < 0 || node_position.posy > (Maze[0].length -1) || node_position.posy < 0){
                        continue;
                    }

                    if (Maze[node_position.posx][node_position.posy] != 0){
                        continue;
                    }

                    Nodo new_nodo = new Nodo(current_node, node_position);
                    children.add(new_nodo);
                }
                System.out.println("=*=*=*=*=*=*=*=*=*=*=**==**=*=*=*==*=*=*");
                printLista(children);
                System.out.println("=*=*=*=*=*=*=*=**=*=*=*=**=*=*=*=");


                for(int i = 0; i< children.size(); i++){
                    Nodo child = (Nodo) children.get(i);
                    for(int j = 0; j< closed_list.size(); j++){
                        if(child == closed_list.get(j)){
                            continue;
                        }
                    }

                    child.g = current_node.g + 1;
                    child.h = (int) (Math.pow((child.Pos.posx - end_node.Pos.posx), 2) + Math.pow((child.Pos.posy - end_node.Pos.posy),2));
                    child.f = child.g + child.h;

                    for(int j = 0; j< open_list.size(); j++){
                        Nodo open_node = (Nodo) open_list.get(j);
                        if(child == open_node && child.g > open_node.g){
                            continue;
                        }
                    }
                    open_list.add(child);
                    System.out.println("===============================}");
                    System.out.println(open_list.size());
                    System.out.println("===============================}");


                }
            }

            return open_list;
        }

        public LinkedList invierte(LinkedList list){
            LinkedList tmp = new LinkedList();
            for(int i = list.size()-1; i>=0; i--){
                tmp.add(list.get(i));
            }
            return tmp;
        }

        public void printLista(LinkedList L1){
            for(int i = 0; i< L1.size(); i++){
                Nodo tmp = (Nodo) L1.get(i);
                System.out.println("( " + tmp.Pos.posx + ", " + tmp.Pos.posy + ")" );
            }

        }

        public void printPath(LinkedList L1){
            for(int i = 0; i< L1.size(); i++){
                Posicion tmp = (Posicion) L1.get(i);
                System.out.println("( " + tmp.posx + ", " + tmp.posy + ")" );
            }

        }


    }
