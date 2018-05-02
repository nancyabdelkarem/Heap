package eg.edu.alexu.csd.filestructure.sort;

public class HeapNode<T extends Comparable<T>> implements INode<T> {

	private INode parent;
	private INode leftChild;
	private INode rightChild;
	private int index;
	private T value;

	@Override
	public INode<T> getLeftChild() {
		return leftChild;
	}

	@Override
	public INode<T> getRightChild() {
		return rightChild;
	}

	@Override
	public INode<T> getParent() {
		return parent;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(final T value) {
		this.value = value;
	}

	public void setParent(final INode parent) {
		this.parent = parent;
	}

	public void setLeftChild(final INode leftCild) {
		this.leftChild = leftCild;
	}

	public void setRightChild(final INode rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

}
