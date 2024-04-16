package IOClasses;

import Interfaces.Instructions;
import Interfaces.SimpleInstructions;
import Interfaces.Trigger;

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
