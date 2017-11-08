package cache;

public class CacheStat {
	  int accesses=0;			/* number of memory references */
	  int hit = 0;
	  int misses=0;			/* number of cache misses */
	  int replacements=0;		/* number of misses that cause replacments */
	  int demand_fetches=0;		/* number of fetches from lower memory */
	  int copies_back=0;		/* number of write backs to lower memory */
}
