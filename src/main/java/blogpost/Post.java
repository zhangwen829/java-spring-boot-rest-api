package blogpost;

import lombok.Data;
import java.util.List;

@Data
public class Post {
  private long id;
  private String author;
  private int likes;
  private float popularity;
  private long reads;
  private List<String> tags;
}