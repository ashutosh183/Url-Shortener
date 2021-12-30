package urlshortener;

import java.util.*;

public class XUrlImpl  implements XUrl{
    HashMap<String, String>fwdMap = new HashMap<>();
    HashMap<String, String>revMap = new HashMap<>();
    HashMap<String, Integer>hitCount = new HashMap<>();
    int SHORT_URL_LEN = 9;

    public String registerNewUrl(String longUrl) {
        if (fwdMap.containsKey(longUrl)) {
            String val = fwdMap.get(longUrl);
            hitCount.put(longUrl, hitCount.get(longUrl)+1);
            return val;
        }
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer sb = new StringBuffer();
        String shortUrl = null;

        do {
            for (int i = 0; i < SHORT_URL_LEN; ++i) {
                sb.append(charSet.charAt(rand.nextInt(36)));
            }
        } while (fwdMap.containsKey(sb.toString()));
        shortUrl = "http://short.url/"+sb.toString();
        hitCount.put(longUrl, 1);
        fwdMap.put(longUrl, shortUrl);
        revMap.put(shortUrl, longUrl);
        return shortUrl;

    }
    public String registerNewUrl(String longUrl, String shortUrl){
        if(revMap.containsKey(shortUrl)){
            return null;
        }
        fwdMap.put(longUrl, shortUrl);
        revMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String getUrl(String shortUrl){
        if(fwdMap.containsValue(shortUrl)){
            return revMap.get(shortUrl);
        }
        else{
            return null;
        }
    }

    public Integer getHitCount(String longUrl){
        if(!fwdMap.containsKey(longUrl)){
            return 0;
        }
        return hitCount.get(longUrl);
    }

    public String delete(String longUrl){
        fwdMap.remove(longUrl);
        return null;
    }

}
