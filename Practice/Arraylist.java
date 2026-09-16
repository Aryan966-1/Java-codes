import java.util.*;
public class Arraylist{
    public static void main(String[] args){
        ArrayList<String> al = new ArrayList<String>();
        al.add("Apple");
        al.add("Mango");
        al.add("Orange");
        System.out.println("Returning Element: " + al.get(1));
        al.set(1, "Dates");
        Iterator<String> itr = al.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}