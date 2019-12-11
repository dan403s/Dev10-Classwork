/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.shapesandperimeters;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {
        // instantiate new App object
        App appInstance = new App();
        // call getSquare method
        appInstance.getSquare();
        // call getRectangle method
        appInstance.getRectangle();
        // call getTriangle method
        appInstance.getTriangle();
        // call getCircle method
        appInstance.getCircle();
    }

    public void getSquare() {
        // begin square
        System.out.println("THIS IS THE SQUARE----------------");
        // instantiate new Square object extending Shape Class
        Square squareInstance = new Square(100, "Orangish Red");
        // test new square
        System.out.printf("The new square is %s. The sidelength is %.2f. The area is %.2f. The perimeter is %.2f. %n%n",
                squareInstance.getColor(), squareInstance.getSideLength(), squareInstance.getArea(), squareInstance.getPerimeter()
        );
        // change color and sidelength, which will change area and perimeter when called
        System.out.println("CHANGING COLOR AND SIDE LENGTH ON THIS SQUARE...NOW");
        squareInstance.setColor("Greenish Blue");
        squareInstance.setSideLength(50);
        // test same square with new color and sidelength
        System.out.printf("The same square is now %s. The sidelength is now %.2f. The area is now %.2f. The perimeter is now %.2f. %n%n",
                squareInstance.getColor(), squareInstance.getSideLength(), squareInstance.getArea(), squareInstance.getPerimeter()
        );
    }

    public void getRectangle() {
        // begin rectangle
        System.out.println("THIS IS THE RECTANGLE----------");
        // instantiate new Rectangle object extending Shape Class
        Rectangle rectangleInstance = new Rectangle(500, 100, "Brown");
        // test new rectangle
        System.out.printf("The new rectangle is %s. The sidelength is %.2f. The sidewidth is %.2f. The area is %.2f. The perimeter is %.2f. %n%n",
                rectangleInstance.getColor(), rectangleInstance.getSideLength(), rectangleInstance.getSideWidth(), rectangleInstance.getArea(), rectangleInstance.getPerimeter()
        );
        // change color, sideLength, and sideWidth, which will change area and perimeter when called
        System.out.println("CHANGING COLOR AND SIDE LENGTH AND SIDE WIDTH ON THIS RECTANGLE...NOW");
        rectangleInstance.setColor("Violet");
        rectangleInstance.setSideLength(250);
        rectangleInstance.setSideWidth(50);
        // test same rectangle with new color and sidelength
        System.out.printf("The same rectangle is now %s. The sidelength is now %.2f. The sidewidth is now %.2f. The area is now %.2f. The perimeter is now %.2f. %n%n",
                rectangleInstance.getColor(), rectangleInstance.getSideLength(), rectangleInstance.getSideWidth(), rectangleInstance.getArea(), rectangleInstance.getPerimeter()
        );
    }
    
    public void getTriangle() {
        // begin triangle
        System.out.println("THIS IS THE TRIANGLE----------");
        // instantiate new Triangle object extending Shape Class
        Triangle triangleInstance = new Triangle(10, 10, 100, 50, "Blue");
        // test new triangle
        System.out.printf("The new triangle is %s. The first side is %.2f. The second side is %.2f. The third side is %.2f. The height is %.2f. The area is %.2f. The perimeter is %.2f. %n%n",
                triangleInstance.getColor(), triangleInstance.getSide1(), triangleInstance.getSide2(), triangleInstance.getSide3(), triangleInstance.getHeight(), triangleInstance.getArea(), triangleInstance.getPerimeter()
        );
        // change color, side 1 length, side 2 length, side 3 length, and height, which will change area and perimeter when called
        System.out.println("CHANGING COLOR AND SIDE 1 LENGTH, SIDE 2 LENGTH, SIDE 3 LENGTH AND HEIGHT ON THIS TRIANGLE...NOW");
        triangleInstance.setColor("Red");
        triangleInstance.setSide1(5);
        triangleInstance.setSide2(5);
        triangleInstance.setSide3(50);
        triangleInstance.setHeight(25);
        // test same rectangle with new color and sidelength
        System.out.printf("The same triangle is now %s. The first side is now %.2f. The second side is now %.2f. The third side is now %.2f. The height is now %.2f. The area is now %.2f. The perimeter is now %.2f. %n%n",
                triangleInstance.getColor(), triangleInstance.getSide1(), triangleInstance.getSide2(), triangleInstance.getSide3(), triangleInstance.getHeight(), triangleInstance.getArea(), triangleInstance.getPerimeter()
        );
    }
    
    public void getCircle() {
        // begin circle
        System.out.println("THIS IS THE CIRCLE-------------");
        // instantiate new Circle object extending Shape Class
        Circle circleInstance = new Circle(100, "Denim Color");
        // test new circle
        System.out.printf("The new circle is %s. The radius is %.2f. The area is %.2f. The perimeter is %.2f. %n%n",
                circleInstance.getColor(), circleInstance.getRadius(), circleInstance.getArea(), circleInstance.getPerimeter()
        );
        // change color, and radius, which will change area and perimeter when called
        System.out.println("CHANGING COLOR AND RADIUS ON THIS CIRCLE...NOW");
        circleInstance.setColor("Flannel Color");
        circleInstance.setRadius(50);
        // test same circle with new color and sidelength
        System.out.printf("The same circle is now %s. The radius is now %.2f. The area is now %.2f. The perimeter is now %.2f. %n%n",
                circleInstance.getColor(), circleInstance.getRadius(), circleInstance.getArea(), circleInstance.getPerimeter()
        );
    }

}
