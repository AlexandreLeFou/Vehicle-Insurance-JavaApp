public enum Source {

    DATABASE(1),
    CSV(2);

    private int code;

    Source(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
