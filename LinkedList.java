class Node
{
	public Object nodeval;
	public Node next;

	public Node()
	{
		next = null;
		nodeval = 0;
	}
	
	public Node(Object o, Node n)
	{
		nodeval = o;
		next = n;
	}

	public Object getElement()
	{
		return nodeval;
	}

	public Node getNext()
	{
		return next;
	}

	public void setNext(Node n)
	{
		next = n;
	}

}


public class LinkedList
{
	public Node head = new Node();
	public Node tail = new Node();
	public int size;

	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}

	public Boolean IsEmpty()
	{
		return (size == 0);
	}

	public int size()
	{
		return size;
	}

	public void addLast(Object o)
	{
		Node lastnode = new Node(o,null);
		if (this.IsEmpty()){head=lastnode;}
		else tail.setNext(lastnode);
		tail = lastnode;
		size++;
	}

	public void addFirst(Object o)
	{
		head = new Node(o,head);
		if (size==0){tail=head;}
		size++;
	}

	public Object removeFirst() 
    { 
        if (this.IsEmpty()) return null; 
        Object firstelement = head.getElement(); 
        head = head.getNext();  
        size--; 
        if (size == 0) 
        	tail = null;
        return firstelement;
    }

	public LinkedList clone() // Makes a copy of the current linked list rather than copying its reference
	{
		Node pointer = head;
		LinkedList newll = new LinkedList();
		while(pointer!=null)
		{
			newll.addLast(pointer.getElement());
			pointer = pointer.getNext();
		}
		return newll;
	}

    public Boolean contains(Object o)
    {
        Node pointer = head;
        while(pointer!=null)
        {
            if (pointer.getElement().equals(o))
            	return true;
            pointer=pointer.getNext();
        }
        return false;
    }

    public Object remove(Object o)
    {
    	if (this.IsEmpty() || !(contains(o))) return null; 
    	Node pointer = head;
    	if (pointer.getElement().equals(o)) {this.removeFirst();}
    	else 
    	{
	    	while(pointer != null)
	    	{
	    		Node k = pointer.next;
	    		if (k == null)
	    			break;
	    		if (k.getElement().equals(o))
	    		{
	    			pointer.setNext(k.next);
	    		}
	    		pointer = pointer.getNext();
	    	}
    	}
    	return o;
    }

	public Object get(int i) // Returns the ith element of the linked list
    {
        Node pointer = head;
        int x = 0;
        while (pointer != null)
        {
            if (x == i) 
                return(pointer.getElement());
            x++;
            pointer = pointer.getNext();
        }
        return null;
    }

    public void printList() // Prints all the elements of the current linked list
    {
        Node tmp = this.head;
        while(tmp!=null){System.out.println(tmp.getElement());tmp = tmp.getNext();}
    }    
}