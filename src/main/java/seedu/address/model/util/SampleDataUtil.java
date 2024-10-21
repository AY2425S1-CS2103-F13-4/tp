package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.common.Address;
import seedu.address.model.common.Name;
import seedu.address.model.company.BillingDate;
import seedu.address.model.company.Company;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobCompany;
import seedu.address.model.job.JobDescription;
import seedu.address.model.job.JobSalary;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons(AddressBook sampleAb) {

        List<Job> jobs = new ArrayList<>(sampleAb.getJobList());

        // Need at least two sample jobs
        assert jobs.size() >= 2;

        Job job0 = jobs.get(0);
        Job job1 = jobs.get(1);

        Person person0 = new Person(
                new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Role("Software Engineer"),
                getSkillSet("Python", "C"),
                job0
        );
        Person person1 = new Person(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Role("Copywriter"),
                getSkillSet("wordpress", "MSword"),
                job0
        );
        Person person2 = new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Role("Teacher"),
                getSkillSet("math"),
                job1
        );

        Job newJob0 = createJobWithMatches(job0, List.of(person0, person1));
        Job newJob1 = createJobWithMatches(job1, List.of(person2));
        jobs.set(0, newJob0);
        jobs.set(1, newJob1);
        sampleAb.setJobs(jobs);

        // TODO: discuss solutions, unable to recreate two entity that reference each other
        Company company0 = newJob0.getCompany().value;
        Company company1 = newJob1.getCompany().value;
        // TODO: LoD
        company0.getJobs().setJobs(List.of(newJob0));
        company1.getJobs().setJobs(List.of(newJob1));
        sampleAb.setCompany(company0, company0);
        sampleAb.setCompany(company1, company1);

        return new Person[] {
                person0,
                person1,
                person2,
                new Person(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Role("Data Scientist"),
                getSkillSet("R", "CUDA")
        ), new Person(
                new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Role("Machine Learning Engineer"),
                getSkillSet("CUDA", "Python")
        ), new Person(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Role("Bus driver"),
                getSkillSet("DrivingLicense")
        )};
    }

    public static Job[] getSampleJobs(AddressBook sampleAb) {

        List<Company> companies = new ArrayList<>(sampleAb.getCompanyList());

        // Need at least two sample companies
        assert companies.size() >= 2;

        Company company0 = companies.get(0);
        Company company1 = companies.get(1);

        Job job0 = new Job(
                new Name("Software Engineer, Google Pay, Core"),
                new JobCompany(company0.getName().fullName, sampleAb),
                new JobSalary("100"),
                new JobDescription(
                        "As a software engineer, you will work on a specific project critical to Google’s needs with "
                                + "opportunities to switch teams and projects as you and our fast-paced business grow and "
                                + "evolve. "),
                getRequirements("Go", "Kubernetes", "Docker", "5YOE")
        );

        Job job1 = new Job(
                new Name("Software Engineering Manager II, YouTube"),
                new JobCompany(company1.getName().fullName, sampleAb),
                new JobSalary("200"),
                new JobDescription(
                        "As a Software Engineering Manager you manage your project goals, contribute to product "
                                + "strategy and help develop your team. "),
                getRequirements("Leadership", "AGILE", "SDLC", "CICD")
        );


        companies.set(0, createCompanyWithJobs(company0, List.of(job0)));
        companies.set(1, createCompanyWithJobs(company1, List.of(job1)));
        sampleAb.setCompanies(companies);

        return new Job[]{ job0, job1 };
    }

    public static Company[] getSampleCompanies() {
        return new Company[]{new Company(
                new Name("Google"),
                new Address("70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371"),
                new BillingDate("1"),
                new Phone("65218000")
        ), new Company(
                new Name("Meta"),
                new Address("9 Straits View, Marina One, Singapore 018937"),
                new BillingDate("2"),
                new Phone("12345678")
        ), new Company(
                new Name("Amazon"),
                new Address("23 Church St, #10-01, Singapore 049481"),
                new BillingDate("3"),
                new Phone("67220300")
        )};
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        // Due to the dependency between the entities, companies should be created
        // before jobs, then contacts
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }

        for (Job sampleJob : getSampleJobs(sampleAb)) {
            sampleAb.addJob(sampleJob);
        }

        for (Person samplePerson : getSamplePersons(sampleAb)) {
            sampleAb.addPerson(samplePerson);
        }

        return sampleAb;
    }

    private static Company createCompanyWithJobs(Company company, List<Job> jobs) {
        Name newName = company.getName();
        Address newAddress = company.getAddress();
        BillingDate newBillingDate = company.getBillingDate();
        Phone newPhone = company.getPhone();
        return new Company(newName, newAddress, newBillingDate, newPhone, jobs);
    }

    private static Job createJobWithMatches(Job job, List<Person> matches) {
        Name newName = job.getName();
        JobCompany newCompany = job.getCompany();
        JobSalary newSalary = job.getSalary();
        JobDescription newDescription = job.getDescription();
        Set<Tag> newRequirements = job.getRequirements();
        return new Job(newName, newCompany, newSalary, newDescription, newRequirements, matches);
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings).map(Skill::new).collect(Collectors.toSet());
    }

    /**
     * Returns a requirement set containing the list of strings given.
     */
    public static Set<Tag> getRequirements(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
