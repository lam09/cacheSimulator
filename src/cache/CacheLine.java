package cache;

public class CacheLine {
	int index;
	Integer tag=-1;
	boolean valid = false;
	int dirty=0;
	int LRU=0; //used for full associative cache
	int data_byte_count;
	public CacheLine(int index)
	{
		this.index=index;
	}
}
