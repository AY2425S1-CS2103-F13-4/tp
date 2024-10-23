package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.common.Name;
import seedu.address.model.skill.Skill;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Role role;
    private final Set<Skill> skills = new HashSet<>();
    // List<String> is to prevent a possible bug where companyName happens to be a jobName, so we enforce an order.
    private final Optional<List<String>> matchedJobIdentifier;

    /**
     * Every parameter must be present and not null.
     * Creates a new person, utilised in the context of addContactCommand.
     */
    public Person(Name name, Phone phone, Email email, Role role, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, role, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.skills.addAll(skills);
        this.matchedJobIdentifier = Optional.empty();
    }

    /**
     * Creates a new person, utilised in the context of loading from {@code Storage}.
     */
    public Person(Name name, Phone phone, Email email, Role role,
                  Set<Skill> skills, Optional<List<String>> matchedJobIdentifier) {
        requireAllNonNull(name, phone, email, role, skills, matchedJobIdentifier);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.skills.addAll(skills);
        this.matchedJobIdentifier = matchedJobIdentifier;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns any existing associations for conversion to JSON for Jackson use and for display in the UI.
     */
    public List<String> getMatchedIdentifier() {
        return matchedJobIdentifier.orElse(null);
    }

    /**
     * Returns true if this person has any job matches, returns false otherwise.
     */
    public boolean isMatchPresent() {
        return matchedJobIdentifier.isPresent();
    }

    /**
     * Checks if this person has matched with the specified job.
     *
     * @param jobIdentifier A list containing two strings, company and job name, that uniquely identify a job.
     */
    public boolean hasMatched(List<String> jobIdentifier) {
        return matchedJobIdentifier.map(s -> s.equals(jobIdentifier)).orElse(false);
    }

    /**
     * Returns a string that identify the Person object.
     */
    public List<String> getPersonalIdentifier() {
        return List.of(name.toString(), phone.toString());
    }

    // Todo: I commented out this whole chunk, slated for deletion. This is because people can have the same name
    //  and I believe that we agreed to distinguish people by the email or handphone number.
    //  verify this in the PR review, especially since we will have to modify the JavaDocs of
    //  modelmanager, addressbook, and uniquepersonList.
    //    /**
    //     * Returns true if both persons have the same name.
    //     * This defines a weaker notion of equality between two persons.
    //     */
    //    public boolean isSamePerson(Person otherPerson) {
    //        if (otherPerson == this) {
    //            return true;
    //        }
    //
    //        return otherPerson != null
    //                && otherPerson.getName().equals(getName());
    //    }

    /**
     * Returns true if both persons have the same number or email address.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getEmail().equals(getEmail()) || otherPerson.getPhone().equals(getPhone()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && role.equals(otherPerson.role)
                && skills.equals(otherPerson.skills)
                && matchedJobIdentifier.equals(otherPerson.matchedJobIdentifier);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, role, skills, matchedJobIdentifier);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("role", role)
                .add("skills", skills)
                .toString();
    }

}
