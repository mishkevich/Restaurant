package by.mishkevich.my_restaurant.exeptions;

public class MealNotFoundException extends RuntimeException{

    public MealNotFoundException() {
    }

    public MealNotFoundException(String message) {
        super(message);
    }

    public MealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealNotFoundException(Throwable cause) {
        super(cause);
    }

    public MealNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
