package maze;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class MapPaths extends Main {

	private Map map;
	private ArrayList<MazePoint> list;
	private Pane pane;

	public MapPaths(Map map, ArrayList<MazePoint> list, Pane pane) {
		this.map = map;
		this.list = list;
		this.pane = pane;

	}

	public static Pane PaintPath(Pane pane, ArrayList<MazePoint> list, Map map) {
		for (int i = 0; i < list.size() - 1; i++)

		{

			MazePoint t = list.get(i);
			MazePoint t2 = list.get(i + 1);
			Line line = new Line(t.getX() * sp + SW + sp / 2.0, t.getY() * sp + SH + sp / 2.0,
					t2.getX() * sp + SW + sp / 2.0, t2.getY() * sp + SH + sp / 2.0);
			line.setStroke(Color.GOLD);
			line.setStrokeWidth(SWP);
			//
			//// System.out.println("(" + t.getX() + "," + t.getY() + ")");
			// //
			// // // circle.setRadius(5);
			// // circle.setCenterX(t.getX() * sp + sp +sp/ 2.0);
			// // circle.setCenterY(t.getY() * sp + sp +sp/ 2.0);
			// // circle.setFill(Color.RED);
			// // circle.setStroke(Color.BLACK);
			pane.getChildren().add(line);
		}
		return pane;
	}

	public static Pane PaintPath(Pane pane, ArrayList<MazePoint> list, Map map, Paint color) {
		for (int i = 0; i < list.size() - 1; i++)

		{

			MazePoint t = list.get(i);
			MazePoint t2 = list.get(i + 1);
			Line line = new Line(t.getX() * sp + SW + sp / 2.0, t.getY() * sp + SH + sp / 2.0,
					t2.getX() * sp + SW + sp / 2.0, t2.getY() * sp + SH + sp / 2.0);
			line.setStroke(color);
			line.setStrokeWidth(SWP + 2);
			//
			//// System.out.println("(" + t.getX() + "," + t.getY() + ")");
			// //
			// // // circle.setRadius(5);
			// // circle.setCenterX(t.getX() * sp + sp +sp/ 2.0);
			// // circle.setCenterY(t.getY() * sp + sp +sp/ 2.0);
			// // circle.setFill(Color.RED);
			// // circle.setStroke(Color.BLACK);
			pane.getChildren().add(line);
		}
		return pane;
	}

	public static Pane PaintMap(Pane pane, ArrayList<MazePoint> my, Map map) {
		for (int i1 = 0; i1 < map.getHeight(); i1++) {
			for (int j = 0; j < map.getWidth(); j++) {
				int index = i1 * map.getWidth() + j;
				if (my.get(index).getUp() == 0) {
					if ((my.get(index).getY() - 1) >= 0 && my.get(index - map.getWidth()).getDown() != 0
							|| my.get(index).getY() == 0) {
						Line lineUp = new Line(j * sp + SW, i1 * sp + SH, j * sp + sp + SW, i1 * sp + SH);
						lineUp.setStrokeWidth(SWB);

						pane.getChildren().add(lineUp);
					}
				}
				if (my.get(index).getLeft() == 0) {
					if ((my.get(index).getX() - 1) >= 0 && my.get(index - 1).getRight() != 0
							|| my.get(index).getX() == 0) {
						Line lineLeft = new Line(j * sp + SW, i1 * sp + SH, j * sp + SW, i1 * sp + sp + SH);
						lineLeft.setStrokeWidth(SWB);
						pane.getChildren().add(lineLeft);
					}
				}
				if (my.get(index).getDown() == 0) {
					Line lineDown = new Line(j * sp + SW, i1 * sp + sp + SH, j * sp + sp + SW, i1 * sp + sp + SH);
					lineDown.setStrokeWidth(SWB);
					pane.getChildren().add(lineDown);
				}
				if (my.get(index).getRight() == 0) {
					Line lineRight = new Line(j * sp + sp + SW, i1 * sp + SH, j * sp + sp + SW, i1 * sp + sp + SH);
					lineRight.setStrokeWidth(SWB);
					pane.getChildren().add(lineRight);
				}

			}
			for (int i = 0; i < map.getWidth(); i++) {
				Label wl = new Label(i + "");
				wl.setFont(Font.font(10));
				wl.setLayoutX(i * sp + SW + sp / 2.0);
				wl.setLayoutY(SW / 2.0 );
				pane.getChildren().add(wl);

			}
			for (int j = 0; j < map.getHeight(); j++) {
				Label wh = new Label(j + "");
				wh.setFont(Font.font(10));
				wh.setLayoutX(SW /2.0 );
				wh.setLayoutY(SH + sp * j + sp / 2.0);
				pane.getChildren().add(wh);

			}

		}
		pane.setStyle("-fx-background-color: edfd;-fx-border-style:solid;");
		pane.getChildren().add(circle);

		return pane;

	}

	public static Pane PaintPathNum(Pane pane,ArrayList<MazePoint>my,Map map)
	{
		for (int i1 = 0; i1 < map.getHeight(); i1++) {
			for (int j = 0; j < map.getWidth(); j++) {
				int index = i1 * map.getWidth() + j;
				String s=my.get(index).getStep()+"";
				Label label=new Label(s);
				label.setLayoutX(my.get(index).getX() * sp + SW + sp / 2.0);
				label.setLayoutY(my.get(index).getY() * sp + SH + sp / 2.0);
				pane.getChildren().add(label);
				
				
				
				
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		return pane;
		
	}
}
