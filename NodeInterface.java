package data_structures;

/*
 * Node interface for use in Assignment 3
 * Implementing class should have an instance variable representing the node's color
 */
public interface NodeInterface {

	//Return the node's color
    int getColor();

    //Set the node's color
    void setColor(int color);

    //Returns true if the node or one of it's children contain the value
    boolean contains(int value);

    //Inserts a value into the correct position
    void add(int value);

    //Returns the node's value
    int getValue();

    //Set the node's left child
    void setLeftChild(NodeInterface n);

    //Returns the node's left child
    NodeInterface getLeftChild();

    //Set the node's right child
    void setRightChild(NodeInterface n);

    //Returns the node's right child
    NodeInterface getRightChild();

    //Return the node's parent
    NodeInterface getParent();

    //set the node's parent
    void setParent(NodeInterface n);
}
