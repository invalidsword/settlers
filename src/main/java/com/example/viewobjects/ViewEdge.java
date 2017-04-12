package com.example.viewobjects;

/**
 * Created by tooji on 3/10/2017.
 */
public class ViewEdge {

    private double x;
    private double y;
    private String stroke;
    private String stroke_width;
    private String points;
    private String id;

    public ViewEdge(double x, double y, String stroke, String stroke_width, String points, String id) {
        this.x = x;
        this.y = y;
        this.stroke = stroke;
        this.stroke_width = stroke_width;
        this.points = points;
        this.id = id;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStroke_width() {
        return stroke_width;
    }

    public void setStroke_width(String stroke_width) {
        this.stroke_width = stroke_width;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
