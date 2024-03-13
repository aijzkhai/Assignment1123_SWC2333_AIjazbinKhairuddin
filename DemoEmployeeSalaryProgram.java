/**
 * Programmer  : Aijaz bin Khairuddin
 * Section   : 1
 * Student ID  : AM2307013950
 * Purpose/Objective: To calculate the annual salary of each employee and categorize them based on annual income and years of service
 * Date    : 13th March 2024
 */

import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class DemoEmployeeSalaryProgram  {

  public static void main(String[] args) {
    // Decimal format for currency display
    DecimalFormat df = new DecimalFormat("0.00");

    // "try" block for error handling
    try {
      // Input file reader
      BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries(assignment 1123).txt"));

      // Output files for high and low performing employees
      PrintWriter highPerformers = new PrintWriter(new FileWriter("topPerformingEmployee.txt"));
      PrintWriter lowPerformers = new PrintWriter(new FileWriter("leastPerformingEmployee.txt"));

      // Write headers for output files
      writeHeader(highPerformers, "High Annual Salaries (Over RM200,000) and Long Service (Over 5 Years)");
      writeHeader(lowPerformers, "Low Annual Salaries (Under RM20,000) or Short Service (Under 5 Years)");

      String dataLine;
      while ((dataLine = inputFile.readLine()) != null) {
        StringTokenizer tokens = new StringTokenizer(dataLine, "|"); // Assuming comma separator

        String employeeName = tokens.nextToken();
        double baseSalary = Double.parseDouble(tokens.nextToken());
        int years = Integer.parseInt(tokens.nextToken());

        // Calculate annual salary with 5% raise
        double annualSalary = baseSalary + (baseSalary * 0.05 * years);

        // Categorize and write to respective output file
        if (annualSalary >= 10000 && years > 5) {
          writeEmployeeData(highPerformers, employeeName, annualSalary, years, df);
        } else if (annualSalary < 10000 || years <= 5) {
          writeEmployeeData(lowPerformers, employeeName, annualSalary, years, df);
        }
      }

      // Close all files
      inputFile.close();
      highPerformers.close();
      lowPerformers.close();

    

    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null, "Error: Input file 'employeeSalaries(assignment 1123).txt' not found.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: An unexpected error occurred while processing the files.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Error: Invalid data format in the input file. Please check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  // Method to write output file header
  private static void writeHeader(PrintWriter writer, String category) {
    writer.println("List of Employees with " + category + ":");
    writer.println("\n\n-----------------------------------------------------------------------------------------------");
    writer.println("\nEmployee Name\t\t\t\tAnnual Salary\t\t\t\tYears of Service");
    writer.println("\n-----------------------------------------------------------------------------------------------");
  }

  // Method to write formatted employee data to a file
  private static void writeEmployeeData(PrintWriter writer, String name, double salary, int years, DecimalFormat format) {
    writer.println(name + "\t\t\t" + format.format(salary) + "\t\t\t" + years);
  }
}
