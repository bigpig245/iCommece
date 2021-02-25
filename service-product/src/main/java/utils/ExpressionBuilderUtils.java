package utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;

import java.math.BigDecimal;

public class ExpressionBuilderUtils {
  public static <T> BooleanExpression eqExpression(StringExpression expression, String value) {
    return value == null ? null : expression.equalsIgnoreCase(value);
  }

  public static <T> BooleanExpression likeExpression(StringExpression expression, String value) {
    return value == null ? null : expression.containsIgnoreCase(value);
  }

  public static BooleanExpression betweenExpression(
      NumberPath<BigDecimal> numPath, BigDecimal fromNum, BigDecimal toNum) {
    if (fromNum != null && toNum == null) {
      return numPath.gt(fromNum);
    } else if (fromNum == null && toNum != null) {
      return numPath.lt(toNum);
    } else if (fromNum != null) {
      return numPath.between(fromNum, toNum);
    }
    return null;
  }

}
