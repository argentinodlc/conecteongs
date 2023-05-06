package com.conecteongs.conecteongs.utils.enums;

public enum Recurrence {
    WEEKLY(7),
    MONTHLY(30),
    BIMONTHLY(60),
    QUARTERLY(90),
    SEMIANNUAL(180),
    ANNUAL(365);
    
    private int dias;
    
    private Recurrence(int dias) {
        this.dias = dias;
    }
    
    public int getDias() {
        return dias;
    }
}
