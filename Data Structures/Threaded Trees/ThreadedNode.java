public class ThreadedNode<T>
{
//______________________________________________________________________________________________________________________
//-------------------------------------------------- FIELDS ------------------------------------------------------------
    private T element;                      //The root of this tree
    private ThreadedNode<T> left;           //The left subtree
    private ThreadedNode<T> right;          //The right subtree
    private boolean lThread = false;        //True if the left node is a threaded node
    private boolean rThread = false;        //True if the right node is a threaded node
//----------------------------------------------------------------------------------------------------------------------




//______________________________________________________________________________________________________________________
//----------------------------------------------- CONSTRUCTORS ---------------------------------------------------------
    //Default constructor that creates a ThreadedNode tree given the root, left subtree, and right subtree
    ThreadedNode(T theElement, ThreadedNode<T> lt, ThreadedNode<T> rt)
    {
        element = theElement;
        left = lt;
        right = rt;
    }

    //Overloaded constructor that converts a Binary tree into a Threaded tree
    ThreadedNode(BinaryNode<T> root)
    {
        //If the Binary root is null, then the Binary tree is not formatted correctly
        if(root == null)
        {
            //Throw an exception
            throw new IllegalArgumentException("Sorry! The Binary tree provided is not formatted correctly.");
        }
        //Else Binary root has a valid element
        else
        {
            //Get the Binary root's element into this tree
            element = root.getElement();
        }

        //Get the Binary root's left subtree
        BinaryNode<T> binaryLeftTree = root.getLeft();

        //If the Binary root's left subtree is not null, then call a recursive helper class to thread it
        if (binaryLeftTree != null)
        {
            left = ThreadedNode.threadHelper(binaryLeftTree, null, this);
        }

        //Get the Binary root's right subtree
        BinaryNode<T> binaryRightTree = root.getRight();

        //If the Binary root's right subtree is not null, then call a recursive helper class to thread it
        if (binaryRightTree != null)
        {
            right = ThreadedNode.threadHelper(binaryRightTree, this, null);
        }
     }

    //Helper class for the overloaded ThreadedNode constructor that converts a Binary tree into a Threaded tree
    public static <T> ThreadedNode<T> threadHelper(BinaryNode<T> root, ThreadedNode<T> leftTree, ThreadedNode<T> rightTree)
    {
        //Create a tree with the Binary root's element as it's element, as well as default null subtrees
        ThreadedNode<T> thisNode = new ThreadedNode(root.getElement(), null, null);

        //Get the Binary root's left subtree
        BinaryNode<T> binaryLeftTree = root.getLeft();

        //If the left subtree is null, create a Threaded child for the left subtree
        if(binaryLeftTree == null)
        {
            thisNode.lThread = true;
            thisNode.left = leftTree;
        }
        //Else it exists, so
        else
        {
            //Call recursively this same method and attach it to this tree
            thisNode.left = ThreadedNode.threadHelper(binaryLeftTree, leftTree, thisNode);
        }

        //Get the Binary root's right subtree
        BinaryNode<T> binaryRightTree = root.getRight();

        //If the right subtree is null, create a Threaded child for the right subtree
        if(binaryRightTree == null)
        {
            thisNode.rThread = true;
            thisNode.right = rightTree;
        }
        //Else it exists, so
        else
        {
            //Call recursively this same method and attach it to this tree
            thisNode.right = ThreadedNode.threadHelper(binaryRightTree, thisNode, rightTree);
        }

        //We are done creating this tree (or subtree if in a recursive state)
        return thisNode;
    }
//----------------------------------------------------------------------------------------------------------------------




//______________________________________________________________________________________________________________________
//------------------------------------------------- SETS AND GETS ------------------------------------------------------
    public T getElement()
    {
        return element;
    }

    public boolean isLeftAThread()
    {
        return lThread;
    }

    public ThreadedNode<T> getLeft()
    {
        return left;
    }

    public boolean isRightAThread()
    {
        return rThread;
    }

    public ThreadedNode<T> getRight()
    {
        return right;
    }

    public void setElement(T x)
    {
        element = x;
    }

    public void setLeft(ThreadedNode<T> t)
    {
        left = t;
    }

    public void setRight(ThreadedNode<T> t)
    {
        right = t;
    }

    public void setLeftThread(boolean newT)
    {
        lThread = newT;
    }

    public void setRightThread(boolean newT)
    {
        rThread = newT;
    }
//----------------------------------------------------------------------------------------------------------------------




//______________________________________________________________________________________________________________________
//-------------------------------------------------- OTHER METHODS------------------------------------------------------
    //Print the InOrder Traversal of this Threaded tree
    public void printInOrder()
    {
        //Print a bar to mark the beginning of each element in the traversal
        System.out.print("| ");

        //Create a temporary Threaded tree similar to this tree to work with
        ThreadedNode<T> thisTree = this;

        //While this tree still has left subtrees
        while(thisTree.getLeft() != null)
        {
            //Go left
            thisTree = thisTree.getLeft();
        }

        //At this point, we are at the leftmost child

        //As long as the child in question is not null
        while(thisTree != null)
        {
            //Print the child's element
            System.out.print(thisTree.getElement());

            //If the right child of this tree is a thread, then go right like normal
            if(thisTree.isRightAThread())
            {
                thisTree = thisTree.getRight();
            }
            //Else we are on a different subtree and must find the leftmost child of this new subtree
            else
            {
                //Create another temporary tree
                ThreadedNode<T> newTree = thisTree.getRight();

                //While the new tree's left subtree does not equal the real tree
                while(newTree.left != thisTree)
                {
                    //Go left
                    newTree = newTree.getLeft();
                }

                //We are now at the leftmost part of the new subtree

                //Replace the main tree with the new tree to continue the traversal
                thisTree = newTree;
            }

            //We are finished with this element! Print a bar to separate it from other elements.
            System.out.print(" | ");
        }
    }
//----------------------------------------------------------------------------------------------------------------------
}