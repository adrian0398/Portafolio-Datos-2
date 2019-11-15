//
// Created by adrian on 20/8/19.
//

#ifndef SOCKETSERVER_LINKEDLIST_H
#define SOCKETSERVER_LINKEDLIST_H

#include <stdio.h>
#include <stdlib.h>
#include <iostream>

std::string str5;

typedef struct node {
    int val;
    struct node * next;
} node_t;

void print_list(node_t * head) {
    node_t * current = head;

    while (current != NULL) {
        str5.append(std::to_string(current->val));
        str5.append(" ");
        current = current->next;
    }
}

//Anadir al inicio







//Eliminar al inicio
int pop(node_t ** head) {
    int retval = -1;
    node_t * next_node = NULL;

    if (*head == NULL) {
        return -1;
    }

    next_node = (*head)->next;
    retval = (*head)->val;
    free(*head);
    *head = next_node;

    return retval;
}




int obtain_by_index(node_t ** head, int n) {
    int i = 0;
    int retval = -1;
    node_t * current = *head;
    node_t * temp_node = NULL;

    if (n == 0) {
        return current->val;
    }

    else {
        for (i = 0; i < n - 1; i++) {
            if (current->next == NULL) {
                return -1;
            }
            current = current->next;
        }

        temp_node = current->next;
        retval = temp_node->val;
        return retval;

    }
}

int edit_by_index(node_t ** head, int n, int newval) {
    int i = 0;
    int retval = -1;
    node_t * current = *head;
    node_t * temp_node = NULL;

    if (n == 0) {
        current->val=newval;
    }

    else {
        for (i = 0; i < n - 1; i++) {
            if (current->next == NULL) {
                return -1;
            }
            current = current->next;
        }

        temp_node = current->next;
        temp_node->val=newval;
        return retval;

    }
}



void push(node_t ** head, int val) {
    node_t * current = *head;
    if (current->val == NULL) {
        current->val=val;
    }
    else {
        node_t *new_node;
        new_node = static_cast<node_t *>(malloc(sizeof(node_t)));

        new_node->val = val;
        new_node->next = *head;
        *head = new_node;
    }
}





/*int main(void) {
    node_t * test_list = static_cast<node_t *>(malloc(sizeof(node_t)));

    push(&test_list,2);
    push(&test_list,3);
    push(&test_list,4);
    push(&test_list,5);
    pop(&test_list);
    edit_by_index(&test_list,2,43);

    std::cout <<"El valor es: ";
    std::cout <<obtain_by_index(&test_list,2) << std::endl;







    print_list(test_list);

    return EXIT_SUCCESS;
}*/
#endif //SOCKETSERVER_LINKEDLIST_H
