package ca.ubc.ece.cpen221.mp3.avltree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import ca.ubc.ece.cpen221.mp3.avltree.AvlTreeSet;
import org.junit.BeforeClass;
import org.junit.Test;

public class AvlTreeSetTest {
    private AvlTreeSet AVL = new AvlTreeSet();

    public void setup(){

    }
    /*
    NOTES

    Size functions eeems wrong. After inserting a value of 1 and immediately checking size, size returns 0.
    This is possibly due to the fact that the size of a node is only based off of it's left and right, which means that
    a new node (which isn't assigned left and right values) will have a size of 0 before being assigned these values.
    Possible fix: adding a 1 to the size on line 140 seems to fix it (the +1 accounts for the unweighted size of the
    original node)

    containsRight is failing but contains left isn't. A quick check in the contains method shows that there
    isn't actually a check for values higher than mValue. Added if statement (which solved it).
        Another thing to realize with the contain methods is that the "return false" at the end will never be
        reached, as there are if statements covering every possible outcome

    Regarding the above, the same is actually true with the insert function, as again, there is an if statement for
    every possible outcome (empty, =, <, >). Not a bug per se but to be kept in mind

    Max and min functions seem fine

     */

    @Test
    public void sizeTest1(){
        AVL.insert(1);
        assertEquals(1, AVL.size());
    }

    @Test
    public void insertTest1(){
        AVL.insert(1);
        assertEquals(1, AVL.size());
    }

    @Test
    public void insertTest2(){
        AVL.insert(0);
        AVL.insert(1);
        AVL.insert(2);
        assertEquals(3, AVL.size());
    }

    @Test
    public void removeTest1(){
        AVL.insert(1);
        AVL.insert(2);
        AVL.insert(0);
        AVL.remove(2);
        assertEquals(false, AVL.contains(2));
        assertEquals(2, AVL.size());
    }

    @Test (expected = IllegalStateException.class)
    public void removeTest2() throws IllegalStateException{
        AVL.remove(0);
    }

    @Test
    public void removeTest3(){
        AVL.insert(1);
        AVL.insert(2);
        AVL.remove(1);
        assertEquals(1, AVL.size());
        assertEquals(true, AVL.contains(2));
        assertEquals(false, AVL.contains(1));
    }

    @Test
    public void removeTest4(){
        AVL.insert(1);
        AVL.insert(-1);
        AVL.remove(1);
        assertEquals(1, AVL.size());
        assertEquals(true, AVL.contains(-1));
        assertEquals(false, AVL.contains(1));
    }

    @Test
    public void removeTest5(){
        AVL.insert(1);
        AVL.insert(2);
        AVL.insert(-1);
        AVL.remove(-1);
        assertEquals(false, AVL.contains(-1));
        AVL.insert(-2);
        AVL.remove(1);
        assertEquals(false, AVL.contains(1));

    }

    @Test
    public void containsLeft(){
        AVL.insert(1);
        AVL.insert(-2);
        assertEquals(true, AVL.contains(-2));
    }

    @Test
    public void containsSame(){
        AVL.insert(1);
        assertEquals(true, AVL.contains(1));
    }


    @Test
    public void containsRight(){
        AVL.insert(1);
        AVL.insert(3);
        assertEquals(true, AVL.contains(3));
    }

    @Test
    public void heightTest(){
        AVL.insert(1);
        AVL.insert(2);
        AVL.insert(3);
        assertEquals(1, AVL.getHeight());
        AVL.insert(4);
        AVL.insert(5);
        AVL.insert(6);
        AVL.insert(7);
        AVL.insert(8);
        AVL.insert(9);
        assertEquals(3, AVL.getHeight());
    }

    @Test
    public void maxTest1(){
        AVL.insert(1);
        AVL.insert(0);
        assertEquals(1, AVL.getMax()); //should return the mValue of 1 as there is no max
        AVL.insert(2);
        assertEquals(2, AVL.getMax());
    }

    @Test
    public void minTest1(){
        AVL.insert(1);
        AVL.insert(-1);
        assertEquals(-1, AVL.getMin());
    }

    @Test (expected = IllegalStateException.class)
    public void maxTest2() throws IllegalStateException{
        AVL.getMax();
    }

    @Test (expected = IllegalStateException.class)
    public void minTest2() throws IllegalStateException{
        AVL.getMin();
    }

    @Test
    public void balanceTest1(){ //overloading right side and w/o removing 1 to get a double rotate left
        AVL.insert(1);
       // AVL.remove(1);
        AVL.insert(7);
        AVL.insert(5);
        AVL.insert(6);
        AVL.remove(7);
        AVL.remove(5);
        AVL.remove(6);

    }

    @Test
    public void balanceTest2(){ //overloading right side but removing 1 to get a double rotate right
        AVL.insert(1);
        AVL.remove(1);
        AVL.insert(7);
        AVL.insert(5);
        AVL.insert(6);
        AVL.remove(7);
        AVL.remove(5);
        AVL.remove(6);

    }

    @Test
    public void balanceTest3(){
        AVL.insert(1);
        AVL.insert(-1);
        AVL.insert(-2);
        AVL.insert(-3);
        AVL.remove(1);

    }


}

