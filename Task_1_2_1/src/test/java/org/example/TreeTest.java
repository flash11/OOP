package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    Tree<Integer> treeI;
    Tree<String> treeS;

    @BeforeEach
    void setUp() {
        treeI = new Tree<Integer>(1);
        treeS = new Tree<String>("Root");
    }

    @Test
    void testSetValueException() {
        assertThrows(IllegalArgumentException.class, () -> {
            treeS.setNodeValue(null);
        });
    }

    @Test
    void testGetSetValueI() {
        treeI.setNodeValue(5);
        assertEquals(5, treeI.getNodeValue());
    }

    @Test
    void testGetSetValueS() {
        treeS.setNodeValue("Root0");
        assertEquals("Root0", treeS.getNodeValue());
    }

    @Test
    void testAddChildSubtreeSizeI() {
        Tree<Integer> child = new Tree<>(2);
        treeI.addChild(child);
        assertEquals(1, treeI.nodeChildren.size());
    }

    @Test
    void testAddChildSubtreeGetI() {
        Tree<Integer> child = new Tree<>(2);
        treeI.addChild(child);
        assertEquals(child, treeI.nodeChildren.get(0));
    }

    @Test
    void testAddChildSubtreeParentI() {
        Tree<Integer> child = new Tree<>(2);
        treeI.addChild(child);
        assertEquals(treeI, child.getNodeParent());
    }


    @Test
    void testAddChildSubtreeSizeS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        assertEquals(1, treeS.nodeChildren.size());
    }

    @Test
    void testAddChildSubtreeGetS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        assertEquals(child, treeS.nodeChildren.get(0));
    }

    @Test
    void testAddChildSubtreeParentS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        assertEquals(treeS, child.getNodeParent());
    }

    @Test
    void testAddChildLeafSizeI() {
        treeI.addChild(3);
        assertEquals(1, treeI.nodeChildren.size());
    }

    @Test
    void testAddChildLeafParentI() {
        Tree<Integer> leaf = treeI.addChild(3);
        assertEquals(3, leaf.getNodeValue());
    }


    @Test
    void testAddChildLeafSizeS() {
        treeS.addChild("RootChild");
        assertEquals(1, treeS.nodeChildren.size());
    }

    @Test
    void testAddChildLeafParentS() {
        Tree<String> leaf = treeS.addChild("RootChild");
        assertEquals("RootChild", leaf.getNodeValue());
    }

    @Test
    void testRemoveI() {
        Tree<Integer> child = new Tree<>(2);
        treeI.addChild(child);
        treeI.remove();
        assertNull(treeI.getNodeValue());
    }

    @Test
    void testRemoveParentI() {
        Tree<Integer> child = new Tree<>(2);
        treeI.addChild(child);
        treeI.remove();
        assertNull(treeI.getNodeParent());
    }

    @Test
    void testRemoveSizeI() {
        Tree<Integer> child = new Tree<>(2);
        treeI.addChild(child);
        treeI.remove();
        assertEquals(0, treeI.nodeChildren.size());
    }

    @Test
    void testRemoveS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        treeS.remove();
        assertNull(treeS.getNodeValue());
    }

    @Test
    void testRemoveParentS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        treeS.remove();
        assertNull(treeS.getNodeParent());
    }


    @Test
    void testRemoveChildS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        treeS.remove();
        assertEquals("RootChild", child.getNodeValue());
    }

    @Test
    void testRemoveChildParentS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        treeS.remove();
        assertNull(child.getNodeParent());
    }

    @Test
    void testRemoveSizeS() {
        Tree<String> child = new Tree<>("RootChild");
        treeS.addChild(child);
        treeS.remove();
        assertEquals(0, treeS.nodeChildren.size());
    }

    @Test
    void testEqHashCodeI() {
        Tree<Integer> sameTree = new Tree<>(1);
        assertEquals(treeI.hashCode(), sameTree.hashCode());
    }

    @Test
    void testHashCodeI() {
        Tree<Integer> diffTree = new Tree<>(2);
        assertNotEquals(treeI.hashCode(), diffTree.hashCode());
    }

    @Test
    void testEqHashCodeS() {
        Tree<String> sameTree = new Tree<>("Root");
        assertEquals(treeS.hashCode(), sameTree.hashCode());
    }

    @Test
    void testHashCodeS() {
        Tree<String> diffTree = new Tree<>("AnotherRoot");
        assertNotEquals(treeS.hashCode(), diffTree.hashCode());
    }

    @Test
    void testEqualsI() {
        Tree<Integer> sameTree = new Tree<>(1);
        assertEquals(treeI, sameTree);
    }

    @Test
    void testNotEqualsI() {
        Tree<Integer> diffTree = new Tree<>(2);
        assertNotEquals(treeI, diffTree);
    }

    @Test
    void testEqualsS() {
        Tree<String> sameTree = new Tree<>("Root");
        assertEquals(treeS, sameTree);
    }

    @Test
    void testNotEqualsS() {
        Tree<String> diffTree = new Tree<>("AnotherRoot");
        assertNotEquals(treeS, diffTree);
    }

    @Test
    void testDifGenEquals() {
        Tree<Integer> diffTree = new Tree<>(2);
        assertNotEquals(treeS, diffTree);
    }

    @Test
    void testNullEquals() {
        assertNotEquals(treeI, null);
    }

    @Test
    void testBfs() {
        Tree<Integer> child1 = treeI.addChild(2);
        Tree<Integer> child2 = treeI.addChild(3);
        child1.addChild(4);
        child2.addChild(5);

        Iterable<Integer> bfs = treeI.bfs();
        Iterator<Integer> itr = bfs.iterator();

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> result = new ArrayList<>();
        while (itr.hasNext()) {
            result.add(itr.next());
        }

        assertEquals(expected, result);
    }

    @Test
    void testDfs() {
        Tree<String> child1 = treeS.addChild("B");
        Tree<String> child2 = treeS.addChild("A");
        child1.addChild("B_child");
        child2.addChild("A2");
        child2.addChild("A1");

        Iterable<String> bfs = treeS.dfs();
        Iterator<String> itr = bfs.iterator();

        ArrayList<String> expected =
                new ArrayList<>(Arrays.asList("Root", "A", "A1", "A2", "B", "B_child"));

        ArrayList<String> result = new ArrayList<>();
        while (itr.hasNext()) {
            result.add(itr.next());
        }

        assertEquals(expected, result);
    }


    @Test
    void testBfsConcurrentExceptionBeforeTraverse() {
        Tree<String> child1 = treeS.addChild("B");
        Tree<String> child2 = treeS.addChild("A");
        child1.addChild("B_child");
        child2.addChild("A2");
        child2.addChild("A1");


        ArrayList<String> result = new ArrayList<>();
        for (String curVal : treeS.bfs()) {
            if (curVal.equals("A2"))
                child2.remove();
            result.add(curVal);
        }
        ArrayList<String> expected =
                new ArrayList<>(Arrays.asList("Root", "B", "A", "B_child", "A2", "A1"));
        assertEquals(expected, result);
    }

    /*
        Здесь мы пытаемся удалить вершину ниже текущего обхода BFS, поэтому получим ConcurrentModificationException
     */
    @Test
    void testBfsConcurrentExceptionAfterTraverse() {
        Tree<String> child1 = treeS.addChild("B");
        Tree<String> child2 = treeS.addChild("A");
        child1.addChild("B_child");
        child2.addChild("A2");
        child2.addChild("A1");


        assertThrows(ConcurrentModificationException.class, () -> {
            for (String curVal : treeS.bfs()) {
                if (curVal.equals("Root")) {
                    child2.remove();
                }
            }
        });
    }

    /*
        У DFS корень до самого конца считается находящимся в обходе
        так что здесь при удалении хоть выше, хоть ниже должно быть исключение.
     */
    @Test
    void testDfsConcurrentExceptionBeforeTraverse() {
        Tree<Integer> child1 = treeI.addChild(1);
        Tree<Integer> child2 = treeI.addChild(2);
        child1.addChild(5);
        child2.addChild(4);
        child2.addChild(3);


        assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer curVal : treeI.dfs()) {
                if (curVal == 1) {
                    child2.remove();
                }
            }
        });
    }

    @Test
    void testDfsConcurrentExceptionAfterTraverse() {
        Tree<Integer> child1 = treeI.addChild(1);
        Tree<Integer> child2 = treeI.addChild(2);
        child1.addChild(5);
        child2.addChild(4);
        child2.addChild(3);


        assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer curVal : treeI.dfs()) {
                if (curVal == 5) {
                    treeI.remove();
                }
            }
        });
    }

    /*
        Когда обход полностью завершился, мы снова должны иметь возможность удалять вершины
     */
    @Test
    void testDfsConcurrentExceptionAfterTraverseEnd() {
        Tree<Integer> child1 = treeI.addChild(1);
        Tree<Integer> child2 = treeI.addChild(2);
        child1.addChild(5);
        child2.addChild(4);
        child2.addChild(3);

        ArrayList<Integer> result = new ArrayList<>();
        for (Integer curVal : treeI.dfs()) {
            result.add(curVal);
        }
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1, 5));
        assertEquals(expected, result);

        child2.remove();
        result.clear();
        for (Integer curVal : treeI.dfs()) {
            result.add(curVal);
        }
        expected = new ArrayList<>(Arrays.asList(1, 1, 5));
        assertEquals(expected, result);
    }

    /*
        Для BFS удалим все листья, чтобы удостовериться в полной разблокировке
     */
    @Test
    void testBfsConcurrentExceptionAfterTraverseEnd() {
        Tree<String> child1 = treeS.addChild("B");
        Tree<String> child2 = treeS.addChild("A");
        Tree<String> forDeletion1 = child1.addChild("B_child");
        Tree<String> forDeletion2 = child2.addChild("A2");
        Tree<String> forDeletion3 = child2.addChild("A1");


        ArrayList<String> result = new ArrayList<>();
        for (String curVal : treeS.bfs()) {
            result.add(curVal);
        }
        ArrayList<String> expected =
                new ArrayList<>(Arrays.asList("Root", "B", "A", "B_child", "A2", "A1"));
        assertEquals(expected, result);

        forDeletion1.remove();
        forDeletion2.remove();
        forDeletion3.remove();
        result.clear();
        for (String curVal : treeS.bfs()) {
            result.add(curVal);
        }
        expected = new ArrayList<>(Arrays.asList("Root", "B", "A"));
        assertEquals(expected, result);
    }
}
