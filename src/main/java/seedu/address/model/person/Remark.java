package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remark should be valid!";
    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remake.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    public static boolean isValidRemark(String remark) {
        return true;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
