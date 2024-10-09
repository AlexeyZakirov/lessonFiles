import com.fasterxml.jackson.databind.ObjectMapper;
import jsonObjects.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JsonTests {
    private final String pathToJsonFile = "src/test/resources/student.json";

    @Tag("SMOKE")
    @DisplayName("Значения полей в JSON файле должно соответствовать ожидаемым значениям")
    @Test
    public void jsonStudentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathToJsonFile);
        Student student = objectMapper.readValue(file, Student.class);

        Assertions.assertEquals("John", student.getFirstName());
        Assertions.assertEquals("Snow", student.getLastName());
        Assertions.assertEquals(15, student.getAge());
        Assertions.assertEquals(false, student.isHasScholarship());
        Assertions.assertArrayEquals(new String[]{"Math", "Chemistry", "History"}, student.getFavoriteSubjects());
    }
}
