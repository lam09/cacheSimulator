package cache;

import java.util.ArrayList;

public class Cache {
	 int size;			/* cache size */
	  int associativity;		/* cache associativity */
	  int n_sets;			/* number of cache sets */
	  long index_mask;		/* mask to find cache index */
	  int index_mask_offset;	/* number of zero bits in mask */
	  ArrayList<CacheLine> lines;	/* head of LRU list for each set */
	  ArrayList<CacheLine> LRU_tail;	/* tail of LRU list for each set */
	  ArrayList<Integer> set_contents;		/* number of valid entries in set */
	  int contents;		/* number of valid entries in cache */
	  
}
