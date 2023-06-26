package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerEncoder {

  public static String encode(int value) {
    List<Integer> fib = new ArrayList<Integer>();
    if (value >= 1) {
      fib.add(1);

      if (value >= 2) {
        fib.add(2);

        int i = 2;
        while (value >= fib.get(i - 1) + fib.get(i - 2)) {
          fib.add(fib.get(i - 1) + fib.get(i - 2));
          i++;
        }
      }
    }

    StringBuilder bitString = new StringBuilder("1");
    for (int i = fib.size() - 1; i >= 0; i--) {
      int f = fib.get(i);
      if (value >= f) {
        bitString.append("1");
        value -= f;
      } else {
        bitString.append("0");
      }
    }

    return bitString.reverse().toString();
  }

  public static int decode(String bitString) throws DecodingException {
    int value = 0;

    List<Integer> fib = new ArrayList<>();
    for (int i = 0; i < bitString.length() - 1; i++) {
      if (i == 0) {
        fib.add(1);
      } else if (i == 1) {
        fib.add(2);
      } else {
        fib.add(fib.get(i - 1) + fib.get(i - 2));
      }
    }

    for (int i = 0; i < bitString.length() - 1; i++) {
      if (bitString.charAt(i) == '1') {
        value += fib.get(i);
      }
    }
    return value;
  }
}
