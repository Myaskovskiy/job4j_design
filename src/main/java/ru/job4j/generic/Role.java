package ru.job4j.generic;

public class Role extends Base {
    private String nameRole;

    public Role(String id, String nameRole) {
        super(id);
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    @Override
    public String toString() {
        return "Role{" + "nameRole='"
                + nameRole + '\'' + '}';
    }
}
