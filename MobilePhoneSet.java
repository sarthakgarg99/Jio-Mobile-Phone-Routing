public class MobilePhoneSet extends Myset
{
	Myset mobileset = new Myset();

	public Boolean IsEmpty()
	{
		return mobileset.IsEmpty();
	}

	public MobilePhone remove()
	{
		return (MobilePhone) mobileset.remove();
	}
	
	public Boolean IsMember(MobilePhone o)
	{
		MobilePhoneSet b = new MobilePhoneSet();
		b = this.Mysetclone();
		while(b!=null)
		{
			MobilePhone a = b.remove();
			if (a.number()==o.number())
				return true;
		}
		return false;
	}
	
	public MobilePhone Member(MobilePhone o)
	{
		MobilePhoneSet b = new MobilePhoneSet();
		b = this.Mysetclone();
		while(!b.IsEmpty())
		{
			MobilePhone a = b.remove();
			if (a!=null && a.mobileid==o.mobileid)
				return a;
		}
		return null;
	}

	public void Insert(MobilePhone o)
	{
		mobileset.Insert(o);
	}
	
	public void Delete(MobilePhone o)
	{
		mobileset.Delete(o);
	}

	public String PrintAll() // Prints all the Mobile Phones in the Mobile Phone Set
	{
		MobilePhoneSet a = new MobilePhoneSet();
		a = this.Mysetclone();
		MobilePhone m;
		String s = "";
		while(!(a.mobileset.IsEmpty()))
		{
			m = a.remove();
			s = s + (m.mobileid + ", ");
		}
		return s;
	}
	
	public MobilePhoneSet Mysetclone()
	{
		MobilePhoneSet a = new MobilePhoneSet();
		a.mobileset = mobileset.Mysetclone();
		return a;
	}

	public MobilePhoneSet Union(MobilePhoneSet a)
	{
		MobilePhoneSet x = new MobilePhoneSet();
		x.mobileset = mobileset.Union(a.mobileset);
		return x;
	}

	public MobilePhoneSet Intersection(MobilePhoneSet a)
	{
		MobilePhoneSet x = new MobilePhoneSet();
		x.mobileset = mobileset.Intersection(a.mobileset);
		return x;
	}
}