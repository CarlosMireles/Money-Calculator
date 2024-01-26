package software.ulpgc.moneyCalculator.model;

public record Currency(String code, String name) {
    @Override
    public String code() {
        return code;
    }
    @Override
    public String toString() {
        return code + '-' + name;
    }
}
