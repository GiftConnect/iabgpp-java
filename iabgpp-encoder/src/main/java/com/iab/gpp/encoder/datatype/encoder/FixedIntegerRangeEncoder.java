package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {


  public static String encode(List<Integer> value) {
    Collections.sort(value);

    List<List<Integer>> groups = new ArrayList<>();

    int groupStartIndex = 0;
    while (groupStartIndex < value.size()) {
      int groupEndIndex = groupStartIndex;
      while (groupEndIndex < value.size() - 1 && value.get(groupEndIndex) + 1 == value.get(groupEndIndex + 1)) {
        groupEndIndex++;
      }

      groups.add(value.subList(groupStartIndex, groupEndIndex + 1));

      groupStartIndex = groupEndIndex + 1;
    }

    StringBuilder bitString = new StringBuilder(FixedIntegerEncoder.encode(groups.size(), 12));
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        bitString.append("0").append(FixedIntegerEncoder.encode(groups.get(i).get(0), 16));
      } else {
        bitString.append("1").append( FixedIntegerEncoder.encode(groups.get(i).get(0), 16)
            + FixedIntegerEncoder.encode(groups.get(i).get(groups.get(i).size() - 1), 16));
      }
    }
    return bitString.toString();
  }

  public static List<Integer> decode(String bitString) throws DecodingException {
    List<Integer> value = new ArrayList<>();
    int count = FixedIntegerEncoder.decode(bitString.substring(0, 12));
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString.substring(startIndex, startIndex + 1));
      startIndex++;

      if (group == true) {
        int start = FixedIntegerEncoder.decode(bitString.substring(startIndex, startIndex + 16));
        startIndex += 16;

        int end = FixedIntegerEncoder.decode(bitString.substring(startIndex, startIndex + 16));
        startIndex += 16;

        for (int j = start; j <= end; j++) {
          value.add(j);
        }
      } else {
        int val = FixedIntegerEncoder.decode(bitString.substring(startIndex, startIndex + 16));
        value.add(val);
        startIndex += 16;
      }
    }

    return value;
  }
}
