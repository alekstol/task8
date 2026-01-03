package ru.vsu.cs.course1;
public class task8 {
    public static int[][] process(int[][] arr2) {
        if (arr2 == null || arr2.length == 0 || arr2[0].length == 0) {
            return new int[0][0];
        }
        int rows = arr2.length;
        int cols = arr2[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = arr2[i][j];
                int count = 0;
                int r = i, c = j;
                while (r >= 0 && c >= 0) {
                    if (arr2[r][c] == value) count++;
                    r--;
                    c--;
                }
                r = i + 1;
                c = j + 1;
                while (r < rows && c < cols) {
                    if (arr2[r][c] == value) count++;
                    r++;
                    c++;
                }
                r = i - 1;
                c = j + 1;
                while (r >= 0 && c < cols) {
                    if (arr2[r][c] == value) count++;
                    r--;
                    c++;
                }
                r = i + 1;
                c = j - 1;
                while (r < rows && c >= 0) {
                    if (arr2[r][c] == value) count++;
                    r++;
                    c--;
                }
                result[i][j] = count;
            }
        }
        return result; // ← ВАЖНО: вернуть результат!
    }
}