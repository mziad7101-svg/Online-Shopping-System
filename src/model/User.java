package model;

public abstract class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("Enter a Valid ID.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


}
