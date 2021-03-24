package andreyskakunenko.rssfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enclosure {

        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("type")
        @Expose
        private String type;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


}
