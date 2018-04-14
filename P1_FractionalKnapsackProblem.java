package greedy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class P1_FractionalKnapsackProblem {

	public static class Item {
		double price;
		double weight;

		public Item(Double price, Double weight) {
			this.price = price;
			this.weight = weight;
		}
	}

	class Sort implements Comparator<Item> {

		@Override
		public int compare(Item a, Item b) {
			return (int) (a.price / a.weight - b.price / b.weight);
		}

	}

	static ArrayList<Item> items;

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);

		String[] firstLine = console.nextLine().split(" ");
		int capacity = Integer.parseInt(firstLine[1]);

		String[] secondLine = console.nextLine().split(" ");
		int n = Integer.parseInt(secondLine[1]);

		items = new ArrayList<Item>();

		for (int i = 0; i < n; i++) {
			String[] line = console.nextLine().split(" ");
			double price = Double.parseDouble(line[0]);
			double weight = Double.parseDouble(line[2]);
			items.add(new Item(price, weight));
		}
		console.close();
		P1_FractionalKnapsackProblem p = new P1_FractionalKnapsackProblem();
		Collections.sort(items, p.new Sort());

		double totalPrice = 0.0;
		int index = 0;
		DecimalFormat decimalFormat = new DecimalFormat("#.00");

		while (capacity > 0 && index < items.size()) {
			Item currentItem = items.get(index);
			double takenQuantity = Math.min(capacity, items.get(index).weight);

			double percQuantity = takenQuantity / currentItem.weight;
			totalPrice += percQuantity * currentItem.price;

			capacity -= takenQuantity;
			index++;

			String percAsString = percQuantity == 1 ? "100" : decimalFormat.format(percQuantity * 100);

			System.out.printf("Take %s%% of item with price %.2f and weight %.2f\n", percAsString, currentItem.price,
					currentItem.weight);
		}

		System.out.printf("Total price: %.2f", totalPrice);

	}

	private static void sort(ArrayList<Item> items) {
		for (int j = 0; j < items.size(); j++) {
			for (int index = 1; index < items.size(); index++) {
				double currentItem = items.get(index).price / items.get(index).weight;
				double previousItem = items.get(index - 1).price / items.get(index - 1).weight;

				if (currentItem > previousItem) {
					swap(index);
				}
			}
		}
	}

	private static void swap(int i) {
		Item temp = items.get(i);
		items.remove(i);
		items.add(i, items.get(i - 1));
		items.remove(i - 1);
		items.add(i - 1, temp);
	}

}