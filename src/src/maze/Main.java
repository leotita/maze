package maze;


import java.util.ArrayList;
import java.util.Stack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	public  static MapPane pane = new MapPane(800,800);
	public static ArrayList<MazePoint> my;
	public static Map map = new Map();
	public static ArrayList<MazePoint> s = null;
	public static Circle circle=new Circle(10);
	public static Stack<MazePoint> stack = new Stack<>();
	public static ArrayList<MazePoint> list1 = new ArrayList<>();
	public static Hander hander=null;
	public static double sp = pane.getPrefWidth() / ((double) map.getHeight() + 1);// 迷宫墙之间的间距
	public final static double SWB = 4;// 迷宫墙宽度
	public final static double SWP = 3;// 路径宽度
	public final static double SW = 30;
	public final static double SH = 30;
	public static Stage stage;
	

	public static void main(String[] args) {
		

		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
	     
		my = new ArrayList<>();	
	    my = map.getMaze();
//		 map.writeMap(my);
		 
//		my = map.getMapByFile();
		Parent fxmlright=FXMLLoader.load(getClass().getResource("RightBox.fxml"));
		
		// for (int i = 0; i < map.getHeight(); i++) {
		// for (int j = 0; j < map.getWidth(); j++) {
		// MazePoint m = my.get(j + i * map.getWidth());
		// System.out.print(m.getRight() + "" + m.getDown() + "" + m.getLeft() + "" +
		// m.getUp() + "\t");
		// }
		// System.out.println();
		//
		// }

		// 找路径
		 hander = new Hander(my, map);
		 
		// Stack<MazePoint> path = hander.randfind();
		ArrayList<MazePoint> list = new ArrayList<>();
		// for (int i = path.size() - 1; i >= 0; i--) {
		// list.add(path.pop());
		//
		// }

		// 迷宫显示面板
		
		circle.setFill(Color.RED);
		circle.setCenterX(my.get(0).getX()* sp + SW + sp / 2.0);
		circle.setCenterY(my.get(0).getY()* sp + SH + sp / 2.0);
		
		list1.add(my.get(0));

		s = list1;

		// 画迷宫
		pane = (MapPane) MapPaths.PaintMap(pane, my, map);
		
		// 画路径
		// pane = MapPaths.PaintPath(pane, list, map);

		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("🍎");
		MenuItem menuFile = new MenuItem("💰");
		menuFile.setOnAction(e->
		{
			pane.getChildren().clear();
			ImageView bp=new ImageView(new Image("123.png"));
			pane.getChildren().add(bp);
		});
		menu.getItems().addAll(menuFile);
		menubar.getMenus().add(menu);


		BorderPane bp = new BorderPane();
		bp.setTop(menubar);
		bp.setCenter(pane);

		bp.setRight(fxmlright);
//		bp.setBottom(fxmlbottom);
		
		
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();

	}
	
	public static void step()
	{
		
		s = hander.randFindonce(stack, list1, pane);
		circle.setCenterX(s.get(s.size()-1).getX()* sp + SW + sp / 2.0);
		circle.setCenterY(s.get(s.size()-1).getY()* sp + SH + sp / 2.0);
		
	}
}
