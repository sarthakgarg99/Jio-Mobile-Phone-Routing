public class Myset
{
	public LinkedList linklist = new LinkedList();
	
	public Boolean IsEmpty()
	{
		return (linklist.size()==0);
	}

	public Boolean IsMember(Object o)
	{
		return linklist.contains(o);
	}	

	public Object remove()
	{
		return linklist.removeFirst();
	}

	public void Insert(Object o)
	{
		if (!linklist.contains(o))
		{
			linklist.addLast(o);
		}
	}

	public void Delete(Object o)
	{
		if (linklist.contains(o))
		{linklist.remove(o);}
	}

	public Myset Mysetclone() // Similar to linked list clone, makes a clone of the Myset
	{
		Myset a = new Myset();
		a.linklist = this.linklist.clone();
		return a;
	}

	public Myset Union(Myset a)
	{
		LinkedList templl = new LinkedList();
		Myset b = new Myset();
		b = a.Mysetclone();
		templl = linklist.clone();
		while(templl.size!=0)
		{
			Object temp = templl.removeFirst();
			if (!(b.IsMember(temp)))
			{
				b.Insert(temp);
				(b.linklist.size)++;
			}

		}
		return b;
	}

	public Myset Intersection(Myset a)
	{
		LinkedList templl = new LinkedList();
		Myset b = new Myset();
		templl = linklist.clone();
		for (int i = 0; i<linklist.size(); i++)
		{
			Object temp = templl.removeFirst();
			if (a.IsMember(temp))
			{
				b.Insert(temp);
				b.linklist.size++;

			}
		}
		return b;
	}
}