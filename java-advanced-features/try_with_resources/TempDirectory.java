package try_with_resources;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class TempDirectory implements AutoCloseable {
    private final Path dir;

    public TempDirectory(String prefix) throws IOException {
        this.dir = Files.createTempDirectory(prefix);
    }

    public Path getDir() {
        return dir;
    }

    @Override
    public void close() throws IOException {

        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("operating on file : " + file);
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("finished all operations on dir : " + dir);
                if (exc == null) {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                } else {
                    throw exc;
                }
            }
        });

    }
}

// new SimpleFileVisitor<Path>() {

// @Override
// public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws
// IOException {
// Files.delete(file);
// return FileVisitResult.CONTINUE;
// };

// @Override
// public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws
// IOException {
// if (exc == null) {
// Files.delete(dir);
// return FileVisitResult.CONTINUE;
// } else {
// throw exc;
// }
// };
// }