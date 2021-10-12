//import seedu.address.model.AddressBook;
//import seedu.address.testutil.TypicalPersons;
//
//public class JsonSerializableAddressBookTest {
//
//    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
//    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
//    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
//    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
//
//    @Test
//    public void toModelType_typicalPersonsFile_success() throws Exception {
//        JsonSerializableAssistantBuddy dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
//                JsonSerializableAssistantBuddy.class).get();
//        AddressBook addressBookFromFile = dataFromFile.toModelType();
//        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
//        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
//    }
//
//    @Test
//    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
//        JsonSerializableAssistantBuddy dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
//                JsonSerializableAssistantBuddy.class).get();
//        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
//    }
//
//    @Test
//    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
//        JsonSerializableAssistantBuddy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
//                JsonSerializableAssistantBuddy.class).get();
//        assertThrows(IllegalValueException.class, JsonSerializableAssistantBuddy.MESSAGE_DUPLICATE_PERSON,
//                dataFromFile::toModelType);
//    }
//
//}