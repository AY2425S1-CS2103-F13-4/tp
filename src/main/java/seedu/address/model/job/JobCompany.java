package seedu.address.model.job;

import seedu.address.model.common.Name;

/**
 * Represents a Job's company in the address book.
 */
public class JobCompany extends Name {
    public static final String MESSAGE_CONSTRAINTS = "The company provided does not exist";

    /**
     * Constructs a {@code JobCompany}.
     *
     * @param companyName An existing company's name.
     */
    public JobCompany(String companyName) {
        super(companyName);
    }

    /**
     * Returns true if a given company exists.
     */
    public static boolean isValidCompany(String companyName) {
        return Name.isValidName(companyName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JobCompany)) {
            return false;
        }

        JobCompany otherCompany = (JobCompany) other;
        return fullName.equals(otherCompany.fullName);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
