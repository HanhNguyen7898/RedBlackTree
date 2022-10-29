
import data_structures.RedBlackTree;
import data_structures.RedBlackTreeInterface;
public class TestDriverProgram {
    public final int[] testInsertSet = {12,4,23,56,15,29,1,3,6,7,30};
    public final int[] testViolationSet = {3,1,5,7};
    int RED = 1;
    int BLACK = 0;
    RedBlackTreeInterface oTree = new RedBlackTree();
    /**
     * Constructor for TestDriverProgram class
     */
    public TestDriverProgram(){
        testAdd();
        testRemove();
        testViolationHandlers();
        testNonPresentValues();
    }
    /**
     * This function will test how the implementation handles
     * violations that occur as items are added and deleted
     * from the tree.
     * Pay attention to some of the notes in the code below,
     * this might help with fixing the issues.
     */
    private void testViolationHandlers() {
        System.out.println("------ TEST: testing for violation handling ------");
        try{
            // insert values into the tree
            for(int i : testViolationSet){
                oTree.add(i);
            }
            // by this point the tree should have made a color swap
            if(oTree.get(3).getColor() != BLACK)
                throw new RuntimeException("Error: root is not black");
            if(oTree.get(3).getLeftChild().getColor() != BLACK)
                throw new RuntimeException("Error: the color should be black");
            if(oTree.get(3).getRightChild().getColor() != BLACK)
                throw new RuntimeException("Error: the color should be black");
            if(oTree.get(7).getColor() != RED)
                throw new RuntimeException("Error: the color of the new node should be red");
            System.out.println();
            System.out.println("Color Swap handled correctly when inserting 3,1,5,7");
            // This will force a right left rotation
            oTree.add(6);
            if(oTree.get(5).getColor() != RED || oTree.get(7).getColor() != RED)
                throw new RuntimeException("Error: node should be red after rotation and color swap");
            if(oTree.get(6).getColor() != BLACK)
                throw new RuntimeException("Error: node should be black after color swap");
            System.out.println();
            System.out.println("Left right rotation handled correctly after inserting 6");
            // this will cause color flip
            oTree.add(8);
            if(oTree.get(8).getColor() != RED)
                throw new RuntimeException("Error: this inserted node should be red");
            if(oTree.get(8).getParent().getColor() != BLACK)
                throw new RuntimeException("Error: the parent for this node should be black");
            if(oTree.get(8).getParent().getParent().getColor() != RED)
                throw new RuntimeException("Error: the grandparent for this node should be red");
            if(oTree.get(8).getParent().getParent().getLeftChild().getColor() != BLACK)
                throw new RuntimeException("Error: the aunt aunt/uncle node should be black");
            System.out.println();
            System.out.println("Color flip applied correctly after inserting 8");
            // this will cause a rotate left operation
            oTree.add(11);
            if(oTree.get(11).getColor() != RED)
                throw new RuntimeException("Error: the inserted node should be red");
            if(oTree.get(11).getParent().getColor() != BLACK)
                throw new RuntimeException("Error: the parent for this node should be black");
            if(oTree.get(11).getParent().getLeftChild().getColor() != RED)
                throw new RuntimeException("Error: the sibling for this node should be RED");
            System.out.println();
            System.out.println("Rotate left performed correctly after inserting 11");
            // this will cause a color flip + a Left rotation. Keep in mind that the
            oTree.add(15);
            if(oTree.get(6).getColor() != BLACK)
                throw new RuntimeException("Error: the root node should always be black");
            if(oTree.get(3).getColor() != RED || oTree.get(8).getColor() != RED)
                throw new RuntimeException("Error: don't forget to adjust colors after rotation");
            if(oTree.get(6).getLeftChild().getValue() != 3)
                throw new RuntimeException("Error: the left child of the root should be 3");
            if(oTree.get(6).getRightChild().getValue() != 8)
                throw new RuntimeException("Error: the right child of the root should be 8");
            if(oTree.get(3).getLeftChild().getValue() != 1)
                throw new RuntimeException("Error: left child should be 1 after operation");
            if(oTree.get(3).getRightChild().getValue() != 5)
                throw new RuntimeException("Error: right child should be 5 after operation");
            System.out.println();
            System.out.println("Color flip + left rotation applied correctly");
            oTree.remove(15);
            if(oTree.contains(15))
                throw new RuntimeException("Error: 15 should not exist in the tree");
            System.out.println();
            System.out.println("Deletion of a node with no children applied correctly");
            oTree.remove(7);
            if(oTree.contains(7))
                throw new RuntimeException("Error: 7 should not exist in the tree");
            if(oTree.get(8).getRightChild().getValue() != 11)
                throw new RuntimeException("Error: the right child of 8 should be11");
            if(oTree.get(6).getColor() != BLACK)
                throw new RuntimeException("Error: the root should always be black");
            System.out.println();
            System.out.println("Deletion of a black node with no children applied correctly");
            oTree.remove(8);
            if(oTree.contains(8))
                throw new RuntimeException("Error: 8 should not exist in the tree");
            if(oTree.get(6).getRightChild().getValue() != 11)
                throw new RuntimeException("Error: the right child of the root nodeshould be 11");
            if(oTree.get(11).getParent().getColor() == RED)
                throw new RuntimeException("Error: The parent of 11 should be black, root is always black");
            System.out.println();
            System.out.println("Deletion of a node with a child applied correctly");
            oTree.remove(3);
            if(oTree.contains(3))
            	throw new RuntimeException("Error: 3 should not exist in the tree");
            if(!oTree.contains(1) || !oTree.contains(5))
                throw new RuntimeException("Error: 1 and 5 should still be in the tree");
            if(oTree.get(6).getColor() != BLACK)
                throw new RuntimeException("Error: the root is always black");
            System.out.println();
            System.out.println("Deletion of a node with two children applied  correctly");
            System.out.println();
            System.out.println("Everything looks ok here");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * These tests contain operation on values that don't exists in the tree.
     */
    private void testNonPresentValues() {
        System.out.println("------ TEST: testing for some edge cases ------");
        try{
            // test to see if handles search for values not in the tree
            if(oTree.contains(99))
                throw new RuntimeException("Error: 99 should not be in the tree");
            if(oTree.contains(-89))
                throw new RuntimeException("Error: -89 should not be in the tree");
            // test to see if we handle cases where the value doesn't exists
            oTree.remove(-566);
            oTree.remove(565);
            System.out.println();
            System.out.println("Everything looks ok here");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This tests if the values can be removed from the tree.
     */
    private void testRemove() {
        System.out.println("------ TEST: remove(int value) ------");
        try{
            // remove items
            System.out.print("Removing: ");
            for(int i : testInsertSet) {
                oTree.remove(i);
                System.out.print(i +" ");
            }
            System.out.println();
            System.out.println("Removed values from the tree without crashing - Good");
            // check to see if the values are still there
            System.out.print("Contains: ");
            for(int i : testInsertSet){
                if(oTree.contains(i))
                    throw new RuntimeException("ERROR: found value that should be removed");
                System.out.print(i +" ");
            }
            System.out.println();
            System.out.println("Verified that the tree doesn't contain the values -Good");
            System.out.println();
            System.out.println("Everything Looks ok here");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     *  Method will test if the implementation can add values to the tree.
     */
    private void testAdd() {
        System.out.println("------ TEST: add(int value) ------");
        try{
            // Add all the values in the testSet array
            System.out.print("Inserting: ");
            for(int i : testInsertSet){
                System.out.print( i +" ");
                oTree.add(i);
            }
            System.out.println();
            System.out.println("Inserted all values into tree without crashing - Good");
            System.out.println();
            // check that all those values were inserted
            System.out.print("Contains: ");
            for(int i : testInsertSet){
                if(!oTree.contains(i))
                    throw new RuntimeException("ERROR: could not find value inside the tree");
                System.out.print(i +" ");
            }
            System.out.println();
            System.out.println("Verified all values inserted are in the tree - Good");
            System.out.println();
            System.out.println("Performing some preliminary tests:");
            // preliminary checks without violation handling
            if((oTree.get(30)).getValue() != 30)
                throw new RuntimeException("ERROR: did not get correct node");
            if(oTree.get(7).getValue() != 7)
                throw new RuntimeException("ERROR: did not get correct node");
            if(oTree.get(1).getValue() != 1)
                throw new RuntimeException("ERROR: did not get correct node");
            System.out.println("Preliminary tests passed - Good");
            System.out.println();
            System.out.println("Everything looks ok here");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        new TestDriverProgram();
    }
}

