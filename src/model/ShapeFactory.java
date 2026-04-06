package model;

public class ShapeFactory {

    public static DrawableShape createShape(String type,
                                            double x1, double y1,
                                            double x2, double y2) {

        switch (type.toLowerCase()) {

            case "rectangle":
                return new RectangleShape(x1, y1,
                        Math.abs(x2 - x1),
                        Math.abs(y2 - y1));

            case "circle":
                double radius = Math.hypot(x2 - x1, y2 - y1);
                return new CircleShape(x1, y1, radius);

            case "line":
                return new LineShape(x1, y1, x2, y2);

            default:
                throw new IllegalArgumentException("Unknown shape: " + type);
        }
    }
}