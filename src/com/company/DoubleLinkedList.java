package com.company;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> extends AbstractSequentialList<E> {

    private Node<E> mHead = null;
    private Node<E> mTail= null;
    private int size = 0;


    public boolean isEmpty(){
        return mHead==null;
    }

    /*Add an item at position index
      @param index The position at which the object is to be inserted
     * @param obj The object to be inserted
     * @throw IndexOutOfBoundsException if the index is out of range (i < 0 || i > size() */
    public void add(int index, E obj) {

        listIterator(index).add(obj);
    }

    //same has add but we use a literal for the index
    public void addFirst(E obj) {

        add(0, obj);
    }


    public void addLast(E obj) {

        add(size, obj);
    }

    public E get(int i) {
        if(i < 0 || i >=size)
        {
            throw new IndexOutOfBoundsException();
        }
        //create new iterator at index i - iter will be constructed at that index, which is just before the
        // .data that we want to return so return iter.next
        ListIterator<E> iter = listIterator(i);
        return iter.next();

    }

    public E getFirst() {

        return (E) mHead.mData;
    }

    public E getLast() {

        return (E) mTail.mData;
    }

    public int size() {

        return size;
    }

    //helper method remove() in ListIter class
    public E remove(int i) {

        //instantiate a new object = to null
        E returnValue = null;
        //new ListIterator at the specified index
        ListIterator<E> iter = listIterator(i);
        //loop through list
        if (iter.hasNext())
        {
            returnValue = iter.next();
            //remove that item
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;

    }

    public ListIterator<E> listIterator(){
        return new ListIter(0);
    }
    public ListIterator<E> listIterator(int index){
        return new ListIter(index);
    }
    public ListIterator<E> listIterator(ListIter iter){
        return new ListIter( iter);
    }

    /***************Inner ClassListIterator*********************/
    protected class ListIter implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;

        //consturctor - will reference the nth item. @param i The index of the item to be reference
        public ListIter(int i) {
            //validate i parameter
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null;  //no item returned yet
            //special case of last item- then there's nothing to return, we set index(the iterator to size) and set nextItem to null
            if (i == size) {
                index = size;
                nextItem = null;
            } else { // if it's not the same as size we need to loop through to get our index positioned
                //start at head
                nextItem = mHead;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.mNext;
                }
            }

        }

        public ListIter(ListIter other)
        {
            nextItem = other.nextItem;
            index = other.index;
            lastItemReturned = other.lastItemReturned;
        }
        //void add(E e)
        /**
         * Inserts object obj into the list just before the item that would be returned by the next call to method
         * next and after the item that would have been returned by
         * method previous. If the method previous is called after add, the newly inserted obj will be returned
         * 4 special cases: add to empty list, add to head of list, add to tail of list and add to middle of the list
         */
        public void add(E e) {
            //add to an empty list - an empty list is indicated by head equal to null
            if (mHead == null) {
                mHead = new Node<>(e);
                mTail = mHead;
            }
            //insert at the head
            else if (nextItem == mHead) {
                //create new node
                Node<E> newNode = new Node<>(e);
                //link it to the nextItem
                newNode.mNext = nextItem;
                //link  nextItem to the new Node
                nextItem.mPrevious = newNode;
                //the new node is now the head
                mHead = newNode;
            }
            //insert at the tail
            else if (nextItem == null) {
                //create a new node
                Node<E> newNode = new Node<>(e);
                //link the tail to the new node
                mTail.mNext = newNode;
                //link the new node to the tail
                newNode.mPrevious = mTail;
                //the new node is the new tail
                mTail = newNode;
                mTail.mNext = mHead;
            }
            //insert at the middle
            else {
                //create new node
                Node<E> newNode = new Node<>(e);
                //link it to nextItem.prev
                newNode.mPrevious = nextItem.mPrevious;
                nextItem.mPrevious.mNext = newNode;
                //link it to the nextItem
                newNode.mNext = nextItem;
                nextItem.mPrevious = newNode;
            }

            //after the new node is inserted, both size and index are incremented and lastItemReturned is set to null
            size++;
            index++;
            lastItemReturned = null;
        }

        /**
         * returns ture if next will not throw an exception
         */
        public boolean hasNext() {
            //determine if nextItem is null
            return nextItem != null;
        }

        /***returns the next object and moves the iterator forward. If the iterator is at the end, the NoSuchElementException is thrown*/
        public E next() {
            //call hasNext() if result is fasle throw NoSuchElementException
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            //else lastItemReturned is set to nextItem
            lastItemReturned = nextItem;
            //nextItem is advanced to the next node
            nextItem = nextItem.mNext;
            //and we increment the index by 1
            index++;
            //lastItemReturned is returned
            return lastItemReturned.mData;
        }


        /**
         * returns true if previous will not throw an exception
         * Can determine that there is a previous item by checking the size - a non-empty list wil have a previous item when the iterator is at the end
         * if not at the end, then nextItem is not null, and we can check for a previous item by examining nextItem.prev
         */
        public boolean hasPrevious() {
            //if size == 0 its an empty list, we can't have a prev AND if the next item is null then we are at tail
            //if nextItem.previous is null then there is no previous data
            return size != 0 && (nextItem == null  || nextItem.mPrevious != null);
        }

        /**
         * returns the previous object and moves the iterator backward. If the iterator is at the beginning of th elist, the NoSuchElementException is thrown
         */
        public E previous() {
            //begins by calling hasPrevious if false throw error
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            //if nextItem is null the iter is past the last element so nextItem is set to tail b.c the previous element must be the last list element
            if (nextItem == null) {
                nextItem = mTail;
            }
            //else - if nextItem is not null, nextItem is set to nextItem.prev
            else {
                nextItem = nextItem.mPrevious;
            }
            //either way lastItemReturned is set to nextItem and index is decremented. The data field of the node lastItemReturned is returned
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.mData;

        }


        /**
         * returns the index of the item that will be returned by the next call to next. If the iterator is at the end, the list size is returned
         */
        public int nextIndex() {
            return  index ;
        }


        /**
         * Returns the index of the item taht will be returned by the nex call to previous. If the iterator is at the beginning of the list, -1 is returned
         */
        public int previousIndex() {
            return index-1;
        }


        /**
         * removes the last item returned from a call to next or previous. If a call to remove is not preceded by a call to next or previous,
         * the IllegalStateException is thrown
         */
        public void remove() {

            if(lastItemReturned == null)
            {
                throw new IllegalStateException();
            }
            //if we are at the start of the list
            if(lastItemReturned.mPrevious == null) {

                if (size > 1) {
                    //remove the head by setting the next node to the head
                    mHead = lastItemReturned.mNext;
                    //and the new head's prev to null (the head's prev is always null, one way we know it's the head)
                    mHead.mPrevious = null;
                }
                else { //size is 0 so all that is left is the head, set to null to remove
                    mHead = null;
                }
            }

            //if from the tail
            else if (lastItemReturned.mNext == null) {
                //remove the tail, set the tail to the tails previous, removing the link
                mTail = mTail.mPrevious;
                //set mTail.next to null
                mTail.mNext = mHead;

            }

            //if in the middle
            else {

                //first set the prev node to lastItemReturned's next to lastItemReturned.next
                lastItemReturned.mPrevious.mNext = lastItemReturned.mNext;
                //now need to connect lastItemReturned's next prev slot with lastItemReturned's prev
                lastItemReturned.mNext.mPrevious = lastItemReturned.mPrevious;
            }
            size--;

        }//remove()

        /**
         * replaces the last item returned from a call to next or previous with obj. If a call to set is not precedded
         * by a call to next or previous, the IllegalStateException is thrown
         */
        public void set(E obj) {
            if(lastItemReturned == null)
            {
                throw new IllegalStateException();
            }
            lastItemReturned.mData = obj;

        }

    }


    /************Inner Class Node***********/
    /**
     * static indicates that the class will not reference it's outer class(it can't b.c it has no methods other than consturctors
     */
    private static class Node<E> {
        E mData; // is this like data?
        Node<E> mPrevious = null, mNext = null;


        Node(E data) {
            mData = data;
        }

    }//inner class
}//outer class

