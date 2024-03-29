package ru.job4j.test;

import java.util.*;

public final class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }
    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PhoneNumber)) {
                return false;
            }
            PhoneNumber pn = (PhoneNumber) o;
            return pn.lineNum == lineNum && pn.prefix == prefix
                    && pn.areaCode == areaCode;
        }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        PhoneNumber firstNumber = new PhoneNumber(727, 327, 5349);
        PhoneNumber secondNumber = new PhoneNumber(727, 347, 5349);
        m.put(firstNumber, "Alex");
        m.put(firstNumber, "Elena");
        m.put(secondNumber, "Sergey");
        System.out.println("title: " + m.get(firstNumber)
                + " hashCodeNewObj: " + new PhoneNumber(727, 327, 5349).hashCode()
                + " hashCodeExObj: " + firstNumber.hashCode());
        System.out.println("title: " + m.get(secondNumber)
                + " hashCodeNewObj: " + new PhoneNumber(727, 347, 5349).hashCode()
                + " hashCodeExObj: " + secondNumber.hashCode());

    }
}