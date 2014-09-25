public class Test
{
    public static void main(String[] args)
    {
        Character[] post = {'A', 'C', 'E', 'D', 'B', 'H', 'I', 'G', 'F'};
        Character[] in = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        BinaryNode<Character> bn = BinaryNode.postPlusIn(post, in);

        BinaryTree tree = new BinaryTree(bn);
        System.out.println(tree.get(0));
        //System.out.println(tree.get(1));
        //System.out.println(tree.get(2));
    }
}
