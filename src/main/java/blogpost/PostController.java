package blogpost;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Comparator;
// import com.google.common.collect.ImmutableMap;

@RestController
public class PostController {
  private static String URL_TMPL = "INSERT URL HERE";
  private final RestTemplate restTemplate;

  // TODO: comparator function
  /*
   * private final static ImmutableMap<String, Comparator<Post>> SORT_FUNC = ImmutableMap.of("id",
   * (p1, p2) -> p1.getId().intValue() - p2.getId().intValue(), "reads", (p1, p2) -> p1.getReads() -
   * p2.getReads(), "likes", (p1, p2) -> p1.getLikes() - p2.getLikes(), "popularity", (p1, p2) ->
   * (int) (p1.getPopularities() - p2.getPopularities()));
   */

  @Autowired
  PostController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @RequestMapping("/api/posts")
  public List<Post> searchPosts(@RequestParam(value = "tags") List<String> tags,
      @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
      @RequestParam(value = "direction", defaultValue = "asc") String direction) {
    List<Post> posts = tags.stream()
        .map(tag -> restTemplate.getForObject(String.format(URL_TMPL, tag), Posts.class).getPosts())
        .flatMap(p -> p.stream()).distinct().collect(Collectors.toList());


    return posts;
  }
}
