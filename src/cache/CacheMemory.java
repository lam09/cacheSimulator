package cache;

public class CacheMemory implements CacheFunction {
	public static int TRUE= 1;
	public static int FALSE= 0;

	public static int TRACE_DATA_LOAD =0;
	public static int TRACE_DATA_STORE =1;
	public static int TRACE_INST_LOAD =2;
	
	/* default cache parameters--can be changed */
	public static int WORD_SIZE= 4;
	public static int WORD_SIZE_OFFSET= 2;
	public static int DEFAULT_CACHE_SIZE= (8 * 1024);
	public static int DEFAULT_CACHE_BLOCK_SIZE= 16;
	public static int DEFAULT_CACHE_ASSOC= 1;
	public static int DEFAULT_CACHE_WRITEBACK= TRUE;
	public static int DEFAULT_CACHE_WRITEALLOC= TRUE;

	/* constants for settting cache parameters */
	public static int CACHE_PARAM_BLOCK_SIZE= 0;
	public static int CACHE_PARAM_USIZE =1;
	public static int CACHE_PARAM_ISIZE =2;
	public static int CACHE_PARAM_DSIZE =3;
	public static int CACHE_PARAM_ASSOC =4;
	public static int CACHE_PARAM_WRITEBACK =5;
	public static int CACHE_PARAM_WRITETHROUGH =6;
	public static int CACHE_PARAM_WRITEALLOC =7;
	public static int CACHE_PARAM_NOWRITEALLOC =8;
	
	
	
	
	Integer cache_size = 8192 ;
	Integer cache_block_size = 16;
	int cache_associativity = 1 ;//(direct mapped)
	String replaacement="write_back";
	String write="write_alocate";
	
	
	Cache cache;
	
	
	public CacheMemory() {
		cache = new Cache();
	}
	@Override
	public void set_cache_param() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init_cache() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void perform_access() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(CacheLine line) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dump_settings() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print_stats() {
		// TODO Auto-generated method stub
		
	}
}