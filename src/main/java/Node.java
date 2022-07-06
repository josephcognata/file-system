import java.util.*;

public class Node {
    String name;
    char type;
    Node parent;
    HashMap<String, Node> children;

    public Node(String name, char type) {
        this.name = name;
        this.type = type;
        this.parent = null;
        this.children = new HashMap<String, Node>();
    }

    public Node(String name) {
        this.name = name;
        this.type = 'f';
        this.parent = null;
        this.children = new HashMap<String, Node>();
    }

    public String toString() {
        return this.name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public char GetType() {
        return this.type;
    }

    public Node GetParent() {
        return this.parent;
    }

    public void SetParent(Node parent) {
        this.parent = parent;
    }

    public HashMap<String, Node> getChildren() {
        return this.children;
    }

    public Node getChild(String name) {
        return this.children.get(name);
    }

    public void addChild(Node child) {
        child.SetParent(this);
        this.children.put(child.toString(), child);
    }

    public void addChild(String name, char type) {
        Node child = new Node(name, type);
        addChild(child);
    }

    public void removeChild(String name) {
        this.children.remove(name);
    }

    public Boolean containsChild(String name) {
        return this.children.get(name) != null;
    }
}
