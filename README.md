# RedBlackTree
Red-Black Tree 
 
For this assignment you will implement a red-black tree. Additionally, we will make use of the null 
object pattern for the leaf nodes. 
 
Implementing the node interface provided to write Node classes. The Node interface as follows: 
 
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
} 
 
A red-black tree is a binary search tree with nodes colored red and black in a way that satisfies 
the following properties: 
 
Root Property: The root is black. 
External Property: Every external node is black. 
Red Property: The children of a red node are black. 
Depth Property: All external nodes (leaves) have the same black depth, defined as the number of 
proper ancestors that are black. 
 
For  the  add  operation,  you  need  to  remedy  a  potential  double  red  violation;  For  the  remove 
operation,  you  need  to  remedy  a  potential  double  black  violation.  Please  refer  to  the  textbook 
11.6.1 and its code snippets to understand how this is done. 
 
For the Red-Black tree class, you will implement four methods: add, remove, contains, and get. 
The first two methods modify the tree and might trigger rotations. The third method checks if the 
tree contains a value. The last method searches for a value within the tree and returns the node 
containing it. The tree interface follows: 
 
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
 
Additional Details  
• Each method must be as efficient as possible. That is, a O(n) is unacceptable if the 
method could be written with O(log n) complexity.  
• You may not make any modifications to the interface files provided. I will grade your project 
with my copy of this file. 
• All source code files must have your name and class account number at the beginning of 
the file. 
• All of the above classes must be in a package named 'data_structures'. 
• You may import java.util.Iterator, java.util.NoSuchElementException, and java.io.* for file 
read and write. If you feel that you need to import anything else, let me know. You are 
expected to write all of the code yourself, and you may not use the Java API for any 
containers. 
• Your code must not print anything. 
• Your code should never crash, but must handle any/all error conditions gracefully.  
• You must write generic code according to the interface provided. You may not add 
any public methods to the implementations, but you may add private ones, if needed. 
• Your code may generate unchecked cast warnings when compiled, but it must 
compile and run correctly on edoras to receive any credit. 
 
Testing Your Code 
• Tester/driver programs will be provided going forward to help you test your code. 
• Allowing sufficient time for testing on edoras is essential. Use your sandbox with the 
following directory structure: 
~/sandbox/pa3/ 
TestDriverProgram.java 
data_structures/ 
       NodeInterface.java 
       RedBlackTreeInterface.java 
RedBlackTree.java 
• Compile and test from ~/sandbox/pa3 directory: 
[cssc0000@edoras pa1]$ javac TestDriverProgram.java 
[cssc0000@edoras pa1]$ java TestDriverProgram 
