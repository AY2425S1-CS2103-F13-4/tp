package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCompanyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.*;

/**
 * Parses input arguments and creates a new ViewCompanyCommand object.
 */
public class ViewCompanyCommandParser implements Parser<ViewCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of a ViewCompanyCommand
     * and returns a ViewCompanyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ViewCompanyCommand parse(String args) throws ParseException {

        String[] splitArgs = args.trim().split("\\s+");

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCompanyCommand.MESSAGE_USAGE));
        } else if (!splitArgs[0].equals("company")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCompanyCommand.MESSAGE_USAGE));
        } else if (splitArgs.length == 1) {
            throw new ParseException((MESSAGE_MISSING_INDEX));
        }

        String indexString = splitArgs[1];

        Index index;

        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_DISPLAYED_INDEX,
                            ViewCompanyCommand.MESSAGE_USAGE), pe);
        }

        return new ViewCompanyCommand(index);

    }
}
