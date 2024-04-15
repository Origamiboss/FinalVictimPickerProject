package src.IOClasses;

import src.Interfaces.Instructions;

public class CompInstrHolder<T> {
    private final T component;
    private final Instructions<T> instructions;

    public CompInstrHolder(T component, Instructions<T> instructions){
        this.component = component;
        this.instructions = instructions;
    }

    public void update(){
        instructions.update(component);
    }

}
