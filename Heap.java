package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
	int size = 0;
	ArrayList<INode<T>> heap;

	@Override
	public INode<T> getRoot() {

		if (size > 1) {
			return heap.get(1);
		}
		return null;
	}

	@Override
	public int size() {

		if (size <= 1) {
			return 0;
		}
		return size - 1;
	}

	@Override
	public void heapify(final INode<T> node) {
		if (node == null) {
			throw new RuntimeException();
		}
		INode<T> left = null;
		INode<T> right = null;
		INode<T> largest = null;
		if (((HeapNode) (node)).getIndex() * 2 < size && ((HeapNode) (node)).getIndex() * 2 + 1 < size) {
			left = heap.get(((HeapNode) (node)).getIndex() * 2);
			right = heap.get(((HeapNode) (node)).getIndex() * 2 + 1);
			largest = (left.getValue().compareTo(right.getValue()) > 0) ? left : right;
		} else if (((HeapNode) (node)).getIndex() * 2 < size) {
			left = heap.get(((HeapNode) (node)).getIndex() * 2);
			largest = left;

		}
		if (largest != null) {
			if (node.getValue().compareTo(largest.getValue()) < 0) {
				final T temp = largest.getValue();
				largest.setValue(node.getValue());
				node.setValue(temp);
				heapify(largest);
			}
		}

	}

	@Override
	public T extract() {
		if (size == 2) {
			size = 0;
			return (heap.get(1)).getValue();
		}
		if (size > 1) {
			final T max = heap.get(1).getValue();
			heap.get(1).setValue(heap.get(size - 1).getValue());
			if (heap.get(size - 1).getParent() != null) {
				final INode<T> parent = heap.get(size - 1).getParent();
				if (parent.getRightChild() != null) {
					((HeapNode) parent).setRightChild(null);
				} else {
					((HeapNode) parent).setLeftChild(null);

				}
			}
			heap.set(size - 1, null);

			size--;
			heapify(getRoot());
			return max;

		}
		return null;
	}

	@Override
	/*
	 * public void insert(final T element) { if(size>1){ INode<T> child=new
	 * HeapNode(); child.setValue(element); ((HeapNode)child).setIndex(size);
	 * heap.add(size, child); size++; INode<T> parent = heap.get(((HeapNode)
	 * child).getIndex()/2);
	 * while(parent!=null&&parent.getValue().compareTo(child.getValue())<0){
	 * final T temp = parent.getValue(); parent.setValue(child.getValue());
	 * child.setValue(temp); child=parent; parent=heap.get(((HeapNode)
	 * child).getIndex()/2); }
	 *
	 * }else { heap=new ArrayList<INode<T>>(); heap.add(null); heap.add(new
	 * HeapNode()); (heap.get(1)).setValue(element);
	 * ((HeapNode)(heap.get(1))).setIndex(1);
	 *
	 * size=2; }
	 *
	 * }
	 */

	public void insert(final T element) {
		if (size > 1) {
			INode child = new HeapNode();
			child.setValue(element);
			((HeapNode) child).setIndex(size);
			heap.add(size, child);
			size++;
			INode parent = heap.get(((HeapNode) child).getIndex() / 2);
			if (parent.getLeftChild() != null) {
				((HeapNode) parent).setRightChild(child);
			} else {
				((HeapNode) parent).setLeftChild(child);

			}
			((HeapNode) child).setParent(parent);
			while (parent != null && parent.getValue().compareTo(child.getValue()) < 0) {
				final T temp = (T) parent.getValue();
				parent.setValue(child.getValue());
				child.setValue(temp);
				child = parent;
				parent = heap.get(((HeapNode) child).getIndex() / 2);
			}

		} else {
			heap = new ArrayList<INode<T>>();
			heap.add(null);
			heap.add(new HeapNode());
			(heap.get(1)).setValue(element);
			((HeapNode) (heap.get(1))).setIndex(1);

			size = 2;
		}

	}

	@Override
	public void build(final Collection<T> unordered) {
		heap = new ArrayList();
		heap.add(null);

		int i = 1;
		for (final Iterator iterator = unordered.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unchecked")
			final T t = (T) iterator.next();
			heap.add(new HeapNode<>());
			heap.get(i).setValue(t);
			((HeapNode) heap.get(i)).setIndex(i++);
		}
		if (i > 1) {
			size = unordered.size() + 1;
		}
		for (i = size / 2; i > 0; i--) {
			heapify(heap.get(i));
		}

		for (i = 1; i < size; i++) {
			if (2 * i < size) {
				((HeapNode<T>) heap.get(i)).setLeftChild(heap.get(2 * i));
				((HeapNode<T>) heap.get(i * 2)).setParent(heap.get(i));
			}
			if (2 * i + 1 < size) {
				((HeapNode<T>) heap.get(i)).setRightChild(heap.get(2 * i + 1));
				((HeapNode<T>) heap.get(i * 2 + 1)).setParent(heap.get(i));
			}
		}

	}

	public void sort(final ArrayList<T> sortArray) {
		INode<T> node = new HeapNode<T>();
		build(sortArray);
		for (int i = sortArray.size(); i > 0; i--) {

			sortArray.set(i - 1, heap.get(1).getValue());

			swapNode(heap, 1, i);

			size--;

			node = heap.get(1);

			heapify(node);
		}
		int i = 1;
		heap = new ArrayList();
		heap.add(null);
		for (final Iterator iterator = sortArray.iterator(); iterator.hasNext();) {

			final T t = (T) iterator.next();

			heap.add(new HeapNode<>());
			heap.get(i).setValue(t);
			((HeapNode) heap.get(i)).setIndex(i++);
		}
		if (i > 1) {
			size = sortArray.size() + 1;
		}

		for (i = 1; i < size; i++) {

			if (2 * i < size) {

				((HeapNode<T>) heap.get(i)).setLeftChild(heap.get(2 * i));
				((HeapNode<T>) heap.get(i * 2)).setParent(heap.get(i));
			}
			if (2 * i + 1 < size) {

				((HeapNode<T>) heap.get(i)).setRightChild(heap.get(2 * i + 1));
				((HeapNode<T>) heap.get(i * 2 + 1)).setParent(heap.get(i));
			}
		}

	}

	void swapNode(final ArrayList<INode<T>> heap, final int i, final int j) {
		T temp;
		temp = heap.get(i).getValue();
		heap.get(i).setValue(heap.get(j).getValue());
		heap.get(j).setValue(temp);

	}
}