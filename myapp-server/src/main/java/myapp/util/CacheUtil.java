package myapp.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myapp.entity.domain.DOBook;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;

public class CacheUtil {

    private static final Logger LOG = LoggerFactory .getLogger(CacheUtil.class);

    public static void testCache(List<DOBook> bookList) {
        CacheManager cm = CacheManager.getInstance();
        net.sf.ehcache.Cache cache = cm.getCache("myapp.model.Book");
        bookList.forEach(e -> cache.put(new Element(e.getBookId(), e)));
    
        // Get the element from cache
        Element ele = cache.get(new Integer(1));
        if (ele != null) {
            DOBook b = (DOBook) ele.getObjectValue();
            LOG.debug("book from cache:" + b);
        } else {
            LOG.debug("book not in cache cache: id:1");
        }
    }

    public static List<String> getCacheInfo() {
        List<String> cacheInfo = new ArrayList<>();
        List<CacheManager> tempManagers = CacheManager.ALL_CACHE_MANAGERS;
        cacheInfo.add("Number of CacheManagers:" + tempManagers.size());
    
        for (CacheManager tempCM : tempManagers) {
            cacheInfo.add("Cache Manager Name:" + tempCM.getName());
            cacheInfo.add("ActiveConfigurationText:"
                    + tempCM.getActiveConfigurationText());
            String[] cacheNames = tempCM.getCacheNames();
            cacheInfo.add("*********************************************");
    
            for (int i = 0; i < cacheNames.length; i++) {
    
                String cacheName = cacheNames[i];
                cacheInfo.add("******" + cacheName + "******");
                Ehcache cache = tempCM.getEhcache(cacheName);
                cacheInfo.add("MaxEntriesLocalHeap:"
                        + cache.getCacheConfiguration()
                                .getMaxEntriesLocalHeap());
                StatisticsGateway stats = cache.getStatistics();
                cacheInfo.add("localHeapSizeInBytes:"
                        + stats.getLocalHeapSizeInBytes());
                cacheInfo.add("cacheHitCount:" + stats.cacheHitCount());
                cacheInfo.add("cacheMissCount:" + stats.cacheMissCount());
                cacheInfo.add("*********************************************");
            }
    
        }
        return cacheInfo;
    }

}
