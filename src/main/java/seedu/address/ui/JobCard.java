package seedu.address.ui;

import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.job.Job;



/**
 * A UI component that displays information of a {@code Job}.
 */
public class JobCard extends UiPart<Region> {

    private static final String FXML = "JobListCard.fxml";

    public final Job job;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label company;
    @FXML
    private Label salary;
    @FXML
    private Label description;
    @FXML
    private FlowPane requirements;
    @FXML
    private Label matchStatus;

    /**
     * Creates a {@code JobCode} with the given {@code Job} and index to display.
     */
    public JobCard(Job job, int displayedIndex) {
        super(FXML);
        this.job = job;
        id.setText(displayedIndex + ". ");
        name.setText(job.getName().fullName);
        company.setText(job.getCompany().fullName);
        salary.setText(job.getSalary().toString());
        description.setText(job.getDescription().value);
        job.getRequirements().stream()
                .sorted(Comparator.comparing(requirement -> requirement.tagName))
                .forEach(requirement -> requirements.getChildren().add(new Label(requirement.tagName)));

        List<String> matchedPersonIdentifiers = job.getMatch();
        if (matchedPersonIdentifiers != null) {
            String personName = matchedPersonIdentifiers.get(0);
            String personNumber = matchedPersonIdentifiers.get(1);
            matchStatus.setText("Matched with " + personName + ", " + personNumber);
        } else {
            matchStatus.setText("Open to applications");
        }
    }
}
