import com.fasterxml.jackson.databind.ObjectMapper;
import jsonObjects.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTests {
    private final String pathToJsonFile = "src/test/resources/student.json";

    @Tag("SMOKE")
    @DisplayName("Значения полей в JSON файле должно соответствовать ожидаемым значениям")
    @Test
    public void jsonStudentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathToJsonFile);
        Student student = objectMapper.readValue(file, Student.class);

        assertThat(student.getFirstName()).isEqualTo("John");
        assertThat(student.getLastName()).isEqualTo("Snow");
        assertThat(student.getAge()).isEqualTo(15);
        assertThat(student.isHasScholarship()).isEqualTo(false);
        assertThat(student.getFavoriteSubjects()).isEqualTo(new String[]{
                "Math", "Chemistry", "History"
        });
    }
}
