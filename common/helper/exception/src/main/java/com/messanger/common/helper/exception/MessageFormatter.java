package com.messanger.common.helper.exception;

import java.util.Arrays;
import java.util.HashMap;

public class MessageFormatter {
    private static final String START_DELIMITER_TOKEN = "{";
    private static final String END_DELIMITER_TOKEN = "}";
    private static final String DELIMITER_STRING = START_DELIMITER_TOKEN + END_DELIMITER_TOKEN;
    private static final char ESCAPE_TOKEN = '\\';

    public static String format(String msg, Object... args) {
        if (msg == null) {
            return null;
        }

        if (args == null || args.length == 0) {
            return msg.replace(DELIMITER_STRING, "");
        }

        StringBuilder builder = new StringBuilder(msg.length() + 50);

        int startIndex = 0;
        for (int i = 0; i < args.length; i++) {
            int delimIndex = msg.indexOf(DELIMITER_STRING);

            if (delimIndex == -1) {
                break;
            }

            if (isEscapeToken(msg, delimIndex)) {
                if (!isDoubleEscaped(msg, delimIndex)) {
                    i--;
                    builder.append(msg, i, delimIndex - 1);
                    builder.append(START_DELIMITER_TOKEN);
                    startIndex = delimIndex + 1;
                } else {
                    builder.append(msg, startIndex, delimIndex - 1);
                    deeplyAppendParameter(builder, args[i], new HashMap<>());
                    startIndex = delimIndex + 2;
                }
            } else {
                builder.append(msg, startIndex, delimIndex);
                deeplyAppendParameter(builder, args[i], new HashMap<>());
                startIndex = delimIndex + 2;
            }
        }

        return builder.append(msg, startIndex, msg.length()).toString();
    }

    private static boolean isEscapeToken(String msg, int index) {
        return index != 0 && msg.charAt(index - 1) == ESCAPE_TOKEN;
    }

    private static boolean isDoubleEscaped(String msg, int index) {
        return index >= 2 && msg.charAt(index - 2) == ESCAPE_TOKEN;
    }

    private static void deeplyAppendParameter(StringBuilder builder, Object arg, HashMap<Object[], Object> seenMap) {
        if (arg == null) {
            builder.append("null");
            return;
        }

        if (arg.getClass().isArray()) {
            if (arg instanceof boolean[]) {
                booleanArrayAppend(builder, (boolean[]) arg);
            } else if (arg instanceof byte[]) {
                byteArrayAppend(builder, (byte[]) arg);
            } else if (arg instanceof char[]) {
                charArrayAppend(builder, (char[]) arg);
            } else if (arg instanceof short[]) {
                shortArrayAppend(builder, (short[]) arg);
            } else if (arg instanceof int[]) {
                intArrayAppend(builder, (int[]) arg);
            } else if (arg instanceof long[]) {
                longArrayAppend(builder, (long[]) arg);
            } else if (arg instanceof float[]) {
                floatArrayAppend(builder, (float[]) arg);
            } else if (arg instanceof double[]) {
                doubleArrayAppend(builder, (double[]) arg);
            } else {
                objectArrayAppend(builder, (Object[]) arg, seenMap);
            }
        } else {
            safeObjectAppend(builder, arg);
        }
    }

    private static void booleanArrayAppend(StringBuilder builder, boolean[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void byteArrayAppend(StringBuilder builder, byte[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void charArrayAppend(StringBuilder builder, char[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void shortArrayAppend(StringBuilder builder, short[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void intArrayAppend(StringBuilder builder, int[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void longArrayAppend(StringBuilder builder, long[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void floatArrayAppend(StringBuilder builder, float[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void doubleArrayAppend(StringBuilder builder, double[] a) {
        builder.append(Arrays.toString(a));
    }

    private static void objectArrayAppend(StringBuilder builder, Object[] arg, HashMap<Object[], Object> seenMap) {
        builder.append('[');
        if (!seenMap.containsKey(arg)) {
            seenMap.put(arg, null);
            final int len = arg.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(builder, arg[i], seenMap);
                if (i != len - 1)
                    builder.append(", ");
            }
            seenMap.remove(arg);
        } else {
            builder.append("...");
        }
        builder.append(']');
    }

    private static void safeObjectAppend(StringBuilder builder, Object arg) {
        try {
            String oAsString = arg.toString();
            builder.append(oAsString);
        } catch (Throwable t) {
            builder.append("[FAILED toString()]");
        }
    }
}
