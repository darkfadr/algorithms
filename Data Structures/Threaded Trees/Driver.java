public class Driver
{


    public static void main(String[] args)
    {
        System.out.println("Testing Threaded Nodes");
        System.out.println("======================\n\n\n");

        System.out.println("We create a binary tree.");
        BinaryNode<Integer> n1 = new BinaryNode<Integer>(98,null,null);
        BinaryNode<Integer> n2 = new BinaryNode<Integer>(78, null,null);
        BinaryNode<Integer> n3 = new BinaryNode<Integer>(87, null,null);
        BinaryNode<Integer> n4 = new BinaryNode<Integer>(39, null,null);
        BinaryNode<Integer> n5 = new BinaryNode<Integer>(55, null,null);
        BinaryNode<Integer> n6 = new BinaryNode<Integer>(60, null,null);
        BinaryNode<Integer> n7 = new BinaryNode<Integer>(37, null,null);
        BinaryNode<Integer> n8 = new BinaryNode<Integer>(50, n1,n2);
        BinaryNode<Integer> n9 = new BinaryNode<Integer>(45, n3,n4);
        BinaryNode<Integer> n10 = new BinaryNode<Integer>(49, null,n5);
        BinaryNode<Integer> n11 = new BinaryNode<Integer>(40, n6,null);
        BinaryNode<Integer> n12 = new BinaryNode<Integer>(35, null,n8);
        BinaryNode<Integer> n13 = new BinaryNode<Integer>(95, n9,n10);
        BinaryNode<Integer> n14 = new BinaryNode<Integer>(65, n11,n7);
        BinaryNode<Integer> n15 = new BinaryNode<Integer>(25, n12,null);
        BinaryNode<Integer> n16 = new BinaryNode<Integer>(20, n13,n14);
        BinaryNode<Integer> n17 = new BinaryNode<Integer>(15, n15,n16);

        // print the nodes in inorder
        System.out.println("The nodes of the binary tree in inorder are:");
        n17.printInOrder(); // n17 is the root


        // form the threaded tree
        System.out.println("\nWe form the threaded tree.");
        ThreadedNode<Integer> threadedTree = new ThreadedNode(n17);
        System.out.println("We did it");

        System.out.println("Now we print the threaded tree in inorder.");
        threadedTree.printInOrder();


        // Now test threading a binary tree and producing a graph
        System.out.println("\n\nWe test threading and unthreading of a binary tree/graph. ");
        System.out.println("We first thread the original tree.");

        BinaryNode<Integer> graph = BinaryNode.thread(n17);
        //BinaryNode<Integer> graph = ThreadedNode.thread(n17);

        System.out.println("The graph is equal to the original tree? " + n17.equals(graph));
        System.out.println("Now we unthread it.");
        BinaryNode<Integer> newNode = BinaryNode.unthread(graph);
        // BinaryNode<Integer> newNode = ThreadedNode.unthread(graph);
        System.out.println("The unthreaded graph is equal to the original tree? " + n17.equals(newNode));
    }
}