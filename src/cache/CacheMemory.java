package cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class CacheMemory  {
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
	
	
	
	
	Integer cache_size = 8192 ; //
	Integer cache_block_size = 16;
	Integer cache_data_offset = 4;
	int k; //
	String cache_associativity = "directmapped" ;//(direct mapped)
	String replaacement="write_back";
	String write="write_alocate";
	
	String traceFileName;
	ArrayList<AccessInput> inputs = new ArrayList<AccessInput>();
	
	ArrayList<CacheLine> cacheLines;
	CacheLine[] lines;
	HashMap<Integer, CacheLine> cacheLineMap = new HashMap<Integer, CacheLine>();
	
	Integer count_line = 0;
	
	CacheStat stats_data, stats_ins;
	public CacheMemory() {
	}

	public void set_cache_param(String s) {
		// TODO Auto-generated method stub
		switch (s){

		case "cachesize1":
			cache_size=1024;
			break;

		case "cachesize2":
			cache_size=2048;
			break;

		case "cachesize3":
			cache_size=4096;
			break;
		case "blocksize1":
			cache_block_size=4;
			cache_data_offset=2;
			break;
		case "blocksize2":
			cache_block_size=8;
			cache_data_offset=3;
			break;
		case "writeback":
			replaacement="writeback";
			break;
		case "writethrough":
			replaacement="writethrough";
			break;
		case "direct":
			cache_associativity="directmapped";
			break;
		case "fullassociativity":
			cache_associativity="fullassociativity";
			break;
		default:
			traceFileName=s;
			break;
		}
	}
	public void initCache()
	{
		System.out.println("Cache size: " + cache_size);
		System.out.println("Cache block size: " + cache_block_size);
		System.out.println("Cache replacement policy: " + replaacement);
		System.out.println("Cache associativity: " + cache_associativity);
		
		
		cacheLines = new ArrayList<CacheLine>();
		count_line = cache_size/cache_block_size;
		k=0;
		int pom= count_line/2;

		while(pom>0){
			pom/=2;
			k++;
		}
		System.out.println("Number of block: " +count_line);
		System.out.println("k: " +k);

		if(cache_associativity.compareTo("fullassociativity")==0) cache_data_offset=0;

		lines = new CacheLine[count_line] ;		
		int index = 0;
		for(int i=0;i<count_line;i++){
			lines[i] = new CacheLine(index++);
			lines[i].data_byte_count=cache_data_offset+1;
		}
	}
	
	public void play_trace() {
		// TODO Auto-generated method stub
		stats_data = new CacheStat();
		stats_ins = new CacheStat();

		String fileName =traceFileName;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] input= line.split(" ");
				//inputs.add(new AccessInput(Integer.parseInt(input[0]),Integer.parseInt(input[1],16)));
				AccessInput in = new AccessInput(Integer.parseInt(input[0]),Integer.parseInt(input[1],16));
				trace_in(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void trace_in(AccessInput in) {
		// TODO Auto-generated method stub
		CacheStat stats;
		if(in.type==0 ||in.type==1) stats=stats_data;
		else stats=stats_ins;
		
		stats.accesses++;
	switch (replaacement){
	case "writethrough":
		{
			if(in.type == 0 || in.type ==2 ||in.type==1)
			{
				int index = cache_hit(in);
				if(index >=0)
				{
					// if cache hit
					stats.hit++;
				}
				else
				{
					//cache miss 
					/**
					 * increase miss
					 * get data from main memory to cache
					 * replace data in cache then return to CPU
					 */
					stats.misses++;
					stats.replacements++;
					stats.demand_fetches++;
				}					
			}
			else{
			}
		}
		break;
	case "writeback":
		{
		
		}
		break;
	default:
		break;
	}
	}

	private CacheLine get_replaced_cache_block(AccessInput in) {
		// TODO Auto-generated method stub
		return null;
	}

	private int cache_hit(AccessInput in) {
		// TODO Auto-generated method stub
		switch (cache_associativity){
		case "directmapped":
			{
				CacheLine line;
				int address_without_offset = in.address>> cache_data_offset;
				int index = address_without_offset%(count_line);
				int tag = in.address>>(k+cache_data_offset);

				if(lines[index].tag == tag && lines[index].valid) {					
					return index;
				}
				else {
					lines[index].tag = tag;
					lines[index].index = index;
					lines[index].valid=true;
					return -1;
				}
			}
		case "fullassociativity":
			{
				
				int address_without_offset = in.address>> cache_data_offset;
				int index = in.address%(count_line);
				int tag = in.address>>(k+cache_data_offset);
	/*	System.out.println("address: " +address_without_offset);
				System.out.println("index: " +index);
				System.out.println("tag: " +tag);
*/
				if(lines[index].tag == tag && lines[index].valid) {
					
					return index;
				}
				else {
					lines[index].tag = tag;
					lines[index].index = index;
					lines[index].valid=true;
					return -1;
				}
			}
		default:
			return -1;
		}
	}
	
	private void replace_in_cache(CacheLine line) {
		// TODO Auto-generated method stub
		
	}

	private boolean not_found(AccessInput in) {
		// TODO Auto-generated method stub
		return false;
	}

	public void print_trace_stats() {
		// TODO Auto-generated method stub
		System.out.println("********************** Printf Data stats ***************  ");
		System.out.println("Access: "+stats_data.accesses);
		System.out.println("Miss: " +stats_data.misses);
		System.out.println("Replacement: "+stats_data.replacements);
		System.out.println("Hit: "+ stats_data.hit);
		System.out.println("Copies back from lower memory: "+ stats_data.copies_back);
		System.out.println("Fetches from lower memory: "+ stats_data.demand_fetches);
		System.out.println("********************** Printf Instruction stats ***************  ");
		System.out.println("Access: "+stats_ins.accesses);
		System.out.println("Miss: " +stats_ins.misses);
		System.out.println("Replacement: "+stats_ins.replacements);
		System.out.println("Hit: "+ stats_data.hit);
		System.out.println("Copies back from lower memory: "+ stats_ins.copies_back);
		System.out.println("Fetches from lower memory: "+ stats_ins.demand_fetches);
	}






}