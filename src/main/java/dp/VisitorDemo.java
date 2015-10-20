package dp;

import java.lang.Math;

/** Demostrate visitor design pattern to create area of objects **/

// Abstract Receiver,
abstract class Shape {
  abstract <T> T accept(ShapeVisitor<T> visitor);
}

// Visitor
interface ShapeVisitor<T> {
  T visitSquare(ShapeSquare shape);
  T visitRectangle(ShapeRectangle shape);
}

// Concrete receiver
class ShapeSquare extends Shape {
  int length;
  public ShapeSquare(int length) {
    this.length = length;
  }

  public <T> T accept(ShapeVisitor<T> visitor) {
    return visitor.visitSquare(this);
  }
}

abstract class ShapeRectangle extends Shape {

}

public class VisitorDemo {

  public static void main(String[] s) {

    ShapeSquare square = new ShapeSquare(5);

    // Implementation can be described outside.
    Integer area = square.accept(new ShapeVisitor<Integer>() {

      public Integer visitSquare(ShapeSquare square) {
        return (int) Math.pow((double)square.length, (double)2);
      }

      public Integer visitRectangle(ShapeRectangle rectangle) {
        return -1;
      }

    });

    System.out.println("AREA = " + area);
  }

}