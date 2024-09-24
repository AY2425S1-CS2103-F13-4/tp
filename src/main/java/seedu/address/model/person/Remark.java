package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remark should be valid!";
    public final String value;

    public Remark(String remark) {
        value = remark;
    }

    public static boolean isValidRemark(String remark) {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }
}
