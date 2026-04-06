package model;

public class ShapeFactory {

    public static ShapeModel createShape(String type) {
        switch (type) {
            case "RECTANGLE": return new RectangleShape();
            case "CIRCLE": return new CircleShape();
            case "LINE": return new LineShape();
            default: return null;
        }
    }
}