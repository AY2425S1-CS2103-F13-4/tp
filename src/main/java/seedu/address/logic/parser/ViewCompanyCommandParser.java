package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ViewCompanyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        String[] splitArgs = ParserUtil.parseRequiredNumberOfArguments(args,
                2, ViewCompanyCommand.MESSAGE_USAGE);
        String indexString = splitArgs[1];
        String entityString = splitArgs[0];

        Index index = ParserUtil.parseIndex(indexString);
        String entity = ParserUtil.parseEntity(entityString);

        if (entity.equals(ViewCompanyCommand.ENTITY_WORD)) {
            return new ViewCompanyCommand(index);
        } else {
            String exceptionMessage = String.format(Messages.MESSAGE_OPERATION_NOT_ALLOWED,
                    ViewCompanyCommand.COMMAND_WORD, entity);
            throw new ParseException(exceptionMessage);
        }
    }
}
