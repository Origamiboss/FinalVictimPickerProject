package src.Main;

import java.io.FileNotFoundException;

public class Frontend {
    private Holder mainHolder;
    private VicMainUI vicUI;

    public Frontend() throws FileNotFoundException {

        mainHolder = new Holder(); //<- Tech team go here
        VicMainUI mainFront = new VicMainUI(mainHolder); //Useless

        mainHolder = new Holder();
        VicMainUI mainFront = new VicMainUI(mainHolder);

    }

    public Holder getHolder(){
        return mainHolder;
    }

    public VicMainUI getVicUI(){
        return vicUI;
    }

}
