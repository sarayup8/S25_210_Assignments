package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * 1st constructor that creates an empty max heap of prioritized elements.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * @return size of heap
     */
    @Override
    public int size() {
        return _heap.size();
    }

    /**
     * TODO (Task 2A): enqueue using value and priority
     * @param value
     * @param priority
     */
    @Override
    public void enqueue(V value, P priority) {
        Patient<V, P> patient = new Patient<>(value, priority);
        _heap.add(patient);
        bubbleUp(_heap.size() - 1);
    }

    /**
     * TODO (Task 2A): enqueue (overloaded) using only value
     * @param value
     */
    public void enqueue(V value) {
        Patient<V, P> patient = new Patient<>(value);
        _heap.add(patient);
        bubbleUp(_heap.size() - 1);
    }

    // helper function
    public void swap(int parent, int child)  {
        Prioritized<V, P> temp = _heap.get(parent);
        _heap.set(parent, _heap.get(child));
        _heap.set(child, temp);
    }

    // helper function
    public void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (_heap.get(parentIndex).compareTo(_heap.get(index)) < 0) {
            swap(parentIndex, index);
            bubbleUp(parentIndex);
        }
    }

    // helper function
    public void bubbleDown(int index) {
        int rightChild = 2*index + 2;
        int leftChild = 2*index + 1;
        int largerChild;

        // no children exist
        if (leftChild >= _heap.size()) {
            return;
        }

        if ((rightChild < _heap.size())) {
            if (_heap.get(rightChild).compareTo(_heap.get(leftChild)) > 0) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
        } else {
            largerChild = leftChild;
        }

        if (_heap.get(index).compareTo(_heap.get(largerChild)) < 0) {
            swap(index, largerChild);
            bubbleDown(largerChild);
        }
    }

    /**
     * TODO (Task 2A): dequeue from maxBinHeapER
     * @return element with highest priority
     */
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        } else {
            V maxValue = _heap.get(0).getValue();
            _heap.set(0, _heap.get(_heap.size() - 1));
            _heap.remove(_heap.size() - 1);
            bubbleDown(0);
            return maxValue;
        }
    }

    /**
     * TODO (Task 2A): getMax
     * @return return value of element with the highest priority
     */
    @Override
    public V getMax() {
        if (_heap.isEmpty()) {
            return null;
        } else {
            return _heap.get(0).getValue();
        }
    }

    /**
     * TODO (Task 2B): updatePriority
     * Change the priority of the patient with the given value.
     * if newPriority is <0 then remove the element with given value
     * (In case value is not matching and existing Priority < 0, nothing is to be done.)
     * @param value
     * @param newPriority
     */
    public void updatePriority(V value, P newPriority) {
        for (int i = 0; i < _heap.size(); i++) {
            Prioritized<V, P> placeholder = _heap.get(i);

            if (placeholder.getValue().equals(value)) {
                if (newPriority.compareTo((P)(Integer)0) < 0 ) {
                    _heap.remove(i);
                    bubbleDown(i);
                    return;
                }
                Patient<V, P> update = new Patient<>(value, newPriority);
                if (newPriority.compareTo(_heap.get(i).getPriority()) > 0) {
                    _heap.set(i, update);
                    bubbleUp(i);
                } else {
                    _heap.set(i, update);
                    bubbleDown(i);
                }
            }
        }
    }

    /**
     * TODO (Task 3): MaxBinHeapER
     * 2nd constructor that builds a heap given an initial array of prioritized elements.
     * @param initialEntries This is an initial ArrayList of patients
     */
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();

        // add all elements rather than enqueue to use a build-heap
        for (Prioritized<V,P> patient: initialEntries) {
            _heap.add(patient);
        }

        for (int i = ((_heap.size() - 2) / 2); i >= 0; i--) {
            bubbleUp(i);
        }
    }

    /**
     * Retrieves contents of heap as an array.
     * @return array of Prioritized elements in the order stored in the heap
     */
    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
}

