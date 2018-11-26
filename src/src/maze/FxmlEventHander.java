package maze;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FxmlEventHander implements Initializable{
	File filePath=new File("d:");
	File filePath1=new File("d:");
	Circle circle=Main.circle;
	public static int Width;
	public static int Height;
	public static  Stage window=null;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button importMap;
	
	public void importMap()
	{
		importMap.setOnMouseClicked(e->
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				    new FileChooser.ExtensionFilter("All TXT", "*.*"),
				    new FileChooser.ExtensionFilter("TXT", "*.txt")
				);
			fileChooser.setTitle("Open Resource File");
			fileChooser.setInitialDirectory(filePath);
			File f=fileChooser.showOpenDialog(Main.stage);
			try {
				if(f!=null)
				Main.my=Map.getMapByFile(f);
				Main.map=new Map((int)Math.sqrt(Main.my.size()),(int)Math.sqrt(Main.my.size()));
				Main.sp = Main.pane.getPrefWidth() / ((double) Main.map.getHeight() + 1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(f!=null)
			{
			String s=f.getParent();
			filePath=new File(s);
			Main.pane.getChildren().clear();
			Main.hander = new Hander(Main.my, Main.map);
			Main.pane = (MapPane) MapPaths.PaintMap(Main.pane, Main.my, Main.map);
			Main.stack = new Stack<>();
			Main.list1.add(Main.my.get(0));

			Main.s = Main.list1;
				
			
			}
		});
	}
	
	@FXML
	private Button StepOnce;
	
	public void StepOnce()
	{
		StepOnce.setOnMouseClicked(e->
		{
//			Map.reNewMap(Main.my);
			Main.step();
		});
	}
	
	
	@FXML
	private Button randMap;
	public void randMap() throws Exception
	{
//		textOfWidth.deleteText(0, 9);
		randMap.setOnMouseClicked(e->
		{
			
		    window=new Stage();
			window.setTitle("Width And Height");
			window.initModality(Modality.APPLICATION_MODAL);
			window.setMinHeight(200);
			window.setMinWidth(300);
		
				Parent fxmlwindow = null;
				try {
					fxmlwindow = FXMLLoader.load(getClass().getResource("windowOfRandMap.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			Scene scene=new Scene(fxmlwindow);
			scene.setFill(null);
			window.setScene(scene);
			window.initStyle(StageStyle.TRANSPARENT);
		    window.showAndWait();
			
		}); 
	}

	@FXML
	private Button findPath;
	public void findPath()
	{
		findPath.setOnMouseClicked(e->
		{
			Map.reNewMap(Main.my);
			MapPaths.PaintPath(Main.pane, Main.hander.randfind(), Main.map);
			Map.reNewMapPoint(Main.my);
			circle.setCenterX(Main.my.get(Main.my.size()-1).getX()* Main.sp + Main.SW + Main.sp / 2.0);
			circle.setCenterY(Main.my.get(Main.my.size()-1).getY()* Main.sp + Main.SH + Main.sp / 2.0);
			
			
		});
	}
	
	
	@FXML
	private Button close;
	public void close()
	{
		close.setOnMouseClicked(e->
		{
			window.close();
			
		});
	}
	@FXML
	private Button ok;
	@FXML
	private TextField textOfWidth;
	
	@FXML
	private TextField textOfHeight;
	
	public void TextOfWidth()
	{
		textOfWidth.setOnKeyPressed(e->
		{
			if(e.getCode()==KeyCode.ENTER)
				System.out.println("adfjlka");
			
		});
	}
	public void TextOfHeight()
	{
		textOfHeight.setOnKeyPressed(e->
		{
			if(e.getCode()==KeyCode.ENTER)
				{
				Width=Integer.parseInt(textOfWidth.getText());
				Height=Integer.parseInt(textOfHeight.getText());
				window.close();
				
				Main.pane.getChildren().clear();
				Main.map=new Map(Width,Height);
				Main.my = Main.map.getMaze();
				
				Main.sp=Main.pane.getPrefWidth() / ((double) Main.map.getHeight() + 1);
				Main.hander = new Hander(Main.my, Main.map);
			    Main.pane = (MapPane) MapPaths.PaintMap(Main.pane, Main.my, Main.map);
			    Main.stack=new Stack<MazePoint>();
			    Main.list1.add(Main.my.get(0));

				Main.s = Main.list1;
				
				
				
				
				}
			
		});
	}
	
	
	public void ok(){
		
		ok.setOnMouseClicked(e->
		{
			Width=Integer.parseInt(textOfWidth.getText());
			Height=Integer.parseInt(textOfHeight.getText());
			window.close();
			
			Main.pane.getChildren().clear();
			Main.map=new Map(Width,Height);
			Main.my = Main.map.getMaze();
			
			Main.sp=Main.pane.getPrefWidth() / ((double) Main.map.getHeight() + 1);
			Main.hander = new Hander(Main.my, Main.map);
		    Main.pane = (MapPane) MapPaths.PaintMap(Main.pane, Main.my, Main.map);
		    Main.list1.add(Main.my.get(0));

			Main.s = Main.list1;
		    
		});
	}
	@FXML
	private Button Default;
	public void Default()
	{
		Default.setOnMouseClicked(e->
		{
			Width=10;
			Height=10;
			window.close();
			
			Main.pane.getChildren().clear();
			Main.map=new Map(Width,Height);
			Main.my = Main.map.getMaze();
			
			Main.sp=Main.pane.getPrefWidth() / ((double) Main.map.getHeight() + 1);
			Main.hander = new Hander(Main.my, Main.map);
		    Main.pane = (MapPane) MapPaths.PaintMap(Main.pane, Main.my, Main.map);
		    Main.list1.add(Main.my.get(0));

			Main.s = Main.list1;
			
			
			
			
			
		});
	}
	
	@FXML
	private Button save;
	public void save()
	{

		
		save.setOnMouseClicked(e->
		{
			
			FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			    new FileChooser.ExtensionFilter("TXT", "*.txt")
			);
		fileChooser.setInitialFileName("map.txt");
		fileChooser.setTitle("Save Resource File");
		
		fileChooser.setInitialDirectory(filePath1);
		File f=fileChooser.showSaveDialog(Main.stage);
			
				if(f!=null)
				try {
					Main.map.writeMap(Main.my,f);
					filePath1=f.getParentFile();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
		});
	}

	@FXML
	private Button renew;
	public void renew() {
		
		renew.setOnMouseClicked(e->
		{
			
			Map.reNewMap(Main.my);
			circle.setCenterX(Main.my.get(0).getX()* Main.sp + Main.SW + Main.sp / 2.0);
			circle.setCenterY(Main.my.get(0).getY()* Main.sp + Main.SH + Main.sp / 2.0);
			
		});
		
		
	}

	@FXML
	private Button ergodicMap;
	public void ergodicMap()
	{
		Map.reNewMap(Main.my);
		int step=Main.hander.ergodicMap(Main.my);
		 Main.pane=(MapPane) MapPaths.PaintPathNum(Main.pane, Main.my, Main.map);
		ArrayList<MazePoint> listduan=Hander.duanlu();
		 
	}
	@FXML
	private Button duan;
	public void duan()
	{
		duan.setOnMouseClicked(e->
		{
			
			Map.reNewMap(Main.my);
			int step=Main.hander.ergodicMap(Main.my);
			ArrayList<MazePoint> listduan=Hander.duanlu();
			MapPaths.PaintPath(Main.pane, listduan, Main.map);	
			circle.setCenterX(Main.my.get(Main.my.size()-1).getX()* Main.sp + Main.SW + Main.sp / 2.0);
			circle.setCenterY(Main.my.get(Main.my.size()-1).getY()* Main.sp + Main.SH + Main.sp / 2.0);
			
			
		});
	}
		
	
}
