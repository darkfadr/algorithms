import java.util.NoSuchElementException;

public class CircularQueue<T> implements Queue<T>
{
    public int f;           //Points to the first element of the queue
    public int r;           //Points to the last element of the queue
    public T[] myArray;     //Queue, or array of generic type T
    public int size;        //Size of the queue

    //------------------------------------------------------------------------------------------------------------------
    /*  Two Constructors
        Default length is 3, unless overloaded otherwise by parameter 'length.'
        Pointer 'r' initialized at -1 because no elements are added until add() is called.
        Pointer 'f' initialized at 0. Although no elements are added until add() is called, because remove() is required
            to increment the 'f' pointer and remove() will not work if 'size' equals 0, it is safe to initialize 'f' as 0.
        Size initialized to 0 until elements are added to the queue. */
    //------------------------------------------------------------------------------------------------------------------
    CircularQueue()
    {
        myArray = (T[]) new Object[3];
        size = 0;
        f = 0;
        r = -1;
    }

    CircularQueue(int length)
    {
        myArray = (T[]) new Object[length];
        size = 0;
        f = 0;
        r = -1;
    }
    //------------------------------------------------------------------------------------------------------------------

    //This add method adds a T type item to the end of the array
    public void add(T item)
    {
        //If the array is full:
        if(size == myArray.length)
        {
            //Double the size of the array using method isEmpty()
            doubleArray();
        }

        //If 'r', the last pointer of the queue, is at the end of the array:
        if((r + 1) == myArray.length)
        {
            //Reset the pointer back to the beginning of the array
            r = 0;
        }
        else
        {
            r++;
        }

        //Increment the size of the queue
        size++;
        //Add the new element to 'myArray' where 'r' points
        myArray[r] = item;
    }

    //This remove method removes a T type item from the beginning of the array
    public T remove()
    {
        //If the queue is empty:
        if(size == 0)
        {
            //Inform the user that it is impossible to remove any elements
            throw new NoSuchElementException();
        }
        else
        {
            //Copy the pointer that points to the first element of the queue to 'temp' to return later
            int temp = f;

            //If 'f', the pointer that points to the beginning of the queue is at the end of the queue:
            if(f == myArray.length - 1)
            {
                //Reset the pointer back to the beginning of the queue
                f = 0;
            }
            else
            {
                //Increment 'f' to 'erase' the old element
                f++;
            }

            //Decrease the size of the queue
            size--;
            //Return the element T that was removed from the queue and saved to 'temp'
            return myArray[temp];
        }
    }

    //This getFirst method returns the first element in the queue, pointed by 'f'
    public T getFirst()
    {
        //If there are no elements in the queue:
        if(size == 0)
        {
            //Inform the user that it is impossible to return any elements
            throw new NoSuchElementException();
        }
        else
        {
            //Return the element in the queue referenced by 'f'
            return myArray[f];
        }
    }

    //This getSize method returns the current size of the queue
    public int getSize()
    {
        return size;
    }

    //This isEmpty method returns true if the queue is empty, false otherwise
    public boolean isEmpty()
    {
       if(size == 0)
       {
            return true;
       }
       else
       {
           return false;
       }
    }

    //This clear method resets the size of the queue and it's pointers
    public void clear()
    {
        size = 0;
        f = 0;
        r = -1;
    }

    public void doubleArray()
    {
        int oldLength = myArray.length;                 //Save the length of the old array
        int newLength = 2 * oldLength;                  //Calculate double the length of the old array
        T[] newArray = (T[]) new Object[newLength];     //Create a temporary array of type 'T' with the new length

        //Scenario 1: [f][][][]...[][r]
        if(r > f)
        {
            //Copy all elements from pointer 'f' (start) to 'r' (end)
            System.arraycopy(myArray, 0, newArray, 0, oldLength);
        }

        //Scenario 2: [][]...[][r][f]...[][][]
        else
        {
            //Copy all elements from pointer 'f' to 'end', and elements from 'start' to pointer 'r'.
            System.arraycopy(myArray, f, newArray, 0, (oldLength - f));
            System.arraycopy(myArray, 0, newArray, (oldLength - f), (r + 1));
        }

        //Replace the old queue with the newly created queue
        myArray = newArray;

        //Change the pointers to match the new queue
        f = 0;
        r = oldLength - 1;
    }

    //This method displays the current elements of the queue, the size of the queue, and the locations of both pointers
    public void display()
    {
        String output = "\n" + "Queue =";

        for(T element : myArray)
        {
            output += " [" + element + "]";
        }

        output += "\n";
        output += "f = " + f + "\n";
        output += "r = " + r + "\n";
        output += "Size = " + getSize();
        output += "\n";

        System.out.println(output);
    }
}
