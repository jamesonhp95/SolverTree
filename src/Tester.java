import java.util.Scanner;

public class Tester {
    public static void main(String args[])
    {
        SolverTree tree = new SolverTree();
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter numbers in this format: 2345");
        try
        {
            String sequence = kb.nextLine();
            tree.insertSequence(sequence);
            tree.printPossibleWords();
        } catch(Exception err)
        {
            System.out.println("Invalid number format");
        }
    }
}
