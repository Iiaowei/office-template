package org.example.template.core;

public class Position {
    public static char[] colArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
    'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String getPosition(int rowIndex, int colIndex) {
        StringBuilder positionBuilder = new StringBuilder();
        int i;
        do {
            i = colIndex % colArray.length;
            positionBuilder.append(colArray[i]);
            colIndex = colIndex / colArray.length;
        } while (colIndex != 0);
        positionBuilder.append(rowIndex + 1);
        return positionBuilder.toString();
    }
}
