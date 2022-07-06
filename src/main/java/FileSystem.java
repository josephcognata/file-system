import java.util.ArrayList;

public class FileSystem {
    Node curDirectory;

    public FileSystem() {
        this.curDirectory = new Node("root");
    }

    public String pwd() {
        String password = "/root";
        Node curNode = this.curDirectory;
        while (curNode.parent != null) {
            password = password.concat("/" + curNode.toString());
            curNode = curNode.parent;
        }
        return password;
    }

    public String mkdir(String dirName) {
        if (this.curDirectory.containsChild(dirName)) {
            return ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS;
        }
        this.curDirectory.addChild(new Node(dirName, 'd'));
        return "";
    }

    public String cd(String dirName) {
        if (dirName == "..") {
            if (this.curDirectory.GetParent() == null) {
                return ErrorMessages.ALREADY_AT_ROOT;
            }
            this.curDirectory = this.curDirectory.GetParent();
            return "";
        } else if (!this.curDirectory.containsChild(dirName)) {
            return ErrorMessages.NO_SUCH_FILE_OR_DIRECTORY;
        }
        Node targetNode = this.curDirectory.getChild(dirName);
        if (targetNode.GetType() == 'f') {
            return ErrorMessages.CANNOT_CD_INTO_FILE;
        }
        this.curDirectory = targetNode;
        return "";
    }

    public String touch(String name) {
        if (this.curDirectory.containsChild(name)) {
            return ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS;
        }
        this.curDirectory.addChild(new Node(name));  
        return "";
    }

    public String ls() {
        ArrayList<String> fileAndDirNames = new ArrayList<String>();

        for (Node child : this.curDirectory.getChildren().values()) {
            fileAndDirNames.add(child.GetType() + " " + child.toString() + "\n");
        }

        return String.join("", fileAndDirNames);
    }

    public String rm(String name) {
        if (!this.curDirectory.containsChild(name)) {
            return ErrorMessages.NO_SUCH_FILE_OR_DIRECTORY;
        }
        this.curDirectory.removeChild(name);
        return "";
    }

    public String mv(String curName, String newName) {
        if (!this.curDirectory.containsChild(curName)) {
            return ErrorMessages.NO_SUCH_FILE_OR_DIRECTORY;
        } else if (this.curDirectory.containsChild(newName)) {
            return ErrorMessages.FILE_OR_DIRECTORY_ALREADY_EXISTS;
        }
        Node targetNode = this.curDirectory.getChild(curName);
        targetNode.SetName(newName);
        return "";
    }
}