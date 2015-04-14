package gpa;

import BinaryTrees.BinarySearchTree;
import BinaryTrees.TreeIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Lab 13: TreeSort using a Binary Search Tree
 *
 * @author Sam Beckmann
 *         ID: 1443946
 *         CS 2003-01
 */
public class Lab13
{
    /**
     * Name of the file that contains the input.
     */
    private static final String FILENAME = "students.in";

    public static void main(String[] args)
    {
        BinarySearchTree<StudentGPA> tree = parseFile(new File(FILENAME));
        System.out.println(printResults(tree));
    } // end main

    /**
     * parses of file of input GPAs, creating a binary tree from them.
     *
     * @param file Input file to be parsed.
     * @return A BinarySearchTree created from the input file.
     */
    private static BinarySearchTree<StudentGPA> parseFile(File file)
    {
        BinarySearchTree<StudentGPA> tree = new BinarySearchTree<StudentGPA>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currLine = reader.readLine();
            while (currLine != null)
            {
                Scanner input = new Scanner(currLine);
                int id = input.nextInt();
                String initials = input.next();
                double gpa = input.nextDouble();
                if (input.hasNext())
                    tree.insert(new GraduateStudentGPA(id, initials, gpa, input.next()));
                else
                    tree.insert(new StudentGPA(id, initials, gpa));
                currLine = reader.readLine();
            }
        } catch (IOException e)
        {
            System.err.print("Encounter IO error creating Binary Tree from data.\n" + e);
        }
        return tree;
    } // end parseFile

    /**
     * Returns a formatted String of all the students, in order of their GPA.
     *
     * @param tree BinarySearchTree to be iterated through.
     * @return String of all students, sorted by GPA.
     */
    private static String printResults(BinarySearchTree<StudentGPA> tree)
    {
        TreeIterator<StudentGPA> iterator = new TreeIterator<StudentGPA>(tree);
        String result = "";
        while (iterator.hasNext())
        {
            result += iterator.next().toString() + "\n";
        }
        return result;
    } // end printResults
}
