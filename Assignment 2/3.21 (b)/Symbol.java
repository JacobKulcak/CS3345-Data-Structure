// 3.21 b
import java.util.Stack;
import java.util.Scanner;

public class Symbol {
  // This function checks whether the String str has balancing symbols
  // Example: {[[/*([()])*/]]} -> balanced
  //          {([[(]])}        -> not balanced
  // Time complexity: O(n), where n is the number of characters in the String str
  public static boolean check(String str) {
    Stack<Character> s = new Stack<>(); // create new Stack from java Stack library

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i); // take character from String str at position i

      // ===== Push open-symbols =====
      if (c == '(' || c == '[' || c == '{') {
        s.push(c);
        continue;
      }

      // if char is '/', it needs to be followed by '*'
      if (c == '/' && i + 1 < str.length() && str.charAt(i + 1) == '*') {
        s.push('/');
        i++;
        continue;
      }

      // skip spaces
      if (c == ' ')
        continue;

      char ch;

      // ===== Pop close-symbols =====
      switch (c) {
        case ')':
            if (s.empty())
                return false; // if stack is still empty -> close-symbol without prev. open-symbol
          ch = s.pop();
          if (ch != '(')
            return false;
          break;
        case ']':
            if (s.empty())
                return false;
          ch = s.pop();
          if (ch != '[')
            return false;
          break;
        case '}':
            if (s.empty())
                return false;
          ch = s.pop();
          if (ch != '{')
            return false;
          break;
        case '*':
            if (s.empty())
                return false;
          // if char is '*', it needs to be followed by '/'
          if (i + 1 < str.length() && str.charAt(i + 1) == '/') {
            ch = s.pop();
            if (ch != '/')
              return false;
            i++;
            break;
          }
          return false;
      }
    }
    // if there's symbols left in stack, means they never got closed
    return s.empty();
  }

    // Main for testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // for user input

        System.out.print("Enter testcase: ");
        String testCase = scanner.nextLine();  // Read user input
        scanner.close();

        System.out.println(check(testCase) ? "Balanced" : "Not Balanced");
    }
}