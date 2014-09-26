package genericlinkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * This class is a set of methods to use on generic LinkedList, where the type
 * of the LinkedList is not specified. It provides a delete method, a sorting
 * method, remove duplicates, convert LinkedList to a LinkedList of Pairs, as 
 * well as checks if equations are balanced and remove all instances of a 
 * LinkedList entry where the entry begins with a given character.
 * 
 * @author Guido Ruiz
 */

public class GenericLinkedList
{
    /**
     * This method converts a LinkedList of any generic type to a new LinkedList
     * composed of Pairs, in which each Pair is an element and how many 
     * occurrences of that element appear in the list.
     * 
     * For example:
     * LinkedList {Hello, My, Name, Is Hello, Is} converts to:
     * LinkedList {{Hello, 2}, {My, 1}, {Name, 1}, {Is, 2}}.
     * 
     * NOTE: T must implement the Comparable interface!
     * 
     * @param <T>
     * @param list which is the LinkedList to process.
     * @return a formatted LinkedList.
     */
    
    public static <T>LinkedList<Pair<T, Integer>> convert(LinkedList<T> list)
    {
        if(list == null)
            return null;

        LinkedList<Pair<T, Integer>> pairList = new LinkedList<>();

        ListIterator<T> itr1 = list.listIterator(); 
        ListIterator<T> itr2;
        
        T elem, temp;
        ArrayList<T> wordsDone = new ArrayList<>();   

        while(itr1.hasNext())
        {
            elem = itr1.next();
            
            if(!wordsDone.contains(elem))
            {
                int count = 0;
                itr2 = list.listIterator(itr1.nextIndex());

                while(itr2.hasNext())
                {
                    temp = itr2.next();
                    
                    if(temp.equals(elem))
                        count++;
                }

                wordsDone.add(elem);
                pairList.add(new Pair<>(elem, count));
            }
        }

        return pairList;
    }

    /**
     * This method removes all instances of an entry in a LinkedList made of
     * strings that contain a specified character as their first character.
     * 
     * @param list a LinkedList of strings.
     * @param ch the character for which to delete all entries that start with.
     * @return LinkedList of type String
     */
    
    public static LinkedList<String> reduceList(LinkedList<String> list, char ch)
    {
        ListIterator<String> itr = list.listIterator();
        String elem;
        
        while(itr.hasNext())
        {
            elem = itr.next();
            
            if(elem.charAt(0) == ch)
                itr.remove();
        }
        
        return list;
    }

    /**
     * This method returns true if a string equation passed to it is balanced, 
     * false if not. All brackets are supported, and this method uses a stack to
     * take note of the brackets it traverses through to make sure that each 
     * opening bracket has a corresponding closing bracket in the string.
     * 
     * Example 'true' cases: ()(), [(){}], {[()()[]{}]}
     * Example 'false' cases: ](){}, {{[[((]]}}))
     * 
     * @param in string which contains the equation.
     * @return boolean true if balanced, false if not.
     */
    
    public static boolean isBalanced(String in)
    {
        if(in == null)
            return false;

        Stack<Character> st = new Stack<>(); 
        int length = in.length();

        for(int i = 0 ; i < length ; i++)
        {
            char c = in.charAt(i);
        
            switch(c)
            {
                case '(': 
                case '[': 
                case '{':
                    st.push(c);
                    continue;
                case ')':
                    if(st.peek() == '(')
                         st.pop();
                     else
                         return false;
                    break;
                case ']':
                    if(st.peek() == '[')
                        st.pop();
                    else
                        return false;
                    break;
                case '}':
                    if(st.peek() == '{')
                        st.pop();
                    else
                        return false;
                    break;
                default:
            }
        }
     
        return st.empty(); // stack should be empty if balanced
    }

    /**
     * This method removes all duplicates in a generic LinkedList. Because 
     * remove by a type cannot be done, the pointers of where the duplicates
     * happen must be stored and deleted in order from the start to the end
     * of the LinkedList (with a counter indicating how many left shifts have
     * taken place). A PriorityQueue is used here to get the minimum pointer
     * of the bank of pointers to delete.
     * 
     * @param list
     * @param <T>
     * @return LinkedList without duplicates
     */
    
    public static <T extends Comparable>LinkedList<T> eliminateDuplicates(LinkedList<T> list)
    {   
        ListIterator<T> itr = list.listIterator();
        ListIterator<T> itr2;
        
        PriorityQueue<Integer> deleteBank = new PriorityQueue<>();
        ArrayList<T> wordsDone = new ArrayList<>();
        
        T elem, temp; 

        while(itr.hasNext())
        {
            elem = itr.next();
            
            if(!wordsDone.contains(elem))
            {
                itr2 = list.listIterator(itr.nextIndex());
                  
                while(itr2.hasNext())
                {
                    temp = itr2.next();
                    
                    if(temp.equals(elem))
                        deleteBank.add(itr2.nextIndex() - 1);
                }

                wordsDone.add(elem);
            }
        }

        int leftShift = 0;
        
        for(int p : deleteBank)
            list.remove(p - (leftShift++));
        
        return list;
    }

    /**
     * TODO: BLACK BOX
     * @param <T>
     * @param list 
     */
    
    public static <T extends Comparable>LinkedList<T> sort(LinkedList<T> list)
    {
        LinkedList<T> copyOfList  = (LinkedList)list.clone();   
        list.clear();  
        ListIterator<T> itr1; 
        T first, second;
        
        int maxIndex; 

        while(copyOfList.size() > 1)
        {
            maxIndex = 0;
            itr1 = copyOfList.listIterator();
            first = itr1.next();
            
            while(itr1.hasNext())
            {
                second = itr1.next();

                if(first.compareTo(second) < 0)
                {          
                    first = second;
                    maxIndex = itr1.nextIndex() - 1;
                }
            }

            itr1 = copyOfList.listIterator(maxIndex);
            first = itr1.next();
            itr1.remove();
            list.addFirst(first);
        }

        itr1 = copyOfList.listIterator(0);
        first = itr1.next();
        list.addFirst(first);
        
        return list;
    }
}