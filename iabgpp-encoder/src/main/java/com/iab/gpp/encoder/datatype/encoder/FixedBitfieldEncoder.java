package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedBitfieldEncoder {


  public static String encode(List<Boolean> value, int bitStringLength) throws EncodingException {
    StringBuilder bitString = new StringBuilder();
    for (int i = 0; i < value.size(); i++) {
      bitString.append(BooleanEncoder.encode(value.get(i)));
    }

    while (bitString.length() < bitStringLength) {
      bitString.append("0");
    }

    return bitString.toString();
  }

  public static List<Boolean> decode(String bitString) throws DecodingException {
    List<Boolean> value = new ArrayList<>();
    for (int i = 0; i < bitString.length(); i++) {
      value.add(BooleanEncoder.decode(bitString.substring(i, i + 1)));
    }
    return value;
  }
}
