package net.deechael.visual.test.geometry.position;

import net.deechael.visual.api.curve.CubicBezierCurve;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.curve.CubicBezierCurveImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CurveTest {

    public static void main(String[] args) {

        CubicBezierCurve curve = new CubicBezierCurveImpl(.22,.83,.92,.4);

        List<Point2d> points = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            double t = ((double) i) / 100.0;
            points.add(curve.getPoint(t));
        }

        JFrame frame = new JFrame("Three-dimensional Bezier Curve");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BezierCurvePanel panel = new BezierCurvePanel(points);
        frame.add(panel);
        frame.setSize(800, 600);

        frame.pack();
        frame.setVisible(true);
    }

}

class BezierCurvePanel extends JPanel {

    private final List<Point2d> points;

    public BezierCurvePanel(List<Point2d> points) {
        this.points = points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 清空画布

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // 设置画笔样式、颜色等属性
        BasicStroke stroke = new BasicStroke(2f);
        Color color = Color.RED;
        g2d.setColor(color);
        g2d.setStroke(stroke);

        // 计算控制点位置
        double baseX = width * 0.4;
        double baseY = height * 0.8;

        for (Point2d point : this.points) {
            Point2D p = new Point2D.Double(baseX + (point.x() * 100), baseY - (point.y() * 100));
            g2d.draw(new Line2D.Double(p, p));
        }

        // 更新UI
        repaint();
    }

}
