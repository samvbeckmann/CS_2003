package implementations;

import BinaryTrees.HeapSort;
import gpa.GraduateStudentGPA;
import gpa.StudentGPA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Sam Beckmann
 *         ID: 1443946
 *         CS 2000-01
 */
public class Lab14
{
    /**
     * Name of the file that contains the input.
     */
    private static final String FILENAME = "students.in";

    public static void main(String[] args)
    {
        Vector<StudentGPA> vec = parseFile(new File(FILENAME));
        new HeapSort<StudentGPA>().heapSort(vec);
        System.out.println(printResults(vec));
    } // end main

    /**
     * parses of file of input GPAs, creating a binary tree from them.
     *
     * @param file Input file to be parsed.
     * @return A BinarySearchTree created from the input file.
     */
    private static Vector<StudentGPA> parseFile(File file)
    {
        Vector<StudentGPA> vector = new Vector<StudentGPA>();
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
                    vector.add(new GraduateStudentGPA(id, initials, gpa, input.next()));
                else
                    vector.add(new StudentGPA(id, initials, gpa));
                currLine = reader.readLine();
            }
        } catch (IOException e)
        {
            System.err.print("Encounter IO error creating Vector from data.\n" + e);
        }
        return vector;
    } // end parseFile

    /**
     * Returns a formatted String of all the students, in order of their GPA.
     *
     * @param vector BinarySearchTree to be iterated through.
     * @return String of all students, sorted by GPA.
     */
    private static String printResults(Vector<StudentGPA> vector)
    {
        String result = "";
        for (StudentGPA gpa : vector)
        {
            result += gpa.toString() + "\n";
        }
        return result;
    } // end printResults
}
