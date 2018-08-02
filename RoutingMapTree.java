public class RoutingMapTree
{
	public Exchange root;

	public RoutingMapTree()
	{
		root = new Exchange(0);
	}

	public Boolean containsNode(Exchange a) // Tells whether Exchange with identifier a.nodeindex exists or not in the tree
	{
		if (root.nodeindex==a.nodeindex) return true;
		else
		{
			for (int i=0; i<root.childlist.size(); i++)
			{
				if(root.subtree(i).containsNode(a));
					return true;
			}
		}
		return false;
	}

	public Exchange getexch(Exchange a) // Returns the Exchange in the tree with identifier same as that of Exchange a
	{
		if (root.nodeindex==a.nodeindex)
			{
				return root;
			}
		else
		{
			Exchange temp= null; 
			for (int i=0; i<root.numChildren(); i++)
			{
				temp = root.subtree(i).getexch(a);
				if(temp!=null)
					break;
			}
			if(temp!=null)
				return temp;
			return null;
		}
	}

	public void switchOn(MobilePhone a, Exchange b) 
	{
		try{
		if (a.status() == false)
		{
			Exchange x = new Exchange(0);
			x = b;
			while (x != null)
			{
				(x.nodeset).Insert(a);
				x=x.parent();
			}
			a.baseloc = b;
		}
	    else throw new Exception("Phone is already on!");
	    a.switchOn();
	}catch(Exception e){System.out.println(e.getMessage());}
	}

	public void switchOff(MobilePhone a)
	{
		try{
		if (a.status() == true)
		{
			Exchange x = a.location();
			while(x != null)
			{
				if (!x.nodeset.IsEmpty()) {x.nodeset.Delete(a);}
				else throw new Exception("The Set is already Empty!");
				x=x.parent();
			}
			a.switchOff();
		}
		else throw new Exception("Phone is already off!");
	}
	catch (Exception e) {System.out.println(e.getMessage());}
	}

	public Exchange findPhone(MobilePhone m)
	{
		if (m.status()==false)
			System.out.print("The Mobile Phone is not on!");
		return m.location();
	}

	public Exchange lowestRouter(Exchange a, Exchange b)
	{
		if (a==b) {return a;}
		Exchange x;
		x = a.parent();
		while(x!=null)
		{
			for (int i=0; i<x.numChildren(); i++)
			{
				if (x.subtree(i).containsNode(b))
					return x;
				x = x.parent();
			}
		}
		return null;
	}

	public ExchangeList routeCallhelpera(MobilePhone a, MobilePhone b) // Adds exchanges from a.location() to z
	{
		Exchange x = a.location();
		Exchange y = b.location();
		Exchange z = lowestRouter(x,y);
		ExchangeList route = new ExchangeList();
		while(x!=z.parent())
		{
			route.addLast(x);
			x=x.parent();
		}
		return route;
	}

	public ExchangeList routeCallhelperb(MobilePhone a, MobilePhone b) // Adds exchanges from b.location() to z's child
	{
		Exchange x = a.location();
		Exchange y = b.location();
		Exchange z = lowestRouter(x,y);
		ExchangeList route = new ExchangeList();
		while(y!=z)
		{
			route.addFirst(y);
			y=y.parent();
		}
		return route;
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b)
	{
		ExchangeList x = routeCallhelpera(a,b);
		ExchangeList y = routeCallhelperb(a,b);
		return y.concatlists(x);
	}

	public void movePhone(MobilePhone a, Exchange b)
	{
		if (containsNode(b) && a.status()==true)
		{
			switchOff(a);
			switchOn(a,b);
		}
		else System.out.println("The required change of location is invalid!");
	}

	public int initiala(String a) // Gets starting index of a
	{
		int counter = 0;
		while(!(a.charAt(counter)==' '))
		{
			counter++;
		}
		counter++;
		return counter;
	}
	
	public int finala(String a) // Gets ending index of a
	{
		int counter = 0;
		int p = 0;
		while(!(a.charAt(counter)==' ') || !(p==1))
		{
			if (a.charAt(counter)==' ')
			{
				p++;
			}
			counter++;
		}
		return counter;
	}	

	public int geta1(String a)
	{	
		String x = a.substring(initiala(a),finala(a));
		return Integer.parseInt(x);
	}

	public int getb1(String a)
	{
		String x = a.substring(finala(a)+1,a.length());
		return Integer.parseInt(x);
	}

	public int geta2(String a)
	{
		String x = a.substring(initiala(a),a.length());
		return Integer.parseInt(x);
	}

	public String performAction(String actionMessage)
	{   
	    try
	    {
    		String action1 = actionMessage.substring(0,actionMessage.length());
    		String action2 = actionMessage.substring(0,actionMessage.length());
    		if (actionMessage.charAt(0)=='a')
    		{
    			int a = geta1(action1);
    			int b = getb1(action2);
	            if (a<0 || b<0) {throw new Exception("Invalid inputs "+a+" and "+b+" are given!");}    			
    			Exchange aexch = new Exchange(a);
    			aexch = getexch(aexch);
    			Exchange bexch = new Exchange(b);
    			bexch.parent = aexch;
    			if (aexch!=null)
    			{
    				aexch.childlist.addLast(bexch);
    				aexch = aexch.parent;
    				return("");
    			}
    			else throw new Exception("No Exchange with identifier "+a+" exists!");
    		}
    
    		else if (actionMessage.substring(0,8).equals("switchOn"))
    		{
    			int a = geta1(action1);
    			int b = getb1(action2);
    	        if (a<0 || b<0) {throw new Exception("Invalid inputs "+a+" and "+b+" are given!");}    			
    			Exchange bexch = new Exchange(b);
    			bexch = this.getexch(bexch);
    			MobilePhone amobile = new MobilePhone(a);
    			amobile = root.nodeset.Member(amobile);
    			if (bexch!=null)
    			{
    				if (amobile!=null)
    				{
    					switchOff(amobile);
    					switchOn(amobile,bexch);
    				}	
    				else 
    				{
    					MobilePhone amob = new MobilePhone(a);
    					switchOn(amob,bexch);				
    				}
    				return("");
    			}
    			else throw new Exception("No Exchange with identifier "+b+" exists!");
    		}
    
    		else if (actionMessage.substring(0,8).equals("switchOf"))
    		{
    			int a = geta2(action1);
    			if (a<0) {throw new Exception("Invalid input "+a+" is given!");}
    			MobilePhone amobile = new MobilePhone(a);
    			amobile = root.nodeset.Member(amobile);
    			if (amobile!=null)
    			{
    				switchOff(amobile);
    			    return("");
    			}
    			else throw new Exception("No Mobile Phone with identifier "+a+" exists!");
    		}
    
    		else if (actionMessage.substring(0,8).equals("queryNth"))
    		{
    			int a = geta1(action1);
    			int b = getb1(action2);
    	        if (a<0 || b<0) {throw new Exception("Invalid inputs "+a+" and "+b+" are given!");}    			
    			Exchange aexch = new Exchange(a);
    			aexch = getexch(aexch);
    			if (aexch!=null)
    			{
    				if (aexch.child(b)!=null)
    				return(actionMessage + ": " + aexch.child(b).nodeindex);
    				else throw new Exception("The given index "+b+" is out of bounds for the childlist of aexch!");
    			}
    			else throw new Exception("No Exchange with identifier "+a+" exists!");
    		}
    
    		else if (actionMessage.substring(0,8).equals("queryMob"))
    		{
    			int a = geta2(action1);
    			if (a<0) {throw new Exception("Invalid input "+a+" is given!");}
    			Exchange aexch = new Exchange(a);
    			aexch = this.getexch(aexch);
    			if (aexch!=null)
    			{
    				MobilePhoneSet done = aexch.nodeset;
    				String s = (actionMessage + ": " + done.PrintAll());
    				return(s.substring(0,s.length()-2));
    			}
    			else throw new Exception("No Exchange with identifier "+a+" exists!");
    		}

    		else if (actionMessage.substring(0,14).equals("queryFindPhone"))
    		{
    			int a = geta2(action1);
    			MobilePhone amobile = new MobilePhone(a);
    			amobile = root.nodeset.Member(amobile);
    			return (actionMessage + ": " + findPhone(amobile).nodeindex);
    		}
    
    		else if (actionMessage.substring(0,8).equals("queryLow"))
    		{
    			int a = geta1(action1);
    			int b = getb1(action2);
    			Exchange aexch = new Exchange(a);
    			aexch = getexch(aexch);
    			Exchange bexch = new Exchange(b);
    			bexch = getexch(bexch);
    			return (actionMessage + ": " + lowestRouter(aexch,bexch).nodeindex);
    		}

    		else if (actionMessage.substring(0,14).equals("queryFindCallP"))
    		{
    			int a = geta1(action1);
    			int b = getb1(action2);
    			MobilePhone amobile = new MobilePhone(a);
    			amobile = root.nodeset.Member(amobile);
    			MobilePhone bmobile = new MobilePhone(b);
    			bmobile = root.nodeset.Member(bmobile);
    			String s = (actionMessage + ": " + routeCall(amobile,bmobile).PrintExchList());
    			return s.substring(0,s.length()-2);

    		}

    		else if (actionMessage.substring(0,8).equals("movePhon"))
    		{
    			int a = geta1(action1);
    			int b = getb1(action2);
    			MobilePhone amobile = new MobilePhone(a);
    			amobile = root.nodeset.Member(amobile);
    			Exchange bexch = new Exchange(b);
    			bexch = getexch(bexch);
    			movePhone(amobile,bexch);
    			return "";
    		}

    		return("No actionMessage matched with the given cases!");
	    }
	    catch(Exception e)
	    {
	        return (e.getMessage());
	    }
	}

}