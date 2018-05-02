package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sorting<T extends Comparable<T>> implements ISort<T> {
	Heap<T> p = new Heap<T>();

	@Override
	public IHeap<T> heapSort(final ArrayList<T> unordered) {

		p.sort(unordered);
		return p;
	}

	@Override
	public void sortSlow(final ArrayList<T> unordered) {
		final int n = unordered.size();

		int k;
		for (int i = n; i >= 0; i--) {
			for (int j = 0; j < n - 1; j++) {
				k = j + 1;
				if ((unordered.get(j).compareTo(unordered.get(k)) > 0)) {
					swap(unordered, j, k);
				}
			}
		}

	}

	@Override
	public void sortFast(final ArrayList<T> unordered) {
		// sort(unordered,0,unordered.size());
		if (unordered.isEmpty()) {
			return;
		}
		quickSort(unordered, 0, (unordered.size() - 1));

	}

	private void quickSort(final ArrayList<T> unordered, final int lower, final int higher) {
		int i = lower;
		int j = higher;
		final T pivot = unordered.get(lower + (higher - lower) / 2);
		while (i <= j) {
			while (unordered.get(i).compareTo(pivot) < 0) {
				i++;
			}
			while (unordered.get(j).compareTo(pivot) > 0) {
				j--;
			}
			if (i <= j) {
				swap(unordered, i, j);
				i++;
				j--;
			}

		}
		if (lower < j) {
			quickSort(unordered, lower, j);
		}
		if (i < higher) {
			quickSort(unordered, i, higher);
		}

	}

	/*
	 * public void sort( final ArrayList<T> unordered, final int low, final int
	 * high) { final int N = high - low; if (N <= 1) { return; } final int mid =
	 * low + N/2;
	 *
	 * sort(unordered, low, mid); sort(unordered, mid, high);
	 *
	 *
	 * final ArrayList<T> temp = new ArrayList<>() ;
	 *
	 * int i = low, j = mid; for (int k = 0; k < N; k++) { if (i == mid) {
	 *
	 *
	 * temp.add(k, unordered.get(j++));
	 *
	 * } else if (j == high) {
	 *
	 *
	 * temp.add(k, unordered.get(i++));
	 *
	 *
	 * } else if (unordered.get(j).compareTo(unordered.get(i)) < 0) {
	 *
	 * temp.add(k, unordered.get(j++));
	 *
	 * } else {
	 *
	 *
	 * temp.add(k, unordered.get(i++)); } }
	 *
	 * for (int k = 0; k < N; k++) {
	 *
	 * unordered.set(low+k, temp.get(k)); } }
	 *
	 */

	private void swap(final ArrayList<T> unordered, final int i, final int j) {
		T temp;
		temp = unordered.get(i);

		unordered.set(i, unordered.get(j));
		unordered.set(j, temp);

	}

}
