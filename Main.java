import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.Helper;
import src.learning.java.Flight;

public class Main {

  int x = 90; // an attribute of the class.
  // private static final RetrievalApi CLIENT_RETRIEVAL = new
  // GroundingClient().retrieval();

  public static void main(String[] args) {
    // variables in java
    // declaring variables
    // type variableName = value;
    // 1. Strings - double quotes
    // args is an array of commandLine arguments 
    // if we run java Main.java 1 hi "hello world"
    // args will be equal to ["1","hi","hello world"]
    if(args.length>1){
      for (String arg : args) {
        System.out.println(arg);
      }
    }
    String str = "hello";
    Flight f = new Flight();
    // 2. integers - whole numbers
    int num = 123;
    // 3. float - stores floating point numbers
    float fp = 19.99f;
    // char - store single character - 'a', 'B' - single quotes
    char ch = 'a';
    // boolean - stores true or false
    boolean flag = true;

    // constant variable - which can not be reassign - read-only
    final int fixedNum = 90;

    // Primitive data types - includes byte, short, int, long, float, double,
    // boolean and char
    // Non-primitive data types - such as String, Arrays and Classes
    // Non-primitive data types are called reference types because they refer to
    // objects.

    // print the value
    // println use the newline in the end
    System.out.println(str);
    System.out.println(str.length());
    System.out.println(num);
    System.out.println(fixedNum);
    System.out.println(fp);
    System.out.println(ch);
    System.out.println(flag);
    System.out.println("Hello World");
    // print doesn't use newline in the end
    System.out.print("pritn a string ");
    System.out.print("new string");

    // Math module
    System.out.println(Math.max(5, 10));
    System.out.println(Math.min(5, 10));
    System.out.println(Math.sqrt(64));

    // loops - same as c++ loops
    // for-each loop
    String[] cars = { "volvo", "bmw", "ford", "tata" };
    for (String car : cars) {
      System.out.println(car);
    }

    // Arrays
    // Arrays are used to store multiple values in a single variable of same type
    // array of integers
    int[] nums = { 10, 20, 30, 40 };
    // or
    int ages[] = { 20, 22, 18, 40 };
    System.out.println(nums[0] + ", " + ages.length);
    // length of array
    System.out.println(nums.length);

    // loop through array
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }

    // multiDimensional Arrays
    int[][] numbers = { { 1, 2, 3, 4 }, { 5, 6, 7 } };

    // java methods
    myMethod();
    System.out.println(addNum(10, 20));

    int myNum1 = add(8, 5);
    double myNum2 = add(4.3, 6.26);
    System.out.println("int: " + myNum1);
    System.out.println("double: " + myNum2);

    createClassObject();

    // test package
    testPackages();

    PrimitiveDataTypes primitiveDataTypes = new PrimitiveDataTypes();
    primitiveDataTypes.define();
    WorkingWithStrings workingWithStrings = new WorkingWithStrings();
    workingWithStrings.define();
  }

  // method - A method is a block of code which only runs when it is called.
  // A method must be declared within a class
  static void myMethod() {
    // myMethod() is the name of the method
    // static means that the method belongs to the Main class and not an object of
    // the Main class.
    // void means that this method does not have a return value.
    System.out.println("I just got executed!");

  }

  // method with return a value
  static int addNum(int num1, int num2) {
    return num1 + num2;
  }
  /*
   * Method Overloading With method overloading, multiple methods can have the
   * same name with different parameters:
   */

  static int add(int x, int y) {
    return x + y;
  }

  static double add(double x, double y) {
    return x + y;
  }
  /*
   * Java Scope In Java, variables are only accessible inside the region they are
   * created. This is called scope.
   */

  /*
   * Java - What is OOP? OOP stands for Object-Oriented Programming.
   * 
   * Procedural programming is about writing procedures or methods that perform
   * operations on the data, while object-oriented programming is about creating
   * objects that contain both data and methods. So, a class is a template for
   * objects, and an object is an instance of a class.
   */

  static void createClassObject() {
    Main obj = new Main();
    System.out.println(obj.x);

    // call static method
    myStaticMethod();
    obj.myPublicMethod();
  }

  public void temp() {
    myPublicMethod();
  }

  /*
   * Static vs. Public You will often see Java programs that have either static or
   * public attributes and methods.
   * 
   * In the example above, we created a static method, which means that it can be
   * accessed without creating an object of the class, unlike public, which can
   * only be accessed by objects:
   */

  /*
   * static method, can be accessed without creating an object of the class and
   * can be accessed inside static method directly public method, which can only
   * be accessed by objects and inside the non-static methods directly
   */

  // Static method
  static void myStaticMethod() {
    System.out.println("Static methods can be called without creating objects");
  }

  // Public method
  public void myPublicMethod() {
    System.out.println("Public methods must be called by creating objects");
    System.out.println(x);
  }

  /*
   * A constructor in Java is a special method that is used to initialize objects.
   * The constructor is called when an object of a class is created. It can be
   * used to set initial values for object attributes: Note that the constructor
   * name must match the class name, and it cannot have a return type (like void).
   * 
   * Also note that the constructor is called when the object is created.
   * 
   * All classes have constructors by default: if you do not create a class
   * constructor yourself, Java creates one for you. public class Main { int x; //
   * Create a class attribute
   * 
   * // Create a class constructor for the Main class public Main() { x = 5; //
   * Set the initial value for the class attribute x }
   * 
   * public static void main(String[] args) { Main myObj = new Main(); // Create
   * an object of class Main (This will call the constructor)
   * System.out.println(myObj.x); // Print the value of x } }
   */

  /*
   * working with packages
   */

  static public void testPackages() {
    String[] strs = { "hi", "helper" };
    Helper hel = new Helper();
    hel.main(strs);
    hel.main(strs);
    Scanner myObj = new Scanner(System.in);
    String userName;

    // Enter username and press Enter
    System.out.println("Enter username");
    userName = myObj.nextLine();

    System.out.println("Username is: " + userName);

    Main m = new Main();

    System.out.println("\n\n check the query " + m.parseQueryString("$expand=organization($expand=nameDetails)"));

  }

  private Map<String, String[]> parseQueryString(String queryString) {
    Map<String, String[]> queryParams = new HashMap<>();
    if (queryString == null || queryString.isEmpty()) {
      return queryParams;
    }

    Pattern pattern = Pattern.compile("(\\$[a-zA-Z]+)=([^&]+)(&|$)");
    Matcher matcher = pattern.matcher(queryString);

    while (matcher.find()) {
      String key = matcher.group(1);
      String value = matcher.group(2);
      queryParams.put(key, new String[] { value });

      System.err.println("\n" + key + " : " + value);
    }
    return queryParams;
  }
}

class PrimitiveDataTypes {

  void define() {
    /*
     * Integer types byte - size 1byte = 8bits - range (-2^7 to 2^7-1)=(-128 to 127)
     * short - size 2byte = 16bits - range (-2^15 to 2^15-1)=(-32768 to 32767) 
     * int - size 4byte = 32bits - range (-2^31 to 2^31-1)=(-2147483648 to 2147483647)
     * long - size 8byte = 64bits - range (-2^63 to 2^63-1)=(-9223372036854775808 to
     * 9223372036854775807)
     */
    byte b = 90;
    short s = 10000;
    int i = 100000;
    long l = 10006589L;
    System.out.println("byte : " + b + " short : " + s + " int : " + i + " long : " + l);
    
    /* Floating Points Types (fractional numbers or decimal numbers)
     * float - size 4byte = 32bits
     * double - size 8byte = 64bits
     */
    float f = 3.15f;
    double d = 3.15d;
    System.out.println("float : "+f+" double : " +d);
    /*Character Type
     * char - stores a single unicode character
     */
    char ch = 'c';
    System.out.println("char : "+ch );
    // Boolean Type
    boolean trueOrFalse = true;
    System.out.println("boolean : "+trueOrFalse);
  }
}

class AdvanceTypes {
  void define(){
    int [] intArray = new int[5]; // array of fixed size
    intArray[2]=56;
    //  array initialization
    int [] intArray2 ={1,2,3,4};
    System.out.println(intArray[2]);
    System.out.println(intArray.length);

    int sum = 0;
    // for each loop
    for (int i : intArray2) {
      sum+=i;
    }
    System.out.println(sum);
  }
}

// Reference Types - means variable stores the reference
// Classes in Java are Reference Types
// String is also a class 
class WorkingWithStrings {
  /* String
   * stores a sequence characters
   * all unicode charaters in UTF-16 format
   * literals mean (as it is or contants) are enclosed in double qoutes
   * Strings are immutable
   * String variables donot directly hold the string value instead stores a reference (address) to the instance of String
   */
  void define(){
    String str = "I am a string";
    String temp = "Getting started with strings in java";
    System.out.println(str + temp);
    // equality of two strings will be work as expected as equality will check for reference not value
    // to check equality use
    System.out.println(str.equals(temp));
   System.out.println( temp.split(" ")[0]);
  }
}