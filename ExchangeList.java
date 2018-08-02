public class ExchangeList extends LinkedList
{
   LinkedList ll = new LinkedList();

    public Boolean IsEmpty()
    {
        return ll.IsEmpty();
    }

    public int size()
    {
        return ll.size();
    }
    
    public void addLast(Exchange o)
    {
        ll.addLast(o);
    }
    
    public void addFirst(Exchange o)
    {
        ll.addFirst(o);
    }

    public Exchange removeFirst()
    {
        return (Exchange) ll.removeFirst();
    }
    
    public ExchangeList clone()
    {
        ExchangeList x = new ExchangeList();
        x.ll = ll.clone();
        return x;
    }
    
    public Boolean contains(Exchange o)
    {
        for (int i=0; i<ll.size(); i++)
        {
            if (this.get(i).nodeindex==o.nodeindex)
                {return true;}
        }
        return false;
    }

    public ExchangeList concatlists(ExchangeList a)
    {
        ExchangeList x = a.clone();
        ExchangeList y = this.clone();
        while(y!=null)
        {
            Exchange z = y.removeFirst();
            x.addLast(z);
        }
        return x;
    }
    
    public Exchange remove(Exchange o)
    {
        return (Exchange) ll.remove(o);
    }
    
    public Exchange get(int i)
    {
        return (Exchange) ll.get(i);
    }

    public String PrintExchList()
    {
        ExchangeList x = this.clone();
        Exchange e;
        String s = "";
        while(!x.IsEmpty())
        {
            e=x.removeFirst();
            s = (s + e + ", ");
        }
        return s;
    }
}