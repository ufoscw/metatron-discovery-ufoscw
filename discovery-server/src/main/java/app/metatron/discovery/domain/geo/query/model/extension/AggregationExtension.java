package app.metatron.discovery.domain.geo.query.model.extension;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.thymeleaf.util.StringUtils;

import java.util.List;

import app.metatron.discovery.common.GlobalObjectMapper;
import app.metatron.discovery.query.druid.Aggregation;
import app.metatron.discovery.query.druid.Dimension;
import app.metatron.discovery.query.druid.PostAggregation;

public class AggregationExtension implements DruidExtension {

  List<Dimension> dimensions;

  List<Aggregation> aggregators;

  List<PostAggregation> postAggregators;

  String boundary;

  String boundaryJoin;

  @JsonCreator
  public AggregationExtension(
      @JsonProperty("dimensions") List<Dimension> dimensions,
      @JsonProperty("aggregators") List<Aggregation> aggregators,
      @JsonProperty("postAggregators") List<PostAggregation> postAggregators,
      @JsonProperty("boundary") String boundary,
      @JsonProperty("boundaryJoin") String boundaryJoin) {
    this.dimensions = dimensions;
    this.aggregators = aggregators;
    this.postAggregators = postAggregators;
    this.boundary = boundary;
    this.boundaryJoin = boundaryJoin;
  }

  public List<Dimension> getDimensions() {
    return dimensions;
  }

  public List<Aggregation> getAggregators() {
    return aggregators;
  }

  public List<PostAggregation> getPostAggregators() {
    return postAggregators;
  }

  public String getBoundary() {
    return boundary;
  }

  public String getBoundaryJoin() {
    return boundaryJoin;
  }

  @Override
  public String toParamString() {
    String queryStr = GlobalObjectMapper.writeValueAsString(this);

    queryStr = StringUtils.replace(queryStr, ",", "\\,");

    return "druid:" + queryStr;
  }
}
