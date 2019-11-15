#include <iostream>
#include <string>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <netdb.h>
#include <sys/uio.h>
#include <sys/time.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <fstream>
#include <sstream>
#include <cereal/archives/json.hpp>
#include <cereal/types/vector.hpp>
#include "Json_data.h"
#include "Json_output.h"
#include "binarytree.h"
#include "linkedlist.h"

using namespace std;
//Server side
int main(int argc, char *argv[])
{

    //grab the port number
    int port = 8080;
    //buffer to send and receive messages with
    char msg[1500];

    struct tnode *root;
    root = new_tnode(20);

    node_t * test_list = static_cast<node_t *>(malloc(sizeof(node_t)));

    //setup a socket and connection tools
    sockaddr_in servAddr;
    bzero((char*)&servAddr, sizeof(servAddr));
    servAddr.sin_family = AF_INET;
    servAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servAddr.sin_port = htons(port);

    //open stream oriented socket with internet address
    //also keep track of the socket descriptor
    int serverSd = socket(AF_INET, SOCK_STREAM, 0);
    if(serverSd < 0)
    {
        cerr << "Error establishing the server socket" << endl;
        exit(0);
    }
    //bind the socket to its local address
    int bindStatus = bind(serverSd, (struct sockaddr*) &servAddr,
                          sizeof(servAddr));
    if(bindStatus < 0)
    {
        cerr << "Error binding socket to local address" << endl;
        exit(0);
    }
    cout << "Waiting for a client to connect..." << endl;
    //listen for up to 5 requests at a time
    listen(serverSd, 5);
    //receive a request from client using accept
    //we need a new address to connect with the client
    sockaddr_in newSockAddr;
    socklen_t newSockAddrSize = sizeof(newSockAddr);
    //accept, create a new socket descriptor to
    //handle the new connection with client
    int newSd = accept(serverSd, (sockaddr *)&newSockAddr, &newSockAddrSize);
    if(newSd < 0)
    {
        cerr << "Error accepting request from client!" << endl;
        exit(1);
    }
    cout << "Connected with client!" << endl;
    //lets keep track of the session time
    struct timeval start1, end1;
    gettimeofday(&start1, NULL);
    //also keep track of the amount of data sent as well
    int bytesRead, bytesWritten = 0;









    while(1)
    {
        //receive a message from the client (listen)
        cout << "Awaiting client response..." << endl;
        memset(&msg, 0, sizeof(msg));//clear the buffer
        bytesRead += recv(newSd, (char*)&msg, sizeof(msg), 0);
        if(!strcmp(msg, "exit"))
        {
            cout << "Client has quit the session" << endl;
            break;
        }
        cout << "Client: " << msg << endl;

        std::stringstream is(msg);

        json_data data_new;

        {
            cereal::JSONInputArchive archive_in(is);
            archive_in(data_new);
            std::cout << data_new.data_type1 << std::endl;
        }

        std::cout << data_new.data_type2 << std::endl;


        ////////////////////////////////////////////////////////////////

        json_out newjson_out;


        if("Lista Enlazada"== data_new.data_type1 ){
            newjson_out.structtype="Lista Enlazada";
            if("Insertar al inicio"== data_new.data_type2 ){
                push(&test_list,std::stoi(data_new.value));
            }
            if("Eliminar al inicio"== data_new.data_type2 ){
                pop(&test_list);
            }
            if("Editar por posicion"== data_new.data_type2 ){
               edit_by_index(&test_list,std::stoi(data_new.value2),std::stoi(data_new.value));
            }

            str5="";
            print_list(test_list);
            std::cout << str5 << "\n";


            if("Obtener por posicion"== data_new.data_type2 ){
                obtain_by_index(&test_list,std::stoi(data_new.value));
                str5=std::to_string(obtain_by_index(&test_list,std::stoi(data_new.value)));
            }
            newjson_out.val=str5;
        }

        if("Arbol Binario"== data_new.data_type1 ){
            newjson_out.structtype="Arbol Binario";
            if("Insertar"== data_new.data_type2 ){
                insert(root,std::stoi(data_new.value));
            }
            if("Eliminar"== data_new.data_type2 ){
                dele(root,std::stoi(data_new.value));
            }

            str2="";
            printtree(root);
            std::cout << str2 << "\n";
            newjson_out.val=str2;



        }
        std::stringstream os;
        {
            cereal::JSONOutputArchive archive_out(os);
            archive_out(CEREAL_NVP(newjson_out));
        }
        std::string json_str1 = os.str();
        std::cout << json_str1 << std::endl;

        memset(&msg, 0, sizeof(msg)); //clear the buffer
        strcpy(msg, json_str1.c_str());

        //send the message to client
        bytesWritten += send(newSd, (char*)&msg, strlen(msg), 0);
    }
    //we need to close the socket descriptors after we're all done
    gettimeofday(&end1, NULL);
    close(newSd);
    close(serverSd);
    cout << "********Session********" << endl;
    cout << "Bytes written: " << bytesWritten << " Bytes read: " << bytesRead << endl;
    cout << "Elapsed time: " << (end1.tv_sec - start1.tv_sec)
         << " secs" << endl;
    cout << "Connection closed..." << endl;
    return 0;
}