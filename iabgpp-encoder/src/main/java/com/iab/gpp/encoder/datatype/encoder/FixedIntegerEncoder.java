package com.iab.gpp.encoder.datatype.encoder;

import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerEncoder {


  public static String encode(int value, int bitStringLength) {
    // let bitString = value.toString(2);

    StringBuilder bitString = new StringBuilder();
    while (value > 0) {
      if ((value & 1) == 1) {
        bitString.append("1");
      } else {
        bitString.append("0");
      }
      value = value >> 1;
    }

    while (bitString.length() < bitStringLength) {
      bitString.append("0");
    }

    return bitString.reverse().toString();
  }

  public static int decode(String bitString) throws DecodingException {
    // return parseInt(bitString, 2);

    int value = 0;

    for (int i = 0; i < bitString.length(); i++) {
      if (bitString.charAt(bitString.length() - (i + 1)) == '1') {
        value += 1 << i;
      }
    }

    return value;
  }
}
