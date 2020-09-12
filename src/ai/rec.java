package ai;
public class rec 
{
	private long stTi;
	private long mxRnTi;
	private int mxDptlvl;
	
	public rec(long mxRnTi)
	{
		this.mxRnTi = mxRnTi;
		stTi = System.nanoTime();
	}
	
	public boolean dptRchd(int Dptlvl)
	{
		return Dptlvl >= mxDptlvl;
	}
    
    public boolean tmOvr() 
    {
        long diff = System.nanoTime() - stTi;
        return diff > mxRnTi;
    }
	
	public void seMxDpt(int level)
	{
		mxDptlvl = level;
	}
}
