package Logic;

public class WinCombination {
    private String imageDescription;
    private int win;

    public WinCombination(String imageDescription, int win) {
        this.imageDescription = imageDescription;
        this.win = win;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public int getWin() {
        return win;
    }
}