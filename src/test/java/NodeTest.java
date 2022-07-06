import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest
{
	@Test
	void TestCreation()
	{
		Node node = new Node ("root", 'd');
		assertEquals("root", node.toString());
        assertEquals('d', node.GetType());
        assertEquals(null, node.GetParent());

        // a new node has no children
        assertEquals(0, node.getChildren().size());
       
        // default type is file
        Node fileNode = new Node ("filename"); 
        assertEquals('f', fileNode.GetType());

	}
    @Test
    void TestChaining()
    {
      
		Node node = new Node ("SomeNode");
        Node parent = new Node ("Parent");
        Node child1 = new Node("firstChild");
        
        //add first child
        node.addChild(child1);

        assertSame(child1, node.getChild("firstChild"));

        // node is the parent of child1
        assertSame(node, child1.GetParent());
        
        // add second child
        node.addChild("secondChild", 'f');
        assertEquals('f', node.getChild("secondChild").GetType());

        // connect node to a parent
        node.SetParent(parent);
        assertSame(parent, node.GetParent());

        // now node has two children
        assertEquals(2, node.getChildren().size());

        node.removeChild("secondChild");
        
        // now we have one child
        assertEquals(1, node.getChildren().size());
	}
}