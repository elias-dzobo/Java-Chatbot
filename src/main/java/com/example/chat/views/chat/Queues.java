import java.io.*;

public interface Queue {
    int size();
    boolean isEmpty();
    String first();
    String enqueue(String e);
    String dequeue();
}

public class Queues implements Queue {
    

    private String[] data;
    private int f = 0;
    private int sz = 0;

    //constructors
    public Queues() {
        this(5);
    }

    public Queues(int size){
        data =  (String[]) new Object[size];
    }

    public boolean isEmpty(){
        return this.sz == 0;
    }

    public String enqueue(String e) {
        if (sz == data.length) { 
            return ("Queue is full");
        }
        int idx = (f + sz)% data.length;
        data[idx] = e;
        sz ++;
        return ("Insert " + e);
    }

    public String dequeue()  {
        if (isEmpty()) {

             return ("Queue is Empty");
        }
        String element = data[f];
        data[f] = null;
        this.f = (f+1)%data.length;
        sz--;
        return (element + " deleted");

    } 

    public String display(){
        String el = "";
        String first = "Front index -> " + f;
        int rear = ((sz-1)+f)%data.length;
        String r = "Rear index -> " + rear;
        for (String e: data){
            el += e + " ";
        }

        return (first + "\n" + el + "\n" + r);
    }

    public int size(){
        return data.length;
    }

    public String first(){

        return data[f];
    }

    public void createFile(){

        try {

            
            FileWriter myWriter = new FileWriter("data.txt");
            
            
            for (int i = 0; i < data.length; i++){
                myWriter.write(data[i]);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
    }


}