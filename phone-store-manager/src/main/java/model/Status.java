package model;

public class Status {
    private int id;
    private String type;

    public Status() {
    }

    public Status(String type) {
        this.type = type;
    }

    public Status(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}