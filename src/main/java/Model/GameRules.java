package Model;

public class GameRules {
    private double twoAlike;
    private double threeAlike;
    private String noneSame;

    public GameRules(double twoAlike, double threeAlike, String noneSame) {
        this.twoAlike = twoAlike;
        this.threeAlike = threeAlike;
        this.noneSame = noneSame;
    }

    public double getTwoAlike() {
        return twoAlike;
    }

    public void setTwoAlike(double twoAlike) {
        this.twoAlike = twoAlike;
    }

    public double getThreeAlike() {
        return threeAlike;
    }

    public void setThreeAlike(double threeAlike) {
        this.threeAlike = threeAlike;
    }

    public String getNoneSame() {
        return noneSame;
    }

    public void setNoneSame(String noneSame) {
        this.noneSame = noneSame;
    }
}
