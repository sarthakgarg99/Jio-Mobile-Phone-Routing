public class MobilePhone
{   public int mobileid = 0;
    public Boolean mobilestatus = false;
    public Exchange baseloc = null;

    public MobilePhone(int number)
    {
        mobileid=number;
    }

    public int number()
    {
        return mobileid;
    }

    public Boolean status()
    {
        return mobilestatus;
    }

    public void switchOn()
    {
        mobilestatus=true;
    }

    public void switchOff()
    {
        baseloc = null;
        mobilestatus=false;
    }

    public Exchange location()
    {   
        if (mobilestatus == false)
        {
           return null;
        }
        else
            return baseloc;
    }
}