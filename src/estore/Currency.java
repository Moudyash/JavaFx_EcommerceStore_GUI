package estore;

public class Currency {

    private final int curcode;
    private final String curTybe;

   

    public Currency(int curcode, String curTybe) {
        this.curcode = curcode;
        this.curTybe = curTybe;
    }

    @Override
    public String toString() {
        return String.format("%03d-%s", this.curcode, this.curTybe);
    }

}
