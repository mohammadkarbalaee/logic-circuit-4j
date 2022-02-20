package encoding;

import formatconversion.FormatConverter;

import java.util.ArrayList;

public class HammingCode {

  public static String encode(String BCD) {
    int BCDlength = BCD.length();
    int parityCount = parityCountCalculator(BCD);
    StringBuilder encodedString = new StringBuilder(BCD);
    int codeLength = parityCount + BCDlength;
    for (int i = 1; i <= codeLength; i++) {
      if (isPowerOf2(i)) {
        encodedString.insert(i - 1,'%');
      }
    }
    int unknownIndex = 0;
    for (int i = 0; i < codeLength; i++) {
      if (encodedString.charAt(i) == '%') {
        unknownIndex++;
        char missingParityBit;
        ArrayList<Character> xorBits = new ArrayList<>();
        for (int j = i + 1; j < codeLength; j++) {
          if (encodedString.charAt(j) == '%') {
            continue;
          }
          String binaryForm = FormatConverter.toBinary(j + 1);
          if (binaryForm.charAt(binaryForm.length() - unknownIndex) == '1') {
            xorBits.add(encodedString.charAt(j));
          }
        }
        char tempXORresult = XOR(xorBits.get(0),xorBits.get(1));
        for (int j = 2; j < xorBits.size(); j++) {
          tempXORresult = XOR(tempXORresult,xorBits.get(j));
        }
        if (tempXORresult == '0') {
          missingParityBit = '0';
        } else {
          missingParityBit = '1';
        }
        encodedString.setCharAt(i,missingParityBit);
      }
    }
    return encodedString.toString();
  }

  private static char XOR(char A,char B) {
    if (A == B) {
      return '0';
    } else {
      return '1';
    }
  }

  public static boolean isPowerOf2(int number) {
    boolean isPowerOf2 = false;
    double squareRoot = logBase2(number);
    if (squareRoot == Math.floor(squareRoot)) {
      isPowerOf2 = true;
    }
    return isPowerOf2;
  }

  private static double logBase2(int number) {
    double logOnBase10 = Math.log10(number);
    double logOnBase10Two = Math.log10(2);
    return logOnBase10 / logOnBase10Two;
  }


  public static int parityCountCalculator(String BCD) {
    int BCDlength = BCD.length();
    int parityCount = 0;
    boolean foundParityCount = false;
    for (int i = 0; !foundParityCount ; i++) {
      double twoRaisedByi = Math.pow(2, i);
      if ((BCDlength + 1 + i) <= twoRaisedByi) {
        parityCount = i;
        foundParityCount = true;
      }
    }
    return parityCount;
  }

  public String decode() {
    return "";
  }

  public String fixCode() {
    return "";
  }
}
