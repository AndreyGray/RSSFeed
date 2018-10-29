package andreyskakunenko.rssfeed.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RSSObject {

    @SerializedName("feed")
    @Expose
    private Feed feed;
    @SerializedName("items")
    @Expose
    public List<Item> items;

    public RSSObject( Feed feed, List<Item> items) {
        this.feed = feed;
        this.items = items;
    }


    public List<Item> getItems() {
        return items;
    }
}

