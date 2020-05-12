package com.scottsdaleair.dataTests;

import static org.junit.Assert.assertEquals;

import com.scottsdaleair.data.PhoneNumber;
import org.junit.Test;

public class PhoneNumberTest {

  @Test
  public void constructor_validInput_allValuesInstantiated() {
    PhoneNumber phoneNumber = new PhoneNumber("test", "test");
    boolean allValuesGood = (phoneNumber.getName() != null && phoneNumber.getNum() != null);
    assertEquals(true, allValuesGood);
  }

  @Test
  public void setName_validInput_allValuesInstantiated() {
    PhoneNumber phoneNumber = new PhoneNumber("test", "test");
    phoneNumber.setName("changed");
    boolean nameChanged = phoneNumber.getName().equals("changed");
    assertEquals(true, nameChanged);
  }

  @Test
  public void setNum_validInput_allValuesInstantiated() {
    PhoneNumber phoneNumber = new PhoneNumber("test", "test");
    phoneNumber.setNum("changed");
    boolean numChanged = phoneNumber.getNum().equals("changed");
    assertEquals(true, numChanged);
  }

  @Test
  public void equals_validInput_returnsTrue() {
    PhoneNumber phoneNumber = new PhoneNumber("a", "a");
    PhoneNumber phoneNumber2 = new PhoneNumber("a", "a");
    boolean allValuesGood = phoneNumber.equals(phoneNumber2);
    assertEquals(true, allValuesGood);
  }

  @Test
  public void equals_validInput_returnsFalse() {
    PhoneNumber phoneNumber = new PhoneNumber("a", "a");
    PhoneNumber phoneNumber2 = new PhoneNumber("b", "b");
    boolean allValuesGood = phoneNumber.equals(phoneNumber2);
    assertEquals(false, allValuesGood);
  }

  @Test
  public void toString_returnsExpectedString() {
    PhoneNumber phoneNumber = new PhoneNumber("a", "a");
    String expected = "{name='a', num='a'}";
    assertEquals(expected, phoneNumber.toString());
  }
}
