package metrices.jmx;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import com.sun.management.OperatingSystemMXBean;

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

		MemoryUsage nonHeapMem = memoryMXBean.getNonHeapMemoryUsage();

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
	
	@SuppressWarnings("deprecation")
	public JSONObject getCPULoadAndMEM() throws JSONException {
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
		
		JSONObject cpu = new JSONObject();

		cpu.put("JVM_CPU_%", osBean.getProcessCpuLoad());
		cpu.put("TOTAL_CPU_%", osBean.getSystemCpuLoad());
		
		cpu.put("FREE_MEMORY", osBean.getFreePhysicalMemorySize());
		cpu.put("FREE_SWAP_MEMORY", osBean.getFreeSwapSpaceSize());
		cpu.put("TOTAL_SWAP_MEMORY", osBean.getTotalSwapSpaceSize());
		cpu.put("TOTAL_MEMORY", osBean.getTotalPhysicalMemorySize());
		cpu.put("SYS_LOAD_AVG", osBean.getSystemLoadAverage());
		
		return cpu;
		
		
	}
	
	public JSONObject getDiskSizeMetrices(String drivePath) throws JSONException {
		
		JSONObject disk = new JSONObject();
		
		File drive = new File(drivePath);
		disk.put("TOTAL", drive.getTotalSpace());
		disk.put("FREE", drive.getFreeSpace());
		disk.put("USABLE", drive.getUsableSpace());
		
		return disk;
	}
	
}
