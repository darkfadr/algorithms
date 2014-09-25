import java.util.*;

public class LinkedListMethods
{
    public static <T> LinkedList<Pair<T, Integer>> convert(LinkedList<T> inList)
    {
        //First check if the accepted parameter 'inList' is a null method (not compatible).
        if(inList == null)
        {
            return null;
        }

        LinkedList<Pair<T, Integer>> myPairList = new LinkedList<Pair<T, Integer>>();

        ListIterator<T> itr1 = inList.listIterator();  //Used to iterate through each word of the 'inList' LinkedList.
        ListIterator<T> itr2;  //Used as a second iterator to calculate how many times each word appears.
        T element, temp;    //Both of these are used to store the iteration of each iterator: '.next()'
        ArrayList<T> wordsDone = new ArrayList<T>();    //Word pointerBank that stores elements already processed.
        int count;  //To keep track of how many times an element appears in the list: 'inList'

        //For all elements found in 'inList'
        while(itr1.hasNext())
        {
            //Grab the next element (or first element if first iteration).
            element = itr1.next();

            //If the element is not in the word pointerBank,
            if(!wordsDone.contains(element))
            {
                //Reset both the count and the second iterator (both used to find how many times the element appears).
                count = 0;
                itr2 = inList.listIterator();

                //For each element found in 'inList'
                while(itr2.hasNext())
                {
                    //Grab the element
                    temp = itr2.next();

                    //If it's the same as the element in question from the first iterator,
                    if(temp == element)
                    {
                        //Increment the count.
                        count++;
                    }
                }

                //Since this is a new word that has just been processed, add it to the word pointerBank of finished words.
                wordsDone.add(element);

                //Add the pair representation of the element plus the count into the new LinkedList representation
                myPairList.add(new Pair<T, Integer>(element, count));
            }
        }

        return myPairList;
    }

    public static void reduceList(java.util.LinkedList<String> list, char ch)
    {
        //Create an iterator to check all the elements in the 'list'
        ListIterator<String> itr = list.listIterator();

        //Variable used to store each element.
        String s;

        //While there are more elements in list:
        while(itr.hasNext())
        {
            //Grab the element
            s = itr.next();

            //The element might be null, and in such a case, make sure the program does not crash.
            try
            {
                if(s.charAt(0) == ch)
                {
                    itr.remove();
                }
            }
            catch(NullPointerException e)
            {
                //do nothing
            }
        }
    }

    public static boolean isBalanced(String in)
    {
        //Some valid scenarios: ()(), [(){}], {[()()[]{}]}
        //Some invalid scenarios: ](){}, {{[[((]]}}))

        //If the string accepted is null, then there is nothing to balance (nor is it balanced).
        if(in == null)
        {
            return false;
        }

        Stack<Character> st = new Stack<Character>();   //Used to pop and push parenthesis to check for balance.
        int length = in.length();   //Store the length of the string 'in'

        //For every character in the string 'in':
        for(int i = 0 ; i < length ; i++)
        {
            //Get the character at the 'i' position
            char c = in.charAt(i);

            //If the character in the string is an open parenthesis:
            if(c == '(' || c == '[' || c == '{')
            {
                //If the element is either of the parenthesis, push it onto the stack
                st.push(c);
            }
            //Else, it must be a closing parenthesis
            else
            {
                //Since it is a closing parenthesis, an empty stack means the equation is not balanced
                if(st.isEmpty())
                {
                    return false;
                }

                //Check for closing parenthesis
                switch(c)
                {
                    /*For every closing parenthesis, the top of the stack must be it's opened counterpart. At any
                    scenario that it is not, the equation is immediately unbalanced (i.e. '{([)}'). Return false if
                    such is the case. I also don't have to worry about EmptyStackException because I made sure that the
                    stack is not empty before this part of the method.*/

                    case ')':
                        if(st.peek() == '(')
                        {
                            st.pop();
                        }
                        else
                        {
                            return false;
                        }
                        break;

                    case ']':
                        if(st.peek() == '[')
                        {
                            st.pop();
                        }
                        else
                        {
                            return false;
                        }
                        break;

                    case '}':
                        if(st.peek() == '{')
                        {
                            st.pop();
                        }
                        else
                        {
                            return false;
                        }
                        break;

                    default:
                        //do nothing
                        break;
                }
            }
        }

        /*It has survived the ultimate test, but there is still one more check. If the equation truly is balanced, then
          the stack should be empty. Make sure the stack is empty, and return true if so.*/
          return st.empty();
    }

    public static <T> void eliminateDuplicates(LinkedList<T> list)
    {   
        ListIterator<T> itr = list.listIterator();  //Used to iterate through each word of the 'list' LinkedList.
        ListIterator<T> itr2;   //Used as a second iterator to calculate how many times each word appears.
        ArrayList<Integer> pointerBank = new ArrayList<Integer>(); //Keeps the indexes of where duplicates occur.
        ArrayList<T> wordsDone = new ArrayList<T>();    //Keeps words already processed.
        T element, temp;    //Both of these are used to store the iteration of each iterator: '.next()'

        /*This pointer makes sure the second iterator starts one ahead of the first iterator, so
        that the program does not count the first instance of a word as a duplicate. Starts at 1
        because the first iterator starts at 0.*/
        int itr2Start = 1;    
       
        int pointer;    //Saves where one duplicate instance of 'element' occurs.

        //For all elements found in 'list'
        while(itr.hasNext())
        {
            //Grab the next element (or first element if first iteration).
            element = itr.next();
            
            //If the element is not in the wordsDone array:
            if(!wordsDone.contains(element))
            {
                //Create a new list iterator starting at 'itr2Start' (one more in front of itr1).
                itr2 = list.listIterator(itr2Start);

                //Also start pointer at 'itr2Start'
                pointer = itr2Start;

                //For each element found in 'inList'
                while(itr2.hasNext())
                {
                    //Grab the element
                    temp = itr2.next();

                    //If the new element just grabbed (temp) is the same as the element in itr1 (element):
                    if(temp.equals(element))
                    {
                        //A duplicate has occurred. Save the pointer to the pointer bank.
                        pointerBank.add(pointer);
                    }

                    //Move the pointer up to save the next element's pointer if it is a duplicate.
                    pointer++;
                }

                //This word has been processed! Add it to the words done bank to prevent it from being processed again.
                wordsDone.add(element);
            }
            //Before moving to the next iteration of itr1, increment the start of itr2 to keep itr2 one higher.
            itr2Start++;
        }

        /*By this point, we have a bank full of pointers that show us where duplicates occur. However, because we cannot
        simply '.remove()' each pointer in the list due to the list shifting left once an element has been deleted. It's
        necessary to make sure the element deleted in the list corresponds to the shifted version of the pointers found
        in the bank*/

        int leftShift = 0;  //Keep track of how many times the list has shifted to the left.
        int i = 0;  //To go through pointer bank's elements in order.
        int size = pointerBank.size();  //To know how many elements are left in the pointer bank.

        //As long as the pointer bank has at least one element:
        while(size > 0)
        {
            //Ask if the pointer bank has 'i' as an element. If it does:
            if(pointerBank.contains(i))
            {
                //Remove i from the list, also considering shifts if they have happened.
                list.remove(i - leftShift);

                //And since a remove has happened, a new shift will also happen.
                leftShift++;

                //The size of the bank is now 'one smaller' (in reality it is not).
                size--;
            }

            //Increment i to test the next possible pointer where a duplicate might have occurred in.
            i++;
        }
    }

    public static <T extends Comparable> void sort(LinkedList<T> list)
    {
        LinkedList<T> copyOfList  = (LinkedList)list.clone();   //Copy the list to another LinkedList
        list.clear();   //Clear the original list of any elements
        ListIterator<T> itr1;   //List iterator for the copy of the list
        T first, second;    //T types to be used to compare elements within the list
        int maxIndex;   //Stores the index where the highest of two elements is located.


        //While the list has at least 2 elements:
        while(copyOfList.size() > 1)
        {
            maxIndex = 0;

            //Start the copyOfList iterator at the start of the list
            itr1 = copyOfList.listIterator();

            //Grab the next element of the list (first if first iteration)
            first = itr1.next();

            //While there are more elements in the list:
            while(itr1.hasNext())
            {
                //Grab the next element of the list into 'second' (the one after the first element, named 'first')
                second = itr1.next();

                //If 'second' is less than the 'first' element
                if(first.compareTo(second) < 0)
                {
                    //Override first with second
                    first = second;

                    //Save the index where the highest one was.
                    maxIndex = itr1.nextIndex() - 1;
                }
            }

            //Begin the next iterations of the list where the max element was located.
            itr1 = copyOfList.listIterator(maxIndex);

            //Grab the max element from the list
            first = itr1.next();

            //Remove it form the iterator (causes a left shift) and add it to the list we are creating.
            itr1.remove();
            list.addFirst(first);
        }

        //Now add the first element to the top of the list and the list is sorted! (The only element left)
        itr1 = copyOfList.listIterator(0);
        first = itr1.next();
        list.addFirst(first);
    }
}