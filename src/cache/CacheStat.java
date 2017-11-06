package cache;

public class CacheStat {
	 int accesses;			/* number of memory references */
	  int misses;			/* number of cache misses */
	  int replacements;		/* number of misses that cause replacments */
	  int demand_fetches;		/* number of fetches */
	  int copies_back;		/* number of write backs */
}
