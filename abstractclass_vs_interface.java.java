public //Abstract

abstract class Shape{
    abstract double area();
    
    void display()
    {
        //shjdhhdj
    }
}

abstract Circle extends Shape
{
    int radius =5;

    @Override
    double area(){
        return 3.14*radius*radius;
    }
}

//Interface

interface Drawable{
    void draw();
}

class Rectangle implements Drawable
{
    @Override
    public void draw()
    {
        //dbkbfkbkbdkbksbd
    }
}

 {
    
}
