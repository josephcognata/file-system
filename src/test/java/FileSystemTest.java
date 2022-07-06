import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class FileSystemTest {

    @Test
    public void TestBasicCommands() {
        FileSystem fs = new FileSystem();
        assertEquals("/root", fs.pwd());
        assertEquals("", fs.mkdir("DSA"));
        assertEquals(ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS, fs.mkdir("DSA"));
        assertEquals(ErrorMessages.NO_SUCH_FILE_OR_DIRECTORY, fs.cd("foo"));
        assertEquals("", fs.cd("DSA"));
        assertEquals("/root/DSA", fs.pwd());
        assertEquals("", fs.cd(".."));
        assertEquals(ErrorMessages.ALREADY_AT_ROOT, fs.cd(".."));
        assertEquals("", fs.touch("DSAFile.txt"));
        assertEquals(ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS, fs.touch("DSAFile.txt"));
        assertEquals(ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS, fs.touch("DSA"));
        assertEquals(ErrorMessages.CANNOT_CD_INTO_FILE, fs.cd("DSAFile.txt"));
        assertEquals("/root", fs.pwd());
    }

    @Test
    public void TestLs() {
        FileSystem fs = new FileSystem();
        fs.mkdir("DSA");
        fs.cd("DSA");
        assertEquals("/root/DSA", fs.pwd());
        fs.cd("..");
        fs.mkdir("DSA2");
        fs.touch("DSAFile");
        fs.cd("DSA");
        fs.mkdir("Level2");
        fs.cd("..");
        ArrayList<String> expectedFileList = new ArrayList<String>(Arrays.asList("d DSA", "f DSAFile", "d DSA2"));

        ArrayList<String> actualFileList = new ArrayList<String>();
        String result[] = fs.ls().split("\n");

        for (String s : result)
            actualFileList.add(s);

        Collections.sort(actualFileList);
        Collections.sort(expectedFileList);

        assertTrue(actualFileList.equals(expectedFileList));
    }

    @Test
    public void TestRm() {

        FileSystem fs = new FileSystem();
        fs.mkdir("DSA");
        fs.cd("DSA");
        assertEquals("", fs.ls());
        assertEquals("", fs.mkdir("newDir"));
        assertEquals("d newDir\n", fs.ls());

        assertEquals("", fs.cd("newDir"));
        assertEquals("", fs.ls());
        fs.cd("..");
        assertEquals("", fs.rm("newDir"));
        assertEquals(ErrorMessages.NO_SUCH_FILE_OR_DIRECTORY, fs.rm("newDir"));
        assertEquals("", fs.ls());

        fs.mkdir("dir1");
        fs.mkdir("dir2");
        fs.touch("file1");
        assertEquals("", fs.rm("dir2"));
        assertEquals("", fs.rm("file1"));
        assertEquals("", fs.rm("dir1"));
        assertEquals("", fs.ls());

    }
    @Test
    public void TestMv()
    {
        FileSystem fs = new FileSystem();
        fs.mkdir("dir1");
        fs.mkdir("dir2");
        fs.touch("file1");
        assertEquals("", fs.mv("dir1", "directory1"));
        assertEquals(ErrorMessages.NO_SUCH_FILE_OR_DIRECTORY, fs.mv("foo", "bar"));
        assertEquals(ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS, fs.mv("file1", "dir2"));
    }

}
