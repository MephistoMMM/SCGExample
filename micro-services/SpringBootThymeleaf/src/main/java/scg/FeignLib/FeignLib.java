package scg.FeignLib;

import org.springframework.beans.factory.annotation.Autowired;

public class FeignLib {
	@Autowired
    TimeRemote time;
	@Autowired
    ImageRemote image;
	@Autowired
    AddrRemote addr;
	
	private static FeignLib feignlib = new FeignLib();
	
	public static String timeContent(String key) {
		return feignlib.time.content(key);
	}
	public static String imageContent(String key) {
		return feignlib.image.content(key);
	}
	public static String addrContent(String key) {
		return feignlib.addr.content(key);
	}
}
