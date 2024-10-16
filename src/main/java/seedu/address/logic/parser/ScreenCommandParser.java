package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScreenCommand;
import seedu.address.logic.commands.ScreenJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new subclass of ScreenCommand object
 */
public class ScreenCommandParser implements Parser<ScreenCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ScreenCommand
     * and returns a ScreenCommand subclass object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScreenCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split("\\s+");

        if (splitArgs.length < 1) {
            throw new ParseException(ScreenCommand.MESSAGE_USAGE);
        }
        String entityType = splitArgs[0];
        if (splitArgs.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScreenCommand.MESSAGE_USAGE));
        }
        String indexString = splitArgs[1];
        Index index;

        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScreenCommand.MESSAGE_USAGE), pe);
        }
        switch (entityType) {
        case ScreenJobCommand.ENTITY_WORD:
            return new ScreenJobCommand(index);
        default:
            throw new ParseException(ScreenCommand.MESSAGE_USAGE);
        }
    }
}
