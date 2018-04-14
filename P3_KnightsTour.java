package greedy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P3_KnightsTour {

	ArrayList<String> isMarked = new ArrayList<>();
	TreeMap<Integer, Point> points;
	int[] rows = new int[] { -2, 2, -2, 2, -1, 1, -1, 1 };
	int[] cols = new int[] { 1, -1, -1, 1, 2, -2, -2, 2 };
	String cell;
	int index = 1;
	static P3_KnightsTour knight = new P3_KnightsTour();
	static int[][] elements;
	static int size;

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private void printSolution() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(elements[i][j] + " ");
			}
			System.out.println();
		}
	}

	private int findOppotunities(int row, int col) {
		int opportunities = 0;
		for (int i = 0; i < 8; i++) {
			if (checkPoint(row + rows[i], col + cols[i])) {
				opportunities++;
			}
		}
		return opportunities;
	}

	private boolean isInBound(int row, int col) {
		if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
			return false;
		}
		return true;
	}

	public boolean checkPoint(int row, int col) {
		String currentCell = row + "" + col;
		if (!isMarked.contains(currentCell) && isInBound(row, col)) {
			return true;
		}
		return false;
	}

	private void findNextPoint(int row, int col) {
		for (int i = 0; i < 8; i++) {
			if (checkPoint(row + rows[i], col + cols[i])) {
				points.put(findOppotunities(row + rows[i], col + cols[i]),
						knight.new Point(row + rows[i], col + cols[i]));
			}
		}
	}

	private void solve(int row, int col) {
		while (isMarked.size() != size * size) {
			points = new TreeMap<>();
			elements[row][col] = index;
			index++;
			cell = row + "" + col;
			isMarked.add(cell);
			findNextPoint(row, col);
			if (points.size() != 0) {
				Map.Entry<Integer, Point> entry = points.entrySet().iterator().next();
				Point currentPoint = entry.getValue();
				row = currentPoint.x;
				col = currentPoint.y;
			}
		}
	}

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		size = Integer.parseInt(console.nextLine());
		elements = new int[size][size];
		knight.solve(0, 0);
		knight.printSolution();
	}

}