import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Lab 7
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
public class Lab7
{

    /**
     * Enables print statements for debugging.
     */
    static boolean debugStatements = false;

    /**
     * Asks for File input, parses the file into lines, then prints the result of each line,
     * evaluated as a postfix expression
     *
     * @param args String arguments passed when executing.
     */
    public static void main(String[] args)
    {
        try
        {
            Scanner console = new Scanner(System.in);
            System.out.print("Enter name of file: ");
            String input = console.next();

            BufferedReader br = new BufferedReader(new FileReader(new File(input)));

            String line = br.readLine();
            while (!(line == null))
            {
                System.out.println(evaluatePostfix(line));
                line = br.readLine();
            }

        } catch (IOException e)
        {
            System.err.print("IO ERROR!");
        }
    } // end main

    /**
     * Evaluates a postfix expression contained in a given String.
     *
     * @param expr String to evaluate result of.
     * @return int result of the expression.
     */
    public static int evaluatePostfix(String expr)
    {
        StackVectorBased<Integer> stack = new StackVectorBased<Integer>();
        Scanner parser = new Scanner(expr);
        parser.useDelimiter(" ");

        while (parser.hasNext())
        {
            String part = parser.next();

            if (part.equalsIgnoreCase("+"))
            {
                stack.push(stack.pop() + stack.pop());
                if (debugStatements) System.out.println("Adding Result: " + stack.peek());

            } else if (part.equalsIgnoreCase("-"))
            {
                int buffer = stack.pop();
                stack.push(stack.pop() - buffer);
                if (debugStatements) System.out.println("Subtracting Result: " + stack.peek());

            } else if (part.equalsIgnoreCase("*"))
            {
                stack.push(stack.pop() * stack.pop());
                if (debugStatements) System.out.println("Multiplying Result: " + stack.peek());

            } else if (part.equalsIgnoreCase("/"))
            {
                int buffer = stack.pop();
                stack.push(stack.pop() / buffer);
                if (debugStatements) System.out.println("Division Result: " + stack.peek());

            } else
            {
                stack.push(Integer.parseInt(part));
                if (debugStatements) System.out.println("Pushing Value: " + stack.peek());
            }
        }
        int result = stack.pop();
        if (stack.isEmpty())
        {
            return result;
        } else
        {
            System.out.println("ERROR: Incorrect number of values in stack!");
            return result;
        }
    } // end evaluatePostfix

} // end Lab7
