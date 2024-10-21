package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Address;
import seedu.address.model.common.Name;
import seedu.address.model.company.BillingDate;
import seedu.address.model.company.Company;
import seedu.address.model.job.Job;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of <code>Company</code>.
 */
public class JsonAdaptedCompany {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";
    private final String name;
    private final String address;
    private final String billingDate;
    private final String phone;
    private final List<JsonAdaptedJob> jobs = new ArrayList<>();

    /**
     * Constructs a json adapted company with the company details.
     *
     * @param name Company name.
     * @param address Company address.
     * @param billingDate Company billing date.
     * @param phone Company phone number.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("name") String name,
                              @JsonProperty("address") String address, @JsonProperty("billing date") String billingDate,
            @JsonProperty("phone") String phone, @JsonProperty("jobs") List<JsonAdaptedJob> jobs) {
        this.name = name;
        this.address = address;
        this.billingDate = billingDate;
        this.phone = phone;
        if (jobs != null) {
            this.jobs.addAll(jobs);
        }
    }

    /**
     * Converts a <code>Company</code> into this class for Jackson use.
     * @param source Company to be converted.
     */
    public JsonAdaptedCompany(Company source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        billingDate = source.getBillingDate().date;
        phone = source.getPhone().value;
        jobs.addAll(source.getJobs().stream().map(JsonAdaptedJob::new).collect(Collectors.toList()));
    }

    /**
     * Converts this class back to a <code>Company</code>.
     *
     * @return The <code>Company</code> object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted company.
     */
    public Company toModelType() throws IllegalValueException {
        // instantiating company name
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        // instantiating company address
        if (address == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        // instantiating billing date
        if (billingDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            BillingDate.class.getSimpleName()));
        }
        if (!BillingDate.isValidBillingDate(billingDate)) {
            throw new IllegalValueException(BillingDate.MESSAGE_CONSTRAINTS);
        }
        final BillingDate modelBillingDate = new BillingDate(billingDate);
        // instantiating phone number
        if (phone == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        // TODO: data validation for job
        final List<Job> modelJobs = new ArrayList<>();
        for(JsonAdaptedJob job : jobs) {
            modelJobs.add(job.toModelType());
        }

        return new Company(modelName, modelAddress, modelBillingDate, modelPhone, modelJobs);
    }
}
