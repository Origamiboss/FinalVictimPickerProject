package src.IOClasses;

import src.Interfaces.Instructions;
import src.Interfaces.SimpleInstructions;
import src.Interfaces.Trigger;

public class SimpleInstrHolder implements Trigger {
    private SimpleInstructions instructions;

    public SimpleInstrHolder(SimpleInstructions instructions) {
        this.instructions = instructions;
    }

    @Override
    public void execute() {
        if (instructions != null) {
            instructions.execute();
        }
    }
}
