import java.util.UUID;

public class RandomStringGenerator {

    public RandomStringGenerator(){

    }

    public String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}