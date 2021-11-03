package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FIND_STUDENT_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NAME_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.StudentBuilder.DEFAULT_ID;
import static seedu.address.testutil.TypicalModules.INVALID_MODULE_NAME;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.StudentIdEqualsKeywordPredicate;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStudentCommand}.
 */
public class FindStudentCommandTest {
    private Model model = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());

    @Test
    public void equals() {
        StudentIdEqualsKeywordPredicate firstPredicate =
                new StudentIdEqualsKeywordPredicate(VALID_STUDENT_ID_AMY);
        StudentIdEqualsKeywordPredicate secondPredicate =
                new StudentIdEqualsKeywordPredicate(VALID_STUDENT_ID_BOB);

        FindStudentCommand findFirstCommand = new FindStudentCommand(new ModuleName(MODULE_NAME_0),
                new StudentId(VALID_STUDENT_ID_AMY));
        FindStudentCommand findSecondCommand = new FindStudentCommand(new ModuleName(MODULE_NAME_1),
                new StudentId(VALID_STUDENT_ID_BOB));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStudentCommand findFirstCommandCopy = new FindStudentCommand(new ModuleName(MODULE_NAME_0),
                new StudentId(VALID_STUDENT_ID_AMY));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different student -> returns false
        findFirstCommandCopy = new FindStudentCommand(new ModuleName(MODULE_NAME_0),
                new StudentId(VALID_STUDENT_ID_BOB));
        assertFalse(findFirstCommand.equals(findFirstCommandCopy));

        // different module -> returns false
        findFirstCommandCopy = new FindStudentCommand(new ModuleName(MODULE_NAME_1),
                new StudentId(VALID_STUDENT_ID_AMY));
        assertFalse(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different module and student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_moduleNotFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_NAME_NOT_FOUND, INVALID_MODULE_NAME);
        FindStudentCommand command = new FindStudentCommand(new ModuleName(INVALID_MODULE_NAME),
                new StudentId(VALID_STUDENT_ID_AMY));
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(getTypicalModules(), model.getFilteredModuleList());
    }

    @Test
    public void execute_studentNotFound() {
        String expectedMessage = String.format(MESSAGE_STUDENT_NOT_FOUND, DEFAULT_ID);
        FindStudentCommand command = new FindStudentCommand(new ModuleName(MODULE_NAME_0),
                new StudentId(DEFAULT_ID));
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(getTypicalModules(), model.getFilteredModuleList());
    }

    @Test
    public void execute_studentIsFound() {
        String expectedMessage = String.format(MESSAGE_FIND_STUDENT_SUCCESS, VALID_STUDENT_ID_AMY);
        StudentIdEqualsKeywordPredicate predicate = new StudentIdEqualsKeywordPredicate(VALID_STUDENT_ID_AMY);
        FindStudentCommand command = new FindStudentCommand(new ModuleName(MODULE_NAME_0),
                new StudentId(VALID_STUDENT_ID_AMY));
        expectedModel.getFilteredModuleList().get(0).updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalModules.getTypicalModules().get(0).getFilteredStudentList().get(0)),
                model.getFilteredModuleList().get(0).getFilteredStudentList()
        );
    }
}