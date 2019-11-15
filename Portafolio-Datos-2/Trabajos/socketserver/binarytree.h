//
// Created by adrian on 20/8/19.
//

#ifndef SOCKETSERVER_BINARYTREE_H
#define SOCKETSERVER_BINARYTREE_H


#include <stdio.h>
#include <stdlib.h>
#include <iostream>

std::string str2;

struct tnode
{
    int data; //node will store an integer
    struct tnode *right_child; // right child
    struct tnode *left_child; // left child
};

struct tnode* search(struct tnode *root, int x)
{
    if(root==NULL || root->data==x) //if root->data is x then the element is found
        return root;
    else if(x>root->data) // x is greater, so we will search the right subtree
        return search(root->right_child, x);
    else //x is smaller than the data, so we will search the left subtree
        return search(root->left_child,x);
}

//function to find the minimum value in a node
struct tnode* find_minimum(struct tnode *root)
{
    if(root == NULL)
        return NULL;
    else if(root->left_child != NULL) // node with minimum value will have no left child
        return find_minimum(root->left_child); // left most element will be minimum
    return root;
}

//function to create a node
struct tnode* new_tnode(int x)
{
    struct tnode *p;
    p = static_cast<tnode *>(malloc(sizeof(struct tnode)));
    p->data = x;
    p->left_child = NULL;
    p->right_child = NULL;

    return p;
}

struct tnode* insert(struct tnode *root, int x)
{
    //searching for the place to insert
    if(root==NULL)
        return new_tnode(x);
    else if(x>root->data) // x is greater. Should be inserted to right
        root->right_child = insert(root->right_child, x);
    else // x is smaller should be inserted to left
        root->left_child = insert(root->left_child,x);
    return root;
}

// funnction to delete a node
struct tnode* dele(struct tnode *root, int x)
{
//searching for the item to be deleted
    if(root==NULL)
        return NULL;
    if (x>root->data)
        root->right_child = dele(root->right_child, x);
    else if(x<root->data)
        root->left_child = dele(root->left_child, x);
    else
    {
//No Children
        if(root->left_child==NULL && root->right_child==NULL)
        {
            free(root);
            return NULL;
        }

//One Child
        else if(root->left_child==NULL || root->right_child==NULL)
        {
            struct tnode *temp;
            if(root->left_child==NULL)
                temp = root->right_child;
            else
                temp = root->left_child;
            free(root);
            return temp;
        }

//Two Children
        else
        {
            struct tnode *temp = find_minimum(root->right_child);
            root->data = temp->data;
            root->right_child = dele(root->right_child, temp->data);
        }
    }
    return root;
}

void printtree(struct tnode *root)
{
    if(root!=NULL) // checking if the root is not null
    {
        printtree(root->left_child); // visiting left child
        str2.append(std::to_string(root->data));
        str2.append(" ");


        printtree(root->right_child);// visiting right child
    }


}
#endif //SOCKETSERVER_BINARYTREE_H
