package lab3.others;

public class Monom {
    private Integer coef;
    private Integer exp;

    @Override
    public String toString() {
        return "Monom{" +
                "coef=" + coef +
                ", exp=" + exp +
                '}';
    }

    public Monom(Integer exp, Integer coef) {
        this.coef = coef;
        this.exp = exp;
    }

    public Integer getCoef() {
        return coef;
    }

    public Integer getExp() {
        return exp;
    }
}
