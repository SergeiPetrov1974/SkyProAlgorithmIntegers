package pro.sky.algorithmspart_2.integerslist;

public interface IntegerList {
    Integer add(Integer item);
    Integer add(int index, Integer item);
    Integer set(int index, Integer item);
    Integer remove(Integer item);
    Integer remove(int index);
    boolean contains(Integer item);
    boolean equals(IntegerList otherList);
    boolean isEmpty();
    int indexOf(Integer item);
    int lastIndexOf(Integer item);
    Integer get(int index);
    int getSize();
    void clear();
    Integer[] toArray();

}
