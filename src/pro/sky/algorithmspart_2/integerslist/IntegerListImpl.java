package pro.sky.algorithmspart_2.integerslist;

import pro.sky.algorithmspart_2.exception.InvalidIndexException;
import pro.sky.algorithmspart_2.exception.ItemNotFoundException;
import pro.sky.algorithmspart_2.exception.NullParameterException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[10];
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < size; i++) {
            int temp = get(i);
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


    @Override
    public Integer add(Integer item) {
        if (size >= storage.length) {
            storage = Arrays.copyOf(storage, (int) (storage.length + (storage.length * 0.5)));
        }
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index >= size || index < 0) {
            throw new InvalidIndexException();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= size || index < 0) {
            throw new InvalidIndexException();
        }
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int itemIndex = indexOf(item);
        if (itemIndex == -1) {
            throw new ItemNotFoundException();
        }
        if (itemIndex != storage.length - 1) {
            System.arraycopy(storage, itemIndex + 1, storage, itemIndex, size - itemIndex);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index >= size || index < 0) {
            throw new InvalidIndexException();
        }
        Integer itemToRemove = storage[index];
        if (index != storage.length - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        size--;
        return itemToRemove;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageClone = storage.clone();
        sort(storageClone);
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(storageClone[mid])) {
                return true;
            }
            if (item < storageClone[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullParameterException();
        }
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0 ; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index <= size && index >= 0) {
            return storage[index];
        }
        throw new InvalidIndexException();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
