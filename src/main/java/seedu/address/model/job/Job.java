package seedu.address.model.job;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.common.Name;
import seedu.address.model.tag.Tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Job Listing in the address book.
 */
public class Job {
    private final Name name;
    private final JobCompany company;
    private final JobSalary salary;
    private final JobDescription description;
    private final Set<Tag> requirements = new HashSet<>();
    // Todo: I named it more verbosely to be clearer since matches and match was confusing me.
    //  The main thing that I wanted to highlight was standardisation: keep Person or change to Contact.
    //  Aside, to identify a contact, I chose to utilise phone number, this guarantees uniqueness.
    //  Subsequent modification to utilise name + phone, this is for UI stuff to set text and standardise JSON.
    private final Optional<List<String>> matchedContactIdentifier;

    /**
     * Creates a new job, utilised in the context of addJobCommand.
     */
    public Job(Name name, JobCompany company, JobSalary salary,
               JobDescription description, Set<Tag> requirements) {
        requireAllNonNull(name, company, salary, description, requirements);
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.description = description;
        this.requirements.addAll(requirements);
        this.matchedContactIdentifier = Optional.empty();
    }

    /**
     * Creates a new job, utilised in the context of loading from {@code Storage}.
     */
    public Job(Name name, JobCompany company, JobSalary salary,
            JobDescription description, Set<Tag> requirements, Optional<List<String>> matches) {
        requireAllNonNull(name, company, salary, description, requirements, matches);
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.description = description;
        this.requirements.addAll(requirements);
        this.matchedContactIdentifier = matches;
    }

    public Name getName() {
        return name;
    }

    public JobCompany getCompany() {
        return company;
    }

    public JobSalary getSalary() {
        return salary;
    }

    public JobDescription getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getRequirements() {
        return Collections.unmodifiableSet(requirements);
    }

    /**
     * Returns the identifier of the corresponding matched {@code Person}.
     */
    public Optional<List<String>> getMatchedIdentifier() {
        return this.matchedContactIdentifier;
    }

    /**
     * Returns any existing associations for conversion to JSON for Jackson use and for display in the UI.
     */
    public List<String> getMatch() {
        return matchedContactIdentifier.orElse(null);
    }

    /**
     * Checks if the Job object has matched with a {@code Person}.
     * Utilised in assertations to ensure bidirectional associations.
     *
     * @param contactIdentifier List containing the name and number of the {@code Person} to be matched with.
     * @return True if this job has been matched and the identity of the matched {@code Person} is the same.
     */
    public boolean hasMatched(List<String> contactIdentifier) {
        return matchedContactIdentifier.map(s -> s.equals(contactIdentifier)).orElse(false);
    }

    /**
     * Returns a list of two strings, company name and job name, that identify the Job object.
     */
    public List<String> getIdentifier() {
        return List.of(company.toString(), name.fullName);
    }

    /**
     * Returns true if both jobs have the same name and company.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null && otherJob.getName().equals(getName()) && otherJob.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both jobs have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return name.equals(otherJob.name)
                && company.equals(otherJob.company)
                && salary.equals(otherJob.salary)
                && requirements.equals(otherJob.requirements)
                && description.equals(otherJob.description)
                && matchedContactIdentifier.equals(otherJob.matchedContactIdentifier);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name)
                                        .add("company", company)
                                        .add("salary", salary)
                                        .add("description", description)
                                        .add("requirements", requirements)
                                        .toString();
    }
}
