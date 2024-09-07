

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class MyArrayList<E> {
    private Object[] list;
    private E type;
    private int size;
    private int fullSize;
  
    
    public MyArrayList() {
      list = new Object[10];
      fullSize = 10;
      size = 0;
      
    }
  
    public boolean add(E item) {
      if (size >= fullSize) {
        doubleListSize();
      }
      size ++;
      list[size - 1] = item;
      return true;
    }
  
    public boolean add(int loc, E item) {
      if (size >= fullSize) {
        doubleListSize();
      }
      size ++;
      if (list[loc] != null) {
        for (int i = size - 1; i > loc; i --) {
          list[i] = list[i - 1];
        }
      }
  
      list[loc] = item;
     
      return true;
    }
    @SuppressWarnings("unchecked")
    public E get(int loc) {
      return (E) list[loc];
    }
    @SuppressWarnings("unchecked")
    public E remove(int loc) {
      E result = (E) list[loc];
      list[loc] = null;
  
      for (int i = loc; i < size - 1; i ++) {
        list[i] = list[i + 1];
      }
      size --;
  
      return result;
    }
    @SuppressWarnings("unchecked")
    public E remove(E target) {
      for (int i = 0; i < size; i ++) {
        if (list[i].equals(target)) {
          E result = (E) list[i];
          
  
          for (int j = i; j < size - 1; j ++) {
            list[j] = list[j + 1];
          }
          size --;
          return result;
        }
      }
      return target;
    }
  
    public void set(int loc, E item) {
      list[loc] = item;
    }
  
    public String toString() {
      String result;
      if (size > 0) {
        result = ("[" + list[0]);
      } else {
        result = "[";
      }
      
      for (int i = 1; i < size; i ++) {
        result = (result + ", " + list[i]);
      }
      result = result + "]";
      return result;
    }
  
    public int size() {
      return size;
    }
  
    public void doubleListSize() {
      fullSize *= 2;
      Object[] listold = list;
      list = new Object[fullSize];
      for (int i = 0; i < listold.length; i ++){
        list[i] = listold[i];
      }
      
    }
       // Shuffle the elements in the list
    public void shuffle() {
        Random rand = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // Pick a random index from 0 to i
            // Swap elements[i] with elements[j]
            Object temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
    }

  
    // returns list
    public Object[] listreturn(){
        
        return (E[]) list;
    }
     @SuppressWarnings("unchecked")
     //sorts list 
    public void sort() {
        Arrays.sort((E[]) list, 0, size);
    }

    // Sort the list using a custom comparator
   
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) list, 0, size, c);
    }
    
  }