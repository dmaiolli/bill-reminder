package br.com.dmaiolli.billreminder.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ArrayUtil {

    public static String removeAndReturnArrayElement(List<String> array, int pos) {
        String element = array.get(pos);

        array.remove(element);

        return element;
    }

}
