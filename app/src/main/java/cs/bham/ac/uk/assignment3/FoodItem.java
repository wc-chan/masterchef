package cs.bham.ac.uk.assignment3;

public class FoodItem {

    //Class variables
    private String food_name;
    private Integer food_id;
    private int time;


    //constructor
    public FoodItem(String string_param, Integer integer_param, int time_param)
    {
        food_name = string_param;
        food_id = integer_param;
        time = time_param;
    }

    //Getter and setter methods for the private variables
    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public Integer getFood_id() {
        return food_id;
    }

    public void setFood_id(Integer food_id)
    {
        this.food_id = food_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String toString()
    {
        return food_name;
    }
}
