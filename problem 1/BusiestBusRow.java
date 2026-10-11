import java.util.Scanner;

/**
 * Finds the row with the largest sum in a bus seating grid.
 *
 * Complexity of busiestRow: O(r * c) time and O(1) additional space,
 * where r is the number of rows and c is the number of columns.
 */
public class BusiestBusRow {
    public static final class Result {
        public final int rowIndex;
        public final int total;

        private Result(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }
    }

    /**
     * Returns the zero-based index and student total of the busiest row.
     * If rows tie, the row with the smaller index is returned.
     */
    public static Result busiestRow(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException("The grid must have at least one row and one column.");
        }

        int columns = grid[0].length;
        int busiestIndex = 0;
        int busiestTotal = Integer.MIN_VALUE;

        for (int row = 0; row < grid.length; row++) {
            if (grid[row] == null || grid[row].length != columns) {
                throw new IllegalArgumentException("Every grid row must have the same number of columns.");
            }

            int rowTotal = 0;
            for (int students : grid[row]) {
                rowTotal += students;
            }

            // Strictly greater preserves the first (smallest-index) row on a tie.
            if (rowTotal > busiestTotal) {
                busiestTotal = rowTotal;
                busiestIndex = row;
            }
        }

        return new Result(busiestIndex, busiestTotal);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            int[][] grid = new int[rows][columns];

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    grid[row][column] = scanner.nextInt();
                }
            }

            Result result = busiestRow(grid);
            System.out.printf("Row %d, Total %d%n", result.rowIndex, result.total);
        }
    }
}
