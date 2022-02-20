package formatconversion;

public class FormatConverter {
  public static String toBinary(int number) {
    String binary = "";
    while (number >= 2) {
      int remainder = number % 2;
      binary += remainder;
      number /= 2;
    }
    binary += number;
    int difference = 4 - binary.length();
    for (int i = 0; i < difference; i++) {
      binary += "0";
    }
    return new StringBuilder(binary).reverse().toString();
  }
}
