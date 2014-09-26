package genericlinkedlist;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Tester class for the methods found in the GenericLinkedList class.
 * 
 * @author Guido
 */

public class Test
{
    public static void main(String args[])
    {
        LinkedList<String> list = new LinkedList<>();
        
        String data = "Hello,My,Name,Is,Hello,Name";
        String[] elems = data.split(",");
        
        list.addAll(Arrays.asList(elems));
        
        //-- Test convert() method --//
        LinkedList<Pair<String, Integer>> test = GenericLinkedList.convert(list);
        
        for(Pair p : test)
            System.out.println(p);
        
        //-- Test sort() method --//
        list = GenericLinkedList.sort(list);
        System.out.println(list);
        
        //-- Test eliminateDuplicates() method --//
        list = GenericLinkedList.eliminateDuplicates(list);
        System.out.println(list);
        
        //-- Test reduceList() method --//
        list = GenericLinkedList.reduceList(list, 'H');
        System.out.println(list);
        
        //-- Test isBalanced() method --//
        String eq1 = "{[([{()}])]}";
        System.out.println(eq1 + " balanced? " + GenericLinkedList.isBalanced(eq1));
        
        eq1 = "({}]";
        System.out.println(eq1 + " balanced? " + GenericLinkedList.isBalanced(eq1));
    }
}
