package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncodableOptimizedFixedRangeTest {

  private EncodableOptimizedFixedRange encodableOptimizedFixedRange = new EncodableOptimizedFixedRange();

  @Test
  public void testEncode1() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(12, 24, 48));
    Assertions.assertEquals("00000000001100000000000000001000000000001000000000000000000000001",
        encodableOptimizedFixedRange.encode());
  }

  @Test
  public void testEncode2() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(18, 30));
    Assertions.assertEquals("00000000000111100000000000000000001000000000001", encodableOptimizedFixedRange.encode());
  }

  @Test
  public void testEncode3() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(28));
    Assertions.assertEquals("000000000001110000000000000000000000000000001", encodableOptimizedFixedRange.encode());
  }

  @Test
  public void testEncode4() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(29));
    Assertions.assertEquals("0000000000011101100000000000100000000000011101", encodableOptimizedFixedRange.encode());
  }

  @Test
  public void testDecode1() {
    encodableOptimizedFixedRange.decode("00000000001100000000000000001000000000001000000000000000000000001");
    Assertions.assertEquals(Arrays.asList(12, 24, 48), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode2() {
    encodableOptimizedFixedRange.decode("00000000000111100000000000000000001000000000001");
    Assertions.assertEquals(Arrays.asList(18, 30), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode3() {
    encodableOptimizedFixedRange.decode("000000000001110000000000000000000000000000001");
    Assertions.assertEquals(Arrays.asList(28), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode4() {
    encodableOptimizedFixedRange.decode("0000000000011101100000000000100000000000011101");
    Assertions.assertEquals(Arrays.asList(29), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("000000000001110000000000000000000000000000001", encodableOptimizedFixedRange.substring(
        "000010001111010010000110111111111100000000001111010010000110111111111100000000000000000000000000000000000000000100001101000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000001000000000000000000000000000000",
        213));
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("0000000000011101100000000000100000000000011101", encodableOptimizedFixedRange.substring(
        "000010001111010010000110111111111100000000001111010010000110111111111100000000000000000000000000000000000000000100001101000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110110000000000010000000000001110100000000000000000000000000000",
        213));
  }
}
