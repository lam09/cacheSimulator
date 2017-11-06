package cache;

public interface CacheFunction {
	/* function prototypes */
	public void set_cache_param();
	public void init_cache();
	public void perform_access();
	public void flush();
	public void delete(CacheLine line);
	public void insert();
	public void dump_settings();
	public void print_stats();

}
