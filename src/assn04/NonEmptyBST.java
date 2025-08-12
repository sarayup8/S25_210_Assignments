package assn04;

import java.sql.SQLOutput;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: complete the insert method
	@Override
	public BST<T> insert(T element){
		if (element.compareTo(_element) < 0) {
			if (_left.isEmpty()) {
				NonEmptyBST<T> bst = new NonEmptyBST<>(element);
				_left = bst;
			}
			else
				_left.insert(element);
		} else if (element.compareTo(_element) > 0) {
			if(_right.isEmpty()) {
				NonEmptyBST<T> bst = new NonEmptyBST<>(element);
				_right = bst;
			}
			else {
				_right.insert(element);
			}
		}
		// return the new structure back up to the previous frame to update the tree
		return this; // change such default lines as necessary
	}


	// TODO: printInOrderTraversal
	@Override
	public void printInOrderTraversal() {
		// left - current - right
		if (!_left.isEmpty()) {
			_left.printInOrderTraversal();
		}
		System.out.print(_element + " ");

		if (!_right.isEmpty()) {
			_right.printInOrderTraversal();
		}
	}


	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		// current - left - right

		// print current element
		System.out.print(_element + " ");
		if (!_left.isEmpty()) {
			_left.printPreOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPreOrderTraversal();
		}
	}


	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		// left - right - current
		if (!_left.isEmpty()) {
			_left.printPostOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPostOrderTraversal();
		}
		System.out.print(_element + " ");
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(_element) > 0) {
			_right = _right.remove(element);
		} else if (element.compareTo(_element) < 0) {
			_left = _left.remove(element);
		} else {  // remove (3 initial cases):
			// case 1: leaf node
			if (_left.isEmpty() && _right.isEmpty())
				return new EmptyBST<>();
			// case 2: one child node
			// return the child node in place of the value you're trying to return
			if (_left.isEmpty() && !_right.isEmpty()) {
				return _right;
			} else if (!_left.isEmpty() && _right.isEmpty()) {
				return _left;
			}
			// case 3: two children nodes
			// call findMin() to find the smallest node on the right
			if (!_left.isEmpty() && !_right.isEmpty()) {
				_element = _right.findMin();
				// call remove to remove the duplicate --> go down to right and then call remove to remove the duplicate and not the one you replaced
				_right = _right.remove(_element);
			}
		}
		return this; // change such default lines as necessary
	}

	// TODO: findMin
	@Override
	public T findMin() {
		// keep going down the left until you don't have a left child
		if (_left.isEmpty()) {
			return _element;
		} else {
			return _left.findMin();
		}
	}

	// TODO: replaceRange
	@Override
	public BST<T> replaceRange(T start, T end, T newValue) {
		this.removeRange(start, end);

		// insert the newValue if it's outside the range
		if ((newValue.compareTo(start) < 0) || (newValue.compareTo(end) > 0)) {
			this.insert(newValue);
		}
		return this; // change such default lines as necessary
	}


	// helper method --> make them compatible with recursive calls
	public BST<T> removeRange(T start, T end) {
		// case 1: _element is below start --> anything to the left will also be outside of the range
		// remove everything in the range in the right tree only
		if (_element.compareTo(start) < 0) {
			_right = _right.removeRange(start, end);

		// case 2: _element is above end
		} else if (_element.compareTo(end) > 0) {
			_left = _left.removeRange(start, end);

		// case 3: _element is between start and end
		} else {
			_left = _left.removeRange(start, end);
			_right = _right.removeRange(start, end);
			return this.remove(_element);
		}
		return this; // change such default lines as necessary
	}



	//====================================================================
	// Do not change the methods below
	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}


// a
// b
// a.compareTo(b)
// if a > b: 1, if a < b: -1, if a == b: 0