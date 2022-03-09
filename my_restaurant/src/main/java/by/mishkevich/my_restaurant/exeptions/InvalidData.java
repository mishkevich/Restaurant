package by.mishkevich.my_restaurant.exeptions;

public class InvalidData extends RuntimeException {

    public InvalidData(String message) {
        super(message);
    }
}
