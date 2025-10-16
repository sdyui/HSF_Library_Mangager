public enum MembershipType {
    ORDINARY(0, 5),
    ADVANCE(20000, 10),
    PREMIUM(100000, Integer.MAX_VALUE);

    private final double fee;
    private final int limit;

    MembershipType(double fee, int limit) {
        this.fee = fee;
        this.limit = limit;
    }

    public double getFee() { return fee; }
    public int getLimit() { return limit; }
}