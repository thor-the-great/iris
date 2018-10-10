package iris.service;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

public class DataReadService {

    private static String _DOLLAR_SIGN;

    static {
        try {
            _DOLLAR_SIGN = URLEncoder.encode("$", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public List<DataItem> readData(String rssUrl) {
        List<DataItem> res = new LinkedList();
        try {
            URL feedUrl = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            List<SyndEntryImpl> entries = feed.getEntries();
            for (SyndEntryImpl entry : entries) {
                DataItem item = new DataItem();
                String title = entry.getTitle().replace("&#x0024;", "$");
                item.setTitle(title);
                if (title.lastIndexOf("$") >= 0) {
                    item.setPrice(Integer.parseInt(title.substring(title.lastIndexOf("$") + 1)));
                }
                res.add(item);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return res;
    }

    public class DataItem {
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        String title;
        int price;
    }

    public static void main(String[] args) {
        DataReadService obj = new DataReadService();
        List<DataItem> entries = obj.readData("https://sfbay.craigslist.org/search/sss?format=rss&query=iphone");
        for (DataItem item : entries) {
            System.out.println(item.getPrice() + "$ " + item.title);
        }
    }
}
