package ru.job4j.generic;

public class UserStore extends MemStore<User> {
    private final Store<User> store = new MemStore<>();
}
