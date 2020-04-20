package calculator;

public class Operations {
    String name;
    String shortName;

    public Operations(String name, String shortName){
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return name;
    }
}
