public interface Queue {
    int size();
    boolean isEmpty();
    String first();
    String enqueue(String e);
    String dequeue();
}