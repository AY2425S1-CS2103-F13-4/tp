package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.common.Address;
import seedu.address.model.common.Name;
import seedu.address.model.company.BillingDate;
import seedu.address.model.company.Company;
import seedu.address.model.person.Phone;

/**
 * Represents a Job's company in the address book.
 */
public class JobCompany {
    public static final String MESSAGE_CONSTRAINTS = "The company provided does not exist";

    // TODO: Change type to Company during integration
    public final Company value;

    /**
     * Constructs a {@code JobCompany}.
     *
     * @param companyName An existing company's name.
     */
    public JobCompany(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompany(companyName), MESSAGE_CONSTRAINTS);
        value = new Company(new Name(companyName), new Address("Help"), new BillingDate("1"), new Phone("911"));
    }


    public JobCompany(String companyName, AddressBook ab) {
        requireNonNull(companyName);
        checkArgument(isValidCompany(companyName, ab), MESSAGE_CONSTRAINTS);
        Company existedCompany = null;
        for(Company company: ab.getCompanyList()) {
            if(company.getName().equals(new Name(companyName))) {
                existedCompany = company;
                break;
            }
        }
        this.value = existedCompany;

        // The Company with companyName should exist and be assigned to value
        assert this.value != null;
    }

    /**
     * Returns true if a given company exists.
     */
    public static boolean isValidCompany(String companyName, AddressBook ab) {
        Company company = new Company(new Name(companyName), new Address("Help"), new BillingDate("1"), new Phone(
                "911"));
        return ab.hasCompany(company);
    }

    public static boolean isValidCompany(String companyName) {
        // TODO: check if the company exists
         return true;
    }


    @Override
    public int hashCode() {
        return value.hashCode();
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
        return value.equals(otherCompany.value);
    }

    @Override
    public String toString() {
        return value.getName().fullName;
    }
}
