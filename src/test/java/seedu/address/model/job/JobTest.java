package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BARISTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BARISTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBNAME_BARISTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_BARISTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BARISTA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.BARISTA;
import static seedu.address.testutil.TypicalJobs.SWE;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.JobBuilder;
import seedu.address.testutil.PersonBuilder;

public class JobTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getSkills().remove(0));
    }

    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(SWE.isSameJob(SWE));

        // null -> returns false
        assertFalse(SWE.isSameJob(null));

        // same name and company, all other attributes different -> returns true
        Job editedSWE = new JobBuilder(SWE)
                .withSalary(VALID_SALARY_BARISTA)
                .withRequirements(VALID_REQUIREMENTS_BARISTA)
                .withDescription(VALID_DESCRIPTION_BARISTA)
                .build();
        assertTrue(SWE.isSameJob(editedSWE));

        // different name, all other attributes same -> returns false
        editedSWE = new JobBuilder(SWE).withName(VALID_JOBNAME_BARISTA).build();
        assertFalse(SWE.isSameJob(editedSWE));

        // different company, all other attributes same -> returns false
        editedSWE = new JobBuilder(SWE).withCompany(VALID_COMPANY_BARISTA).build();
        assertFalse(SWE.isSameJob(editedSWE));

        // name differs in case, all other attributes same -> returns false
        Job editedBarista = new JobBuilder(BARISTA).withName(VALID_JOBNAME_BARISTA.toLowerCase()).build();
        assertFalse(BARISTA.isSameJob(editedBarista));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBarista = new JobBuilder(BARISTA).withName(nameWithTrailingSpaces).build();
        assertFalse(BARISTA.isSameJob(editedBarista));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Job copySWE = new JobBuilder(SWE).build();
        assertTrue(SWE.equals(copySWE));

        // same object -> returns true
        assertTrue(SWE.equals(SWE));

        // null -> returns false
        assertFalse(SWE.equals(null));

        // different type -> returns false
        assertFalse(SWE.equals(5));

        // different job -> returns false
        assertFalse(SWE.equals(BARISTA));

        // different name -> returns false
        Job editedSWE = new JobBuilder(SWE).withName(VALID_JOBNAME_BARISTA).build();
        assertFalse(SWE.equals(editedSWE));

        // different company -> returns false
        editedSWE = new JobBuilder(SWE).withCompany(VALID_COMPANY_BARISTA).build();
        assertFalse(SWE.equals(editedSWE));

        // different salary -> returns false
        editedSWE = new JobBuilder(SWE).withSalary(VALID_SALARY_BARISTA).build();
        assertFalse(SWE.equals(editedSWE));

        // different requirements -> returns false
        editedSWE = new JobBuilder(SWE).withRequirements(VALID_REQUIREMENTS_BARISTA).build();
        assertFalse(SWE.equals(editedSWE));

        // different description -> returns false
        editedSWE = new JobBuilder(SWE).withDescription(VALID_DESCRIPTION_BARISTA).build();
        assertFalse(SWE.equals(editedSWE));
    }

    @Test
    public void toStringMethod() {
        String expected =
                Job.class.getCanonicalName() + "{name=" + SWE.getName() + ", company=" + SWE.getCompany() + ", salary="
                        + SWE.getSalary() + ", requirements=" + SWE.getRequirements() + ", description="
                        + SWE.getDescription() + "}";
        assertEquals(expected, SWE.toString());
    }
}
