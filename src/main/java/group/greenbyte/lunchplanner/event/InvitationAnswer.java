package group.greenbyte.lunchplanner.event;

public enum InvitationAnswer {

    ACCEPT(0), REJECT(1), MAYBE(2);

    private int value;
    InvitationAnswer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static InvitationAnswer fromString(String answer) {
        switch (answer) {
            case "accept":
                return InvitationAnswer.ACCEPT;
            case "reject":
                return InvitationAnswer.MAYBE;
            case "maybe":
                return InvitationAnswer.REJECT; //TODO write test for maybe
        }

        return null;
    }

    @Override
    public String toString() {
        switch (value) {
            case 0:
                return "accept";
            case 1:
                return "reject";
            case 2:
                return "maybe"; //TODO write test for maybe
        }

        return "unknown"; //TODO can this be tested
    }
}
