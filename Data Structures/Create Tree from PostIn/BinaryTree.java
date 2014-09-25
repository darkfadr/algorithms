import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinaryTree<T> {

    /** Creates an empty binary tree */
    public BinaryTree()
    {
        root = null;
    }

    // create a binary tree with a given root value
    public BinaryTree( T rootItem)
    {
        root = new BinaryNode( rootItem, null, null);
        size = 1;
    }



    public int get(int i)
    {
        ArrayList<T> inorder = root.getInOrder();
        System.out.println(inorder);
        //return(inorder.get(i));
        return 0;
    }

    // @return the root of the tree
    public BinaryNode<T> getRoot()
    {
        return root;
    }

    // @return the size of the tree
    public int size()
    {
        return size;
    }

    // @return the the height of the tree
    public int height()
    {
        return BinaryNode.height(root);
    }

    // print the tree in preorder
    public void printPreOrder()
    {
        if (root != null)
            root.printPreOrder();
    }

    // print the tree in postorder
    public void printPostOrder()
    {
        if (root != null)
            root.printPostOrder();
    }

    // print the tree in inorder
    public void printInOrder()
    {
        if (root != null)
            root.printInOrder();
    }

    // empty the tree
    public void makeEmpty()
    {
        root = null;
        size = 0;
    }

    // check if the tree is empty
    public boolean isEmpty()
    {
        return root == null;
    }

    // forms a new tree from rootItem t1 and t2 
    // does not allow t1 and t2 to be the same
    public void merge(T rootItem, BinaryTree<T> t1, BinaryTree<T> t2)
    {
        if (t1.root == t2.root && t1.root != null)
            throw new IllegalArgumentException();

        // allocate new node
        root = new BinaryNode<T>(rootItem, t1.root, t2.root);
        size = t1.size() + t2.size() + 1;
        // ensures that every node is in one tree
        if (this != t1)
            t1.root = null;
        if ( this != t2)
            t2.root = null;
    }


    private BinaryNode<T> root;
    private int size = 0;
}