import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTests {
    private final String pathToZipFile = "src/test/resources/filesLessons.zip";
    private final String pathToPdfFile = "fileslessons/pdfExample.pdf";
    private final String pathToXlsxFile = "fileslessons/xlsxExample.xlsx";
    private final String pathToCsvFile = "fileslessons/csvExample.csv";

    @Tag("SMOKE")
    @DisplayName("PDF файл должен содержать ожидаемый текст")
    @Test
    public void readPdfInZipTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(pathToZipFile)) {
            ZipEntry entry = zipFile.getEntry(pathToPdfFile);
            PDF pdf = new PDF(zipFile.getInputStream(entry));

            assertThat(pdf.numberOfPages).isEqualTo(2);
            assertThat(pdf.text).contains("Lorem ipsum dolor sit amet, consectetuer adipiscing elit");
        }
    }

    @Tag("SMOKE")
    @DisplayName("XLSX файл должен содержать ожидаемый текст в ячейке после внесения изменений")
    @Test
    public void editAndReadXlsxInZipTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(pathToZipFile)) {
            ZipEntry entry = zipFile.getEntry(pathToXlsxFile);
            XLS xls = new XLS(zipFile.getInputStream(entry));

            xls.excel.getSheetAt(1).getRow(3).getCell(2).setCellValue("Activities!!!!!");
            String resultCellValue = xls.excel.getSheetAt(1).getRow(3).getCell(2).getStringCellValue();

            assertThat(resultCellValue).isEqualTo("Activities!!!!!");
        }
    }

    @Tag("SMOKE")
    @DisplayName("Значения строк CSV файла должны соответствовать ожидаемым значениям")
    @Test
    public void readCsvInZipTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(pathToZipFile)) {
            ZipEntry entry = zipFile.getEntry(pathToCsvFile);

            try (InputStream inputStream = zipFile.getInputStream(entry);
                 CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                List<String[]> data = reader.readAll();

                assertThat(data.size()).isEqualTo(3);

                assertThat(data.get(0)).isEqualTo(new String[]{
                        "Name", "Job Title", "Address", "State", "City"
                });
                assertThat(data.get(1)).isEqualTo(new String[]{
                        "John Doe", "Designer", "325 Pine Street", "", "Seattle"
                });
                assertThat(data.get(2)).isEqualTo(new String[]{
                        "Edward Green", "Developer", "110 Pike Street", "WA", "Seattle"
                });
            }
        }
    }
}
