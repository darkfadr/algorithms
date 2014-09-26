import java.util.ArrayList;

public class Test
{
    public static <T> void main(String args[])
    {
        Character[] post = {'A', 'C', 'E', 'D', 'B', 'H', 'I', 'G', 'F'};
        Character[] in = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        BinaryNode<Character> bn = BinaryNode.postPlusIn(post, in);

        Character[] post1 = {'A', 'C', 'E', 'D', 'B'};
        Character[] in1 = {'A', 'B', 'C', 'D', 'E'};
        BinaryNode<Character> bn1 = BinaryNode.postPlusIn(post1, in1);

        bn.printInOrder();
        bn.iterativeInOrder();
        Boolean test = bn.equals(bn1);
        System.out.println(test);

        BinaryNode<Character> bn3 = bn.parent(bn1);
        if(bn3 != null)
        {
            bn3.iterativeInOrder();

        }
    }
}
