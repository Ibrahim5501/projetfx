package observer;

public interface ShapeSubject {
    void addObserver(ShapeObserver observer);
    void removeObserver(ShapeObserver observer);
}