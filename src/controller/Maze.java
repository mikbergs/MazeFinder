package controller;

import java.util.List;
import java.util.Random;

import model.DisjointSets;
import model.Pair;
import model.Point;

public class Maze extends Board {
	private ExtendedGraph graph;
	private DisjointSets set;
	private int numberOfSets;
	private Point.Direction direction;
	private Random rng, randomCell;

	public Maze(int rows, int cols) {
		super(rows, cols);
		randomCell = new Random();
		rng = new Random();
	}

	public void create() {
		graph = new ExtendedGraph();
		set = new DisjointSets(maxCell);
		numberOfSets = maxCell;
		while (numberOfSets > 1) {
			
			//Decides which direction by the RNG
			int i;
			i = rng.nextInt(4);
			switch (i) {
			case 0:
				direction = Point.Direction.UP;
				break;
			case 1:
				direction = Point.Direction.LEFT;
				break;
			case 2:
				direction = Point.Direction.RIGHT;
				break;
			case 3:
				direction = Point.Direction.DOWN;
				break;
			}
			// creates two poits on the same cell.
			int a = randomCell.nextInt(maxCell);
			Point p1 = new Point(getRow(a), getCol(a));
			Point p2 = new Point(getRow(a), getCol(a));
			// move the
			p2.move(direction);


			if (isValid(p2)) {
				int id1 = getCellId(p1);
				int id2 = getCellId(p2);
				int set1 = set.find(id1);
				int set2 = set.find(id2);
				if (set1 != set2) {
					set.union(set1, set2);
					numberOfSets--;
					graph.addEdge(id1, id2, 1);
					graph.addEdge(id2, id1, 1);
					Pair<Integer, Point.Direction> pair = new Pair<Integer, Point.Direction>(id1, direction);
					setChanged();
					notifyObservers(pair);
				}
			}
		}
	}

	public void search() {
		List<Integer> path = graph.getPath(maxCell - 1);
		for (Integer cellId : path) {
			setChanged();
			notifyObservers(cellId);
		}
	}
}