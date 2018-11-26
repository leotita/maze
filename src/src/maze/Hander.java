package maze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Hander {
	private Random rnd = new Random();

	private ArrayList<MazePoint> point = null;
	private Map map = null;

	public static boolean isexist(MazePoint pt, Stack<MazePoint> stack1) {
		boolean is = false;
		int x = stack1.size();
		for (int i = 0; i < x; i++) {
			if (pt.equals(stack1.get(i)))
				is = true;
		}

		return is;
	}

	public Hander(ArrayList<MazePoint> list, Map map)

	{
		this.point = list;
		this.map = map;
	}

	public Stack<MazePoint> find() {

		Stack<MazePoint> stack = new Stack<>();

		boolean flage = true;
		MazePoint pt = point.get(0);

		while (flage)// 顺时针找路，right->down->left->up
		{

			if ((pt.getX() == map.getWidth() - 1) && (pt.getY() == map.getHeight() - 1)) {
				stack.push(pt);
				break;
			}
			if (pt.getRight() == 1 && point.get(pt.getX() + pt.getY() * map.getWidth() + 1).access == false) {
				stack.push(pt);
				pt.access = true;
				System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
				pt = point.get(pt.getX() + pt.getY() * map.getWidth() + 1);
				continue;
			} else if (pt.getDown() == 1
					&& point.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth()).access == false) {
				stack.push(pt);
				pt.access = true;
				System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
				pt = point.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth());
				continue;
			} else if (pt.getLeft() == 1 && point.get(pt.getX() + pt.getY() * map.getWidth() - 1).access == false) {
				stack.push(pt);
				pt.access = true;
				System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
				pt = point.get(pt.getX() + pt.getY() * map.getWidth() - 1);

				continue;
			} else if (pt.getUp() == 1
					&& point.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth()).access == false) {
				stack.push(pt);

				pt.access = true;
				System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
				pt = point.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth());

				continue;
			} else {
				if (!isexist(pt, stack)) {
					pt.access = true;
					stack.push(pt);

					System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
				}
				pt = stack.pop();
				pt = stack.peek();
				if (pt.access == true)
					pt.access = false;
			}

		}

		return stack;

	}

	//单步寻路 
	public ArrayList<MazePoint> randFindonce(Stack<MazePoint> stack, ArrayList<MazePoint> list, Pane pane) {

		//终点
		MazePoint pt = point.get(list.get(list.size() - 1).getX() + list.get(list.size() - 1).getY() * map.getWidth());
		
		//左下右上 出栈
		int[] val = new int[] { -1, -1, -1, -1, -1 };
		int times = 0;
		while (times < 5) {
			int dir = rnd.nextInt(4);
			if (val[0] != -1 && val[1] != -1 && val[2] != -1 && val[3] != -1) {
				val[4] = 1;
				dir = 4;
			}

			if (val[dir] == dir && val[4] != 1)
				continue;
			else
				val[dir] = dir;
			if ((pt.getX() == map.getWidth() - 1) && (pt.getY() == map.getHeight() - 1)) {
				stack.push(pt);
				break;

			}
			switch (dir) {

			case 0:
				if (pt.getRight() == 1 && point.get(pt.getX() + pt.getY() * map.getWidth() + 1).access == false) {
					if (!isexist(pt, stack))
						stack.push(pt);

					pt.access = true;

					pt = point.get(pt.getX() + pt.getY() * map.getWidth() + 1);
					System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
					list.add(pt);
					ArrayList<MazePoint> list_tg = new ArrayList<>();
					list_tg.add(list.get(list.size() - 2));
					list_tg.add(list.get(list.size() - 1));
					pane = MapPaths.PaintPath(pane, list_tg, map);
					System.out.println("画了路");
					return list;

				}
				break;

			case 1:
				if (pt.getDown() == 1
						&& point.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth()).access == false) {
					if (!isexist(pt, stack))
						stack.push(pt);

					pt.access = true;

					pt = point.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth());
					System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
					list.add(pt);
					ArrayList<MazePoint> list_tg = new ArrayList<>();
					list_tg.add(list.get(list.size() - 2));
					list_tg.add(list.get(list.size() - 1));

					pane = MapPaths.PaintPath(pane, list_tg, map);
					System.out.println("画了路");
					return list;

				}
				break;

			case 2:
				if (pt.getLeft() == 1 && point.get(pt.getX() + pt.getY() * map.getWidth() - 1).access == false) {
					if (!isexist(pt, stack))
						stack.push(pt);

					pt.access = true;

					pt = point.get(pt.getX() + pt.getY() * map.getWidth() - 1);
					System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
					list.add(pt);
					ArrayList<MazePoint> list_tg = new ArrayList<>();
					list_tg.add(list.get(list.size() - 2));
					list_tg.add(list.get(list.size() - 1));

					pane = MapPaths.PaintPath(pane, list_tg, map);
					System.out.println("画了路");
					return list;

				}
				break;

			case 3:
				if (pt.getUp() == 1
						&& point.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth()).access == false) {
					if (!isexist(pt, stack))
						stack.push(pt);

					pt.access = true;

					pt = point.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth());
					System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
					list.add(pt);
					ArrayList<MazePoint> list_tg = new ArrayList<>();
					list_tg.add(list.get(list.size() - 2));
					list_tg.add(list.get(list.size() - 1));
					pane = MapPaths.PaintPath(pane, list_tg, map);
					System.out.println("画了路");

					return list;

				}
				break;

			case 4: {
				if (!isexist(pt, stack)) {
					pt.access = true;
					stack.push(pt);
					System.out.println(".....");

				}
				ArrayList<MazePoint> list_tg = new ArrayList<>();
				pt = stack.pop();

				list_tg.add(pt);
				pt = stack.peek();
				list.add(pt);
				System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
				list_tg.add(pt);
				pane = MapPaths.PaintPath(pane, list_tg, map, Color.rgb(242, 242, 242));
				System.out.println("清除");
				// if (pt.access == truehgn)
				// pt.access =false;
			}
				break;
			}

			times++;

		}
		int x = stack.size();
		for (int i = 0; i < x; i++)
			System.out.println(stack.get(i).getX() + "," + stack.get(i).getY());

		return list;

	}

	//寻路
	public ArrayList<MazePoint> randfind() {
		Stack<MazePoint> stack = new Stack<>();

		boolean flage = true;
		MazePoint pt = point.get(0);
		int top = 0;
		while (top >= 0) {

			int[] val = new int[] { -1, -1, -1, -1, -1 };
			int times = 0;
			flag: while (times < 5)//随机方向
			{
				int dir = rnd.nextInt(4);
				if (val[0] != -1 && val[1] != -1 && val[2] != -1 && val[3] != -1) {
					val[4] = 1;
					dir = 4;
				}

				if (val[dir] == dir && val[4] != 1)
					continue;
				else
					val[dir] = dir;
				if ((pt.getX() == map.getWidth() - 1) && (pt.getY() == map.getHeight() - 1)) {
					stack.push(pt);
					top = -1;
					break;

				}
				switch (dir) {

				case 0:
					if (pt.getRight() == 1 && point.get(pt.getX() + pt.getY() * map.getWidth() + 1).access == false) {
						if (!isexist(pt, stack))
						stack.push(pt);
						pt.access = true;
						System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
						pt = point.get(pt.getX() + pt.getY() * map.getWidth() + 1);
						break flag;

					}
					break;

				case 1:
					if (pt.getDown() == 1
							&& point.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth()).access == false) {
						if (!isexist(pt, stack))
						stack.push(pt);
						pt.access = true;
						System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
						pt = point.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth());
						break flag;

					}
					break;

				case 2:
					if (pt.getLeft() == 1 && point.get(pt.getX() + pt.getY() * map.getWidth() - 1).access == false) {
						if (!isexist(pt, stack))
						stack.push(pt);
						pt.access = true;
						System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
						pt = point.get(pt.getX() + pt.getY() * map.getWidth() - 1);
						break flag;

					}
					break;

				case 3:
					if (pt.getUp() == 1
							&& point.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth()).access == false) {
						if (!isexist(pt, stack))
						stack.push(pt);

						pt.access = true;
						System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
						pt = point.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth());
						break flag;

					}
					break;

				case 4: {
					if (!isexist(pt, stack)) {
						pt.access = true;
						stack.push(pt);

						System.out.println("现在的坐标：" + pt.getX() + " ," + pt.getY());
					}
					pt = stack.pop();
					pt = stack.peek();
				}
					break;
				}

				times++;

			}
		}
		int index=stack.size();
		ArrayList<MazePoint> list=new ArrayList<>();
		
		for(int i=0;i<index;i++)
			list.add(stack.get(i));

		return list;

	}

	
	//便利迷宫
	public int ergodicMap(ArrayList<MazePoint> list)
	{
		LinkedList<MazePoint> queue=new LinkedList<>();
		MazePoint pt;
		MazePoint ptC;
		list.get(0).ergodic=true;
		
		queue.add(list.get(0));
		
		while(queue.size()!=0)
		{
			pt=queue.remove();
		
			if ((pt.getX() == map.getWidth() - 1) && (pt.getY() == map.getHeight() - 1)) {
				
				continue;
			}
			
			if (pt.getRight() == 1 && list.get(pt.getX() + pt.getY() * map.getWidth() + 1).ergodic == false) {
				ptC=list.get(pt.getX() + pt.getY() * map.getWidth() + 1);
			    ptC.setStep(list.get(ptC.getX() + ptC.getY() * map.getWidth() -1).getStep()+1);
				ptC.ergodic = true;
				
				queue.add(ptC);
				
			}
			if (pt.getDown() == 1&& list.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth()).ergodic == false) {
				ptC=list.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth());
				ptC.setStep(list.get(ptC.getX() + ptC.getY() * map.getWidth() - map.getWidth()).getStep()+1);
				ptC.ergodic = true;
				
				 queue.add(ptC);
				
			} 
			if (pt.getLeft() == 1 && list.get(pt.getX() + pt.getY() * map.getWidth() - 1).ergodic == false) {
				ptC=list.get(pt.getX() + pt.getY() * map.getWidth() - 1);
				ptC.setStep(list.get(ptC.getX() + ptC.getY() * map.getWidth() +1).getStep()+1);
				ptC.ergodic =true;
				
				queue.add(ptC);
				
			}
			if (pt.getUp() == 1
					&& list.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth()).ergodic == false) {
				ptC=list.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth());
				ptC.setStep(list.get(ptC.getX() + ptC.getY() * map.getWidth() + map.getWidth()).getStep()+1);
				ptC.ergodic =true;
				
				queue.add(ptC);

			}
		
		}
		return 0;
		
		
		
		
		
		
	}

	//最短路径
	
	
	public static  ArrayList<MazePoint> duanlu()
	{
		
		ArrayList<MazePoint> list=new ArrayList<>();
		ArrayList<MazePoint> tg=Main.my;
		MazePoint pt=null;
		Map map=Main.map;
		pt=tg.get(tg.size()-1);
		list.add(pt);
		while(!(pt.getX()==0&&pt.getY()==0))
		{
			if(pt.getRight()==1&&tg.get(pt.getX() + pt.getY() * map.getWidth() + 1).getStep()==pt.getStep()-1)
			{
				list.add(tg.get(pt.getX() + pt.getY() * map.getWidth() + 1));
				pt=tg.get(pt.getX() + pt.getY() * map.getWidth() + 1);
				continue;
			}
			if(pt.getDown()==1&&tg.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth()).getStep()==pt.getStep()-1)
			
			{
				list.add(tg.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth()));
				pt=tg.get(pt.getX() + pt.getY() * map.getWidth() + map.getWidth());
				continue;
			}
			if(pt.getLeft() == 1 && tg.get(pt.getX() + pt.getY() * map.getWidth() - 1).getStep() ==pt.getStep()-1)
			{
				list.add(tg.get(pt.getX() + pt.getY() * map.getWidth() - 1));
				pt=tg.get(pt.getX() + pt.getY() * map.getWidth() - 1);
				continue;
			}
			if (pt.getUp() == 1
					&& tg.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth()).getStep() == pt.getStep()-1)
			{
				list.add(tg.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth()));
				pt=tg.get(pt.getX() + pt.getY() * map.getWidth() - map.getWidth());
				continue;
			}
			
			
			
		}
		list.add(tg.get(0));
		
		return list;
		
		
		
	}
	
	
	public static ArrayList<MazePoint> ergodicMapr(ArrayList<MazePoint> list)
	{
		
		return list;
		
	}

}



