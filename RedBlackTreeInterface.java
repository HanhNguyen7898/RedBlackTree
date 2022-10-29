package data_structures;
/*
 * Tree interface for use in Assignment 3
 * Implementing class should have an instance variable representing the tree's root
 */
public interface RedBlackTreeInterface {
    int RED = 1;
    int BLACK = 0;
    //Inserts a value into the tree and performs the necessary balancing
    void add(int value);
    //Removes a value from the tree if present and performs the necessary balancing
    void remove(int value);
    //Returns true if the tree contains the specified value
    boolean contains(int value);
    //Returns node containing specified value
    NodeInterface get(int value);
}