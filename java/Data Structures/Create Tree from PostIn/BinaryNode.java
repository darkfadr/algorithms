import java.util.ArrayList;
import java.util.Stack;
import java.lang.reflect.Array;
import java.util.LinkedList; // to simulate a queue

public class BinaryNode<T>
{

    // create an empty node
    public BinaryNode()
    {
        this(null, null, null);
    }

    // create a node with given value and children
    public BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt)
    {
        element = theElement;
        left = lt;
        right = rt;
    }

    // return the element
    public T getElement()
    {
        return element;
    }

    // return the left child
    public BinaryNode<T> getLeft()
    {
        return left;
    }

    // return the right child
    public BinaryNode<T> getRight()
    {
        return right;
    }

    // set the element to a given value
    // @param x is the new value
    public void setElement( T x)
    {
        element = x;
    }

    // set the left child
    // @param t is the left child
    public void setLeft(BinaryNode<T> t)
    {
        left = t;
    }

    // set the right child
    // @param t is the right child
    public void setRight(BinaryNode<T> t)
    {
        right = t;
    }

    // @return the size of the subtree at node t 
    public static <T> int size( BinaryNode<T> t)
    {
        if ( t == null)
            return 0;
        else
            return 1 + size(t.left) + size(t.right);
    }

    // @return the height of the subtree of node t
    public static <T> int height( BinaryNode<T> t)
    {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    // find if an item occurs in the tree
    // @param inq is the inquiry
    public BinaryNode<T> find(T inq)
    {
        if (element.equals(inq))
            return this;
        BinaryNode<T> out = null;
        if ( left != null)
        {
            out = left.find(inq);
        }
        if ( out != null)
            return out;
        else if ( right != null)
            out = right.find(inq);
        return out;
    }

    public ArrayList<T> getInOrder()
    {
        ArrayList<T> inorder = new ArrayList<T>();
        inorder = getInOrderHelper(inorder);
        return(inorder);
    }

    public ArrayList<T> getInOrderHelper(ArrayList<T> inorder)
    {
        if (left != null)
            left.getInOrderHelper(inorder);
        inorder.add(element);
        if (right != null)
            right.getInOrderHelper(inorder);
        return inorder;
    }

    // create a duplicate tree
    // @return the root of the duplicate tree
    public BinaryNode<T> duplicate()
    {
        BinaryNode<T> root = new BinaryNode<T>(element,null,null);
        if ( left != null)
            // create a duplicate tree for the left subtree
            root.left = left.duplicate();
        if (right != null)
            root.right = right.duplicate();
        return root;
    }


    // print the tree elements in preorder
    public void printPreOrder()
    {
        System.out.print(element + ", ");
        if (left != null)
            left.printPreOrder();
        if (right != null)
            right.printPreOrder();
    }


    // print the tree elements in postorder // iterativeInorder
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


    // iterative in order 
    public void iterativeInOrder()
    {

        Stack<BinaryNode<T>> s = new Stack();
        BinaryNode<T> current = this;
        while (current != null || !s.empty())
        {
            if (current != null)
            {
                s.push(current);
                current = current.left;
            }
            else // pop the stack and go right
            {
                current = s.pop();
                System.out.println(current.element);
                current = current.right;
            }
        }
    }



    // print in post order
    public void printPostOrder()
    {
        if (left != null)
            left.printPostOrder();
        if (right != null)
            right.printPostOrder();
        System.out.println(element);
    }

    // iterative print in postorder
    public void iterativePostOrder()
    {
        // we save the node in s
        // in leftyOrRight we put l if we are processing the left subtree
        // and r if we are processing the right subtree 
        Stack<BinaryNode<T>> s = new Stack();
        Stack<String> leftOrRight = new Stack();
        BinaryNode<T> current = this;
        while ( !s.empty() || current != null)
        {
            if (current != null)
            {
                s.push(current);
                leftOrRight.push("l"); // we process the left subtree
                current = current.left;
            }
            else // pop the stack
            {
                // get current
                current = s.pop();
                String subtree = leftOrRight.pop();
                if (subtree.equals("l")) // process the right subtree
                {
                    s.push(current);
                    leftOrRight.push("r");
                    current = current.right; // traverse the right subtree
                }
                else // subtree is "r"
                {
                    System.out.println(current.element);
                    current = null;
                }
            }

        }
    }

    // print the tree elements in inorder
    public void printInOrder()
    {
        if (left != null)
            left.printInOrder();
        System.out.println(element);
        if (right != null)
            right.printInOrder();
    }

    // print the tree by levels
    public void printByLevels()
    {
        LinkedList<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
        q.add(this);
        // process the lements of q
        while ( !q.isEmpty())
        {
            // get the first node in the queue
            BinaryNode<T> current = q.removeFirst();
            System.out.println(current.getElement());
            // put the children of current at the end of the queue
            if (current.getLeft() != null)
                q.add(current.getLeft());
            if (current.getRight() != null)
                q.add(current.getRight());
        }

    }

    public static <T> BinaryNode<T> prePlusIn(T[] pre, T[] in)
    {
        if ( pre.length != in.length)
            throw new IllegalArgumentException();
        BinaryNode<T> node  = prePlusIn(pre, 0, pre.length-1, in, 0, in.length -1);
        return node;

    }

    // contruct a tree from the prorder slice pre[lp:hp] and inorder slice in[li:hi]
    // we assume that the 2 slices are the same length
    private static <T> BinaryNode<T> prePlusIn( T[] pre, int lp, int hp, T[] in, int li, int hi)
    {

        if ( lp > hp)
            return null;
        else if (lp == hp)
            return new BinaryNode<T>(pre[lp],null,null);
        else
        {
            BinaryNode<T>  node = new BinaryNode<T>(pre[lp],null, null);
            // search for pre[lp] in in[li:hi]
            int j = li;
            for (; j <= hi; j++)
            {
                if ( pre[lp].equals(in[j]))
                    break;
            }

            if (j > hi)
                throw new IllegalArgumentException("We cannot construct the tree");
            // the length of the left subtree
            node.setLeft(prePlusIn(pre,lp+1, lp + j -li, in, li, j-1));
            node.setRight(prePlusIn(pre,lp + j -li + 1, hp, in, j+1, hi));
            return node;
        }
    }


    public static <T> BinaryNode<T> postPlusIn(T[] post, T[] in)
    {
        if ( post.length != in.length)
            throw new IllegalArgumentException();
        BinaryNode<T> node  = postPlusIn(post, 0, post.length-1, in, 0, in.length -1);
        return node;

    }

    // contruct a tree from the prorder slice pre[lp:hp] and inorder slice in[li:hi]
    // we assume that the 2 slices are the same length
    private static <T> BinaryNode<T> postPlusIn( T[] post, int lp, int hp, T[] in, int li, int hi)
    {

        if ( lp > hp)
            return null;
        else if (lp == hp)
            return new BinaryNode<T>(post[hp],null,null);
        else
        {
            BinaryNode<T>  node = new BinaryNode<T>(post[hp],null, null);
            // search for post[lp] in in[li:hi]
            int j = li;
            for (; j <= hi; j++)
            {
                if ( post[hp].equals(in[j]))
                    break;
            }

            if (j > hi)
                throw new IllegalArgumentException("We cannot construct the tree");
            // the length of the left subtree
            node.setLeft(postPlusIn(post,lp, lp + j -li -1, in, li, j-1));
            node.setRight(postPlusIn(post,lp + j -li, hp -1, in, j+1, hi));
            return node;
        }
    }

    // construct a binary tree from its levels and in order traversals
    public static <T> BinaryNode<T> levelsPlusIn(T[] in, T[] levels)
    {
        if ( levels.length != in.length)
            throw new IllegalArgumentException();
        BinaryNode<T> node  = levelsPlusIn(levels, in, 0, in.length -1);
        return node;
    }

    // helper method for levelsPlusIn
    private static <T> BinaryNode<T> levelsPlusIn( T[] levels, T[] in, int li, int hi)
    {

        if (levels.length == 0)
            return null;
        if (levels.length == 1)
            return new BinaryNode<T>(levels[0],null,null);
        BinaryNode<T> node = new BinaryNode<T>(levels[0],null,null);
        // search for levels[ll] in the array in
        int rootIndex = index(levels[0],in,li,hi);
        // form the 2 level arrays and fill them up

        T[] leftLevels = (T[]) new Object[rootIndex - li];
        T[] rightLevels = (T[]) new Object[hi - rootIndex];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int j = 1; j < levels.length; j++)
            if (index(levels[j],in,li,hi)< rootIndex)
                leftLevels[leftIndex++] = levels[j];
            else
                rightLevels[rightIndex++] = levels[j];
        // set the children of node
        node.left = levelsPlusIn(leftLevels, in, li, rootIndex - 1);
        node.right = levelsPlusIn(rightLevels, in, rootIndex + 1, hi);
        return node;
    }

    // search a[low,high] for the position of x
    private static <T> int index(T x, T[] arr, int low, int hi)
    {
        for (int i = low; i <= hi; i++)
            if (x.equals(arr[i]))
                return i;
        // not found
        return -1;
    }


    // longestPath returns the elements along a longest path
    public static <T> ArrayList<T> longestPath(BinaryNode<T> root)
    {
        ArrayList<T> arr = new ArrayList();
        return longestPath(root,arr);  //helper method             
    }

    private static <T> ArrayList<T> longestPath(BinaryNode<T> root, ArrayList<T> path)
    {
        if (root == null)
            return path;
        // the element of the root to the path
        path.add(root.element);
        // follow the longest path
        if (height(root.left) >= height(root.right))
            return longestPath(root.left,path);
        else
            return longestPath(root.right, path);
    }


    // find the parent of a given node in this tree
    // if n =null throw an illegal argument exception
    // if n is the root or does not occur in the tree return null.
    public BinaryNode<T> parent(BinaryNode<T> n)
    {
        if ( n == null)
            throw new IllegalArgumentException("The argument is null");
        BinaryNode<T> p = null;
        if (n == this) // the root has no parent
            return null;
        return findParent(n);
    }

    // find the parent of n != null
    // we know that this != n
    private BinaryNode<T> findParent(BinaryNode<T> n)
    {
        BinaryNode<T> p = null;

        // check if this is the parent
        if (left == n || right == n)
            return this;

        // check if n occurs in the left subtree
        if (left!= null)
            p = left.parent(n);
        // check if n was found in the left subtree
        if (p!= null)
            return p;

        // n was not found in the left subtree, so check the right one
        if (right!= null)
            p = right.parent(n);
        return p;
    }

    // a nonrecursive longestPath
    public static <T> ArrayList<T> longestPath2(BinaryNode<T> root)
    {
        ArrayList<T> arr = new ArrayList();
        BinaryNode<T> current = root;
        while (current != null)
        {
            arr.add(current.element);
            if (height(root.left) >= height(root.right))
                current = current.left;
            else
                current = current.right;
        }
        return arr;
    }

    // return a longest path of binary nodes in the tree with root root 
    public static <T> BinaryNode<T>[] longestPath3(BinaryNode<T> root)
    {
        if (root == null)
            return null;
        // traverse the root tree in postorder
        // the output tree
        BinaryNode<T>[] longestPath = null;

        Stack<BinaryNode<T>> s = new Stack();
        Stack<String> leftOrRight = new Stack();
        BinaryNode<T>  current = root;
        while ( !s.empty() || current != null)
        {
            if (current != null)
            {
                s.push(current);
                leftOrRight.push("l"); // we process the left subtree
                current = current.left;
            }
            else // pop the stack
            {
                // get current
                current = s.pop();
                String subtree = leftOrRight.pop();
                if (subtree.equals("l")) // process the right subtree
                {
                    s.push(current);
                    leftOrRight.push("r");
                    current = current.right; // traverse the right subtree
                }
                else // process current
                {

                    // check if current is a leaf
                    if (current.left == null && current.right == null)
                    {
                        if (longestPath == null || s.size() > longestPath.length)
                        {
                            // update longestPath
                            BinaryNode<T>[] longer = (BinaryNode<T>[]) Array.newInstance(current.getClass(), s.size() + 1);
                            // copy s onto longer

                            int i = longer.length -1;
                            for (BinaryNode<T> e: s)
                            {
                                longer[i--] = e;
                            }
                            longer[i] = current;
                            longestPath = longer;
                        }
                    }

                    current = null;
                }
            }   // end else

        }    // end while

        return longestPath;
    }

    // check if this is isomorphic to r
    // the isomorphism means that they have the same children,
    // though the order may be different
    public boolean isomorphic(BinaryNode<T> r )
    {

        if (r== null)
            return false;
        if (element == null && r.element != null ||
                element != null && r.element == null)
            return false;
        if ( element != null && !element.equals(r.element))
            return false;
        if (left != null && right != null)
            return left.isomorphic(r.left) && right.isomorphic(r.right) ||
                    left.isomorphic(r.right) && right.isomorphic(r.left);
        if (left != null) // right == null
            return r.right == null && left.isomorphic(r.left) ||
                    r.left == null && left.isomorphic(r.right);
        if (right != null) // left == null
            return r.left == null && right.isomorphic(r.right) ||
                    r.right == null && right.isomorphic(r.left);
        // both left and right are null
        return r.left == null && r.right == null;

    }

    // check if this is equal to r
    public boolean equals(BinaryNode<T> r )
    {

        if (r == null)
            return false;
        if (element == null && r.element != null ||
                element != null && r.element == null)
            return false;
        if ( element != null && !element.equals(r.element))
            return false;
        if (left != null && right != null)
            return left.equals(r.left) && right.equals(r.right) ;
        if (left != null) // right == null
            return r.right == null && left.equals(r.left);
        if (right != null) // left == null
            return r.left == null && right.equals(r.right);
        // both left and right are null
        return r.left == null && r.right == null;

    }

    // the fields
    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
}
