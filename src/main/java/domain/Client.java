package domain;

public class Client extends Entity<Integer> {
    String name;

    public Client(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
