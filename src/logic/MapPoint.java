package logic;

import java.awt.Point;
import java.util.ArrayList;

public class MapPoint {
	public static String[][] Field;
	public static ArrayList<Point> point;

	public MapPoint() {
		setPoint(createField());
	}

	public static ArrayList<Point> createField() {
		Field = CSVParser.readCSV("DiamondMapCsv.csv");
		point = new ArrayList<Point>();
		for (int j = 6; j < 15; j++) {
			for (int i = 5; i < 18; i++) {
				if (!(Field[j][i].equals("8"))) {
					point.add(new Point(i * 32, j * 32));

				}
			}
		}
		return point;

	}

	public static ArrayList<Point> getPoint() {
		return point;
	}

	public static void setPoint(ArrayList<Point> point) {
		MapPoint.point = point;
	}

}
