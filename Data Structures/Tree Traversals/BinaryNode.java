import java.util.ArrayList;
import java.util.Stack;

public class BinaryNode<T>
{
    //------------------------------FIELDS----------------------------------//
    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private ArrayList<T> storedTraversal;   //Global for returning ArrayList traversals to methods
    private BinaryNode<T> foundNode;
    //----------------------------------------------------------------------//




    //---------------------------CONSTRUCTORS-------------------------------//
    //Default BinaryNode with null elements
    public BinaryNode()
    {
        this(null, null, null);
    }

    //Overloaded BinaryNode with given parents and children
    public BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt)
    {
        element = theElement;
        left = lt;
        right = rt;
    }
    //----------------------------------------------------------------------//




    //------------------------------METHODS---------------------------------//
    //Calculate the size of the subtree of BinaryNode 't'
    public static <T> int size( BinaryNode<T> t)
    {
        if ( t == null)
        {
            return 0;
        }
        else
        {
            return 1 + size(t.left) + size(t.right);
        }
    }

    //Calculate the height of the subtree of BinaryNode 't'
    public static <T> int height( BinaryNode<T> t)
    {
        if (t == null)
        {
            return -1;
        }
        else
        {
            return 1 + Math.max(height(t.left), height(t.right));
        }
    }

    //Find if an element exists in the BinaryNode tree
    public BinaryNode<T> find(T inq)
    {
        if (element.equals(inq))
        {
            return this;
        }

        BinaryNode<T> out = null;

        if ( left != null)
        {
            out = left.find(inq);
        }

        if ( out != null)
        {
            return out;
        }
        else if ( right != null)
        {
            out = right.find(inq);
        }

        return out;
    }

    //Duplicate the BinaryNode tree
    public BinaryNode<T> duplicate()
    {
        BinaryNode<T> root = new BinaryNode<T>(element,null,null);

        if ( left != null)
        {
            root.left = left.duplicate();
        }
        if (right != null)
        {
            root.right = right.duplicate();
        }

        return root;
    }

    //Print the elements in the BinaryNode tree in PRE-ORDER normal format
    public void printPreOrder()
    {
        System.out.println(element);
        if (left != null)
            left.printPreOrder();
        if (right != null)
            right.printPreOrder();
    }

    //Print the elements in the BinaryNode tree in PRE-ORDER format WITHOUT RECURSION
    public void iterativePreOrder()
    {

        Stack<BinaryNode<T>> s = new Stack();
        BinaryNode<T> current = this;

        while (current != null || !s.empty())
        {
            if (current != null)
            {
                System.out.println(current.element);
                // process it, put it in the stack, and go left
                s.push(current);
                current = current.left;
            }
            else // pop the stack and go right
            {
                current = s.pop();
                current = current.right;
            }
        }
    }

    //Print the elements in the BinaryNode tree in IN-ORDER format WITHOUT RECURSION
    public void iterativeInOrder()
    {
        BinaryNode<T> t = this;   //The topmost node of 'this'

        Stack<BinaryNode<T>> s = new Stack<BinaryNode<T>>();    //Stack to keep nodes from 'this'

        //As long as the stack is not empty and the last node has not been reached
        while(!s.empty() || t != null)
        {
            if(t != null)
            {
                //Add as many 'lefts' as possible to the stack
                s.push(t);
                t = t.left;
            }
            else
            {
                //Pop the stack
                t = s.pop();

                //Print the element of the current node
                System.out.println(t.element);

                //Go right
                t = t.right;
            }
        }
    }

    //Print the elements in the BinaryNode tree in POST-ORDER format
    public void printPostOrder()
    {
        if (left != null)
        {
            left.printPostOrder();
        }

        if (right != null)
        {
            right.printPostOrder();
        }

        System.out.println(element);
    }

    //Print the elements in the BinaryNode tree in IN-ORDER format
    public void printInOrder()
    {
        if (left != null)
        {
            left.printInOrder();
        }

        System.out.println(element);

        if (right != null)
        {
            right.printInOrder();
        }
    }

    //Calculate and prepare an ArrayList in POST-ORDER format to send to other methods
    public void calculatePostOrder(ArrayList<T> storedTraversal)
    {
        if(left != null)
        {
            left.calculatePostOrder(storedTraversal);
        }

        if(right != null)
        {
            right.calculatePostOrder(storedTraversal);
        }

        storedTraversal.add(element);
    }

    //Calculate and prepare an ArrayList in IN-ORDER format to send to other methods
    public void calculateInOrder(ArrayList<T> storedTraversal)
    {
        if(left != null)
        {
            left.calculateInOrder(storedTraversal);
        }

        storedTraversal.add(element);

        if(right != null)
        {
            right.calculateInOrder(storedTraversal);
        }
    }

    //Test if two trees are equal by comparing traversals
    public boolean equals(BinaryNode<T> r)
    {
        BinaryNode<T> t = this;

        ArrayList<T> tree1Traversal = t.getInorderTraversal();  //Get an ArrayList with the IN-ORDER of 'this'
        ArrayList<T> tree2Traversal = r.getInorderTraversal();  //Get an ArrayList with the IN-ORDER of 'r'

        int length = tree1Traversal.size(); //Check the size of one of them

        //If the sizes don't match, or the arrays don't have the same elements, then these trees are not the same
        if(length != tree2Traversal.size() || !tree1Traversal.equals(tree2Traversal))
        {
            return false;
        }

        //By now they might be the same, but a second traversal check must be done to make sure
        tree1Traversal = t.getPostorderTraversal(); //Get an ArrayList with the POST-ORDER of 'this'
        tree2Traversal = r.getPostorderTraversal(); //Get an ArrayList with the POST-ORDER of 'r'

        //If they don't have the same elements, then the trees are not the same
        if(!tree1Traversal.equals(tree2Traversal))
        {
            return false;
        }

        //If all these tests pass, then the trees are duplicates
        return true;
    }

    //Creates a tree using POST-ORDER and IN-ORDER traversals of it
    public static <T> BinaryNode<T> postPlusIn(T[] post, T[] in)
    {
        if(post.length != in.length)
        {
            throw new IllegalArgumentException("Error! POST-ORDER and IN-ORDER do not have the same length!");
        }

        int len = post.length;  //The length of post to be used for array slices
        T root = post[post.length - 1]; //The root of this node is the last element of the POST-ORDER traversal
        int middle = 0; //Counter to find where in the IN-ORDER the root is located

        //For every element in the IN-ORDER traversal
        for(T elem : in)
        {
            //If it is the root, break
            if(elem == root)
            {
                break;
            }

            //Increase the counter since it was not found
            middle++;
        }

        /*After finding the root in both traversals, array slices should be done because the left hand side of the
        IN-ORDER traversal from the root are elements found in the left hand side of the tree, while the right hand
        side of the IN-ORDER traversal are elements found in the right hand side of the tree. If a negative array
        size exception occurs, then the two traversals are not valid*/

        try
        {
            T[] leftIn = (T[])new Object[middle];
            T[] rightIn = (T[])new Object[len - middle - 1];
            T[] leftPost = (T[])new Object[middle];
            T[] rightPost = (T[])new Object[len - middle - 1];

            int m = 0;  //Used as a pointer to add elements to the new left array of the IN-ORDER traversal
            int n = 0;  //Used as a pointer to add elements to the new right array of the IN-ORDER traversal

            //For every element in the IN-ORDER, if it is to the left hand side of the root, add it to the left
            //If it is on the right hand side of the root, place it in the right
            for(int i = 0 ; i < len ; i++)
            {
                if(i < middle)
                {
                    leftIn[m] = in[i];
                    m++;
                }

                else if(i > middle)
                {
                    rightIn[n] = in[i];
                    n++;
                }
            }

            //The same thing as above must be done with the POST-ORDER traversal, except this time we are using the amount
            //of elements found on each side of the array as a guide instead of the root
            int o = 0;

            for(int i = 0 ; i < len - 1 ; i++)
            {
                if(i < leftIn.length)
                {
                    leftPost[i] = post[i];
                }
                else
                {
                    rightPost[o] = post[i];
                    o++;
                }
            }

        /*The following methods are the recursive methods. If the left hand side of the tree and the right hand side
        of the tree, now found in new arrays, have more than one element, there are more nodes. Therefore, return
        a new binary node with this method called again with the new arrays. However, if the amount of elements is 1
        for one array, then the left or right child of the root is that element depending on which of the new arrays
        it is found on. If it is 0, then the root does not have any children, and a BinaryNode of the root and null
        children must be returned.*/

            if(leftIn.length == 1 && rightIn.length == 1)
            {
                return(new BinaryNode(root, new BinaryNode(leftIn[0], null, null), new BinaryNode(rightIn[0], null, null)));
            }
            else if(leftIn.length == 1 && rightIn.length > 1)
            {
                return(new BinaryNode(root, new BinaryNode(leftIn[0], null, null), postPlusIn(rightPost, rightIn)));
            }
            else if(rightIn.length == 1 && leftIn.length > 1)
            {
                return(new BinaryNode(root, postPlusIn(leftPost, leftIn), new BinaryNode(rightIn[0], null, null)));
            }
            else if(leftIn.length > 1 && rightIn.length > 1)
            {
                return(new BinaryNode(root, postPlusIn(leftPost, leftIn), postPlusIn(rightPost, rightIn)));
            }
            else if(leftIn.length == 0 && rightIn.length != 0)
            {
                return(new BinaryNode(root, null, postPlusIn(rightPost, rightIn)));
            }
            else if(rightIn.length == 0 && leftIn.length != 0)
            {
                return(new BinaryNode(root, postPlusIn(leftPost, leftIn), null));
            }
            else
            {
                return new BinaryNode(root, null, null);
            }
        }
        catch(NegativeArraySizeException e)
        {
            throw new IllegalArgumentException("Error! POST-ORDER and IN-ORDER traversals are corrupted!");
        }
    }

    //This method, when called by main with a root, creates an ArrayList to send and calls a helper to get the longest path
    public BinaryNode<T> parent(BinaryNode<T> n)
    {
        //We can't find the parent of a null node
        if(n == null)
        {
            throw new IllegalArgumentException("Error! Can't find the parent of a null node!");
        }

        //Create a 'this' BinaryNode to iterate through
        BinaryNode<T> t = this;

        //If n and t are equal, return null
        if(n.equals(this))
        {
            return null;
        }

        foundNode = new BinaryNode<T>();    //initiate new global variable to keep the parent
        parentAyuda(n, t);  //Ask a recursive helper to do all the work
        return(foundNode);  //Return the parent (null if there is none)
    }

    //Because Arrays need to be either global or sent recursively to keep the data stored, a helper method was created
    public void parentAyuda(BinaryNode<T> n, BinaryNode<T> t)
    {
        if(t != null)
        {
            //If the left and right node are both not null and are n
            if((t.left != null && t.left.equals(n)) || (t.right != null && t.right.equals(n)))
            {
                //We're done! We found the parent!
                foundNode = t;
            }
            //Else if the left node is null, but the right node has more elements
            else if(t.left == null && t.right != null)
            {
                //Recursively call with the right node
                parentAyuda(n, t.right);
            }
            //Else if the right node is null, but the left node has more elements
            else if(t.left != null && t.right == null)
            {
                //Recursively call with the left node
                parentAyuda(n, t.left);
            }
            //Else both nodes have elements on both side, therefore, check both
            else
            {
                parentAyuda(n, t.left);
                parentAyuda(n, t.right);
            }
        }
    }

    //This method, when called by main with a root, creates an ArrayList to send and calls a helper to get the longest path
    public static ArrayList longestPath(BinaryNode root)
    {
        ArrayList pathArray = new ArrayList();
        return longestPathAyuda(root, pathArray);
    }

    //Because Arrays need to be either global or sent recursively to keep the data stored, a helper method was created
    private static ArrayList longestPathAyuda(BinaryNode root, ArrayList pathArray)
    {
        //If the root is null, then there is no longest path, or we've found the end.
        if(root == null)
        {
            return null;
        }

        //If the height of the right tree is larger (and right has more elements)
        if(height(root.right) > height(root.left))
        {
            //Add the root element to the list
            pathArray.add(root.element);

            //Call recursively the left side, also sending the ArrayList to keep the data stored
            longestPathAyuda(root.right, pathArray);
        }
        //Else, the height of the left tree is larger (and left has more elements)
        else
        {
            //Add the root element to the list
            pathArray.add(root.element);

            //Call recursively the right side, also sending the ArrayList to keep the data stored
            longestPathAyuda(root.left, pathArray);
        }

        //The end has been reached, because no side is larger than the other!
        return pathArray;
    }
    //----------------------------------------------------------------------//




    //---------------------------SETS AND GETS------------------------------//
    //Set the T type variable 'element' to given 'x'
    public void setElement(T x)
    {
        element = x;
    }

    //Set the left child of the BinaryNode tree to subtree 't'
    public void setLeft(BinaryNode<T> t)
    {
        left = t;
    }

    //Set the right child of the BinaryNode tree to subtree 't'
    public void setRight(BinaryNode<T> t)
    {
        right = t;
    }

    public void setStoredTraversal(ArrayList<T> in)
    {
        storedTraversal = in;
    }

    //Return 'element'
    public T getElement()
    {
        return element;
    }

    //Return the left child of the BinaryNode tree
    public BinaryNode<T> getLeft()
    {
        return left;
    }

    //Return the right child of the BinaryNode tree
    public BinaryNode<T> getRight()
    {
        return right;
    }

    //Returns an ArrayList of the Inorder representation of 'this'
    public ArrayList<T> getInorderTraversal()
    {
        storedTraversal = new ArrayList<T>();
        calculateInOrder(storedTraversal);
        return storedTraversal;
    }

    //Returns an ArrayList of the Postorder representation of 'this'
    public ArrayList<T> getPostorderTraversal()
    {
        storedTraversal = new ArrayList<T>();
        calculatePostOrder(storedTraversal);
        return storedTraversal;
    }
    //----------------------------------------------------------------------//
}


