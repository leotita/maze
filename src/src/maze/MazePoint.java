package maze;
import java.io.Serializable;

public class MazePoint implements Serializable {
	private int left = 0;
	private int right = 0;
	private int up = 0;
	private int down = 0;
	private int x;
	private int y;
	private int step=0;
	public boolean visted;
	public boolean access = false;
	public boolean ergodic=false;
	
	

	public boolean isErgodic() {
		return ergodic;
	}

	public void setErgodic(boolean ergodic) {
		this.ergodic = ergodic;
	}

	public MazePoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft() {
		this.left = 1;
	}

	public int getRight() {
		return right;
	}

	public void setRight() {
		this.right = 1;
	}

	public int getUp() {
		return up;
	}

	public void setUp() {
		this.up = 1;
	}

	public int getDown() {
		return down;
	}

	public void setDown() {
		this.down = 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		MazePoint tg=(MazePoint)obj;
		if(this.x==tg.x&&this.y==tg.y)
			return true;
		return false;
	}
	
	

}