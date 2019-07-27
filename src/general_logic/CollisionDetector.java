package general_logic;

public class CollisionDetector
{
	static public class Rect
	{
		public double x1, y1, x2, y2;

		public boolean pointInside(double x, double y) {
			return (x >= x1 && x <= x2) && (y >= y1 && y <= y2);
		}

		public void set(double ax, double ay, double w, double h) {
			x1 = ax;
			y1 = ay;
			x2 = x1 + w;
			y2 = y1 + h;
		}
	}

	public static Rect rt = new Rect();

	public static boolean check
			(
					double ax1, double ay1, double w, double h,
					double bx1, double by1, double w2, double h2
			)
	{
		rt.set(ax1, ay1, w, h);

		double bx2 = bx1 + w2;
		double by2 = by1 + h2;

		if(rt.pointInside(bx1, by1) || rt.pointInside(bx2, by1) ||
				rt.pointInside(bx1, by2) || rt.pointInside(bx2, by2))
		{
			// Collision found
			return true;
		}

		// No collisions
		return false;
	}
}