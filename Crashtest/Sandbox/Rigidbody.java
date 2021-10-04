public class Rigidbody extends Shape{
    
    private double time = 1.0;

    public Rigidbody(){
        super();
    }

    public double getTime(){
        return time;
    }

    public void setTime(double value){
        time = value;
    }

    public double getGravity(){
        return time + (9.8 * Math.pow(time, 2) / 2) * Math.pow(10, -1);
    }

    public double getImpulse(double vector, double mass) {
        return (vector * getGravity() * (mass * 2) * Math.pow(10, -3)) / time;
    }
}
