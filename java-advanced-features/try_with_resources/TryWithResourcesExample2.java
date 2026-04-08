package try_with_resources;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TryWithResourcesExample2 {
    public static void main(String[] args) {
        try (
                TempDirectory tempDirectory = new TempDirectory("test");
                BufferedWriter bufferedWriter = Files.newBufferedWriter(tempDirectory.getDir().resolve("test.txt"),
                        StandardCharsets.UTF_8)) {

            System.out.println("Temporary Directory : " + tempDirectory.getDir());
            bufferedWriter.write("try with resources examples");
        } catch (Exception e) {
            System.out.println("Inside catch block");
        }
    }
}