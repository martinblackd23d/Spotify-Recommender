package edu.metrostate;

import java.util.LinkedList;
import java.util.List;

public class ValueStore {

    private final List<ValueChangedListener> listeners = new LinkedList<>();

    private int value;

    public ValueStore() {
        this.value = 0;
    }

    public void deregisterValueChangeListener(ValueChangedListener listener) {
        this.listeners.remove(listener);
    }

    public void registerValueChangeListener(ValueChangedListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
            listener.onValueChange(this.value);
        }
    }

    public void decrement() {
        this.value -= 1;
        notifyListeners();
    }

    public void increment() {
        this.value += 1;
        notifyListeners();
    }

    private void notifyListeners() {
        for (ValueChangedListener listener : listeners) {
            listener.onValueChange(this.value);
        }
    }
}
