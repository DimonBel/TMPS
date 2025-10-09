package org.example;

interface IVersionControl {
    void commit(String message);
    void push();
    void pull();
}
