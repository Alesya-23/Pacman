package PacmanListRecord;

public class NodeRec {
	int key;
	NodeRec next;
	NodeRec prev;
	
	public NodeRec(int key, NodeRec next, NodeRec prev){
		this.key = key;
		this.next = next;
		this.prev = prev;
	}
}
