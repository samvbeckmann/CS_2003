import java.util.Scanner;

/**
 * Lab6: Complete the recursive method isaPalindrome
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
public class Lab6
{


    public static void main(String[] args)
    {
        /******************************************************** Palindrome */
        if (!isaPalindrome("satanoscillatemymetallicsonatas"))
            System.out.println("You have a bug in your code!");
        else
            System.out.println("check for palindrome ok");

        Scanner console = new Scanner(System.in);
        System.out.print("Input a string\n>");
        String line = console.nextLine();
        while (!line.equals(""))
        {
            if (isaPalindrome(line))
                System.out.println(line + " is a palindrome");
            else
                System.out.println(line + " is *not* a palindrome");
            System.out.print("Input a string\n >");
            line = console.nextLine();
        }
    }

    /**
     * method to determine whether the specified string is a palindrome
     *
     * @param expr string
     * @return true if the specified string is a palindrome, otherwise false.
     */
    public static boolean isaPalindrome(String expr)
    {
        if (expr.length() <= 1) // Base Case
            return true;

        else if (expr.charAt(0) != expr.charAt(expr.length()-1)) // Not a palindrome
            return false;

        else
            return isaPalindrome(expr.substring(1, expr.length()-1)); // Recursive call
    }

}
