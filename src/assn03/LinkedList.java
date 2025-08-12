package assn03;

// Starter Code provided with Assignment #3 for COMP210

public class LinkedList {
    private Node _head = null;
    private Node _tail = null;
    private int _size = 0;

    /**
     * Task 1
     * Simply merge given linked (list2) at the end of the current list.
     *
     * Note: Do NOT create and return a new list, merge the second list at the end of the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 2 -> 3 -> 4 -> 5 -> 6
     *
     * @param list2 - list to be merged
     */
    public void simpleMerge(LinkedList list2) {
        // tail._next for the current list is Null. Set tail._next to the _head of list2
        _tail.setNext(list2._head);
        // update the _tail pointer
        _tail = list2._tail;
        // update _size
        _size = _size + list2._size;
    }

    /**
     * Task 2
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the _size of the list, throw an IndexOutOfBounds Exception
     *
     * ex: list: A -> B -> C -> D
     *     i: 1
     *     list after removeAtIndex: A -> C -> D
     *
     * @param i    - index of node to remove
     */
    public void removeAtIndex(int i) {
        // if index is out of bounds
        if (i >= _size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == 0) {
            _head = _head.getNext();
            _size--;
            return;
        }
        // make a pointer to iterate through the linked list
        Node curr = _head;
        // move forward until you reach the index prior to the one you're deleting
        for (int index = 0; index < i - 1; index++) {
            curr = curr.getNext();
        }
        curr.setNext(curr.getNext().getNext());
    }


    /**
     * Task 3
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same _size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     *      list2: 1 -> 4 -> 2
     *      return: true
     *
     *      list: 1 -> 5
     *      list2: 2 -> 5
     *      return false;
     *
     * @param list2 - the list to compare with the current list
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList list2) {
        // if the linked lists are not the same size, they are not identical
        if (_size != list2._size) {
            return false;
        }
        Node curr1 = _head;
        Node curr2 = list2._head;
        while (curr1 != null && curr2 != null ) {
            if (curr1.getValue() != curr2.getValue()) {
                return false;
            // if value matches, move curr pointers forward
            } else {
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
            }
        }
        return true;    // Change this statement as required
    }



    /**
     * Task 4
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        Node curr = _head;
        // Always check if the node is null before getting the value
        while (curr != null && curr.getNext() != null) {
            if (curr.getValue() == curr.getNext().getValue()) {
                Node ptr = curr;
                while (ptr != null && ptr.getValue() == ptr.getNext().getValue()) {
                    ptr = ptr.getNext();
                }
                curr.setNext(ptr.getNext());
            } else {
                curr = curr.getNext();
            }
        }
    }


     /**
     * Task 5
     * Reverse the list.
     * eg list:  10 -> 9 -> 8 -> 7
     * list after reverse: 7 -> 8 -> 9 -> 10
     */
    public void reverse() {
        // if the linked list is empty or only has one element, you don't need to reverse it
        if (_head == null || _head.getNext() == null) {
            return;
        }
        Node prev = null;
        Node curr = _head;
        Node next = null;

        while (curr != null) {
            // keep track of the next value
            next = curr.getNext();
            // reverse the nodes
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        _head = prev;
    }


    /**
     * Task 6
     * Merge the given linked list2 into the current list. The 2 lists will always be
     * either the same _size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     return 1 -> 5 -> 2 -> 6 -> 3 -> 4
     *
     * @param list2
     */
    public void merge(LinkedList list2) {
        if (list2._head == null) {
            return;
        }

        Node curr1 = _head;
        Node curr2 = list2._head;
        Node next1 = curr1.getNext();
        Node next2 = curr2.getNext();

        while (next1 != null && next2 != null) {
            curr1.setNext(curr2);
            curr2.setNext(next1);
            // move all pointers forward
            curr1 = next1;
            next1 = next1.getNext();
            curr2 = next2;
            next2 = next2.getNext();
        } if (next2 == null) {
            curr1.setNext(curr2);
            curr2.setNext(next1);
        }
    }


    /* Implementations below are being given to you. Do not modify below this. */

    public int size() {
        return _size;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public void clear() {
        _head = null;
        _tail = null;
        _size = 0;
    }

    public boolean contains(int element) {
        Node current = _head;
        while(current != null) {
            if(current.getValue() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int[] toArray() {
        int[] arr =   new int[size()];
        Node current = _head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(int element) {
        Node newNode = new NodeImpl(element, null);
        if(isEmpty()) {
            _head = newNode;
            _tail = newNode;
            _size++;
        } else {
            _tail.setNext(newNode);
            _tail = newNode;
            _size++;
        }

    }

    public boolean remove(int element) {
        Node current = _head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            _head = _head.getNext();
            _size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            _tail = current;
        }
        current.setNext(current.getNext().getNext());
        _size--;
        return true;
    }

    public int get(int index) {
        validIndex(index);
        Node current = _head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public int set(int index, int element) {
        validIndex(index);
        Node current = _head;
        int prevValue = 0;
        int i = 0;
        if(index == 0) {
            prevValue = _head.getValue();
            _head.setValue( element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue( element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, int element) {
        if(index > _size) {
            validIndex(index);
        }
        Node current = _head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node newNode = new NodeImpl( element, _head.getNext());
                _head = newNode;
                _size++;
                return;
            }

        }  else if(index == _size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node temp = current.getNext();
                Node newNode = new NodeImpl( element, temp);
                current.setNext(newNode);
                _size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(int element) {
        Node current = _head;
        int index = 0;
        while(current != null) {
            if(current.getValue() == element) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(int element) {
        Node current = _head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue() == element) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= _size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node gethead() {
        return _head;
    }

    @Override
    public String toString() {
        String list = "";
        Node current = _head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}