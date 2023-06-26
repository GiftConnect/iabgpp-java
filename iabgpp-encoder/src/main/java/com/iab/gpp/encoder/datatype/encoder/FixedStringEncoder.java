package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedStringEncoder {
    private static final String SPACE = FixedIntegerEncoder.encode(63, 6);

  public static String encode(String value, int stringLength) throws EncodingException {
    while (value.length() < stringLength) {
      value += " ";
    }

    StringBuilder bitString = new StringBuilder();
    for (int i = 0; i < value.length(); i++) {
      int code = (int) value.charAt(i);
      if (code == 32) {
        // space
        bitString.append(SPACE);
      } else if (code >= 65) {
        bitString.append(FixedIntegerEncoder.encode(((int) value.charAt(i)) - 65, 6));
      } else {
        throw new EncodingException("Unencodable FixedString '" + value + "'");
      }
    }

    return bitString.toString();
  }

  public static String decode(String bitString) throws DecodingException {
    StringBuilder value = new StringBuilder();

    for (int i = 0; i < bitString.length(); i += 6) {
      int code = FixedIntegerEncoder.decode(bitString.substring(i, i + 6));
      if (code == 63) {
        value.append(" ");
      } else {
        value.append((char) (code + 65));
      }
    }

    return value.toString().trim();
  }
}
