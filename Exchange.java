public class Exchange
{
	public MobilePhoneSet nodeset;
	public int nodeindex = 0;
	public Exchange parent = null;
	public ExchangeList childlist;

	public Exchange(int number)
	{
		nodeindex = number;
		childlist = new ExchangeList();
		parent = null;
		nodeset = new MobilePhoneSet();
	}

	public Exchange parent()
	{
		return parent;
	}

	public int numChildren()
	{
		return childlist.size();
	}

	public Exchange child(int i)
	{
	    if (i<childlist.size())
		return childlist.get(i);
		else return null;
		
	}

	public Boolean isRoot()
	{
		if (parent == null) return true;
		else 
			return false;
	}

	public RoutingMapTree subtree(int i)
	{
		Exchange a = new Exchange(0);
		a = childlist.get(i);
		RoutingMapTree b = new RoutingMapTree();
		b.root = a;
		return b;
	}

	public MobilePhoneSet residentSet()
	{
		return nodeset;
	}

}