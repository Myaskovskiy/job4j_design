package ru.job4j.generic;

public class RoleStore extends MemStore<Role> {
    private final Store<Role> store = new MemStore<>();
}
