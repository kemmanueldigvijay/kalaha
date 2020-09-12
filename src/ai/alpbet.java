package ai;

import kalaha.GameState;

public class alpbet 
{
	private final int ply;
	private final GameState brd;
	private int utlVal = 0;
	private int bMov = -1;
	
	public alpbet(GameState brd, int ply)
	{
		this.brd = brd;
		this.ply = ply;
        rstUtlVal();
	}
	
	public int gtUtlVal()
	{
		return utlVal;
	}
	
	public int geBeMv()
	{
		return bMov;
	}
	
	public int visit(int dptlvl, rec rec, int mxzr, int mnzr)
	{
		if(rec.dptRchd(dptlvl)) 
			return calBrdUtlVal();
		
        boolean isUtlValAss = false;
      
        for(int mvIdx = 6; mvIdx >= 1; mvIdx--)
        {
            if(rec.tmOvr()) break;
            
            if(brd.moveIsPossible(mvIdx))
            {   GameState nextBoard = brd.clone();
                nextBoard.makeMove(mvIdx);
                alpbet nextNode = new alpbet(nextBoard, ply);
                int value = nextNode.visit(dptlvl + 1, rec, mxzr, mnzr);
                uptUtlVal(value, mvIdx);
                
                if (iMxNd()) 
                {if (value > mnzr) break;
                    mxzr = Math.max(value, mxzr);
                }
                else 
                {
                    if (value < mxzr) break;
                    mnzr = Math.min(value, mnzr);
                }
                isUtlValAss = true;
            }
        }
        
        if (!isUtlValAss) 
        {
            return calBrdUtlVal();
        } 
        else 
        {
            return utlVal;
        }
	}
    
    private void rstUtlVal () 
    {
		if(iMxNd())
		{
			utlVal = Integer.MIN_VALUE;
		}
		else
		{
			utlVal = Integer.MAX_VALUE;
		}
    }
	
	private void uptUtlVal(int nxtNdUtlVal, int mv) 
	{
        if(iMxNd())
        {
            if(nxtNdUtlVal > utlVal)
            {
                utlVal = nxtNdUtlVal;
                bMov = mv;
            }
        } 
        else 
        {
            if(nxtNdUtlVal < utlVal)
            {
                utlVal = nxtNdUtlVal;
                bMov = mv;
            }
        }
	}
        private int calBrdUtlVal()
	{
		int pl1Sc = brd.getScore(ply);
        int pl2Sc = brd.getScore((ply % 2) + 1);
        utlVal = pl1Sc - pl2Sc;
        
		return utlVal;
	}
    
    private boolean iMxNd() 
    {
        return brd.getNextPlayer() == ply;
    }
}
