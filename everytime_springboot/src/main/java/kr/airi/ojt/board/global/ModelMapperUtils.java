package kr.airi.ojt.board.global;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {

  private final ModelMapper modelMapper;

  private ModelMapperUtils() {
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  private static class ModelMapperUtilsHolder {
    private static final ModelMapperUtils INSTANCE = new ModelMapperUtils();
  }

  public static ModelMapperUtils getInstance() {
    return ModelMapperUtilsHolder.INSTANCE;
  }

  public <D, T> void addMappings(PropertyMap<T, D> addMap) {
    this.modelMapper.addMappings(addMap);
  }

  public <D, T> D map(final T entity, Class<D> outClass) {
    return this.modelMapper.map(entity, outClass);
  }

  public <S, D> D map(final S source, D destination) {
    this.modelMapper.map(source, destination);
    return destination;
  }

  public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass) {
    return entityList
        .stream()
        .map(entity -> map(entity, outClass))
        .collect(Collectors.toList());
  }

}
