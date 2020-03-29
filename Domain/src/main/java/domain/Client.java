package domain;

public class Client extends Entity<Integer> {
    String name;

    public Client(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ",name='" + name +
                '}';
    }
}
