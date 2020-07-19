package PacmanListRecord;

import java.io.FileWriter;
import java.io.IOException;


public class PacmanListRecord {
	
	NodeRec head;
	NodeRec tail;
	
	public PacmanListRecord(int key){
		head = new NodeRec(0,null,null);
		tail = new NodeRec(0,head,head);
		
		head.next = tail;
		head.prev = tail;
	}


	public void insertToHead(int key) {
		NodeRec p = new NodeRec(key, head.next, head);		
		head.next.prev = p;
		head.next = p;	
	}

	public String toString() {
		String str = "";
		int number = 1;
		NodeRec p = head.next;
		while (p != tail) {			
			str = str + number + "-" + p.key + "\n ";
			p = p.next;
			number++;
		}
		try (FileWriter file = new FileWriter("Record.txt")){
			file.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str; 		
	}

}
