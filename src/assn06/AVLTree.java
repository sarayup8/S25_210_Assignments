package assn06;

import static java.lang.Math.max;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }

    //==================================================================
    // The methods below need to be completed.
    // Refer to the SelfBalancingTree interface for the descriptions.
    //==================================================================


    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 AVLTree<T> newRootNode = _right;
         AVLTree<T> newLeftChild = new AVLTree<T>();
         newLeftChild._value = _value;
         newLeftChild._left = _left;

         // edge case --> The right node has a left child that would "disappear" when rotating
         newLeftChild._right = newRootNode._left;

         newRootNode._left = newLeftChild;

         newLeftChild.update();
         newRootNode.update();

         return newRootNode;
     }


    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
         AVLTree<T> newRootNode = _left;
         AVLTree<T> newRightChild = new AVLTree<T>();
         newRightChild._value = _value;
         newRightChild._right = _right;

         // edge case --> The left node has a right child that would "disappear" when rotating
         newRightChild._left = newRootNode._right;

         newRootNode._right = newRightChild;

         newRightChild.update();
         newRootNode.update();

         return newRootNode;
     }

    private void update() {
        int heightLeft;
        int sizeLeft;
        if (_left == null || _left.isEmpty()) {
            heightLeft = -1;
            sizeLeft = 0;
        } else {
            heightLeft = _left._height;
            sizeLeft = _left._size;
        }
        int heightRight;
        int sizeRight;
        if (_right == null || _right.isEmpty()) {
            heightRight = -1;
            sizeRight = 0;
        } else {
            heightRight = _right._height;
            sizeRight = _right._size;
        }
        _height = 1 + max(heightLeft, heightRight);
        _size = 1 + sizeLeft + sizeRight;
    }

    private AVLTree<T> balance() {
        int heightLeft;
        if (_left == null || _left.isEmpty()) {
            heightLeft = -1;
        } else {
            heightLeft = _left._height;
        }
        int heightRight;
        if (_right == null || _right.isEmpty()) {
            heightRight = -1;
        } else {
            heightRight = _right._height;
        }
        int balanceFactor = heightLeft - heightRight;

        if (balanceFactor > 1) {
            int leftChildLeftHeight;
            if (_left == null || _left._left == null || _left._left.isEmpty()) {
                leftChildLeftHeight = -1;
            } else {
                leftChildLeftHeight = _left._left._height;
            }

            int leftChildRightHeight;
            if (_left == null || _left._right == null || _left._right.isEmpty()) {
                leftChildRightHeight = -1;
            } else {
                leftChildRightHeight = _left._right._height;
            }

            int leftChildBalance = leftChildLeftHeight - leftChildRightHeight;
            if (leftChildBalance < 0) {
                _left = _left.rotateLeft();
            }
            return rotateRight();

        } else if (balanceFactor < -1) {
                int rightChildLeftHeight;
                if (_right == null || _right._left == null || _right._left.isEmpty()) {
                    rightChildLeftHeight = -1;
                } else {
                    rightChildLeftHeight = _right._left._height;
                }

                int rightChildRightHeight;
                if (_right == null || _right._right == null || _right._right.isEmpty()) {
                    rightChildRightHeight = -1;
                } else {
                    rightChildRightHeight = _right._right._height;
                }

                int rightChildBalance = rightChildLeftHeight - rightChildRightHeight;
                if (rightChildBalance > 0) {
                    _right = _right.rotateRight();
                }
                return rotateLeft();
            }
        return this;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (this.isEmpty()) {
            _value = element;
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            _height = 0;
            _size = 1;
            return this;
        }

        if (_value.compareTo(element) > 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (_value.compareTo(element) < 0) {
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            return this;
        }
        update();
        return balance();
    }

    // _right = rotateRight(...)

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	if (this.isEmpty()) {
            return this;
        }
        if (_value.compareTo(element) > 0) {
            _left = (AVLTree<T>) _left.remove(element);
        } else if (_value.compareTo(element) < 0) {
            _right = (AVLTree<T>) _right.remove(element);
        } else {
            // _value == element
            if (_left.isEmpty() && _right.isEmpty()) { // if the element is a leaf, return an empty AVL tree
                return new AVLTree<>();
            } else if (_left.isEmpty()) { // if there is one child, replace the element with the child
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            } else { // two children --> replace with smallest number on right side
                _value = _right.findMin();
                _right = (AVLTree<T>) _right.remove(_right.findMin());
            }
        }
        update();
        return balance();
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_left == null || _left.isEmpty()) {
            return _value;
        }

        return _left.findMin();
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right == null || _right.isEmpty()) {
            return _value;
        }

        return _right.findMax();
     }

    @Override
    public boolean contains(T element) {
        if (this.isEmpty()) {
            return false;
        }
        if (_value.compareTo(element) > 0) {
            return _left.contains(element);
        } else if (_value.compareTo(element) < 0) {
            return _right.contains(element);
        } else {
            return true;
        }
    }

    @Override
    public boolean rangeContain(T start, T end) {
        int s = (Integer) start;
        int e = (Integer) end;
        for (int i = s; i <= e; i++) {
            if (!contains((T) Integer.valueOf(i))) {
                return false;
            }
        }
        return true;
    }

}
