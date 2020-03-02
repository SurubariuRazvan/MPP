package domain;

public class Destination extends Entity<Integer> {
    String name;

    public Destination(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
