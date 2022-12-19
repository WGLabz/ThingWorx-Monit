package metrices.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JMXMetrices {
	private MemoryMXBean memoryMXBean;
	private ThreadMXBean threadMXBean;

//	private double FACTOR = (1 / 1073741824);
	
	public JSONObject getMetrices() throws JSONException {
		
		this.memoryMXBean = ManagementFactory.getMemoryMXBean();
		this.threadMXBean = ManagementFactory.getThreadMXBean();
		
// 		Final Response object
		JSONObject response = new JSONObject();
		
		
		JSONObject memory = new JSONObject();
		
//		HEAP Memory Usage values are in Bytes
		
		MemoryUsage heapMem = memoryMXBean.getHeapMemoryUsage();

		memory.put("INITIAL_HEAP",heapMem.getInit() );
		memory.put("USED_HEAP",heapMem.getUsed() );
		memory.put("MAX_HEAP",heapMem.getMax() );
		memory.put("COMMITED_HEAP",heapMem.getCommitted());	
		
		
// 		Non-HEAP Usage	values are in Bytes

		MemoryUsage nonHeapMem = memoryMXBean.getHeapMemoryUsage();

		memory.put("INITIAL_NON_HEAP",nonHeapMem.getInit() );
		memory.put("USED_NON_HEAP",nonHeapMem.getUsed() );
		memory.put("MAX_NON_HEAP",nonHeapMem.getMax() );
		memory.put("COMMITED_NON_HEAP",nonHeapMem.getCommitted());	
		
//		CPU Usage

		JSONArray threads = new JSONArray();
		
		
		for(Long threadID : threadMXBean.getAllThreadIds()) {
			JSONObject thread = new JSONObject();
			
		    ThreadInfo info = threadMXBean.getThreadInfo(threadID);
		    
		    thread.put("NAME", info.getThreadName());
		    thread.put("STATE",info.getThreadState());
		    thread.put("CPU_TIME",threadMXBean.getThreadCpuTime(threadID));  // In nanoseconds
		    
		    threads.put(thread);
		  }

		
//		Populate the response Object
		response.put("MEM", memory);
		response.put("THREADS", threads);
		
		return response;
	}
	
	
}
