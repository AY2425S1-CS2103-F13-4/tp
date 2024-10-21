package seedu.address.model.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.common.Name;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Represents a Job Listing in the address book.
 */
public class Job {
    private final Name name;
    private final JobCompany company;
    private final JobSalary salary;
    private final JobDescription description;
    private final Set<Tag> requirements = new HashSet<>();
    private final UniquePersonList matches = new UniquePersonList();

    /**
     * TODO: Implement data validation
     * TODO: What is meant by this ah ^^?
     * Every field has no validation for now.
     */
    public Job(Name name, JobCompany company, JobSalary salary,
            JobDescription description, Set<Tag> requirements) {
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.description = description;
        this.requirements.addAll(requirements);
    }

    public Job(Name name, JobCompany company, JobSalary salary, JobDescription description, Set<Tag> requirements,
            List<Person> matches) {
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.description = description;
        this.requirements.addAll(requirements);
        for (Person match : matches) {
            this.matches.add(match);
        }
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

    public FilteredList<Person> getMatches() {
        return new FilteredList<>(matches.asUnmodifiableObservableList());
    }

    /** Returns true if both jobs have the same name and company. */
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
        return name.equals(otherJob.name) && company.equals(otherJob.company) && salary.equals(otherJob.salary)
               && requirements.equals(otherJob.requirements) && description.equals(otherJob.description);
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
