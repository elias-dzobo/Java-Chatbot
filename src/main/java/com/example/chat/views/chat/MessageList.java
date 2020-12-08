package com.example.chat.views.chat;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import org.vaadin.artur.Avataaar;

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
        String filepath = "/vaadin-ai-chat-master/src/main/resources/data.txt";

        File file = new File(filepath);
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(writer);

        try {
            for (int i = 0; i < data.length; i++){
                bw.write(data[i]);
            }
        } catch (IOException e){

        }
        
        writer.close();
        bw.close();
    }


}

public class MessageList extends Div {

    public static Queues data;

    // constructor
    public MessageList() {
        setClassName(getClass().getSimpleName());
        data = new Queues(15);
    }

    public void addMessage(String from, Avataaar avatar, String text, boolean isCurrentUser) {
        Span fromContainer = new Span(new Text(from));
        fromContainer.addClassName(getClass().getSimpleName() + "-name");

        Div textContainer = new Div(new Text(text));
        textContainer.addClassName(getClass().getSimpleName() + "-bubble");

        Div avatarContainer = new Div(avatar, fromContainer);
        avatarContainer.addClassName(getClass().getSimpleName() + "-avatar");

        Div line = new Div(avatarContainer, textContainer);
        line.addClassName(getClass().getSimpleName() + "-row");
        add(line);

        if (isCurrentUser) {
            line.addClassName(getClass().getSimpleName() + "-row-currentUser");
            textContainer.addClassName(getClass().getSimpleName() + "-bubble-currentUser");
        }

        line.getElement().callJsFunction("scrollIntoView");

        data.enqueue(text);

        data.createFile();
        
    }



}



