package util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Util {

    private Util() {}

    public static String toString(Object objToRepresent) {

        StringBuilder strBuilder = new StringBuilder(objToRepresent.getClass().getName());

        strBuilder.append("[");

        strBuilder.append(

                Arrays.stream(objToRepresent.getClass().getDeclaredFields())

                        .map((field) -> {

                            field.setAccessible(true);

                            try
                            {
                                return field.getName() + ":" + field.get(objToRepresent);
                            }
                            catch (Exception e)
                            {
                                return field.getName() + ":" + e.getMessage();
                            }

                        }).collect(Collectors.joining(", ")));

        strBuilder.append("]");

        return strBuilder.toString();

    }

}