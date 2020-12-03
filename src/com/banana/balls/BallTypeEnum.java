package com.banana.balls;

public enum BallTypeEnum {
    NORMAL_BALL(""),
    FOUR("4"),
    SIX("6"),
    NO_BALL("NO"),
    WIDE_BALL("WD"),
    WICKET("W");

    public String getLabel() {
        return label;
    }

    private String label;

    BallTypeEnum(String s) {
        label = s;
    }

    public static BallTypeEnum getBallTypeFromLabel(String s) {
        for (BallTypeEnum bt : BallTypeEnum.values()) {
            if (bt.label.equalsIgnoreCase(s.trim())) {
                return bt;
            }
        }
        try {
            Integer.parseInt(s);
            return BallTypeEnum.NORMAL_BALL;
        } catch (Exception e) {
            throw new IllegalStateException("Unidentified input");
        }
    }
}
