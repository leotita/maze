package maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Map {
	private int width = 0;
	private int height = 0;
	private Random rnd = new Random();
	
	public Map() {
		this.width = 8; // 迷宫宽度
		this.height =8; // 迷宫高度
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	
	
	//自动生成迷宫
	public ArrayList<MazePoint> getMaze() {
		ArrayList<MazePoint> maze = new ArrayList<MazePoint>();
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				MazePoint point = new MazePoint(w, h);
				maze.add(point);
			}
		}
		return CreateMaze(maze);
	}

	private ArrayList<MazePoint> CreateMaze(ArrayList<MazePoint> maze) {
		int top = 0;
		int x = 0;
		int y = 0;
		ArrayList<MazePoint> team = new ArrayList<MazePoint>();
		team.add(maze.get(x + y * width));
		while (top >= 0) {
			int[] val = new int[] { -1, -1, -1, -1 };
			int times = 0;
			boolean flag = false;
			MazePoint pt = (MazePoint) team.get(top);
			x = pt.getX();
			y = pt.getY();
			pt.visted = true;

			ro1: while (times < 4) {
				int dir = rnd.nextInt(4);
				if (val[dir] == dir)
					continue;
				else
					val[dir] = dir;

				switch (dir) {
				case 0: // 左边
					if ((x - 1) >= 0 && maze.get(x - 1 + y * width).visted == false) {
						maze.get(x + y * width).setLeft();
						maze.get(x - 1 + y * width).setRight();
						team.add(maze.get(x - 1 + y * width));
						top++;
						flag = true;
						break ro1;

					}
					break;
				case 1: // 右边
					if ((x + 1) < width && maze.get(x + 1 + y * width).visted == false) {

						maze.get(x + y * width).setRight();
						maze.get(x + 1 + y * width).setLeft();
						team.add(maze.get(x + 1 + y * width));
						top++;
						flag = true;
						break ro1;
					}
					break;
				case 2: // 上边
					if ((y - 1) >= 0 && maze.get(x + (y - 1) * width).visted == false) {
						maze.get(x + y * width).setUp();
						maze.get(x + (y - 1) * width).setDown();
						team.add(maze.get(x + (y - 1) * width));
						top++;
						flag = true;
						break ro1;
					}
					break;
				case 3: // 下边
					if ((y + 1) < height && maze.get(x + (y + 1) * width).visted == false) {
						maze.get(x + y * width).setDown();
						maze.get(x + (y + 1) * width).setUp();
						team.add(maze.get(x + (y + 1) * width));
						top++;
						flag = true;
						break ro1;
					}
					break;
				}
				times += 1;
			}
			if (!flag) {
				team.remove(top);
				top -= 1;
			}

		}
		
		//随机去 墙
//		i<(int)Math.sqrt(this.height*this.width);
		for(int i=0;i<(int)Math.sqrt(this.height*this.width);)
		{
			int rand = rnd.nextInt(maze.size());
			MazePoint tg=maze.get(rand);
			if(tg.visted)
			{
			tg.visted=false;
			
			//left->down->right->up
			if((tg.getX() - 1) >= 0&&tg.getLeft()==0&&maze.get(rand-1).visted==true)
			{
				tg.setLeft();
				maze.get(rand-1).setRight();
				maze.get(rand-1).visted=false;
				i++;
				
			}
			else if((tg.getY() + 1) <this.height &&tg.getDown()==0&&maze.get(rand+this.width).visted==true)
			{
				tg.setDown();
				maze.get(rand+this.width).setUp();
				maze.get(rand+this.width).visted=false;
				i++;
				
			}
			else if((tg.getX() + 1) <this.width &&tg.getRight()==0&&maze.get(rand+1).visted==true)
			{
				tg.setRight();
				maze.get(rand+1).setLeft();
				maze.get(rand+1).visted=false;
				i++;
				
			}
			else if((tg.getY() - 1) >= 0 &&tg.getUp()==0&&maze.get(rand-this.width).visted==true)
			{
				tg.setUp();
				maze.get(rand-this.width).setDown();
				maze.get(rand-this.width).visted=false;
				i++;
				
			}
			
			
			
		}
		
		}

		return maze;
	}
	//导出地图文件
	
	public void writeMap(ArrayList<MazePoint> my,File f)throws Exception
	{
		// 地图点写入文件
		Map.reNewMapPoint(my);
		FileOutputStream output = new FileOutputStream(f);
		ObjectOutputStream obout = new ObjectOutputStream(output);
		for (int i = 0; i < my.size(); i++)
			obout.writeObject(my.get(i));
		obout.close();
	}

	//读取地图文件
	public static ArrayList<MazePoint> getMapByFile(File f) throws Exception {
		//
	

		// 地图点读入内存
		ArrayList<MazePoint> my = new ArrayList<>();
		FileInputStream input = new FileInputStream(f);
		ObjectInputStream obin = new ObjectInputStream(input);
		MazePoint tg;
		while (true) {
			try {
				tg = (MazePoint) obin.readObject();
				my.add(tg);
			} catch (Exception e) {
				break;
			}
		}
		obin.close();
		return my;
	}


	//初始化地图
	public static void reNewMap(ArrayList<MazePoint> list)
	{
		
		Map.reNewMapPoint(list);
		Main.stack=new Stack<>();
		Main.list1=new ArrayList<>();
		
		Main.list1.add(Main.my.get(0));

		Main.s = Main.list1;
		Main.pane.getChildren().clear();
		MapPaths.PaintMap(Main.pane, list, Main.map);
		
	}
		
	
	public static void reNewMapPoint(ArrayList<MazePoint> list)
	{
		MazePoint tg;
		for(int i=0;i<list.size();i++)
		{
			tg=list.get(i);
			tg.setStep(0);
			tg.setErgodic(false);
			tg.access=false;
			
		}
	}


}

