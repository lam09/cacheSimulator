package cache;

public class Main {
	static CacheMemory cacheMemory;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheMemory cacheMemory = new CacheMemory();
		for(String s:args){
			//System.out.print(s);
			cacheMemory.set_cache_param(s);
		}
		cacheMemory.initCache();
		cacheMemory.play_trace();
		cacheMemory.print_trace_stats();
		
	}

}
