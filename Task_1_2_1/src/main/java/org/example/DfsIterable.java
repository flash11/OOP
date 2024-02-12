package org.example;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an iterable interface for depth-first traversal of a Tree.
 */
public class DfsIterable<T> implements Iterable<T> {

    /**
     * Implements an iterator interface for depth-first traversal of a Tree.
     */
    private class DfsIterator implements Iterator<T> {

        // Stack(Deque) used to store nodes in depth-first order.
        public ArrayDeque<Tree<T>> dfsStack;

        /**
         * Constructor. Initializes the queue with the root node.
         */
        public DfsIterator() {
            dfsStack = new ArrayDeque<Tree<T>>();
            dfsStack.add(treeNode);
            treeNode.setTraverseProtection(true);
        }

        /**
         * Returns true if the iteration has more elements.
         * In other words, returns true if next() would return an element.
         * Rather than throwing an exception.
         *
         * @return true if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            if (dfsStack.isEmpty()) {
                treeNode.setTraverseProtection(false);
            }
            return !dfsStack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> currentNode = dfsStack.pollLast();
            dfsStack.addAll(currentNode.nodeChildren);
            return currentNode.getNodeValue();
        }
    }

    // The tree to traverse.
    private final Tree<T> treeNode;

    /**
     * Constructor.
     *
     * @param treeNode The tree to traverse.
     */
    public DfsIterable(Tree<T> treeNode) {
        this.treeNode = treeNode;
    }

    /**
     * Returns an iterator over elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new DfsIterator();
    }
}
